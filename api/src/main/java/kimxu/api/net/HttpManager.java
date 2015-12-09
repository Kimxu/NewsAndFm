package kimxu.api.net;

import android.content.Context;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import android.util.Log;

import org.apache.http.Header;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.params.CoreProtocolPNames;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.zip.GZIPInputStream;

import kimxu.api.net.model.IRequest;
import kimxu.api.net.model.QueuedRequest;
import kimxu.api.net.utils.NetworkUtil;

public class HttpManager implements INet{


    public static final int MSGCODE_HTTP_ERROR = 0;
    public static final int MSGCODE_HTTP_RESPONSE = 1;
    private static final int BUFSIZE = 1024;
    private static INet httpManager;
    private Context mContext;

    private class Downloader extends HandlerThread {
        private int id;
        HttpClient httpClient;

        public Downloader(int id) {
            super("worker" + id);
            this.id = id;
        }

        public void init() {
            httpClient = new IWHttpsClient(mContext);
            threadHandlers[id] = new Handler(getLooper()) {
                public void handleMessage(Message msg) {
                    // 下载前判断参数是否为空
                    if (msg != null && msg.obj != null) {
                        try {
                            doDownload((QueuedRequest) msg.obj);
                        } catch (Exception e) {
                            Log.i("http_result", "Exception! " + e.getMessage());
                            // e.printStackTrace();
                            return;
                        }
                    }

                    // Clear the busy flag
                    availableWorkingThreadBits &= (~(1 << id));
                }
            };
        }

        private void doDownload(QueuedRequest r) {

            boolean error = false;
            HttpUriRequest request = null;

            if (r.requestType == QueuedRequest.requestTypeApi) {
                HttpPost postRequest = new HttpPost(r.url);
                if (r.httpHeader!=null){
                    for (Map.Entry<String, String> entry : r.httpHeader.entrySet()) {
                        String key = entry.getKey();
                        String val = entry.getValue();
                        postRequest.setHeader(key, val);
                    }
                }
                postRequest.addHeader("Accept-Encoding", "gzip");
                postRequest.getParams().setBooleanParameter(
                        CoreProtocolPNames.USE_EXPECT_CONTINUE, false);
                try {
                    postRequest.setEntity(new UrlEncodedFormEntity(
                            r.nameValuePairs, "utf-8"));
                } catch (UnsupportedEncodingException e) {
                    error = true;
                }
                request = postRequest;
            } else if (r.requestType == QueuedRequest.requestTypeLog) {
                HttpPost postRequest = new HttpPost(r.url);
                if (r.httpHeader!=null){
                    for (Map.Entry<String, String> entry : r.httpHeader.entrySet()) {
                        String key = entry.getKey();
                        String val = entry.getValue();
                        postRequest.setHeader(key, val);
                    }
                }
                postRequest.getParams().setBooleanParameter(
                        CoreProtocolPNames.USE_EXPECT_CONTINUE, false);
                try {
                    postRequest.setEntity(new UrlEncodedFormEntity(
                            r.nameValuePairs, "utf-8"));
                } catch (UnsupportedEncodingException e) {
                    error = true;
                }
                request = postRequest;
            } else if (r.requestType == QueuedRequest.requestTypeGetApi) {
                HttpGet getRequest = new HttpGet(r.url);
                if (r.httpHeader!=null){
                    for (Map.Entry<String, String> entry : r.httpHeader.entrySet()) {
                        String key = entry.getKey();
                        String val = entry.getValue();
                        getRequest.setHeader(key, val);
                    }
                }
                request = getRequest;
            }

            // FIXED YYH-332 WangGang
            NetworkUtil.resetHttpClientWap(mContext, httpClient);
            if (error == false
                    && (r.requestType == QueuedRequest.requestTypeApi || r.requestType == QueuedRequest.requestTypeGetApi)) {
                try {
                    StringBuffer sb = new StringBuffer();
                    HttpResponse response = httpClient.execute(request);
                    StatusLine status = response.getStatusLine();
                    if (status != null) {
                        r.responseHttpCode = status.getStatusCode();
                    }
                    Header encodeHeader;
                    String encode = "";
                    if ((encodeHeader = response
                            .getFirstHeader("Content-Encoding")) != null) {
                        encode = encodeHeader.getValue();
                    }

                    InputStreamReader reader;
                    if (encode.equals("gzip")) {
                        reader = new InputStreamReader(new GZIPInputStream(
                                response.getEntity().getContent()));
                    } else {
                        reader = new InputStreamReader(response.getEntity()
                                .getContent(), "utf-8");
                    }

                    char buffer[] = new char[BUFSIZE];
                    int count;
                    while ((count = reader.read(buffer, 0, buffer.length)) != -1) {
                        sb.append(buffer, 0, count);
                    }
                    r.result = sb.toString();
                    reader.close();
                } catch (ClientProtocolException e) {
                    error = true;
                } catch (IOException e) {
                    error = true;
                } catch (Throwable e) {
                    error = true;
                }
            } else if (error == false
                    && r.requestType == QueuedRequest.requestTypeLog) {
                try {
                    HttpResponse response = httpClient.execute(request);
                    r.result = 0;
                    if (response != null) {
                        StatusLine status = response.getStatusLine();
                        if (status != null) {
                            int code = status.getStatusCode();
                            if (code == 200) {
                                r.result = 1;
                            }
                            r.responseHttpCode = code;
                        }
                    }
                } catch (ClientProtocolException e) {
                    error = true;
                } catch (IOException e) {
                    error = true;
                } catch (Throwable e) {
                    error = true;
                }
            } else {
                error = true;
            }

            // Try to send the success/failure message using handler
            if (error == false) {
                // HTTP success response
                if (r.handler == null) {
                    Log.i("http_result", "Success with no response");
                    return;
                }
                r.handler.sendMessage(r.handler.obtainMessage(
                        MSGCODE_HTTP_RESPONSE, r));
                Log.i("http_result", "Success with response");
                Log.i("http_result", "result: " + r.result);
                return;
            }

            // Some problem is met in HTTP response, retry several times
            if (r.retry > 0) {
                r.retry--;
                download(r, 1000);
                return;
            }

            if (r.handler == null) {
                // No handler, don't change the status, just discard the
                // request.
                Log.i("http_result", "Failed with no response");
                return;
            }

            Log.i("http_result", "Failed");
            r.handler.sendMessage(r.handler
                    .obtainMessage(MSGCODE_HTTP_ERROR, r));
        }
    }

    private int availableWorkingThreadBits = 0;
    private final static int numWorkingThreads = 6;
    private final static int allWorkingThreadBits = 0x3f;

    private Handler mHandler;
    private Handler[] threadHandlers = new Handler[numWorkingThreads];

    private class Dispatcher extends HandlerThread {
        public Dispatcher() {
            super("dispatcher");
        }

        public void init() {
            mHandler = new Handler(getLooper()) {
                public void handleMessage(Message msg) {
                    processMessage(msg);
                }
            };
        }

        private void processMessage(Message msg) {
            if (availableWorkingThreadBits != allWorkingThreadBits) {
                // There's available thread, find it
                int threadid = -1;
                for (int i = 0; i < numWorkingThreads; ++i) {
                    if ((availableWorkingThreadBits & (1 << i)) == 0) {
                        threadid = i;
                        break;
                    }
                }

                // Send the queued request to the worker thread.
                availableWorkingThreadBits |= 1 << threadid;

                Message msg2 = Message.obtain();
                msg2.obj = msg.obj;
                threadHandlers[threadid].sendMessage(msg2);
            } else {
                // Send a timer message to self 1 second later.
                Message msg2 = Message.obtain();
                msg2.obj = msg.obj;
                mHandler.sendMessageDelayed(msg2, 1000);
            }
        }
    }

    private HttpManager(Context context) {
        mContext = context;

        // Init all worker threads
        for (int i = 0; i < numWorkingThreads; ++i) {
            Downloader worker = new Downloader(i);
            worker.setDaemon(true);
            worker.start();
            worker.init();
        }

        // Init dispatcher thread
        Dispatcher managerThread = new Dispatcher();
        managerThread.setDaemon(true);
        managerThread.start();
        managerThread.init();
    }

    public static INet getInstance(Context context) {
        synchronized (HttpManager.class) {
            if (httpManager == null) {
                httpManager = new HttpManager(context);
            }
        }
        return httpManager;
    }

    private void download(QueuedRequest qr) {
        Message msg = Message.obtain();
        msg.obj = qr;
        mHandler.sendMessage(msg);
    }

    private void download(QueuedRequest qr, int millisec) {
        Message msg = Message.obtain();
        msg.obj = qr;
        mHandler.sendMessageDelayed(msg, millisec);
    }


    @Override
    public void addApiReqest(List<NameValuePair> nameValuePairs,
                             String apiHost, Handler handler, int requestId) {
        QueuedRequest qr = new QueuedRequest();
        qr.requestType = QueuedRequest.requestTypeApi;
        qr.requestId = requestId;
        qr.url = apiHost;
        Log.i("post request_url is ", qr.url);
        qr.nameValuePairs = nameValuePairs;
        qr.handler = handler;
        download(qr);
    }

    @Override
    public void addApiReqest(List<NameValuePair> nameValuePairs, String apiHost, Handler handler, HashMap<String, String> httpHeader, int requestId) {
        QueuedRequest qr = new QueuedRequest();
        qr.requestType = QueuedRequest.requestTypeApi;
        qr.httpHeader=httpHeader;
        qr.requestId = requestId;
        qr.url = apiHost;
        Log.i("post request_url is ", qr.url);
        qr.nameValuePairs = nameValuePairs;
        qr.handler = handler;
        download(qr);
    }

    @Override
    public void addApiGetRequest(String apiHost, Handler handler, HashMap<String,String> httpHeader, int requestId) {
        QueuedRequest qr = new QueuedRequest();
        qr.httpHeader =httpHeader;
        qr.requestType = QueuedRequest.requestTypeGetApi;
        qr.requestId = requestId;
        qr.url = apiHost;
        Log.i("get request_url is ", qr.url);
        qr.nameValuePairs = null;
        qr.handler = handler;
        download(qr);
    }

    @Override
    public void addApiGetRequest(String apiHost, Handler handler, int requestId) {
        QueuedRequest qr = new QueuedRequest();
        qr.requestType = QueuedRequest.requestTypeGetApi;
        qr.requestId = requestId;
        qr.url = apiHost;
        Log.i("get request_url is ", qr.url);
        qr.nameValuePairs = null;
        qr.handler = handler;
        download(qr);
    }

    @Override
    public void addApiRequest(IRequest request) {
        QueuedRequest qr = (QueuedRequest)request;
        download(qr);
    }
}

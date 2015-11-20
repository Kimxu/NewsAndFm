package kimxu.core.net.model;

import android.os.Handler;

import org.apache.http.NameValuePair;

import java.util.List;

import kimxu.api.net.model.QueuedRequest;

public class CQueuedRequest {
    private static final int MAX_RETRY_TIMES = 5;
    public int responseHttpCode;
    public int requestType;
    public int requestId;
    public String url;
    public List<NameValuePair> nameValuePairs;
    public Handler handler;
    public Object result;
    public int retry = MAX_RETRY_TIMES;

    public CQueuedRequest(Object obj) {
        QueuedRequest queuedRequest = (QueuedRequest) obj;
        responseHttpCode = queuedRequest.responseHttpCode;
        requestType = queuedRequest.requestType;
        url = queuedRequest.url;
        handler = queuedRequest.handler;
        result = queuedRequest.result;
        retry = queuedRequest.retry;
        nameValuePairs = queuedRequest.nameValuePairs;
    }

}
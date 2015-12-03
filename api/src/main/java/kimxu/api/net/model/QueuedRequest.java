package kimxu.api.net.model;

import android.os.Handler;

import org.apache.http.NameValuePair;

import java.util.HashMap;
import java.util.List;

public class QueuedRequest implements IRequest{

    private static final int MAX_RETRY_TIMES = 5;
    public static final int requestTypeApi = 1;
    public static final int requestTypeLog = 3;
    public static final int requestTypeGetApi = 4;

    public int responseHttpCode;
    public int requestType;
    public int requestId;
    public String url;
    public List<NameValuePair> nameValuePairs;
    public Handler handler;
    public Object result;
    public HashMap<String,String> httpHeader;
    public int retry = MAX_RETRY_TIMES;
}
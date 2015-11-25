package kimxu.core.net.model;

import org.apache.http.NameValuePair;

import java.util.List;

import kimxu.api.net.model.QueuedRequest;

public class CQueuedRequest {
    public int responseHttpCode;
    public int requestType;
    public int requestId;
    public String url;
    public List<NameValuePair> nameValuePairs;
    public Object result;

    public CQueuedRequest(Object obj) {
        QueuedRequest queuedRequest = (QueuedRequest) obj;
        responseHttpCode = queuedRequest.responseHttpCode;
        requestType = queuedRequest.requestType;
        url = queuedRequest.url;
        requestId=queuedRequest.requestId;
        result = queuedRequest.result;
        nameValuePairs = queuedRequest.nameValuePairs;
    }

}
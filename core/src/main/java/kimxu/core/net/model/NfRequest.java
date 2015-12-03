package kimxu.core.net.model;

import kimxu.api.net.model.QueuedRequest;

public class NfRequest extends QueuedRequest {

    /**
     * 接受解析
     * @param obj
     */
    public NfRequest(Object obj) {
        QueuedRequest request = (QueuedRequest) obj;
//        responseHttpCode=request.responseHttpCode;
//        requestType=request.requestType;
//        nameValuePairs=request.nameValuePairs;
//        handler=request.handler;
//        httpHeader=request.httpHeader;
//        url=request.url;
        requestId = request.requestId;
        result = request.result;
    }
}
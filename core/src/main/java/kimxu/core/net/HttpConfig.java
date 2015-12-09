package kimxu.core.net;

import kimxu.api.net.HttpManager;

public class HttpConfig {

     /**
      * 请求链接
      */
     final static String URL_WEINEWS_URL = "http://api.huceo.com/wxnew/other/";
     final static String URL_YIDIAN_HOT_URL = "http://124.243.203.100/Website/channel/news-list-for-hot-channel";
     final static String URL_YIDIAN_URL = "http://124.243.203.100/Website/channel/news-list-for-channel";


    public final static int MSGCODE_HTTP_ERROR =HttpManager.MSGCODE_HTTP_ERROR;
    public final static int MSGCODE_HTTP_RESPONSE =HttpManager.MSGCODE_HTTP_RESPONSE;


    //社会
    final static String SITE_SHEHUI = "1655594054";
    //科技
    public final static String SITE_KEJI = "1655594078";
    //段子
    final static String SITE_DUANZI = "1655594086";


}

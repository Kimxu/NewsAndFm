package kimxu.newsandfm.model;

import java.util.List;

/**
 * 一点资讯新闻实体
 * Created by xuzhiguo on 15/11/25.
 */
public class News {

    private String status;
    private int code;
    private int fresh_count;
    private String search_hint;
    private String channel_name;
    private String channel_type;
    private String channel_image;

    private List<ResultEntity> result;

    public void setStatus(String status) {
        this.status = status;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public void setFresh_count(int fresh_count) {
        this.fresh_count = fresh_count;
    }

    public void setSearch_hint(String search_hint) {
        this.search_hint = search_hint;
    }

    public void setChannel_name(String channel_name) {
        this.channel_name = channel_name;
    }

    public void setChannel_type(String channel_type) {
        this.channel_type = channel_type;
    }

    public void setChannel_image(String channel_image) {
        this.channel_image = channel_image;
    }

    public void setResult(List<ResultEntity> result) {
        this.result = result;
    }

    public String getStatus() {
        return status;
    }

    public int getCode() {
        return code;
    }

    public int getFresh_count() {
        return fresh_count;
    }

    public String getSearch_hint() {
        return search_hint;
    }

    public String getChannel_name() {
        return channel_name;
    }

    public String getChannel_type() {
        return channel_type;
    }

    public String getChannel_image() {
        return channel_image;
    }

    public List<ResultEntity> getResult() {
        return result;
    }

    public static class ResultEntity {
        private String ctype;
        private String title;
        private String dtype;
        private String meta;
        private String date;
        private String docid;
        private String url;
        private String mtype;
        private String image;

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }

        public String getCtype() {
            return ctype;
        }

        public void setCtype(String ctype) {
            this.ctype = ctype;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getDtype() {
            return dtype;
        }

        public void setDtype(String dtype) {
            this.dtype = dtype;
        }

        public String getMeta() {
            return meta;
        }

        public void setMeta(String meta) {
            this.meta = meta;
        }

        public String getDate() {
            return date;
        }

        public void setDate(String date) {
            this.date = date;
        }

        public String getDocid() {
            return docid;
        }

        public void setDocid(String docid) {
            this.docid = docid;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getMtype() {
            return mtype;
        }

        public void setMtype(String mtype) {
            this.mtype = mtype;
        }
    }
}

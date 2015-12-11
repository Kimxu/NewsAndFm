
package kimxu.core.net.model.news;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class Result {

    @SerializedName("ctype")
    @Expose
    private String ctype;
    @SerializedName("date")
    @Expose
    private String date;
    @SerializedName("docid")
    @Expose
    private String docid;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("meta")
    @Expose
    private String meta;
    @SerializedName("comment_count")
    @Expose
    private long commentCount;
    @SerializedName("down")
    @Expose
    private long down;
    @SerializedName("like")
    @Expose
    private long like;
    @SerializedName("source")
    @Expose
    private String source;
    @SerializedName("summary")
    @Expose
    private String summary;
    @SerializedName("up")
    @Expose
    private long up;
    @SerializedName("dtype")
    @Expose
    private long dtype;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("channels")
    @Expose
    private List<Channel> channels = new ArrayList<Channel>();
    @SerializedName("url")
    @Expose
    private String url;
    @SerializedName("image")
    @Expose
    private String image;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    /**
     * 
     * @return
     *     The ctype
     */
    public String getCtype() {
        return ctype;
    }

    /**
     * 
     * @param ctype
     *     The ctype
     */
    public void setCtype(String ctype) {
        this.ctype = ctype;
    }

    /**
     * 
     * @return
     *     The date
     */
    public String getDate() {
        return date;
    }

    /**
     * 
     * @param date
     *     The date
     */
    public void setDate(String date) {
        this.date = date;
    }

    /**
     * 
     * @return
     *     The docid
     */
    public String getDocid() {
        return docid;
    }

    /**
     * 
     * @param docid
     *     The docid
     */
    public void setDocid(String docid) {
        this.docid = docid;
    }

    /**
     * 
     * @return
     *     The title
     */
    public String getTitle() {
        return title;
    }

    /**
     * 
     * @param title
     *     The title
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * 
     * @return
     *     The meta
     */
    public String getMeta() {
        return meta;
    }

    /**
     * 
     * @param meta
     *     The meta
     */
    public void setMeta(String meta) {
        this.meta = meta;
    }

    /**
     * 
     * @return
     *     The commentCount
     */
    public long getCommentCount() {
        return commentCount;
    }

    /**
     * 
     * @param commentCount
     *     The comment_count
     */
    public void setCommentCount(long commentCount) {
        this.commentCount = commentCount;
    }

    /**
     * 
     * @return
     *     The down
     */
    public long getDown() {
        return down;
    }

    /**
     * 
     * @param down
     *     The down
     */
    public void setDown(long down) {
        this.down = down;
    }

    /**
     * 
     * @return
     *     The like
     */
    public long getLike() {
        return like;
    }

    /**
     * 
     * @param like
     *     The like
     */
    public void setLike(long like) {
        this.like = like;
    }

    /**
     * 
     * @return
     *     The source
     */
    public String getSource() {
        return source;
    }

    /**
     * 
     * @param source
     *     The source
     */
    public void setSource(String source) {
        this.source = source;
    }

    /**
     * 
     * @return
     *     The summary
     */
    public String getSummary() {
        return summary;
    }

    /**
     * 
     * @param summary
     *     The summary
     */
    public void setSummary(String summary) {
        this.summary = summary;
    }

    /**
     * 
     * @return
     *     The up
     */
    public long getUp() {
        return up;
    }

    /**
     * 
     * @param up
     *     The up
     */
    public void setUp(long up) {
        this.up = up;
    }

    /**
     * 
     * @return
     *     The dtype
     */
    public long getDtype() {
        return dtype;
    }

    /**
     * 
     * @param dtype
     *     The dtype
     */
    public void setDtype(long dtype) {
        this.dtype = dtype;
    }

    /**
     * 
     * @return
     *     The description
     */
    public String getDescription() {
        return description;
    }

    /**
     * 
     * @param description
     *     The description
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * 
     * @return
     *     The channels
     */
    public List<Channel> getChannels() {
        return channels;
    }

    /**
     * 
     * @param channels
     *     The channels
     */
    public void setChannels(List<Channel> channels) {
        this.channels = channels;
    }

}

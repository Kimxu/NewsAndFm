
package kimxu.core.net.model.gson;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class List_ {

    @SerializedName("id")
    @Expose
    private int id;
    @SerializedName("orderNum")
    @Expose
    private int orderNum;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("subtitle")
    @Expose
    private String subtitle;
    @SerializedName("coverPath")
    @Expose
    private String coverPath;
    @SerializedName("contentType")
    @Expose
    private String contentType;
    @SerializedName("url")
    @Expose
    private String url;
    @SerializedName("sharePic")
    @Expose
    private String sharePic;
    @SerializedName("enableShare")
    @Expose
    private boolean enableShare;
    @SerializedName("contentUpdatedAt")
    @Expose
    private long contentUpdatedAt;
    @SerializedName("isHot")
    @Expose
    private boolean isHot;
    @SerializedName("isExternalUrl")
    @Expose
    private boolean isExternalUrl;

    /**
     * 
     * @return
     *     The id
     */
    public int getId() {
        return id;
    }

    /**
     * 
     * @param id
     *     The id
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * 
     * @return
     *     The orderNum
     */
    public int getOrderNum() {
        return orderNum;
    }

    /**
     * 
     * @param orderNum
     *     The orderNum
     */
    public void setOrderNum(int orderNum) {
        this.orderNum = orderNum;
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
     *     The subtitle
     */
    public String getSubtitle() {
        return subtitle;
    }

    /**
     * 
     * @param subtitle
     *     The subtitle
     */
    public void setSubtitle(String subtitle) {
        this.subtitle = subtitle;
    }

    /**
     * 
     * @return
     *     The coverPath
     */
    public String getCoverPath() {
        return coverPath;
    }

    /**
     * 
     * @param coverPath
     *     The coverPath
     */
    public void setCoverPath(String coverPath) {
        this.coverPath = coverPath;
    }

    /**
     * 
     * @return
     *     The contentType
     */
    public String getContentType() {
        return contentType;
    }

    /**
     * 
     * @param contentType
     *     The contentType
     */
    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    /**
     * 
     * @return
     *     The url
     */
    public String getUrl() {
        return url;
    }

    /**
     * 
     * @param url
     *     The url
     */
    public void setUrl(String url) {
        this.url = url;
    }

    /**
     * 
     * @return
     *     The sharePic
     */
    public String getSharePic() {
        return sharePic;
    }

    /**
     * 
     * @param sharePic
     *     The sharePic
     */
    public void setSharePic(String sharePic) {
        this.sharePic = sharePic;
    }

    /**
     * 
     * @return
     *     The enableShare
     */
    public boolean isEnableShare() {
        return enableShare;
    }

    /**
     * 
     * @param enableShare
     *     The enableShare
     */
    public void setEnableShare(boolean enableShare) {
        this.enableShare = enableShare;
    }

    /**
     * 
     * @return
     *     The contentUpdatedAt
     */
    public long getContentUpdatedAt() {
        return contentUpdatedAt;
    }

    /**
     * 
     * @param contentUpdatedAt
     *     The contentUpdatedAt
     */
    public void setContentUpdatedAt(long contentUpdatedAt) {
        this.contentUpdatedAt = contentUpdatedAt;
    }

    /**
     * 
     * @return
     *     The isHot
     */
    public boolean isIsHot() {
        return isHot;
    }

    /**
     * 
     * @param isHot
     *     The isHot
     */
    public void setIsHot(boolean isHot) {
        this.isHot = isHot;
    }

    /**
     * 
     * @return
     *     The isExternalUrl
     */
    public boolean isIsExternalUrl() {
        return isExternalUrl;
    }

    /**
     * 
     * @param isExternalUrl
     *     The isExternalUrl
     */
    public void setIsExternalUrl(boolean isExternalUrl) {
        this.isExternalUrl = isExternalUrl;
    }

}

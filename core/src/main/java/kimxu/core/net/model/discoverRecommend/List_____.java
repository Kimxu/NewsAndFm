
package kimxu.core.net.model.discoverRecommend;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class List_____ {

    @SerializedName("id")
    @Expose
    private int id;
    @SerializedName("shortTitle")
    @Expose
    private String shortTitle;
    @SerializedName("longTitle")
    @Expose
    private String longTitle;
    @SerializedName("pic")
    @Expose
    private String pic;
    @SerializedName("type")
    @Expose
    private int type;
    @SerializedName("specialId")
    @Expose
    private int specialId;
    @SerializedName("subType")
    @Expose
    private int subType;
    @SerializedName("isShare")
    @Expose
    private boolean isShare;
    @SerializedName("is_External_url")
    @Expose
    private boolean isExternalUrl;
    @SerializedName("uid")
    @Expose
    private int uid;
    @SerializedName("albumId")
    @Expose
    private int albumId;
    @SerializedName("trackId")
    @Expose
    private long trackId;
    @SerializedName("url")
    @Expose
    private String url;

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
     *     The shortTitle
     */
    public String getShortTitle() {
        return shortTitle;
    }

    /**
     * 
     * @param shortTitle
     *     The shortTitle
     */
    public void setShortTitle(String shortTitle) {
        this.shortTitle = shortTitle;
    }

    /**
     * 
     * @return
     *     The longTitle
     */
    public String getLongTitle() {
        return longTitle;
    }

    /**
     * 
     * @param longTitle
     *     The longTitle
     */
    public void setLongTitle(String longTitle) {
        this.longTitle = longTitle;
    }

    /**
     * 
     * @return
     *     The pic
     */
    public String getPic() {
        return pic;
    }

    /**
     * 
     * @param pic
     *     The pic
     */
    public void setPic(String pic) {
        this.pic = pic;
    }

    /**
     * 
     * @return
     *     The type
     */
    public int getType() {
        return type;
    }

    /**
     * 
     * @param type
     *     The type
     */
    public void setType(int type) {
        this.type = type;
    }

    /**
     * 
     * @return
     *     The specialId
     */
    public int getSpecialId() {
        return specialId;
    }

    /**
     * 
     * @param specialId
     *     The specialId
     */
    public void setSpecialId(int specialId) {
        this.specialId = specialId;
    }

    /**
     * 
     * @return
     *     The subType
     */
    public int getSubType() {
        return subType;
    }

    /**
     * 
     * @param subType
     *     The subType
     */
    public void setSubType(int subType) {
        this.subType = subType;
    }

    /**
     * 
     * @return
     *     The isShare
     */
    public boolean isIsShare() {
        return isShare;
    }

    /**
     * 
     * @param isShare
     *     The isShare
     */
    public void setIsShare(boolean isShare) {
        this.isShare = isShare;
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
     *     The is_External_url
     */
    public void setIsExternalUrl(boolean isExternalUrl) {
        this.isExternalUrl = isExternalUrl;
    }

    /**
     * 
     * @return
     *     The uid
     */
    public int getUid() {
        return uid;
    }

    /**
     * 
     * @param uid
     *     The uid
     */
    public void setUid(int uid) {
        this.uid = uid;
    }

    /**
     * 
     * @return
     *     The albumId
     */
    public int getAlbumId() {
        return albumId;
    }

    /**
     * 
     * @param albumId
     *     The albumId
     */
    public void setAlbumId(int albumId) {
        this.albumId = albumId;
    }

    /**
     * 
     * @return
     *     The trackId
     */
    public long getTrackId() {
        return trackId;
    }

    /**
     * 
     * @param trackId
     *     The trackId
     */
    public void setTrackId(long trackId) {
        this.trackId = trackId;
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

}

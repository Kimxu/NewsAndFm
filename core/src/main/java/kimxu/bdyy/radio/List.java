
package kimxu.bdyy.radio;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class List {

    @SerializedName("channelid")
    @Expose
    private String channelid;
    @SerializedName("itemid")
    @Expose
    private String itemid;
    @SerializedName("album_id")
    @Expose
    private String albumId;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("pic")
    @Expose
    private String pic;
    @SerializedName("type")
    @Expose
    private String type;
    @SerializedName("desc")
    @Expose
    private String desc;

    /**
     * 
     * @return
     *     The channelid
     */
    public String getChannelid() {
        return channelid;
    }

    /**
     * 
     * @param channelid
     *     The channelid
     */
    public void setChannelid(String channelid) {
        this.channelid = channelid;
    }

    /**
     * 
     * @return
     *     The itemid
     */
    public String getItemid() {
        return itemid;
    }

    /**
     * 
     * @param itemid
     *     The itemid
     */
    public void setItemid(String itemid) {
        this.itemid = itemid;
    }

    /**
     * 
     * @return
     *     The albumId
     */
    public String getAlbumId() {
        return albumId;
    }

    /**
     * 
     * @param albumId
     *     The album_id
     */
    public void setAlbumId(String albumId) {
        this.albumId = albumId;
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
    public String getType() {
        return type;
    }

    /**
     * 
     * @param type
     *     The type
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * 
     * @return
     *     The desc
     */
    public String getDesc() {
        return desc;
    }

    /**
     * 
     * @param desc
     *     The desc
     */
    public void setDesc(String desc) {
        this.desc = desc;
    }

}

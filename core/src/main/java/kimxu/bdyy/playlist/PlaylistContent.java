
package kimxu.bdyy.playlist;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PlaylistContent {

    @SerializedName("listid")
    @Expose
    private String listid;
    @SerializedName("listenum")
    @Expose
    private String listenum;
    @SerializedName("collectnum")
    @Expose
    private String collectnum;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("pic_300")
    @Expose
    private String pic300;
    @SerializedName("tag")
    @Expose
    private String tag;
    @SerializedName("desc")
    @Expose
    private String desc;
    @SerializedName("pic_w300")
    @Expose
    private String picW300;
    @SerializedName("width")
    @Expose
    private String width;
    @SerializedName("height")
    @Expose
    private String height;

    /**
     * 
     * @return
     *     The listid
     */
    public String getListid() {
        return listid;
    }

    /**
     * 
     * @param listid
     *     The listid
     */
    public void setListid(String listid) {
        this.listid = listid;
    }

    /**
     * 
     * @return
     *     The listenum
     */
    public String getListenum() {
        return listenum;
    }

    /**
     * 
     * @param listenum
     *     The listenum
     */
    public void setListenum(String listenum) {
        this.listenum = listenum;
    }

    /**
     * 
     * @return
     *     The collectnum
     */
    public String getCollectnum() {
        return collectnum;
    }

    /**
     * 
     * @param collectnum
     *     The collectnum
     */
    public void setCollectnum(String collectnum) {
        this.collectnum = collectnum;
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
     *     The pic300
     */
    public String getPic300() {
        return pic300;
    }

    /**
     * 
     * @param pic300
     *     The pic_300
     */
    public void setPic300(String pic300) {
        this.pic300 = pic300;
    }

    /**
     * 
     * @return
     *     The tag
     */
    public String getTag() {
        return tag;
    }

    /**
     * 
     * @param tag
     *     The tag
     */
    public void setTag(String tag) {
        this.tag = tag;
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

    /**
     * 
     * @return
     *     The picW300
     */
    public String getPicW300() {
        return picW300;
    }

    /**
     * 
     * @param picW300
     *     The pic_w300
     */
    public void setPicW300(String picW300) {
        this.picW300 = picW300;
    }

    /**
     * 
     * @return
     *     The width
     */
    public String getWidth() {
        return width;
    }

    /**
     * 
     * @param width
     *     The width
     */
    public void setWidth(String width) {
        this.width = width;
    }

    /**
     * 
     * @return
     *     The height
     */
    public String getHeight() {
        return height;
    }

    /**
     * 
     * @param height
     *     The height
     */
    public void setHeight(String height) {
        this.height = height;
    }

}

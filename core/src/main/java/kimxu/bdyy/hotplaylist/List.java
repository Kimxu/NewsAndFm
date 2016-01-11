
package kimxu.bdyy.hotplaylist;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class List {

    @SerializedName("listid")
    @Expose
    private String listid;
    @SerializedName("pic")
    @Expose
    private String pic;
    @SerializedName("listenum")
    @Expose
    private String listenum;
    @SerializedName("collectnum")
    @Expose
    private String collectnum;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("tag")
    @Expose
    private String tag;
    @SerializedName("type")
    @Expose
    private String type;

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

}

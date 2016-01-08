
package kimxu.bdyy.ranking;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class RankingContent {

    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("type")
    @Expose
    private Long type;
    @SerializedName("comment")
    @Expose
    private String comment;
    @SerializedName("web_url")
    @Expose
    private String webUrl;
    @SerializedName("pic_s192")
    @Expose
    private String picS192;
    @SerializedName("pic_s444")
    @Expose
    private String picS444;
    @SerializedName("pic_s260")
    @Expose
    private String picS260;
    @SerializedName("pic_s210")
    @Expose
    private String picS210;
    @SerializedName("content")
    @Expose
    private List<RankingContent_> content = new ArrayList<RankingContent_>();
    @SerializedName("pic_s640")
    @Expose
    private String picS640;

    /**
     * 
     * @return
     *     The name
     */
    public String getName() {
        return name;
    }

    /**
     * 
     * @param name
     *     The name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 
     * @return
     *     The type
     */
    public Long getType() {
        return type;
    }

    /**
     * 
     * @param type
     *     The type
     */
    public void setType(Long type) {
        this.type = type;
    }

    /**
     * 
     * @return
     *     The comment
     */
    public String getComment() {
        return comment;
    }

    /**
     * 
     * @param comment
     *     The comment
     */
    public void setComment(String comment) {
        this.comment = comment;
    }

    /**
     * 
     * @return
     *     The webUrl
     */
    public String getWebUrl() {
        return webUrl;
    }

    /**
     * 
     * @param webUrl
     *     The web_url
     */
    public void setWebUrl(String webUrl) {
        this.webUrl = webUrl;
    }

    /**
     * 
     * @return
     *     The picS192
     */
    public String getPicS192() {
        return picS192;
    }

    /**
     * 
     * @param picS192
     *     The pic_s192
     */
    public void setPicS192(String picS192) {
        this.picS192 = picS192;
    }

    /**
     * 
     * @return
     *     The picS444
     */
    public String getPicS444() {
        return picS444;
    }

    /**
     * 
     * @param picS444
     *     The pic_s444
     */
    public void setPicS444(String picS444) {
        this.picS444 = picS444;
    }

    /**
     * 
     * @return
     *     The picS260
     */
    public String getPicS260() {
        return picS260;
    }

    /**
     * 
     * @param picS260
     *     The pic_s260
     */
    public void setPicS260(String picS260) {
        this.picS260 = picS260;
    }

    /**
     * 
     * @return
     *     The picS210
     */
    public String getPicS210() {
        return picS210;
    }

    /**
     * 
     * @param picS210
     *     The pic_s210
     */
    public void setPicS210(String picS210) {
        this.picS210 = picS210;
    }

    /**
     * 
     * @return
     *     The content
     */
    public List<RankingContent_> getContent() {
        return content;
    }

    /**
     * 
     * @param content
     *     The content
     */
    public void setContent(List<RankingContent_> content) {
        this.content = content;
    }

    /**
     * 
     * @return
     *     The picS640
     */
    public String getPicS640() {
        return picS640;
    }

    /**
     * 
     * @param picS640
     *     The pic_s640
     */
    public void setPicS640(String picS640) {
        this.picS640 = picS640;
    }

}

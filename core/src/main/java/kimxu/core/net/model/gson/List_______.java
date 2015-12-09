
package kimxu.core.net.model.gson;

import javax.annotation.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class List_______ {

    @SerializedName("columnType")
    @Expose
    private int columnType;
    @SerializedName("specialId")
    @Expose
    private int specialId;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("subtitle")
    @Expose
    private String subtitle;
    @SerializedName("footnote")
    @Expose
    private String footnote;
    @SerializedName("coverPath")
    @Expose
    private String coverPath;
    @SerializedName("contentType")
    @Expose
    private String contentType;

    /**
     * 
     * @return
     *     The columnType
     */
    public int getColumnType() {
        return columnType;
    }

    /**
     * 
     * @param columnType
     *     The columnType
     */
    public void setColumnType(int columnType) {
        this.columnType = columnType;
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
     *     The footnote
     */
    public String getFootnote() {
        return footnote;
    }

    /**
     * 
     * @param footnote
     *     The footnote
     */
    public void setFootnote(String footnote) {
        this.footnote = footnote;
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

}

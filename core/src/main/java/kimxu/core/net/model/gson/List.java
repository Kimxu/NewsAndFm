
package kimxu.core.net.model.gson;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class List {

    @SerializedName("id")
    @Expose
    private int id;
    @SerializedName("entranceType")
    @Expose
    private String entranceType;
    @SerializedName("coverPath")
    @Expose
    private String coverPath;
    @SerializedName("title")
    @Expose
    private String title;

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
     *     The entranceType
     */
    public String getEntranceType() {
        return entranceType;
    }

    /**
     * 
     * @param entranceType
     *     The entranceType
     */
    public void setEntranceType(String entranceType) {
        this.entranceType = entranceType;
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

}

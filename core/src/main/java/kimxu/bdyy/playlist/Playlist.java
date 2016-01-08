
package kimxu.bdyy.playlist;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

import kimxu.mvp.model.IModel;

public class Playlist implements IModel {

    @SerializedName("error_code")
    @Expose
    private Long errorCode;
    @SerializedName("total")
    @Expose
    private Long total;
    @SerializedName("havemore")
    @Expose
    private Long havemore;
    @SerializedName("content")
    @Expose
    private List<PlaylistContent> content = new ArrayList<PlaylistContent>();

    /**
     * 
     * @return
     *     The errorCode
     */
    public Long getErrorCode() {
        return errorCode;
    }

    /**
     * 
     * @param errorCode
     *     The error_code
     */
    public void setErrorCode(Long errorCode) {
        this.errorCode = errorCode;
    }

    /**
     * 
     * @return
     *     The total
     */
    public Long getTotal() {
        return total;
    }

    /**
     * 
     * @param total
     *     The total
     */
    public void setTotal(Long total) {
        this.total = total;
    }

    /**
     * 
     * @return
     *     The havemore
     */
    public Long getHavemore() {
        return havemore;
    }

    /**
     * 
     * @param havemore
     *     The havemore
     */
    public void setHavemore(Long havemore) {
        this.havemore = havemore;
    }

    /**
     * 
     * @return
     *     The content
     */
    public List<PlaylistContent> getContent() {
        return content;
    }

    /**
     * 
     * @param content
     *     The content
     */
    public void setContent(List<PlaylistContent> content) {
        this.content = content;
    }

}

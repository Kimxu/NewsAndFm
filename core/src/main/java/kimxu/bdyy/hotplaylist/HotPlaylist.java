
package kimxu.bdyy.hotplaylist;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import kimxu.mvp.model.IModel;

public class HotPlaylist implements IModel {

    @SerializedName("error_code")
    @Expose
    private Long errorCode;
    @SerializedName("content")
    @Expose
    private Content content;

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
     *     The content
     */
    public Content getContent() {
        return content;
    }

    /**
     * 
     * @param content
     *     The content
     */
    public void setContent(Content content) {
        this.content = content;
    }

}

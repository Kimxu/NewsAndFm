
package kimxu.bdyy.recommend;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

import kimxu.mvp.model.IModel;

//歌曲推荐
public class Recommend implements IModel{

    @SerializedName("error_code")
    @Expose
    private Long errorCode;
    @SerializedName("content")
    @Expose
    private List<Content> content = new ArrayList<Content>();

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
    public List<Content> getContent() {
        return content;
    }

    /**
     * 
     * @param content
     *     The content
     */
    public void setContent(List<Content> content) {
        this.content = content;
    }

}

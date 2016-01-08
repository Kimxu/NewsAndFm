
package kimxu.bdyy.ranking;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

import kimxu.mvp.model.IModel;

/**
 * 歌单
 */
public class Ranking implements IModel{

    @SerializedName("content")
    @Expose
    private List<RankingContent> content = new ArrayList<RankingContent>();
    @SerializedName("error_code")
    @Expose
    private Long errorCode;

    /**
     * 
     * @return
     *     The content
     */
    public List<RankingContent> getContent() {
        return content;
    }

    /**
     * 
     * @param content
     *     The content
     */
    public void setContent(List<RankingContent> content) {
        this.content = content;
    }

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

}

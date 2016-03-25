package kimxu.bdyy.search;

/**
 * 歌曲搜索推荐
 * Created by kimxu on 16/3/25.
 */


import java.util.ArrayList;
import java.util.List;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SearchRecommended {

    @SerializedName("error_code")
    @Expose
    private Long errorCode;
    @SerializedName("result")
    @Expose
    private List<Result> result = new ArrayList<Result>();

    /**
     * @return The errorCode
     */
    public Long getErrorCode() {
        return errorCode;
    }

    /**
     * @param errorCode The error_code
     */
    public void setErrorCode(Long errorCode) {
        this.errorCode = errorCode;
    }

    /**
     * @return The result
     */
    public List<Result> getResult() {
        return result;
    }

    /**
     * @param result The result
     */
    public void setResult(List<Result> result) {
        this.result = result;
    }

}

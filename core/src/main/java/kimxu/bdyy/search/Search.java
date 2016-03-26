
package kimxu.bdyy.search;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Search {

    @SerializedName("error_code")
    @Expose
    private Long errorCode;
    @SerializedName("result")
    @Expose
    private Result result;

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
     *     The result
     */
    public Result getResult() {
        return result;
    }

    /**
     * 
     * @param result
     *     The result
     */
    public void setResult(Result result) {
        this.result = result;
    }

}

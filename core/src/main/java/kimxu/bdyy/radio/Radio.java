
package kimxu.bdyy.radio;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

import kimxu.mvp.model.IModel;

//推荐电台
public class Radio implements IModel {

    @SerializedName("error_code")
    @Expose
    private Long errorCode;
    @SerializedName("list")
    @Expose
    private java.util.List<List> list = new ArrayList<List>();

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
     *     The list
     */
    public java.util.List<List> getList() {
        return list;
    }

    /**
     * 
     * @param list
     *     The list
     */
    public void setList(java.util.List<List> list) {
        this.list = list;
    }

}


package kimxu.bdyy.banner;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;
//推荐banner
public class Banner {

    @SerializedName("pic")
    @Expose
    private List<Pic> pic = new ArrayList<Pic>();
    @SerializedName("error_code")
    @Expose
    private Long errorCode;

    /**
     * 
     * @return
     *     The pic
     */
    public List<Pic> getPic() {
        return pic;
    }

    /**
     * 
     * @param pic
     *     The pic
     */
    public void setPic(List<Pic> pic) {
        this.pic = pic;
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

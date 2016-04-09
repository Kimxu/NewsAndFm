
package kimxu.bdyy.song;

import javax.annotation.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Generated("org.jsonschema2pojo")
public class GetInfo {

    @SerializedName("songurl")
    @Expose
    private Songurl songurl;
    @SerializedName("error_code")
    @Expose
    private Long errorCode;
    @SerializedName("songinfo")
    @Expose
    private Songinfo songinfo;

    /**
     * 
     * @return
     *     The songurl
     */
    public Songurl getSongurl() {
        return songurl;
    }

    /**
     * 
     * @param songurl
     *     The songurl
     */
    public void setSongurl(Songurl songurl) {
        this.songurl = songurl;
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

    /**
     * 
     * @return
     *     The songinfo
     */
    public Songinfo getSonginfo() {
        return songinfo;
    }

    /**
     * 
     * @param songinfo
     *     The songinfo
     */
    public void setSonginfo(Songinfo songinfo) {
        this.songinfo = songinfo;
    }

}

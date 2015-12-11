
package kimxu.core.net.model.news;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class News {

    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("code")
    @Expose
    private long code;
    @SerializedName("result")
    @Expose
    private List<Result> result = new ArrayList<Result>();
    @SerializedName("fresh_count")
    @Expose
    private long freshCount;
    @SerializedName("channel_name")
    @Expose
    private String channelName;
    @SerializedName("channel_type")
    @Expose
    private String channelType;
    @SerializedName("channel_image")
    @Expose
    private String channelImage;

    /**
     * 
     * @return
     *     The status
     */
    public String getStatus() {
        return status;
    }

    /**
     * 
     * @param status
     *     The status
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * 
     * @return
     *     The code
     */
    public long getCode() {
        return code;
    }

    /**
     * 
     * @param code
     *     The code
     */
    public void setCode(long code) {
        this.code = code;
    }

    /**
     * 
     * @return
     *     The result
     */
    public List<Result> getResult() {
        return result;
    }

    /**
     * 
     * @param result
     *     The result
     */
    public void setResult(List<Result> result) {
        this.result = result;
    }

    /**
     * 
     * @return
     *     The freshCount
     */
    public long getFreshCount() {
        return freshCount;
    }

    /**
     * 
     * @param freshCount
     *     The fresh_count
     */
    public void setFreshCount(long freshCount) {
        this.freshCount = freshCount;
    }

    /**
     * 
     * @return
     *     The channelName
     */
    public String getChannelName() {
        return channelName;
    }

    /**
     * 
     * @param channelName
     *     The channel_name
     */
    public void setChannelName(String channelName) {
        this.channelName = channelName;
    }

    /**
     * 
     * @return
     *     The channelType
     */
    public String getChannelType() {
        return channelType;
    }

    /**
     * 
     * @param channelType
     *     The channel_type
     */
    public void setChannelType(String channelType) {
        this.channelType = channelType;
    }

    /**
     * 
     * @return
     *     The channelImage
     */
    public String getChannelImage() {
        return channelImage;
    }

    /**
     * 
     * @param channelImage
     *     The channel_image
     */
    public void setChannelImage(String channelImage) {
        this.channelImage = channelImage;
    }

}

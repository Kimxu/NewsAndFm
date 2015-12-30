
package kimxu.xmly.discoverRecommend;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class DiscoveryColumns {

    @SerializedName("ret")
    @Expose
    private int ret;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("list")
    @Expose
    private List<List_> list = new ArrayList<List_>();
    @SerializedName("locationInHotRecommend")
    @Expose
    private int locationInHotRecommend;

    /**
     * 
     * @return
     *     The ret
     */
    public int getRet() {
        return ret;
    }

    /**
     * 
     * @param ret
     *     The ret
     */
    public void setRet(int ret) {
        this.ret = ret;
    }

    /**
     * 
     * @return
     *     The title
     */
    public String getTitle() {
        return title;
    }

    /**
     * 
     * @param title
     *     The title
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * 
     * @return
     *     The list
     */
    public List<List_> getList() {
        return list;
    }

    /**
     * 
     * @param list
     *     The list
     */
    public void setList(List<List_> list) {
        this.list = list;
    }

    /**
     * 
     * @return
     *     The locationInHotRecommend
     */
    public int getLocationInHotRecommend() {
        return locationInHotRecommend;
    }

    /**
     * 
     * @param locationInHotRecommend
     *     The locationInHotRecommend
     */
    public void setLocationInHotRecommend(int locationInHotRecommend) {
        this.locationInHotRecommend = locationInHotRecommend;
    }

}

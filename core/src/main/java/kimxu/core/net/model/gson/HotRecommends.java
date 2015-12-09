
package kimxu.core.net.model.gson;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class HotRecommends {

    @SerializedName("ret")
    @Expose
    private int ret;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("list")
    @Expose
    private List<List___> list = new ArrayList<List___>();

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
    public List<List___> getList() {
        return list;
    }

    /**
     * 
     * @param list
     *     The list
     */
    public void setList(List<List___> list) {
        this.list = list;
    }

}

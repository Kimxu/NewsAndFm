
package kimxu.core.net.model.gson;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class FocusImages {

    @SerializedName("ret")
    @Expose
    private int ret;
    @SerializedName("list")
    @Expose
    private List<List_____> list = new ArrayList<List_____>();
    @SerializedName("title")
    @Expose
    private String title;

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
     *     The list
     */
    public List<List_____> getList() {
        return list;
    }

    /**
     * 
     * @param list
     *     The list
     */
    public void setList(List<List_____> list) {
        this.list = list;
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

}

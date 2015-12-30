
package kimxu.xmly.discoverRecommend;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class Entrances {

    @SerializedName("ret")
    @Expose
    private int ret;
    @SerializedName("list")
    @Expose
    private java.util.List<List> list = new ArrayList<List>();

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

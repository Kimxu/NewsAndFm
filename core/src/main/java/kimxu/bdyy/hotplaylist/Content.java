
package kimxu.bdyy.hotplaylist;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class Content {

    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("list")
    @Expose
    private java.util.List<ListHostPlay> list = new ArrayList<ListHostPlay>();

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
    public java.util.List<ListHostPlay> getList() {
        return list;
    }

    /**
     * 
     * @param list
     *     The list
     */
    public void setList(java.util.List<ListHostPlay> list) {
        this.list = list;
    }

}

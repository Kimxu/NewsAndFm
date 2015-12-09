
package kimxu.core.net.model.discoverRecommend;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;
public class EditorRecommendAlbums {

    @SerializedName("ret")
    @Expose
    private int ret;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("hasMore")
    @Expose
    private boolean hasMore;
    @SerializedName("list")
    @Expose
    private List<List__> list = new ArrayList<List__>();

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
     *     The hasMore
     */
    public boolean isHasMore() {
        return hasMore;
    }

    /**
     * 
     * @param hasMore
     *     The hasMore
     */
    public void setHasMore(boolean hasMore) {
        this.hasMore = hasMore;
    }

    /**
     * 
     * @return
     *     The list
     */
    public List<List__> getList() {
        return list;
    }

    /**
     * 
     * @param list
     *     The list
     */
    public void setList(List<List__> list) {
        this.list = list;
    }

}

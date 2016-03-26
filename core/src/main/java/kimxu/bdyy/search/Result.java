
package kimxu.bdyy.search;

import java.util.ArrayList;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Result {

    @SerializedName("total")
    @Expose
    private Long total;
    @SerializedName("have_more")
    @Expose
    private Long haveMore;
    @SerializedName("items")
    @Expose
    private List<Item> items = new ArrayList<Item>();

    /**
     * 
     * @return
     *     The total
     */
    public Long getTotal() {
        return total;
    }

    /**
     * 
     * @param total
     *     The total
     */
    public void setTotal(Long total) {
        this.total = total;
    }

    /**
     * 
     * @return
     *     The haveMore
     */
    public Long getHaveMore() {
        return haveMore;
    }

    /**
     * 
     * @param haveMore
     *     The have_more
     */
    public void setHaveMore(Long haveMore) {
        this.haveMore = haveMore;
    }

    /**
     * 
     * @return
     *     The items
     */
    public List<Item> getItems() {
        return items;
    }

    /**
     * 
     * @param items
     *     The items
     */
    public void setItems(List<Item> items) {
        this.items = items;
    }

}

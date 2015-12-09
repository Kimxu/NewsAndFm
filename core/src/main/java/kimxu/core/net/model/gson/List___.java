
package kimxu.core.net.model.gson;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class List___ {

    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("contentType")
    @Expose
    private String contentType;
    @SerializedName("isFinished")
    @Expose
    private boolean isFinished;
    @SerializedName("categoryId")
    @Expose
    private int categoryId;
    @SerializedName("count")
    @Expose
    private int count;
    @SerializedName("hasMore")
    @Expose
    private boolean hasMore;
    @SerializedName("list")
    @Expose
    private List<List____> list = new ArrayList<List____>();

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
     *     The contentType
     */
    public String getContentType() {
        return contentType;
    }

    /**
     * 
     * @param contentType
     *     The contentType
     */
    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    /**
     * 
     * @return
     *     The isFinished
     */
    public boolean isIsFinished() {
        return isFinished;
    }

    /**
     * 
     * @param isFinished
     *     The isFinished
     */
    public void setIsFinished(boolean isFinished) {
        this.isFinished = isFinished;
    }

    /**
     * 
     * @return
     *     The categoryId
     */
    public int getCategoryId() {
        return categoryId;
    }

    /**
     * 
     * @param categoryId
     *     The categoryId
     */
    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    /**
     * 
     * @return
     *     The count
     */
    public int getCount() {
        return count;
    }

    /**
     * 
     * @param count
     *     The count
     */
    public void setCount(int count) {
        this.count = count;
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
    public List<List____> getList() {
        return list;
    }

    /**
     * 
     * @param list
     *     The list
     */
    public void setList(List<List____> list) {
        this.list = list;
    }

}

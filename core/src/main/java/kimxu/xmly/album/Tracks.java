
package kimxu.xmly.album;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class Tracks {

    @SerializedName("list")
    @Expose
    private java.util.List<List> list = new ArrayList<List>();
    @SerializedName("pageId")
    @Expose
    private Long pageId;
    @SerializedName("pageSize")
    @Expose
    private Long pageSize;
    @SerializedName("maxPageId")
    @Expose
    private Long maxPageId;
    @SerializedName("totalCount")
    @Expose
    private Long totalCount;

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

    /**
     * 
     * @return
     *     The pageId
     */
    public Long getPageId() {
        return pageId;
    }

    /**
     * 
     * @param pageId
     *     The pageId
     */
    public void setPageId(Long pageId) {
        this.pageId = pageId;
    }

    /**
     * 
     * @return
     *     The pageSize
     */
    public Long getPageSize() {
        return pageSize;
    }

    /**
     * 
     * @param pageSize
     *     The pageSize
     */
    public void setPageSize(Long pageSize) {
        this.pageSize = pageSize;
    }

    /**
     * 
     * @return
     *     The maxPageId
     */
    public Long getMaxPageId() {
        return maxPageId;
    }

    /**
     * 
     * @param maxPageId
     *     The maxPageId
     */
    public void setMaxPageId(Long maxPageId) {
        this.maxPageId = maxPageId;
    }

    /**
     * 
     * @return
     *     The totalCount
     */
    public Long getTotalCount() {
        return totalCount;
    }

    /**
     * 
     * @param totalCount
     *     The totalCount
     */
    public void setTotalCount(Long totalCount) {
        this.totalCount = totalCount;
    }

}

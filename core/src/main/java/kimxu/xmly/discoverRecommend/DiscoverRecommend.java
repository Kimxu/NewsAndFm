
package kimxu.xmly.discoverRecommend;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * 获得推荐内容
 */
public class DiscoverRecommend {

    @SerializedName("entrances")
    @Expose
    private Entrances entrances;
    @SerializedName("ret")
    @Expose
    private int ret;
    @SerializedName("discoveryColumns")
    @Expose
    private DiscoveryColumns discoveryColumns;
    @SerializedName("editorRecommendAlbums")
    @Expose
    private EditorRecommendAlbums editorRecommendAlbums;
    @SerializedName("hotRecommends")
    @Expose
    private HotRecommends hotRecommends;
    @SerializedName("focusImages")
    @Expose
    private FocusImages focusImages;
    @SerializedName("bulletArea")
    @Expose
    private BulletArea bulletArea;
    @SerializedName("msg")
    @Expose
    private String msg;
    @SerializedName("specialColumn")
    @Expose
    private SpecialColumn specialColumn;

    /**
     * 
     * @return
     *     The entrances
     */
    public Entrances getEntrances() {
        return entrances;
    }

    /**
     * 
     * @param entrances
     *     The entrances
     */
    public void setEntrances(Entrances entrances) {
        this.entrances = entrances;
    }

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
     *     The discoveryColumns
     */
    public DiscoveryColumns getDiscoveryColumns() {
        return discoveryColumns;
    }

    /**
     * 
     * @param discoveryColumns
     *     The discoveryColumns
     */
    public void setDiscoveryColumns(DiscoveryColumns discoveryColumns) {
        this.discoveryColumns = discoveryColumns;
    }

    /**
     * 
     * @return
     *     The editorRecommendAlbums
     */
    public EditorRecommendAlbums getEditorRecommendAlbums() {
        return editorRecommendAlbums;
    }

    /**
     * 
     * @param editorRecommendAlbums
     *     The editorRecommendAlbums
     */
    public void setEditorRecommendAlbums(EditorRecommendAlbums editorRecommendAlbums) {
        this.editorRecommendAlbums = editorRecommendAlbums;
    }

    /**
     * 
     * @return
     *     The hotRecommends
     */
    public HotRecommends getHotRecommends() {
        return hotRecommends;
    }

    /**
     * 
     * @param hotRecommends
     *     The hotRecommends
     */
    public void setHotRecommends(HotRecommends hotRecommends) {
        this.hotRecommends = hotRecommends;
    }

    /**
     * 
     * @return
     *     The focusImages
     */
    public FocusImages getFocusImages() {
        return focusImages;
    }

    /**
     * 
     * @param focusImages
     *     The focusImages
     */
    public void setFocusImages(FocusImages focusImages) {
        this.focusImages = focusImages;
    }

    /**
     * 
     * @return
     *     The bulletArea
     */
    public BulletArea getBulletArea() {
        return bulletArea;
    }

    /**
     * 
     * @param bulletArea
     *     The bulletArea
     */
    public void setBulletArea(BulletArea bulletArea) {
        this.bulletArea = bulletArea;
    }

    /**
     * 
     * @return
     *     The msg
     */
    public String getMsg() {
        return msg;
    }

    /**
     * 
     * @param msg
     *     The msg
     */
    public void setMsg(String msg) {
        this.msg = msg;
    }

    /**
     * 
     * @return
     *     The specialColumn
     */
    public SpecialColumn getSpecialColumn() {
        return specialColumn;
    }

    /**
     * 
     * @param specialColumn
     *     The specialColumn
     */
    public void setSpecialColumn(SpecialColumn specialColumn) {
        this.specialColumn = specialColumn;
    }

}

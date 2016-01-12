
package kimxu.bdyy.style;

import javax.annotation.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Operation {

    @SerializedName("scene_id")
    @Expose
    private String sceneId;
    @SerializedName("scene_name")
    @Expose
    private String sceneName;
    @SerializedName("icon_ios")
    @Expose
    private String iconIos;
    @SerializedName("icon_android")
    @Expose
    private String iconAndroid;
    @SerializedName("bgpic_ios")
    @Expose
    private String bgpicIos;
    @SerializedName("bgpic_android")
    @Expose
    private String bgpicAndroid;
    @SerializedName("scene_model")
    @Expose
    private String sceneModel;
    @SerializedName("scene_desc")
    @Expose
    private String sceneDesc;

    /**
     * 
     * @return
     *     The sceneId
     */
    public String getSceneId() {
        return sceneId;
    }

    /**
     * 
     * @param sceneId
     *     The scene_id
     */
    public void setSceneId(String sceneId) {
        this.sceneId = sceneId;
    }

    /**
     * 
     * @return
     *     The sceneName
     */
    public String getSceneName() {
        return sceneName;
    }

    /**
     * 
     * @param sceneName
     *     The scene_name
     */
    public void setSceneName(String sceneName) {
        this.sceneName = sceneName;
    }

    /**
     * 
     * @return
     *     The iconIos
     */
    public String getIconIos() {
        return iconIos;
    }

    /**
     * 
     * @param iconIos
     *     The icon_ios
     */
    public void setIconIos(String iconIos) {
        this.iconIos = iconIos;
    }

    /**
     * 
     * @return
     *     The iconAndroid
     */
    public String getIconAndroid() {
        return iconAndroid;
    }

    /**
     * 
     * @param iconAndroid
     *     The icon_android
     */
    public void setIconAndroid(String iconAndroid) {
        this.iconAndroid = iconAndroid;
    }

    /**
     * 
     * @return
     *     The bgpicIos
     */
    public String getBgpicIos() {
        return bgpicIos;
    }

    /**
     * 
     * @param bgpicIos
     *     The bgpic_ios
     */
    public void setBgpicIos(String bgpicIos) {
        this.bgpicIos = bgpicIos;
    }

    /**
     * 
     * @return
     *     The bgpicAndroid
     */
    public String getBgpicAndroid() {
        return bgpicAndroid;
    }

    /**
     * 
     * @param bgpicAndroid
     *     The bgpic_android
     */
    public void setBgpicAndroid(String bgpicAndroid) {
        this.bgpicAndroid = bgpicAndroid;
    }

    /**
     * 
     * @return
     *     The sceneModel
     */
    public String getSceneModel() {
        return sceneModel;
    }

    /**
     * 
     * @param sceneModel
     *     The scene_model
     */
    public void setSceneModel(String sceneModel) {
        this.sceneModel = sceneModel;
    }

    /**
     * 
     * @return
     *     The sceneDesc
     */
    public String getSceneDesc() {
        return sceneDesc;
    }

    /**
     * 
     * @param sceneDesc
     *     The scene_desc
     */
    public void setSceneDesc(String sceneDesc) {
        this.sceneDesc = sceneDesc;
    }

}

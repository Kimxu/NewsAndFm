
package kimxu.bdyy.style;

import javax.annotation.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Config {

    @SerializedName("scene_version")
    @Expose
    private Long sceneVersion;
    @SerializedName("bgpic")
    @Expose
    private String bgpic;
    @SerializedName("bgpic_special")
    @Expose
    private String bgpicSpecial;
    @SerializedName("scene_color")
    @Expose
    private String sceneColor;
    @SerializedName("play_color")
    @Expose
    private String playColor;
    @SerializedName("button_color")
    @Expose
    private String buttonColor;
    @SerializedName("color_other")
    @Expose
    private String colorOther;
    @SerializedName("desc")
    @Expose
    private String desc;
    @SerializedName("start_time")
    @Expose
    private Long startTime;
    @SerializedName("end_time")
    @Expose
    private Long endTime;
    @SerializedName("native_update")
    @Expose
    private Long nativeUpdate;

    /**
     * 
     * @return
     *     The sceneVersion
     */
    public Long getSceneVersion() {
        return sceneVersion;
    }

    /**
     * 
     * @param sceneVersion
     *     The scene_version
     */
    public void setSceneVersion(Long sceneVersion) {
        this.sceneVersion = sceneVersion;
    }

    /**
     * 
     * @return
     *     The bgpic
     */
    public String getBgpic() {
        return bgpic;
    }

    /**
     * 
     * @param bgpic
     *     The bgpic
     */
    public void setBgpic(String bgpic) {
        this.bgpic = bgpic;
    }

    /**
     * 
     * @return
     *     The bgpicSpecial
     */
    public String getBgpicSpecial() {
        return bgpicSpecial;
    }

    /**
     * 
     * @param bgpicSpecial
     *     The bgpic_special
     */
    public void setBgpicSpecial(String bgpicSpecial) {
        this.bgpicSpecial = bgpicSpecial;
    }

    /**
     * 
     * @return
     *     The sceneColor
     */
    public String getSceneColor() {
        return sceneColor;
    }

    /**
     * 
     * @param sceneColor
     *     The scene_color
     */
    public void setSceneColor(String sceneColor) {
        this.sceneColor = sceneColor;
    }

    /**
     * 
     * @return
     *     The playColor
     */
    public String getPlayColor() {
        return playColor;
    }

    /**
     * 
     * @param playColor
     *     The play_color
     */
    public void setPlayColor(String playColor) {
        this.playColor = playColor;
    }

    /**
     * 
     * @return
     *     The buttonColor
     */
    public String getButtonColor() {
        return buttonColor;
    }

    /**
     * 
     * @param buttonColor
     *     The button_color
     */
    public void setButtonColor(String buttonColor) {
        this.buttonColor = buttonColor;
    }

    /**
     * 
     * @return
     *     The colorOther
     */
    public String getColorOther() {
        return colorOther;
    }

    /**
     * 
     * @param colorOther
     *     The color_other
     */
    public void setColorOther(String colorOther) {
        this.colorOther = colorOther;
    }

    /**
     * 
     * @return
     *     The desc
     */
    public String getDesc() {
        return desc;
    }

    /**
     * 
     * @param desc
     *     The desc
     */
    public void setDesc(String desc) {
        this.desc = desc;
    }

    /**
     * 
     * @return
     *     The startTime
     */
    public Long getStartTime() {
        return startTime;
    }

    /**
     * 
     * @param startTime
     *     The start_time
     */
    public void setStartTime(Long startTime) {
        this.startTime = startTime;
    }

    /**
     * 
     * @return
     *     The endTime
     */
    public Long getEndTime() {
        return endTime;
    }

    /**
     * 
     * @param endTime
     *     The end_time
     */
    public void setEndTime(Long endTime) {
        this.endTime = endTime;
    }

    /**
     * 
     * @return
     *     The nativeUpdate
     */
    public Long getNativeUpdate() {
        return nativeUpdate;
    }

    /**
     * 
     * @param nativeUpdate
     *     The native_update
     */
    public void setNativeUpdate(Long nativeUpdate) {
        this.nativeUpdate = nativeUpdate;
    }

}

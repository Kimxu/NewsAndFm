
package kimxu.bdyy.pic;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Url {

    @SerializedName("show_link")
    @Expose
    private String showLink;
    @SerializedName("hash")
    @Expose
    private String hash;
    @SerializedName("is_udition_url")
    @Expose
    private Long isUditionUrl;
    @SerializedName("free")
    @Expose
    private Long free;
    @SerializedName("replay_gain")
    @Expose
    private String replayGain;
    @SerializedName("song_file_id")
    @Expose
    private Long songFileId;
    @SerializedName("file_size")
    @Expose
    private Long fileSize;
    @SerializedName("file_extension")
    @Expose
    private String fileExtension;
    @SerializedName("file_duration")
    @Expose
    private Long fileDuration;
    @SerializedName("can_see")
    @Expose
    private Long canSee;
    @SerializedName("can_load")
    @Expose
    private Boolean canLoad;
    @SerializedName("preload")
    @Expose
    private Float preload;
    @SerializedName("file_bitrate")
    @Expose
    private Long fileBitrate;
    @SerializedName("file_link")
    @Expose
    private String fileLink;
    @SerializedName("original")
    @Expose
    private Long original;
    @SerializedName("down_type")
    @Expose
    private Long downType;

    /**
     * 
     * @return
     *     The showLink
     */
    public String getShowLink() {
        return showLink;
    }

    /**
     * 
     * @param showLink
     *     The show_link
     */
    public void setShowLink(String showLink) {
        this.showLink = showLink;
    }

    /**
     * 
     * @return
     *     The hash
     */
    public String getHash() {
        return hash;
    }

    /**
     * 
     * @param hash
     *     The hash
     */
    public void setHash(String hash) {
        this.hash = hash;
    }

    /**
     * 
     * @return
     *     The isUditionUrl
     */
    public Long getIsUditionUrl() {
        return isUditionUrl;
    }

    /**
     * 
     * @param isUditionUrl
     *     The is_udition_url
     */
    public void setIsUditionUrl(Long isUditionUrl) {
        this.isUditionUrl = isUditionUrl;
    }

    /**
     * 
     * @return
     *     The free
     */
    public Long getFree() {
        return free;
    }

    /**
     * 
     * @param free
     *     The free
     */
    public void setFree(Long free) {
        this.free = free;
    }

    /**
     * 
     * @return
     *     The replayGain
     */
    public String getReplayGain() {
        return replayGain;
    }

    /**
     * 
     * @param replayGain
     *     The replay_gain
     */
    public void setReplayGain(String replayGain) {
        this.replayGain = replayGain;
    }

    /**
     * 
     * @return
     *     The songFileId
     */
    public Long getSongFileId() {
        return songFileId;
    }

    /**
     * 
     * @param songFileId
     *     The song_file_id
     */
    public void setSongFileId(Long songFileId) {
        this.songFileId = songFileId;
    }

    /**
     * 
     * @return
     *     The fileSize
     */
    public Long getFileSize() {
        return fileSize;
    }

    /**
     * 
     * @param fileSize
     *     The file_size
     */
    public void setFileSize(Long fileSize) {
        this.fileSize = fileSize;
    }

    /**
     * 
     * @return
     *     The fileExtension
     */
    public String getFileExtension() {
        return fileExtension;
    }

    /**
     * 
     * @param fileExtension
     *     The file_extension
     */
    public void setFileExtension(String fileExtension) {
        this.fileExtension = fileExtension;
    }

    /**
     * 
     * @return
     *     The fileDuration
     */
    public Long getFileDuration() {
        return fileDuration;
    }

    /**
     * 
     * @param fileDuration
     *     The file_duration
     */
    public void setFileDuration(Long fileDuration) {
        this.fileDuration = fileDuration;
    }

    /**
     * 
     * @return
     *     The canSee
     */
    public Long getCanSee() {
        return canSee;
    }

    /**
     * 
     * @param canSee
     *     The can_see
     */
    public void setCanSee(Long canSee) {
        this.canSee = canSee;
    }

    /**
     * 
     * @return
     *     The canLoad
     */
    public Boolean getCanLoad() {
        return canLoad;
    }

    /**
     * 
     * @param canLoad
     *     The can_load
     */
    public void setCanLoad(Boolean canLoad) {
        this.canLoad = canLoad;
    }

    /**
     * 
     * @return
     *     The preload
     */
    public Float getPreload() {
        return preload;
    }

    /**
     * 
     * @param preload
     *     The preload
     */
    public void setPreload(Float preload) {
        this.preload = preload;
    }

    /**
     * 
     * @return
     *     The fileBitrate
     */
    public Long getFileBitrate() {
        return fileBitrate;
    }

    /**
     * 
     * @param fileBitrate
     *     The file_bitrate
     */
    public void setFileBitrate(Long fileBitrate) {
        this.fileBitrate = fileBitrate;
    }

    /**
     * 
     * @return
     *     The fileLink
     */
    public String getFileLink() {
        return fileLink;
    }

    /**
     * 
     * @param fileLink
     *     The file_link
     */
    public void setFileLink(String fileLink) {
        this.fileLink = fileLink;
    }

    /**
     * 
     * @return
     *     The original
     */
    public Long getOriginal() {
        return original;
    }

    /**
     * 
     * @param original
     *     The original
     */
    public void setOriginal(Long original) {
        this.original = original;
    }

    /**
     * 
     * @return
     *     The downType
     */
    public Long getDownType() {
        return downType;
    }

    /**
     * 
     * @param downType
     *     The down_type
     */
    public void setDownType(Long downType) {
        this.downType = downType;
    }

}

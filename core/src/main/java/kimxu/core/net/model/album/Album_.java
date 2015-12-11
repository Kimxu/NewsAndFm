
package kimxu.core.net.model.album;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Album_ {

    @SerializedName("albumId")
    @Expose
    private Long albumId;
    @SerializedName("categoryId")
    @Expose
    private Long categoryId;
    @SerializedName("categoryName")
    @Expose
    private String categoryName;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("coverOrigin")
    @Expose
    private String coverOrigin;
    @SerializedName("coverSmall")
    @Expose
    private String coverSmall;
    @SerializedName("coverMiddle")
    @Expose
    private String coverMiddle;
    @SerializedName("coverLarge")
    @Expose
    private String coverLarge;
    @SerializedName("coverWebLarge")
    @Expose
    private String coverWebLarge;
    @SerializedName("createdAt")
    @Expose
    private Long createdAt;
    @SerializedName("updatedAt")
    @Expose
    private Long updatedAt;
    @SerializedName("uid")
    @Expose
    private Long uid;
    @SerializedName("nickname")
    @Expose
    private String nickname;
    @SerializedName("isVerified")
    @Expose
    private Boolean isVerified;
    @SerializedName("avatarPath")
    @Expose
    private String avatarPath;
    @SerializedName("intro")
    @Expose
    private String intro;
    @SerializedName("introRich")
    @Expose
    private String introRich;
    @SerializedName("tags")
    @Expose
    private String tags;
    @SerializedName("tracks")
    @Expose
    private Long tracks;
    @SerializedName("shares")
    @Expose
    private Long shares;
    @SerializedName("hasNew")
    @Expose
    private Boolean hasNew;
    @SerializedName("isFavorite")
    @Expose
    private Boolean isFavorite;
    @SerializedName("playTimes")
    @Expose
    private Long playTimes;
    @SerializedName("status")
    @Expose
    private Long status;
    @SerializedName("serializeStatus")
    @Expose
    private Long serializeStatus;
    @SerializedName("serialState")
    @Expose
    private Long serialState;

    /**
     * 
     * @return
     *     The albumId
     */
    public Long getAlbumId() {
        return albumId;
    }

    /**
     * 
     * @param albumId
     *     The albumId
     */
    public void setAlbumId(Long albumId) {
        this.albumId = albumId;
    }

    /**
     * 
     * @return
     *     The categoryId
     */
    public Long getCategoryId() {
        return categoryId;
    }

    /**
     * 
     * @param categoryId
     *     The categoryId
     */
    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    /**
     * 
     * @return
     *     The categoryName
     */
    public String getCategoryName() {
        return categoryName;
    }

    /**
     * 
     * @param categoryName
     *     The categoryName
     */
    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
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
     *     The coverOrigin
     */
    public String getCoverOrigin() {
        return coverOrigin;
    }

    /**
     * 
     * @param coverOrigin
     *     The coverOrigin
     */
    public void setCoverOrigin(String coverOrigin) {
        this.coverOrigin = coverOrigin;
    }

    /**
     * 
     * @return
     *     The coverSmall
     */
    public String getCoverSmall() {
        return coverSmall;
    }

    /**
     * 
     * @param coverSmall
     *     The coverSmall
     */
    public void setCoverSmall(String coverSmall) {
        this.coverSmall = coverSmall;
    }

    /**
     * 
     * @return
     *     The coverMiddle
     */
    public String getCoverMiddle() {
        return coverMiddle;
    }

    /**
     * 
     * @param coverMiddle
     *     The coverMiddle
     */
    public void setCoverMiddle(String coverMiddle) {
        this.coverMiddle = coverMiddle;
    }

    /**
     * 
     * @return
     *     The coverLarge
     */
    public String getCoverLarge() {
        return coverLarge;
    }

    /**
     * 
     * @param coverLarge
     *     The coverLarge
     */
    public void setCoverLarge(String coverLarge) {
        this.coverLarge = coverLarge;
    }

    /**
     * 
     * @return
     *     The coverWebLarge
     */
    public String getCoverWebLarge() {
        return coverWebLarge;
    }

    /**
     * 
     * @param coverWebLarge
     *     The coverWebLarge
     */
    public void setCoverWebLarge(String coverWebLarge) {
        this.coverWebLarge = coverWebLarge;
    }

    /**
     * 
     * @return
     *     The createdAt
     */
    public Long getCreatedAt() {
        return createdAt;
    }

    /**
     * 
     * @param createdAt
     *     The createdAt
     */
    public void setCreatedAt(Long createdAt) {
        this.createdAt = createdAt;
    }

    /**
     * 
     * @return
     *     The updatedAt
     */
    public Long getUpdatedAt() {
        return updatedAt;
    }

    /**
     * 
     * @param updatedAt
     *     The updatedAt
     */
    public void setUpdatedAt(Long updatedAt) {
        this.updatedAt = updatedAt;
    }

    /**
     * 
     * @return
     *     The uid
     */
    public Long getUid() {
        return uid;
    }

    /**
     * 
     * @param uid
     *     The uid
     */
    public void setUid(Long uid) {
        this.uid = uid;
    }

    /**
     * 
     * @return
     *     The nickname
     */
    public String getNickname() {
        return nickname;
    }

    /**
     * 
     * @param nickname
     *     The nickname
     */
    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    /**
     * 
     * @return
     *     The isVerified
     */
    public Boolean getIsVerified() {
        return isVerified;
    }

    /**
     * 
     * @param isVerified
     *     The isVerified
     */
    public void setIsVerified(Boolean isVerified) {
        this.isVerified = isVerified;
    }

    /**
     * 
     * @return
     *     The avatarPath
     */
    public String getAvatarPath() {
        return avatarPath;
    }

    /**
     * 
     * @param avatarPath
     *     The avatarPath
     */
    public void setAvatarPath(String avatarPath) {
        this.avatarPath = avatarPath;
    }

    /**
     * 
     * @return
     *     The intro
     */
    public String getIntro() {
        return intro;
    }

    /**
     * 
     * @param intro
     *     The intro
     */
    public void setIntro(String intro) {
        this.intro = intro;
    }

    /**
     * 
     * @return
     *     The introRich
     */
    public String getIntroRich() {
        return introRich;
    }

    /**
     * 
     * @param introRich
     *     The introRich
     */
    public void setIntroRich(String introRich) {
        this.introRich = introRich;
    }

    /**
     * 
     * @return
     *     The tags
     */
    public String getTags() {
        return tags;
    }

    /**
     * 
     * @param tags
     *     The tags
     */
    public void setTags(String tags) {
        this.tags = tags;
    }

    /**
     * 
     * @return
     *     The tracks
     */
    public Long getTracks() {
        return tracks;
    }

    /**
     * 
     * @param tracks
     *     The tracks
     */
    public void setTracks(Long tracks) {
        this.tracks = tracks;
    }

    /**
     * 
     * @return
     *     The shares
     */
    public Long getShares() {
        return shares;
    }

    /**
     * 
     * @param shares
     *     The shares
     */
    public void setShares(Long shares) {
        this.shares = shares;
    }

    /**
     * 
     * @return
     *     The hasNew
     */
    public Boolean getHasNew() {
        return hasNew;
    }

    /**
     * 
     * @param hasNew
     *     The hasNew
     */
    public void setHasNew(Boolean hasNew) {
        this.hasNew = hasNew;
    }

    /**
     * 
     * @return
     *     The isFavorite
     */
    public Boolean getIsFavorite() {
        return isFavorite;
    }

    /**
     * 
     * @param isFavorite
     *     The isFavorite
     */
    public void setIsFavorite(Boolean isFavorite) {
        this.isFavorite = isFavorite;
    }

    /**
     * 
     * @return
     *     The playTimes
     */
    public Long getPlayTimes() {
        return playTimes;
    }

    /**
     * 
     * @param playTimes
     *     The playTimes
     */
    public void setPlayTimes(Long playTimes) {
        this.playTimes = playTimes;
    }

    /**
     * 
     * @return
     *     The status
     */
    public Long getStatus() {
        return status;
    }

    /**
     * 
     * @param status
     *     The status
     */
    public void setStatus(Long status) {
        this.status = status;
    }

    /**
     * 
     * @return
     *     The serializeStatus
     */
    public Long getSerializeStatus() {
        return serializeStatus;
    }

    /**
     * 
     * @param serializeStatus
     *     The serializeStatus
     */
    public void setSerializeStatus(Long serializeStatus) {
        this.serializeStatus = serializeStatus;
    }

    /**
     * 
     * @return
     *     The serialState
     */
    public Long getSerialState() {
        return serialState;
    }

    /**
     * 
     * @param serialState
     *     The serialState
     */
    public void setSerialState(Long serialState) {
        this.serialState = serialState;
    }

}

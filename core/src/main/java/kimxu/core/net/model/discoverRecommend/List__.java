
package kimxu.core.net.model.discoverRecommend;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class List__ {

    @SerializedName("albumId")
    @Expose
    private int albumId;
    @SerializedName("coverLarge")
    @Expose
    private String coverLarge;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("tags")
    @Expose
    private String tags;
    @SerializedName("tracks")
    @Expose
    private int tracks;
    @SerializedName("playsCounts")
    @Expose
    private int playsCounts;
    @SerializedName("isFinished")
    @Expose
    private int isFinished;
    @SerializedName("serialState")
    @Expose
    private int serialState;
    @SerializedName("trackId")
    @Expose
    private long trackId;
    @SerializedName("trackTitle")
    @Expose
    private String trackTitle;

    /**
     * 
     * @return
     *     The albumId
     */
    public int getAlbumId() {
        return albumId;
    }

    /**
     * 
     * @param albumId
     *     The albumId
     */
    public void setAlbumId(int albumId) {
        this.albumId = albumId;
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
    public int getTracks() {
        return tracks;
    }

    /**
     * 
     * @param tracks
     *     The tracks
     */
    public void setTracks(int tracks) {
        this.tracks = tracks;
    }

    /**
     * 
     * @return
     *     The playsCounts
     */
    public int getPlaysCounts() {
        return playsCounts;
    }

    /**
     * 
     * @param playsCounts
     *     The playsCounts
     */
    public void setPlaysCounts(int playsCounts) {
        this.playsCounts = playsCounts;
    }

    /**
     * 
     * @return
     *     The isFinished
     */
    public int getIsFinished() {
        return isFinished;
    }

    /**
     * 
     * @param isFinished
     *     The isFinished
     */
    public void setIsFinished(int isFinished) {
        this.isFinished = isFinished;
    }

    /**
     * 
     * @return
     *     The serialState
     */
    public int getSerialState() {
        return serialState;
    }

    /**
     * 
     * @param serialState
     *     The serialState
     */
    public void setSerialState(int serialState) {
        this.serialState = serialState;
    }

    /**
     * 
     * @return
     *     The trackId
     */
    public long getTrackId() {
        return trackId;
    }

    /**
     * 
     * @param trackId
     *     The trackId
     */
    public void setTrackId(long trackId) {
        this.trackId = trackId;
    }

    /**
     * 
     * @return
     *     The trackTitle
     */
    public String getTrackTitle() {
        return trackTitle;
    }

    /**
     * 
     * @param trackTitle
     *     The trackTitle
     */
    public void setTrackTitle(String trackTitle) {
        this.trackTitle = trackTitle;
    }

}

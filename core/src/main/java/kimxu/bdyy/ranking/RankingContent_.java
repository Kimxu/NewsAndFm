
package kimxu.bdyy.ranking;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RankingContent_ {

    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("author")
    @Expose
    private String author;
    @SerializedName("song_id")
    @Expose
    private String songId;
    @SerializedName("album_id")
    @Expose
    private String albumId;
    @SerializedName("album_title")
    @Expose
    private String albumTitle;
    @SerializedName("rank_change")
    @Expose
    private String rankChange;
    @SerializedName("all_rate")
    @Expose
    private String allRate;

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
     *     The author
     */
    public String getAuthor() {
        return author;
    }

    /**
     * 
     * @param author
     *     The author
     */
    public void setAuthor(String author) {
        this.author = author;
    }

    /**
     * 
     * @return
     *     The songId
     */
    public String getSongId() {
        return songId;
    }

    /**
     * 
     * @param songId
     *     The song_id
     */
    public void setSongId(String songId) {
        this.songId = songId;
    }

    /**
     * 
     * @return
     *     The albumId
     */
    public String getAlbumId() {
        return albumId;
    }

    /**
     * 
     * @param albumId
     *     The album_id
     */
    public void setAlbumId(String albumId) {
        this.albumId = albumId;
    }

    /**
     * 
     * @return
     *     The albumTitle
     */
    public String getAlbumTitle() {
        return albumTitle;
    }

    /**
     * 
     * @param albumTitle
     *     The album_title
     */
    public void setAlbumTitle(String albumTitle) {
        this.albumTitle = albumTitle;
    }

    /**
     * 
     * @return
     *     The rankChange
     */
    public String getRankChange() {
        return rankChange;
    }

    /**
     * 
     * @param rankChange
     *     The rank_change
     */
    public void setRankChange(String rankChange) {
        this.rankChange = rankChange;
    }

    /**
     * 
     * @return
     *     The allRate
     */
    public String getAllRate() {
        return allRate;
    }

    /**
     * 
     * @param allRate
     *     The all_rate
     */
    public void setAllRate(String allRate) {
        this.allRate = allRate;
    }

}

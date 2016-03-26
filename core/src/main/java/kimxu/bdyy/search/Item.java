
package kimxu.bdyy.search;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Item {

    @SerializedName("song_id")
    @Expose
    private String songId;
    @SerializedName("song_title")
    @Expose
    private String songTitle;
    @SerializedName("artist_id")
    @Expose
    private String artistId;
    @SerializedName("artist_name")
    @Expose
    private String artistName;
    @SerializedName("album_id")
    @Expose
    private String albumId;
    @SerializedName("album_title")
    @Expose
    private String albumTitle;
    @SerializedName("learn")
    @Expose
    private Long learn;

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
     *     The songTitle
     */
    public String getSongTitle() {
        return songTitle;
    }

    /**
     * 
     * @param songTitle
     *     The song_title
     */
    public void setSongTitle(String songTitle) {
        this.songTitle = songTitle;
    }

    /**
     * 
     * @return
     *     The artistId
     */
    public String getArtistId() {
        return artistId;
    }

    /**
     * 
     * @param artistId
     *     The artist_id
     */
    public void setArtistId(String artistId) {
        this.artistId = artistId;
    }

    /**
     * 
     * @return
     *     The artistName
     */
    public String getArtistName() {
        return artistName;
    }

    /**
     * 
     * @param artistName
     *     The artist_name
     */
    public void setArtistName(String artistName) {
        this.artistName = artistName;
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
     *     The learn
     */
    public Long getLearn() {
        return learn;
    }

    /**
     * 
     * @param learn
     *     The learn
     */
    public void setLearn(Long learn) {
        this.learn = learn;
    }

}


package kimxu.bdyy.search.merge;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ArtistList {

    @SerializedName("artist_id")
    @Expose
    private String artistId;
    @SerializedName("author")
    @Expose
    private String author;
    @SerializedName("ting_uid")
    @Expose
    private String tingUid;
    @SerializedName("avatar_middle")
    @Expose
    private String avatarMiddle;
    @SerializedName("album_num")
    @Expose
    private Long albumNum;
    @SerializedName("song_num")
    @Expose
    private Long songNum;
    @SerializedName("country")
    @Expose
    private String country;
    @SerializedName("artist_desc")
    @Expose
    private String artistDesc;
    @SerializedName("artist_source")
    @Expose
    private String artistSource;

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
     *     The tingUid
     */
    public String getTingUid() {
        return tingUid;
    }

    /**
     * 
     * @param tingUid
     *     The ting_uid
     */
    public void setTingUid(String tingUid) {
        this.tingUid = tingUid;
    }

    /**
     * 
     * @return
     *     The avatarMiddle
     */
    public String getAvatarMiddle() {
        return avatarMiddle;
    }

    /**
     * 
     * @param avatarMiddle
     *     The avatar_middle
     */
    public void setAvatarMiddle(String avatarMiddle) {
        this.avatarMiddle = avatarMiddle;
    }

    /**
     * 
     * @return
     *     The albumNum
     */
    public Long getAlbumNum() {
        return albumNum;
    }

    /**
     * 
     * @param albumNum
     *     The album_num
     */
    public void setAlbumNum(Long albumNum) {
        this.albumNum = albumNum;
    }

    /**
     * 
     * @return
     *     The songNum
     */
    public Long getSongNum() {
        return songNum;
    }

    /**
     * 
     * @param songNum
     *     The song_num
     */
    public void setSongNum(Long songNum) {
        this.songNum = songNum;
    }

    /**
     * 
     * @return
     *     The country
     */
    public String getCountry() {
        return country;
    }

    /**
     * 
     * @param country
     *     The country
     */
    public void setCountry(String country) {
        this.country = country;
    }

    /**
     * 
     * @return
     *     The artistDesc
     */
    public String getArtistDesc() {
        return artistDesc;
    }

    /**
     * 
     * @param artistDesc
     *     The artist_desc
     */
    public void setArtistDesc(String artistDesc) {
        this.artistDesc = artistDesc;
    }

    /**
     * 
     * @return
     *     The artistSource
     */
    public String getArtistSource() {
        return artistSource;
    }

    /**
     * 
     * @param artistSource
     *     The artist_source
     */
    public void setArtistSource(String artistSource) {
        this.artistSource = artistSource;
    }

}

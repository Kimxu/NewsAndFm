
package kimxu.bdyy.search.searchresult;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Song {

    @SerializedName("bitrate_fee")
    @Expose
    private String bitrateFee;
    @SerializedName("yyr_artist")
    @Expose
    private String yyrArtist;
    @SerializedName("songname")
    @Expose
    private String songname;
    @SerializedName("artistname")
    @Expose
    private String artistname;
    @SerializedName("control")
    @Expose
    private String control;
    @SerializedName("songid")
    @Expose
    private String songid;
    @SerializedName("has_mv")
    @Expose
    private String hasMv;
    @SerializedName("encrypted_songid")
    @Expose
    private String encryptedSongid;

    /**
     * 
     * @return
     *     The bitrateFee
     */
    public String getBitrateFee() {
        return bitrateFee;
    }

    /**
     * 
     * @param bitrateFee
     *     The bitrate_fee
     */
    public void setBitrateFee(String bitrateFee) {
        this.bitrateFee = bitrateFee;
    }

    /**
     * 
     * @return
     *     The yyrArtist
     */
    public String getYyrArtist() {
        return yyrArtist;
    }

    /**
     * 
     * @param yyrArtist
     *     The yyr_artist
     */
    public void setYyrArtist(String yyrArtist) {
        this.yyrArtist = yyrArtist;
    }

    /**
     * 
     * @return
     *     The songname
     */
    public String getSongname() {
        return songname;
    }

    /**
     * 
     * @param songname
     *     The songname
     */
    public void setSongname(String songname) {
        this.songname = songname;
    }

    /**
     * 
     * @return
     *     The artistname
     */
    public String getArtistname() {
        return artistname;
    }

    /**
     * 
     * @param artistname
     *     The artistname
     */
    public void setArtistname(String artistname) {
        this.artistname = artistname;
    }

    /**
     * 
     * @return
     *     The control
     */
    public String getControl() {
        return control;
    }

    /**
     * 
     * @param control
     *     The control
     */
    public void setControl(String control) {
        this.control = control;
    }

    /**
     * 
     * @return
     *     The songid
     */
    public String getSongid() {
        return songid;
    }

    /**
     * 
     * @param songid
     *     The songid
     */
    public void setSongid(String songid) {
        this.songid = songid;
    }

    /**
     * 
     * @return
     *     The hasMv
     */
    public String getHasMv() {
        return hasMv;
    }

    /**
     * 
     * @param hasMv
     *     The has_mv
     */
    public void setHasMv(String hasMv) {
        this.hasMv = hasMv;
    }

    /**
     * 
     * @return
     *     The encryptedSongid
     */
    public String getEncryptedSongid() {
        return encryptedSongid;
    }

    /**
     * 
     * @param encryptedSongid
     *     The encrypted_songid
     */
    public void setEncryptedSongid(String encryptedSongid) {
        this.encryptedSongid = encryptedSongid;
    }

}

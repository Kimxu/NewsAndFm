
package kimxu.bdyy.searchSongId;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Album {

    @SerializedName("albumname")
    @Expose
    private String albumname;
    @SerializedName("artistpic")
    @Expose
    private String artistpic;
    @SerializedName("albumid")
    @Expose
    private String albumid;
    @SerializedName("artistname")
    @Expose
    private String artistname;

    /**
     * 
     * @return
     *     The albumname
     */
    public String getAlbumname() {
        return albumname;
    }

    /**
     * 
     * @param albumname
     *     The albumname
     */
    public void setAlbumname(String albumname) {
        this.albumname = albumname;
    }

    /**
     * 
     * @return
     *     The artistpic
     */
    public String getArtistpic() {
        return artistpic;
    }

    /**
     * 
     * @param artistpic
     *     The artistpic
     */
    public void setArtistpic(String artistpic) {
        this.artistpic = artistpic;
    }

    /**
     * 
     * @return
     *     The albumid
     */
    public String getAlbumid() {
        return albumid;
    }

    /**
     * 
     * @param albumid
     *     The albumid
     */
    public void setAlbumid(String albumid) {
        this.albumid = albumid;
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

}

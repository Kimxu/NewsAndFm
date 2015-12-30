
package kimxu.xmly.album;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Album {

    @SerializedName("ret")
    @Expose
    private Long ret;
    @SerializedName("album")
    @Expose
    private Album_ album;
    @SerializedName("tracks")
    @Expose
    private Tracks tracks;
    @SerializedName("msg")
    @Expose
    private String msg;

    /**
     * 
     * @return
     *     The ret
     */
    public Long getRet() {
        return ret;
    }

    /**
     * 
     * @param ret
     *     The ret
     */
    public void setRet(Long ret) {
        this.ret = ret;
    }

    /**
     * 
     * @return
     *     The album
     */
    public Album_ getAlbum() {
        return album;
    }

    /**
     * 
     * @param album
     *     The album
     */
    public void setAlbum(Album_ album) {
        this.album = album;
    }

    /**
     * 
     * @return
     *     The tracks
     */
    public Tracks getTracks() {
        return tracks;
    }

    /**
     * 
     * @param tracks
     *     The tracks
     */
    public void setTracks(Tracks tracks) {
        this.tracks = tracks;
    }

    /**
     * 
     * @return
     *     The msg
     */
    public String getMsg() {
        return msg;
    }

    /**
     * 
     * @param msg
     *     The msg
     */
    public void setMsg(String msg) {
        this.msg = msg;
    }

}

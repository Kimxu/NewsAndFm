
package kimxu.bdyy.search.catalogsug;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

import kimxu.bdyy.Album;
import kimxu.bdyy.Song;
import kimxu.mvp.model.IModel;

public class CatalogSug implements IModel{

    @SerializedName("song")
    @Expose
    private List<Song> song = new ArrayList<Song>();
    @SerializedName("order")
    @Expose
    private String order;
    @SerializedName("error_code")
    @Expose
    private Long errorCode;
    @SerializedName("album")
    @Expose
    private List<Album> album = new ArrayList<Album>();

    /**
     * 
     * @return
     *     The song
     */
    public List<Song> getSong() {
        return song;
    }

    /**
     * 
     * @param song
     *     The song
     */
    public void setSong(List<Song> song) {
        this.song = song;
    }

    /**
     * 
     * @return
     *     The order
     */
    public String getOrder() {
        return order;
    }

    /**
     * 
     * @param order
     *     The order
     */
    public void setOrder(String order) {
        this.order = order;
    }

    /**
     * 
     * @return
     *     The errorCode
     */
    public Long getErrorCode() {
        return errorCode;
    }

    /**
     * 
     * @param errorCode
     *     The error_code
     */
    public void setErrorCode(Long errorCode) {
        this.errorCode = errorCode;
    }

    /**
     * 
     * @return
     *     The album
     */
    public List<Album> getAlbum() {
        return album;
    }

    /**
     * 
     * @param album
     *     The album
     */
    public void setAlbum(List<Album> album) {
        this.album = album;
    }

}

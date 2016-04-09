
package kimxu.bdyy.search.merge;

import java.util.ArrayList;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SongInfo {

    @SerializedName("total")
    @Expose
    private Long total;
    @SerializedName("song_list")
    @Expose
    private List<SongList> songList = new ArrayList<SongList>();

    /**
     * 
     * @return
     *     The total
     */
    public Long getTotal() {
        return total;
    }

    /**
     * 
     * @param total
     *     The total
     */
    public void setTotal(Long total) {
        this.total = total;
    }

    /**
     * 
     * @return
     *     The songList
     */
    public List<SongList> getSongList() {
        return songList;
    }

    /**
     * 
     * @param songList
     *     The song_list
     */
    public void setSongList(List<SongList> songList) {
        this.songList = songList;
    }

}

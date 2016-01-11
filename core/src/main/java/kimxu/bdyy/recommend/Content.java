
package kimxu.bdyy.recommend;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class Content {

    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("song_list")
    @Expose
    private List<SongList> songList = new ArrayList<SongList>();

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

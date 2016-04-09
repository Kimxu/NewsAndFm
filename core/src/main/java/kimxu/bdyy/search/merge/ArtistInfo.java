
package kimxu.bdyy.search.merge;

import java.util.ArrayList;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ArtistInfo {

    @SerializedName("total")
    @Expose
    private Long total;
    @SerializedName("artist_list")
    @Expose
    private List<ArtistList> artistList = new ArrayList<ArtistList>();

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
     *     The artistList
     */
    public List<ArtistList> getArtistList() {
        return artistList;
    }

    /**
     * 
     * @param artistList
     *     The artist_list
     */
    public void setArtistList(List<ArtistList> artistList) {
        this.artistList = artistList;
    }

}

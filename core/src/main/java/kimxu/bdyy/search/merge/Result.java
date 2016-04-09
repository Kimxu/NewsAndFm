
package kimxu.bdyy.search.merge;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Result {

    @SerializedName("query")
    @Expose
    private String query;
    @SerializedName("syn_words")
    @Expose
    private String synWords;
    @SerializedName("rqt_type")
    @Expose
    private Long rqtType;
    @SerializedName("song_info")
    @Expose
    private SongInfo songInfo;
    @SerializedName("artist_info")
    @Expose
    private ArtistInfo artistInfo;

    /**
     * 
     * @return
     *     The query
     */
    public String getQuery() {
        return query;
    }

    /**
     * 
     * @param query
     *     The query
     */
    public void setQuery(String query) {
        this.query = query;
    }

    /**
     * 
     * @return
     *     The synWords
     */
    public String getSynWords() {
        return synWords;
    }

    /**
     * 
     * @param synWords
     *     The syn_words
     */
    public void setSynWords(String synWords) {
        this.synWords = synWords;
    }

    /**
     * 
     * @return
     *     The rqtType
     */
    public Long getRqtType() {
        return rqtType;
    }

    /**
     * 
     * @param rqtType
     *     The rqt_type
     */
    public void setRqtType(Long rqtType) {
        this.rqtType = rqtType;
    }

    /**
     * 
     * @return
     *     The songInfo
     */
    public SongInfo getSongInfo() {
        return songInfo;
    }

    /**
     * 
     * @param songInfo
     *     The song_info
     */
    public void setSongInfo(SongInfo songInfo) {
        this.songInfo = songInfo;
    }

    /**
     * 
     * @return
     *     The artistInfo
     */
    public ArtistInfo getArtistInfo() {
        return artistInfo;
    }

    /**
     * 
     * @param artistInfo
     *     The artist_info
     */
    public void setArtistInfo(ArtistInfo artistInfo) {
        this.artistInfo = artistInfo;
    }

}

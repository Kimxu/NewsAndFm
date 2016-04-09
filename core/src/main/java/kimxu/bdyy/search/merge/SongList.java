
package kimxu.bdyy.search.merge;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SongList {

    @SerializedName("content")
    @Expose
    private String content;
    @SerializedName("copy_type")
    @Expose
    private String copyType;
    @SerializedName("toneid")
    @Expose
    private String toneid;
    @SerializedName("info")
    @Expose
    private String info;
    @SerializedName("all_rate")
    @Expose
    private String allRate;
    @SerializedName("resource_type")
    @Expose
    private Long resourceType;
    @SerializedName("relate_status")
    @Expose
    private Long relateStatus;
    @SerializedName("has_mv_mobile")
    @Expose
    private Long hasMvMobile;
    @SerializedName("song_id")
    @Expose
    private String songId;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("ting_uid")
    @Expose
    private String tingUid;
    @SerializedName("author")
    @Expose
    private String author;
    @SerializedName("album_id")
    @Expose
    private String albumId;
    @SerializedName("album_title")
    @Expose
    private String albumTitle;
    @SerializedName("is_first_publish")
    @Expose
    private Long isFirstPublish;
    @SerializedName("havehigh")
    @Expose
    private Long havehigh;
    @SerializedName("charge")
    @Expose
    private Long charge;
    @SerializedName("has_mv")
    @Expose
    private Long hasMv;
    @SerializedName("learn")
    @Expose
    private Long learn;
    @SerializedName("song_source")
    @Expose
    private String songSource;
    @SerializedName("piao_id")
    @Expose
    private String piaoId;
    @SerializedName("korean_bb_song")
    @Expose
    private String koreanBbSong;
    @SerializedName("resource_type_ext")
    @Expose
    private String resourceTypeExt;
    @SerializedName("mv_provider")
    @Expose
    private String mvProvider;
    @SerializedName("artist_id")
    @Expose
    private String artistId;
    @SerializedName("all_artist_id")
    @Expose
    private String allArtistId;
    @SerializedName("lrclink")
    @Expose
    private String lrclink;
    @SerializedName("data_source")
    @Expose
    private Long dataSource;
    @SerializedName("cluster_id")
    @Expose
    private Long clusterId;

    /**
     * 
     * @return
     *     The content
     */
    public String getContent() {
        return content;
    }

    /**
     * 
     * @param content
     *     The content
     */
    public void setContent(String content) {
        this.content = content;
    }

    /**
     * 
     * @return
     *     The copyType
     */
    public String getCopyType() {
        return copyType;
    }

    /**
     * 
     * @param copyType
     *     The copy_type
     */
    public void setCopyType(String copyType) {
        this.copyType = copyType;
    }

    /**
     * 
     * @return
     *     The toneid
     */
    public String getToneid() {
        return toneid;
    }

    /**
     * 
     * @param toneid
     *     The toneid
     */
    public void setToneid(String toneid) {
        this.toneid = toneid;
    }

    /**
     * 
     * @return
     *     The info
     */
    public String getInfo() {
        return info;
    }

    /**
     * 
     * @param info
     *     The info
     */
    public void setInfo(String info) {
        this.info = info;
    }

    /**
     * 
     * @return
     *     The allRate
     */
    public String getAllRate() {
        return allRate;
    }

    /**
     * 
     * @param allRate
     *     The all_rate
     */
    public void setAllRate(String allRate) {
        this.allRate = allRate;
    }

    /**
     * 
     * @return
     *     The resourceType
     */
    public Long getResourceType() {
        return resourceType;
    }

    /**
     * 
     * @param resourceType
     *     The resource_type
     */
    public void setResourceType(Long resourceType) {
        this.resourceType = resourceType;
    }

    /**
     * 
     * @return
     *     The relateStatus
     */
    public Long getRelateStatus() {
        return relateStatus;
    }

    /**
     * 
     * @param relateStatus
     *     The relate_status
     */
    public void setRelateStatus(Long relateStatus) {
        this.relateStatus = relateStatus;
    }

    /**
     * 
     * @return
     *     The hasMvMobile
     */
    public Long getHasMvMobile() {
        return hasMvMobile;
    }

    /**
     * 
     * @param hasMvMobile
     *     The has_mv_mobile
     */
    public void setHasMvMobile(Long hasMvMobile) {
        this.hasMvMobile = hasMvMobile;
    }

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
     *     The isFirstPublish
     */
    public Long getIsFirstPublish() {
        return isFirstPublish;
    }

    /**
     * 
     * @param isFirstPublish
     *     The is_first_publish
     */
    public void setIsFirstPublish(Long isFirstPublish) {
        this.isFirstPublish = isFirstPublish;
    }

    /**
     * 
     * @return
     *     The havehigh
     */
    public Long getHavehigh() {
        return havehigh;
    }

    /**
     * 
     * @param havehigh
     *     The havehigh
     */
    public void setHavehigh(Long havehigh) {
        this.havehigh = havehigh;
    }

    /**
     * 
     * @return
     *     The charge
     */
    public Long getCharge() {
        return charge;
    }

    /**
     * 
     * @param charge
     *     The charge
     */
    public void setCharge(Long charge) {
        this.charge = charge;
    }

    /**
     * 
     * @return
     *     The hasMv
     */
    public Long getHasMv() {
        return hasMv;
    }

    /**
     * 
     * @param hasMv
     *     The has_mv
     */
    public void setHasMv(Long hasMv) {
        this.hasMv = hasMv;
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

    /**
     * 
     * @return
     *     The songSource
     */
    public String getSongSource() {
        return songSource;
    }

    /**
     * 
     * @param songSource
     *     The song_source
     */
    public void setSongSource(String songSource) {
        this.songSource = songSource;
    }

    /**
     * 
     * @return
     *     The piaoId
     */
    public String getPiaoId() {
        return piaoId;
    }

    /**
     * 
     * @param piaoId
     *     The piao_id
     */
    public void setPiaoId(String piaoId) {
        this.piaoId = piaoId;
    }

    /**
     * 
     * @return
     *     The koreanBbSong
     */
    public String getKoreanBbSong() {
        return koreanBbSong;
    }

    /**
     * 
     * @param koreanBbSong
     *     The korean_bb_song
     */
    public void setKoreanBbSong(String koreanBbSong) {
        this.koreanBbSong = koreanBbSong;
    }

    /**
     * 
     * @return
     *     The resourceTypeExt
     */
    public String getResourceTypeExt() {
        return resourceTypeExt;
    }

    /**
     * 
     * @param resourceTypeExt
     *     The resource_type_ext
     */
    public void setResourceTypeExt(String resourceTypeExt) {
        this.resourceTypeExt = resourceTypeExt;
    }

    /**
     * 
     * @return
     *     The mvProvider
     */
    public String getMvProvider() {
        return mvProvider;
    }

    /**
     * 
     * @param mvProvider
     *     The mv_provider
     */
    public void setMvProvider(String mvProvider) {
        this.mvProvider = mvProvider;
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
     *     The allArtistId
     */
    public String getAllArtistId() {
        return allArtistId;
    }

    /**
     * 
     * @param allArtistId
     *     The all_artist_id
     */
    public void setAllArtistId(String allArtistId) {
        this.allArtistId = allArtistId;
    }

    /**
     * 
     * @return
     *     The lrclink
     */
    public String getLrclink() {
        return lrclink;
    }

    /**
     * 
     * @param lrclink
     *     The lrclink
     */
    public void setLrclink(String lrclink) {
        this.lrclink = lrclink;
    }

    /**
     * 
     * @return
     *     The dataSource
     */
    public Long getDataSource() {
        return dataSource;
    }

    /**
     * 
     * @param dataSource
     *     The data_source
     */
    public void setDataSource(Long dataSource) {
        this.dataSource = dataSource;
    }

    /**
     * 
     * @return
     *     The clusterId
     */
    public Long getClusterId() {
        return clusterId;
    }

    /**
     * 
     * @param clusterId
     *     The cluster_id
     */
    public void setClusterId(Long clusterId) {
        this.clusterId = clusterId;
    }

}

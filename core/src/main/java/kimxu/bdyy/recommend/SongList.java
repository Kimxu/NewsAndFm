
package kimxu.bdyy.recommend;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SongList {

    @SerializedName("artist_id")
    @Expose
    private String artistId;
    @SerializedName("pic_big")
    @Expose
    private String picBig;
    @SerializedName("pic_small")
    @Expose
    private String picSmall;
    @SerializedName("pic_premium")
    @Expose
    private String picPremium;
    @SerializedName("pic_huge")
    @Expose
    private String picHuge;
    @SerializedName("pic_singer")
    @Expose
    private String picSinger;
    @SerializedName("all_artist_ting_uid")
    @Expose
    private String allArtistTingUid;
    @SerializedName("file_duration")
    @Expose
    private String fileDuration;
    @SerializedName("del_status")
    @Expose
    private String delStatus;
    @SerializedName("resource_type")
    @Expose
    private String resourceType;
    @SerializedName("all_rate")
    @Expose
    private String allRate;
    @SerializedName("toneid")
    @Expose
    private String toneid;
    @SerializedName("copy_type")
    @Expose
    private String copyType;
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
    @SerializedName("desc")
    @Expose
    private String desc;
    @SerializedName("url")
    @Expose
    private String url;
    @SerializedName("recommend_reason")
    @Expose
    private String recommendReason;

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
     *     The picBig
     */
    public String getPicBig() {
        return picBig;
    }

    /**
     * 
     * @param picBig
     *     The pic_big
     */
    public void setPicBig(String picBig) {
        this.picBig = picBig;
    }

    /**
     * 
     * @return
     *     The picSmall
     */
    public String getPicSmall() {
        return picSmall;
    }

    /**
     * 
     * @param picSmall
     *     The pic_small
     */
    public void setPicSmall(String picSmall) {
        this.picSmall = picSmall;
    }

    /**
     * 
     * @return
     *     The picPremium
     */
    public String getPicPremium() {
        return picPremium;
    }

    /**
     * 
     * @param picPremium
     *     The pic_premium
     */
    public void setPicPremium(String picPremium) {
        this.picPremium = picPremium;
    }

    /**
     * 
     * @return
     *     The picHuge
     */
    public String getPicHuge() {
        return picHuge;
    }

    /**
     * 
     * @param picHuge
     *     The pic_huge
     */
    public void setPicHuge(String picHuge) {
        this.picHuge = picHuge;
    }

    /**
     * 
     * @return
     *     The picSinger
     */
    public String getPicSinger() {
        return picSinger;
    }

    /**
     * 
     * @param picSinger
     *     The pic_singer
     */
    public void setPicSinger(String picSinger) {
        this.picSinger = picSinger;
    }

    /**
     * 
     * @return
     *     The allArtistTingUid
     */
    public String getAllArtistTingUid() {
        return allArtistTingUid;
    }

    /**
     * 
     * @param allArtistTingUid
     *     The all_artist_ting_uid
     */
    public void setAllArtistTingUid(String allArtistTingUid) {
        this.allArtistTingUid = allArtistTingUid;
    }

    /**
     * 
     * @return
     *     The fileDuration
     */
    public String getFileDuration() {
        return fileDuration;
    }

    /**
     * 
     * @param fileDuration
     *     The file_duration
     */
    public void setFileDuration(String fileDuration) {
        this.fileDuration = fileDuration;
    }

    /**
     * 
     * @return
     *     The delStatus
     */
    public String getDelStatus() {
        return delStatus;
    }

    /**
     * 
     * @param delStatus
     *     The del_status
     */
    public void setDelStatus(String delStatus) {
        this.delStatus = delStatus;
    }

    /**
     * 
     * @return
     *     The resourceType
     */
    public String getResourceType() {
        return resourceType;
    }

    /**
     * 
     * @param resourceType
     *     The resource_type
     */
    public void setResourceType(String resourceType) {
        this.resourceType = resourceType;
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
     *     The desc
     */
    public String getDesc() {
        return desc;
    }

    /**
     * 
     * @param desc
     *     The desc
     */
    public void setDesc(String desc) {
        this.desc = desc;
    }

    /**
     * 
     * @return
     *     The url
     */
    public String getUrl() {
        return url;
    }

    /**
     * 
     * @param url
     *     The url
     */
    public void setUrl(String url) {
        this.url = url;
    }

    /**
     * 
     * @return
     *     The recommendReason
     */
    public String getRecommendReason() {
        return recommendReason;
    }

    /**
     * 
     * @param recommendReason
     *     The recommend_reason
     */
    public void setRecommendReason(String recommendReason) {
        this.recommendReason = recommendReason;
    }

}

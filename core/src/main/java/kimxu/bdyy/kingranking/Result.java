
package kimxu.bdyy.kingranking;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Result {

    @SerializedName("pic_big")
    @Expose
    private String picBig;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("author")
    @Expose
    private String author;

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

}

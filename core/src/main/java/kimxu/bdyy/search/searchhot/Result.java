package kimxu.bdyy.search.searchhot;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Result {

    @SerializedName("strong")
    @Expose
    private Long strong;
    @SerializedName("word")
    @Expose
    private String word;

    /**
     * 
     * @return
     *     The strong
     */
    public Long getStrong() {
        return strong;
    }

    /**
     * 
     * @param strong
     *     The strong
     */
    public void setStrong(Long strong) {
        this.strong = strong;
    }

    /**
     * 
     * @return
     *     The word
     */
    public String getWord() {
        return word;
    }

    /**
     * 
     * @param word
     *     The word
     */
    public void setWord(String word) {
        this.word = word;
    }

}


package kimxu.bdyy.style;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Result {

    @SerializedName("action")
    @Expose
    private List<Action> action = new ArrayList<Action>();
    @SerializedName("emotion")
    @Expose
    private List<Emotion> emotion = new ArrayList<Emotion>();
    @SerializedName("operation")
    @Expose
    private List<Operation> operation = new ArrayList<Operation>();
    @SerializedName("other")
    @Expose
    private List<Other> other = new ArrayList<Other>();

    /**
     * 
     * @return
     *     The action
     */
    public List<Action> getAction() {
        return action;
    }

    /**
     * 
     * @param action
     *     The action
     */
    public void setAction(List<Action> action) {
        this.action = action;
    }

    /**
     * 
     * @return
     *     The emotion
     */
    public List<Emotion> getEmotion() {
        return emotion;
    }

    /**
     * 
     * @param emotion
     *     The emotion
     */
    public void setEmotion(List<Emotion> emotion) {
        this.emotion = emotion;
    }

    /**
     * 
     * @return
     *     The operation
     */
    public List<Operation> getOperation() {
        return operation;
    }

    /**
     * 
     * @param operation
     *     The operation
     */
    public void setOperation(List<Operation> operation) {
        this.operation = operation;
    }

    /**
     * 
     * @return
     *     The other
     */
    public List<Other> getOther() {
        return other;
    }

    /**
     * 
     * @param other
     *     The other
     */
    public void setOther(List<Other> other) {
        this.other = other;
    }

}

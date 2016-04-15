package me.kimxu.enums;

/**
 * 播放模式
 * Created by wcy on 2015/12/26.
 */
public enum PlayModeEnum {
    LOOP(0),
    SHUFFLE(1),
    ONE(2);

    private int value;

    PlayModeEnum(int value) {
        this.value = value;
    }

    public static PlayModeEnum valueOf(int value) {
        switch (value) {
            case 0:
                return LOOP;
            case 1:
                return SHUFFLE;
            case 2:
                return ONE;
            default:
                return LOOP;
        }
    }

    public int value() {
        return value;
    }
}

package kimxu.newsandfm.play;

public class State {
    public static final int
            IDLE = 0,
            INITIALIZED = 1,
            PREPARED = 2,
            PREPARING = 3,
            STARTED = 4,
            PAUSED = 5,
            STOPPED = 6,
            COMPLETED = 7,
            END = -1,
            ERROR = -2;
}
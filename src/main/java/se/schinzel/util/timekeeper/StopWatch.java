package se.schinzel.util.timekeeper;

/**
 * The purpose of this class act as a StopWatch, no more no less.
 *
 * @author schinzel
 */
class StopWatch {
    /**
     * The time when the lap started.
     */
    private long mLapStartTime = 0l;
    /**
     * Number of laps measured.
     */
    private int mNumberOfLaps = 0;
    /**
     * The time of all laps.
     */
    private long mSumOfAllLaps;
    /**
     * The current state of this stop watch.
     */
    private State mState = State.STOPPED;

    private enum State {
        STARTED, STOPPED;
    }


    private StopWatch() {
    }


    static StopWatch create() {
        return new StopWatch();
    }


    /**
     * Start measuring the time for a lap.
     *
     * @return This for chaining.
     */
    StopWatch start() {
        if (this.isStarted()){
            throw new RuntimeException("Cannot start as is already started");
        }
        mLapStartTime = System.nanoTime();
        mState = State.STARTED;
        return this;
    }


    /**
     * Stop the measurement of a lap
     *
     * @return This for chaining.
     */
    StopWatch stop() {
        if (!this.isStarted()){
            throw new RuntimeException("Cannot stop as is not started");
        }
        long currentLapTime = System.nanoTime() - mLapStartTime;
        mState = State.STOPPED;
        mSumOfAllLaps += currentLapTime;
        mLapStartTime = 0;
        mNumberOfLaps++;
        return this;
    }


    
    /**
     * 
     * @return True if is currently in mid lap, else false. 
     */
    boolean isStarted() {
        return (mState == State.STARTED);
    }
    
    /**
     *
     * @return The number of laps measured.
     */
    int getLaps() {
        return mNumberOfLaps;
    }


    /**
     *
     * @return The average lap time in ms.
     */
    double getAvgInMs() {
        double avg = mSumOfAllLaps / mNumberOfLaps * 1d;
        return toMillis(avg);
    }


    /**
     *
     * @return The total time of all laps measured.
     */
    double getTotTimeInMs() {
        return toMillis(mSumOfAllLaps);
    }


    /**
     *
     * @param nanos
     * @return The argument nanos as millis.
     */
    static double toMillis(long nanos) {
        return nanos / 1000000;
    }


    /**
     *
     * @param nanos
     * @return The argument nanos as millis.
     */
    static double toMillis(double nanos) {
        return nanos / 1000000;
    }

}

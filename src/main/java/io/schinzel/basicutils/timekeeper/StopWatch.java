package io.schinzel.basicutils.timekeeper;

import io.schinzel.basicutils.thrower.Thrower;
import lombok.Getter;
import lombok.experimental.Accessors;

/**
 * The purpose of this class act as a StopWatch, no more no less.
 *
 * @author schinzel
 */
@Accessors(prefix = "m")
class StopWatch {
    /** The time when the lap started */
    private long mLapStartTime = 0L;
    /** Number of laps measured. */
    @Getter private int mNumberOfLaps = 0;
    /** The time of all laps. */
    private long mSumTimeOfAllLaps;
    /** The current state of this stop watch. */
    private State mState = State.STOPPED;

    private enum State {
        STARTED, STOPPED
    }


    private StopWatch() {
    }


    /**
     * @return A new instance of the StopWatch.
     */
    static StopWatch create() {
        return new StopWatch();
    }


    /**
     * Start measuring the time of a lap.
     *
     * @return This for chaining.
     */
    StopWatch start() {
        Thrower.throwIfTrue(this.isStarted(), "Cannot start as is already started");
        mLapStartTime = System.nanoTime();
        mState = State.STARTED;
        return this;
    }


    /**
     * Stop the measurement of a lap.
     *
     * @return This for chaining.
     */
    StopWatch stop() {
        Thrower.throwIfFalse(this.isStarted(), "Cannot stop as is not started");
        long currentLapTime = System.nanoTime() - mLapStartTime;
        mState = State.STOPPED;
        mSumTimeOfAllLaps += currentLapTime;
        mLapStartTime = 0;
        mNumberOfLaps++;
        return this;
    }


    /**
     * @return True if is currently in mid lap, else false.
     */
    boolean isStarted() {
        return (mState == State.STARTED);
    }


    /**
     * @return The average lap time in ms.
     */
    double getAvgInMs() {
        double avg = (double) mSumTimeOfAllLaps / mNumberOfLaps;
        //Convert nanos to millis
        return avg / 1_000_000D;
    }


    /**
     * @return The total time of all laps measured.
     */
    double getTotTimeInMs() {
        //Convert from nanos to millis
        return (double) mSumTimeOfAllLaps / 1_000_000D;
    }


}

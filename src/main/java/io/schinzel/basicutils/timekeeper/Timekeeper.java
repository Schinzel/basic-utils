package io.schinzel.basicutils.timekeeper;

import io.schinzel.basicutils.state.State;
import io.schinzel.basicutils.str.Str;

/**
 * The purpose of this class is for measuring the time one or several lines of
 * code consumes. Typically used for debugging.
 * <p>
 * When debugging performance one typically starts with the outermost method and
 * step by step dig further down the call stack.
 * <p>
 * As such the timekeeper creates an hierarchy of measurements. If a new
 * measurement is started before the current is stopped, as subtree of
 * measurements is created.
 *
 * @author schinzel
 */
@SuppressWarnings({"WeakerAccess", "UnusedReturnValue"})
public class Timekeeper {
    /** Singleton instance */
    private static Timekeeper mSingletonInstance = new Timekeeper();
    /** The laps are in a tree hierarchy. This is the current executing node in the tree. */
    private Lap mCurrentLap = new Lap("root", null).start();


    /**
     * @return A freshly created Timekeeper.
     */
    public static Timekeeper create() {
        return new Timekeeper();
    }


    /**
     * Note, there also exists a create-method.
     *
     * @return The singleton instance.
     */
    public static Timekeeper getSingleton() {
        return mSingletonInstance == null
                ? new Timekeeper()
                : mSingletonInstance;
    }


    /**
     * Starts tracking a new lap. This lap will be a
     * sub-lap to the currently running lap.
     *
     * @param lapName The label to the measurement.
     * @return This for chaining
     */
    public Timekeeper start(String lapName) {
        mCurrentLap = mCurrentLap.start(lapName);
        return this;
    }


    /**
     * Stops the current measurement being tracked.
     *
     * @return This for chaining
     */
    public Timekeeper stop() {
        mCurrentLap = mCurrentLap.stop();
        return this;
    }


    /**
     * Stops the current lap and starts a new lap. Starts tracking a new lap. This lap will be a
     * sub-lap to the currently running lap.
     *
     * @param lapName The name of the lap to start
     * @return This for chaining
     */
    public Timekeeper stopAndStart(String lapName) {
        mCurrentLap = mCurrentLap.stop().start(lapName);
        return this;
    }


    /**
     * Clears all saved data.
     *
     * @return This for chaining
     */
    public final Timekeeper reset() {
        mSingletonInstance = null;
        return this;
    }


    /**
     * @return The results of the times measured from the root node and down.
     */
    public State getResults() {
        Lap root = mCurrentLap.getRoot();
        if (root.getStopWatch().isStarted()) {
            root.stop();
        }
        return root.getState();
    }


    @Override
    public String toString() {
        return Str.create("Current lap: ").aq(mCurrentLap.getKey()).toString();
    }


}

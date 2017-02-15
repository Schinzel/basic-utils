package io.schinzel.basicutils.timekeeper;

import io.schinzel.json.JsonOrdered;

/**
 * The purpose of this class is for measuring the time one or several lines of
 * code consumes. Typically used for debugging.
 *
 * When debugging performance one typically starts with the outermost method and
 * step by step dig further down the call stack.
 *
 * As such the timekeeper creates an hierarchy of measurements. If a new
 * measurement is started before the current is stopped, as subtree of
 * measurements is created.
 *
 * @author schinzel
 */
public class Timekeeper {

    private Lap mCurrentLap = new Lap("root", null).start();

    private static Timekeeper INSTANCE = new Timekeeper();

    /**
     *
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
        if (INSTANCE == null) {
            INSTANCE = new Timekeeper();
        }
        return INSTANCE;
    }


    /**
     * Starts tracking a new time measurement. This measurement will be a
     * sub-measurement to the currently running measurement.
     *
     * @param lapName The label to the measurement.
     * @return This for chaining
     */
    public Timekeeper startLap(String lapName) {
        mCurrentLap = mCurrentLap.start(lapName);
        return this;
    }


    /**
     * Stops the current measurement being tracked.
     *
     * @return This for chaining
     */
    public Timekeeper stopLap() {
        mCurrentLap = mCurrentLap.stop();
        return this;
    }


    /**
     * Stops the whole timekeeper.
     *
     * @return This for chaining
     */
    public Timekeeper stop() {
        mCurrentLap.getRoot().stop();
        return this;
    }


    /**
     * Clears all saved data.
     *
     * @return This for chaining
     */
    public final Timekeeper reset() {
        INSTANCE = null;
        return this;
    }


    /**
     *
     * @return The results of the times measured from the root node and down.
     */
    @Override
    public String toString() {
        return mCurrentLap.getRoot().getState().toString();
    }


    /**
     *
     * @return The results of the times measured as JSON.
     */
    public JsonOrdered toJson() {
        return mCurrentLap.getRoot().getState().getJson();
    }


}

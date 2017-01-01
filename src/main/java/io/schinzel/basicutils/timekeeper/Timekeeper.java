package io.schinzel.basicutils.timekeeper;

import org.json.JSONObject;

/**
 * The purpose of this class is for measuring the time a one or several lines
 * of code consumes. Typically used for debugging.

 When debugging performance one typically starts with the outermost method
 and step by step dig further down the call stack.

 As such the time keeper creates an hierarchy of measurements. If a new
 measurement is started before the current is stopped, as subtree of
 measurements is created.

 Example:
 Timekeeper tk = Timekeeper.getSingleton();
 tk.startLap("A");
 tk.stopLap();
 tk.startLap("B");
 tk.startLap("B1");
 MiscUtil.snooze(10);
 tk.stopLap();
 tk.startLap("B2");
 MiscUtil.snooze(20);
 tk.stopLap();
 tk.stopLap();
 tk.startLap("C");
 MiscUtil.snooze(10);
 tk.stopLap();
 tk.stop();
 System.out.println(tk.toString());

 Name:root Tot:46ms Avg:46.55ms Hits:1
 -- Name:A Share:0% Tot:0ms Avg:0.01ms Hits:1
 -- Name:B Share:74% Tot:34ms Avg:34.69ms Hits:1
 ---- Name:B1 Share:38% Tot:13ms Avg:13.47ms Hits:1
 ---- Name:B2 Share:62% Tot:21ms Avg:21.18ms Hits:1
 -- Name:C Share:24% Tot:11ms Avg:11.67ms Hits:1
 *
 *
 * @author schinzel
 */
public class Timekeeper {
    private Lap mCurrentLap = new Lap("root", null).start();

    private static Timekeeper INSTANCE = new Timekeeper();


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
     * Creates a new instance. Note, there also exists a getSingleton method.
     *
     * @return A freshly created instance.
     */
    public static Timekeeper create() {
        return new Timekeeper();
    }


    /**
     * Starts tracking a new time measurement. This measurement
     * will be a sub-measurement to the currently running measurement.
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
     * 
     * @return Stops the whole timekeeper.
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
     * @return The results of the times measured from the root node
     * and down.
     */
    @Override
    public String toString() {
        return (mCurrentLap.getRoot().getResultTree());
    }


    public JSONObject toJson() {
        return (mCurrentLap.getRoot().getResultAsJSON());
    }

}

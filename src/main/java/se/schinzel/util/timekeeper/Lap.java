package se.schinzel.util.timekeeper;

import com.google.common.base.Joiner;
import java.util.LinkedHashMap;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONObject;
import se.schinzel.util.Thrower;

/**
 * The purpose of this class be a lap in a tree of laps.
 * The laps knows its parent and children.
 *
 * @author schinzel
 */
class Lap {
    /**
     * The name of this alp.
     */
    private final String mName;
    /**
     * The parent of this lap.
     */
    final Lap mParent;
    /**
     * The children of this lap.
     */
    private final Map<String, Lap> mChildren = new LinkedHashMap<>();
    /**
     * Measures the time.
     */
    private final StopWatch mStopWatch = StopWatch.create();


    Lap(String lapName, Lap parent) {
        Thrower.throwErrorIfEmpty("lapName", lapName);
        mName = lapName;
        mParent = parent;
    }


    /**
     * Start
     *
     * @param lapName
     * @return The child not that was started
     */
    Lap start(String lapName) {
        Lap subLap;
        if (mChildren.containsKey(lapName)) {
            subLap = mChildren.get(lapName);
            if (subLap.mStopWatch.isStarted()) {
                throw new RuntimeException("Cannot start '" + lapName + "' as there is already a lap with this name started.");
            }
        } else {
            subLap = new Lap(lapName, this);
            mChildren.put(lapName, subLap);
        }
        return subLap.start();
    }


    Lap start() {
        mStopWatch.start();
        return this;
    }


    /**
     *
     * @return The parent of this lap.
     */
    Lap stop() {
        //If this lap is stopwatch is mid-lap, i.e. currently measuring a lap
        if (!mStopWatch.isStarted()) {
            throw new RuntimeException("Cannot stop lap '" + mName + "' as it has not been started");
        }
        //Stop the stopwatch
        mStopWatch.stop();
        //Return the parent of this lap
        return mParent;
    }


    /**
     *
     * @return Returns the root lap of the tree.
     */
    Lap getRoot() {
        if (mParent == null) {
            return this;
        }
        return mParent.getRoot();
    }


    @Override
    public String toString() {
        return mName;
    }


    /**
     *
     * @return The result of this lap and all its sub-laps.
     */
    String getResultTree() {
        return this.getResultTree(0);
    }


    /**
     *
     * @param intendation How many level the tree is this lap..
     * @return The result of this lap and all its sub laps.
     */
    String getResultTree(int intendation) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < intendation; i++) {
            sb.append("--");
        }
        sb.append(" ")
                .append(this.getResultAsString())
                .append("\n");
        for (Lap lap : mChildren.values()) {
            sb.append(lap.getResultTree(intendation + 1));
        }
        return sb.toString();
    }


    /**
     *
     * @return The result of this lap.
     */
    String getResultAsString() {
        return Joiner.on(" ")
                .withKeyValueSeparator(":")
                .join(this.getResult());
    }


    /**
     *
     * @return Get the percent of this lap execution time makes up of the
     * parent's lap execution time.
     */
    double getPercentOfParent() {
        if (mParent != null) {
            return mStopWatch.getTotTimeInMs() / mParent.mStopWatch.getTotTimeInMs() * 100d;
        }
        return 0d;
    }


    /**
     *
     * @return Get the percent of this laps execution time makes up of the
     * root lap execution time.
     */
    double getPercentOfRoot() {
        if (mParent != null) {
            return mStopWatch.getTotTimeInMs() / this.getRoot().mStopWatch.getTotTimeInMs() * 100d;
        }
        return 0d;
    }


    /**
     *
     * @return The result of this lap.
     */
    Map<String, Object> getResult() {
        if (mStopWatch.isStarted()) {
            throw new RuntimeException("Cannot get results for '" + mName + "' as has not been stopped.");
        }
        Map<String, Object> stats = new LinkedHashMap<>();
        stats.put("Name", mName);
        if (mParent != null) {
            stats.put("Root", String.format("%.0f%%", this.getPercentOfRoot()));
            stats.put("Parent", String.format("%.0f%%", this.getPercentOfParent()));
        }
        stats.put("Tot", String.format("%.0fms", mStopWatch.getTotTimeInMs()));
        stats.put("Avg", String.format("%.2fms", mStopWatch.getAvgInMs()));
        stats.put("Hits", String.valueOf(mStopWatch.getLaps()));
        return stats;
    }


    /**
     *
     * @return The result of this lap and all its sub-laps.
     */
    JSONObject getResultAsJSON() {
        JSONObject json = new JSONObject(this.getResult());
        if (!mChildren.isEmpty()) {
            JSONArray ja = new JSONArray();
            for (Lap lap : mChildren.values()) {
                ja.put(lap.getResultAsJSON());
            }
            json.put("children", ja);
        }
        return json;
    }

}

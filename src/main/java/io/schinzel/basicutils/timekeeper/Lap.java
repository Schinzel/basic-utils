package io.schinzel.basicutils.timekeeper;

import io.schinzel.basicutils.Thrower;
import io.schinzel.basicutils.state.IStateNode;
import io.schinzel.basicutils.state.State;
import io.schinzel.basicutils.state.StateBuilder;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * The purpose of this class be a lap in a tree of laps. The laps knows it's
 * parent and children.
 *
 * @author schinzel
 */
class Lap implements IStateNode {
    /** The name of this lap.*/
    private final String mName;
    /** The parent of this lap. */
    final Lap mParent;
    /** The children of this lap. */
    private final Map<String, Lap> mChildren = new LinkedHashMap<>();
    /** Measures the time. */
    private final StopWatch mStopWatch = StopWatch.create();


    /**
     *
     * @param lapName The name of this lap.
     * @param parent The parent of this lap.
     */
    Lap(String lapName, Lap parent) {
        Thrower.throwIfEmpty("lapName", lapName);
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
            //Throw an error as the a stop is requested for a lap that is not started.
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
        //If this object has not parent, i.e. it is root.
        if (mParent == null) {
            //Return this object as it is root.
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
     * @return Get the percent of this laps execution time makes up of the root
     * lap execution time.
     */
    double getPercentOfRoot() {
        if (mParent != null) {
            return mStopWatch.getTotTimeInMs() / this.getRoot().mStopWatch.getTotTimeInMs() * 100d;
        }
        return 0d;
    }


    @Override
    public State getState() {
        Thrower.throwIfTrue(mStopWatch.isStarted(), "Cannot get results for '" + mName + "' as has not been stopped.");
        StateBuilder stateBuilder = State.getBuilder()
                .addProp().key("Name").val(mName).buildProp();
        if (mParent != null) {
            stateBuilder.addProp().key("Root").val(this.getPercentOfRoot()).decimals(0).unit("%").buildProp();
            stateBuilder.addProp().key("Parent").val(this.getPercentOfParent()).decimals(0).unit("%").buildProp();
        }
        stateBuilder
                .addProp().key("Tot").val(mStopWatch.getTotTimeInMs()).decimals(0).unit("ms").buildProp()
                .addProp().key("Avg").val(mStopWatch.getAvgInMs()).decimals(2).unit("ms").buildProp()
                .addProp().key("Hits").val(mStopWatch.getLaps()).buildProp();
        if (!mChildren.isEmpty()) {
            stateBuilder.addChildren("sublaps", mChildren.values());
        }
        return stateBuilder.build();

    }

}

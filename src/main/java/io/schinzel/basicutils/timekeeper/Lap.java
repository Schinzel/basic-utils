package io.schinzel.basicutils.timekeeper;

import io.schinzel.basicutils.Thrower;
import io.schinzel.basicutils.state.IStateNode;
import io.schinzel.basicutils.state.State;
import io.schinzel.basicutils.state.StateBuilder;
import lombok.Getter;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * The purpose of this class be a lap in a tree of laps. The laps knows it's
 * parent and children.
 *
 * @author schinzel
 */
@Accessors(prefix = "m")
@ToString
class Lap implements IStateNode {
    /** The name of this lap. */
    @Getter private final String mName;
    /** The parent of this lap. */
    final Lap mParent;
    /** The children of this lap. */
    private final Map<String, Lap> mChildren = new LinkedHashMap<>();
    /** Measures the time. */
    @Getter private final StopWatch mStopWatch = StopWatch.create();


    /**
     * @param lapName The name of this lap.
     * @param parent  The parent of this lap.
     */
    Lap(String lapName, Lap parent) {
        Thrower.throwIfVarEmpty("lapName", lapName);
        mName = lapName;
        mParent = parent;
    }


    /**
     * Start
     *
     * @param lapName
     * @return The child node that was started
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


    /**
     * Starts measuring a lap. Requires that this lap has not already been started.
     *
     * @return This for chaining.
     */
    Lap start() {
        mStopWatch.start();
        return this;
    }


    /**
     * Stops measurement of current lap.
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
     * @return Returns the root lap of the tree.
     */
    Lap getRoot() {
        //If current node has no parent, then is root and as such return this
        //else request root from parent.
        return (mParent == null) ? this : mParent.getRoot();
    }


    /**
     * @return Get the percent of this lap execution time makes up of the
     * parent's lap execution time.
     */
    double getPercentOfParent() {
        return (mParent == null) ? 0d
                : mStopWatch.getTotTimeInMs() / mParent.mStopWatch.getTotTimeInMs() * 100d;
    }


    /**
     * @return Get the percent of this laps execution time makes up of the root
     * lap execution time.
     */
    double getPercentOfRoot() {
        return (mParent == null) ? 0d
                : mStopWatch.getTotTimeInMs() / this.getRoot().mStopWatch.getTotTimeInMs() * 100d;
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

package io.schinzel.basicutils.timekeeper;

import io.schinzel.basicutils.thrower.Thrower;
import io.schinzel.basicutils.collections.valueswithkeys.IValueWithKey;
import io.schinzel.basicutils.collections.valueswithkeys.ValuesWithKeys;
import io.schinzel.basicutils.state.IStateNode;
import io.schinzel.basicutils.state.State;
import lombok.Getter;
import lombok.experimental.Accessors;

import java.util.LinkedHashMap;

/**
 * The purpose of this class be a lap in a tree of laps. The laps knows it's
 * parent and children.
 *
 * @author schinzel
 */
@Accessors(prefix = "m")
class Lap implements IStateNode, IValueWithKey {
    /** The name of this lap */
    @Getter private final String mKey;
    /** The parent of this lap */
    final Lap mParentLap;
    /** The children of this lap */
    final ValuesWithKeys<Lap> mChildLaps = new ValuesWithKeys<>("sublaps", new LinkedHashMap<>());
    /** Measures the time */
    @Getter private final StopWatch mStopWatch = StopWatch.create();


    /**
     * @param lapName The name of this lap
     * @param parent  The parent of this lap
     */
    Lap(String lapName, Lap parent) {
        Thrower.throwIfVarEmpty("lapName", lapName);
        mKey = lapName;
        mParentLap = parent;
    }


    /**
     * Start a new lap. This new lap will be a child lap of this lap.
     *
     * @param lapName The name of the lap to start
     * @return The lap that was started
     */
    Lap start(String lapName) {
        Lap subLap = mChildLaps.has(lapName)
                ? mChildLaps.get(lapName)
                : mChildLaps.addAndGet(new Lap(lapName, this));
        Thrower.throwIfTrue(subLap.mStopWatch.isStarted())
                .message("Cannot start '" + lapName + "' as there is already a lap with this name started.");
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
        Thrower.throwIfFalse(mStopWatch.isStarted())
                .message("Cannot stop lap '" + mKey + "' as it has not been started");
        mStopWatch.stop();
        return (mParentLap == null) ? this : mParentLap;
    }


    /**
     * @return Returns the root lap of the tree.
     */
    Lap getRoot() {
        //If current node has no parent, then is root and as such return this
        //else request root from parent.
        return (mParentLap == null) ? this : mParentLap.getRoot();
    }


    /**
     * @return Get the percent of this lap execution time makes up of the
     * parent's lap execution time.
     */
    double getPercentOfParent() {
        return (mParentLap == null) ? 0d
                : mStopWatch.getTotTimeInMs() / mParentLap.mStopWatch.getTotTimeInMs() * 100d;
    }


    /**
     * @return Get the percent of this laps execution time makes up of the root
     * lap execution time.
     */
    double getPercentOfRoot() {
        return (mParentLap == null) ? 0d
                : mStopWatch.getTotTimeInMs() / this.getRoot().mStopWatch.getTotTimeInMs() * 100d;
    }


    @Override
    public State getState() {
        Thrower.throwIfTrue(mStopWatch.isStarted())
                .message("Cannot get results for lap '" + mKey + "' as it has not been stopped.");
        return State.getBuilder()
                .addProp().key("Name").val("'" + mKey + "'").buildProp()
                .ifTrue(mParentLap != null)
                .addProp().key("Root").val(this.getPercentOfRoot()).decimals(0).unit("%").buildProp()
                .addProp().key("Parent").val(this.getPercentOfParent()).decimals(0).unit("%").buildProp()
                .endIf()
                .addProp().key("Tot").val(mStopWatch.getTotTimeInMs()).decimals(0).unit("ms").buildProp()
                .addProp().key("Avg").val(mStopWatch.getAvgInMs()).decimals(2).unit("ms").buildProp()
                .addProp().key("Hits").val(mStopWatch.getNumberOfLaps()).buildProp()
                .ifTrue(!mChildLaps.isEmpty())
                .addChildren(mChildLaps)
                .endIf()
                .build();
    }

}

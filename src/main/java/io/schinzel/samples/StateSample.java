package io.schinzel.samples;

import io.schinzel.basicutils.RandomUtil;
import io.schinzel.basicutils.state.IStateNode;
import io.schinzel.basicutils.state.State;
import io.schinzel.basicutils.state.StateBuilder;

import java.util.ArrayList;
import java.util.List;

/**
 * Sample class to show how State can be used.
 * <p>
 * Created by schinzel on 2017-04-02.
 */
public class StateSample implements IStateNode {
    private final String mName;
    private final int mCount = RandomUtil.getRandomNumber(0, 100);
    private final double mVal = RandomUtil.create().getDouble(0d, 100d);
    private final List<StateSample> mChildren = new ArrayList<>();


    public static void main(String[] args) {
        StateSample stateSample = new StateSample("Root", 2);
        System.out.println(stateSample.getState().getString());

    }


    StateSample(String name, int numOfChildren) {
        mName = name;
        for (int i = 0; i < numOfChildren; i++) {
            mChildren.add(new StateSample(RandomUtil.getRandomString(4), 0));
        }
    }


    @Override
    public State getState() {
        return State.getBuilder()
                .addProp().key("Name").val(mName).buildProp()
                .addProp().key("SomeCount").val(mCount).buildProp()
                .addProp().key("SomeVal").val(mVal).decimals(2).buildProp()
                //Add a set of children that will be rendered as a sub tree
                .addChildren("SubThingies", mChildren)
                .build();
    }
}


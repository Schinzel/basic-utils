package io.schinzel.basicutils.state.REMOVME;

import io.schinzel.basicutils.state.IStateReturner;
import io.schinzel.basicutils.state.State;
import java.util.Arrays;
import java.util.List;

/**
 * The purpose of this class ...
 *
 * @author schinzel
 */
class TheClass implements IStateReturner {

    String mName = "AnyName";
    double mValue = 123.4567d;

    Pipe mLeft = new Pipe("copper", 123,
            new Nut("WingNut", 3.45d),
            new Nut("WeldNut", 45.45d));
    Pipe mRight = new Pipe("lead", 500,
            new Nut("WingNut", 3.77777d),
            new Nut("WeldNut", 4.5555d));
    List<Exit> mExists = Arrays.asList(
            new Exit("North", "Sliding"),
            new Exit("South", "Revolving"),
            new Exit("East", "Normal")
    );
    List<Exit> mHiddenExists = Arrays.asList(
            new Exit("SouthWest", "TrapDoor"),
            new Exit("NorthWest", "TrapDoor")
    );


    TheClass() {
    }


    static TheClass create() {
        return new TheClass();
    }


    @Override
    public State getState() {
        return State.getBuilder()
                .add("Name", mName)
                .add("Val", mValue, 2)
                .addChild("Left", mLeft)
                .addChild("Right", mRight)
                .addChildren("Doors", mExists.iterator())
                .addChildren("HiddenExists", mHiddenExists.iterator())
                .build();
    }

}

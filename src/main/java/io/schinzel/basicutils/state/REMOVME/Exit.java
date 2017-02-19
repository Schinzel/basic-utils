package io.schinzel.basicutils.state.REMOVME;

import io.schinzel.basicutils.state.IStateReturner;
import io.schinzel.basicutils.state.State;

/**
 * The purpose of this class ...
 *
 * @author schinzel
 */
class Exit implements IStateReturner {

    String mDirection;
    String mType;


    Exit(String dir, String type) {
        mDirection = dir;
        mType = type;
    }


    @Override
    public State getState() {
        return State.getBuilder()
                .add("Dir", mDirection)
                .add("Type", mType)
                .build();
    }

}

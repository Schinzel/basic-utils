package io.schinzel.basicutils.state.REMOVME;

import io.schinzel.basicutils.state.IStateReturner;
import io.schinzel.basicutils.state.State;

/**
 * The purpose of this class ...
 *
 * @author schinzel
 */
class Nut implements IStateReturner {

    String mType;
    double mDimension;


    Nut(String type, double dim) {
        mType = type;
        mDimension = dim;
    }


    @Override
    public State getState() {
        return State.getBuilder()
                .add("Type", mType)
                .add("Dim", mDimension, 2)
                .build();
    }

}

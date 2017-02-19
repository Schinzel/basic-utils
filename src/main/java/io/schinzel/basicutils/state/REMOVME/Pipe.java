package io.schinzel.basicutils.state.REMOVME;

import io.schinzel.basicutils.state.IStateReturner;
import io.schinzel.basicutils.state.State;

/**
 * The purpose of this class ...
 *
 * @author schinzel
 */
class Pipe implements IStateReturner {

    String mMaterial;
    int mWeight;
    Nut mStartNut;
    Nut mEndNut;


    Pipe(String pipe, int weight, Nut startNut, Nut endNut) {
        mMaterial = pipe;
        mWeight = weight;
        mStartNut = startNut;
        mEndNut = endNut;
    }


    @Override
    public State getState() {
        return State.getBuilder()
                .add("Material", mMaterial)
                .add("Weight", mWeight)
                .addChild("StartNut", mStartNut)
                .addChild("EndNut", mEndNut)
                .build();
    }

}

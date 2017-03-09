package io.schinzel.basicutils.state;

import java.util.ArrayList;
import java.util.List;

/**
 * The purpose of this class ...
 *
 * @author schinzel
 */
class MyClass implements IStateNode {

    final String mName;
    final int mCost;
    List<IStateNode> mChildren = new ArrayList<>();
    MyClass mLeft;
    MyClass mRight;

    MyClass(String name, int cost) {
        mName = name;
        mCost = cost;
    }


    @Override
    public State getState() {
        StateBuilder builder = State.getBuilder()
                .add("Name", mName)
                .add("Cost", mCost)
                .add("mykids", mChildren);
        if (mLeft != null){
            builder.add("Left", mLeft);
        }
        if (mRight != null){
            builder.add("Right", mRight);
        }
        return builder.build();
                
    }

    @Override
    public String toString(){
        return this.getState().toString();
    }

}

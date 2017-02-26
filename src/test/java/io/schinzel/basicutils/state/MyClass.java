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

    MyClass(String name, int cost) {
        mName = name;
        mCost = cost;
    }


    @Override
    public State getState() {
        return State.getBuilder()
                .add("Name", mName)
                .add("Cost", mCost)
                .addChildren("mykids", mChildren.iterator())
                .build();
                
    }

    @Override
    public String toString(){
        return this.getState().toString();
    }

}

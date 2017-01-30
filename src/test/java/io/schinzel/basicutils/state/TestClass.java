package io.schinzel.basicutils.state;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * The purpose of this class ...
 *
 * @author schinzel
 */
class TestClass implements IStateNode {

    final String mName;
    List<IStateNode> mChildren = new ArrayList<>();

    TestClass(String name) {
        mName = name;
    }


    @Override
    public State getState() {
        return State.getBuilder().add("Name", mName).build();
    }


    @Override
    public Iterator<IStateNode> getStateChildren() {
        return mChildren.iterator();
    }


}

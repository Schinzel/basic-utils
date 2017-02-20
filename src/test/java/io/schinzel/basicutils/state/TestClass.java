package io.schinzel.basicutils.state;

import io.schinzel.basicutils.RandomUtil;
import java.util.ArrayList;
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
        return State.getBuilder()
                .add("Name", mName)
                .add("Cost", RandomUtil.getRandomNumber(10, 99))
                .addChildren("mykids", mChildren.iterator())
                .build();
                
    }

    @Override
    public String toString(){
        return this.getState().toString();
    }

}

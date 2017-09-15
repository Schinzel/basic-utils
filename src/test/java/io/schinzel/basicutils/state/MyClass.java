package io.schinzel.basicutils.state;

import io.schinzel.basicutils.collections.valueswithkeys.IValueWithKey;
import lombok.Getter;
import lombok.experimental.Accessors;

import java.util.ArrayList;
import java.util.List;

@Accessors(prefix = "m")
class MyClass implements IStateNode, IValueWithKey {
    @Getter final String mKey;
    private final int mCost;
    List<IStateNode> mChildren = new ArrayList<>();
    MyClass mLeft;
    MyClass mRight;


    MyClass(String name, int cost) {
        mKey = name;
        mCost = cost;
    }


    @Override
    public State getState() {
        StateBuilder builder = State.getBuilder()
                .addProp().key("Name").val(mKey).buildProp()
                .addProp().key("Cost").val(mCost).buildProp()
                .addChildren("mykids", mChildren);
        if (mLeft != null) {
            builder.addChild("Left", mLeft);
        }
        if (mRight != null) {
            builder.addChild("Right", mRight);
        }
        return builder.build();

    }


    @Override
    public String toString() {
        return this.getState().toString();
    }

}

package io.schinzel.basicutils.test;

import com.google.common.collect.ImmutableList;
import io.schinzel.basicutils.test.sub.State;
import io.schinzel.basicutils.test.sub.StateBuilder;

import java.util.List;

public class REMOVE_ME {

    public static void main(String[] args) {
        List<String> listan = new ImmutableList.Builder<String>()
                .add("item1")
                .add("item2")
                .build();

        State state = StateBuilder.create()
                .addProp().key("key1").val("theVal").unit("buppisar").buildProp()
                .addProp().key("nyckel2").val(0.5).decimals(2).unit("ms").buildProp()
                .addProp().key("jave3").val(3).unit("meters").buildProp()
                .addProp().key("kyu").val(listan).buildProp()
                .buildState();
        System.out.println(state.getPropertiesAsString());
    }
}

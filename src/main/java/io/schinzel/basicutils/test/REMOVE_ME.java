package io.schinzel.basicutils.test;

import io.schinzel.basicutils.test.sub.State;
import io.schinzel.basicutils.test.sub.StateBuilder;

/**
 * Created by schinzel on 2017-03-26.
 */
public class REMOVE_ME {

    public static void main(String[] args) {

        State state = StateBuilder.create()
                .addProp().key("key1").val("theVal").unit("buppisar").buildProp()
                .addProp().key("nyckel2").val(0.5).decimals(2).unit("ms").buildProp()
                .addProp().key("jave3").val(3).unit("meters").buildProp()
                .buildState();
        System.out.println(state.getPropertiesAsString());
    }
}

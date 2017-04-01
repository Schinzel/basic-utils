package io.schinzel.basicutils.test;

import io.schinzel.basicutils.test.sub.State;
import io.schinzel.basicutils.test.sub.StateBuilder;

/**
 * Created by schinzel on 2017-03-26.
 */
public class REMOVE_ME {

    public static void main(String[] args) {

        State state = StateBuilder.create()
                .addProp().key("").val("").unit("").buildProp()
                .addProp().key("").val(0.5).decimals(2).unit("").buildProp()
                .addProp().key("").val(3).unit("").buildProp()
                .buildState();
    }
}

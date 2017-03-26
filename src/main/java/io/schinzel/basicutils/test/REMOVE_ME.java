package io.schinzel.basicutils.test;

import io.schinzel.basicutils.test.sub.State;
import io.schinzel.basicutils.test.sub.StateBuilder;
import lombok.val;

/**
 * Created by schinzel on 2017-03-26.
 */
public class REMOVE_ME {

    public static void main(String[] args) {
        val apa = new TheThing();
        apa
                .addProp().key("speed").val(170).unit("m").buildProp()
                .addProp().key("height").val(2d, 3).unit("%").buildProp();
        System.out.println("apa");
        /**
         * kan man göra if-statements??
         *
         * En if true add???
         *  .iff().isTrue(true)
         .addProp().key("otherkey").val("myval").buildProp()
         .addProp().key("otherkey").val("myval").buildProp()
         .endIf()
         .

         */

        State state = StateBuilder.create()
                .addProp().key("mykey").val(33).unit("m").buildProp()
                .addProp().key("otherkey").val("my val").buildProp()
                .addProp().key("thirdkey").val(13d).decimals(3).buildProp()
                .buildState();
    }
}

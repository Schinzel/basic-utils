package io.schinzel.basicutils;

import io.schinzel.basicutils.str.Str;

/**
 * Created by schinzel on 2017-02-28.
 */
interface IThrower {

    default void throwIfTrue(boolean boo, String message, String... keyValues) {
        Str str = Str.create()
                //Error message
                .a(message).asp()
                //Class name
                .a("In class: ").aq(this.getClass().getSimpleName()).a(". ");
        if (!Checker.isEmpty(keyValues)) {
            Str args = Str.create();
            for (int i = 0; i < keyValues.length; i += 2) {
                if (!args.isEmpty()){
                    args.asp();
                }
                args.a(keyValues[i]).a(":").aq(keyValues[i+1]);
            }
            str.a("Arguments: {").a(args).a("}");
        }
        throw new RuntimeException(str.getString());
    }
}

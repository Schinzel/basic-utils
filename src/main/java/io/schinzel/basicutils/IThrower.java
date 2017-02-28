package io.schinzel.basicutils;

import io.schinzel.basicutils.str.Str;

/**
 * Created by schinzel on 2017-02-28.
 */
interface IThrower {

    default void throwIfTrue(boolean expression, String message, String... keyValues) {
        if (expression) {
            String methodName = Thread.currentThread().getStackTrace()[2].getMethodName();
            Str str = Str.create().a(message).asp()
                    .a("In class: ").aq(this.getClass().getSimpleName()).a(". ")
                    .a("Method: ").aq(methodName).a(". ")
                    .a(Helper.getArgs(keyValues));
            throw new RuntimeException(str.getString());
        }
    }


    /**
     * Inner class so that can have private methods
     */
    class Helper {


        private static Str getArgs(String... keyValues) {
            Str str = Str.create();
            if (!Checker.isEmpty(keyValues)) {
                Str args = Str.create();
                for (int i = 0; i < keyValues.length; i += 2) {
                    if (!args.isEmpty()) {
                        args.asp();
                    }
                    args.a(keyValues[i]).a(":").aq(keyValues[i + 1]);
                }
                str.a("Arguments: {").a(args).a("}");
            }
            return str;
        }
    }
}

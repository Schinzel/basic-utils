package io.schinzel.samples;

import io.schinzel.basicutils.Thrower;

class ThrowerSample {

    public static void main(String[] args) {
        Thrower.throwIfTrue(false).message("This is an exception message");
        //
        String myVar = "Non empty sgtring";
        Thrower.throwIfVarEmpty(myVar, "myVar");
        //
        int myIntVar = 5;
        Thrower.throwIfVarOutsideRange(myIntVar, "myIntVar", 0, 5);
    }




}

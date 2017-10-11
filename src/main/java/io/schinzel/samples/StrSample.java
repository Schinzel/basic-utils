package io.schinzel.samples;

import io.schinzel.basicutils.str.Str;

/**
 * The purpose of this class is to provide sample usage of the Str class.
 * <p>
 * Created by Schinzel on 2017-02-27.
 */

class StrSample {

    public static void main(String[] args) {
        double weight = 1234.56789d;
        int cost = 12000000;
        Str.create()
                .atab("Boat weighs: ").af(weight, 2).anl(" kg")
                .atab("Boat costs: ").af(cost).anl(" Euros")
                .atab("Boat name:  ").aq("Boaty McBoatface").anl()
                .pln();
        Str.create()
                .af(System.currentTimeMillis())
                //Write to file
                .writeToFile("MyTextFile.txt")
                //Print to system out with prefix
                .plnWithPrefix("Time now in millis: ");
    }
}

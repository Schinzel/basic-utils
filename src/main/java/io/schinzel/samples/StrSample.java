package io.schinzel.samples;

import io.schinzel.basicutils.str.Str;

/**
 * The purpose of this class provide sample usage of the Str class.
 * <p>
 * Created by Schinzel on 2017-02-27.
 */

class StrSample {

    public static void main(String[] args) {
        double weight = 1234.56789d;
        int cost = 12000000;
        Str.create()
                .a("Boat weighs: ").atab().a(weight, 2).anl(" kg")
                .a("Boat costs: ").atab().a(cost).anl(" Euros")
                .a("Boat name:  ").atab().aq("Boaty McBoatface").anl()
                .pln();
        //Print to system out with prefix
        Str.create()
                .a(System.currentTimeMillis())
                .plnWithPrefix("Time now in millis: ");
        //Write to file
        Str.create()
                .a(System.currentTimeMillis())
                .writeToFile("MyTextFile.txt");
    }
}

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
                .a("Boat weighs: ").atab().a(weight, 2).a(" kg").anl()
                .a("Boat costs: ").atab().a(cost).a(" Euros").anl()
                .pln();
    }
}

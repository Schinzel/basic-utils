package io.schinzel.samples;

import io.schinzel.basicutils.ratio.Ratio;
import io.schinzel.basicutils.str.Str;

/**
 * Sample class to show how Ratio can be used.
 * Created by schinzel on 2017-03-19.
 */
public class RatioSample {
    public static void main(String[] args) {
        Ratio.create(1, 3)
                .times(1, 3)
                .plus(1, 3)
                .dividedBy(2)
                .minus(1,6)
                .getStr().pln();

        double d1 = 1d/3d;
        double d2 = 2d/3d;
        Str.create()
                .a("Height").atab().a(d1, 2).anl()
                .a("Width").atab().a(d2, 2).anl()
                .pln();

    }
}

package io.schinzel.samples;

import io.schinzel.basicutils.ratio.Ratio;

/**
 * Sample class to show how Ratio can be used.
 * <p>
 * Created by schinzel on 2017-03-19.
 */
class RatioSample {
    public static void main(String[] args) {
        Ratio ratio = Ratio.create(1, 3)
                .times(1, 3)
                .plus(1, 3)
                .dividedBy(2)
                .minus(1, 6);
        System.out.println("Str: " + ratio.toString());
        System.out.println("Big dec: " + ratio.getBigDecimal());
        System.out.println("Double: " + ratio.getDouble());
    }
}

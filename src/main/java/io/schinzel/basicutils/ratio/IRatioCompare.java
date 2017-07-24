package io.schinzel.basicutils.ratio;

import io.schinzel.basicutils.Thrower;

import java.math.BigInteger;

/**
 * Handles comparing ratios.
 * <p>
 * Created by schinzel on 2017-03-19.
 */
interface IRatioCompare<T extends IRatio<T>> extends IRatio<T>, Comparable<T> {


    @Override
    default int compareTo(T other) {
        Thrower.throwIfVarNull(other, "other");
        BigInteger a1 = this.getNumerator();
        BigInteger b1 = this.getDenominator();
        BigInteger a2 = other.getNumerator();
        BigInteger b2 = other.getDenominator();
        //The numerator in a subtraction (see here https://en.wikipedia.org/wiki/Rational_number#Subtraction)
        //The denominator is not necessary to calculate which is larger
        BigInteger subtractResult = (a1.multiply(b2)).subtract(a2.multiply(b1));
        return subtractResult.compareTo(BigInteger.ZERO);
    }


    /**
     * @param ratio A ratio
     * @return True if this is greater than argument. Else false.
     */
    default boolean greaterThan(T ratio) {
        return (this.compareTo(ratio) > 0);
    }


    /**
     * @param ratio A ratio
     * @return True if this is less than argument. Else false.
     */
    default boolean lessThan(T ratio) {
        return (this.compareTo(ratio) < 0);
    }


    /**
     * @param other The other ratio to compare this to.
     * @return True if the value held by the argument ratio is the same as this.
     */
    default boolean equals(T other) {
        return (other != null) && (this.compareTo(other) == 0);
    }
}


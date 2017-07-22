package io.schinzel.basicutils.ratio;

import java.math.BigInteger;

/**
 * Handles multiplication of ratios.
 * <p>
 * Created by schinzel on 2017-03-19.
 */
interface IRatioTimes<T extends IRatio<T>> extends IRatio<T> {
    /**
     * @param val The value to multiply this by.
     * @return This for chaining.
     */
    default T times(int val) {
        return this.times(val, 1);
    }


    /**
     * @param numerator   A numerator
     * @param denominator A denominator
     * @return This for chaining
     */
    default T times(int numerator, int denominator) {
        BigInteger a2 = BigInteger.valueOf(numerator);
        BigInteger b2 = BigInteger.valueOf(denominator);
        return this.times(a2, b2);
    }


    /**
     * @param ratio A ratio
     * @return This for chaining
     */
    default T times(T ratio) {
        return this.times(ratio.getNumerator(), ratio.getDenominator());
    }


    /**
     * New num: mNum * num
     * New den: mDen * den
     *
     * @param numerator   A numerator
     * @param denominator A denominator
     * @return This for chaining
     */
    @SuppressWarnings("Duplicates")
    default T times(BigInteger numerator, BigInteger denominator) {
        //Formula here https://en.wikipedia.org/wiki/Rational_number#Multiplication
        BigInteger newNum = this.getNumerator().multiply(numerator);
        BigInteger newDen = this.getDenominator().multiply(denominator);
        return this.newInstance(newNum, newDen);
    }

}

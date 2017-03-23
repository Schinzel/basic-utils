package io.schinzel.basicutils.ratio;

import java.math.BigInteger;

/**
 * Handles subtraction of ratios.
 *
 * Created by schinzel on 2017-03-19.
 */
@SuppressWarnings("SameParameterValue")
interface IRatioMinus<T extends IRatio<T>> extends IRatio<T> {

    /**
     * @param val The value to subtract from this.
     * @return This for chaining
     */
    default T minus(int val) {
        return this.minus(val, 1);
    }


    /**
     * @param numerator   A numerator
     * @param denominator A denominator
     * @return This for chaining
     */
    default T minus(int numerator, int denominator) {
        BigInteger a2 = BigInteger.valueOf(numerator);
        BigInteger b2 = BigInteger.valueOf(denominator);
        return this.minus(a2, b2);
    }


    /**
     * @param ratio A ratio
     * @return This for chaining
     */
    default T minus(T ratio) {
        return this.minus(ratio.getNumerator(), ratio.getDenominator());
    }


    /**
     * New num = (mNum * den) - (mDen * num)
     * New den = mDen * den
     *
     * @param numerator   A numerator
     * @param denominator A denominator
     * @return This for chaining
     */
    default T minus(BigInteger numerator, BigInteger denominator) {
        //Formula here https://en.wikipedia.org/wiki/Rational_number#Subtraction
        BigInteger newNum = (this.getNumerator().multiply(denominator))
                .subtract(numerator.multiply(this.getDenominator()));
        BigInteger newDen = this.getDenominator().multiply(denominator);
        this.setRatio(newNum, newDen);
        return this.getThis();
    }

}

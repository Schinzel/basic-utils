package io.schinzel.basicutils.ratio;

import java.math.BigInteger;

/**
 * Handles addition of ratios.
 * <p>
 * Created by schinzel on 2017-03-19.
 */
interface IRatioPlus<T extends IRatio<T>> extends IRatio<T> {
    default T plus(int val) {
        return this.plus(val, 1);
    }


    /**
     * @param numerator   A numerator
     * @param denominator A denominator
     * @return This for chaining
     */
    default T plus(int numerator, int denominator) {
        BigInteger a2 = BigInteger.valueOf(numerator);
        BigInteger b2 = BigInteger.valueOf(denominator);
        return this.plus(a2, b2);
    }


    /**
     * @param ratio A ratio
     * @return This for chaining
     */
    default T plus(T ratio) {
        return this.plus(ratio.getNumerator(), ratio.getDenominator());
    }


    /**
     * New numerator = (mNum * den) + (mDen * num)
     * New denominator = mDen * den
     *
     * @param numerator   A numerator
     * @param denominator A denominator
     * @return This for chaining
     */
    default T plus(BigInteger numerator, BigInteger denominator) {
        //Formula here https://en.wikipedia.org/wiki/Rational_number#Addition
        BigInteger newNum = (this.getNumerator().multiply(denominator))
                .add(numerator.multiply(this.getDenominator()));
        BigInteger newDen = this.getDenominator().multiply(denominator);
        return this.newInstance(newNum, newDen);
    }
}

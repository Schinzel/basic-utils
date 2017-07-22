package io.schinzel.basicutils.ratio;

import java.math.BigInteger;

/**
 * Handles division of ratios.
 * <p>
 * Created by schinzel on 2017-03-19.
 */
interface IRatioDividedBy<T extends IRatio<T>> extends IRatio<T> {

    /**
     * @param val The value to divide this by.
     * @return This for chaining
     */
    default T dividedBy(int val) {
        return this.dividedBy(val, 1);
    }


    /**
     * @param numerator   A numerator
     * @param denominator A denominator
     * @return This for chaining
     */
    default T dividedBy(int numerator, int denominator) {
        BigInteger num = BigInteger.valueOf(numerator);
        BigInteger den = BigInteger.valueOf(denominator);
        return this.dividedBy(num, den);
    }


    /**
     * @param ratio A ratio
     * @return This for chaining
     */
    default T dividedBy(T ratio) {
        BigInteger num = ratio.getNumerator();
        BigInteger den = ratio.getDenominator();
        return this.dividedBy(num, den);
    }


    /**
     * New num: mNum * den
     * New den: mDen * num
     *
     * @param numerator   A numerator
     * @param denominator A denominator
     * @return This for chaining
     */
    @SuppressWarnings("Duplicates")
    default T dividedBy(BigInteger numerator, BigInteger denominator) {
        if (numerator.equals(BigInteger.ZERO) || denominator.equals(BigInteger.ZERO)) {
            throw new RuntimeException("Cannot do division by zero.");
        }
        //Formula here https://en.wikipedia.org/wiki/Rational_number#Division
        BigInteger newNum = this.getNumerator().multiply(denominator);
        BigInteger newDen = this.getDenominator().multiply(numerator);
        return this.newInstance2(newNum, newDen);
    }

}

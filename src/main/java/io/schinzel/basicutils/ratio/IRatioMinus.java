package io.schinzel.basicutils.ratio;

import java.math.BigInteger;

/**
 * Handles subtraction of ratios.
 * <p>
 * Created by schinzel on 2017-03-19.
 */
@SuppressWarnings("SameParameterValue")
interface IRatioMinus<T extends IRatio<T>> extends IRatio<T> {

    /**
     * @param val The value to subtract
     * @return The new value resulting from the operation
     */
    default T minus(int val) {
        return this.minus(BigInteger.valueOf(val));
    }


    /**
     * @param val The value to subtract
     * @return The new value resulting from the operation
     */
    default T minus(Long val) {
        return this.minus(BigInteger.valueOf(val));
    }


    /**
     * @param val The value to subtract
     * @return The new value resulting from the operation
     */
    default T minus(BigInteger val) {
        return this.minus(this.newInstance(val, BigInteger.ONE));
    }


    /**
     * @param val The value to subtract
     * @return The new value resulting from the operation
     */
    default T minus(T val) {
        //Formula here https://en.wikipedia.org/wiki/Rational_number#Subtraction
        BigInteger newNum = (this.getNumerator().multiply(val.getDenominator()))
                .subtract(val.getNumerator().multiply(this.getDenominator()));
        BigInteger newDen = this.getDenominator().multiply(val.getDenominator());
        return this.newInstance(newNum, newDen);
    }


}

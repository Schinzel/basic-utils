package io.schinzel.basicutils.ratio;

import java.math.BigInteger;

/**
 * Handles multiplication of ratios.
 * <p>
 * Created by schinzel on 2017-03-19.
 */
interface IRatioTimes<T extends IRatio<T>> extends IRatio<T> {
    /**
     * @param val The val to multiply this value by
     * @return The new value resulting from the operation
     */
    default T times(int val) {
        return this.times(BigInteger.valueOf(val));
    }


    /**
     * @param val The val to multiply this value by
     * @return The new value resulting from the operation
     */
    default T times(long val) {
        return this.times(BigInteger.valueOf(val));
    }


    /**
     * @param val The val to multiply this value by
     * @return The new value resulting from the operation
     */
    default T times(BigInteger val) {
        return this.times(this.newInstance(val, BigInteger.ONE));
    }


    /**
     * @param val The val to multiply this value by
     * @return The new value resulting from the operation
     */
    default T times(T val) {
        //Formula here https://en.wikipedia.org/wiki/Rational_number#Multiplication
        BigInteger newNum = this.getNumerator().multiply(val.getNumerator());
        BigInteger newDen = this.getDenominator().multiply(val.getDenominator());
        return this.newInstance(newNum, newDen);
    }


}

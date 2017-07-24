package io.schinzel.basicutils.ratio;

import java.math.BigInteger;

/**
 * Handles addition of ratios.
 * <p>
 * Created by schinzel on 2017-03-19.
 */
interface IRatioPlus<T extends IRatio<T>> extends IRatio<T> {
    /**
     * @param val The value to add to this value.
     * @return The new value resulting from the operation
     */
    default T plus(int val) {
        return this.plus(BigInteger.valueOf(val));
    }


    /**
     * @param val The value to add to this value.
     * @return The new value resulting from the operation
     */
    default T plus(Long val) {
        return this.plus(BigInteger.valueOf(val));
    }


    /**
     * @param val The value to add to this value.
     * @return The new value resulting from the operation
     */
    default T plus(BigInteger val) {
        return this.plus(this.newInstance(val, BigInteger.ONE));
    }


    /**
     * @param val The value to add to this value.
     * @return The new value resulting from the operation
     */
    default T plus(T val) {
        //Formula here https://en.wikipedia.org/wiki/Rational_number#Addition
        BigInteger newNum = (this.getNumerator().multiply(val.getDenominator()))
                .add(val.getNumerator().multiply(this.getDenominator()));
        BigInteger newDen = this.getDenominator().multiply(val.getDenominator());
        return this.newInstance(newNum, newDen);
    }


}

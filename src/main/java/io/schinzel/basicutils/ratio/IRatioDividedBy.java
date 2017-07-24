package io.schinzel.basicutils.ratio;

import io.schinzel.basicutils.Thrower;

import java.math.BigInteger;

/**
 * Handles division of ratios.
 * <p>
 * Created by schinzel on 2017-03-19.
 */
interface IRatioDividedBy<T extends IRatio<T>> extends IRatio<T> {


    /**
     * @param divisor The value to divide by
     * @return The new value resulting from the operation
     */
    default T dividedBy(int divisor) {
        return this.dividedBy(BigInteger.valueOf(divisor));
    }


    /**
     * @param divisor The value to divide by
     * @return The new value resulting from the operation
     */
    default T dividedBy(long divisor) {
        return this.dividedBy(BigInteger.valueOf(divisor));
    }


    /**
     * @param divisor The value to divide by
     * @return The new value resulting from the operation
     */
    default T dividedBy(BigInteger divisor) {
        return this.dividedBy(this.newInstance(divisor, BigInteger.ONE));
    }


    /**
     * @param divisor The value to divide by
     * @return The new value resulting from the operation
     */
    default T dividedBy(T divisor) {
        Thrower.throwIfTrue(divisor.getNumerator().equals(BigInteger.ZERO))
                .message("Cannot do division by zero");
        //Formula here https://en.wikipedia.org/wiki/Rational_number#Division
        BigInteger newNum = this.getNumerator().multiply(divisor.getDenominator());
        BigInteger newDen = this.getDenominator().multiply(divisor.getNumerator());
        return this.newInstance(newNum, newDen);
    }


}

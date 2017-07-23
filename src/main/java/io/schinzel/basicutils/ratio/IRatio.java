package io.schinzel.basicutils.ratio;

import io.schinzel.basicutils.str.Str;

import java.math.BigInteger;

/**
 * This is the base interface.
 * <p>
 * Actual functionality are implemented in a set of interfaces that extends this
 * interface. The implementing class implements one or several of the interfaces that extended this.
 * <p>
 * Created by schinzel on 2017-03-19.
 */
interface IRatio<T extends IRatio<T>> {


    /**
     * This should really be package private or protected. But as this is not an option, it has to
     * be public.
     *
     * @param numerator   The numerator
     * @param denominator The denominator
     * @return A new ratio instance.
     */
    T newInstance(BigInteger numerator, BigInteger denominator);


    /**
     * @return The numerator of this ratio.
     */
    BigInteger getNumerator();


    /**
     * @return The denominator of this ratio.
     */
    BigInteger getDenominator();


    /**
     * Sample output: "1/3".
     * Could be argued that this method should be in IRatioCast, but is here for
     * easier testing.
     *
     * @return The ratio as a Str.
     */
    default Str getStr() {
        String s = this.getNumerator().toString() + "/" + this.getDenominator().toString();
        return Str.create().a(s);
    }
}

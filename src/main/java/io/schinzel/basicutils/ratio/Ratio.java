package io.schinzel.basicutils.ratio;

import com.google.common.base.Objects;
import lombok.experimental.Accessors;

import java.math.BigInteger;

/**
 * The purpose of this class enable the basic arithmetic operations on rational numbers and not lose
 * precision.
 * Precision might be lost as decimal numbers cannot be represented in binary code.
 * <p>
 * Formulas from here
 * https://en.wikipedia.org/wiki/Rational_number#Arithmetic
 * <p>
 *
 * @author schinzel
 */
@SuppressWarnings({"SameParameterValue", "WeakerAccess"})
@Accessors(prefix = "m")
public class Ratio extends AbstractRatio<Ratio> implements IRatioCompare<Ratio>, IRatioDividedBy<Ratio>, IRatioMinus<Ratio>,
        IRatioCast<Ratio>, IRatioPlus<Ratio>, IRatioTimes<Ratio> {


    public Ratio(BigInteger num, BigInteger den) {
        super(num, den);
    }


    /**
     * @param numerator   The numerator
     * @param denominator The denominator
     * @return A new instance
     */
    public static Ratio create(int numerator, int denominator) {
        return new Ratio(BigInteger.valueOf(numerator), BigInteger.valueOf(denominator));
    }


    /**
     * @param numerator   The numerator
     * @param denominator The denominator
     * @return A new instance
     */
    public static Ratio create(long numerator, long denominator) {
        return new Ratio(BigInteger.valueOf(numerator), BigInteger.valueOf(denominator));
    }


    /**
     * @param numerator   The numerator
     * @param denominator The denominator
     * @return A new instance
     */
    public static Ratio create(BigInteger numerator, BigInteger denominator) {
        return new Ratio(numerator, denominator);
    }


    @Override
    public Ratio newInstance(BigInteger numerator, BigInteger denominator) {
        return new Ratio(numerator, denominator);
    }


    @Override
    public boolean equals(Object o) {
        return (o != null) && (o instanceof Ratio) && this.equals((Ratio) o);
    }


    @Override
    public int hashCode() {
        return Objects.hashCode(this.getNumerator(), this.getDenominator());
    }


    @Override
    public String toString() {
        return this.getStr().toString();
    }
}

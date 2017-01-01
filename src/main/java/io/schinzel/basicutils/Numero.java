package io.schinzel.basicutils;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;
import java.text.NumberFormat;
import java.util.Locale;

/**
 * The purpose of this class to easily and reliably handle multiplying and
 * adding decimal numbers.
 * Instance of a class represents an immutable number.
 * The double is stored internally as a BigInteger.
 *
 * @author schinzel
 */
public class Numero implements Comparable<Numero> {
    /**
     * The base number that the constructor argument double is multiplies by.
     * For example, if the argument double is 0.123456, this is represented
     * and stored internally as 0.123456 * BASE = 123 456 000 [...] 000.
     */
    private static final BigInteger BI_BASE = new BigInteger("1000000000000000000000");
    /**
     * The base as a big decimal.
     */
    private static final BigDecimal BD_BASE = new BigDecimal(BI_BASE);
    public static final Numero ZERO = new Numero(BigInteger.ZERO.multiply(BI_BASE));
    public static final Numero ONE = new Numero(BigInteger.ONE.multiply(BI_BASE));
    public static final Numero ONE_HUNDRED = new Numero(BigInteger.valueOf(100l).multiply(BI_BASE));
    /**
     * Format for pretty print.
     */
    NumberFormat NUMBER_FORMAT = NumberFormat.getInstance(Locale.GERMAN);
    /**
     * The default number of decimal places uses when returning the value
     * as a double.
     */
    private static final int DEFAULT_DECIMAL_PLACES = 2;
    /**
     * The max number of decimal places supported when returning the value held
     * as a double.
     */
    private static final int MAX_DECIMAL_PLACES = 4;
    /**
     *
     */
    private final BigInteger mNumber;


    /**
     * Creates an instance that represented the argument double.
     *
     * @param val
     */
    public Numero(double val) {
        mNumber = BigDecimal.valueOf(val)
                .multiply(BD_BASE)
                .toBigIntegerExact();
    }


    private Numero(BigInteger bi) {
        mNumber = bi;
    }


    /**
     *
     * @param val
     * @return A new Numero that is (this+val).
     */
    public Numero plus(Numero val) {
        BigInteger bi = this.mNumber.add(val.mNumber);
        return new Numero(bi);
    }


    /**
     *
     * @param val
     * @return A new Numero that is (this*val).
     */
    public Numero multiply(Numero val) {
        BigInteger bi1 = this.mNumber.multiply(val.mNumber);
        BigInteger bi2 = bi1.divide(BI_BASE);
        Numero returnVal = new Numero(bi2);
        return returnVal;
    }


    /**
     * Method multiplies the number the instance represents with a ratio,
     * where the ration is stated as the argument numerator and denominator.
     *
     * @param numerator
     * @param denominator
     * @return A new numerator that is (this * numerator / denominator).
     */
    public Numero multiplyByRatio(Numero numerator, Numero denominator) {
        BigDecimal bdNumerator = new BigDecimal(this.mNumber
                .multiply(numerator.mNumber));
        BigDecimal decimalDenom = new BigDecimal(denominator.mNumber);
        BigInteger result = bdNumerator
                .divide(decimalDenom, 0, RoundingMode.HALF_EVEN)
                .toBigIntegerExact();
        return new Numero(result);
    }


    /**
     *
     * @return The number this class represents as a double with two decimals.
     */
    public double getDouble() {
        return this.getDouble(DEFAULT_DECIMAL_PLACES);
    }


    /**
     *
     * @param decimalPlaces
     * @return The number this
     */
    public double getDouble(int decimalPlaces) {
        Thrower.throwErrorIfOutsideRange(decimalPlaces, "decimalPlaces", 0, MAX_DECIMAL_PLACES);
        return new BigDecimal(mNumber)
                .divide(BD_BASE)
                .setScale(decimalPlaces, RoundingMode.HALF_EVEN)
                .doubleValue();
    }


    /**
     * Exists for easier debugging.
     *
     * @return The number held by this in a readable format.
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Raw value ").append(this.getPrettyNumber()).append("\n");
        sb.append("Decimal value ").append(this.getDouble(4));
        return sb.toString();
    }


    public String getPrettyNumber() {
        return NUMBER_FORMAT.format(mNumber);
    }


    @Override
    public int compareTo(Numero val) {
        return this.mNumber.compareTo(val.mNumber);
    }


    public boolean equals(Numero val) {
        return (this.compareTo(val) == 0);
    }


    public static void assertNumeros(Numero expected, Numero result) {
        if (!expected.equals(result)) {
            throw new AssertionError("Roughly expected " + expected.getDouble() + " was " + result.getDouble()
                    + "\nExactly expected " + expected.getPrettyNumber() + " was " + result.getPrettyNumber());
        }
    }

}

package io.schinzel.basicutils.ratio;

import io.schinzel.basicutils.str.Str;

import java.math.BigDecimal;
import java.math.MathContext;

/**
 * Handles the output of the ratio.
 * <p>
 * Created by schinzel on 2017-03-19.
 */
interface IRatioCast<T extends IRatio<T>> extends IRatio<T> {

    /**
     * @return The ratio held by this instance.
     */
    default BigDecimal getBigDec() {
        BigDecimal num = new BigDecimal(this.getNumerator());
        BigDecimal dec = new BigDecimal(this.getDenominator());
        return num.divide(dec, MathContext.DECIMAL128);
    }


    /**
     * @return The ratio held by this instance.
     */
    default double getDouble() {
        return this.getBigDec().doubleValue();
    }


    /**
     * Sample output: "1/3"
     *
     * @return The ratio as a string.
     */
    default String getString() {
        return this.getNumerator().toString() + "/" + this.getDenominator().toString();
    }


    /**
     * Sample output: "1/3"
     *
     * @return The ratio as a Str.
     */
    default Str getStr() {
        return Str.create().a(this.getString());
    }


}

package io.schinzel.basicutils.str;

import java.util.Locale;

/**
 * Created by schinzel on 2017-02-26.
 */
interface IStrNumbers<T extends IStr<T>> extends IStr<T> {


    /**
     * The argument value is added with a thousand separator for readability.
     *
     * @param f             The float to add
     * @param numOfDecimals The number of decimals to display.
     * @return This for chaining,
     */
    default T a(float f, int numOfDecimals) {
        return this.a((double) f, numOfDecimals);
    }


    /**
     * The argument value is added with a thousand separator for readability.
     *
     * @param d             The double to add
     * @param numOfDecimals The number of decimals to display.
     * @return This for chaining,
     */
    default T a(double d, int numOfDecimals) {
        String s = String.format(this.getLocale(), "%,." + numOfDecimals + "f", d);
        this.append(s);
        return this.getThis();
    }


    /**
     * The argument value is added with a thousand separator for readability.
     *
     * @param l The long to add.
     * @return This for chaining.
     */
    default T a(int l) {
        return this.a((long) l);
    }


    /**
     * The argument value is added with a thousand separator for readability.
     *
     * @param l The long to add.
     * @return This for chaining.
     */
    default T a(long l) {
        String s = String.format(this.getLocale(), "%,d", l);
        this.append(s);
        return this.getThis();
    }
}

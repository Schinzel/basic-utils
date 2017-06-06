package io.schinzel.basicutils.str;

/**
 * The purpose of this interface is to add numbers and format them for human readability.
 *
 * Created by schinzel on 2017-02-26.
 */
interface IStrNumbers<T extends IStr<T>> extends IStr<T> {


    /**
     * The argument value is added with a thousand separator for readability.
     *
     * @param f             The float to addChild
     * @param numOfDecimals The number of decimals to display.
     * @return This for chaining,
     */
    default T a(float f, int numOfDecimals) {
        return this.a((double) f, numOfDecimals);
    }


    /**
     * The argument value is added with a thousand separator for readability.
     *
     * @param d             The double to addChild
     * @param numOfDecimals The number of decimals to display.
     * @return This for chaining,
     */
    default T a(double d, int numOfDecimals) {
        String s = String.format(this.getLocale(), "%,." + numOfDecimals + "f", d);
        this.a(s);
        return this.getThis();
    }


    /**
     * The argument value is added with a thousand separator for readability.
     *
     * @param l The long to addChild.
     * @return This for chaining.
     */
    default T a(int l) {
        return this.a((long) l);
    }


    /**
     * The argument value is added with a thousand separator for readability.
     *
     * @param l The long to addChild.
     * @return This for chaining.
     */
    default T a(long l) {
        String s = String.format(this.getLocale(), "%,d", l);
        this.a(s);
        return this.getThis();
    }
}

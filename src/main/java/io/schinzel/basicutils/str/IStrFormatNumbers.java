package io.schinzel.basicutils.str;

/**
 * The purpose of this interface is to add numbers and format them for human readability.
 * <p>
 * Created by schinzel on 2017-02-26.
 */
interface IStrFormatNumbers<T extends IStr<T>> extends IStr<T> {


    /**
     * The argument value is append with a thousand separator for readability
     *
     * @param f             The float to append
     * @param numOfDecimals The number of decimals to display
     * @return This for chaining
     */
    default T af(float f, int numOfDecimals) {
        return this.af((double) f, numOfDecimals);
    }


    /**
     * The argument value is append with a thousand separator for readability
     *
     * @param d             The double to append
     * @param numOfDecimals The number of decimals to display
     * @return This for chaining
     */
    default T af(double d, int numOfDecimals) {
        String s = String.format(this.getLocale(), "%,." + numOfDecimals + "f", d);
        return this.a(s);
    }


    /**
     * The argument value is append with a thousand separator for readability
     *
     * @param i The int to append
     * @return This for chaining
     */
    default T af(int i) {
        return this.af((long) i);
    }


    /**
     * The argument value is append with a thousand separator for readability
     *
     * @param l The long to append
     * @return This for chaining
     */
    default T af(long l) {
        String s = String.format(this.getLocale(), "%,d", l);
        return this.a(s);
    }
}

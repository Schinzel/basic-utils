package io.schinzel.basicutils.str;

import io.schinzel.basicutils.EmptyObjects;

/**
 * Appends quoted strings.
 * <p>
 * Created by schinzel on 2017-03-10.
 */
@SuppressWarnings("SameParameterValue")
interface IStrQuote<T extends IStr<T>> extends IStr<T> {


    /**
     * Appends the argument string surrounded with single quotation marks.
     *
     * @param s The string to append.
     * @return This for chaining.
     */
    default T aq(String s) {
        return this.aq(s, "'", "'");
    }


    /**
     * Appends the argument string surrounded with argument quote char.
     *
     * @param s     The string to append.
     * @param quote The quote char to surround string with.
     * @return This for chaining.
     */
    default T aq(String s, char quote) {
        return this.aq(s, String.valueOf(quote), String.valueOf(quote));
    }


    /**
     * Appends the argument string surrounded with argument quote string.
     *
     * @param s     The string to append.
     * @param quote The quote string to surround string with.
     * @return This for chaining.
     */
    default T aq(String s, String quote) {
        return this.aq(s, quote, quote);
    }


    /**
     * Appends the argument string surrounded with argument start and end quote chars.
     *
     * @param s          The string to append.
     * @param startQuote The quote char to prefix the argument string with.
     * @param endQuote   The quote char to suffix the argument string with.
     * @return This for chaining.
     */
    default T aq(String s, char startQuote, char endQuote) {
        return this.aq(s, String.valueOf(startQuote), String.valueOf(endQuote));
    }


    /**
     * Appends the argument string surrounded with argument start and end quote String.
     *
     * @param s          The string to append.
     * @param startQuote The quote string to prefix the argument string with.
     * @param endQuote   The quote string to suffix the argument string with.
     * @return This for chaining.
     */
    default T aq(String s, String startQuote, String endQuote) {
        if (s == null) {
            s = EmptyObjects.EMPTY_STRING;
        }
        return this.a(startQuote + s + endQuote);
    }
}

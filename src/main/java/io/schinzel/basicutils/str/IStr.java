package io.schinzel.basicutils.str;

import java.util.Locale;

/**
 * This is the base interfaces. Actual functionality are implemented in a set of interfaces that extends this
 * interface
 *
 *
 * Created by schinzel on 2017-02-26.
 */
interface IStr<T extends IStr<T>> {

    /**
     * Appends the argument string to the string held.
     *
     * This should really be package private or protected. But as this is not an option, it has to be public.
     *
     * @param s
     * @return This for chaining.
     */
    T append(String s);


    /**
     * Exists for programming technical reasons only. Allows implementing classes and extending interfaces return
     * this without casting and
     *
     * This should really be package private or protected. But as this is not an option, it has to be public.
     *
     * @return This for chaining.
     */
    T getThis();


    /**
     * This should really be package private or protected. But as this is not an option, it has to be public.
     *
     * @return The locale used by any implementing class.
     */
    Locale getLocale();


    /**
     *
     * @return The string held by the implementing class.
     */
    String getString();

}

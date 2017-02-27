package io.schinzel.basicutils.str;

import java.util.Locale;

/**
 * This is the base interfaces.
 *
 * Actual functionality are implemented in a set of interfaces that extends this
 * interface. The implementing class implements one or several of the interfaces that extended this.
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
    T a(String s);


    /**
     * Exists for programming technical reasons only. Allows implementing classes and extending interfaces to return
     * a "this" that refers to implementing class or extending interface, instead of the interface being implemented
     * or extended. This as opposed to casting and/or overloading methods just to return the correct type.
     *
     * This should really be package private or protected. But as this is not an option, it has to be public.
     *
     * @return This for chaining.
     */
    T getThis();


    /**
     *
     * This should really be package private or protected. But as this is not an option, it has to be public.
     *
     * @return The locale used by operations where locale is a factor.
     */
    Locale getLocale();


    /**
     *
     * @return The string held.
     */
    String getString();

}

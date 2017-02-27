package io.schinzel.basicutils.str;

import java.util.Locale;

/**
 * Created by schinzel on 2017-02-26.
 */
interface IStr<T extends IStr<T>> {

    T append(String s);


    T getThis();


    Locale getLocale();


    String getString();

}

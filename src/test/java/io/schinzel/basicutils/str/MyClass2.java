package io.schinzel.basicutils.str;

import java.util.Locale;

/**
 * Created by schinzel on 2017-02-27.
 */
public class MyClass2 implements IStr<MyClass2> {
    private StringBuilder sb = new StringBuilder();
    @Override
    public MyClass2 a(String s) {
        sb.append(s);
        return this;
    }


    @Override
    public MyClass2 getThis() {
        return this;
    }


    @Override
    public Locale getLocale() {
        return Locale.US;
    }


    @Override
    public String getString() {
        return sb.toString();
    }
}

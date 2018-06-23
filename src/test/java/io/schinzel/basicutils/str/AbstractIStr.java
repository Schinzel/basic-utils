package io.schinzel.basicutils.str;

import java.util.Locale;

abstract class AbstractIStr<T extends IStr<T>> implements IStr<T> {
    private StringBuilder sb = new StringBuilder();


    @Override
    public T a(String s) {
        sb.append(s);
        return this.getThis();
    }


    @Override
    public Locale getLocale() {
        return Locale.US;
    }


    @Override
    public String asString() {
        return sb.toString();
    }
}

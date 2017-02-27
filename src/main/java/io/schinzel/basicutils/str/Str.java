package io.schinzel.basicutils.str;

import java.util.Locale;

/**
 * Created by schinzel on 2017-02-26.
 */
public class Str implements IStrNumbers<Str>, IStrWhitespace<Str>, IStrOutput<Str> {
    StringBuilder sb = new StringBuilder();


    public static Str create() {
        return new Str();
    }


    @Override
    public Str append(String s) {
        sb.append(s);
        return this;
    }


    @Override
    public Str getThis() {
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

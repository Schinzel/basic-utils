package io.schinzel.basicutils.str;

import java.util.Locale;

/**
 * Created by schinzel on 2017-02-26.
 */
public class Str implements StrNumbers<Str>, StrWhitespace<Str> {
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


    public Str pln() {
        System.out.println(sb.toString());
        return this;
    }


    public String toString() {
        return sb.toString();
    }
}

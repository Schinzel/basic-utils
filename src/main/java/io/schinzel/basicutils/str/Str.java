package io.schinzel.basicutils.str;

import java.util.Locale;

/**
 * This class contains the most common string operations with less code.
 *
 * Created by schinzel on 2017-02-26.
 */
public class Str implements IStrNumbers<Str>, IStrWhitespace<Str>, IStrOutput<Str>, IStrString<Str>, IStrUtil<Str> {
    StringBuilder sb = new StringBuilder();


    public static Str create() {
        return new Str();
    }


    @Override
    public Str a(String s) {
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


    /**
     * As it is not allowed to override Object methods in interfaces, a getString was necessary. But this method
     * does the same as getString.
     *
     * @return The same as getString.
     */
    @Override
    public String toString() {
        return this.getString();
    }
}

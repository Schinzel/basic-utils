package io.schinzel.basicutils.str;

import io.schinzel.basicutils.Thrower;

import java.util.Locale;

/**
 * This class contains the most common string operations with less code.
 * <p>
 * Created by schinzel on 2017-02-26.
 */
public class Str implements IStrQuote<Str>, IStrNumbers<Str>, IStrWhitespace<Str>, IStrOutput<Str>,
        IStrString<Str>, IStrUtil<Str> {
    StringBuilder sb = new StringBuilder();


    /**
     * @return A new instance of Str.
     */
    public static Str create() {
        return new Str();
    }


    /**
     * @param string String to set as initial string.
     * @return A new instance of Str.
     */
    public static Str create(String string) {
        return new Str().a(string);
    }


    @Override
    public Str a(String s) {
        Thrower.throwIfVarNull(s, "s");
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

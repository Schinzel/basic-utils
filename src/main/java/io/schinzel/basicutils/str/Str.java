package io.schinzel.basicutils.str;

import io.schinzel.basicutils.thrower.Thrower;

import java.util.Locale;

/**
 * This class contains the most common string operations with less code.
 * <p>
 * Created by schinzel on 2017-02-26.
 */

@SuppressWarnings("WeakerAccess")
public class Str implements IStrCast<Str>, IStrFormatNumbers<Str>, IStrOutput<Str>, IStrQuote<Str>,
        IStrString<Str>, IStrUtil<Str>, IStrWhitespace<Str> {
    /** Holds the stings added. */
    private StringBuilder mSb = new StringBuilder();
    /** If true, the adding of strings is paused. */
    private boolean mAddingPaused;


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
        if (!mAddingPaused) {
            mSb.append(s);
        }
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
        return mSb.toString();
    }


    /**
     * @param condition If false, strings added until and endIf request are ignored.
     * @return This for chaining
     */
    public Str ifTrue(boolean condition) {
        mAddingPaused = !condition;
        return this;
    }


    /**
     * Unpauses any potential pause in string adding set by ifTrue(true).
     *
     * @return This for chaining
     */
    public Str endIf() {
        mAddingPaused = false;
        return this;
    }


    /**
     * As it is not allowed to override Object methods in interfaces, a getString was necessary. But
     * this method
     * does the same as getString.
     *
     * @return The same as getString.
     */
    @Override
    public String toString() {
        return this.getString();
    }
}

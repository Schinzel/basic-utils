package io.schinzel.basicutils.str;

/**
 * The purpose of this interface is to append strings.
 * <p>
 * Created by Schinzel on 2017-02-27.
 */
interface IStrString<T extends IStr<T>> extends IStr<T> {


    /**
     * Appends the string held by the argument StringBuilder.
     *
     * @param sb The StringBuilder to append.
     * @return This for chaining.
     */
    default T a(StringBuilder sb) {
        this.a(sb.toString());
        return this.getThis();
    }


    /**
     * Appends another instance of this class.
     *
     * @param s The instance to append.
     * @return This for chaining.
     */
    default T a(T s) {
        this.a(s.getString());
        return this.getThis();
    }


    /**
     * Appends the argument char.
     *
     * @param c The char to append
     * @return This for chaining.
     */
    default T a(char c) {
        this.a(String.valueOf(c));
        return this.getThis();
    }
}

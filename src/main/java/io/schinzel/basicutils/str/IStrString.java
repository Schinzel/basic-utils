package io.schinzel.basicutils.str;

/**
 * The purpose of this interface is to append strings.
 * <p>
 * Created by Schinzel on 2017-02-27.
 */
interface IStrString<T extends IStr<T>> extends IStr<T> {
    String NULL_STRING = "null";


    /**
     * Appends the string held by the argument StringBuilder.
     *
     * @param sb The StringBuilder to append.
     * @return This for chaining.
     */
    default T a(StringBuilder sb) {
        return this.a(sb.toString());
    }


    /**
     * Appends another instance of this class.
     *
     * @param s The instance to append.
     * @return This for chaining.
     */
    default T a(T s) {
        return this.a(s.getString());
    }


    /**
     * Appends the argument char.
     *
     * @param c The char to append
     * @return This for chaining.
     */
    default T a(char c) {
        return this.a(String.valueOf(c));
    }


    /**
     * Appends the argument float.
     *
     * @param f The float to append
     * @return This for chaining.
     */
    default T a(Float f) {
        return (f == null)
                ? this.a(NULL_STRING)
                : this.a(String.valueOf(f));
    }


    /**
     * Appends the argument double.
     *
     * @param d The double to append
     * @return This for chaining.
     */
    default T a(Double d) {
        return (d == null)
                ? this.a(NULL_STRING)
                : this.a(String.valueOf(d));
    }


    /**
     * Appends the argument long.
     *
     * @param l The long to append
     * @return This for chaining.
     */
    default T a(Long l) {
        return (l == null)
                ? this.a(NULL_STRING)
                : this.a(String.valueOf(l));
    }


    /**
     * Appends the argument int.
     *
     * @param i The int to append
     * @return This for chaining.
     */
    default T a(Integer i) {
        return (i == null)
                ? this.a(NULL_STRING)
                : this.a(String.valueOf(i));
    }


    /**
     * Appends the argument boolean.
     *
     * @param b The boolean to append
     * @return This for chaining.
     */
    default T a(Boolean b) {
        return (b == null)
                ? this.a(NULL_STRING)
                : this.a(String.valueOf(b));
    }


}


package io.schinzel.basicutils.str;

/**
 * The purpose of this interface is to append strings.
 * <p>
 * Created by schinzel on 2017-02-27.
 */
interface IStrString<T extends IStr<T>> extends IStr<T> {


    /**
     * Appends the string held by the argument StringBuilder .
     *
     * @param sb The StringBuilder to append.
     * @return This for chaining.
     */
    default T a(StringBuilder sb) {
        this.a(sb.toString());
        return this.getThis();
    }


    /**
     * Appends the string held by the argument Str.
     *
     * @param s The Str to append
     * @return This for chaining.
     */
    default T a(Str s) {
        this.a(s.getString());
        return this.getThis();
    }


    /**
     * Appends the argument string surrounded with single quotation marks.
     *
     * @param s The string to append.
     * @return This for chaining.
     */
    default T aq(String s){
        this.a("'" + s  + "'");
        return this.getThis();
    }

}

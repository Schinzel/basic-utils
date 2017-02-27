package io.schinzel.basicutils.str;

/**
 * Created by schinzel on 2017-02-27.
 */
interface IStrOutput<T extends IStr<T>> extends IStr<T> {


    /**
     * Print the string held to system out.
     *
     * @return This for chaining.
     */
    default T pln() {
        System.out.println(this.getString());
        return this.getThis();
    }

}

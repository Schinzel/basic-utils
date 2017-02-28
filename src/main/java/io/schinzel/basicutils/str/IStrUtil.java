package io.schinzel.basicutils.str;

import io.schinzel.basicutils.Checker;

/**
 * The purpose of this class to hold utility methods such as isEmpty.
 * <p>
 * Created by Schinzel on 2017-02-28.
 */
interface IStrUtil<T extends IStr<T>> extends IStr<T> {

    /**
     *
     * @return True if the string held is empty.
     */
    default boolean isEmpty(){
        return Checker.isEmpty(this.getString());
    }

}

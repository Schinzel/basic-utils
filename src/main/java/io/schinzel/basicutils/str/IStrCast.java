package io.schinzel.basicutils.str;

import com.google.common.base.Charsets;
import com.google.common.primitives.Doubles;
import com.google.common.primitives.Ints;
import io.schinzel.basicutils.Thrower;

/**
 * The purpose of this interface is cast the string held by this object to different data types.
 * <p>
 * Created by Schinzel on 2017-06-06.
 */
interface IStrCast<T extends IStr<T>> extends IStr<T> {

    /**
     * @return Cast the string held by this object to utf8 bytes.
     */
    default byte[] castToUtf8Bytes() {
        return this.getString().getBytes(Charsets.UTF_8);
    }


    /**
     * @return Cast the string held by this object to integer.
     */
    default Integer castToInt() {
        Integer returnValue = Ints.tryParse(this.getString());
        Thrower.throwIfNull(returnValue)
                .message("Cannot cast '" + this.getString() + "' to integer");
        return returnValue;
    }


    /**
     * @return Cast the string held by this object to double.
     */
    default Double castToDouble() {
        Double returnValue = Doubles.tryParse(this.getString());
        Thrower.throwIfNull(returnValue)
                .message("Cannot cast '" + this.getString() + "' to double");
        return returnValue;
    }

}

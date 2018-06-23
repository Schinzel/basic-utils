package io.schinzel.basicutils.str;

import com.google.common.base.Charsets;
import com.google.common.primitives.Ints;
import com.google.common.primitives.Longs;
import io.schinzel.basicutils.thrower.Thrower;

/**
 * The purpose of this interface is cast the string held by this object to different data types.
 * <p>
 * Created by Schinzel on 2017-06-06.
 */
interface IStrCast<T extends IStr<T>> extends IStr<T> {

    /**
     * @return Cast the string held by this object to utf8 bytes.
     */
    default byte[] asUtf8Bytes() {
        return this.asString().getBytes(Charsets.UTF_8);
    }


    /**
     * @return Cast the string held by this object to integer.
     */
    default Integer asInt() {
        Integer returnValue = Ints.tryParse(this.asString());
        Thrower.throwIfNull(returnValue)
                .message("Cannot cast '" + this.asString() + "' to integer");
        return returnValue;
    }


    /**
     * @return Cast the string held by this object to long.
     */
    default Long asLong() {
        Long returnValue = Longs.tryParse(this.asString());
        Thrower.throwIfNull(returnValue)
                .message("Cannot cast '" + this.asString() + "' to long");
        return returnValue;
    }

}

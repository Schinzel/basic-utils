package io.schinzel.basicutils.configvar;

/**
 * The purpose of this interface is to return values for properties keys.
 * <p>
 * Created by schinzel on 2017-07-01.
 */
public interface IConfigVar {
    /**
     * @param keyName The name of the key.
     * @return Returns the value of the property with the argument key.
     */
    String getValue(String keyName);


    /**
     * @param objectWithName A object with a name
     * @return Returns the value of the property with the argument key.
     */
    default String getValue(IName objectWithName) {
        return this.getValue(objectWithName.getMyName());
    }

}

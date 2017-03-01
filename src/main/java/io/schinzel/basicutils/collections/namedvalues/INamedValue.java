package io.schinzel.basicutils.collections.namedvalues;

/**
 * The purpose of this interface is to be implemented for storing custom
 * objects in the collection NamedValues.
 *
 * @author schinzel
 */
public interface INamedValue extends Comparable<INamedValue> {

    /**
     * @return The unique name of this object.
     */
    default String getName() {
        return this.getNamedValue().getName();
    }


    /**
     *
     * @return A description of this object. If non has been set, default text stating this will be returned.
     */
    default String getDescription() {
        return this.getNamedValue().getDescription();
    }


    /**
     *
     * @return
     */
    NamedValue getNamedValue();


    @Override
    default int compareTo(INamedValue o) {
        return this.getName().compareToIgnoreCase(o.getName());
    }

}

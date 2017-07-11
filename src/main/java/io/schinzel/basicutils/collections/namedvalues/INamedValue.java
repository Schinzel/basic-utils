package io.schinzel.basicutils.collections.namedvalues;

/**
 * The purpose of this interface is to be implemented for storing values in the collection
 * NamedValues.
 *
 * @author schinzel
 */
public interface INamedValue extends Comparable<INamedValue> {

    /**
     * @return The name of the value.
     */
    String getName();


    @Override
    default int compareTo(INamedValue o) {
        return this.getName().compareToIgnoreCase(o.getName());
    }
}

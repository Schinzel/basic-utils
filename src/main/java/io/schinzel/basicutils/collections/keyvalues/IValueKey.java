package io.schinzel.basicutils.collections.keyvalues;

/**
 * The purpose of this interface is to be implemented for storing values in the collection KeyValues.
 *
 * @author schinzel
 */
public interface IValueKey extends Comparable<IValueKey> {

    /**
     * @return
     */
    String getKey();


    @Override
    default int compareTo(IValueKey o) {
        return this.getKey().compareToIgnoreCase(o.getKey());
    }
}

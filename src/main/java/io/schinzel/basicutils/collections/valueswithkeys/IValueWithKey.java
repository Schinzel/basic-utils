package io.schinzel.basicutils.collections.valueswithkeys;

/**
 * The purpose of this interface is to be implemented for storing values in the collection
 * ValuesWithKeys.
 *
 * @author schinzel
 */
public interface IValueWithKey extends Comparable<IValueWithKey> {

    /**
     * @return The name of the value.
     */
    String getKey();


    @Override
    default int compareTo(IValueWithKey o) {
        return this.getKey().compareToIgnoreCase(o.getKey());
    }
}

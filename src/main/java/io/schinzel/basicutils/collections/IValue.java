package io.schinzel.basicutils.collections;

/**
 * The purpose of this interface is to be implemented for storing custom
 * objects in the collection IdSet. 
 *
 * @author schinzel
 */
public interface IValue extends Comparable<IValue> {

    /**
     *
     * @return An unique identifier.
     */
    String getid();


    @Override
    default int compareTo(IValue o) {
        return this.getid().compareToIgnoreCase(o.getid());
    }

}

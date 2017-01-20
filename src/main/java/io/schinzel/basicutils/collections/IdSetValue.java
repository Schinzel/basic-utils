package io.schinzel.basicutils.collections;

/**
 * The purpose of this interface is to be implemented for storing custom
 * objects in the collection IdSet. 
 *
 * @author schinzel
 */
public interface IdSetValue extends Comparable<IdSetValue> {

    /**
     *
     * @return An unique identifier.
     */
    String getid();


    @Override
    default int compareTo(IdSetValue o) {
        return this.getid().compareToIgnoreCase(o.getid());
    }

}
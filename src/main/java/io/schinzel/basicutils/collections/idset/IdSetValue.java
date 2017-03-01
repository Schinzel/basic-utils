package io.schinzel.basicutils.collections.idset;

/**
 * The purpose of this interface is to be implemented for storing custom
 * objects in the collection IdSet.
 *
 * @author schinzel
 */
public interface IdSetValue extends Comparable<IdSetValue> {

    /**
     * @return An unique identifier.
     */
    default String getId() {
        return this.getIdObj().getId();
    }


    default String getDescription() {
        return this.getIdObj().getDescription();
    }

    IdObj getIdObj();


    @Override
    default int compareTo(IdSetValue o) {
        return this.getId().compareToIgnoreCase(o.getId());
    }

}

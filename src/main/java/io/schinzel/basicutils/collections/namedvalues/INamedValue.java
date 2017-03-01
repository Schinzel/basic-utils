package io.schinzel.basicutils.collections.namedvalues;

/**
 * The purpose of this interface is to be implemented for storing custom
 * objects in the collection NamedValues.
 *
 * @author schinzel
 */
public interface INamedValue extends Comparable<INamedValue> {

    /**
     * @return An unique identifier.
     */
    default String getId() {
        return this.getIdObj().getId();
    }


    default String getDescription() {
        return this.getIdObj().getDescription();
    }

    NamedValue getIdObj();


    @Override
    default int compareTo(INamedValue o) {
        return this.getId().compareToIgnoreCase(o.getId());
    }

}

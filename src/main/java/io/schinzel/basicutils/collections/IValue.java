package io.schinzel.basicutils.collections;

/**
 *
 * @author schinzel
 */
public interface IValue extends Comparable<IValue> {

    String getid();


    @Override
    default int compareTo(IValue o) {
        return this.getid().compareToIgnoreCase(o.getid());
    }

}

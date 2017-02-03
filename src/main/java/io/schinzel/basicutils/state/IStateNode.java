package io.schinzel.basicutils.state;

/**
 * The purpose of this interface is to generate and return the state of an
 * objects and its logical children as a human readable string or a JSON object.
 *
 * @author schinzel
 */
public interface IStateNode {

    /**
     *
     * @return The state of the implementing object.
     */
    State getState();

}

package io.schinzel.basicutils.state.REMOVME;

import org.json.JSONObject;

/**
 * The purpose of this class ...
 *
 * @author schinzel
 */
class REMOVE_ME {

    public static void main(String[] args) {
        TheClass theClass = TheClass.create();
        JSONObject json = theClass.getState().getJson();
        System.out.println(json.toString(3));
        System.out.println("\n***");
        System.out.println(theClass.getState().getString());
    }

}

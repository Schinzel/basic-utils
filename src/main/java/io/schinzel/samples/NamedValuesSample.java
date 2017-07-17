package io.schinzel.samples;

import io.schinzel.basicutils.collections.namedvalues.INamedValue;
import io.schinzel.basicutils.collections.namedvalues.NamedValues;

/**
 * Sample class to show how KeyValues can be used.
 * <p>
 * Created by schinzel on 2017-03-27.
 */
class NamedValuesSample {

    public static void main(String[] args) {
        new NamedValuesSample().doIt();

    }


    void doIt() {
        NamedValues<MyValue> mySet = NamedValues.<MyValue>create("My nice values")
                .add(new MyValue("A"))
                .add(new MyValue("B"));
        MyValue category = mySet.has("C")
                ? mySet.get("C")
                : mySet.addAndGet(new MyValue("C"));
        //Will throw exception as there is no value with this key
        mySet.get("apa");

    }


    class MyValue implements INamedValue {
        final String key;


        MyValue(String key) {
            this.key = key;
        }


        public String getName() {
            return this.key;
        }
    }
}

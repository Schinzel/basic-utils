package io.schinzel.basicutils.test;

/**
 * Created by schinzel on 2017-03-26.
 */
public class TheThing {

    public Prop addProp(){
        return new Prop(this);
    }
}

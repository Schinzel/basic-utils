package io.schinzel.basicutils.test;

import io.schinzel.basicutils.str.Str;
import lombok.Setter;
import lombok.experimental.Accessors;

@Accessors(prefix = "m", fluent = true, chain = true)
public class Prop {
    @Setter
    private String mKey;
    @Setter
    private String mUnit = "";
    private String mVal;
    Object mValueAsObject;

    private TheThing aThing;


    Prop(TheThing aThing) {
        this.aThing = aThing;
    }


    Prop val(int val) {
        mValueAsObject = val;
        return this.setVal(String.valueOf(val));
    }

    Prop val(double val, int numOfDecimals) {
        mValueAsObject = val;
        return this.setVal(Str.create().a(val, numOfDecimals).toString());
    }

    private Prop setVal(String val) {
        mVal = val;
        return this;
    }


    TheThing buildProp() {
        return this.aThing;
    }

}

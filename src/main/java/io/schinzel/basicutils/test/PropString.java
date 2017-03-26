package io.schinzel.basicutils.test;

import io.schinzel.basicutils.str.Str;

/**
 * Created by schinzel on 2017-03-26.
 */
class PropString {
    final String mVal;


    PropString(String val) {
        mVal = val;
    }


    PropString(int val) {
        mVal = String.valueOf(val);
    }


    PropString(double val, int numOfDecimals) {
        mVal = Str.create().a(val, numOfDecimals).toString();
    }


    PropString(boolean val) {
        mVal = String.valueOf(val);
    }


    Str getStr() {
        return Str.create().a(mVal);
    }
}

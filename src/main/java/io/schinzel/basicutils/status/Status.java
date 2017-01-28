package io.schinzel.basicutils.status;

import io.schinzel.basicutils.Thrower;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * The purpose of this class ...
 *
 * @author schinzel
 */
public class Status {

    final Map<String, Object> mProperties;
    List<Status> mChildren = new ArrayList<>();

    //*************************************************************************
    //* CONSTRUCTORS
    //*************************************************************************
    private Status(Builder builder) {
        mProperties = builder.mProperties;
    }


    public static Builder getBuilder() {
        return new Builder();
    }

    //*************************************************************************
    //* BUILDER
    //*************************************************************************

    public static class Builder {

        Map<String, Object> mProperties = new LinkedHashMap<>();

        public Builder add(String key, String val) {
            Thrower.throwIfEmpty(key, "key");
            Thrower.throwIfEmpty(val, "val");
            mProperties.put(key, val);
            return this;
        }


        public Builder add(String key, int value) {
            String strVal = NumberFormat.getIntegerInstance().format(value);
            return this.add(key, strVal);
        }


        public Builder add(String key, long value) {
            String strVal = NumberFormat.getIntegerInstance().format(value);
            return this.add(key, strVal);
        }


        public Builder add(String key, double value) {
            return this.add(key, value, 2);
        }


        public Builder add(String key, double value, int numOfDecimals) {
            String strVal = String.format("%." + numOfDecimals + "f", value);
            return this.add(key, strVal);
        }


        public Builder add(String key, float value) {
            return this.add(key, value, 2);
        }


        public Builder add(String key, float value, int numOfDecimals) {
            String strVal = String.format("%." + numOfDecimals + "f", value);
            return this.add(key, strVal);
        }


        public Status build() {
            return new Status(this);
        }


    }

    //*************************************************************************
    //* MISC
    //*************************************************************************
    Status addChild(Status child) {
        mChildren.add(child);
        return this;
    }


    //*************************************************************************
    //* GET PROPERTIES
    //*************************************************************************
    Map<String, Object> getProps() {
        return mProperties;
    }


}

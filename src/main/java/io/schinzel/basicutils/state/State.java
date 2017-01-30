package io.schinzel.basicutils.state;

import io.schinzel.basicutils.Thrower;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * The purpose of this class to hold a set of properties.
 *
 * @author schinzel
 */
public class State {

    /**
     * Holds the properties.
     */
    final Map<String, Object> mProperties;

    //*************************************************************************
    //* CONSTRUCTORS
    //*************************************************************************
    private State(Builder builder) {
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

        Builder() {
        }


        public Builder add(String key, String val) {
            Thrower.throwIfEmpty(key, "key");
            Thrower.throwIfEmpty(val, "val");
            mProperties.put(key, val);
            return this;
        }


        public Builder add(String key, int value) {
            return this.add(key, NumberFormatter.format(value));
        }


        public Builder add(String key, long value) {
            return this.add(key, NumberFormatter.format(value));
        }


        public Builder add(String key, double value) {
            return this.add(key, value, 2);
        }


        public Builder add(String key, double value, int numOfDecimals) {
            return this.add(key, NumberFormatter.format(value, numOfDecimals));
        }


        public Builder add(String key, float value) {
            return this.add(key, value, 2);
        }


        public Builder add(String key, float value, int numOfDecimals) {
            return this.add(key, NumberFormatter.format(value, numOfDecimals));
        }


        public State build() {
            return new State(this);
        }


    }

    //*************************************************************************
    //* GET PROPERTIES
    //*************************************************************************
    /**
     *
     * @return The properties held by this object.
     */
    Map<String, Object> getProperties() {
        return mProperties;
    }


}

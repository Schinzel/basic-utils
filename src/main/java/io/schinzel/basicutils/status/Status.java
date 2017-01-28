package io.schinzel.basicutils.status;

import com.google.common.base.Joiner;
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

    final Map<String, String> mProperties;
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

        Map<String, String> mProperties = new LinkedHashMap<>();

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


    }

    //*************************************************************************
    //* MISC
    //*************************************************************************
    Status addChild(Status status) {
        mChildren.add(status);
        return this;
    }


    //*************************************************************************
    //* GET PROPERTIES
    //*************************************************************************
    @Override
    public String toString() {
        return this.getResultTree(0);
    }


    /**
     *
     * @param intendation How many level the tree is this lap..
     * @return The result of this lap and all its sub laps.
     */
    String getResultTree(int intendation) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < intendation; i++) {
            sb.append("--");
        }
        sb.append(" ")
                .append(Joiner.on(" ")
                        .withKeyValueSeparator(":")
                        .join(mProperties))
                .append("\n");
        for (Status status : mChildren) {
            sb.append(status.getResultTree(intendation + 1));
        }
        return sb.toString();
    }


}

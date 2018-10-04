package io.schinzel.basicutils.configvar;

public enum ConfigVarNames implements IName {
    BEAR, EAGLE;


    @Override
    public String getMyName() {
        return this.name();
    }
}

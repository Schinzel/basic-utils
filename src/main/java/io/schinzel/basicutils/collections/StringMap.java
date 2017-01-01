package io.schinzel.basicutils.collections;

/**
 * The purpose of this class is to offer a less verbose usage of the most
 * common map.
 *
 * @author schinzel
 */
public class StringMap extends ChainedHashMap<String, String> {

    /**
     * Private constructor so that cannot be used.
     */
    private StringMap() {
    }


    /**
     * 
     * @return An empty StringMap.
     */
    public static StringMap create() {
        return new StringMap();
    }


    /**
     * 
     * @param key
     * @param value
     * @return A StringMap with the argument pair. 
     */
    public static StringMap create(String key, String value) {
        StringMap map = new StringMap();
        return map.add(key, value);
    }


    /**
     * 
     * @param key
     * @param value
     * @return This for chaining. 
     */
    @Override
    public StringMap add(String key, String value) {
        super.add(key, value);
        return this;
    }

}

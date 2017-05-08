package io.schinzel.basicutils.crypto.hash;

/**
 * The purpose of this class to hold a HashLibrary singleton instance. The rational for a
 * singleton is that typically hashing is used in so many places it can warrant
 * a singleton over passing a reference around.
 * <p>
 * Also, the singleton is practical for setting up a hash enum, which can be useful for creating
 * more concise and readable code.
 * <p>
 * Created by Schinzel on 2017-05-08.
 */
class HashLibrarySingleton {
    static class Holder {
        public static HashLibrarySingleton INSTANCE = new HashLibrarySingleton();
    }

    HashLibrary mLibrary;
    HashLibrary.HashLibraryBuilder mBuilder = HashLibrary.builder();


    /**
     * Package private so that the class cannot be instantiated outside the package.
     */
    HashLibrarySingleton() {
    }


    /**
     * Note that once the method getLibrary has been used the instance has been created and this
     * method will throw an error.
     *
     * @return A library builder.
     */
    public static HashLibrary.HashLibraryBuilder getBuilder() {
        //If builder is null, i.e. if the builder has already been used to create an instance.
        if (Holder.INSTANCE.mBuilder == null) {
            throw new RuntimeException("Hash cannot be added as HashLibrary already has been built.");
        }
        return HashLibrarySingleton.Holder.INSTANCE.mBuilder;
    }


    /**
     * The first time this method is used, the library is built with the hash functions given
     * to the builder.
     *
     * @return Returns a hash library instance.
     */
    public static HashLibrary getLibrary() {
        //If a library has not yet been build
        if (Holder.INSTANCE.mLibrary == null) {
            synchronized (Holder.INSTANCE) {
                Holder.INSTANCE.mLibrary = Holder.INSTANCE.mBuilder.build();
                Holder.INSTANCE.mBuilder = null;
            }
        }
        return Holder.INSTANCE.mLibrary;
    }

}

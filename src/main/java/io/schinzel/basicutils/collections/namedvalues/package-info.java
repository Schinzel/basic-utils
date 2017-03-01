/**
 * Named values offers storage of objects that know their own key/name.
 *
 * The purpose of the named values classes and interface is
 * 1) Objects that can be stored in a collection and know their own key (that is the name).
 * 2) A less verbose version than for example Map
 * 3) Some for me very nice features to have like addAndGet and fail-fast.
 *
 *
 * "Name" was used over "name" as
 * 1) usually name are not meant to be read by humans
 * 2) the latter is a very common term and as such all names IdSet, getName was hard to tell apart from other code.
 *
 * Created by Schinzel on 2017-03-01.
 */
package io.schinzel.basicutils.collections.namedvalues;
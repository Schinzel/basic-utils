/**
 * The purpose of this package is to offer a string utility class that contains the most common string operations
 * in one class with less verbose syntax and less boilerplate code.
 *
 * The functionality is broken down to into a set of interfaces as to have more modular code. We want the modular code
 * for all the usual reasons; easier to understand the code, easier to construct tests, easier to read code and so on.
 * Also there exists the possibility to reuse the functionality in other classes. For example IStrNumber can be
 * implemented in any class in which we to addChild numbers as strings easily.
 *
 * The purpose of working with interfaces rather than classes and inheritance is that:
 * 1) all possible states are stored in exactly one place, the implementing class.
 * 2) multiple inheritance.
 *
 * Created by Schinzel on 2017-02-27.
 */
package io.schinzel.basicutils.str;
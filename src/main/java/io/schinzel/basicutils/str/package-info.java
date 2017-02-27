/**
 * The purpose of this package is to offer a string utility class that contains the common string operations
 * in one class with less verbose syntax and less boilerplate code.
 *
 * Instead of
 * StringBuilder sb = new StringBuilder();
 * sb.append("A")
 * .append(String.format( "%.2f", myDouble ))
 * .append("B");
 * System.out.println(sb.toString());
 *
 * We have:
 * Str.create().a("A").a(myDouble, 2).a("B").pln();
 *
 * The functionality is broken down to into a set of interfaces as to have more modular code. We want the modular code
 * for all the usual reasons; easier to understand the code, easier to construct tests, easier to read code and so on.
 * Also there exists the possibility to reuse the functionality in other classes. For example IStrNumber can be
 * implemented in any class in which we easily want to append readable numbers.
 *
 * Created by schinzel on 2017-02-27.
 */
package io.schinzel.basicutils.str;
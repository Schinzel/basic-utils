/**
 * The purpose of this package is to create and hierarchy of state objects.
 * A state object represents the state of an object and its children.
 * The state hierarchy can be returned as a JSONObject or a more concise
 * and readable string.
 *
 * This is useful for:
 * - debug info.
 * - printing the state of a system to logs after for example a system start.
 * - letting objects return its state with the toString-method which will let
 * some IDEs show the state of objects on for example mouse over.
 * - include that state of a system when errors occur.
 * - return one or several system states in DevOps GUI tool as to quickly
 * assess the state of a system.
 *
 */
package io.schinzel.basicutils.state;

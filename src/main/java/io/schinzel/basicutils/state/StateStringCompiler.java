package io.schinzel.basicutils.state;

import com.google.common.base.Joiner;
import com.google.common.base.Strings;

/**
 * The purpose of this class transform state nodes to human readily human
 * readable strings.
 *
 *
 * @author schinzel
 */
class StateStringCompiler {

    /**
     *
     * @param stateNode
     * @return The argument node and all its sub-nodes as a human readable
     * string.
     */
    static String getStateTreeAsString(IStateNode stateNode) {
        return StateStringCompiler
                .getStateTreeAsString(stateNode, 0, new StringBuilder())
                .toString();
    }


    /**
     *
     * @param depth The current depth in the tree to render.
     * @param stateNode
     * @param sb The generated string is added to this string builder.
     * @return The argument node and all its sub-nodes as a human readable
     * string.
     */
    private static StringBuilder getStateTreeAsString(IStateNode stateNode,
            int depth, StringBuilder sb) {
        sb.append(Strings.repeat("--", depth))
                .append(" ")
                .append(getStateNodeAsString(stateNode))
                .append("\n");
        stateNode.getStateChildren().forEachRemaining(node -> 
                StateStringCompiler.getStateTreeAsString(node, depth + 1, sb));
        return sb;
    }


    /**
     *
     * @param stateNode
     * @return The argument node as human readable string.
     */
    private static String getStateNodeAsString(IStateNode stateNode) {
        return Joiner.on(" ")
                .withKeyValueSeparator(":")
                .join(stateNode.getState().getProperties());
    }


}

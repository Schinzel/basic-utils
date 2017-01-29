package io.schinzel.basicutils.status;

import com.google.common.base.Joiner;
import com.google.common.base.Strings;

/**
 * The purpose of this class transform status nodes to human readily human
 * readable strings.
 *
 *
 * @author schinzel
 */
class StatusStringCompiler {

    /**
     *
     * @param statusNode
     * @return The argument node and all its sub-nodes as a human readable
     * string.
     */
    static String getStatusTreeAsString(IStatusNode statusNode) {
        return StatusStringCompiler
                .getStatusTreeAsString(statusNode, 0, new StringBuilder())
                .toString();
    }


    /**
     *
     * @param depth The current depth in the tree to render.
     * @param statusNode
     * @param sb The generated string is added to this string builder.
     * @return The argument node and all its sub-nodes as a human readable
     * string.
     */
    private static StringBuilder getStatusTreeAsString(IStatusNode statusNode,
            int depth, StringBuilder sb) {
        sb.append(Strings.repeat("--", depth))
                .append(" ")
                .append(getStatusNodeAsString(statusNode))
                .append("\n");
        statusNode.getStatusChildren().forEachRemaining(
                node -> 
                StatusStringCompiler.getStatusTreeAsString(node, depth + 1, sb));
        return sb;
    }


    /**
     *
     * @param statusNode
     * @return The argument node as human readable string.
     */
    private static String getStatusNodeAsString(IStatusNode statusNode) {
        return Joiner.on(" ")
                .withKeyValueSeparator(":")
                .join(statusNode.getStatus().getProps());
    }


}

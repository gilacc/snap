/*
 * src/main/java/io/github/gilacc/snap/node/Node.java - Base class for all nodes
 * Copyright (C) 2026 Antonio Gil
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <https://www.gnu.org/licenses/>.
 */

package io.github.gilacc.snap.node;

import io.github.gilacc.snap.serialization.Serializer;

/**
 * <p>Base class for all nodes in a snapshot.</p>
 *
 * <p>{@link io.github.gilacc.snap.Snapshot} expose a {@link java.util.Map} of nodes, each of them representing a value
 * in the object's state, whether it is a scalar (boolean, number, string), a collection (map, list) or nil (Snap's
 * equivalent of {@code null}).</p>
 */
public sealed interface Node permits BooleanNode, NumberNode, StringNode, ListNode, MapNode, NilNode {

    /**
     * <p>Serializes the encapsulated value with the provided serializer.</p>
     *
     * @param serializer a serializer for the target format type
     * @param <T> the type of object emitted by the serializer
     * @return a serialized version of the node's content
     */
    <T> T serialize(Serializer<T> serializer);

}


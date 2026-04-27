/*
 * src/main/java/io/github/gilacc/snap/serialization/Serializer.java - Generic serializer for different types of nodes
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

package io.github.gilacc.snap.serialization;

import io.github.gilacc.snap.node.Node;

import java.util.List;
import java.util.Map;

/**
 * <p>Generic serializer for all the different types of node values a snapshot can encapsulate.</p>
 *
 * <p>Serializers need to implement the logic to convert different value types: number, boolean, string, list, map and
 * nil. Every method must return an instance of {@link T} (for example, a {@code JsonValue} from the Jakarta JSON API,
 * or a {@code Node} from Java's DOM API).</p>
 *
 * @param <T> the common return type for all conversions
 */
public interface Serializer<T> {

    /**
     * <p>Emits a serialized {@link Number} object.</p>
     *
     * @param number the number object to serialize
     * @return a serialized version of the object
     */
    T serializeNumber(Number number);

    /**
     * <p>Emits a serialized boolean object.</p>
     *
     * @param bool the boolean object to serialize
     * @return a serialized version of the object
     */
    T serializeBoolean(boolean bool);

    /**
     * <p>Emits a serialized string object.</p>
     *
     * @param string the string object to serialize
     * @return a serialized version of the object
     */
    T serializeString(String string);

    /**
     * <p>Emits a serialized list of nodes, recursively converting the values encapsulated by each of its members.</p>
     *
     * @param list the list object to serialize
     * @return a serialized version of the object
     */
    T serializeList(List<Node> list);

    /**
     * <p>Emits a serialized map of nodes, recursively converting the values encapsulated by each of its entries.</p>
     *
     * @param map the map object to serialize
     * @return a serialized version of the object
     */
    T serializeMap(Map<String, Node> map);

    /**
     * <p>Emits nil. Because nil represents the absence of a value (similar to {@code null}), and therefore every nil
     * node has the exact same content, this method takes no input parameters.</p>
     *
     * @return a serialized version of nil
     */
    T serializeNil();

}

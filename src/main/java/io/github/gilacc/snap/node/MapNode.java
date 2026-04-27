/*
 * src/main/java/io/github/gilacc/snap/node/MapNode.java - Node representing a map of values
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

import java.util.Collections;
import java.util.Map;

/**
 * <p>A {@link Node} representing a map of values, that is, a mapping from string keys to node values.</p>
 */
public final class MapNode implements Node {

    private final Map<String, Node> value;

    /**
     * <p>Constructs a node from an empty map.</p>
     */
    public MapNode() {
        this(Collections.emptyMap());
    }

    /**
     * <p>Constructs a node from the given map of nodes.</p>
     *
     * @param map the map value to encapsulate
     */
    public MapNode(final Map<String, Node> map) {
        this.value = Collections.unmodifiableMap(map);
    }

    @Override
    public <T> T serialize(Serializer<T> serializer) {
        return serializer.serializeMap(this.value);
    }

    @Override
    public String toString() {
        return this.value.toString();
    }

}

/*
 * src/main/java/io/github/gilacc/snap/node/ListNode.java - Node representing a list of values
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

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * <p>A {@link Node} representing a list of values.</p>
 */
public final class ListNode implements Node {

    private final List<Node> value;

    /**
     * <p>Constructs a list node from the given nodes.</p>
     *
     * @param nodes the sequence of nodes to encapsulate
     */
    public ListNode(final Node... nodes) {
        this(Arrays.asList(nodes));
    }

    /**
     * <p>Constructs a list node from the given list.</p>
     *
     * @param list the list of nodes to encapsulate
     */
    public ListNode(final List<Node> list) {
        this.value = Collections.unmodifiableList(list);
    }

    @Override
    public <T> T serialize(Serializer<T> serializer) {
        return serializer.serializeList(this.value);
    }

    @Override
    public String toString() {
        return this.value.toString();
    }

}

/*
 * src/main/java/io/github/gilacc/snap/node/StringNode.java - Node representing a string value
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
 * <p>A {@link Node} representing a string value.</p>
 */
public final class StringNode implements Node {

    private final String value;

    /**
     * <p>Constructs a string node from the given value.</p>
     */
    public StringNode() {
        this("");
    }

    /**
     * <p>Constructs a string node from the given value.</p>
     *
     * @param string the string value to encapsulate
     */
    public StringNode(final String string) {
        this.value = string;
    }

    @Override
    public <T> T serialize(Serializer<T> serializer) {
        return serializer.serializeString(this.value);
    }

    @Override
    public String toString() {
        return this.value;
    }

}

/*
 * src/main/java/io/github/gilacc/snap/node/BooleanNode.java - Node representing a boolean value
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
 * <p>A {@link Node} representing a boolean value.</p>
 */
public final class BooleanNode implements Node {

    private final boolean value;

    /**
     * <p>Constructs a boolean value from the given value.</p>
     *
     * @param bool the boolean value to encapsulate
     */
    public BooleanNode(final boolean bool) {
        this.value = bool;
    }

    @Override
    public <T> T serialize(Serializer<T> serializer) {
        return serializer.serializeBoolean(this.value);
    }

    @Override
    public String toString() {
        return Boolean.toString(this.value);
    }

}

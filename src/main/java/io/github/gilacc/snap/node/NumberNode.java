/*
 * src/main/java/io/github/gilacc/snap/node/NumberNode.java - Node representing a numeric value
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

import java.math.BigDecimal;

/**
 * <p>A {@link Node} representing a numeric value.</p>
 */
public final class NumberNode implements Node {

    private final Number value;

    /**
     * <p>Constructs a number node from the given representation, parsed into a {@link Number} object.</p>
     *
     * @param representation a string representation of the numeric value to encapsulate
     */
    public NumberNode(final String representation) {
        this(new BigDecimal(representation));
    }

    /**
     * <p>Constructs a number node from the given value.</p>
     *
     * @param number the numeric value to encapsulate
     */
    public NumberNode(final Number number) {
        this.value = number;
    }

    @Override
    public <T> T serialize(Serializer<T> serializer) {
        return serializer.serializeNumber(this.value);
    }

    @Override
    public String toString() {
        return Double.toString(this.value.doubleValue());
    }

}

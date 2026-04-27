/*
 * src/main/java/io/github/gilacc/snap/deserialization/Deserializer - Generic deserializer for payloads
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

package io.github.gilacc.snap.deserialization;

import io.github.gilacc.snap.Snapshot;

/**
 * <p>Generic deserializer for a payload representing an object's state.</p>
 *
 * <p>Deserializers can be used to convert objects of an arbitrary type into {@link Snapshot} objects.</p>
 *
 * @param <T> the payload type
 */
public interface Deserializer<T> {

    /**
     * <p>Restores a snapshot from a payload.</p>
     *
     * @param source the source payload
     * @return a snapshot representing the values in the payload
     */
    Snapshot restore(T source);

}

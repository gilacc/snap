/*
 * src/main/java/io/github/gilacc/snap/Resource.java - Resource capable of creating snapshots of its own state
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

package io.github.gilacc.snap;

/**
 * <p>Resource capable of creating snapshots of its own state.</p>
 *
 * <p>This interface exposes a single method, {@link Resource#snapshot()}, which creates a {@link Snapshot}, a view of
 * the object's state.</p>
 */
public interface Resource {

    /**
     * <p>Creates a snapshot representing the object's internal state.</p>
     *
     * @return a snapshot of the object's state
     */
    Snapshot snapshot();

}

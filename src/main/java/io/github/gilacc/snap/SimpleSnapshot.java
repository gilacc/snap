/*
 * src/main/java/io/github/gilacc/snap/SimpleSnapshot.java - A simple, immutable snapshot implementation
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

import io.github.gilacc.snap.node.Node;

import java.util.Map;

/**
 * <p>A simple, (shallowly) immutable implementation of {@link Snapshot}.</p>
 *
 * @param nodes the map of snapshot nodes
 */
public record SimpleSnapshot(Map<String, Node> nodes) implements Snapshot {}

/*
 * src/test/java/io/github/gilacc/snap/node/MapNodeTest.java - Test suite for MapNode
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

import io.github.gilacc.snap.demo.ToStringSerializer;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import static org.hamcrest.Matchers.*;
import static org.hamcrest.MatcherAssert.*;

class MapNodeTest {

    @Test
    void itCorrectlySerializesEmptyMap() {
        final MapNode node = new MapNode();
        assertThat(node.serialize(new ToStringSerializer()), equalTo(Collections.emptyMap().toString()));
    }

    @Test
    void itCorrectlySerializesNonEmptyMap() {
        final MapNode node = new MapNode(
            new TreeMap<>(Map.ofEntries(
                Map.entry("a", new StringNode("value")),
                Map.entry("b", new NumberNode("1.2345678")),
                Map.entry("c", new BooleanNode(false)),
                Map.entry("d", new NilNode()),
                Map.entry("e", new ListNode(
                    new StringNode("hello"),
                    new StringNode("world")
                )),
                Map.entry("f", new MapNode(Map.ofEntries(
                    Map.entry("1", new NumberNode(1))
                )))
            ))
        );
        assertThat(
            node.serialize(new ToStringSerializer()),
            equalTo(new TreeMap<>(Map.ofEntries(
                Map.entry("a", "value"),
                Map.entry("b", "1.2345678"),
                Map.entry("c", "false"),
                Map.entry("d", "nil"),
                Map.entry("e", List.of("hello", "world").toString()),
                Map.entry("f", Map.ofEntries(Map.entry("1", "1")).toString())
            )).toString())
        );
    }

}

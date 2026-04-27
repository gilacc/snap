/*
 * src/test/java/io/github/gilacc/snap/node/ListNodeTest.java - Test suite for ListNode
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

class ListNodeTest {

    @Test
    void itCorrectlySerializesEmptyList() {
        final ListNode node = new ListNode();
        assertThat(node.serialize(new ToStringSerializer()), equalTo(Collections.emptyList().toString()));
    }

    @Test
    void itCorrectlySerializesNonEmptyList() {
        final ListNode node = new ListNode(
            new StringNode("string"),
            new NumberNode("1.2345678"),
            new BooleanNode(false),
            new NilNode(),
            new MapNode(new TreeMap<>(Map.ofEntries(
                Map.entry("hello", new StringNode("world")),
                Map.entry("foo", new NumberNode(2))
            ))),
            new ListNode(
                new StringNode("b"),
                new StringNode("a"),
                new StringNode("r")
            )
        );
        assertThat(
            node.serialize(new ToStringSerializer()),
            equalTo(List.of(
                "string",
                "1.2345678",
                "false",
                "nil",
                new TreeMap<>(Map.ofEntries(
                    Map.entry("hello", "world"),
                    Map.entry("foo", "2")
                )).toString(),
                List.of("b", "a", "r").toString()
            ).toString())
        );
    }

}

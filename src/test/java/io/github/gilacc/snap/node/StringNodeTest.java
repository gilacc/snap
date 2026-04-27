/*
 * src/test/java/io/github/gilacc/snap/node/StringNodeTest.java - Test suite for StringNode
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

import static org.hamcrest.Matchers.*;
import static org.hamcrest.MatcherAssert.*;

class StringNodeTest {

    @Test
    void itCorrectlySerializesStrings() {
        final String value = "defghijk";
        final StringNode node = new StringNode(value);
        assertThat(node.serialize(new ToStringSerializer()), equalTo(value));
    }

}

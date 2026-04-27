/*
 * src/test/java/io/github/gilacc/snap/node/NumberNodeTest.java - Test suite for NumberNode
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

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;

import static org.hamcrest.Matchers.*;
import static org.hamcrest.MatcherAssert.*;

class NumberNodeTest {

    @Test
    void itCorrectlySerializesBigDecimal() {
        final Number value = new BigDecimal("1.234567890123");
        final NumberNode node = new NumberNode(value);
        final DecimalFormat format = new DecimalFormat(
            "#.##################",
            new DecimalFormatSymbols(Locale.ENGLISH)
        );
        assertThat(node.serialize(new ToStringSerializer()), equalTo(format.format(value)));
    }

}

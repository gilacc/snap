/*
 * src/test/java/io/github/gilacc/snap/demo/ToStringSerializer.java - Dummy serializer that converts to string
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

package io.github.gilacc.snap.demo;

import io.github.gilacc.snap.node.Node;
import io.github.gilacc.snap.serialization.Serializer;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

/**
 * <p>Dummy serializer for testing purposes. It simply converts values to strings, mainly by calling
 * {@link Object#toString()} or other equivalent methods.</p>
 *
 * <p>NOTE: Because most {@link Map} implementations do not impose any ordering on their keys, and comparing string
 * representations of maps is tedious and error-prone, the implementation for {@link Serializer#serializeMap(Map)} in
 * this class wraps the input in a {@link TreeMap} to get consistent results. In a real-world scenario where you
 * serialize to an actual data representation format, like JSON or XML, you shouldn't need tricks like these for your
 * tests.</p>
 */
public class ToStringSerializer implements Serializer<String> {

    private final Locale locale;

    /**
     * <p>Constructs the serializer with a default locale (currently {@link Locale#ENGLISH}).</p>
     */
    public ToStringSerializer() {
        this(Locale.ENGLISH);
    }

    /**
     * <p>Constructs the serializer with the given locale.</p>
     *
     * @param locale the locale to use when serializing (particularly relevant for numeric values)
     */
    public ToStringSerializer(final Locale locale) {
        this.locale = locale;
    }

    @Override
    public String serializeNumber(Number number) {
        return new DecimalFormat(
            "#.##################",
            new DecimalFormatSymbols(this.locale)
        ).format(number);
    }

    @Override
    public String serializeBoolean(boolean bool) {
        return Boolean.toString(bool);
    }

    @Override
    public String serializeString(String string) {
        return string;
    }

    @Override
    public String serializeList(List<Node> list) {
        return list.stream()
            .map(element -> element.serialize(this))
            .toList()
            .toString();
    }

    @Override
    public String serializeMap(Map<String, Node> map) {
        return new TreeMap<>(map.entrySet()
            .stream()
            .collect(Collectors.toMap(Map.Entry::getKey, entry -> entry.getValue().serialize(this)))
        ).toString();
    }

    @Override
    public String serializeNil() {
        return "nil";
    }

}

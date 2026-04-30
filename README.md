# &#128165; Snap

[![mvn](https://github.com/gilacc/snap/actions/workflows/maven.yml/badge.svg)](https://github.com/gilacc/snap/actions/workflows/maven.yml)
[![GitHub License](https://img.shields.io/github/license/gilacc/snap)](https://github.com/gilacc/snap/blob/master/LICENSE)
[![Maven Central Version](https://img.shields.io/maven-central/v/io.github.gilacc/snap)](https://central.sonatype.com/artifact/io.github.gilacc/snap)

**Snap** is an experimental Java library for serialization/deserialization, focused on clarity and cohesion.

The central idea is very simple. First, you make your objects create [snapshots](#snapshots) of their own state, and
these snapshot objects. Next, you implement [serializers](#serializers) and [deserializers](#deserializers) that work
with snapshots. This approach requires some extra work but can result in more flexible and robust code.

Snap is pretty much just a basic API, so you also need an implementation for your serializers and deserializers. You
can roll out your own, or use an existing backend:

- [snap-json-jakarta](https://github.com/gilacc/snap-json-jakarta). Currently a work in progress. Uses the
[Jakarta JSON API](https://jakarta.ee/learn/docs/jakartaee-tutorial/current/web/jsonp/jsonp.html).

## A quick example

This minimal example shows how to give a class the ability to create snapshots:

```java
public class Message implements Resource {

    private final String content;

    public Message(final String content) {
        this.content = content;
    }

    @Override
    public Snapshot snapshot() {
        return () -> Map.ofEntries(
            Map.entry("content", new StringNode(this.content))
        );
    }
}
```

Important takeaways from this snippet:

- The `Resource` interface exposes a `snapshot()` method, which creates a snapshot of the object's state. The returned
object implements `Snapshot`.
- The `Snapshot` interface, in turn, exposes a `nodes()` method that provides access to its nodes. The returned object
is a `Map<String, Node>`, and you can process those nodes as you see fit.
- `Node` represents all types of values your snapshot can have. `StringNode` is an implementation of this interface, and
represents a single string value. It's worth mentioning that `Node` is a
[sealed interface](https://docs.oracle.com/en/java/javase/17/language/sealed-classes-and-interfaces.html), so you cannot
create new implementations for it.

Note that we haven't written a single line of serialization logic yet. This is only for modeling a generic snapshot
(i.e. not tied to any particular format). In order to serialize snapshots to a given format, you create a class
implementing `Serializer` that converts snapshots into your preferred format. This only needs to be done once for each
target format, not for each type of resource. For example, consider
[this JSON serializer using the Jakarta JSON API](https://github.com/gilacc/snap-json-jakarta/):

```java
public final class JakartaJsonSerializer implements Serializer<JsonValue> {
    private final JsonBuilderFactory jsonBuilderFactory;

    public JakartaJsonSerializer(final JsonBuilderFactory jsonBuilderFactory) {
        this.jsonBuilderFactory = jsonBuilderFactory;
    }

    @Override
    public JsonValue serializeNumber(final Number number) {
        final String representation = new DecimalFormat(
            "#.##################",
            new DecimalFormatSymbols(Locale.ENGLISH)
        ).format(number);
        return Json.createValue(new BigDecimal(representation));
    }

    @Override
    public JsonValue serializeBoolean(final boolean bool) {
        return (bool) ? JsonValue.TRUE : JsonValue.FALSE;
    }

    @Override
    public JsonValue serializeString(final String string) {
        return Json.createValue(string);
    }

    @Override
    public JsonValue serializeList(final List<Node> list) {
        final JsonArrayBuilder arrayBuilder = this.jsonBuilderFactory.createArrayBuilder();
        list.stream()
            .map(node -> node.serialize(this))
            .forEach(arrayBuilder::add);
        return arrayBuilder.build();
    }

    @Override
    public JsonValue serializeMap(final Map<String, Node> map) {
        final JsonObjectBuilder objectBuilder = this.jsonBuilderFactory.createObjectBuilder();
        map.entrySet().stream()
            .filter(entry -> entry.getValue() != null)
            .forEach(entry -> objectBuilder.add(entry.getKey(), entry.getValue().serialize(this)));
        return objectBuilder.build();
    }

    @Override
    public JsonValue serializeNil() {
        return JsonValue.NULL;
    }
}
```

## Design philosophy

### Snapshots

A snapshot is an object that represents the state of a domain object at a given point. The concept is heavily based on
the [Memento design pattern](https://refactoring.guru/design-patterns/memento). Working with snapshots instead of
polluting your domain classes with serialization logic has two advantages: higher control over how objects are
serialized and stronger encapsulation.

Snapshots are essentially mappings of string keys to node values. There's only six types of nodes: *number*, *boolean*,
*string*, *list*, *map* and *nil*.

These types can be grouped into:

- **Scalars** (number, string and boolean). They represent a single element.
- **Collections** (list and map). They represent a group of elements, which may or may not belong to the same type.
- **Nil**. Represents the absence of value. It's the equivalent of Java's `null`.

Serializing a collection node implies recursively serializing each node until a scalar or nil node is found.

### Serializers

A serializer is an object that transforms individual nodes to a specific type of payload. For example, a JSON serializer
might transform nodes to `JsonValue` objects (Jakarta JSON API) or `JsonNode` (Jackson). Serializers are based on the
[Visitor design pattern](https://refactoring.guru/design-patterns/visitor).

### Deserializers

Conversely, a deserializer is an object that transforms a source payload to a snapshot. Because all snapshots share a
common interface, converting a payload to a snapshot shouldn't require casts, reflection or any other weird tricks.

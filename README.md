# &#128165; Snap

**Snap** is an experimental Java library for serialization/deserialization, focused on clarity and cohesion.

The central idea is very simple. First, you make your objects create [snapshots](#TODO) of their own state, and these
snapshot objects. Next, you implement [serializers](#TODO) and [deserializers](#TODO) that work with snapshots. This
approach requires some extra work but can result in more flexible and robust code.

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
target format, not for each type of resource. For example, this is
[a dummy serializer used in Snap's tests](src/test/java/io/github/gilacc/snap/demo/ToStringSerializer.java), which just
converts nodes to Java strings:

```java
public class ToStringSerializer implements Serializer<String> {
    private final Locale locale;

    public ToStringSerializer(Locale locale) {
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
```

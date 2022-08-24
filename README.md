# ELK Libavoid Examples

This repository contains an example for using the [ELK Libavoid](https://github.com/TypeFox/elk-libavoid) Eclipse plug-in. The example uses one of the Eclipse plug-in templates with a [command handler](https://github.com/TypeFox/elk-libavoid-examples/blob/main/test.plugin/src/test/plugin/handlers/SampleHandler.java).

In this example, layout is invoked via the `RecursiveGraphLayoutEngine`. For other ways to use ELK layouts, see the [ELK documentation](https://www.eclipse.org/elk/documentation/tooldevelopers.html).

The basic usage is very simple:
```java
var engine = new RecursiveGraphLayoutEngine();
var graph = createGraph();
engine.layout(graph, new BasicProgressMonitor());
```
([SampleHandler.java:27-29](https://github.com/TypeFox/elk-libavoid-examples/blob/3569ef5f6edcea0e56ba0bb604ea6295d6e9632c/test.plugin/src/test/plugin/handlers/SampleHandler.java#L27-L29))

The following steps are important when creating the graph:

 - Set the [`ALGORITHM` option](https://github.com/TypeFox/elk-libavoid-examples/blob/3569ef5f6edcea0e56ba0bb604ea6295d6e9632c/test.plugin/src/test/plugin/handlers/SampleHandler.java#L53) of the graph to `"de.cau.cs.kieler.kiml.libavoid"`
 - Assign [positions and sizes](https://github.com/TypeFox/elk-libavoid-examples/blob/3569ef5f6edcea0e56ba0bb604ea6295d6e9632c/test.plugin/src/test/plugin/handlers/SampleHandler.java#L57-L58) to all nodes and ports
 - Set the [`PORT_CONSTRAINTS` option](https://github.com/TypeFox/elk-libavoid-examples/blob/3569ef5f6edcea0e56ba0bb604ea6295d6e9632c/test.plugin/src/test/plugin/handlers/SampleHandler.java#L59) of nodes to `FIXED_POS`
 - Set the [`PORT_SIDE` option](https://github.com/TypeFox/elk-libavoid-examples/blob/3569ef5f6edcea0e56ba0bb604ea6295d6e9632c/test.plugin/src/test/plugin/handlers/SampleHandler.java#L65) of every port to one of the values `NORTH`, `EAST`, `SOUTH` or `WEST`

The resulting edge routing can be read from the [edge layout sections](https://github.com/TypeFox/elk-libavoid-examples/blob/3569ef5f6edcea0e56ba0bb604ea6295d6e9632c/test.plugin/src/test/plugin/handlers/SampleHandler.java#L30-L31).

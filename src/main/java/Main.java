import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.io.Output;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.openjdk.jol.info.GraphLayout;
import swienkraam.*;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class Main {

    private static final String XML = "Strassenbaumkataster_2015.gml";
//    private static final String XML = "small.xml";

    public static void main(final String[] args) throws IOException {
        final List<HamburgStreetTree> objectTrees = loadTreesInto(HamburgStreetTreeObjectImpl.class, XML, new ArrayList<>());
        final List<HamburgStreetTree> byteTrees = loadTreesInto(HamburgStreetTreeByteImpl.class, XML, new ArrayList<>());
        final List<HamburgStreetTree> snappyTrees = loadTreesInto(HamburgStreetTreeSnappyImpl.class, XML, new ArrayList<>());
        final List<HamburgStreetTree> lz4FastTrees = loadTreesInto(HamburgStreetTreeLz4FastImpl.class, XML, new ArrayList<>());
        final List<HamburgStreetTree> lz4HighTrees = loadTreesInto(HamburgStreetTreeLz4HighImpl.class, XML, new ArrayList<>());
//        final List<HamburgStreetTree> kryoTrees = loadTreesInto(HamburgStreetTreeKyroImpl.class, XML, new ArrayList<>());
        final List<HamburgStreetTree> protoTrees = loadTreesInto(HamburgStreetTreeProtobufImpl.class, XML, new ArrayList<>());

        // check if toString() produces the same result
        for (int i = 0; i < objectTrees.size(); i++) {
            if (!objectTrees.get(i).equals(byteTrees.get(i))
                    || !objectTrees.get(i).equals(protoTrees.get(i))
                    || !objectTrees.get(i).equals(snappyTrees.get(i))
                    || !objectTrees.get(i).equals(lz4FastTrees.get(i))
                    || !objectTrees.get(i).equals(lz4HighTrees.get(i))) {
                System.out.println(objectTrees.get(i).toString().replace(HamburgStreetTreeObjectImpl.class.getSimpleName(), ""));
                System.out.println(byteTrees.get(i).toString().replace(HamburgStreetTreeByteImpl.class.getSimpleName(), ""));
                System.out.println(snappyTrees.get(i).toString().replace(HamburgStreetTreeSnappyImpl.class.getSimpleName(), ""));
                System.out.println(lz4FastTrees.get(i).toString().replace(HamburgStreetTreeLz4FastImpl.class.getSimpleName(), ""));
                System.out.println(lz4HighTrees.get(i).toString().replace(HamburgStreetTreeLz4HighImpl.class.getSimpleName(), ""));
//                System.out.println(kryoTrees.get(i).toString().replace(HamburgStreetTreeKyroImpl.class.getSimpleName(), ""));
                System.out.println(protoTrees.get(i).toString().replace(HamburgStreetTreeProtobufImpl.class.getSimpleName(), ""));
                throw new RuntimeException("Byte tree impl does not match object tree impl.");
            }
        }

        printSize(objectTrees);
        printSize(byteTrees);
        printSize(snappyTrees);
        printSize(lz4FastTrees);
        printSize(lz4HighTrees);
//        printSize(kryoTrees);
        printSize(protoTrees);

    }

    /**
     * Load the "Straßenbaumkataster" from the given file into the given list
     * using the specified {@link HamburgStreetTree} implementation.
     */
    private static List<HamburgStreetTree> loadTreesInto(final Class<? extends HamburgStreetTree> treeClass,
                                                         final String sourceFileName,
                                                         final List<HamburgStreetTree> targetList) throws IOException {
        final URL gml = Main.class.getClassLoader().getResource(sourceFileName);
        final ObjectMapper xmlMapper = new XmlMapper()
                .registerModule(new JavaTimeModule());

        // some transformations on the json tree to make annotation mapping easier
        final MappingIterator<JsonNode> treeIterator = xmlMapper.readerFor(JsonNode.class).readValues(gml);
        while (treeIterator.hasNext()) {
            final JsonNode possibleTreeNode = treeIterator.next().get("strassenbaumkataster_Point");
            if (possibleTreeNode != null && possibleTreeNode instanceof ObjectNode) {
                final ObjectNode treeNode = (ObjectNode) possibleTreeNode;
                final ObjectNode pointNode = (ObjectNode) treeNode.get("pointProperty").get("Point");
                treeNode.set("srsName", pointNode.get("srsName"));
                treeNode.set("srsDimension", pointNode.get("srsDimension"));
                treeNode.set("pos", pointNode.get("pos"));
                treeNode.remove("pointProperty");

                // no null support yet
                treeNode.fields().forEachRemaining(jsonNodeEntry -> {
                    if (jsonNodeEntry.getValue().textValue() == null) {
                        treeNode.put(jsonNodeEntry.getKey(), "");
                    }
                });

                targetList.add(xmlMapper.treeToValue(treeNode, treeClass));
            }
        }

        return targetList;
    }

    private static void printSize(final List<HamburgStreetTree> trees) {
        System.out.println("-------------------- " + trees.get(0).getClass().getSimpleName() + " --------------------");
        final GraphLayout state = GraphLayout.parseInstance(trees);
        long bytes = state.totalSize();
        System.out.println(BenchUtils.getFormattedSize(bytes) + " " + BenchUtils.getFormattedUnit(bytes));
//        System.out.println(state.toFootprint());
    }

}

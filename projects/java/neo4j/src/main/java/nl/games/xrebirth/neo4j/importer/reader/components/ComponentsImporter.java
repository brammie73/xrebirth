package nl.games.xrebirth.neo4j.importer.reader.components;

import nl.games.xrebirth.neo4j.importer.reader.ImportContext;
import nl.games.xrebirth.neo4j.importer.reader.XRRelationshipType;
import org.apache.commons.vfs2.FileObject;
import org.neo4j.graphdb.*;

import javax.xml.namespace.QName;
import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.Attribute;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Created with IntelliJ IDEA.
 * User: bram
 * Date: 27-12-13
 * Time: 16:01
 * To change this template use File | Settings | File Templates.
 */
public class ComponentsImporter {
    public static final QName Q_COMPONENTS = new QName("components");
    public static final QName Q_COMPONENT = new QName("component");
    public static final QName Q_CONNECTIONS = new QName("connections");
    public static final QName Q_CONNECTION = new QName("connection");

    public static Label COMPONENT = DynamicLabel.label("component");

    ImportContext context;
    Map<String, String> index;

    public ComponentsImporter(ImportContext context) {
        this.context = context;

    }

    public void doImport() {
        this.index = context.getIndex().getComponents();
        Set<String> fileSet = new HashSet<String>();
        fileSet.addAll(index.values());
        for (String entry : fileSet) {
            if (entry.startsWith("assets\\test")) {
                continue;
            }
            try {
                FileObject fo = context.getRoot().resolveFile(entry + ".xml");
                if (fo != null) {
                    doImport(fo.getContent().getInputStream());
                }
            } catch (IOException e) {
                System.err.println("file not found:" + entry);
            } catch (XMLStreamException e) {
                System.err.println("invalid xml in:" + entry);
            }
        }
    }


    public void doImport(InputStream in) throws IOException, XMLStreamException {
        GraphDatabaseService service = context.getDatabaseService();
        try (Transaction tx = service.beginTx()) {
            //create "space" it isnt defined anywhere
            Node space = findComponentNode("standaardspace", "space");
            XMLInputFactory factory = XMLInputFactory.newInstance();
            XMLEventReader reader = factory.createXMLEventReader(in);
            while (reader.hasNext()) {
                XMLEvent e = reader.nextEvent();
                if (e.getEventType() == XMLEvent.START_ELEMENT) {
                    StartElement elm = e.asStartElement();
                    if ("component".equals(elm.getName().getLocalPart())) {
                        Attribute name = elm.getAttributeByName(new QName("name"));
                        Attribute classAttribute = elm.getAttributeByName(new QName("class"));
                        String className = "component";
                        if (classAttribute != null) {
                            className = classAttribute.getValue();
                        }
                        Node node = findOrCreate(name.getValue(), className);
                        //todo rest of the attributes
                    }
                }
            }
            tx.success();
        }
    }

    public void doImportConnections(InputStream in) throws IOException, XMLStreamException {
        GraphDatabaseService service = context.getDatabaseService();
        try (Transaction tx = service.beginTx()) {
            XMLInputFactory factory = XMLInputFactory.newInstance();
            XMLEventReader reader = factory.createXMLEventReader(in);
            Node currentComponent = null;
            while (reader.hasNext()) {
                XMLEvent e = reader.nextEvent();
                if (e.getEventType() == XMLEvent.START_ELEMENT) {
                    StartElement elm = e.asStartElement();
                    if ("component".equals(elm.getName().getLocalPart())) {
                        Attribute name = elm.getAttributeByName(new QName("name"));
                        Attribute classAttribute = elm.getAttributeByName(new QName("class"));
                        String className = "component";
                        if (classAttribute != null) {
                            className = classAttribute.getValue();
                        }
                        currentComponent = findComponentNode(name.getValue(), className);

                    } else if ("connection".equals(elm.getName().getLocalPart())) {
                        String name = elm.getAttributeByName(new QName("name")).getValue();
                        String tag = elm.getAttributeByName(new QName("tag")).getValue();


                    }
                }
            }
            tx.success();
        }
    }


    Node findOrCreate(String name, String className) {
        Node node = findComponentNode(name, className);
        if (node == null) {
            node = createComponentNode(name, className);
        }
        return node;
    }


    Node findComponentNode(String name, String className) {
        ResourceIterable<Node> iterable  = context.getDatabaseService().findNodesByLabelAndProperty(COMPONENT, "name", name);
        for (Node node : iterable) {
            if (node.getProperty("class").equals(className)) {
                return node;
            }
        }
        return null;
    }

    Node createComponentNode(String name, String className) {
        Node node = context.getDatabaseService().createNode();
        node.addLabel(COMPONENT);
        node.setProperty("name", name);
        node.setProperty("class", className);
        Node root = context.findRootNode(COMPONENT.name());
        root.createRelationshipTo(node, XRRelationshipType.IS);
        return node;
    }

}

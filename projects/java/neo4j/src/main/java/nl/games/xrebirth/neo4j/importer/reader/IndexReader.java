package nl.games.xrebirth.neo4j.importer.reader;


import org.apache.commons.vfs2.FileObject;
import org.apache.commons.vfs2.FileSystemManager;
import org.apache.commons.vfs2.VFS;

import javax.xml.namespace.QName;
import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: bram
 * Date: 27-12-13
 * Time: 2:10
 * To change this template use File | Settings | File Templates.
 */
public class IndexReader {

    public static final QName Q_NAME = new QName("name");
    public static final QName Q_VALUE = new QName("value");
    public static final QName Q_ENTRY = new QName("entry");
    XMLInputFactory factory = XMLInputFactory.newInstance();

    private Map<String, String> components = new HashMap<String, String>();
    private Map<String, String> macros = new HashMap<String, String>();



    public IndexReader() {
        components = new HashMap<String, String>();
        macros = new HashMap<String, String>();
    }


    public Map<String, String> getComponents() {
        return components;
    }

    public Map<String, String> getMacros() {
        return macros;
    }

    public void read(FileObject root)  {
        try {
            components = readFile(root.getChild("index").getChild("components.xml"));
            macros = readFile(root.getChild("index").getChild("macros.xml"));
        } catch (Exception e) {
            throw new ImportException(e);
        }
    }


    private Map<String, String> readFile(FileObject file) throws IOException, XMLStreamException {
        Map<String, String> index = new HashMap<String, String>();
        InputStream in = file.getContent().getInputStream();
        XMLEventReader reader = factory.createXMLEventReader(in);
        while (reader.hasNext()) {
            XMLEvent e = reader.nextEvent();
            if (e.getEventType() == XMLEvent.START_ELEMENT) {
                StartElement elm = e.asStartElement();
                if (Q_ENTRY.equals(elm.getName())) {
                    index.put(elm.getAttributeByName(Q_NAME).getValue(), elm.getAttributeByName(Q_VALUE).getValue().toLowerCase());
                }
            }
        }
        return index;
    }


    public static void main(String[] args) throws Exception {
        FileSystemManager manager  = VFS.getManager();
        FileObject root = manager.resolveFile("filesystem://J:/games/X Rebirth");
        IndexReader indexReader = new IndexReader();
        indexReader.read(root);
        System.err.println("jo");
    }

}

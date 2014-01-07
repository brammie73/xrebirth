package nl.games.xrebirth.neo4j.importer.reader;

import nl.games.xrebirth.generated.components.Components;
import nl.games.xrebirth.neo4j.importer.ImportContext;

/**
 * Author: bram
 * Date: 6-1-14
 * Time: 18:52
 */
public class ComponentReader extends AbstractJaxbXmlReader<Components> {


    @Override
    public Components doRead(ImportContext importContext, String file) {
        return super.doRead(importContext, file);
    }
}

package nl.games.xrebirth.neo4j.importer.reader;

import nl.games.xrebirth.generated.components.Components;
import nl.games.xrebirth.generated.macros.MacrosType;
import nl.games.xrebirth.neo4j.importer.ImportContext;

/**
 * Author: bram
 * Date: 7-1-14
 * Time: 2:01
 */
public class MacrosReader extends AbstractJaxbXmlReader<MacrosType> {

    @Override
    public MacrosType doRead(ImportContext importContext, String file) {
        System.err.println("reading:" + file);
        return super.doRead(importContext, file);
    }

}

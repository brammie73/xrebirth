package nl.games.xrebirth.neo4j.importer.events;

import nl.games.xrebirth.generated.AbstractElement;

/**
 * Author: bram
 * Date: 7-1-14
 * Time: 16:28
 */
public class FileEvent {

    private final Class<? extends AbstractElement> clazz;
    private final String file;

    public FileEvent(Class<? extends AbstractElement> clazz, String file) {
        this.file = file;
        this.clazz = clazz;
    }

    public String getFile() {
        return file;
    }

    public Class<? extends AbstractElement> getClazz() {
        return clazz;
    }
}

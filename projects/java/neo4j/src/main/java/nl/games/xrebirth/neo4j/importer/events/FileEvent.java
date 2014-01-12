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
    private String name;
    private Object object;

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

    public Object getObject() {
        return object;
    }

    public void setObject(Object object) {
        this.object = object;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

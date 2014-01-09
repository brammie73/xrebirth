package nl.games.xrebirth.neo4j.importer.writer;

import nl.games.xrebirth.generated.components.ComponentType;
import nl.games.xrebirth.generated.components.Components;
import nl.games.xrebirth.generated.components.ConnectionType;
import nl.games.xrebirth.neo4j.importer.ImportContext;
import nl.games.xrebirth.neo4j.importer.db.PropertyBuilder;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Author: bram
 * Date: 6-1-14
 * Time: 18:56
 */
@Singleton
public class ComponentWriter extends AbstractNeo4jWriter<Components> {


    @Inject
    ImportContext importContext;

    @Override
    public void doWrite(Components components) {
        for (ComponentType componentType : components.getComponent()) {
            importContext.getService().createNode(componentType,
                    PropertyBuilder.create(componentType).add("id", componentType.getName())
            );
            for (ConnectionType connectionType : componentType.getConnections().getConnection()) {
                String temp = connectionType.getTags();
                if (temp == null) {
                    temp = "*";
                }
                String[] tags = temp.split(" ");
            }
        }
    }
}

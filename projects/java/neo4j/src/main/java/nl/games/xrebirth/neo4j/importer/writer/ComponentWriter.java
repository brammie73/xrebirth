package nl.games.xrebirth.neo4j.importer.writer;

import nl.games.xrebirth.generated.components.ComponentType;
import nl.games.xrebirth.generated.components.ComponentsType;
import nl.games.xrebirth.generated.components.ConnectionType;
import nl.games.xrebirth.neo4j.importer.ImportContext;
import nl.games.xrebirth.neo4j.importer.db.PropertyBuilder;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.enterprise.event.Observes;
import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Author: bram
 * Date: 6-1-14
 * Time: 18:56
 */
@Singleton
public class ComponentWriter extends AbstractNeo4jWriter<ComponentsType> {

    Logger log = LogManager.getLogger();

    @Inject
    ImportContext importContext;

    @Override
    public void doWrite( ComponentsType components) {
        for (ComponentType componentType : components.getComponent()) {
            importContext.getService().createNode(
                    componentType,
                    PropertyBuilder.create(componentType).add("id", componentType.getName())
            );
            for (ConnectionType connectionType : componentType.getConnections().getConnection()) {
                String temp = connectionType.getTags();
                if (temp == null) {
                    temp = "*";
                }
                String[] tags = temp.split(" ");
                PropertyBuilder builder = PropertyBuilder.create().add("id", temp);
                builder.add("tag", tags);
                importContext.getService().createNode(connectionType, builder);
                importContext.getService().createRelationship(componentType,
                        connectionType,
                        PropertyBuilder.create()
                                .add("value", connectionType.getValue())
                                .add("optional", connectionType.getOptional())
                );
            }
        }
    }

}

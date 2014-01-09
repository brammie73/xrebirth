package nl.games.xrebirth.neo4j.importer.writer;

import nl.games.xrebirth.generated.races.Race;
import nl.games.xrebirth.generated.races.Races;
import nl.games.xrebirth.generated.races.Relation;
import nl.games.xrebirth.neo4j.importer.ImportContext;
import nl.games.xrebirth.neo4j.importer.db.PropertyBuilder;

import javax.enterprise.event.Observes;
import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Created with IntelliJ IDEA.
 * User: bram
 * Date: 29-12-13
 * Time: 1:58
 * To change this template use File | Settings | File Templates.
 */
@Singleton
public class RacesWriter extends AbstractNeo4jWriter<Races> {

    @Inject
    ImportContext importContext;

    @Override
    public void doWrite(@Observes Races races) {
        for (Race race : races.getRace()) {
            importContext.getService().createNode(
                    race,
                    PropertyBuilder.create(race)
            );
            if (race.getRelations() != null) {
                for (Relation relation : race.getRelations().getRelation()) {
                    Race toRace = createElement(Race.class);
                    toRace.setId(relation.getRace().enumValue());
                    importContext.getService().createRelationship(
                            race,
                            toRace,
                            PropertyBuilder.create().add("value", relation.getRelation())
                    );
                }
            }
        }
    }
}

package nl.games.xrebirth.neo4j.importer.writer;

import nl.games.xrebirth.generated.factions.Faction;
import nl.games.xrebirth.generated.factions.Factions;
import nl.games.xrebirth.generated.factions.Relation;
import nl.games.xrebirth.generated.races.Race;
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
 */

@Singleton
public class FactionsWriter extends AbstractNeo4jWriter<Factions> {

    @Inject
    ImportContext importContext;

    @Override
    public void doWrite(@Observes Factions factions) {
        for (Faction faction : factions.getFaction()) {
            importContext.getService().createNode(
                    faction,
                    PropertyBuilder.create(faction)
            );
            if (faction.getPrimaryrace() != null) {
                Race race = new Race();
                race.setId(faction.getPrimaryrace().enumValue());
                importContext.getService().createRelationship(
                        faction,
                        race,
                        PropertyBuilder.create()
                );
            }
            if (faction.getRelations() != null) {
                for (Relation relation : faction.getRelations().getRelation()) {
                    Faction relationFaction = new Faction();
                    relationFaction.setId(relation.getFaction());
                    importContext.getService().createRelationship(
                            faction,
                            relationFaction,
                            PropertyBuilder.create().add("value", relation.getRelation())
                    );
                }
            }
        }
    }
}

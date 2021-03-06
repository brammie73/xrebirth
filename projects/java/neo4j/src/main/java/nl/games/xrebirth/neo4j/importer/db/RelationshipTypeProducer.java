package nl.games.xrebirth.neo4j.importer.db;

import com.google.common.collect.ImmutableTable;
import com.google.common.collect.Table;
import nl.games.xrebirth.generated.AbstractElement;
import nl.games.xrebirth.generated.macros.MacroType;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.neo4j.graphdb.DynamicRelationshipType;
import org.neo4j.graphdb.RelationshipType;

import javax.inject.Singleton;

/**
 * Author: bram
 * Date: 6-1-14
 * Time: 13:36
 */
@Singleton
public class RelationshipTypeProducer {

    private static final Logger log = LogManager.getLogger();

    static final Table<Class<? extends AbstractElement>, Class<? extends AbstractElement>, String> table =
            ImmutableTable.<Class<? extends AbstractElement>, Class<? extends AbstractElement>, String>builder()
                    .put(nl.games.xrebirth.generated.races.Race.class, nl.games.xrebirth.generated.races.Race.class, "racerelation")
                    .put(nl.games.xrebirth.generated.factions.Faction.class, nl.games.xrebirth.generated.races.Race.class, "primaryrace")
                    .put(nl.games.xrebirth.generated.factions.Faction.class, nl.games.xrebirth.generated.factions.Faction.class, "relation")
                    .put(nl.games.xrebirth.generated.wares.WareType.class, nl.games.xrebirth.generated.wares.ProductionType.class, "produced by")
                    .put(nl.games.xrebirth.generated.wares.ProductionType.class, nl.games.xrebirth.generated.wares.WareType.class, "produces")
                    .put(nl.games.xrebirth.generated.wares.WareType.class, nl.games.xrebirth.generated.wares.ProductionType.Primary.class, "primary")
                    .put(nl.games.xrebirth.generated.wares.WareType.class, nl.games.xrebirth.generated.wares.ProductionType.Secondary.class, "secondairy")
                    .put(nl.games.xrebirth.generated.components.ComponentType.class, nl.games.xrebirth.generated.components.ConnectionType.class, "connection")
                    .put(nl.games.xrebirth.generated.macros.MacroType.class, nl.games.xrebirth.generated.macros.ConnectionType.class, "connection")
                    .build();

    public RelationshipType produce(AbstractElement from, AbstractElement to) {
        if ((from instanceof MacroType) && (to instanceof  MacroType)) {
            MacroType fromType = (MacroType)from;
            MacroType toType = (MacroType)to;
            if (fromType.getClazz() != null && fromType.getClazz().equals(toType.getConnection())) {
                return DynamicRelationshipType.withName(fromType.getClazz());
            } else if (toType.getClazz() != null && toType.getClazz().equals(fromType.getConnection())) {
                return DynamicRelationshipType.withName(toType.getClazz());
            } else {
                StringBuilder builder = new StringBuilder();
                if (fromType.getClazz() != null)
                    builder.append(fromType.getClazz());
                if (fromType.getConnection() != null)
                    builder.append(fromType.getConnection());
                if (toType.getConnection() != null)
                    builder.append(toType.getConnection());
                if (toType.getClazz() != null)
                    builder.append(toType.getClazz());
                return DynamicRelationshipType.withName(builder.toString());
            }
        }
        String name = table.get(from.getClass(), to.getClass());
        if (name == null) {
            name = "fixme";
            log.info("fix relation name for: {}.class, {}.class", from.getClass().getName(), to.getClass().getName());
        }
        return DynamicRelationshipType.withName(name);
    }


    public RelationshipType produce(AbstractElement element) {
        return produce(element.getClass());
    }

    public RelationshipType produce(Class<? extends AbstractElement> clazz) {
        return DynamicRelationshipType.withName(clazz.getSimpleName().toLowerCase());
    }

}

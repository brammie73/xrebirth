package nl.games.xrebirth.neo4j.importer.writer;

import nl.games.xrebirth.generated.AbstractElement;

/**
 * User: bram
 * Date: 12-1-14
 * Time: 19:45
 */
public class TypeLookup {

    public static AbstractElement find(AbstractElement element)  {
        if (element.getClass() == nl.games.xrebirth.generated.wares.ComponentType.class) {
            String ref = ((nl.games.xrebirth.generated.wares.ComponentType)element).getRef();
            if (ref.endsWith("macro")) {
                nl.games.xrebirth.generated.macros.MacroType type = new nl.games.xrebirth.generated.macros.MacroType();
                type.setName(ref);
                type.setRef(ref);
                return type;
            }  else {
                nl.games.xrebirth.generated.components.ComponentType type = new nl.games.xrebirth.generated.components.ComponentType();
                type.setName(ref);
                return type;
            }
        }
        throw new IllegalArgumentException("todo");
    }


}

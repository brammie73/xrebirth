package nl.games.xrebirth.neo4j.importer.events;

import nl.games.xrebirth.generated.components.ComponentType;
import nl.games.xrebirth.generated.macros.MacroType;

/**
 * Created with IntelliJ IDEA.
 * User: bram
 * Date: 11-1-14
 * Time: 16:17
 * To change this template use File | Settings | File Templates.
 */
public class MacroEvent {

    MacroType macroType;
    ComponentType component;

    public MacroType getMacroType() {
        return macroType;
    }

    public void setMacroType(MacroType macroType) {
        this.macroType = macroType;
    }

    public ComponentType getComponent() {
        return component;
    }

    public void setComponent(ComponentType component) {
        this.component = component;
    }
}

package nl.games.xrebirth.neo4j.importer.events;

import nl.games.xrebirth.generated.AbstractElement;
import nl.games.xrebirth.generated.macros.ReferenceType;

/**
 * Created with IntelliJ IDEA.
 * User: bram
 * Date: 9-1-14
 * Time: 18:33
 * To change this template use File | Settings | File Templates.
 */
public class ReferenceHolderEvent {

    private ReferenceType  referenceType;
    private AbstractElement element;

    public ReferenceHolderEvent(ReferenceType referenceType) {
        this.referenceType = referenceType;
    }

    public ReferenceType getReferenceType() {
        return referenceType;
    }

    public AbstractElement getElement() {
        return element;
    }

    public void setElement(AbstractElement element) {
        this.element = element;
    }
}

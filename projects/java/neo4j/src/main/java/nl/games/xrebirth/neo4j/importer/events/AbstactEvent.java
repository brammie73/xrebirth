package nl.games.xrebirth.neo4j.importer.events;

/**
 * Created with IntelliJ IDEA.
 * User: bram
 * Date: 12-1-14
 * Time: 14:30
 * To change this template use File | Settings | File Templates.
 */
public class AbstactEvent {

    private boolean handeled;

    void handle() {
        this.handeled = true;
    }

    public boolean isHandeled() {
        return handeled;
    }
}

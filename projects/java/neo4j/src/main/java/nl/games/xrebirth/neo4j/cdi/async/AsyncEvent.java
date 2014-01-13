package nl.games.xrebirth.neo4j.cdi.async;

import java.util.concurrent.Future;

/**
 * User: bram
 * Date: 12-1-14
 * Time: 18:36
 */
public class AsyncEvent {

    private Future<? extends AsyncEvent> future;

    public <T extends  AsyncEvent>  Future<T> getFuture() {
        return (Future<T>)future;
    }

    void setFuture(Future<? extends AsyncEvent> future) {
        this.future = future;
    }
}

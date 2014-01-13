package nl.games.xrebirth.neo4j.cdi.async;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Event;
import javax.enterprise.event.Observes;
import javax.inject.Inject;

/**
* User: bram
* Date: 12-1-14
* Time: 15:17
*/
@ApplicationScoped
public class EventBus {

    @Inject
    Event<TestEvent> eventBus;


    public TestEvent fire() {
        TestEvent e = new TestEvent();
        eventBus.fire(e);
        return e;
    }



    public static class TestEvent extends AsyncEvent {
        final long create = Thread.currentThread().getId();
        long handle;
        boolean handled = false;

        void  handleIt()  {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            handle  = Thread.currentThread().getId();
            handled  = true;
        }

        boolean valid() {
            return handle != create;
        }

        public boolean isHandled() {
            return handled;
        }
    }

    @ApplicationScoped
    public static class Listener {

        @Asynchronous
        public EventBus.TestEvent handle(@Observes EventBus.TestEvent e) {
            e.handleIt();
            return e;
        }

    }
}

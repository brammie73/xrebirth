package nl.games.xrebirth.neo4j.cdi.async;

import org.apache.deltaspike.cdise.api.CdiContainer;
import org.apache.deltaspike.cdise.api.CdiContainerLoader;
import org.apache.deltaspike.cdise.api.ContextControl;
import org.apache.deltaspike.core.api.provider.BeanProvider;
import org.testng.annotations.Test;

import javax.enterprise.context.ApplicationScoped;
import java.util.concurrent.Future;

import static org.testng.Assert.*;

/**
 * User: bram
 * Date: 12-1-14
 * Time: 15:03
 */
public class AsynchronousInterceptorTest {

    @Test
    public void testAsyncEvent() throws Exception  {

        CdiContainer container = CdiContainerLoader.getCdiContainer();
        container.boot();
        ContextControl contextControl = container.getContextControl();
        contextControl.startContext(ApplicationScoped.class);
        EventBus eventBus = BeanProvider.getContextualReference(EventBus.class);
        EventBus.TestEvent e= eventBus.fire();
        assertFalse(e.isHandled());
        Future<EventBus.TestEvent> f = e.getFuture();
        EventBus.TestEvent tmp = f.get();
        assertTrue(e.isHandled());
        assertTrue(e.valid());
        contextControl.stopContexts();
        container.shutdown();
    }




}

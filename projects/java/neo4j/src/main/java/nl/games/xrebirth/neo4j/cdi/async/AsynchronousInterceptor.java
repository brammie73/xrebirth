package nl.games.xrebirth.neo4j.cdi.async;

import javax.inject.Inject;
import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;
import java.util.concurrent.Callable;
import java.util.concurrent.Future;

/**
 * User: bram
 * Date: 12-1-14
 * Time: 14:52
 */

@Asynchronous
@Interceptor
public class AsynchronousInterceptor {

    private static ThreadLocal<Object> hack = new ThreadLocal<>();

    @Inject
    private AsynchronousService asynchronousService;

    @AroundInvoke
    public Object invokeAsynchronously(final InvocationContext ctx) throws Exception {
        // we assume AsynchronousInterceptor is the first in chain
        if (hack.get() != null){
            return ctx.proceed();
        }
        Callable<Object> callable = new Callable<Object>() {
            @Override
            public Object call() throws Exception {
                hack.set(new Object());
                try {
                    Object bla = ctx.getMethod().invoke(ctx.getTarget(), ctx.getParameters());
                    return bla;

                } finally {
                    hack.remove();
                }
            }
        };
        Future future = asynchronousService.submit(callable);
        Object[]  arr =  ctx.getParameters();
        for (Object o : arr) {
            if (o instanceof AsyncEvent) {
                ((AsyncEvent)o).setFuture(future);
            }
        }
        return null;
    }
}

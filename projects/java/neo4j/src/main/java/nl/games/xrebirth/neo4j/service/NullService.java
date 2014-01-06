package nl.games.xrebirth.neo4j.service;

import nl.games.xrebirth.neo4j.importer.ImportContext;
import nl.games.xrebirth.neo4j.importer.Importer;
import org.apache.deltaspike.cdise.api.CdiContainer;
import org.apache.deltaspike.cdise.api.CdiContainerLoader;
import org.apache.deltaspike.cdise.api.ContextControl;
import org.apache.deltaspike.core.api.provider.BeanProvider;
import org.apache.deltaspike.servlet.api.literal.InitializedLiteral;
import org.neo4j.graphdb.GraphDatabaseService;
import org.neo4j.graphdb.factory.GraphDatabaseFactory;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Observes;
import javax.inject.Singleton;
import java.util.List;

/**
 * Author: bram
 * Date: 6-1-14
 * Time: 6:11
 */

@Singleton
public class NullService {

    private static final String DB_PATH = "neo4j-store";

    public NullService() {
        super();    //To change body of overridden methods use File | Settings | File Templates.
    }

    public static void main(String[] args) {
        GraphDatabaseService graphDb;
        graphDb = new GraphDatabaseFactory().newEmbeddedDatabase(DB_PATH);
        CdiContainer container = CdiContainerLoader.getCdiContainer();
        container.boot();
        ContextControl contextControl = container.getContextControl();
        contextControl.startContext(ApplicationScoped.class);
        ImportContext importContext = BeanProvider.getContextualReference(ImportContext.class);
        importContext.setDatabaseService(graphDb);
        container.getBeanManager().fireEvent(importContext, InitializedLiteral.INSTANCE);
        contextControl.stopContexts();
        graphDb.shutdown();
        container.shutdown();
    }


    public void onStart(@Observes ImportContext importContext) {
        List<Importer> importers = BeanProvider.getContextualReferences(Importer.class, true);
        for (Importer importer : importers) {
            if (!importer.isImported()) {
                importer.doImport(importContext);
            }
        }
    }


}

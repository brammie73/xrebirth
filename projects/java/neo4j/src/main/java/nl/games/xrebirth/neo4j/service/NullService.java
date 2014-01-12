package nl.games.xrebirth.neo4j.service;

import nl.games.xrebirth.neo4j.importer.ImportContext;
import nl.games.xrebirth.neo4j.importer.Importer;
import nl.games.xrebirth.neo4j.importer.db.Neo4jService;
import nl.games.xrebirth.neo4j.importer.importers.ComponentsImporter;
import nl.games.xrebirth.neo4j.importer.importers.GenericImporter;
import nl.games.xrebirth.neo4j.importer.importers.MacrosImporter;
import org.apache.commons.io.FileUtils;
import org.apache.deltaspike.cdise.api.CdiContainer;
import org.apache.deltaspike.cdise.api.CdiContainerLoader;
import org.apache.deltaspike.cdise.api.ContextControl;
import org.apache.deltaspike.core.api.provider.BeanProvider;
import org.apache.deltaspike.servlet.api.literal.InitializedLiteral;
import org.neo4j.graphdb.GraphDatabaseService;
import org.neo4j.unsafe.batchinsert.BatchInserters;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Observes;
import javax.inject.Singleton;
import java.io.File;

/**
 * Author: bram
 * Date: 6-1-14
 * Time: 6:11
 */

@Singleton
public class NullService {

    private static final String DB_PATH = "neo4j-store";

    public NullService() {
        super();
    }

    public static void main(String[] args) throws Exception {
        File file = new File(DB_PATH);
        if (file.exists()) {
            FileUtils.deleteDirectory(file);
        }

        GraphDatabaseService graphDb;
        //graphDb = new GraphDatabaseFactory().newEmbeddedDatabaseBuilder(DB_PATH).newGraphDatabase();
        graphDb = BatchInserters.batchDatabase(DB_PATH);
        CdiContainer container = CdiContainerLoader.getCdiContainer();
        container.boot();
        ContextControl contextControl = container.getContextControl();
        contextControl.startContext(ApplicationScoped.class);
        Neo4jService neo4jService = BeanProvider.getContextualReference(Neo4jService.class);
        neo4jService.setDatabaseService(graphDb);
        ImportContext importContext = BeanProvider.getContextualReference(ImportContext.class);
        importContext.init();
        container.getBeanManager().fireEvent(importContext, InitializedLiteral.INSTANCE);
        contextControl.stopContexts();
        graphDb.shutdown();
        container.shutdown();
    }


    public void onStart(@Observes ImportContext importContext) {
        //List<Importer> importers = BeanProvider.getContextualReferences(Importer.class, true);
        Importer importer = BeanProvider.getContextualReference(GenericImporter.class);
        //importer.doImport();
        importer = BeanProvider.getContextualReference(ComponentsImporter.class);
        //importer.doImport();
        importer = BeanProvider.getContextualReference(MacrosImporter.class);
        importer.doImport();
    }


}

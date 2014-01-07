package nl.games.xrebirth.neo4j.importer.importers;

import nl.games.xrebirth.generated.macros.MacroType;
import nl.games.xrebirth.generated.macros.MacrosType;
import nl.games.xrebirth.neo4j.importer.ImportContext;
import nl.games.xrebirth.neo4j.importer.events.Reference;
import nl.games.xrebirth.neo4j.importer.reader.IndexReader;
import nl.games.xrebirth.neo4j.importer.reader.MacrosReader;
import nl.games.xrebirth.neo4j.importer.writer.MacrosWriter;

import javax.enterprise.event.Observes;
import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.*;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * Author: bram
 * Date: 6-1-14
 * Time: 23:26
 */

@Singleton
public class MacrosImporter extends AbstractImporter<MacrosType> {

    String galaxy = "/maps/XU_ep1_universe/galaxy";
    String clusters = "/maps/XU_ep1_universe/clusters";
    String sectors = "/maps/XU_ep1_universe/sectors";
    String zones = "/maps/XU_ep1_universe/zones";
    String zonehighways = "/maps/XU_ep1_universe/zonehighways";

    @Inject
    IndexReader indexReader;

    @Inject
    ImportContext context;


    ConcurrentLinkedQueue<String> importQueue = new ConcurrentLinkedQueue<>();

    @Inject
    protected MacrosImporter(MacrosReader reader, MacrosWriter writer) {
        super(reader, writer);
        importQueue.offer(galaxy);
        importQueue.offer(clusters);
        importQueue.offer(sectors);
        importQueue.offer(zones);
        importQueue.offer(zonehighways);
    }


    @Override
    public Collection<String> doGetFileLocations() {
        return importQueue;
    }

    Set<String> importedFiles = new HashSet<>();


    private Set<String> counter = new HashSet<>();

    public boolean doImport(ImportContext importContext) {
        indexReader.read(context.getRoot());
        String first = importQueue.poll();
        while (first != null) {
            counter.add(first);
            boolean result = doImport(importContext, first);
            if (!result) {
                System.err.println("error on:" + first);
                return false;
            }
            first = importQueue.poll();
        }
        System.err.println(counter.size());
        Object[] obj = counter.toArray();
        Arrays.sort(obj);
        List<Object> list = Arrays.asList(obj);
        return true;
    }


    @Override
    protected boolean doImport(ImportContext importContext, String file) {
        if (importedFiles.contains(file) || file.contains("\\test\\")) {
            return true;
        }
        boolean res = super.doImport(importContext, file + ".xml");
        importedFiles.add(file);
        return res;
    }

    public void refererFoundListener(@Observes @Reference MacroType macroType) {
        String ref = macroType.getRef();
        String file = indexReader.getMacros().get(ref);
        if (file != null) {
            importQueue.offer(file);
        } else {
            System.err.println("NOT FOUND:" + ref);

        }
    }
}

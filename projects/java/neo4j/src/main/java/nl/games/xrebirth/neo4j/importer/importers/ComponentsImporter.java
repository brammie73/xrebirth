package nl.games.xrebirth.neo4j.importer.importers;

import nl.games.xrebirth.generated.components.Components;
import nl.games.xrebirth.neo4j.importer.ImportContext;
import nl.games.xrebirth.neo4j.importer.reader.ComponentReader;
import nl.games.xrebirth.neo4j.importer.reader.IndexReader;
import nl.games.xrebirth.neo4j.importer.writer.ComponentWriter;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.*;

/**
 * Created with IntelliJ IDEA.
 * User: bram
 * Date: 27-12-13
 * Time: 16:01
 * To change this template use File | Settings | File Templates.
 */
@Singleton
public class ComponentsImporter extends AbstractImporter<Components> {

    @Inject
    ImportContext context;

    @Inject
    IndexReader indexReader;

    @Inject
    protected ComponentsImporter(ComponentReader reader, ComponentWriter writer) {
        super(reader, writer);
    }

    @Override
    public List<String> doGetFileLocations() {
        indexReader.read(context.getRoot());
        Map<String, String> index = indexReader.getComponents();
        return new LinkedList<>(index.values());
    }

    Set<String> importedFiles = new HashSet<>();

    @Override
    protected boolean doImport(ImportContext importContext, String file) {
        if (importedFiles.contains(file) || file.contains("\\test\\")) {
            return true;
        }
        boolean res = super.doImport(importContext, file + ".xml");
        importedFiles.add(file);
        return res;
    }
}

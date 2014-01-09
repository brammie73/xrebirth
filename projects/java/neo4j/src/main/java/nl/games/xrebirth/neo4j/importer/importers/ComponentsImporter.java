package nl.games.xrebirth.neo4j.importer.importers;

import nl.games.xrebirth.neo4j.importer.ImportContext;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

/**
 * Created with IntelliJ IDEA.
 * User: bram
 * Date: 27-12-13
 * Time: 16:01
 * To change this template use File | Settings | File Templates.
 */
@Singleton
public class ComponentsImporter extends AbstractImporter {

    @Inject
    ImportContext context;

    XRIndex xrIndex;


    @Override
    public void doImport() {

    }

    public List<String> doGetFileLocations() {
        //xrIndex.getComponentFiles()
        return new LinkedList<>();
    }

    Set<String> importedFiles = new HashSet<>();

    protected void doImport(ImportContext importContext, String file) {
        if (importedFiles.contains(file) || file.contains("\\test\\")) {
        }
        importedFiles.add(file);
    }
}

package nl.games.xrebirth.neo4j.importer.db;

import nl.games.xrebirth.generated.AbstractElement;
import org.neo4j.graphdb.DynamicLabel;
import org.neo4j.graphdb.Label;

import javax.inject.Singleton;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Author: bram
 * Date: 6-1-14
 * Time: 3:52
 */

@Singleton
public class LabelProducer {

    public Label[] getLabels(AbstractElement element) {
        List<String> list = new LinkedList<>();
        List<String> tmp = element.getClassNames();
        if (tmp.contains("unkown, fix me")) {

        } else {
            if (tmp != null) {
                list.addAll(tmp);
            }
        }

        String elementClass = element.getClass().getSimpleName().toLowerCase();
        list.add(elementClass);
        List<Label> result = new ArrayList<>(list.size());
        for (String value : list) {
            result.add(DynamicLabel.label(value));
        }
        return result.toArray(new Label[result.size()]);
    }

}

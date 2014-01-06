package nl.games.xrebirth.neo4j.producers;

import com.google.common.base.Joiner;
import nl.games.xrebirth.generated.AbstractElement;
import org.neo4j.graphdb.DynamicLabel;
import org.neo4j.graphdb.Label;

import javax.enterprise.inject.Produces;
import javax.inject.Singleton;
import java.util.ArrayList;
import java.util.List;

/**
 * Author: bram
 * Date: 6-1-14
 * Time: 3:52
 */

@Singleton
public class LabelProducer {

    public Label[] getLabels(AbstractElement element) {
        List<String> list = element.getClassNames();
        if (list == null || !list.isEmpty()) {
            String value = element.getClass().getSimpleName().toLowerCase();
            list = new ArrayList<>();
            list.add(value);
        }
        List<Label> result = new ArrayList<>(list.size());
        for (String value : list) {
            result.add(DynamicLabel.label(value));
        }
        return result.toArray(new Label[result.size()]);
    }

}

package nl.games.xrebirth.neo4j.importer;

import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Table;
import nl.games.xrebirth.generated.text.Language;
import nl.games.xrebirth.generated.text.TextPage;
import nl.games.xrebirth.generated.text.TextValue;
import nl.games.xrebirth.neo4j.Config;
import nl.games.xrebirth.neo4j.importer.events.FileEvent;

import javax.enterprise.event.Event;
import javax.enterprise.event.Observes;
import javax.enterprise.inject.Default;
import javax.inject.Inject;
import javax.inject.Singleton;
import java.math.BigDecimal;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created with IntelliJ IDEA.
 * User: bram
 * Date: 30-12-13
 * Time: 22:20
 * To change this template use File | Settings | File Templates.
 */


@Singleton
@Default
public class LanguageTextFormatter implements TextFormatter {


    private Table<Integer, Integer, String> lookupTable = HashBasedTable.create(100, 100);
    private final Pattern pattern = Pattern.compile("\\{[0-9 ]+\\,[0-9 ]+\\}");
    private final Pattern digit = Pattern.compile("[0-9]+");
    private static String fileLocation;

    @Inject
    FileSystem fileSystem;


    static {
        fileLocation = String.format("/t/0001-L%03d.xml", Config.getConfiguration().getInt("language", 44)).toLowerCase();
    }

    @Inject
    Event<FileEvent> fileEventBus;

    public void init() {
        FileEvent event = new FileEvent(Language.class, fileLocation);
        fileEventBus.fire(event);
    }

    public void languageObserver(@Observes Language language) {
        fillTable(language);
    }


    private void fillTable(Language language) {
        lookupTable = HashBasedTable.create(language.getPage().size(), 100);
        for (TextPage page : language.getPage()) {
            for (TextValue text : page.getT()) {
                lookupTable.put(page.getId(), text.getId(), text.getValue());
            }
        }
    }

    @Override
    public Object format(Object in) {
        if (in == null) {
            return null;
        } else if (in instanceof BigDecimal) {
            return ((BigDecimal) in).longValue();
        } else if (in instanceof String) {
            return format((String) in);
        } else if (in instanceof String[]) {
            return format((String[]) in);
        } else {
            return in;
        }
    }

    @Override
    public String format(String in) {
        if (in == null || in.length() < 5) { //{1,1}
            return in;
        } else {
            return formatString(in);
        }
    }

    @Override
    public String[] format(String[] in) {
        for (int i = 0; i < in.length; i++) {
            String value = in[i];
            in[i] = format(value);
        }
        return in;
    }

    public String formatString(String in) {
        Matcher matcher = pattern.matcher(in);
        StringBuffer sb = new StringBuffer(in.length());
        while (matcher.find()) {
            String text = matcher.group();
            try {
                Matcher digitMatcher = digit.matcher(text);
                digitMatcher.find();
                int first = Integer.parseInt(digitMatcher.group());
                digitMatcher.find();
                int second = Integer.parseInt(digitMatcher.group());

                String replacement = lookupTable.get(first, second);
                if (replacement == null) {
                    matcher.appendReplacement(sb, Matcher.quoteReplacement(text));
                } else {
                    matcher.appendReplacement(sb, Matcher.quoteReplacement(replacement));
                }
            } catch (NumberFormatException e) {
                matcher.appendReplacement(sb, Matcher.quoteReplacement(text));
            }
        }
        matcher.appendTail(sb);
        return sb.toString();
    }

}

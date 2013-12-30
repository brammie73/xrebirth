package nl.games.xrebirth.neo4j.importer.reader;

import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Table;
import nl.games.xrebirth.generated.text.Language;
import nl.games.xrebirth.generated.text.Page;
import nl.games.xrebirth.generated.text.Text;

import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created with IntelliJ IDEA.
 * User: bram
 * Date: 30-12-13
 * Time: 22:20
 * To change this template use File | Settings | File Templates.
 */
public class LanguageTextFormatter extends DefaultTextFormatter {


    private Table<Integer, Integer, String> lookupTable  = HashBasedTable.create(100, 100);
    private final Pattern pattern = Pattern.compile("\\{[0-9 ]+\\,[0-9 ]+\\}");
    private final Pattern digit = Pattern.compile("[0-9]+");

    public LanguageTextFormatter(ImportContext importContext, Importer<Language> importer) {
        importer.doImport(importContext);
        Language language = importer.getValues();
        fillTable(language);
        importContext.setTextFormatter(this);
    }

    private void fillTable(Language language) {
        lookupTable = HashBasedTable.create(language.getPage().size(), 100);
        for (Page page : language.getPage()) {
            for (Text text : page.getT()) {
                lookupTable.put(page.getId(), text.getId(), text.getValue());
            }
        }
    }

    @Override
    public String format(String in) {
        if (in == null || in.length() < 5)  { //{1,1}
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

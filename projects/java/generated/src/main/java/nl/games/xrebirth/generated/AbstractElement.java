package nl.games.xrebirth.generated;

import jodd.bean.BeanUtil;
import jodd.typeconverter.TypeConverterManager;

import java.util.*;

/**
 * Author: bram
 * Date: 6-1-14
 * Time: 4:05
 */
@javax.xml.bind.annotation.XmlTransient
public abstract class AbstractElement {

    private static Set<Class> classIgnore = new HashSet<>(500);

    public List<String> getClassNames() {
        if (BeanUtil.hasProperty(this, "clazz")) {
            Object obj = BeanUtil.getProperty(this, "clazz");
            String[] arr  = TypeConverterManager.convertType(obj, String[].class);
            return Arrays.asList(arr);
        } else {
            return null;
        }
    }

    public String getIdString() {
        if (BeanUtil.hasProperty(this, "id")) {
            Object obj = BeanUtil.getProperty(this, "id");
            return TypeConverterManager.convertType(obj, String.class);
        } else {
            return null;
        }
    }

    public List<String> getTagList() {
        if (BeanUtil.hasProperty(this, "tags")) {
            Object obj = BeanUtil.getProperty(this, "tags");
            return Arrays.asList(TypeConverterManager.convertType(obj, String[].class));
        }
        if (BeanUtil.hasProperty(this, "tag")) {
            Object obj = BeanUtil.getProperty(this, "tag");
            if (obj instanceof String) {
                return Arrays.asList(((String)obj).split(" "));
            }
        }
        return null;
    }
}

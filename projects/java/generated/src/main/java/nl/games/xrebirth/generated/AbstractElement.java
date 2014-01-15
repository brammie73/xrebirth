package nl.games.xrebirth.generated;

import jodd.bean.BeanUtil;
import jodd.typeconverter.TypeConverterManager;

import javax.xml.bind.JAXBElement;
import javax.xml.namespace.QName;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Author: bram
 * Date: 6-1-14
 * Time: 4:05
 */
@javax.xml.bind.annotation.XmlTransient
public abstract class AbstractElement implements PropertyVisitable {

    private static Set<Class> classIgnore = new HashSet<>(500);


    @javax.xml.bind.annotation.XmlTransient
    private String xmlFile;

    public String getXmlFile() {
        return xmlFile;
    }

    public void setXmlFile(String xmlFile) {
        this.xmlFile = xmlFile;
    }

    @javax.xml.bind.annotation.XmlTransient
    private Object id;

    public Object getId() {
        return id;
    }

    public void setId(Object nodeId) {
        this.id = nodeId;
    }

    @javax.xml.bind.annotation.XmlTransient
    private QName qName;

    public QName getQName() {
        return qName;
    }

    public void setQName(QName qName) {
        this.qName = qName;
    }

    public JAXBElement toJAXBElement() {
        return new JAXBElement(qName, getClass(), this);
    }


    @javax.xml.bind.annotation.XmlTransient
    private AbstractElement parentElement;

    public AbstractElement getParentElement() {
        return parentElement;
    }

    public void setParentElement(AbstractElement parentElement) {
        this.parentElement = parentElement;
    }

    public List<String> classNames() {
        if (BeanUtil.hasProperty(this, "clazz")) {
            Object obj = BeanUtil.getProperty(this, "clazz");
            String[] arr = TypeConverterManager.convertType(obj, String[].class);
            if (arr == null) {
                return Arrays.asList("unkown, fix me");
            }
            return Arrays.asList(arr);
        } else {
            return null;
        }
    }

    public List<String> tagList() {
        Object obj = null;
        if (BeanUtil.hasProperty(this, "tags")) {
            obj = BeanUtil.getProperty(this, "tags");
        }
        if (BeanUtil.hasProperty(this, "tag")) {
            obj = BeanUtil.getProperty(this, "tag");
        }
        if (obj == null) {
            return null;
        } else if (obj instanceof String) {
            return Arrays.asList(((String) obj).split(" "));
        } else if (obj instanceof String[]) {
            return Arrays.asList((String[]) obj);
        } else if (obj instanceof List) {
            return TypeConverterManager.convertType(obj, List.class);
        }
        throw new IllegalArgumentException("dunno what to do with:" + obj.getClass());
    }

    public String toString() {
        ToStringVisitor visitor = new ToStringVisitor();
        this.accept(visitor,  "root");
        return visitor.string();
    }

}

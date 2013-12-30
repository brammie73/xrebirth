//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.4-2 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2013.12.28 at 10:09:06 PM CET 
//


package nl.games.xrebirth.generated.stats;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for datatype.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="datatype">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="int"/>
 *     &lt;enumeration value="int64"/>
 *     &lt;enumeration value="float"/>
 *     &lt;enumeration value="double"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "datatype")
@XmlEnum
public enum Datatype {

    @XmlEnumValue("int")
    INT("int"),
    @XmlEnumValue("int64")
    INT_64("int64"),
    @XmlEnumValue("float")
    FLOAT("float"),
    @XmlEnumValue("double")
    DOUBLE("double");
    private final String value;

    Datatype(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static Datatype fromValue(String v) {
        for (Datatype c: Datatype.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.4-2 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2013.12.28 at 11:19:24 PM CET 
//


package nl.games.xrebirth.generated.md;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for entityskilllookup.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="entityskilllookup">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="boarding"/>
 *     &lt;enumeration value="combat"/>
 *     &lt;enumeration value="engineering"/>
 *     &lt;enumeration value="leadership"/>
 *     &lt;enumeration value="management"/>
 *     &lt;enumeration value="morale"/>
 *     &lt;enumeration value="navigation"/>
 *     &lt;enumeration value="science"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "entityskilllookup")
@XmlEnum
public enum Entityskilllookup {

    @XmlEnumValue("boarding")
    BOARDING("boarding"),
    @XmlEnumValue("combat")
    COMBAT("combat"),
    @XmlEnumValue("engineering")
    ENGINEERING("engineering"),
    @XmlEnumValue("leadership")
    LEADERSHIP("leadership"),
    @XmlEnumValue("management")
    MANAGEMENT("management"),
    @XmlEnumValue("morale")
    MORALE("morale"),
    @XmlEnumValue("navigation")
    NAVIGATION("navigation"),
    @XmlEnumValue("science")
    SCIENCE("science");
    private final String value;

    Entityskilllookup(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static Entityskilllookup fromValue(String v) {
        for (Entityskilllookup c: Entityskilllookup.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
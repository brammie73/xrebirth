//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.4-2 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2013.12.28 at 10:06:21 PM CET 
//


package nl.games.xrebirth.generated.quotas;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for componentstatelookup.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="componentstatelookup">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="construction"/>
 *     &lt;enumeration value="operational"/>
 *     &lt;enumeration value="upgrade"/>
 *     &lt;enumeration value="wreck"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "componentstatelookup")
@XmlEnum
public enum Componentstatelookup {

    @XmlEnumValue("construction")
    CONSTRUCTION("construction"),
    @XmlEnumValue("operational")
    OPERATIONAL("operational"),
    @XmlEnumValue("upgrade")
    UPGRADE("upgrade"),
    @XmlEnumValue("wreck")
    WRECK("wreck");
    private final String value;

    Componentstatelookup(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static Componentstatelookup fromValue(String v) {
        for (Componentstatelookup c: Componentstatelookup.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}

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
 * <p>Java class for onfaillookup.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="onfaillookup">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="cancel"/>
 *     &lt;enumeration value="complete"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "onfaillookup")
@XmlEnum
public enum Onfaillookup {


    /**
     * 
     *             Cancel the cue when conditions fail
     *           
     * 
     */
    @XmlEnumValue("cancel")
    CANCEL("cancel"),

    /**
     * 
     *             Complete the cue when conditions fail
     *           
     * 
     */
    @XmlEnumValue("complete")
    COMPLETE("complete");
    private final String value;

    Onfaillookup(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static Onfaillookup fromValue(String v) {
        for (Onfaillookup c: Onfaillookup.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
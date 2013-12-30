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
 * <p>Java class for weapontypelookup.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="weapontypelookup">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="all"/>
 *     &lt;enumeration value="combat"/>
 *     &lt;enumeration value="mining"/>
 *     &lt;enumeration value="repair"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "weapontypelookup")
@XmlEnum
public enum Weapontypelookup {


    /**
     * 
     *             All weapons (including mining or repair lasers!)
     *           
     * 
     */
    @XmlEnumValue("all")
    ALL("all"),

    /**
     * 
     *             Combat weapons (default)
     *           
     * 
     */
    @XmlEnumValue("combat")
    COMBAT("combat"),

    /**
     * 
     *             Mining weapons
     *           
     * 
     */
    @XmlEnumValue("mining")
    MINING("mining"),

    /**
     * 
     *             Reapir weapons
     *           
     * 
     */
    @XmlEnumValue("repair")
    REPAIR("repair");
    private final String value;

    Weapontypelookup(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static Weapontypelookup fromValue(String v) {
        for (Weapontypelookup c: Weapontypelookup.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}

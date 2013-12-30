//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.4-2 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2013.12.28 at 10:04:53 PM CET 
//


package nl.games.xrebirth.generated.libraries;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for weaponsystemlookup.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="weaponsystemlookup">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="all"/>
 *     &lt;enumeration value="drone_attack"/>
 *     &lt;enumeration value="drone_defence"/>
 *     &lt;enumeration value="drone_repair"/>
 *     &lt;enumeration value="flak"/>
 *     &lt;enumeration value="laser"/>
 *     &lt;enumeration value="missile"/>
 *     &lt;enumeration value="torpedo"/>
 *     &lt;enumeration value="turret_shortrange"/>
 *     &lt;enumeration value="turret_midrange"/>
 *     &lt;enumeration value="turret_longrange"/>
 *     &lt;enumeration value="turret_missile"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "weaponsystemlookup")
@XmlEnum
public enum Weaponsystemlookup {

    @XmlEnumValue("all")
    ALL("all"),
    @XmlEnumValue("drone_attack")
    DRONE_ATTACK("drone_attack"),
    @XmlEnumValue("drone_defence")
    DRONE_DEFENCE("drone_defence"),
    @XmlEnumValue("drone_repair")
    DRONE_REPAIR("drone_repair"),
    @XmlEnumValue("flak")
    FLAK("flak"),
    @XmlEnumValue("laser")
    LASER("laser"),
    @XmlEnumValue("missile")
    MISSILE("missile"),
    @XmlEnumValue("torpedo")
    TORPEDO("torpedo"),
    @XmlEnumValue("turret_shortrange")
    TURRET_SHORTRANGE("turret_shortrange"),
    @XmlEnumValue("turret_midrange")
    TURRET_MIDRANGE("turret_midrange"),
    @XmlEnumValue("turret_longrange")
    TURRET_LONGRANGE("turret_longrange"),
    @XmlEnumValue("turret_missile")
    TURRET_MISSILE("turret_missile");
    private final String value;

    Weaponsystemlookup(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static Weaponsystemlookup fromValue(String v) {
        for (Weaponsystemlookup c: Weaponsystemlookup.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}

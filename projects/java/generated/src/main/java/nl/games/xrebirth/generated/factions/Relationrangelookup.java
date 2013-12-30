//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.4-2 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2013.12.28 at 10:04:32 PM CET 
//


package nl.games.xrebirth.generated.factions;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for relationrangelookup.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="relationrangelookup">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="ally"/>
 *     &lt;enumeration value="dock"/>
 *     &lt;enumeration value="enemy"/>
 *     &lt;enumeration value="friend"/>
 *     &lt;enumeration value="kill"/>
 *     &lt;enumeration value="member"/>
 *     &lt;enumeration value="nemesis"/>
 *     &lt;enumeration value="neutral"/>
 *     &lt;enumeration value="self"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "relationrangelookup")
@XmlEnum
public enum Relationrangelookup {

    @XmlEnumValue("ally")
    ALLY("ally"),
    @XmlEnumValue("dock")
    DOCK("dock"),
    @XmlEnumValue("enemy")
    ENEMY("enemy"),
    @XmlEnumValue("friend")
    FRIEND("friend"),
    @XmlEnumValue("kill")
    KILL("kill"),
    @XmlEnumValue("member")
    MEMBER("member"),
    @XmlEnumValue("nemesis")
    NEMESIS("nemesis"),
    @XmlEnumValue("neutral")
    NEUTRAL("neutral"),
    @XmlEnumValue("self")
    SELF("self");
    private final String value;

    Relationrangelookup(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static Relationrangelookup fromValue(String v) {
        for (Relationrangelookup c: Relationrangelookup.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}

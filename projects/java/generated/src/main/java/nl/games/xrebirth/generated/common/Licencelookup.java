//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.4-2 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2013.12.28 at 10:01:51 PM CET 
//


package nl.games.xrebirth.generated.common;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for licencelookup.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="licencelookup">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="intelligence"/>
 *     &lt;enumeration value="military"/>
 *     &lt;enumeration value="police"/>
 *     &lt;enumeration value="hazardousmaterials"/>
 *     &lt;enumeration value="intoxicants"/>
 *     &lt;enumeration value="pharmaceuticals"/>
 *     &lt;enumeration value="protectedspecies"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "licencelookup")
@XmlEnum
public enum Licencelookup {

    @XmlEnumValue("intelligence")
    INTELLIGENCE("intelligence"),
    @XmlEnumValue("military")
    MILITARY("military"),
    @XmlEnumValue("police")
    POLICE("police"),
    @XmlEnumValue("hazardousmaterials")
    HAZARDOUSMATERIALS("hazardousmaterials"),
    @XmlEnumValue("intoxicants")
    INTOXICANTS("intoxicants"),
    @XmlEnumValue("pharmaceuticals")
    PHARMACEUTICALS("pharmaceuticals"),
    @XmlEnumValue("protectedspecies")
    PROTECTEDSPECIES("protectedspecies");
    private final String value;

    Licencelookup(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static Licencelookup fromValue(String v) {
        for (Licencelookup c: Licencelookup.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
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
 * <p>Java class for conversationview.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="conversationview">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="facecopilot"/>
 *     &lt;enumeration value="facenormal"/>
 *     &lt;enumeration value="facedetailmonitor"/>
 *     &lt;enumeration value="closeupdetailmonitor"/>
 *     &lt;enumeration value="closeupeventmonitor"/>
 *     &lt;enumeration value="faceconsole"/>
 *     &lt;enumeration value="facepos1"/>
 *     &lt;enumeration value="facepos2"/>
 *     &lt;enumeration value="facepos3"/>
 *     &lt;enumeration value="facepos4"/>
 *     &lt;enumeration value="facepos5"/>
 *     &lt;enumeration value="angry_01"/>
 *     &lt;enumeration value="disappointed_01"/>
 *     &lt;enumeration value="flirting_01"/>
 *     &lt;enumeration value="happy_01"/>
 *     &lt;enumeration value="happy_02"/>
 *     &lt;enumeration value="idle_01"/>
 *     &lt;enumeration value="sad_01"/>
 *     &lt;enumeration value="sad_02"/>
 *     &lt;enumeration value="surprised_01"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "conversationview")
@XmlEnum
public enum Conversationview {

    @XmlEnumValue("facecopilot")
    FACECOPILOT("facecopilot"),
    @XmlEnumValue("facenormal")
    FACENORMAL("facenormal"),
    @XmlEnumValue("facedetailmonitor")
    FACEDETAILMONITOR("facedetailmonitor"),
    @XmlEnumValue("closeupdetailmonitor")
    CLOSEUPDETAILMONITOR("closeupdetailmonitor"),
    @XmlEnumValue("closeupeventmonitor")
    CLOSEUPEVENTMONITOR("closeupeventmonitor"),
    @XmlEnumValue("faceconsole")
    FACECONSOLE("faceconsole"),
    @XmlEnumValue("facepos1")
    FACEPOS_1("facepos1"),
    @XmlEnumValue("facepos2")
    FACEPOS_2("facepos2"),
    @XmlEnumValue("facepos3")
    FACEPOS_3("facepos3"),
    @XmlEnumValue("facepos4")
    FACEPOS_4("facepos4"),
    @XmlEnumValue("facepos5")
    FACEPOS_5("facepos5"),
    @XmlEnumValue("angry_01")
    ANGRY_01("angry_01"),
    @XmlEnumValue("disappointed_01")
    DISAPPOINTED_01("disappointed_01"),
    @XmlEnumValue("flirting_01")
    FLIRTING_01("flirting_01"),
    @XmlEnumValue("happy_01")
    HAPPY_01("happy_01"),
    @XmlEnumValue("happy_02")
    HAPPY_02("happy_02"),
    @XmlEnumValue("idle_01")
    IDLE_01("idle_01"),
    @XmlEnumValue("sad_01")
    SAD_01("sad_01"),
    @XmlEnumValue("sad_02")
    SAD_02("sad_02"),
    @XmlEnumValue("surprised_01")
    SURPRISED_01("surprised_01");
    private final String value;

    Conversationview(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static Conversationview fromValue(String v) {
        for (Conversationview c: Conversationview.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}

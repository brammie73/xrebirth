//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.4-2 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2013.12.28 at 10:51:43 PM CET 
//


package nl.games.xrebirth.generated.savegame;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for cameraType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="cameraType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="movement" type="{}movementType"/>
 *       &lt;/sequence>
 *       &lt;attribute name="component" type="{http://www.w3.org/2001/XMLSchema}string" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "cameraType", propOrder = {
    "movement"
})
public class CameraType {

    @XmlElement(required = true)
    protected MovementType movement;
    @XmlAttribute(name = "component")
    protected String component;

    /**
     * Gets the value of the movement property.
     * 
     * @return
     *     possible object is
     *     {@link MovementType }
     *     
     */
    public MovementType getMovement() {
        return movement;
    }

    /**
     * Sets the value of the movement property.
     * 
     * @param value
     *     allowed object is
     *     {@link MovementType }
     *     
     */
    public void setMovement(MovementType value) {
        this.movement = value;
    }

    /**
     * Gets the value of the component property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getComponent() {
        return component;
    }

    /**
     * Sets the value of the component property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setComponent(String value) {
        this.component = value;
    }

}

//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.4-2 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2013.12.28 at 10:51:43 PM CET 
//


package nl.games.xrebirth.generated.savegame;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for camerasType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="camerasType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="camera" type="{}cameraType"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "camerasType", propOrder = {
    "camera"
})
public class CamerasType {

    @XmlElement(required = true)
    protected CameraType camera;

    /**
     * Gets the value of the camera property.
     * 
     * @return
     *     possible object is
     *     {@link CameraType }
     *     
     */
    public CameraType getCamera() {
        return camera;
    }

    /**
     * Sets the value of the camera property.
     * 
     * @param value
     *     allowed object is
     *     {@link CameraType }
     *     
     */
    public void setCamera(CameraType value) {
        this.camera = value;
    }

}

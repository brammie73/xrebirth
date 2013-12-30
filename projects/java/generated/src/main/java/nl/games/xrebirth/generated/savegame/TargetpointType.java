//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.4-2 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2013.12.28 at 10:51:43 PM CET 
//


package nl.games.xrebirth.generated.savegame;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlMixed;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for targetpointType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="targetpointType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="offset" type="{}offsetType" minOccurs="0"/>
 *       &lt;/sequence>
 *       &lt;attribute name="refobject" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="behavior" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="direction" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="forcerotation" type="{http://www.w3.org/2001/XMLSchema}byte" />
 *       &lt;attribute name="avoidcollisions" type="{http://www.w3.org/2001/XMLSchema}byte" />
 *       &lt;attribute name="resetroll" type="{http://www.w3.org/2001/XMLSchema}byte" />
 *       &lt;attribute name="relativemovement" type="{http://www.w3.org/2001/XMLSchema}byte" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "targetpointType", propOrder = {
    "content"
})
public class TargetpointType {

    @XmlElementRef(name = "offset", type = JAXBElement.class, required = false)
    @XmlMixed
    protected List<Serializable> content;
    @XmlAttribute(name = "refobject")
    protected String refobject;
    @XmlAttribute(name = "behavior")
    protected String behavior;
    @XmlAttribute(name = "direction")
    protected String direction;
    @XmlAttribute(name = "forcerotation")
    protected Byte forcerotation;
    @XmlAttribute(name = "avoidcollisions")
    protected Byte avoidcollisions;
    @XmlAttribute(name = "resetroll")
    protected Byte resetroll;
    @XmlAttribute(name = "relativemovement")
    protected Byte relativemovement;

    /**
     * Gets the value of the content property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the content property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getContent().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link String }
     * {@link JAXBElement }{@code <}{@link OffsetType }{@code >}
     * 
     * 
     */
    public List<Serializable> getContent() {
        if (content == null) {
            content = new ArrayList<Serializable>();
        }
        return this.content;
    }

    /**
     * Gets the value of the refobject property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRefobject() {
        return refobject;
    }

    /**
     * Sets the value of the refobject property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRefobject(String value) {
        this.refobject = value;
    }

    /**
     * Gets the value of the behavior property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBehavior() {
        return behavior;
    }

    /**
     * Sets the value of the behavior property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBehavior(String value) {
        this.behavior = value;
    }

    /**
     * Gets the value of the direction property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDirection() {
        return direction;
    }

    /**
     * Sets the value of the direction property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDirection(String value) {
        this.direction = value;
    }

    /**
     * Gets the value of the forcerotation property.
     * 
     * @return
     *     possible object is
     *     {@link Byte }
     *     
     */
    public Byte getForcerotation() {
        return forcerotation;
    }

    /**
     * Sets the value of the forcerotation property.
     * 
     * @param value
     *     allowed object is
     *     {@link Byte }
     *     
     */
    public void setForcerotation(Byte value) {
        this.forcerotation = value;
    }

    /**
     * Gets the value of the avoidcollisions property.
     * 
     * @return
     *     possible object is
     *     {@link Byte }
     *     
     */
    public Byte getAvoidcollisions() {
        return avoidcollisions;
    }

    /**
     * Sets the value of the avoidcollisions property.
     * 
     * @param value
     *     allowed object is
     *     {@link Byte }
     *     
     */
    public void setAvoidcollisions(Byte value) {
        this.avoidcollisions = value;
    }

    /**
     * Gets the value of the resetroll property.
     * 
     * @return
     *     possible object is
     *     {@link Byte }
     *     
     */
    public Byte getResetroll() {
        return resetroll;
    }

    /**
     * Sets the value of the resetroll property.
     * 
     * @param value
     *     allowed object is
     *     {@link Byte }
     *     
     */
    public void setResetroll(Byte value) {
        this.resetroll = value;
    }

    /**
     * Gets the value of the relativemovement property.
     * 
     * @return
     *     possible object is
     *     {@link Byte }
     *     
     */
    public Byte getRelativemovement() {
        return relativemovement;
    }

    /**
     * Sets the value of the relativemovement property.
     * 
     * @param value
     *     allowed object is
     *     {@link Byte }
     *     
     */
    public void setRelativemovement(Byte value) {
        this.relativemovement = value;
    }

}

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
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.XmlValue;


/**
 * <p>Java class for behaviorType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="behaviorType">
 *   &lt;simpleContent>
 *     &lt;extension base="&lt;http://www.w3.org/2001/XMLSchema>string">
 *       &lt;attribute name="class" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="basespeed" type="{http://www.w3.org/2001/XMLSchema}byte" />
 *       &lt;attribute name="numpaths" type="{http://www.w3.org/2001/XMLSchema}byte" />
 *       &lt;attribute name="numpoints" type="{http://www.w3.org/2001/XMLSchema}byte" />
 *       &lt;attribute name="slowspeed" type="{http://www.w3.org/2001/XMLSchema}byte" />
 *       &lt;attribute name="maxslowroadlength" type="{http://www.w3.org/2001/XMLSchema}short" />
 *       &lt;attribute name="seller" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="type" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="default" type="{http://www.w3.org/2001/XMLSchema}string" />
 *     &lt;/extension>
 *   &lt;/simpleContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "behaviorType", propOrder = {
    "value"
})
public class BehaviorType {

    @XmlValue
    protected String value;
    @XmlAttribute(name = "class")
    protected String clazz;
    @XmlAttribute(name = "basespeed")
    protected Byte basespeed;
    @XmlAttribute(name = "numpaths")
    protected Byte numpaths;
    @XmlAttribute(name = "numpoints")
    protected Byte numpoints;
    @XmlAttribute(name = "slowspeed")
    protected Byte slowspeed;
    @XmlAttribute(name = "maxslowroadlength")
    protected Short maxslowroadlength;
    @XmlAttribute(name = "seller")
    protected String seller;
    @XmlAttribute(name = "type")
    protected String type;
    @XmlAttribute(name = "default")
    protected String _default;

    /**
     * Gets the value of the value property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getValue() {
        return value;
    }

    /**
     * Sets the value of the value property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setValue(String value) {
        this.value = value;
    }

    /**
     * Gets the value of the clazz property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getClazz() {
        return clazz;
    }

    /**
     * Sets the value of the clazz property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setClazz(String value) {
        this.clazz = value;
    }

    /**
     * Gets the value of the basespeed property.
     * 
     * @return
     *     possible object is
     *     {@link Byte }
     *     
     */
    public Byte getBasespeed() {
        return basespeed;
    }

    /**
     * Sets the value of the basespeed property.
     * 
     * @param value
     *     allowed object is
     *     {@link Byte }
     *     
     */
    public void setBasespeed(Byte value) {
        this.basespeed = value;
    }

    /**
     * Gets the value of the numpaths property.
     * 
     * @return
     *     possible object is
     *     {@link Byte }
     *     
     */
    public Byte getNumpaths() {
        return numpaths;
    }

    /**
     * Sets the value of the numpaths property.
     * 
     * @param value
     *     allowed object is
     *     {@link Byte }
     *     
     */
    public void setNumpaths(Byte value) {
        this.numpaths = value;
    }

    /**
     * Gets the value of the numpoints property.
     * 
     * @return
     *     possible object is
     *     {@link Byte }
     *     
     */
    public Byte getNumpoints() {
        return numpoints;
    }

    /**
     * Sets the value of the numpoints property.
     * 
     * @param value
     *     allowed object is
     *     {@link Byte }
     *     
     */
    public void setNumpoints(Byte value) {
        this.numpoints = value;
    }

    /**
     * Gets the value of the slowspeed property.
     * 
     * @return
     *     possible object is
     *     {@link Byte }
     *     
     */
    public Byte getSlowspeed() {
        return slowspeed;
    }

    /**
     * Sets the value of the slowspeed property.
     * 
     * @param value
     *     allowed object is
     *     {@link Byte }
     *     
     */
    public void setSlowspeed(Byte value) {
        this.slowspeed = value;
    }

    /**
     * Gets the value of the maxslowroadlength property.
     * 
     * @return
     *     possible object is
     *     {@link Short }
     *     
     */
    public Short getMaxslowroadlength() {
        return maxslowroadlength;
    }

    /**
     * Sets the value of the maxslowroadlength property.
     * 
     * @param value
     *     allowed object is
     *     {@link Short }
     *     
     */
    public void setMaxslowroadlength(Short value) {
        this.maxslowroadlength = value;
    }

    /**
     * Gets the value of the seller property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSeller() {
        return seller;
    }

    /**
     * Sets the value of the seller property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSeller(String value) {
        this.seller = value;
    }

    /**
     * Gets the value of the type property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getType() {
        return type;
    }

    /**
     * Sets the value of the type property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setType(String value) {
        this.type = value;
    }

    /**
     * Gets the value of the default property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDefault() {
        return _default;
    }

    /**
     * Sets the value of the default property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDefault(String value) {
        this._default = value;
    }

}
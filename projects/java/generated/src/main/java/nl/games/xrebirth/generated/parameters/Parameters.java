//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.4-2 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2013.12.28 at 10:06:04 PM CET 
//


package nl.games.xrebirth.generated.parameters;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for anonymous complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;all>
 *         &lt;element name="playerflight" type="{http://www.w3.org/2001/XMLSchema}anyType" minOccurs="0"/>
 *         &lt;element name="playerfirstperson" type="{http://www.w3.org/2001/XMLSchema}anyType" minOccurs="0"/>
 *         &lt;element name="playercamera" type="{http://www.w3.org/2001/XMLSchema}anyType" minOccurs="0"/>
 *         &lt;element name="environmentobject" type="{http://www.w3.org/2001/XMLSchema}anyType" minOccurs="0"/>
 *         &lt;element name="highlight" type="{}highlight" minOccurs="0"/>
 *         &lt;element name="scanlevel" type="{http://www.w3.org/2001/XMLSchema}anyType" minOccurs="0"/>
 *         &lt;element name="holomap" type="{http://www.w3.org/2001/XMLSchema}anyType" minOccurs="0"/>
 *         &lt;element name="minigame" type="{http://www.w3.org/2001/XMLSchema}anyType" minOccurs="0"/>
 *         &lt;element name="longrangescan" type="{http://www.w3.org/2001/XMLSchema}anyType" minOccurs="0"/>
 *         &lt;element name="drop" type="{http://www.w3.org/2001/XMLSchema}anyType" minOccurs="0"/>
 *         &lt;element name="hintpositions" type="{http://www.w3.org/2001/XMLSchema}anyType" minOccurs="0"/>
 *         &lt;element name="physics" type="{http://www.w3.org/2001/XMLSchema}anyType" minOccurs="0"/>
 *         &lt;element name="bonuscontent" type="{http://www.w3.org/2001/XMLSchema}anyType" minOccurs="0"/>
 *       &lt;/all>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {

})
@XmlRootElement(name = "parameters")
public class Parameters {

    protected Object playerflight;
    protected Object playerfirstperson;
    protected Object playercamera;
    protected Object environmentobject;
    protected Highlight highlight;
    protected Object scanlevel;
    protected Object holomap;
    protected Object minigame;
    protected Object longrangescan;
    protected Object drop;
    protected Object hintpositions;
    protected Object physics;
    protected Object bonuscontent;

    /**
     * Gets the value of the playerflight property.
     * 
     * @return
     *     possible object is
     *     {@link Object }
     *     
     */
    public Object getPlayerflight() {
        return playerflight;
    }

    /**
     * Sets the value of the playerflight property.
     * 
     * @param value
     *     allowed object is
     *     {@link Object }
     *     
     */
    public void setPlayerflight(Object value) {
        this.playerflight = value;
    }

    /**
     * Gets the value of the playerfirstperson property.
     * 
     * @return
     *     possible object is
     *     {@link Object }
     *     
     */
    public Object getPlayerfirstperson() {
        return playerfirstperson;
    }

    /**
     * Sets the value of the playerfirstperson property.
     * 
     * @param value
     *     allowed object is
     *     {@link Object }
     *     
     */
    public void setPlayerfirstperson(Object value) {
        this.playerfirstperson = value;
    }

    /**
     * Gets the value of the playercamera property.
     * 
     * @return
     *     possible object is
     *     {@link Object }
     *     
     */
    public Object getPlayercamera() {
        return playercamera;
    }

    /**
     * Sets the value of the playercamera property.
     * 
     * @param value
     *     allowed object is
     *     {@link Object }
     *     
     */
    public void setPlayercamera(Object value) {
        this.playercamera = value;
    }

    /**
     * Gets the value of the environmentobject property.
     * 
     * @return
     *     possible object is
     *     {@link Object }
     *     
     */
    public Object getEnvironmentobject() {
        return environmentobject;
    }

    /**
     * Sets the value of the environmentobject property.
     * 
     * @param value
     *     allowed object is
     *     {@link Object }
     *     
     */
    public void setEnvironmentobject(Object value) {
        this.environmentobject = value;
    }

    /**
     * Gets the value of the highlight property.
     * 
     * @return
     *     possible object is
     *     {@link Highlight }
     *     
     */
    public Highlight getHighlight() {
        return highlight;
    }

    /**
     * Sets the value of the highlight property.
     * 
     * @param value
     *     allowed object is
     *     {@link Highlight }
     *     
     */
    public void setHighlight(Highlight value) {
        this.highlight = value;
    }

    /**
     * Gets the value of the scanlevel property.
     * 
     * @return
     *     possible object is
     *     {@link Object }
     *     
     */
    public Object getScanlevel() {
        return scanlevel;
    }

    /**
     * Sets the value of the scanlevel property.
     * 
     * @param value
     *     allowed object is
     *     {@link Object }
     *     
     */
    public void setScanlevel(Object value) {
        this.scanlevel = value;
    }

    /**
     * Gets the value of the holomap property.
     * 
     * @return
     *     possible object is
     *     {@link Object }
     *     
     */
    public Object getHolomap() {
        return holomap;
    }

    /**
     * Sets the value of the holomap property.
     * 
     * @param value
     *     allowed object is
     *     {@link Object }
     *     
     */
    public void setHolomap(Object value) {
        this.holomap = value;
    }

    /**
     * Gets the value of the minigame property.
     * 
     * @return
     *     possible object is
     *     {@link Object }
     *     
     */
    public Object getMinigame() {
        return minigame;
    }

    /**
     * Sets the value of the minigame property.
     * 
     * @param value
     *     allowed object is
     *     {@link Object }
     *     
     */
    public void setMinigame(Object value) {
        this.minigame = value;
    }

    /**
     * Gets the value of the longrangescan property.
     * 
     * @return
     *     possible object is
     *     {@link Object }
     *     
     */
    public Object getLongrangescan() {
        return longrangescan;
    }

    /**
     * Sets the value of the longrangescan property.
     * 
     * @param value
     *     allowed object is
     *     {@link Object }
     *     
     */
    public void setLongrangescan(Object value) {
        this.longrangescan = value;
    }

    /**
     * Gets the value of the drop property.
     * 
     * @return
     *     possible object is
     *     {@link Object }
     *     
     */
    public Object getDrop() {
        return drop;
    }

    /**
     * Sets the value of the drop property.
     * 
     * @param value
     *     allowed object is
     *     {@link Object }
     *     
     */
    public void setDrop(Object value) {
        this.drop = value;
    }

    /**
     * Gets the value of the hintpositions property.
     * 
     * @return
     *     possible object is
     *     {@link Object }
     *     
     */
    public Object getHintpositions() {
        return hintpositions;
    }

    /**
     * Sets the value of the hintpositions property.
     * 
     * @param value
     *     allowed object is
     *     {@link Object }
     *     
     */
    public void setHintpositions(Object value) {
        this.hintpositions = value;
    }

    /**
     * Gets the value of the physics property.
     * 
     * @return
     *     possible object is
     *     {@link Object }
     *     
     */
    public Object getPhysics() {
        return physics;
    }

    /**
     * Sets the value of the physics property.
     * 
     * @param value
     *     allowed object is
     *     {@link Object }
     *     
     */
    public void setPhysics(Object value) {
        this.physics = value;
    }

    /**
     * Gets the value of the bonuscontent property.
     * 
     * @return
     *     possible object is
     *     {@link Object }
     *     
     */
    public Object getBonuscontent() {
        return bonuscontent;
    }

    /**
     * Sets the value of the bonuscontent property.
     * 
     * @param value
     *     allowed object is
     *     {@link Object }
     *     
     */
    public void setBonuscontent(Object value) {
        this.bonuscontent = value;
    }

}

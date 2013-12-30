//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.4-2 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2013.12.28 at 10:51:43 PM CET 
//


package nl.games.xrebirth.generated.savegame;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for snapshotType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="snapshotType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="ware" type="{}wareType" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="buildtree" type="{}buildtreeType" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *       &lt;attribute name="time" type="{http://www.w3.org/2001/XMLSchema}float" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "snapshotType", propOrder = {
    "ware",
    "buildtree"
})
public class SnapshotType {

    protected List<WareType> ware;
    protected List<BuildtreeType> buildtree;
    @XmlAttribute(name = "time")
    protected Float time;

    /**
     * Gets the value of the ware property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the ware property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getWare().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link WareType }
     * 
     * 
     */
    public List<WareType> getWare() {
        if (ware == null) {
            ware = new ArrayList<WareType>();
        }
        return this.ware;
    }

    /**
     * Gets the value of the buildtree property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the buildtree property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getBuildtree().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link BuildtreeType }
     * 
     * 
     */
    public List<BuildtreeType> getBuildtree() {
        if (buildtree == null) {
            buildtree = new ArrayList<BuildtreeType>();
        }
        return this.buildtree;
    }

    /**
     * Gets the value of the time property.
     * 
     * @return
     *     possible object is
     *     {@link Float }
     *     
     */
    public Float getTime() {
        return time;
    }

    /**
     * Sets the value of the time property.
     * 
     * @param value
     *     allowed object is
     *     {@link Float }
     *     
     */
    public void setTime(Float value) {
        this.time = value;
    }

}

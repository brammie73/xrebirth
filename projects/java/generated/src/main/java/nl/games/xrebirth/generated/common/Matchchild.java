//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.4-2 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2013.12.28 at 10:01:51 PM CET 
//


package nl.games.xrebirth.generated.common;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;


/**
 * 
 *         Match filter for the children of the objects
 *       
 * 
 * <p>Java class for matchchild complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="matchchild">
 *   &lt;complexContent>
 *     &lt;extension base="{}matchbasewithpilot">
 *       &lt;attGroup ref="{}match"/>
 *       &lt;attGroup ref="{}range"/>
 *       &lt;attribute name="comment" type="{}comment" />
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "matchchild")
public class Matchchild
    extends Matchbasewithpilot
{

    @XmlAttribute(name = "comment")
    protected String comment;
    @XmlAttribute(name = "negate")
    protected Boolean negate;
    @XmlAttribute(name = "class")
    protected List<String> clazz;
    @XmlAttribute(name = "exactclass")
    protected List<String> exactclass;
    @XmlAttribute(name = "hasmasstraffic")
    protected Boolean hasmasstraffic;
    @XmlAttribute(name = "numfreeactorslots")
    protected String numfreeactorslots;
    @XmlAttribute(name = "freemissionactorslot")
    protected String freemissionactorslot;
    @XmlAttribute(name = "docked")
    protected String docked;
    @XmlAttribute(name = "macro")
    protected String macro;
    @XmlAttribute(name = "owner")
    protected String owner;
    @XmlAttribute(name = "trueowner")
    protected String trueowner;
    @XmlAttribute(name = "coverowner")
    protected String coverowner;
    @XmlAttribute(name = "race")
    protected String race;
    @XmlAttribute(name = "tag")
    protected String tag;
    @XmlAttribute(name = "grouptag")
    protected String grouptag;
    @XmlAttribute(name = "integrated")
    protected String integrated;
    @XmlAttribute(name = "invulnerable")
    protected String invulnerable;
    @XmlAttribute(name = "hullinvulnerable")
    protected String hullinvulnerable;
    @XmlAttribute(name = "shieldinvulnerable")
    protected String shieldinvulnerable;
    @XmlAttribute(name = "checkoperational")
    protected String checkoperational;
    @XmlAttribute(name = "functional")
    protected String functional;
    @XmlAttribute(name = "surfaceelement")
    protected String surfaceelement;
    @XmlAttribute(name = "module")
    protected String module;
    @XmlAttribute(name = "known")
    protected Boolean known;
    @XmlAttribute(name = "knownto")
    protected String knownto;
    @XmlAttribute(name = "suspicious")
    protected Boolean suspicious;
    @XmlAttribute(name = "destination")
    protected String destination;
    @XmlAttribute(name = "hastradeoffer")
    protected Boolean hastradeoffer;
    @XmlAttribute(name = "canhaveofferlocation")
    protected String canhaveofferlocation;
    @XmlAttribute(name = "freemissionofferslot")
    protected Boolean freemissionofferslot;
    @XmlAttribute(name = "entitytype")
    protected String entitytype;
    @XmlAttribute(name = "controlentity")
    protected String controlentity;
    @XmlAttribute(name = "representative")
    protected String representative;
    @XmlAttribute(name = "masstraffic")
    protected String masstraffic;
    @XmlAttribute(name = "priorityzone")
    protected Boolean priorityzone;
    @XmlAttribute(name = "tempzone")
    protected Boolean tempzone;
    @XmlAttribute(name = "restorable")
    protected Boolean restorable;
    @XmlAttribute(name = "repairable")
    protected Boolean repairable;
    @XmlAttribute(name = "needsrepair")
    protected Boolean needsrepair;
    @XmlAttribute(name = "exact")
    protected String exact;
    @XmlAttribute(name = "list")
    protected String list;
    @XmlAttribute(name = "min")
    protected String min;
    @XmlAttribute(name = "max")
    protected String max;

    /**
     * Gets the value of the comment property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getComment() {
        return comment;
    }

    /**
     * Sets the value of the comment property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setComment(String value) {
        this.comment = value;
    }

    /**
     * Gets the value of the negate property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isNegate() {
        return negate;
    }

    /**
     * Sets the value of the negate property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setNegate(Boolean value) {
        this.negate = value;
    }

    /**
     * Gets the value of the clazz property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the clazz property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getClazz().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link String }
     * 
     * 
     */
    public List<String> getClazz() {
        if (clazz == null) {
            clazz = new ArrayList<String>();
        }
        return this.clazz;
    }

    /**
     * Gets the value of the exactclass property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the exactclass property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getExactclass().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link String }
     * 
     * 
     */
    public List<String> getExactclass() {
        if (exactclass == null) {
            exactclass = new ArrayList<String>();
        }
        return this.exactclass;
    }

    /**
     * Gets the value of the hasmasstraffic property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isHasmasstraffic() {
        return hasmasstraffic;
    }

    /**
     * Sets the value of the hasmasstraffic property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setHasmasstraffic(Boolean value) {
        this.hasmasstraffic = value;
    }

    /**
     * Gets the value of the numfreeactorslots property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNumfreeactorslots() {
        return numfreeactorslots;
    }

    /**
     * Sets the value of the numfreeactorslots property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNumfreeactorslots(String value) {
        this.numfreeactorslots = value;
    }

    /**
     * Gets the value of the freemissionactorslot property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFreemissionactorslot() {
        return freemissionactorslot;
    }

    /**
     * Sets the value of the freemissionactorslot property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFreemissionactorslot(String value) {
        this.freemissionactorslot = value;
    }

    /**
     * Gets the value of the docked property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDocked() {
        return docked;
    }

    /**
     * Sets the value of the docked property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDocked(String value) {
        this.docked = value;
    }

    /**
     * Gets the value of the macro property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMacro() {
        return macro;
    }

    /**
     * Sets the value of the macro property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMacro(String value) {
        this.macro = value;
    }

    /**
     * Gets the value of the owner property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOwner() {
        return owner;
    }

    /**
     * Sets the value of the owner property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOwner(String value) {
        this.owner = value;
    }

    /**
     * Gets the value of the trueowner property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTrueowner() {
        return trueowner;
    }

    /**
     * Sets the value of the trueowner property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTrueowner(String value) {
        this.trueowner = value;
    }

    /**
     * Gets the value of the coverowner property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCoverowner() {
        return coverowner;
    }

    /**
     * Sets the value of the coverowner property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCoverowner(String value) {
        this.coverowner = value;
    }

    /**
     * Gets the value of the race property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRace() {
        return race;
    }

    /**
     * Sets the value of the race property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRace(String value) {
        this.race = value;
    }

    /**
     * Gets the value of the tag property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTag() {
        return tag;
    }

    /**
     * Sets the value of the tag property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTag(String value) {
        this.tag = value;
    }

    /**
     * Gets the value of the grouptag property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getGrouptag() {
        return grouptag;
    }

    /**
     * Sets the value of the grouptag property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setGrouptag(String value) {
        this.grouptag = value;
    }

    /**
     * Gets the value of the integrated property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIntegrated() {
        return integrated;
    }

    /**
     * Sets the value of the integrated property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIntegrated(String value) {
        this.integrated = value;
    }

    /**
     * Gets the value of the invulnerable property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getInvulnerable() {
        return invulnerable;
    }

    /**
     * Sets the value of the invulnerable property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setInvulnerable(String value) {
        this.invulnerable = value;
    }

    /**
     * Gets the value of the hullinvulnerable property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getHullinvulnerable() {
        return hullinvulnerable;
    }

    /**
     * Sets the value of the hullinvulnerable property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setHullinvulnerable(String value) {
        this.hullinvulnerable = value;
    }

    /**
     * Gets the value of the shieldinvulnerable property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getShieldinvulnerable() {
        return shieldinvulnerable;
    }

    /**
     * Sets the value of the shieldinvulnerable property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setShieldinvulnerable(String value) {
        this.shieldinvulnerable = value;
    }

    /**
     * Gets the value of the checkoperational property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCheckoperational() {
        return checkoperational;
    }

    /**
     * Sets the value of the checkoperational property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCheckoperational(String value) {
        this.checkoperational = value;
    }

    /**
     * Gets the value of the functional property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFunctional() {
        return functional;
    }

    /**
     * Sets the value of the functional property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFunctional(String value) {
        this.functional = value;
    }

    /**
     * Gets the value of the surfaceelement property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSurfaceelement() {
        return surfaceelement;
    }

    /**
     * Sets the value of the surfaceelement property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSurfaceelement(String value) {
        this.surfaceelement = value;
    }

    /**
     * Gets the value of the module property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getModule() {
        return module;
    }

    /**
     * Sets the value of the module property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setModule(String value) {
        this.module = value;
    }

    /**
     * Gets the value of the known property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isKnown() {
        return known;
    }

    /**
     * Sets the value of the known property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setKnown(Boolean value) {
        this.known = value;
    }

    /**
     * Gets the value of the knownto property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getKnownto() {
        return knownto;
    }

    /**
     * Sets the value of the knownto property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setKnownto(String value) {
        this.knownto = value;
    }

    /**
     * Gets the value of the suspicious property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isSuspicious() {
        return suspicious;
    }

    /**
     * Sets the value of the suspicious property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setSuspicious(Boolean value) {
        this.suspicious = value;
    }

    /**
     * Gets the value of the destination property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDestination() {
        return destination;
    }

    /**
     * Sets the value of the destination property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDestination(String value) {
        this.destination = value;
    }

    /**
     * Gets the value of the hastradeoffer property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isHastradeoffer() {
        return hastradeoffer;
    }

    /**
     * Sets the value of the hastradeoffer property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setHastradeoffer(Boolean value) {
        this.hastradeoffer = value;
    }

    /**
     * Gets the value of the canhaveofferlocation property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCanhaveofferlocation() {
        return canhaveofferlocation;
    }

    /**
     * Sets the value of the canhaveofferlocation property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCanhaveofferlocation(String value) {
        this.canhaveofferlocation = value;
    }

    /**
     * Gets the value of the freemissionofferslot property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isFreemissionofferslot() {
        return freemissionofferslot;
    }

    /**
     * Sets the value of the freemissionofferslot property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setFreemissionofferslot(Boolean value) {
        this.freemissionofferslot = value;
    }

    /**
     * Gets the value of the entitytype property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEntitytype() {
        return entitytype;
    }

    /**
     * Sets the value of the entitytype property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEntitytype(String value) {
        this.entitytype = value;
    }

    /**
     * Gets the value of the controlentity property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getControlentity() {
        return controlentity;
    }

    /**
     * Sets the value of the controlentity property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setControlentity(String value) {
        this.controlentity = value;
    }

    /**
     * Gets the value of the representative property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRepresentative() {
        return representative;
    }

    /**
     * Sets the value of the representative property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRepresentative(String value) {
        this.representative = value;
    }

    /**
     * Gets the value of the masstraffic property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMasstraffic() {
        return masstraffic;
    }

    /**
     * Sets the value of the masstraffic property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMasstraffic(String value) {
        this.masstraffic = value;
    }

    /**
     * Gets the value of the priorityzone property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isPriorityzone() {
        return priorityzone;
    }

    /**
     * Sets the value of the priorityzone property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setPriorityzone(Boolean value) {
        this.priorityzone = value;
    }

    /**
     * Gets the value of the tempzone property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isTempzone() {
        return tempzone;
    }

    /**
     * Sets the value of the tempzone property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setTempzone(Boolean value) {
        this.tempzone = value;
    }

    /**
     * Gets the value of the restorable property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isRestorable() {
        return restorable;
    }

    /**
     * Sets the value of the restorable property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setRestorable(Boolean value) {
        this.restorable = value;
    }

    /**
     * Gets the value of the repairable property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isRepairable() {
        return repairable;
    }

    /**
     * Sets the value of the repairable property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setRepairable(Boolean value) {
        this.repairable = value;
    }

    /**
     * Gets the value of the needsrepair property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isNeedsrepair() {
        return needsrepair;
    }

    /**
     * Sets the value of the needsrepair property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setNeedsrepair(Boolean value) {
        this.needsrepair = value;
    }

    /**
     * Gets the value of the exact property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getExact() {
        return exact;
    }

    /**
     * Sets the value of the exact property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setExact(String value) {
        this.exact = value;
    }

    /**
     * Gets the value of the list property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getList() {
        return list;
    }

    /**
     * Sets the value of the list property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setList(String value) {
        this.list = value;
    }

    /**
     * Gets the value of the min property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMin() {
        return min;
    }

    /**
     * Sets the value of the min property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMin(String value) {
        this.min = value;
    }

    /**
     * Gets the value of the max property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMax() {
        return max;
    }

    /**
     * Sets the value of the max property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMax(String value) {
        this.max = value;
    }

}

//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.4-2 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2013.12.28 at 10:09:30 PM CET 
//


package nl.games.xrebirth.generated.unlocks;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for global complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="global">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="infopoint" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;attGroup ref="{}memory"/>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="missionoffer" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;attGroup ref="{}memory"/>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="platform" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;attGroup ref="{}memory"/>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="tradeoffer" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;attGroup ref="{}memory"/>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *       &lt;/sequence>
 *       &lt;attribute name="maxdistance" type="{}unitvalue" />
 *       &lt;attribute name="threshold" type="{}unitvalue" />
 *       &lt;attribute name="rechecktime" type="{}unitvalue" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "global", propOrder = {
    "infopoint",
    "missionoffer",
    "platform",
    "tradeoffer"
})
public class Global {

    protected Global.Infopoint infopoint;
    protected Global.Missionoffer missionoffer;
    protected Global.Platform platform;
    protected Global.Tradeoffer tradeoffer;
    @XmlAttribute(name = "maxdistance")
    protected String maxdistance;
    @XmlAttribute(name = "threshold")
    protected String threshold;
    @XmlAttribute(name = "rechecktime")
    protected String rechecktime;

    /**
     * Gets the value of the infopoint property.
     * 
     * @return
     *     possible object is
     *     {@link Global.Infopoint }
     *     
     */
    public Global.Infopoint getInfopoint() {
        return infopoint;
    }

    /**
     * Sets the value of the infopoint property.
     * 
     * @param value
     *     allowed object is
     *     {@link Global.Infopoint }
     *     
     */
    public void setInfopoint(Global.Infopoint value) {
        this.infopoint = value;
    }

    /**
     * Gets the value of the missionoffer property.
     * 
     * @return
     *     possible object is
     *     {@link Global.Missionoffer }
     *     
     */
    public Global.Missionoffer getMissionoffer() {
        return missionoffer;
    }

    /**
     * Sets the value of the missionoffer property.
     * 
     * @param value
     *     allowed object is
     *     {@link Global.Missionoffer }
     *     
     */
    public void setMissionoffer(Global.Missionoffer value) {
        this.missionoffer = value;
    }

    /**
     * Gets the value of the platform property.
     * 
     * @return
     *     possible object is
     *     {@link Global.Platform }
     *     
     */
    public Global.Platform getPlatform() {
        return platform;
    }

    /**
     * Sets the value of the platform property.
     * 
     * @param value
     *     allowed object is
     *     {@link Global.Platform }
     *     
     */
    public void setPlatform(Global.Platform value) {
        this.platform = value;
    }

    /**
     * Gets the value of the tradeoffer property.
     * 
     * @return
     *     possible object is
     *     {@link Global.Tradeoffer }
     *     
     */
    public Global.Tradeoffer getTradeoffer() {
        return tradeoffer;
    }

    /**
     * Sets the value of the tradeoffer property.
     * 
     * @param value
     *     allowed object is
     *     {@link Global.Tradeoffer }
     *     
     */
    public void setTradeoffer(Global.Tradeoffer value) {
        this.tradeoffer = value;
    }

    /**
     * Gets the value of the maxdistance property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMaxdistance() {
        return maxdistance;
    }

    /**
     * Sets the value of the maxdistance property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMaxdistance(String value) {
        this.maxdistance = value;
    }

    /**
     * Gets the value of the threshold property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getThreshold() {
        return threshold;
    }

    /**
     * Sets the value of the threshold property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setThreshold(String value) {
        this.threshold = value;
    }

    /**
     * Gets the value of the rechecktime property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRechecktime() {
        return rechecktime;
    }

    /**
     * Sets the value of the rechecktime property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRechecktime(String value) {
        this.rechecktime = value;
    }


    /**
     * <p>Java class for anonymous complex type.
     * 
     * <p>The following schema fragment specifies the expected content contained within this class.
     * 
     * <pre>
     * &lt;complexType>
     *   &lt;complexContent>
     *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *       &lt;attGroup ref="{}memory"/>
     *     &lt;/restriction>
     *   &lt;/complexContent>
     * &lt;/complexType>
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "")
    public static class Infopoint {

        @XmlAttribute(name = "memorydistance")
        protected String memorydistance;
        @XmlAttribute(name = "memoryangle")
        protected Float memoryangle;
        @XmlAttribute(name = "memorytime")
        protected String memorytime;

        /**
         * Gets the value of the memorydistance property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getMemorydistance() {
            return memorydistance;
        }

        /**
         * Sets the value of the memorydistance property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setMemorydistance(String value) {
            this.memorydistance = value;
        }

        /**
         * Gets the value of the memoryangle property.
         * 
         * @return
         *     possible object is
         *     {@link Float }
         *     
         */
        public Float getMemoryangle() {
            return memoryangle;
        }

        /**
         * Sets the value of the memoryangle property.
         * 
         * @param value
         *     allowed object is
         *     {@link Float }
         *     
         */
        public void setMemoryangle(Float value) {
            this.memoryangle = value;
        }

        /**
         * Gets the value of the memorytime property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getMemorytime() {
            return memorytime;
        }

        /**
         * Sets the value of the memorytime property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setMemorytime(String value) {
            this.memorytime = value;
        }

    }


    /**
     * <p>Java class for anonymous complex type.
     * 
     * <p>The following schema fragment specifies the expected content contained within this class.
     * 
     * <pre>
     * &lt;complexType>
     *   &lt;complexContent>
     *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *       &lt;attGroup ref="{}memory"/>
     *     &lt;/restriction>
     *   &lt;/complexContent>
     * &lt;/complexType>
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "")
    public static class Missionoffer {

        @XmlAttribute(name = "memorydistance")
        protected String memorydistance;
        @XmlAttribute(name = "memoryangle")
        protected Float memoryangle;
        @XmlAttribute(name = "memorytime")
        protected String memorytime;

        /**
         * Gets the value of the memorydistance property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getMemorydistance() {
            return memorydistance;
        }

        /**
         * Sets the value of the memorydistance property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setMemorydistance(String value) {
            this.memorydistance = value;
        }

        /**
         * Gets the value of the memoryangle property.
         * 
         * @return
         *     possible object is
         *     {@link Float }
         *     
         */
        public Float getMemoryangle() {
            return memoryangle;
        }

        /**
         * Sets the value of the memoryangle property.
         * 
         * @param value
         *     allowed object is
         *     {@link Float }
         *     
         */
        public void setMemoryangle(Float value) {
            this.memoryangle = value;
        }

        /**
         * Gets the value of the memorytime property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getMemorytime() {
            return memorytime;
        }

        /**
         * Sets the value of the memorytime property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setMemorytime(String value) {
            this.memorytime = value;
        }

    }


    /**
     * <p>Java class for anonymous complex type.
     * 
     * <p>The following schema fragment specifies the expected content contained within this class.
     * 
     * <pre>
     * &lt;complexType>
     *   &lt;complexContent>
     *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *       &lt;attGroup ref="{}memory"/>
     *     &lt;/restriction>
     *   &lt;/complexContent>
     * &lt;/complexType>
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "")
    public static class Platform {

        @XmlAttribute(name = "memorydistance")
        protected String memorydistance;
        @XmlAttribute(name = "memoryangle")
        protected Float memoryangle;
        @XmlAttribute(name = "memorytime")
        protected String memorytime;

        /**
         * Gets the value of the memorydistance property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getMemorydistance() {
            return memorydistance;
        }

        /**
         * Sets the value of the memorydistance property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setMemorydistance(String value) {
            this.memorydistance = value;
        }

        /**
         * Gets the value of the memoryangle property.
         * 
         * @return
         *     possible object is
         *     {@link Float }
         *     
         */
        public Float getMemoryangle() {
            return memoryangle;
        }

        /**
         * Sets the value of the memoryangle property.
         * 
         * @param value
         *     allowed object is
         *     {@link Float }
         *     
         */
        public void setMemoryangle(Float value) {
            this.memoryangle = value;
        }

        /**
         * Gets the value of the memorytime property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getMemorytime() {
            return memorytime;
        }

        /**
         * Sets the value of the memorytime property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setMemorytime(String value) {
            this.memorytime = value;
        }

    }


    /**
     * <p>Java class for anonymous complex type.
     * 
     * <p>The following schema fragment specifies the expected content contained within this class.
     * 
     * <pre>
     * &lt;complexType>
     *   &lt;complexContent>
     *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *       &lt;attGroup ref="{}memory"/>
     *     &lt;/restriction>
     *   &lt;/complexContent>
     * &lt;/complexType>
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "")
    public static class Tradeoffer {

        @XmlAttribute(name = "memorydistance")
        protected String memorydistance;
        @XmlAttribute(name = "memoryangle")
        protected Float memoryangle;
        @XmlAttribute(name = "memorytime")
        protected String memorytime;

        /**
         * Gets the value of the memorydistance property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getMemorydistance() {
            return memorydistance;
        }

        /**
         * Sets the value of the memorydistance property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setMemorydistance(String value) {
            this.memorydistance = value;
        }

        /**
         * Gets the value of the memoryangle property.
         * 
         * @return
         *     possible object is
         *     {@link Float }
         *     
         */
        public Float getMemoryangle() {
            return memoryangle;
        }

        /**
         * Sets the value of the memoryangle property.
         * 
         * @param value
         *     allowed object is
         *     {@link Float }
         *     
         */
        public void setMemoryangle(Float value) {
            this.memoryangle = value;
        }

        /**
         * Gets the value of the memorytime property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getMemorytime() {
            return memorytime;
        }

        /**
         * Sets the value of the memorytime property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setMemorytime(String value) {
            this.memorytime = value;
        }

    }

}

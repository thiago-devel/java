
package br.com.cardif.wsdirectsales;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;

/**
 * <p>
 * Classe Java de identification complex type.
 * 
 * <p>
 * O seguinte fragmento do esquema especifica o conte�do esperado contido dentro
 * desta classe.
 * 
 * <pre>
 * &lt;complexType name="identification"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="description" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="ID" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "identification", propOrder = { "description", "id" })
@XmlSeeAlso({ LoginDTO.class, Partner.class, CertificateDTO.class, Coverage.class, PaymentMethod.class, Workshop.class,
        CapitalSeries.class })
public class Identification {

    protected String description;
    @XmlElement(name = "ID")
    protected Integer id;

    /**
     * Obt�m o valor da propriedade description.
     * 
     * @return possible object is {@link String }
     * 
     */
    public String getDescription() {
        return description;
    }

    /**
     * Define o valor da propriedade description.
     * 
     * @param value
     *            allowed object is {@link String }
     * 
     */
    public void setDescription(String value) {
        this.description = value;
    }

    /**
     * Obt�m o valor da propriedade id.
     * 
     * @return possible object is {@link Integer }
     * 
     */
    public Integer getID() {
        return id;
    }

    /**
     * Define o valor da propriedade id.
     * 
     * @param value
     *            allowed object is {@link Integer }
     * 
     */
    public void setID(Integer value) {
        this.id = value;
    }

}

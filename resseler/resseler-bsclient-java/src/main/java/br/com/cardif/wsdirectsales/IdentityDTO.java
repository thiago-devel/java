
package br.com.cardif.wsdirectsales;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;

/**
 * <p>
 * Classe Java de identityDTO complex type.
 * 
 * <p>
 * O seguinte fragmento do esquema especifica o conte�do esperado contido dentro
 * desta classe.
 * 
 * <pre>
 * &lt;complexType name="identityDTO"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="documentType" type="{http://wsdirectsales.cardif.com.br/}document" minOccurs="0"/&gt;
 *         &lt;element name="documentValue" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "identityDTO", propOrder = { "documentType", "documentValue" })
public class IdentityDTO {

    @XmlSchemaType(name = "string")
    protected Document documentType;
    protected String documentValue;

    /**
     * Obt�m o valor da propriedade documentType.
     * 
     * @return possible object is {@link Document }
     * 
     */
    public Document getDocumentType() {
        return documentType;
    }

    /**
     * Define o valor da propriedade documentType.
     * 
     * @param value
     *            allowed object is {@link Document }
     * 
     */
    public void setDocumentType(Document value) {
        this.documentType = value;
    }

    /**
     * Obt�m o valor da propriedade documentValue.
     * 
     * @return possible object is {@link String }
     * 
     */
    public String getDocumentValue() {
        return documentValue;
    }

    /**
     * Define o valor da propriedade documentValue.
     * 
     * @param value
     *            allowed object is {@link String }
     * 
     */
    public void setDocumentValue(String value) {
        this.documentValue = value;
    }

}

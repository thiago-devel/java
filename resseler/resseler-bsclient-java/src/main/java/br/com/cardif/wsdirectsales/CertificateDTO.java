
package br.com.cardif.wsdirectsales;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;

/**
 * <p>
 * Classe Java de certificateDTO complex type.
 * 
 * <p>
 * O seguinte fragmento do esquema especifica o conte�do esperado contido dentro
 * desta classe.
 * 
 * <pre>
 * &lt;complexType name="certificateDTO"&gt;
 *   &lt;complexContent&gt;
 *     &lt;extension base="{http://wsdirectsales.cardif.com.br/}identification"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="capitalSeries" type="{http://wsdirectsales.cardif.com.br/}capitalSeries" minOccurs="0"/&gt;
 *         &lt;element name="certificateCreationDate" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/&gt;
 *         &lt;element name="certificateValidityEndDate" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/&gt;
 *         &lt;element name="certificateValidityInitDate" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/&gt;
 *         &lt;element name="contractNumber" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="policeNumber" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/extension&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "certificateDTO", propOrder = { "capitalSeries", "certificateCreationDate",
        "certificateValidityEndDate", "certificateValidityInitDate", "contractNumber", "policeNumber" })
@XmlSeeAlso({ InsuranceCertificate.class })
public class CertificateDTO extends Identification {

    protected CapitalSeries capitalSeries;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar certificateCreationDate;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar certificateValidityEndDate;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar certificateValidityInitDate;
    protected String contractNumber;
    protected String policeNumber;

    /**
     * Obt�m o valor da propriedade capitalSeries.
     * 
     * @return possible object is {@link CapitalSeries }
     * 
     */
    public CapitalSeries getCapitalSeries() {
        return capitalSeries;
    }

    /**
     * Define o valor da propriedade capitalSeries.
     * 
     * @param value
     *            allowed object is {@link CapitalSeries }
     * 
     */
    public void setCapitalSeries(CapitalSeries value) {
        this.capitalSeries = value;
    }

    /**
     * Obt�m o valor da propriedade certificateCreationDate.
     * 
     * @return possible object is {@link XMLGregorianCalendar }
     * 
     */
    public XMLGregorianCalendar getCertificateCreationDate() {
        return certificateCreationDate;
    }

    /**
     * Define o valor da propriedade certificateCreationDate.
     * 
     * @param value
     *            allowed object is {@link XMLGregorianCalendar }
     * 
     */
    public void setCertificateCreationDate(XMLGregorianCalendar value) {
        this.certificateCreationDate = value;
    }

    /**
     * Obt�m o valor da propriedade certificateValidityEndDate.
     * 
     * @return possible object is {@link XMLGregorianCalendar }
     * 
     */
    public XMLGregorianCalendar getCertificateValidityEndDate() {
        return certificateValidityEndDate;
    }

    /**
     * Define o valor da propriedade certificateValidityEndDate.
     * 
     * @param value
     *            allowed object is {@link XMLGregorianCalendar }
     * 
     */
    public void setCertificateValidityEndDate(XMLGregorianCalendar value) {
        this.certificateValidityEndDate = value;
    }

    /**
     * Obt�m o valor da propriedade certificateValidityInitDate.
     * 
     * @return possible object is {@link XMLGregorianCalendar }
     * 
     */
    public XMLGregorianCalendar getCertificateValidityInitDate() {
        return certificateValidityInitDate;
    }

    /**
     * Define o valor da propriedade certificateValidityInitDate.
     * 
     * @param value
     *            allowed object is {@link XMLGregorianCalendar }
     * 
     */
    public void setCertificateValidityInitDate(XMLGregorianCalendar value) {
        this.certificateValidityInitDate = value;
    }

    /**
     * Obt�m o valor da propriedade contractNumber.
     * 
     * @return possible object is {@link String }
     * 
     */
    public String getContractNumber() {
        return contractNumber;
    }

    /**
     * Define o valor da propriedade contractNumber.
     * 
     * @param value
     *            allowed object is {@link String }
     * 
     */
    public void setContractNumber(String value) {
        this.contractNumber = value;
    }

    /**
     * Obt�m o valor da propriedade policeNumber.
     * 
     * @return possible object is {@link String }
     * 
     */
    public String getPoliceNumber() {
        return policeNumber;
    }

    /**
     * Define o valor da propriedade policeNumber.
     * 
     * @param value
     *            allowed object is {@link String }
     * 
     */
    public void setPoliceNumber(String value) {
        this.policeNumber = value;
    }

}


package br.com.cardif.wsdirectsales;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

/**
 * <p>
 * Classe Java de certificates complex type.
 *
 * <p>
 * O seguinte fragmento do esquema especifica o conte�do esperado contido dentro
 * desta classe.
 *
 * <pre>
 * &lt;complexType name="certificates"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="certificate" type="{http://wsdirectsales.cardif.com.br/}insuranceCertificate" maxOccurs="unbounded" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 *
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "certificates", propOrder = { "certificate" })
public class Certificates {

    @XmlElement(nillable = true)
    protected List<InsuranceCertificate> certificate;

    /**
     * Gets the value of the certificate property.
     *
     * <p>
     * This accessor method returns a reference to the live list, not a
     * snapshot. Therefore any modification you make to the returned list will
     * be present inside the JAXB object. This is why there is not a
     * <CODE>set</CODE> method for the certificate property.
     *
     * <p>
     * For example, to add a new item, do as follows:
     * 
     * <pre>
     * getCertificate().add(newItem);
     * </pre>
     *
     *
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link InsuranceCertificate }
     *
     *
     */
    public List<InsuranceCertificate> getCertificate() {
        if (certificate == null) {
            certificate = new ArrayList<InsuranceCertificate>();
        }
        return certificate;
    }

    @Override
    public String toString() {
        return "Certificates [certificate=" + certificate + "]";
    }

}


package br.com.cardif.wsdirectsales;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

/**
 * <p>
 * Classe Java de doSaleResponse complex type.
 * 
 * <p>
 * O seguinte fragmento do esquema especifica o conte�do esperado contido dentro
 * desta classe.
 * 
 * <pre>
 * &lt;complexType name="doSaleResponse"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="certificate" type="{http://wsdirectsales.cardif.com.br/}insuranceCertificate" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "doSaleResponse", propOrder = { "certificate" })
public class DoSaleResponse {

    protected InsuranceCertificate certificate;

    /**
     * Obt�m o valor da propriedade certificate.
     * 
     * @return possible object is {@link InsuranceCertificate }
     * 
     */
    public InsuranceCertificate getCertificate() {
        return certificate;
    }

    /**
     * Define o valor da propriedade certificate.
     * 
     * @param value
     *            allowed object is {@link InsuranceCertificate }
     * 
     */
    public void setCertificate(InsuranceCertificate value) {
        this.certificate = value;
    }

}


package br.com.cardif.wsdirectsales;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

/**
 * <p>
 * Classe Java de retrieveCustomerResponse complex type.
 *
 * <p>
 * O seguinte fragmento do esquema especifica o conte�do esperado contido dentro
 * desta classe.
 *
 * <pre>
 * &lt;complexType name="retrieveCustomerResponse"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="certificates" type="{http://wsdirectsales.cardif.com.br/}certificates" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 *
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "retrieveCustomerResponse", propOrder = { "certificates" })
public class RetrieveCustomerResponse {

    protected Certificates certificates;

    /**
     * Obt�m o valor da propriedade certificates.
     *
     * @return possible object is {@link Certificates }
     * 
     */
    public Certificates getCertificates() {
        return certificates;
    }

    /**
     * Define o valor da propriedade certificates.
     *
     * @param value
     *            allowed object is {@link Certificates }
     * 
     */
    public void setCertificates(final Certificates value) {
        certificates = value;
    }

    @Override
    public String toString() {
        return "RetrieveCustomerResponse [certificates=" + certificates + "]";
    }

}

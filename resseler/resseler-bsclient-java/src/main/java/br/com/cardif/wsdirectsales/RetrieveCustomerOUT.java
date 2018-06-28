
package br.com.cardif.wsdirectsales;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

/**
 * <p>
 * Classe Java de retrieveCustomerOUT complex type.
 * 
 * <p>
 * O seguinte fragmento do esquema especifica o conte�do esperado contido dentro
 * desta classe.
 * 
 * <pre>
 * &lt;complexType name="retrieveCustomerOUT"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="retrieveCustomerResponse" type="{http://wsdirectsales.cardif.com.br/}retrieveCustomerResponse" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "retrieveCustomerOUT", propOrder = { "retrieveCustomerResponse" })
public class RetrieveCustomerOUT {

    protected RetrieveCustomerResponse retrieveCustomerResponse;

    /**
     * Obt�m o valor da propriedade retrieveCustomerResponse.
     * 
     * @return possible object is {@link RetrieveCustomerResponse }
     * 
     */
    public RetrieveCustomerResponse getRetrieveCustomerResponse() {
        return retrieveCustomerResponse;
    }

    /**
     * Define o valor da propriedade retrieveCustomerResponse.
     * 
     * @param value
     *            allowed object is {@link RetrieveCustomerResponse }
     * 
     */
    public void setRetrieveCustomerResponse(RetrieveCustomerResponse value) {
        this.retrieveCustomerResponse = value;
    }

}

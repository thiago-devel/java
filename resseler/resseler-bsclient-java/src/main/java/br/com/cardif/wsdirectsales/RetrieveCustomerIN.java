
package br.com.cardif.wsdirectsales;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

/**
 * <p>
 * Classe Java de retrieveCustomerIN complex type.
 * 
 * <p>
 * O seguinte fragmento do esquema especifica o conte�do esperado contido dentro
 * desta classe.
 * 
 * <pre>
 * &lt;complexType name="retrieveCustomerIN"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="retrieveCustomerRequest" type="{http://wsdirectsales.cardif.com.br/}retrieveCustomerRequest" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "retrieveCustomerIN", propOrder = { "retrieveCustomerRequest" })
public class RetrieveCustomerIN {

    protected RetrieveCustomerRequest retrieveCustomerRequest;

    /**
     * Obt�m o valor da propriedade retrieveCustomerRequest.
     * 
     * @return possible object is {@link RetrieveCustomerRequest }
     * 
     */
    public RetrieveCustomerRequest getRetrieveCustomerRequest() {
        return retrieveCustomerRequest;
    }

    /**
     * Define o valor da propriedade retrieveCustomerRequest.
     * 
     * @param value
     *            allowed object is {@link RetrieveCustomerRequest }
     * 
     */
    public void setRetrieveCustomerRequest(RetrieveCustomerRequest value) {
        this.retrieveCustomerRequest = value;
    }

}

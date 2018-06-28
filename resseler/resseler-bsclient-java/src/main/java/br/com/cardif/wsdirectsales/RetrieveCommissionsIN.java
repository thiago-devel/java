
package br.com.cardif.wsdirectsales;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

/**
 * <p>
 * Classe Java de retrieveCommissionsIN complex type.
 * 
 * <p>
 * O seguinte fragmento do esquema especifica o conte�do esperado contido dentro
 * desta classe.
 * 
 * <pre>
 * &lt;complexType name="retrieveCommissionsIN"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="retrieveCommissionsRequest" type="{http://wsdirectsales.cardif.com.br/}retrieveCommissionsRequest" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "retrieveCommissionsIN", propOrder = { "retrieveCommissionsRequest" })
public class RetrieveCommissionsIN {

    protected RetrieveCommissionsRequest retrieveCommissionsRequest;

    /**
     * Obt�m o valor da propriedade retrieveCommissionsRequest.
     * 
     * @return possible object is {@link RetrieveCommissionsRequest }
     * 
     */
    public RetrieveCommissionsRequest getRetrieveCommissionsRequest() {
        return retrieveCommissionsRequest;
    }

    /**
     * Define o valor da propriedade retrieveCommissionsRequest.
     * 
     * @param value
     *            allowed object is {@link RetrieveCommissionsRequest }
     * 
     */
    public void setRetrieveCommissionsRequest(RetrieveCommissionsRequest value) {
        this.retrieveCommissionsRequest = value;
    }

}


package br.com.cardif.wsdirectsales;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

/**
 * <p>
 * Classe Java de retrieveCommissionsOUT complex type.
 * 
 * <p>
 * O seguinte fragmento do esquema especifica o conte�do esperado contido dentro
 * desta classe.
 * 
 * <pre>
 * &lt;complexType name="retrieveCommissionsOUT"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="retrieveCommissionsResponse" type="{http://wsdirectsales.cardif.com.br/}retrieveCommissionsResponse" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "retrieveCommissionsOUT", propOrder = { "retrieveCommissionsResponse" })
public class RetrieveCommissionsOUT {

    protected RetrieveCommissionsResponse retrieveCommissionsResponse;

    /**
     * Obt�m o valor da propriedade retrieveCommissionsResponse.
     * 
     * @return possible object is {@link RetrieveCommissionsResponse }
     * 
     */
    public RetrieveCommissionsResponse getRetrieveCommissionsResponse() {
        return retrieveCommissionsResponse;
    }

    /**
     * Define o valor da propriedade retrieveCommissionsResponse.
     * 
     * @param value
     *            allowed object is {@link RetrieveCommissionsResponse }
     * 
     */
    public void setRetrieveCommissionsResponse(RetrieveCommissionsResponse value) {
        this.retrieveCommissionsResponse = value;
    }

}

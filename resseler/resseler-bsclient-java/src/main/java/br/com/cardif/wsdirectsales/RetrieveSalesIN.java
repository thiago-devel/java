
package br.com.cardif.wsdirectsales;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

/**
 * <p>
 * Classe Java de retrieveSalesIN complex type.
 * 
 * <p>
 * O seguinte fragmento do esquema especifica o conte�do esperado contido dentro
 * desta classe.
 * 
 * <pre>
 * &lt;complexType name="retrieveSalesIN"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="retrieveSalesRequest" type="{http://wsdirectsales.cardif.com.br/}retrieveSalesRequest" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "retrieveSalesIN", propOrder = { "retrieveSalesRequest" })
public class RetrieveSalesIN {

    protected RetrieveSalesRequest retrieveSalesRequest;

    /**
     * Obt�m o valor da propriedade retrieveSalesRequest.
     * 
     * @return possible object is {@link RetrieveSalesRequest }
     * 
     */
    public RetrieveSalesRequest getRetrieveSalesRequest() {
        return retrieveSalesRequest;
    }

    /**
     * Define o valor da propriedade retrieveSalesRequest.
     * 
     * @param value
     *            allowed object is {@link RetrieveSalesRequest }
     * 
     */
    public void setRetrieveSalesRequest(RetrieveSalesRequest value) {
        this.retrieveSalesRequest = value;
    }

}

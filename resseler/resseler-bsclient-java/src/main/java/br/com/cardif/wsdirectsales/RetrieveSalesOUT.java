
package br.com.cardif.wsdirectsales;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

/**
 * <p>
 * Classe Java de retrieveSalesOUT complex type.
 * 
 * <p>
 * O seguinte fragmento do esquema especifica o conte�do esperado contido dentro
 * desta classe.
 * 
 * <pre>
 * &lt;complexType name="retrieveSalesOUT"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="retrieveSalesResponse" type="{http://wsdirectsales.cardif.com.br/}retrieveSalesResponse" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "retrieveSalesOUT", propOrder = { "retrieveSalesResponse" })
public class RetrieveSalesOUT {

    protected RetrieveSalesResponse retrieveSalesResponse;

    /**
     * Obt�m o valor da propriedade retrieveSalesResponse.
     * 
     * @return possible object is {@link RetrieveSalesResponse }
     * 
     */
    public RetrieveSalesResponse getRetrieveSalesResponse() {
        return retrieveSalesResponse;
    }

    /**
     * Define o valor da propriedade retrieveSalesResponse.
     * 
     * @param value
     *            allowed object is {@link RetrieveSalesResponse }
     * 
     */
    public void setRetrieveSalesResponse(RetrieveSalesResponse value) {
        this.retrieveSalesResponse = value;
    }

}

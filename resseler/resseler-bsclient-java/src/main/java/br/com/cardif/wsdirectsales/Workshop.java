
package br.com.cardif.wsdirectsales;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

/**
 * <p>
 * Classe Java de workshop complex type.
 * 
 * <p>
 * O seguinte fragmento do esquema especifica o conte�do esperado contido dentro
 * desta classe.
 * 
 * <pre>
 * &lt;complexType name="workshop"&gt;
 *   &lt;complexContent&gt;
 *     &lt;extension base="{http://wsdirectsales.cardif.com.br/}identification"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="regionalID" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/&gt;
 *         &lt;element name="sallers" type="{http://wsdirectsales.cardif.com.br/}salesman" maxOccurs="unbounded" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/extension&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "workshop", propOrder = { "regionalID", "sallers" })
public class Workshop extends Identification {

    protected Integer regionalID;
    @XmlElement(nillable = true)
    protected List<Salesman> sallers;

    /**
     * Obt�m o valor da propriedade regionalID.
     * 
     * @return possible object is {@link Integer }
     * 
     */
    public Integer getRegionalID() {
        return regionalID;
    }

    /**
     * Define o valor da propriedade regionalID.
     * 
     * @param value
     *            allowed object is {@link Integer }
     * 
     */
    public void setRegionalID(Integer value) {
        this.regionalID = value;
    }

    /**
     * Gets the value of the sallers property.
     * 
     * <p>
     * This accessor method returns a reference to the live list, not a
     * snapshot. Therefore any modification you make to the returned list will
     * be present inside the JAXB object. This is why there is not a
     * <CODE>set</CODE> method for the sallers property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * 
     * <pre>
     * getSallers().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list {@link Salesman
     * }
     * 
     * 
     */
    public List<Salesman> getSallers() {
        if (sallers == null) {
            sallers = new ArrayList<Salesman>();
        }
        return this.sallers;
    }

}

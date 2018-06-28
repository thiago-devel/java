
package br.com.cardif.wsdirectsales;

import java.math.BigDecimal;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

/**
 * <p>
 * Classe Java de coverage complex type.
 * 
 * <p>
 * O seguinte fragmento do esquema especifica o conte�do esperado contido dentro
 * desta classe.
 * 
 * <pre>
 * &lt;complexType name="coverage"&gt;
 *   &lt;complexContent&gt;
 *     &lt;extension base="{http://wsdirectsales.cardif.com.br/}identification"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="coveragePremium" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/&gt;
 *         &lt;element name="coverageValueLimit" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/extension&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "coverage", propOrder = { "coveragePremium", "coverageValueLimit" })
public class Coverage extends Identification {

    protected BigDecimal coveragePremium;
    protected BigDecimal coverageValueLimit;

    /**
     * Obt�m o valor da propriedade coveragePremium.
     * 
     * @return possible object is {@link BigDecimal }
     * 
     */
    public BigDecimal getCoveragePremium() {
        return coveragePremium;
    }

    /**
     * Define o valor da propriedade coveragePremium.
     * 
     * @param value
     *            allowed object is {@link BigDecimal }
     * 
     */
    public void setCoveragePremium(BigDecimal value) {
        this.coveragePremium = value;
    }

    /**
     * Obt�m o valor da propriedade coverageValueLimit.
     * 
     * @return possible object is {@link BigDecimal }
     * 
     */
    public BigDecimal getCoverageValueLimit() {
        return coverageValueLimit;
    }

    /**
     * Define o valor da propriedade coverageValueLimit.
     * 
     * @param value
     *            allowed object is {@link BigDecimal }
     * 
     */
    public void setCoverageValueLimit(BigDecimal value) {
        this.coverageValueLimit = value;
    }

}


package br.com.cardif.wsdirectsales;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;

/**
 * <p>
 * Classe Java de document.
 * 
 * <p>
 * O seguinte fragmento do esquema especifica o conte�do esperado contido dentro
 * desta classe.
 * <p>
 * 
 * <pre>
 * &lt;simpleType name="document"&gt;
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *     &lt;enumeration value="CPF"/&gt;
 *     &lt;enumeration value="CNPJ"/&gt;
 *   &lt;/restriction&gt;
 * &lt;/simpleType&gt;
 * </pre>
 * 
 */
@XmlType(name = "document")
@XmlEnum
public enum Document {

    CPF, CNPJ;

    public String value() {
        return name();
    }

    public static Document fromValue(String v) {
        return valueOf(v);
    }

}

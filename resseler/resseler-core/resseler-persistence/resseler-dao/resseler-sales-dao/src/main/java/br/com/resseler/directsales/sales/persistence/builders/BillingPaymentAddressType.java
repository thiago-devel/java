package br.com.resseler.directsales.sales.persistence.builders;

/**
 * Class BillingPaymentAddressType
 * @author b11527
 *
 */
public class BillingPaymentAddressType {

    private String logradouro = null;
    private String numeroEndereco = null;
    private String cidade = null;
    private String CEP = null;
    private String bairro = null;
    private String UF = null;
    private String complemento = null;

    /**
     * the logradouro
     * @return the logradouro
     */
    public String getLogradouro() {
        return logradouro;
    }

    /**
     * the logradouro to set
     * @param logradouro 
     */
    public void setLogradouro(String logradouro) {
        this.logradouro = logradouro;
    }

    /**
     * the numeroEndereco
     * @return the numeroEndereco
     */
    public String getNumeroEndereco() {
        return numeroEndereco;
    }

    /**
     * the numeroEndereco to set
     * @param numeroEndereco 
     */
    public void setNumeroEndereco(String numeroEndereco) {
        this.numeroEndereco = numeroEndereco;
    }

    /**
     * the cidade
     * @return the cidade
     */
    public String getCidade() {
        return cidade;
    }

    /**
     * the cidade to set
     * @param cidade 
     */
    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    /**
     * the cEP
     * @return the cEP
     */
    public String getCEP() {
        return CEP;
    }

    /**
     * the cEP to set
     * @param cEP 
     */
    public void setCEP(String cEP) {
        CEP = cEP;
    }

    /**
     * the bairro
     * @return the bairro
     */
    public String getBairro() {
        return bairro;
    }

    /**
     * the bairro to set
     * @param bairro 
     */
    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    /**
     * the uF
     * @return the uF
     */
    public String getUF() {
        return UF;
    }

    /**
     * the uF to set
     * @param uF 
     */
    public void setUF(String uF) {
        UF = uF;
    }

    /**
     * the complemento
     * @return the complemento
     */
    public String getComplemento() {
        return complemento;
    }

    /**
     * the complemento to set
     * @param complemento 
     */
    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

}

package br.com.resseler.directsales.sales.persistence.builders;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Calendar;

/**
 * Class BillingPaymentType
 * @author b11527
 *
 */
public class BillingPaymentType {
    private String codigoDeSeguranca = null;
    private BigDecimal valorCobrancaCartao = null;
    private BigInteger numeroParcelas = null;
    private String cartaoNumero = null;
    private String cartaoNome = null;
    private Calendar cartaoValidade = null;
    private String cartaoCodigoBandeira = null;
    private String productMasterCode = null;
    private String salesmanPartnerCode = null;
    private Calendar dataDeNascimentoDoSegurado = null;
    private String contractNumber = null;
    private BigDecimal premio = null;
    private BillingPaymentAddressType addressType = null;
    private BillingPaymentContactType contactType = null;
    private BillingPaymentPersonType personType = null;

    /**
     * the codigoDeSeguranca
     * @return the codigoDeSeguranca
     */
    public String getCodigoDeSeguranca() {
        return codigoDeSeguranca;
    }

    /**
     * the codigoDeSeguranca to set
     * @param codigoDeSeguranca 
     */
    public void setCodigoDeSeguranca(String codigoDeSeguranca) {
        this.codigoDeSeguranca = codigoDeSeguranca;
    }

    /**
     * the valorCobrancaCartao
     * @return the valorCobrancaCartao
     */
    public BigDecimal getValorCobrancaCartao() {
        return valorCobrancaCartao;
    }

    /**
     * the valorCobrancaCartao to set
     * @param valorCobrancaCartao 
     */
    public void setValorCobrancaCartao(BigDecimal valorCobrancaCartao) {
        this.valorCobrancaCartao = valorCobrancaCartao;
    }

    /**
     * the numeroParcelas
     * @return the numeroParcelas
     */
    public BigInteger getNumeroParcelas() {
        return numeroParcelas;
    }

    /**
     * the numeroParcelas to set
     * @param numeroParcelas 
     */
    public void setNumeroParcelas(BigInteger numeroParcelas) {
        this.numeroParcelas = numeroParcelas;
    }

    /**
     * the cartaoNumero
     * @return the cartaoNumero
     */
    public String getCartaoNumero() {
        return cartaoNumero;
    }

    /**
     * the cartaoNumero to set
     * @param cartaoNumero 
     */
    public void setCartaoNumero(String cartaoNumero) {
        this.cartaoNumero = cartaoNumero;
    }

    /**
     * the cartaoNome
     * @return the cartaoNome
     */
    public String getCartaoNome() {
        return cartaoNome;
    }

    /**
     * the cartaoNome to set
     * @param cartaoNome 
     */
    public void setCartaoNome(String cartaoNome) {
        this.cartaoNome = cartaoNome;
    }

    /**
     * the cartaoValidade
     * @return the cartaoValidade
     */
    public Calendar getCartaoValidade() {
        return cartaoValidade;
    }

    /**
     * the cartaoValidade to set
     * @param cartaoValidade 
     */
    public void setCartaoValidade(Calendar cartaoValidade) {
        this.cartaoValidade = cartaoValidade;
    }

    /**
     * the cartaoCodigoBandeira
     * @return the cartaoCodigoBandeira
     */
    public String getCartaoCodigoBandeira() {
        return cartaoCodigoBandeira;
    }

    /**
     * the cartaoCodigoBandeira to set
     * @param cartaoCodigoBandeira 
     */
    public void setCartaoCodigoBandeira(String cartaoCodigoBandeira) {
        this.cartaoCodigoBandeira = cartaoCodigoBandeira;
    }

    /**
     * the productMasterCode
     * @return the productMasterCode
     */
    public String getProductMasterCode() {
        return productMasterCode;
    }

    /**
     * the productMasterCode to set
     * @param productMasterCode 
     */
    public void setProductMasterCode(String productMasterCode) {
        this.productMasterCode = productMasterCode;
    }

    /**
     * the salesmanPartnerCode
     * @return the salesmanPartnerCode
     */
    public String getSalesmanPartnerCode() {
        return salesmanPartnerCode;
    }

    /**
     * the salesmanPartnerCode to set
     * @param salesmanPartnerCode 
     */
    public void setSalesmanPartnerCode(String salesmanPartnerCode) {
        this.salesmanPartnerCode = salesmanPartnerCode;
    }

    /**
     * the dataDeNascimentoDoSegurado
     * @return the dataDeNascimentoDoSegurado
     */
    public Calendar getDataDeNascimentoDoSegurado() {
        return dataDeNascimentoDoSegurado;
    }

    /**
     * the dataDeNascimentoDoSegurado to set
     * @param dataDeNascimentoDoSegurado 
     */
    public void setDataDeNascimentoDoSegurado(
            Calendar dataDeNascimentoDoSegurado) {
        this.dataDeNascimentoDoSegurado = dataDeNascimentoDoSegurado;
    }

    /**
     * the contractNumber
     * @return the contractNumber
     */
    public String getContractNumber() {
        return contractNumber;
    }

    /**
     * the contractNumber to set
     * @param contractNumber 
     */
    public void setContractNumber(String contractNumber) {
        this.contractNumber = contractNumber;
    }

    /**
     * the premio
     * @return the premio
     */
    public BigDecimal getPremio() {
        return premio;
    }

    /**
     * the premio to set
     * @param premio 
     */
    public void setPremio(BigDecimal premio) {
        this.premio = premio;
    }

    /**
     * the addressType
     * @return the addressType
     */
    public BillingPaymentAddressType getAddressType() {
        return addressType;
    }

    /**
     * the addressType to set
     * @param addressType 
     */
    public void setAddressType(BillingPaymentAddressType addressType) {
        this.addressType = addressType;
    }

    /**
     * the contactType
     * @return the contactType
     */
    public BillingPaymentContactType getContactType() {
        return contactType;
    }

    /**
     * the contactType to set
     * @param contactType 
     */
    public void setContactType(BillingPaymentContactType contactType) {
        this.contactType = contactType;
    }

    /**
     * the personType
     * @return the personType
     */
    public BillingPaymentPersonType getPersonType() {
        return personType;
    }

    /**
     * the personType to set
     * @param personType 
     */
    public void setPersonType(BillingPaymentPersonType personType) {
        this.personType = personType;
    }

}

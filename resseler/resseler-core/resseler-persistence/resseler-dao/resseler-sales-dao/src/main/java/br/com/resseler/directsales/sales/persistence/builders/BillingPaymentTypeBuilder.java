package br.com.resseler.directsales.sales.persistence.builders;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Calendar;

public class BillingPaymentTypeBuilder {

    private final BillingPaymentType object = new BillingPaymentType();

    public BillingPaymentTypeBuilder() {
    	object.setAddressType(new BillingPaymentAddressType());
    	object.setContactType(new BillingPaymentContactType());
    	object.setPersonType(new BillingPaymentPersonType());
    }
    
    /**
     * 
     * @param codigoDeSeguranca
     * @return
     */
    public BillingPaymentTypeBuilder setCodigoDeSeguranca(final String codigoDeSeguranca) {
        object.setCodigoDeSeguranca(codigoDeSeguranca);
        return this;
    }

    /**
     * 
     * @param valorCobrancaCartao
     * @return
     */
    public BillingPaymentTypeBuilder setValorCobrancaCartao(final BigDecimal valorCobrancaCartao) {
        object.setValorCobrancaCartao(valorCobrancaCartao);
        return this;
    }

    /**
     * 
     * @param numeroParcelas
     * @return
     */
    public BillingPaymentTypeBuilder setNumeroParcelas(final BigInteger numeroParcelas) {
        object.setNumeroParcelas(numeroParcelas);
        return this;
    }

    /**
     * 
     * @param cartaoNumero
     * @return
     */
    public BillingPaymentTypeBuilder setCartaoNumero(final String cartaoNumero) {
        object.setCartaoNumero(cartaoNumero);
        return this;
    }

    /**
     * 
     * @param cartaoNome
     * @return
     */
    public BillingPaymentTypeBuilder setCartaoNome(final String cartaoNome) {
        object.setCartaoNome(cartaoNome);
        return this;
    }

    /**
     * 
     * @param cartaoValidade
     * @return
     */
    public BillingPaymentTypeBuilder setCartaoValidade(final Calendar cartaoValidade) {
        object.setCartaoValidade(cartaoValidade);
        return this;
    }

    /**
     * 
     * @param cartaoCodigoBandeira
     * @return
     */
    public BillingPaymentTypeBuilder setCartaoCodigoBandeira(final String cartaoCodigoBandeira) {
        object.setCartaoCodigoBandeira(cartaoCodigoBandeira);
        return this;
    }

    /**
     * 
     * @param dddEtelefoneResidencial
     * @return
     */
    public BillingPaymentTypeBuilder setDddEtelefoneResidencial(final String dddEtelefoneResidencial) {
        object.getContactType().setDddEtelefoneResidencial(dddEtelefoneResidencial);
        return this;
    }

    /**
     * 
     * @param dddEtelefoneCelular
     * @return
     */
    public BillingPaymentTypeBuilder setDddEtelefoneCelular(final String dddEtelefoneCelular) {
        object.getContactType().setDddEtelefoneCelular(dddEtelefoneCelular);
        return this;
    }

    /**
     * 
     * @param dddETelefoneComercial
     * @return
     */
    public BillingPaymentTypeBuilder setDddETelefoneComercial(final String dddETelefoneComercial) {
        object.getContactType().setDddETelefoneComercial(dddETelefoneComercial);
        return this;
    }

    /**
     * 
     * @param productMasterCode
     * @return
     */
    public BillingPaymentTypeBuilder setProductMasterCode(final String productMasterCode) {
        object.setProductMasterCode(productMasterCode);
        return this;
    }

    /**
     * 
     * @param salesmanPartnerCode
     * @return
     */
    public BillingPaymentTypeBuilder setSalesmanPartnerCode(final String salesmanPartnerCode) {
        object.setSalesmanPartnerCode(salesmanPartnerCode);
        return this;
    }

    /**
     * 
     * @param dataDeNascimentoDoSegurado
     * @return
     */
    public BillingPaymentTypeBuilder setDataDeNascimentoDoSegurado(final Calendar dataDeNascimentoDoSegurado) {
        object.setDataDeNascimentoDoSegurado(dataDeNascimentoDoSegurado);
        return this;
    }

    /**
     * 
     * @param contractNumber
     * @return
     */
    public BillingPaymentTypeBuilder setContractNumber(final String contractNumber) {
        object.setContractNumber(contractNumber);
        return this;
    }

    /**
     * 
     * @param premio
     * @return
     */
    public BillingPaymentTypeBuilder setPremio(final BigDecimal premio) {
        object.setPremio(premio);
        return this;
    }

    /**
     * 
     * @param nomeDoSegurado
     * @return
     */
    public BillingPaymentTypeBuilder setNomeDoSegurado(final String nomeDoSegurado) {
        object.getPersonType().setNomeDoSegurado(nomeDoSegurado);
        return this;
    }

    /**
     * 
     * @param sexo
     * @return
     */
    public BillingPaymentTypeBuilder setSexo(final String sexo) {
        object.getPersonType().setSexo(sexo);
        return this;
    }

    /**
     * 
     * @param email
     * @return
     */
    public BillingPaymentTypeBuilder setEmail(final String email) {
        object.getPersonType().setEmail(email);
        return this;
    }

    /**
     * 
     * @param cpfCnpj
     * @return
     */
    public BillingPaymentTypeBuilder setCpfCnpj(final String cpfCnpj) {
        object.getPersonType().setCpfCnpj(cpfCnpj);
        return this;
    }

    /**
     * 
     * @param civilState
     * @return
     */
    public BillingPaymentTypeBuilder setCivilState(final String civilState) {
        object.getPersonType().setCivilState(civilState);
        return this;
    }

    /**
     * 
     * @param logradouro
     * @return
     */
    public BillingPaymentTypeBuilder setLogradouro(final String logradouro) {
        object.getAddressType().setLogradouro(logradouro);
        return this;
    }

    /**
     * 
     * @param numeroEndereco
     * @return
     */
    public BillingPaymentTypeBuilder setNumeroEndereco(final String numeroEndereco) {
        object.getAddressType().setNumeroEndereco(numeroEndereco);
        return this;
    }

    /**
     * 
     * @param cidade
     * @return
     */
    public BillingPaymentTypeBuilder setCidade(final String cidade) {
        object.getAddressType().setCidade(cidade);
        return this;
    }

    /**
     * 
     * @param cEP
     * @return
     */
    public BillingPaymentTypeBuilder setCEP(final String cep) {
        object.getAddressType().setCEP(cep);
        return this;
    }

    /**
     * 
     * @param bairro
     * @return
     */
    public BillingPaymentTypeBuilder setBairro(final String bairro) {
        object.getAddressType().setBairro(bairro);
        return this;
    }

    /**
     * 
     * @param uF
     * @return
     */
    public BillingPaymentTypeBuilder setUF(final String uf) {
        object.getAddressType().setUF(uf);
        return this;
    }

    /**
     * 
     * @param complemento
     * @return
     */
    public BillingPaymentTypeBuilder setComplemento(final String complemento) {
        object.getAddressType().setComplemento(complemento);
        return this;
    }

    /**
     * 
     * @return BillingPaymentType
     */
    public BillingPaymentType build() {
        return object;
    }
}

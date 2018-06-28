package br.com.rubyit.resseler.core.utils;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import br.com.rubyit.resseler.commons.kernel.dto.ProductDTO;
import br.com.rubyit.resseler.core.commons.dto.Coverage;
import br.com.rubyit.resseler.core.commons.dto.Tax;
import br.com.rubyit.resseler.core.enums.TaxType;

public final class ProductData {

    private static final Integer ID_PRODUCT_LUIZA_RESIDENCIAL_MAIS_PROTECAO = 11;
    private static final Integer ID_PRODUCT_LUIZA_RESIDENCIAL_MENSAL_PLANO1 = 8;
    private static final Integer ID_PRODUCT_LUIZA_VIDA_MENSAL_PLANO1 = 2;
    private static final Integer ID_PRODUCT_SEGURO_LUIZA_CASA_PROTEGIDA = 23;
    private static final Integer ID_PRODUCT_SEGURO_LUIZA_PROTECAO_PESSOAL = 24;
    private static final Integer ID_PRODUCT_SEGURO_LUIZA_PROTECAO_RESIDENCIAL = 22;
    private static final Integer ID_PRODUCT_SEGURO_LUIZA_PROTECAO_VIDA = 21;
    private static final Integer ID_PRODUCT_SEGURO_LUIZA_RESIDENCIAL_PAGTO_MENSAL = 19;
    private static final Integer ID_PRODUCT_SEGURO_LUIZA_VIDA_PAGTO_MENSAL = 20;
    private static final Integer ID_PRODUCT_CARTAO_SUPERPROTEGIDO = 25;
    private static final Integer ID_PRODUCT_PROTECAO_PREMIADA = 26;

    private final double EMPTY_PREMIUM = 0;
    private final int SCALE_2 = 2;
    private static List<Tax> lstTaxResidencialMaisProtecaoTaxes = new ArrayList<>();
    private static List<Tax> lstTaxluizaResidencialMensalPlano1Taxes = new ArrayList<>();
    private static List<Tax> lstTaxluizaVidaMensalPlano1Taxes = new ArrayList<>();
    private static List<Tax> lstTaxseguroLuizaCasaProtegidaTaxes = new ArrayList<>();
    private static List<Tax> lstTaxseguroLuizaProtecaoPessoalTaxes = new ArrayList<>();
    private static List<Tax> lstTaxseguroLuizaProtecaoResidencialTaxes = new ArrayList<>();
    private static List<Tax> lstTaxseguroLuizaProtecaoVidaTaxes = new ArrayList<>();
    private static List<Tax> lstTaxseguroLuizaResidencialPagtoMensalTaxes = new ArrayList<>();
    private static List<Tax> lstTaxseguroLuizaVidaPagtoMensalTaxes = new ArrayList<>();
    private static List<Tax> lstTaxcartaoSuperProtegidoTaxes = new ArrayList<>();
    private static List<Tax> lstTaxprotecaoPremiadaTaxes = new ArrayList<>();
    private static List<Coverage> lstresidencialMaisProtecao = new ArrayList<>();
    private static List<Coverage> lstresidencialMensalPlano = new ArrayList<>();
    private static List<Coverage> lstluizaVidaMensalPlano1Coverages = new ArrayList<>();
    private static List<Coverage> lstseguroLuizaCasaProtegida = new ArrayList<>();
    private static List<Coverage> lstseguroLuizaProtecaoPessoalCoverages = new ArrayList<>();
    private static List<Coverage> lstseguroLuizaProtecaoResidencialCoverages = new ArrayList<>();
    private static List<Coverage> lstseguroLuizaProtecaoVidaCoverages = new ArrayList<>();
    private static List<Coverage> lstseguroLuizaResidencialPagtoMensalCoverages = new ArrayList<>();
    private static List<Coverage> lstseguroLuizaVidaPagtoMensalCoverages = new ArrayList<>();
    private static List<Coverage> lstcartaoSuperprotegidoCoverages = new ArrayList<>();
    private static List<Coverage> lstprotecaoPremiadaCoverages = new ArrayList<>();

    private static Map<Integer, List<Coverage>> mapCoverages = new HashMap<>();
    private static Map<Integer, List<Tax>> mapTaxes = new HashMap<>();

    private static ProductData uniqueInstance;

    private ProductData() {
        setListCoverages();
        setListTaxes();
        setMapCoverages();
        setMapTaxes();
    }

    public static synchronized ProductData getInstance() {
        if (uniqueInstance == null) {
            uniqueInstance = new ProductData();
        }

        return uniqueInstance;
    }

    public List<Coverage> getListCoverageByProduct(final ProductDTO product) {
        List<Coverage> coverage = new ArrayList<>();
        if (mapCoverages.get(product.getID()) != null) {
            coverage = mapCoverages.get(product.getID());
        }
        return coverage;
    }

    public final List<Tax> getListTaxByProduct(final ProductDTO product) {
        List<Tax> tax = new ArrayList<>();
        if (mapTaxes.get(product.getID()) != null) {
            tax = mapTaxes.get(product.getID());
        }
        return tax;
    }

    private final Coverage fillCoverage(final double premium,
            final String description) {
        final Coverage coverage = new Coverage();
        coverage.setDescription(description);
        final Number number = premium;
        if (!number.equals(EMPTY_PREMIUM)) {
            coverage.setCoveragePremium(BigDecimal.valueOf(premium)
                    .setScale(SCALE_2, RoundingMode.HALF_EVEN));
        }

        return coverage;
    }

    @edu.umd.cs.findbugs.annotations.SuppressFBWarnings(value = {
            "findbugs:CNT_ROUGH_CONSTANT_VALUE" }, justification = "Ignorando a regra, pois a "
                    + "regra de negocios requer o valor literal de 3.14.")
    private final Coverage fillCoverage(final double premium,
            final double limit, final String description,
            final Integer installmentLimit) {
        final Coverage coverage = fillCoverage(premium, description);
        coverage.setCoverageValueLimit(BigDecimal.valueOf(limit)
                .setScale(SCALE_2, RoundingMode.HALF_EVEN));
        if (installmentLimit != null) {
            coverage.setInstallmentLimit(installmentLimit);
        }
        return coverage;
    }

    private void seguroLuizaResidencialPagtoMensalCoverages(
            final List<Coverage> lstCoverage) {
        lstCoverage
                .add(fillCoverage(4.61, 50000, "Incendio Raio Explosao", null));
        lstCoverage.add(fillCoverage(2.46, 1500,
                "Roubo ou Furto Qualificado de Bens ", null));
        lstCoverage.add(fillCoverage(1.65, 1500, "Danos Elétricos ", null));
        lstCoverage.add(fillCoverage(1.18, 1000,
                "Vendaval, Furacão, Ciclone, Tornado e Granizo ", null));
        lstCoverage.add(fillCoverage(EMPTY_PREMIUM, 5062.50,
                "valor liquido sorteio cap", null));
    }

    private void seguroLuizaProtecaoResidencialCoverages(
            final List<Coverage> lstCoverage) {
        lstCoverage
                .add(fillCoverage(2.97, 50000, "Incendio Raio Explosao", null));
        lstCoverage.add(fillCoverage(3.39, 1500,
                "Roubo ou Furto Qualificado de Bens ", null));
        lstCoverage.add(fillCoverage(EMPTY_PREMIUM, 5062.50,
                "valor liquido sorteio cap", null));
    }

    private void residencialMaisProtecao(final List<Coverage> lstCoverage) {
        lstCoverage
                .add(fillCoverage(4.62, 50000, "Incendio Raio Explosao", null));
        lstCoverage.add(fillCoverage(5.28, 1500,
                "Roubo ou Furto Qualificado de Bens ", null));
        lstCoverage.add(fillCoverage(EMPTY_PREMIUM, 10125,
                "valor liquido sorteio cap", null));
    }

    private void residencialMensalPlano(final List<Coverage> lstCoverage) {
        lstCoverage
                .add(fillCoverage(4.62, 50000, "Incendio Raio Explosao", null));
        lstCoverage.add(fillCoverage(5.28, 1500,
                "Roubo ou Furto Qualificado de Bens ", null));
        lstCoverage.add(fillCoverage(EMPTY_PREMIUM, 5062.50,
                "valor liquido sorteio cap", null));
    }

    private void seguroLuizaCasaProtegida(final List<Coverage> lstCoverage) {
        lstCoverage
                .add(fillCoverage(4.61, 50000, "Incendio Raio Explosao", null));
        lstCoverage.add(fillCoverage(2.46, 1500,
                "Roubo ou Furto Qualificado de Bens ", null));
        lstCoverage.add(fillCoverage(1.65, 1500, "Danos Elétricos ", null));
        lstCoverage.add(fillCoverage(1.18, 1000,
                "Vendaval, Furacão, Ciclone, Tornado e Granizo ", null));
        lstCoverage.add(fillCoverage(EMPTY_PREMIUM, 5062.50,
                "valor liquido sorteio cap", null));
    }

    private void cartaoSuperprotegidoCoverages(
            final List<Coverage> lstCoverage) {

        lstCoverage.add(fillCoverage(0.08, "Saque ou Compra Sob Coação"));
        lstCoverage
                .add(fillCoverage(1.55, "Roubo e Furto Qualificado da Bolsa"));
        lstCoverage.add(fillCoverage(0.08, "Roubo em Caixa Eletronico"));
        lstCoverage.add(fillCoverage(1.59, "Morte"));
        lstCoverage.add(fillCoverage(0.13, "Invalidez Permanente"));
        lstCoverage.add(fillCoverage(EMPTY_PREMIUM, 3000,
                "Pagamento do saldo devedor morte", null));
        lstCoverage.add(fillCoverage(7.36,
                "Desemprego Involuntario/Incapacidade Tot Temp"));
        lstCoverage.add(fillCoverage(EMPTY_PREMIUM, 1000,
                "Pagamento do saldo devedor desemprego", null));
        lstCoverage.add(fillCoverage(0.66, 20000,
                "Morte em decorrencia de Crime", null));
        lstCoverage.add(fillCoverage(0.25, 20000,
                "Invalidez Permancente Tot ou Parc", null));
        lstCoverage.add(fillCoverage(0.19, 500,
                "Diaria Unica por Hospitalizacao", null));
        lstCoverage.add(fillCoverage(20000, "limite compra para cartão"));
        lstCoverage.add(fillCoverage(EMPTY_PREMIUM, 2000,
                "Pagamento decorrencia roubo", null));
        lstCoverage.add(
                fillCoverage(EMPTY_PREMIUM, 1000, "limite saque cartão", null));
        lstCoverage.add(fillCoverage(EMPTY_PREMIUM, 10000,
                "valor liquido sorteio cap", null));

    }

    private void luizaVidaMensalPlano1Coverages(
            final List<Coverage> lstCoverage) {
        lstCoverage.add(fillCoverage(1.06, 5000, "Morte", null));
        lstCoverage.add(fillCoverage(5.63, 7000, "Morte Acidental", null));
        lstCoverage.add(fillCoverage(0.73, 12000,
                "Invalidez Permanente Total por Acidente", null));
        lstCoverage.add(fillCoverage(2.48, 2200, "Assistencia Funeral", null));
        lstCoverage.add(fillCoverage(EMPTY_PREMIUM, 10125,
                "valor liquido sorteio cap", null));
    }

    @edu.umd.cs.findbugs.annotations.SuppressFBWarnings(value = {
            "findbugs:CNT_ROUGH_CONSTANT_VALUE" }, justification = "Ignorando a regra, pois a "
                    + "regra de negocios requer o valor literal de 3.14.")
    private void seguroLuizaProtecaoVidaCoverages(
            final List<Coverage> lstCoverage) {
        lstCoverage.add(fillCoverage(7.14, 5000, "Morte", null));
        lstCoverage.add(fillCoverage(1.34, 7000, "Morte Acidental", null));
        lstCoverage.add(fillCoverage(0.92, 12000,
                "Invalidez Permanente Total por Acidente", null));
        lstCoverage.add(fillCoverage(3.14, 2200, "Assistencia Funeral", null));
        lstCoverage.add(fillCoverage(EMPTY_PREMIUM, 5062.50,
                "valor liquido sorteio cap", null));
    }

    private void seguroLuizaProtecaoPessoalCoverages(
            final List<Coverage> lstCoverage) {
        lstCoverage.add(
                fillCoverage(7.84, 200, "Diária de Internação Hospitalar", 30));
        lstCoverage.add(fillCoverage(2.06, 2200, "Auxilio Funeral", null));
        lstCoverage.add(fillCoverage(EMPTY_PREMIUM, 5062.50,
                "valor liquido sorteio cap", null));
    }

    private void seguroLuizaVidaPagtoMensalCoverages(
            final List<Coverage> lstCoverage) {
        lstCoverage.add(fillCoverage(5.63, 5000, "Morte", null));
        lstCoverage.add(fillCoverage(1.06, 7000, "Morte Acidental", null));
        lstCoverage.add(fillCoverage(0.73, 12000,
                "Invalidez Permanente Total por Acidente", null));
        lstCoverage.add(fillCoverage(2.48, 2200, "Assistencia Funeral", null));
        lstCoverage.add(fillCoverage(EMPTY_PREMIUM, 5062.50,
                "valor liquido sorteio cap", null));
    }

    private void protecaoPremiadaCoverages(final List<Coverage> lstCoverage) {
        lstCoverage.add(fillCoverage(2.74, 1000, "Auxilio Funeral", null));
        lstCoverage.add(fillCoverage(9.15, 200,
                "Desemprego Involuntário/Incapacidade Física Total Temporária",
                5));
        lstCoverage.add(fillCoverage(EMPTY_PREMIUM, 5062.50,
                "valor liquido sorteio cap", null));
    }

    private void cartaoSuperProtegidoTaxes(final List<Tax> lstTax) {
        lstTax.add(new Tax(TaxType.IOF, "0.16"));
        lstTax.add(new Tax(TaxType.PROLABORE, "50%"));
        lstTax.add(new Tax(TaxType.PROLABORE, "5.87"));
    }

    private void seguroLuizaProtecaoVidaTaxes(final List<Tax> lstTax) {
        lstTax.add(getTaxZeroPercent());
        lstTax.add(new Tax(TaxType.IOF, "0.05"));
        lstTax.add(new Tax(TaxType.PROLABORE, "41%"));
        lstTax.add(new Tax(TaxType.PROLABORE, "5.12"));
    }

    private void seguroLuizaProtecaoResidencialTaxes(final List<Tax> lstTax) {
        lstTax.add(getTaxZeroPercent());
        lstTax.add(new Tax(TaxType.IOF, "0.44"));
        lstTax.add(new Tax(TaxType.PROLABORE, "41%"));
        lstTax.add(new Tax(TaxType.PROLABORE, "2.43"));
    }

    private void seguroLuizaProtecaoPessoalTaxes(final List<Tax> lstTax) {
        lstTax.add(new Tax(TaxType.IOF, "0.04"));
    }

    private void seguroLuizaVidaPagtoMensalTaxes(final List<Tax> lstTax) {
        lstTax.add(getTaxZeroPercent());
        lstTax.add(new Tax(TaxType.IOF, "0.04"));
        lstTax.add(new Tax(TaxType.PROLABORE, "41%"));
        lstTax.add(new Tax(TaxType.PROLABORE, "4.04"));
    }

    private void seguroLuizaCasaProtegidaTaxes(final List<Tax> lstTax) {
        lstTax.add(new Tax(TaxType.IOF, "0.68"));
    }

    private void seguroLuizaResidencialPagtoMensalTaxes(
            final List<Tax> lstTax) {
        lstTax.add(new Tax(TaxType.IOF, "0.68"));
    }

    private void luizaVidaMensalPlano1Taxes(final List<Tax> lstTax) {
        lstTax.add(getTaxZeroPercent());
        lstTax.add(new Tax(TaxType.IOF, "0.03"));
        lstTax.add(new Tax(TaxType.PROLABORE, "41%"));
        lstTax.add(new Tax(TaxType.PROLABORE, "3.23"));
    }

    private void luizaResidencialMensalPlano1Taxes(final List<Tax> lstTax) {
        lstTax.add(getTaxZeroPercent());
        lstTax.add(new Tax(TaxType.IOF, "0.54"));
        lstTax.add(new Tax(TaxType.PROLABORE, "41%"));
        lstTax.add(new Tax(TaxType.PROLABORE, "3.02"));
    }

    private void luizaResidencialMaisProtecaoTaxes(final List<Tax> lstTax) {
        lstTax.add(getTaxZeroPercent());
        lstTax.add(new Tax(TaxType.IOF, "1.00"));
        lstTax.add(new Tax(TaxType.PROLABORE, "41%"));
        lstTax.add(new Tax(TaxType.PROLABORE, "5.54"));
    }

    private void protecaoPremiadaTaxes(final List<Tax> lstTax) {
        lstTax.add(new Tax(TaxType.IOF, "0.05"));
    }

    private final Tax getTaxZeroPercent() {
        final Tax tax = new Tax();
        tax.setTaxType(TaxType.PROLABORE);
        tax.setTaxValue("0%");
        return tax;

    }

    private void setMapTaxes() {
        mapTaxes.put(ID_PRODUCT_LUIZA_RESIDENCIAL_MAIS_PROTECAO,
                lstTaxResidencialMaisProtecaoTaxes);
        mapTaxes.put(ID_PRODUCT_LUIZA_RESIDENCIAL_MENSAL_PLANO1,
                lstTaxluizaResidencialMensalPlano1Taxes);
        mapTaxes.put(ID_PRODUCT_LUIZA_VIDA_MENSAL_PLANO1,
                lstTaxluizaVidaMensalPlano1Taxes);
        mapTaxes.put(ID_PRODUCT_SEGURO_LUIZA_CASA_PROTEGIDA,
                lstTaxseguroLuizaCasaProtegidaTaxes);
        mapTaxes.put(ID_PRODUCT_SEGURO_LUIZA_PROTECAO_PESSOAL,
                lstTaxseguroLuizaProtecaoPessoalTaxes);
        mapTaxes.put(ID_PRODUCT_SEGURO_LUIZA_PROTECAO_RESIDENCIAL,
                lstTaxseguroLuizaProtecaoResidencialTaxes);
        mapTaxes.put(ID_PRODUCT_SEGURO_LUIZA_PROTECAO_VIDA,
                lstTaxseguroLuizaProtecaoVidaTaxes);
        mapTaxes.put(ID_PRODUCT_SEGURO_LUIZA_RESIDENCIAL_PAGTO_MENSAL,
                lstTaxseguroLuizaResidencialPagtoMensalTaxes);
        mapTaxes.put(ID_PRODUCT_SEGURO_LUIZA_VIDA_PAGTO_MENSAL,
                lstTaxseguroLuizaVidaPagtoMensalTaxes);
        mapTaxes.put(ID_PRODUCT_CARTAO_SUPERPROTEGIDO,
                lstTaxcartaoSuperProtegidoTaxes);
        mapTaxes.put(ID_PRODUCT_PROTECAO_PREMIADA, lstTaxprotecaoPremiadaTaxes);
    }

    private void setMapCoverages() {
        mapCoverages.put(ID_PRODUCT_LUIZA_RESIDENCIAL_MAIS_PROTECAO,
                lstresidencialMaisProtecao);
        mapCoverages.put(ID_PRODUCT_LUIZA_RESIDENCIAL_MENSAL_PLANO1,
                lstresidencialMensalPlano);
        mapCoverages.put(ID_PRODUCT_LUIZA_VIDA_MENSAL_PLANO1,
                lstluizaVidaMensalPlano1Coverages);
        mapCoverages.put(ID_PRODUCT_SEGURO_LUIZA_CASA_PROTEGIDA,
                lstseguroLuizaCasaProtegida);
        mapCoverages.put(ID_PRODUCT_SEGURO_LUIZA_PROTECAO_PESSOAL,
                lstseguroLuizaProtecaoPessoalCoverages);
        mapCoverages.put(ID_PRODUCT_SEGURO_LUIZA_PROTECAO_RESIDENCIAL,
                lstseguroLuizaProtecaoResidencialCoverages);
        mapCoverages.put(ID_PRODUCT_SEGURO_LUIZA_PROTECAO_VIDA,
                lstseguroLuizaProtecaoVidaCoverages);
        mapCoverages.put(ID_PRODUCT_SEGURO_LUIZA_RESIDENCIAL_PAGTO_MENSAL,
                lstseguroLuizaResidencialPagtoMensalCoverages);
        mapCoverages.put(ID_PRODUCT_SEGURO_LUIZA_VIDA_PAGTO_MENSAL,
                lstseguroLuizaVidaPagtoMensalCoverages);
        mapCoverages.put(ID_PRODUCT_CARTAO_SUPERPROTEGIDO,
                lstcartaoSuperprotegidoCoverages);
        mapCoverages.put(ID_PRODUCT_PROTECAO_PREMIADA,
                lstprotecaoPremiadaCoverages);
    }

    private void setListTaxes() {
        luizaResidencialMaisProtecaoTaxes(lstTaxResidencialMaisProtecaoTaxes);
        luizaResidencialMensalPlano1Taxes(
                lstTaxluizaResidencialMensalPlano1Taxes);
        luizaVidaMensalPlano1Taxes(lstTaxluizaVidaMensalPlano1Taxes);
        seguroLuizaCasaProtegidaTaxes(lstTaxseguroLuizaCasaProtegidaTaxes);
        seguroLuizaProtecaoPessoalTaxes(lstTaxseguroLuizaProtecaoPessoalTaxes);
        seguroLuizaProtecaoResidencialTaxes(
                lstTaxseguroLuizaProtecaoResidencialTaxes);
        seguroLuizaProtecaoVidaTaxes(lstTaxseguroLuizaProtecaoVidaTaxes);
        seguroLuizaResidencialPagtoMensalTaxes(
                lstTaxseguroLuizaResidencialPagtoMensalTaxes);
        seguroLuizaVidaPagtoMensalTaxes(lstTaxseguroLuizaVidaPagtoMensalTaxes);
        cartaoSuperProtegidoTaxes(lstTaxcartaoSuperProtegidoTaxes);
        protecaoPremiadaTaxes(lstTaxprotecaoPremiadaTaxes);
    }

    private void setListCoverages() {
        residencialMaisProtecao(lstresidencialMaisProtecao);
        residencialMensalPlano(lstresidencialMensalPlano);
        luizaVidaMensalPlano1Coverages(lstluizaVidaMensalPlano1Coverages);
        seguroLuizaCasaProtegida(lstseguroLuizaCasaProtegida);
        seguroLuizaProtecaoPessoalCoverages(
                lstseguroLuizaProtecaoPessoalCoverages);
        seguroLuizaProtecaoResidencialCoverages(
                lstseguroLuizaProtecaoResidencialCoverages);
        seguroLuizaProtecaoVidaCoverages(lstseguroLuizaProtecaoVidaCoverages);
        seguroLuizaResidencialPagtoMensalCoverages(
                lstseguroLuizaResidencialPagtoMensalCoverages);
        seguroLuizaVidaPagtoMensalCoverages(
                lstseguroLuizaVidaPagtoMensalCoverages);
        cartaoSuperprotegidoCoverages(lstcartaoSuperprotegidoCoverages);
        protecaoPremiadaCoverages(lstprotecaoPremiadaCoverages);
    }
}

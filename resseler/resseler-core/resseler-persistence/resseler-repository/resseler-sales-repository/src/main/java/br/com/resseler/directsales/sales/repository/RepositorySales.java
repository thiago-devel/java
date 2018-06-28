package br.com.resseler.directsales.sales.repository;

import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import br.com.resseler.directsales.commons.persistence.dao.impl.SalesmanDAO;
import br.com.resseler.directsales.sales.persistence.builders.CompleteCertificate;
import br.com.resseler.directsales.sales.persistence.dao.impl.CardPaymentDAO;
import br.com.resseler.directsales.sales.persistence.dao.impl.SalesDAO;
import br.com.resseler.directsales.sales.persistence.dao.impl.SalesDBDAO;
import br.com.rubyit.resseler.commons.kernel.dto.ProductDTO;
import br.com.rubyit.resseler.core.commons.dto.CardPaymentDTO;
import br.com.rubyit.resseler.core.commons.dto.InsuranceCertificate;
import br.com.rubyit.resseler.core.commons.dto.Regional;
import br.com.rubyit.resseler.core.commons.dto.Sale;
import br.com.rubyit.resseler.core.commons.dto.Salesman;
import br.com.rubyit.resseler.core.commons.dto.SalesmanScore;
import br.com.rubyit.resseler.core.commons.dto.SystemPerson;
import br.com.rubyit.resseler.core.commons.dto.Workshop;
import br.com.rubyit.resseler.core.commons.exceptions.BusinessException;
import br.com.rubyit.resseler.core.commons.exceptions.ExceptionsConstants;
import br.com.rubyit.resseler.core.enums.ErrorType;

/**
 * RepositorySales
 * @author b11527
 *
 */
@Repository
public class RepositorySales {
    @Autowired
    private final SalesDAO daoSale = null;
    @Autowired
    private final SalesmanDAO daoSalesman = null;
    //@Autowired
    private final /*CertificateDAO*/Object daoCertificate = null;
    @Autowired
    private final CardPaymentDAO daoCardPayment = null;
    @Autowired
    private final SalesDBDAO salesDBDAO = null;

    /**
     * Método responsável por realizar uma venda
     * @param sale
     * @return
     */
    public InsuranceCertificate saveSale(final Sale sale) {
        return daoSale.process(sale);
    }

    /**
     * Método responsável por retornar uma lista
     * de vendas de um determinado cliente
     * e  produto
     * @param identificationCPF
     * @param productID
     * @return
     */
    public Integer retrieveInsuranceCertificatesByCustomerIdentificationAndProduct(
            final String identificationCPF, final Long productID) {

        Integer quantityCertificates = 0;

        if (productID != null) {
            quantityCertificates = null;/*daoCertificate
                    .retrieveActiveCertificatesBy(identificationCPF, productID);*/
        }

        return quantityCertificates;
    }

    /**
     * Método responsável por retornar uma lista
     * de vendas de um determinado vendedor (Salesman)
     * por CPF
     * @param identificationCPF
     * @return
     */
    public List<InsuranceCertificate> retrieveInsuranceCertificatesBySalesmanIdentification(
            final String identificationCPF) {

        final Salesman salesman = daoSalesman
                .retrieveSalesmanBy(identificationCPF);

        final List<InsuranceCertificate> certificates = null;/*daoCertificate
                .retrieveCertificateForSalesmanId(salesman.getID());*/

        return certificates;
    }

    /**
     * Método responsável por retornar uma lista
     * de vendas de um determiado período por: 
     * vendedor (Salesman)
     * ou produto
     * ou regional
     * ou filial
     * ou vendedor
     * @param product
     * @param regional
     * @param workshop
     * @param salesman
     * @param startDate
     * @param endDate
     * @return
     */
    public List<SalesmanScore> retrievePerformance(final ProductDTO product,
            final Regional regional, final Workshop workshop,
            final Salesman salesman, final Calendar startDate,
            final Calendar endDate) {

        Salesman salesmanFind = null;

        if ((salesman != null) && (salesman.getIdentity() != null)
                && (salesman.getIdentity().getDocumentValue() != null)) {

            salesmanFind = daoSalesman.retrieveSalesmanBy(
                    salesman.getIdentity().getDocumentValue());
        }

        return daoSalesman.returnScoresByCriteria(product, regional, workshop,
                salesmanFind, startDate, endDate);
    }

    /**
     * Método responsável por retornar um
     * Vendedor (Salesman)
     * de acordo com seu ID
     * @param salesmanID
     * @return
     */
    public Salesman retrieveSalesmanBy(final Long salesmanID) {

        return daoSalesman.retrieveSalesmanBy(salesmanID);
    }

    /**
     * Método responsável por retornar um
     * Vendedor (Salesman)
     * de acordo com seu CPF/CNPJ
     * @param salesmanCpfCnpj
     * @return
     */
    public Salesman retrieveSalesmanBy(final String salesmanCpfCnpj) {
        return daoSalesman.retrieveSalesmanBy(salesmanCpfCnpj);
    }

    /**
     * Método responsável por retornar o
     * ID do branch pelo SalesmanID
     * @param salesmanID
     * @return
     * @throws DataAccessException
     */
    public Long retrieveWorkshopIDBy(final Long salesmanID)
            throws DataAccessException {
        return daoSalesman.retrieveWorkshopIDBySalesmanID(salesmanID);
    }

    /**
     * Método responsável por retornar o
     * ID do branch pelo salesmanCpfCnpj
     * @param salesmanCpfCnpj
     * @return
     */
    public Long retrieveWorkshopIDBy(final String salesmanCpfCnpj) {
        return salesDBDAO.retrieveWorkshopIDBySalesman(salesmanCpfCnpj);
    }

    /**
     * Método responsável por retornar o
     * CODE do branch pelo workshopID
     * @param workshopID
     * @return
     */
    public String retrieveWorkshopCodeBy(final Long workshopID) {
        return salesDBDAO.retrieveWorkshopCodeBy(workshopID);
    }

    /**
     * Método responsável por retornar o
     * ID da regional pelo workshopID
     * @param workshopID
     * @return
     */
    public Long retrieveRegionIDByWorkshopID(final Long workshopID) {
        return salesDBDAO.retrieveRegionIDByWorkshopID(workshopID);
    }

    /**
     * Método responsável por retornar o
     * uma lista de Certificados por número do contrato
     * @param certificate
     * @return
     */
    public List<InsuranceCertificate> retrieveCertificateFor(
            final InsuranceCertificate certificate) {
        List<InsuranceCertificate> result = null;

        if ((certificate != null) && (certificate.getSalesman() != null)
                && (certificate.getSalesman().getIdentity() != null)) {

            final String cpfCnpj = certificate.getSalesman().getIdentity()
                    .getDocumentValue();
            if (daoSalesman.retrieveSalesmanBy(
                    cpfCnpj) == null) { throw new BusinessException(
                            "ERROR: retrieveCertificateFor(CpfCnpj) fail! Salesman not found.",
                            ExceptionsConstants.CODE_SALESMAN_NOTFOUND,
                            ErrorType.BUSINESS); }

            result = daoSale
                    .retrieveCertificateFor(certificate.getContractNumber());
        }

        return result;
    }

    /**
     * Update no método de pagamento
     * @param certificate
     * @param securityKeyID
     */
    public void updatePaymentMethodContract(
            final InsuranceCertificate certificate,
            final String securityKeyID) {
        daoSale.updatePaymentMethodContract(certificate, securityKeyID);
    }

    /**
     * Recebe o CardPaymentDTO atráves do ID do cartão de crédito
     * @param creditCardID
     * @return
     */
    public CardPaymentDTO retrieveCertificateCardPaymentData(
            final Long creditCardID) {
        return daoCardPayment.retrieveBy(creditCardID);
    }

    /**
     * Complementa dados do InsuranceCertificate
     * @param completeCertificate
     * @return
     */
    public InsuranceCertificate completeCertificateFill(
            final CompleteCertificate completeCertificate) {
        return daoSale.completeCertificateFill(completeCertificate);
    }

    /**
     * Método responsavel por cadastrar um vendedor
     *
     * @param salesman
     *            Possui as informações da entidade Usuário e Vendedor
     * @param systemPerson
     *            Possui as informações da entidade pessoa
     */
    public void saveSalesman(final Salesman salesman,
            final SystemPerson systemPerson) {
        salesDBDAO.saveSalesman(salesman, systemPerson);
    }

    /**
     * Atualizar Ramo e filial do vendedor
     *
     * @param salesman
     */
    public void updateSalesmanBranch(final Salesman salesman) {
        salesDBDAO.updateSalesmanBranch(salesman);
    }

    /**
     * Verificar se existe a filial para essa região
     *
     * @param salesman
     * @return código da filial
     */
    public Long findCodBranchForRegion(final Salesman salesman) {
        return salesDBDAO.findCodBranchForRegion(salesman);
    }

    /**
     * Pesquisar a pessoa por CPF
     * 
     * @param cpfCNPJ
     * @return
     */
    public SystemPerson retrieveBy(final String cpfCNPJ) {
        return salesDBDAO.retrieveBy(cpfCNPJ);
    }
    
    /**
     * Retorna a idade maxima permitida para contratar o seguro
     * @param productID código do produto
     * @return
     */
    public Integer retrieveMaxInsuredAge(final Long productID) {
        return salesDBDAO.retrieveMaxInsuredAge(productID);
    }
}

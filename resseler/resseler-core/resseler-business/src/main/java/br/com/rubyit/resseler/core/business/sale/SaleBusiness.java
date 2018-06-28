package br.com.rubyit.resseler.core.business.sale;

import java.util.Calendar;

import br.com.rubyit.resseler.commons.kernel.dto.ProductDTO;
import br.com.rubyit.resseler.core.commons.dto.CommissionsPagingDTO;
import br.com.rubyit.resseler.core.commons.dto.InsuranceCertificate;
import br.com.rubyit.resseler.core.commons.dto.Regional;
import br.com.rubyit.resseler.core.commons.dto.Sale;
import br.com.rubyit.resseler.core.commons.dto.Salesman;
import br.com.rubyit.resseler.core.commons.dto.ScorePagingDTO;
import br.com.rubyit.resseler.core.commons.dto.Workshop;

/**
 * Define Sale Business interface
 *
 * @author a42239
 *
 */
public interface SaleBusiness {

    /**
     * Assinatura do método doSale
     * @param sale
     * @return
     */
    InsuranceCertificate doSale(Sale sale);

    /**
     * Assinatura do método retrieveComissions
     * @param salesman
     * @param pageNumber
     * @return
     */
    CommissionsPagingDTO retrieveComissions(final Salesman salesman, final Integer pageNumber);

    /**
     * Assinatura do método retrievePerformance
     * @param product
     * @param regional
     * @param workshop
     * @param salesman
     * @param startDate
     * @param endDate
     * @param pageNumber
     * @return
     */
    ScorePagingDTO retrievePerformance(final ProductDTO product, final Regional regional, final Workshop workshop,
            final Salesman salesman, final Calendar startDate, final Calendar endDate, final Integer pageNumber);

    /**
     * Assinatura do método checkUpdateMethod
     * @param certificate
     * @return
     */
    Boolean checkUpdateMethod(InsuranceCertificate certificate);

    /**
     * Assinatura do método updatePaymentMethodContract
     * @param certificate
     * @param securityKeyID
     */
    void updatePaymentMethodContract(InsuranceCertificate certificate, String securityKeyID);
}

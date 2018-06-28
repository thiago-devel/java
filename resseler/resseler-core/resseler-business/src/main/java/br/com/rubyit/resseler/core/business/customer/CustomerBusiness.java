package br.com.rubyit.resseler.core.business.customer;

import java.util.List;

import br.com.rubyit.resseler.core.commons.dto.Customer;
import br.com.rubyit.resseler.core.commons.dto.InsuranceCertificate;

/**
 * Define Sale Business interface
 *
 * @author a42239
 *
 */
public interface CustomerBusiness {

	/**
	 * Retorna segunda via do cliente
	 *
	 * @param customer
	 * @return lista de certificados do cliente
	 *
	 */
    public List<InsuranceCertificate> retrieveCustomerSales(Customer customer);
}

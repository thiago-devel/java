
package br.com.cardif.wsdirectsales;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;

/**
 * This object contains factory methods for each Java content interface and Java
 * element interface generated in the br.com.cardif.wsdirectsales package.
 * <p>
 * An ObjectFactory allows you to programatically construct new instances of the
 * Java representation for XML content. The Java representation of XML content
 * can consist of schema derived interfaces and classes representing the binding
 * of schema type definitions, element declarations and model groups. Factory
 * methods for each of these are provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _RetrieveCustomer_QNAME = new QName("http://wsdirectsales.cardif.com.br/",
            "retrieveCustomer");
    private final static QName _RetrieveCustomerResponse_QNAME = new QName("http://wsdirectsales.cardif.com.br/",
            "retrieveCustomerResponse");
    private final static QName _DoSale_QNAME = new QName("http://wsdirectsales.cardif.com.br/", "doSale");
    private final static QName _DoSaleResponse_QNAME = new QName("http://wsdirectsales.cardif.com.br/",
            "doSaleResponse");
    private final static QName _RetrieveCommissions_QNAME = new QName("http://wsdirectsales.cardif.com.br/",
            "retrieveCommissions");
    private final static QName _RetrieveCommissionsResponse_QNAME = new QName("http://wsdirectsales.cardif.com.br/",
            "retrieveCommissionsResponse");
    private final static QName _RetrieveSales_QNAME = new QName("http://wsdirectsales.cardif.com.br/", "retrieveSales");
    private final static QName _RetrieveSalesResponse_QNAME = new QName("http://wsdirectsales.cardif.com.br/",
            "retrieveSalesResponse");

    /**
     * Create a new ObjectFactory that can be used to create new instances of
     * schema derived classes for package: br.com.cardif.wsdirectsales
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link DoSale }
     * 
     */
    public DoSale createDoSale() {
        return new DoSale();
    }

    /**
     * Create an instance of {@link RetrieveCommissions }
     * 
     */
    public RetrieveCommissions createRetrieveCommissions() {
        return new RetrieveCommissions();
    }

    /**
     * Create an instance of {@link RetrieveCustomerIN }
     * 
     */
    public RetrieveCustomerIN createRetrieveCustomerIN() {
        return new RetrieveCustomerIN();
    }

    /**
     * Create an instance of {@link RetrieveCustomerOUT }
     * 
     */
    public RetrieveCustomerOUT createRetrieveCustomerOUT() {
        return new RetrieveCustomerOUT();
    }

    /**
     * Create an instance of {@link DoSaleIN }
     * 
     */
    public DoSaleIN createDoSaleIN() {
        return new DoSaleIN();
    }

    /**
     * Create an instance of {@link DoSaleOUT }
     * 
     */
    public DoSaleOUT createDoSaleOUT() {
        return new DoSaleOUT();
    }

    /**
     * Create an instance of {@link RetrieveCommissionsIN }
     * 
     */
    public RetrieveCommissionsIN createRetrieveCommissionsIN() {
        return new RetrieveCommissionsIN();
    }

    /**
     * Create an instance of {@link RetrieveCommissionsOUT }
     * 
     */
    public RetrieveCommissionsOUT createRetrieveCommissionsOUT() {
        return new RetrieveCommissionsOUT();
    }

    /**
     * Create an instance of {@link RetrieveSalesIN }
     * 
     */
    public RetrieveSalesIN createRetrieveSalesIN() {
        return new RetrieveSalesIN();
    }

    /**
     * Create an instance of {@link RetrieveSalesOUT }
     * 
     */
    public RetrieveSalesOUT createRetrieveSalesOUT() {
        return new RetrieveSalesOUT();
    }

    /**
     * Create an instance of {@link RetrieveCustomerRequest }
     * 
     */
    public RetrieveCustomerRequest createRetrieveCustomerRequest() {
        return new RetrieveCustomerRequest();
    }

    /**
     * Create an instance of {@link RetrieveCustomerResponse }
     * 
     */
    public RetrieveCustomerResponse createRetrieveCustomerResponse() {
        return new RetrieveCustomerResponse();
    }

    /**
     * Create an instance of {@link br.com.cardif.wsdirectsales.DoSaleRequest }
     * 
     */
    public br.com.cardif.wsdirectsales.DoSaleRequest createDoSaleRequest() {
        return new br.com.cardif.wsdirectsales.DoSaleRequest();
    }

    /**
     * Create an instance of {@link DoSaleResponse }
     * 
     */
    public DoSaleResponse createDoSaleResponse() {
        return new DoSaleResponse();
    }

    /**
     * Create an instance of
     * {@link br.com.cardif.wsdirectsales.RetrieveCommissionsRequest }
     * 
     */
    public br.com.cardif.wsdirectsales.RetrieveCommissionsRequest createRetrieveCommissionsRequest() {
        return new br.com.cardif.wsdirectsales.RetrieveCommissionsRequest();
    }

    /**
     * Create an instance of {@link RetrieveCommissionsResponse }
     * 
     */
    public RetrieveCommissionsResponse createRetrieveCommissionsResponse() {
        return new RetrieveCommissionsResponse();
    }

    /**
     * Create an instance of {@link RetrieveSalesRequest }
     * 
     */
    public RetrieveSalesRequest createRetrieveSalesRequest() {
        return new RetrieveSalesRequest();
    }

    /**
     * Create an instance of {@link RetrieveSalesResponse }
     * 
     */
    public RetrieveSalesResponse createRetrieveSalesResponse() {
        return new RetrieveSalesResponse();
    }

    /**
     * Create an instance of {@link Sale }
     * 
     */
    public Sale createSale() {
        return new Sale();
    }

    /**
     * Create an instance of {@link Customer }
     * 
     */
    public Customer createCustomer() {
        return new Customer();
    }

    /**
     * Create an instance of {@link SystemPerson }
     * 
     */
    public SystemPerson createSystemPerson() {
        return new SystemPerson();
    }

    /**
     * Create an instance of {@link PersonDTO }
     * 
     */
    public PersonDTO createPersonDTO() {
        return new PersonDTO();
    }

    /**
     * Create an instance of {@link LoginDTO }
     * 
     */
    public LoginDTO createLoginDTO() {
        return new LoginDTO();
    }

    /**
     * Create an instance of {@link Identification }
     * 
     */
    public Identification createIdentification() {
        return new Identification();
    }

    /**
     * Create an instance of {@link Partner }
     * 
     */
    public Partner createPartner() {
        return new Partner();
    }

    /**
     * Create an instance of {@link Contacts }
     * 
     */
    public Contacts createContacts() {
        return new Contacts();
    }

    /**
     * Create an instance of {@link ContactDTO }
     * 
     */
    public ContactDTO createContactDTO() {
        return new ContactDTO();
    }

    /**
     * Create an instance of {@link AddressDTO }
     * 
     */
    public AddressDTO createAddressDTO() {
        return new AddressDTO();
    }

    /**
     * Create an instance of {@link PhoneDTO }
     * 
     */
    public PhoneDTO createPhoneDTO() {
        return new PhoneDTO();
    }

    /**
     * Create an instance of {@link IdentityDTO }
     * 
     */
    public IdentityDTO createIdentityDTO() {
        return new IdentityDTO();
    }

    /**
     * Create an instance of {@link Operator }
     * 
     */
    public Operator createOperator() {
        return new Operator();
    }

    /**
     * Create an instance of {@link Certificates }
     * 
     */
    public Certificates createCertificates() {
        return new Certificates();
    }

    /**
     * Create an instance of {@link InsuranceCertificate }
     * 
     */
    public InsuranceCertificate createInsuranceCertificate() {
        return new InsuranceCertificate();
    }

    /**
     * Create an instance of {@link CertificateDTO }
     * 
     */
    public CertificateDTO createCertificateDTO() {
        return new CertificateDTO();
    }

    /**
     * Create an instance of {@link Coverages }
     * 
     */
    public Coverages createCoverages() {
        return new Coverages();
    }

    /**
     * Create an instance of {@link Coverage }
     * 
     */
    public Coverage createCoverage() {
        return new Coverage();
    }

    /**
     * Create an instance of {@link PaymentMethod }
     * 
     */
    public PaymentMethod createPaymentMethod() {
        return new PaymentMethod();
    }

    /**
     * Create an instance of {@link CardPaymentDTO }
     * 
     */
    public CardPaymentDTO createCardPaymentDTO() {
        return new CardPaymentDTO();
    }

    /**
     * Create an instance of {@link InsuranceDTO }
     * 
     */
    public InsuranceDTO createInsuranceDTO() {
        return new InsuranceDTO();
    }

    /**
     * Create an instance of {@link ProductDTO }
     * 
     */
    public ProductDTO createProductDTO() {
        return new ProductDTO();
    }

    /**
     * Create an instance of {@link Salesman }
     * 
     */
    public Salesman createSalesman() {
        return new Salesman();
    }

    /**
     * Create an instance of {@link Taxes }
     * 
     */
    public Taxes createTaxes() {
        return new Taxes();
    }

    /**
     * Create an instance of {@link Tax }
     * 
     */
    public Tax createTax() {
        return new Tax();
    }

    /**
     * Create an instance of {@link Workshop }
     * 
     */
    public Workshop createWorkshop() {
        return new Workshop();
    }

    /**
     * Create an instance of {@link CapitalSeries }
     * 
     */
    public CapitalSeries createCapitalSeries() {
        return new CapitalSeries();
    }

    /**
     * Create an instance of {@link DoSale.DoSaleRequest }
     * 
     */
    public DoSale.DoSaleRequest createDoSaleDoSaleRequest() {
        return new DoSale.DoSaleRequest();
    }

    /**
     * Create an instance of
     * {@link RetrieveCommissions.RetrieveCommissionsRequest }
     * 
     */
    public RetrieveCommissions.RetrieveCommissionsRequest createRetrieveCommissionsRetrieveCommissionsRequest() {
        return new RetrieveCommissions.RetrieveCommissionsRequest();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}
     * {@link RetrieveCustomerIN }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://wsdirectsales.cardif.com.br/", name = "retrieveCustomer")
    public JAXBElement<RetrieveCustomerIN> createRetrieveCustomer(RetrieveCustomerIN value) {
        return new JAXBElement<RetrieveCustomerIN>(_RetrieveCustomer_QNAME, RetrieveCustomerIN.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}
     * {@link RetrieveCustomerOUT }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://wsdirectsales.cardif.com.br/", name = "retrieveCustomerResponse")
    public JAXBElement<RetrieveCustomerOUT> createRetrieveCustomerResponse(RetrieveCustomerOUT value) {
        return new JAXBElement<RetrieveCustomerOUT>(_RetrieveCustomerResponse_QNAME, RetrieveCustomerOUT.class, null,
                value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link DoSaleIN }
     * {@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://wsdirectsales.cardif.com.br/", name = "doSale")
    public JAXBElement<DoSaleIN> createDoSale(DoSaleIN value) {
        return new JAXBElement<DoSaleIN>(_DoSale_QNAME, DoSaleIN.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link DoSaleOUT }
     * {@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://wsdirectsales.cardif.com.br/", name = "doSaleResponse")
    public JAXBElement<DoSaleOUT> createDoSaleResponse(DoSaleOUT value) {
        return new JAXBElement<DoSaleOUT>(_DoSaleResponse_QNAME, DoSaleOUT.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}
     * {@link RetrieveCommissionsIN }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://wsdirectsales.cardif.com.br/", name = "retrieveCommissions")
    public JAXBElement<RetrieveCommissionsIN> createRetrieveCommissions(RetrieveCommissionsIN value) {
        return new JAXBElement<RetrieveCommissionsIN>(_RetrieveCommissions_QNAME, RetrieveCommissionsIN.class, null,
                value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}
     * {@link RetrieveCommissionsOUT }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://wsdirectsales.cardif.com.br/", name = "retrieveCommissionsResponse")
    public JAXBElement<RetrieveCommissionsOUT> createRetrieveCommissionsResponse(RetrieveCommissionsOUT value) {
        return new JAXBElement<RetrieveCommissionsOUT>(_RetrieveCommissionsResponse_QNAME, RetrieveCommissionsOUT.class,
                null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link RetrieveSalesIN
     * }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://wsdirectsales.cardif.com.br/", name = "retrieveSales")
    public JAXBElement<RetrieveSalesIN> createRetrieveSales(RetrieveSalesIN value) {
        return new JAXBElement<RetrieveSalesIN>(_RetrieveSales_QNAME, RetrieveSalesIN.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}
     * {@link RetrieveSalesOUT }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://wsdirectsales.cardif.com.br/", name = "retrieveSalesResponse")
    public JAXBElement<RetrieveSalesOUT> createRetrieveSalesResponse(RetrieveSalesOUT value) {
        return new JAXBElement<RetrieveSalesOUT>(_RetrieveSalesResponse_QNAME, RetrieveSalesOUT.class, null, value);
    }

}

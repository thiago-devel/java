using Microsoft.Web.Services3.Design;
using WSDirectSales.wsdirectsales;

using Microsoft.Web.Services3;
using Microsoft.Web.Services3.Security;
using Microsoft.Web.Services3.Security.Tokens;

namespace WSDirectSales
{

    class CustomerSalesServicesClient {

        private CustomerSalesServiceBaseClient client = new CustomerSalesServiceBaseClient();

        public CustomerSalesServicesClient()
        {
            // Apply policy here
            Policy policy = new Policy();
            policy.Assertions.Add(new UsernameClientAssertion("AppCardifWsDirectSales", "Cardfif@WsDirectSales"));
            client.SetPolicy(policy);
        }

        public retrieveCustomerSalesResponse retrieveCustomerSales(retrieveCustomerSalesRequest retrieveCustomerSalesRequest) {
            return client.retrieveCustomerSales(retrieveCustomerSalesRequest);
        }

        public retrievePerformanceResponse retrievePerformance(retrievePerformanceRequest retrievePerformanceRequest) {

            return client.retrievePerformance(retrievePerformanceRequest);
        }

        public retrieveCommissionsResponse retrieveCommissions(retrieveCommissionsRequest retrieveCommissionsRequest) {

            return client.retrieveCommissions(retrieveCommissionsRequest);
        }

        public doSaleResponse doSale(doSaleRequest doSaleRequest) {

            return client.doSale(doSaleRequest);
        }

        public updatePaymentMethodResponse updatePaymentMethod(updatePaymentMethodRequest updatePaymentMethodRequest)
        {
            return client.updatePaymentMethod(updatePaymentMethodRequest);
        }
    }

    public class UsernameClientAssertion : SecurityPolicyAssertion
    {
        public string username;
        public string password;

        public UsernameClientAssertion(string username, string password)
        {
            this.username = username;
            this.password = password;
        }

        public override SoapFilter
               CreateClientOutputFilter(FilterCreationContext context)
        {
            return new ClientOutputFilter(this, context);
        }

        public override SoapFilter
               CreateClientInputFilter(FilterCreationContext context)
        {
            return null;
        }

        public override SoapFilter
               CreateServiceInputFilter(FilterCreationContext context)
        {
            return null;
        }

        public override SoapFilter
               CreateServiceOutputFilter(FilterCreationContext context)
        {
            return null;
        }
    }

    class ClientOutputFilter : SendSecurityFilter
    {
        UsernameClientAssertion parentAssertion;
        FilterCreationContext filterContext;

        public ClientOutputFilter(UsernameClientAssertion parentAssertion,
                                  FilterCreationContext filterContext)
            : base(parentAssertion.ServiceActor, false, parentAssertion.ClientActor)
        {
            this.parentAssertion = parentAssertion;
            this.filterContext = filterContext;
        }

        public override void SecureMessage(SoapEnvelope envelope, Security security)
        {
            UsernameToken userToken = new UsernameToken(
                                                parentAssertion.username,
                                                parentAssertion.password,
                                                PasswordOption.SendHashed);

            // Add the token to the SOAP header.
            security.Tokens.Add(userToken);
        }
    }
}

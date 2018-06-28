using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using Microsoft.Web.Services3;
using Microsoft.Web.Services3.Security;
using Microsoft.Web.Services3.Security.Tokens;
using WSDirectSales.wsdirectsales;
using System.ServiceModel;
using System.ServiceModel.Description;
using System.ServiceModel.Dispatcher;
using System.ServiceModel.Channels;
using System.Xml;

namespace WSDirectSales {

    class OldTestCustomerSalesServiceClient {

        static void Main1(string[] args) {

            CustomerSalesService client = new CustomerSalesService();


            UsernameToken textToken = new UsernameToken("AppCardifWsDirectSales",
                                                        "Cardfif@WsDirectSales",
                                                        PasswordOption.SendNone);

            //UsernameToken textToken = new UsernameToken(null);
            //textToken.

            try {
                //client.RequestSoapContext.Security.Tokens.Add(textToken);
                //client.RequestSoapContext.Security.
                //client.RequestSoapContext.Security.Timestamp.TtlInSeconds = 60;

                @operator oper = new @operator();
                oper.operatorName = "José da Silva";
                loginDTO loginOper = new loginDTO();
                loginOper.username = "usermagazine";
                loginOper.password = "pass@cardif";
                oper.login = loginOper;

                identityDTO iden = new identityDTO();
                iden.documentType = document.CPF;
                iden.documentValue = "27890664001";
                customer cust = new customer();
                cust.identity = iden;

                retrieveCustomerSalesRequest request = new retrieveCustomerSalesRequest();
                request.@operator = oper;
                request.customer = cust;

                retrieveCustomerSalesResponse response = (retrieveCustomerSalesResponse)client.retrieveCustomerSales(request);

                Console.WriteLine("Response = " + response);
            } catch (Exception ex) {
                Console.WriteLine("Exception to call service : " + ex);
            }
            
        }

    }
}

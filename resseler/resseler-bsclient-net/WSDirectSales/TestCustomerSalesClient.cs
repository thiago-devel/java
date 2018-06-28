using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using WSDirectSales.wsdirectsales;

namespace WSDirectSales {

    class TestCustomerSalesClient {

        static void Main(string[] args) {
            TestCustomerSalesClient tester = new TestCustomerSalesClient();

            //tester.testUpdatePaymentMethod();
            //tester.testDoSale();
            tester.testRetrievePerformance();
            //tester.testRetrieveCommissions();
            //tester.testRetrieveCustomerSales();
        }

        private void testUpdatePaymentMethod() {

            CustomerSalesServicesClient client = new CustomerSalesServicesClient();

            cardPaymentDTO card = new cardPaymentDTO();
            card.cardDisplayName = "MARIA L B SANTOS";
            card.cardFlag = "LUIZA";
            card.cardNumber = "5307804589564512";
            card.cardValidity = new DateTime(2017, 4, 1);
            card.cardValiditySpecified = true;

            paymentMethod paymentMethod = new paymentMethod();
            paymentMethod.cardPayment = card;

            identityDTO idensales = new identityDTO();
            idensales.documentType = document.CPF;
            idensales.documentTypeSpecified = true;
            idensales.documentValue = "44795714851";

            salesman salesman1 = new salesman();
            salesman1.identity = idensales;


            updatePaymentMethodRequest request = new updatePaymentMethodRequest();
            insuranceCertificate certificate1 = new insuranceCertificate();
            certificate1.paymentMethod = paymentMethod;
            certificate1.salesman = salesman1;
            certificate1.contractNumber = "1095828040419";

            updatePaymentMethodResponse response = null;

            request.certificate = certificate1;

            try {
                response = client.updatePaymentMethod(request);

                Console.WriteLine("Response = " + response);
                Console.WriteLine("Updated = " + response.updated);
                Console.WriteLine("Message = " + response.message);
            } catch (Exception ex) {
                Console.WriteLine("Exception to call service : " + ex);
            }

        }

        private void testRetrieveCustomerSales() {
            
            customer customer = new customer();
            identityDTO ident = new identityDTO();
            ident.documentType = document.CPF;
            ident.documentTypeSpecified = true;
            ident.documentValue = "82753247005";
            customer.identity = ident;

            retrieveCustomerSalesRequest request = new retrieveCustomerSalesRequest();
            request.customer = customer;

            CustomerSalesServicesClient client = new CustomerSalesServicesClient();
            retrieveCustomerSalesResponse response = null;

            customer c = new customer();
            addressDTO add = new addressDTO();
            add.addressDetail = "RUA 1";
            add.type = addressType.RESIDENTIAL;
            phoneDTO phone = new phoneDTO();
            phone.phoneNumber = "1143216363";
            phone.type = phoneType.RESIDENTIAL;
            contactDTO cc = new contactDTO();
            cc.address = add;
            cc.phone = phone;
            contactDTO[] contatos = new contactDTO[] {cc };
            c.contacts = contatos;

            sale saleee = new sale();
            salesman ssss = new salesman();
            //ssss.operatorName
            //saleee.

            try {
                response =  client.retrieveCustomerSales(request);

                Console.WriteLine("Response = " + response);
            } catch (Exception ex) {
                Console.WriteLine("Exception to call service : " + ex);
            }
        }

        private void testDoSale()
        {
            CustomerSalesServicesClient client = new CustomerSalesServicesClient();

            doSaleRequest request = new doSaleRequest();

            customer c = new customer();
            c.birthDate = new DateTime(1984, 3, 3);
            c.birthDateSpecified = true;

            addressDTO add = new addressDTO();
            add.addressDetail = "Rua 1";
            add.type = addressType.RESIDENTIAL;
            add.typeSpecified = true;
            add.addressNumber = "123";
            add.addressPostalCode = "06871120";
            add.city = "Embu";
            add.neighborhood = "Marilú";
            add.state = "SP";

            phoneDTO phone = new phoneDTO();
            phone.phoneNumber = "1143216363";
            phone.type = phoneType.RESIDENTIAL;
            phone.typeSpecified = true;

            c.fullName = "Teste Abobrinha";
            c.gender = gender.MALE;
            c.genderSpecified = true;

            identityDTO iden = new identityDTO();
            iden.documentType = document.CPF;
            iden.documentTypeSpecified = true;
            iden.documentValue = "40614102022";


            c.maritalStatus = maritalStatus.SINGLE;
            c.maritalStatusSpecified = true;
            //criar novo contato para celular e repetir 
            //cus.contacts[1].phone.phoneNumber = segurosuppro.Celular;
            //cus.contacts[1].phone.type = phoneType.MOBILE;

            cardPaymentDTO card = new cardPaymentDTO();
            card.cardDisplayName = "THIAGO SANTANA";
            card.cardFlag = "luiza";
            card.cardNumber = "5307804589564512";
            card.cardSecurityCode = "456";
            card.cardValidity = new DateTime(1985, 1, 26);
            card.cardValiditySpecified = true;
            card.cardValue = Convert.ToDecimal("19,90");
            card.cardValueSpecified = true;

            productDTO prod = new productDTO();
            prod.descripton = "CARTÃO PROTEGIDO";
            prod.ID = 25;
            prod.IDSpecified = true;

            contactDTO cc = new contactDTO();
            cc.address = add;
            cc.phone = phone;

            contactDTO[] contatos = new contactDTO[] { cc };
            c.contacts = contatos;
            c.identity = iden;

            paymentMethod payM = new paymentMethod();
            payM.cardPayment = card;

            identityDTO idensales = new identityDTO();
            idensales.documentType = document.CPF;
            idensales.documentTypeSpecified = true;
            idensales.documentValue = "10752104969";

            loginDTO log = new loginDTO();
            log.username = "MTVI_SILVA";
            log.password = "MTVI_SILVA_____0____";

            partner part = new partner();
            part.ID = 001;
            part.IDSpecified = true;

            salesman salman = new salesman();
            salman.identity = idensales;
            salman.login = log;
            salman.partner = part;
            salman.operatorName = "Saulo Mezencio";

            sale sa = new sale();
            sa.customer = c;
            sa.paymentMethod = payM;
            sa.product = prod;
            sa.salesman = salman;

            request.sale = sa;

            doSaleResponse response = null;

            try {

                response = client.doSale(request);

                Console.WriteLine("Response = " + response);
                Console.WriteLine("PARANDO PARA VISUALIZAR JANELA");

            } catch (Microsoft.Web.Services3.ResponseProcessingException exR) {
                                
                Console.WriteLine("Exception to call service FAULT: " + exR.Response.OuterXml);
                Console.WriteLine("PARANDO PARA VISUALIZAR JANELA ANTERIOR");
            }/* catch (Exception ex) {                
                Console.WriteLine("Exception : " + ex);
                Console.WriteLine("PARANDO PARA VISUALIZAR JANELA");
            }*/
        }
        
        private void testRetrieveCommissions() {
            CustomerSalesServicesClient client = new CustomerSalesServicesClient();
            
            retrieveCommissionsRequest request = new retrieveCommissionsRequest();
            identityDTO idensales = new identityDTO();
            idensales.documentType = document.CPF;
            idensales.documentTypeSpecified = true;
            idensales.documentValue = "06573508696";

            salesman salman = new salesman();
            salman.identity = idensales;

            request.pageNumber = 1;
            request.pageNumberSpecified = true;
            request.salesman = salman;
            


            retrieveCommissionsResponse response = null;

            try {
                response = client.retrieveCommissions(request);

                Console.WriteLine("Response = " + response);
            } catch (Exception ex) {
                Console.WriteLine("Exception to call service : " + ex);
            }
        }

        private void testRetrievePerformance() {
            CustomerSalesServicesClient client = new CustomerSalesServicesClient();

            retrievePerformanceRequest request = new retrievePerformanceRequest();
            request.startDate = new DateTime(2016, 7, 1);
            request.startDateSpecified = true;
            request.endDate = new DateTime(2016, 7, 10);
            request.endDateSpecified = true;

            //request.
            retrievePerformanceResponse response = null;

            try {
                response = client.retrievePerformance(request);

                Console.WriteLine("Response = " + response);
            } catch (Exception ex) {
                Console.WriteLine("Exception to call service : " + ex);
            }
        }
    }
}

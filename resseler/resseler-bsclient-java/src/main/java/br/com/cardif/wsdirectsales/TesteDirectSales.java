package br.com.cardif.wsdirectsales;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import javax.xml.ws.BindingProvider;
import javax.xml.ws.handler.Handler;

public class TesteDirectSales {

    public static void main(final String[] args)  {
        final URL url = new URL("https://uatparceiros.cardif.com.br/wsdirectsales/CustomerSalesService?wsdl");
        final CustomerSalesService_Service service = new CustomerSalesService_Service(url);
        final CustomerSalesService port = service.getCustomerSalesServicePort();

        final BindingProvider bindingProvider = (BindingProvider) port;
        final List<Handler> handlerChain = bindingProvider.getBinding().getHandlerChain();
        handlerChain.add(new WSSecurityHandler("AppCardifWsDirectSales", "Cardfif@WsDirectSales"));

        bindingProvider.getBinding().setHandlerChain(handlerChain);

        final RetrieveCustomerRequest retrieveCustomerRequest = new RetrieveCustomerRequest();
        final RetrieveCustomerResponse result = port.retrieveCustomer(retrieveCustomerRequest);

        System.out.println(result);
    }

}

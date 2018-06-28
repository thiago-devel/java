using System;
using WSDirectSales.wsdirectsales;

public class CustomerSalesServiceBaseClient : CustomerSalesService
{
    protected override WebRequest GetWebRequest(Uri uri)
    {
        // call the base class to get the underlying WebRequest object
        HttpWebRequest req = (HttpWebRequest)base.GetWebRequest(uri);

                
        return (WebRequest)req;
    }
}

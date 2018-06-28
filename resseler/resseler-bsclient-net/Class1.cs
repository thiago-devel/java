using System;

public class CustomerSalesServiceClient : CustomerSalesService
{
    protected override WebRequest GetWebRequest(Uri uri)
    {
        // call the base class to get the underlying WebRequest object
        HttpWebRequest req = (HttpWebRequest)base.GetWebRequest(uri);

        if (null != this.m_HeaderName)
        {
            // set the header
            req.Headers.Add(this.m_HeaderName, this.m_HeaderValue);
        }

        // return the WebRequest to the caller
        return (WebRequest)req;
    }
}

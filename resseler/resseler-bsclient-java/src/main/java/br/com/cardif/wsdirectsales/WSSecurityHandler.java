package br.com.cardif.wsdirectsales;

import java.io.Closeable;
import java.io.IOException;
import java.io.StringWriter;
import java.util.HashSet;
import java.util.Set;

import javax.xml.namespace.QName;
import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPMessage;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.ws.handler.MessageContext;
import javax.xml.ws.handler.soap.SOAPHandler;
import javax.xml.ws.handler.soap.SOAPMessageContext;

import org.apache.wss4j.common.ext.WSSecurityException;
import org.apache.wss4j.dom.WSConstants;
import org.apache.wss4j.dom.message.WSSecHeader;
import org.apache.wss4j.dom.message.WSSecTimestamp;
import org.apache.wss4j.dom.message.WSSecUsernameToken;

public class WSSecurityHandler implements SOAPHandler<SOAPMessageContext> {

    private final String username;
    private final String password;

    private final boolean encoded;

    public WSSecurityHandler(final String username, final String password) {
        this(username, password, false);
    }

    public WSSecurityHandler(final String username, final String password, final boolean encoded) {
        this.username = username;
        this.password = password;
        this.encoded = encoded;
    }

    @Override
    public void close(final MessageContext context) {
    }

    @Override
    public boolean handleFault(final SOAPMessageContext context) {
        return true;
    }

    @Override
    public boolean handleMessage(final SOAPMessageContext context) {
        final Boolean outbound = (Boolean) context.get(MessageContext.MESSAGE_OUTBOUND_PROPERTY);
        if (outbound) {
            try {
                final SOAPMessage message = context.getMessage();
                final WSSecHeader header = new WSSecHeader(message.getSOAPBody().getOwnerDocument());

                header.insertSecurityHeader();
                final WSSecUsernameToken usernameToken = new WSSecUsernameToken();

                usernameToken.setUserInfo(username, password);
                usernameToken.setPasswordsAreEncoded(encoded);
                usernameToken.setPasswordType(WSConstants.PASSWORD_DIGEST);
                usernameToken.addNonce();
                usernameToken.addCreated();
                usernameToken.prepare(message.getSOAPBody().getOwnerDocument());
                usernameToken.appendToHeader(header);

                final WSSecTimestamp timestamp = new WSSecTimestamp();
                timestamp.build(message.getSOAPBody().getOwnerDocument(), header);

                System.out.println("** Response: " + convertSOAPToString(context.getMessage()));

            } catch (WSSecurityException | SOAPException e) {
                e.printStackTrace();
                return false;
            }
        }
        return true;
    }

    @Override
    public Set<QName> getHeaders() {

        final QName securityHeader = new QName(WSConstants.WSSE_NS, "Security");
        final QName securityUsernameTokenHeader = new QName(WSConstants.WSSE_NS, WSConstants.USERNAME_TOKEN_LN);

        final QName securityUsernameHeader = new QName(WSConstants.WSSE_NS, WSConstants.USERNAME_LN);
        final QName securityPasswordHeader = new QName(WSConstants.WSSE_NS, WSConstants.PASSWORD_TEXT);

        final QName securityNonceHeader = new QName(WSConstants.WSSE_NS, WSConstants.NONCE_LN);
        final QName securityCreatedHeader = new QName(WSConstants.WSSE_NS, WSConstants.CREATED_LN);

        final HashSet<QName> headers = new HashSet<QName>();

        headers.add(securityHeader);
        headers.add(securityUsernameTokenHeader);
        headers.add(securityUsernameHeader);
        headers.add(securityPasswordHeader);
        headers.add(securityNonceHeader);
        headers.add(securityCreatedHeader);

        return headers;
    }

    private String convertSOAPToString(final SOAPMessage soapMessage) {
        StringWriter stringWriter = null;

        try {
            stringWriter = new StringWriter();
            final StreamResult streamResult = new StreamResult(stringWriter);

            final TransformerFactory transformFactory = TransformerFactory.newInstance();
            final Transformer transformer = transformFactory.newTransformer();
            transformer.transform(new DOMSource(soapMessage.getSOAPPart()), streamResult);

        } catch (final TransformerException e) {
            throw new RuntimeException(e);
        } finally {
            close(stringWriter);
        }
        return stringWriter.toString();
    }

    private void close(final Closeable closeable) {
        if (closeable != null) {
            try {
                closeable.close();
            } catch (final IOException e) {
                e.printStackTrace();
            }
        }
    }
}

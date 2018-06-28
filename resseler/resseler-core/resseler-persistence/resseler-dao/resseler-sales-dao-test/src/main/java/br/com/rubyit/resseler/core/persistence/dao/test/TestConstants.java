package br.com.rubyit.resseler.core.persistence.dao.test;

public interface TestConstants {

    final String tag2SearchCPF = "CPF";
    final String tagCPFValue1 = "082.123.322-12";
    final String tagCPFValue2 = "082.123.322-14";
    static final String MESSAGEM_CODE_PARAMETER_PAYLOADS_DE_TESTES = "PAYLOADS_DE_TESTES";

    static final String PAYLOAD_REQUEST_EXAMPLE = "<soapenv:Envelope xmlns:soapenv='http://schemas.xmlsoap.org/soap/envelope/' xmlns:ws='http://ws.logger.request.cardif.com.br/'>"
            + "<soapenv:Header/>" + "<soapenv:Body>"
            + " <CPF>082.123.322-12</CPF>" + " <rg>23.432.23-23</rg>"
            + "</soapenv:Body>" + "</soapenv:Envelope>";
    static final String PAYLOAD_RESPONSE_EXAMPLE = "<soapenv:Envelope xmlns:soapenv='http://schemas.xmlsoap.org/soap/envelope/' xmlns:ws='http://ws.logger.request.cardif.com.br/'>"
            + "<soapenv:Header/>" + "<soapenv:Body>"
            + " <result><CPF>082.123.322-12</CPF>"
            + " <rg>23.432.23-23</rg></result>" + "</soapenv:Body>"
            + "</soapenv:Envelope>";
    static final String PAYLOAD_REQUEST_EXAMPLE2 = "<soapenv:Envelope xmlns:soapenv='http://schemas.xmlsoap.org/soap/envelope/' xmlns:ws='http://ws.logger.request.cardif.com.br/'>"
            + "<soapenv:Header/>" + "<soapenv:Body>"
            + " <CPF>082.123.322-14</CPF>" + " <rg>23.432.23-23</rg>"
            + "</soapenv:Body>" + "</soapenv:Envelope>";
    static final String PAYLOAD_RESPONSE_EXAMPLE2 = "<soapenv:Envelope xmlns:soapenv='http://schemas.xmlsoap.org/soap/envelope/' xmlns:ws='http://ws.logger.request.cardif.com.br/'>"
            + "<soapenv:Header/>" + "<soapenv:Body>"
            + " <result><CPF>082.123.322-14</CPF>"
            + " <rg>23.432.23-23</rg></result>" + "</soapenv:Body>"
            + "</soapenv:Envelope>";
}

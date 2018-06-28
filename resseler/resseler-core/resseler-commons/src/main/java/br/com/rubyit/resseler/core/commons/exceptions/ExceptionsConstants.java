package br.com.rubyit.resseler.core.commons.exceptions;

/**
 * Classes de constantes
 * utilizadas em exceções
 * @author b11527
 *
 */
public final class ExceptionsConstants {

    /**
     * Constructor default
     */
    private ExceptionsConstants() {
    }
    
    public static final String MSG_1_CERTIFICATE_ALREADY_EXISTS_FOR_CPF = "Já existe um certificado ativo para o CPF informado.";
    public static final String MSG_3_CERTIFICATE_ALREADY_EXISTS_FOR_CPF = "Já existem 3 certificados ativos para o CPF informado.";
    
    public static final String CODE_PREFIX = "@0";
    //CODE
    public static final String CODE_GENERIC = CODE_PREFIX + "999";
    public static final String CODE_PRODUTO_INVALIDO = CODE_PREFIX+"001";
    public static final String CODE_DATAINICIAL_EMPTY = CODE_PREFIX+"002";
    public static final String CODE_DATAFIM_EMPTY = CODE_PREFIX+"003";
    public static final String CODE_IDENTITY_EMPTY = CODE_PREFIX+"004";
    public static final String CODE_CPF_EMPTY = CODE_PREFIX+"005";
    public static final String CODE_DOCUMENTTYPE_EMPTY = CODE_PREFIX+"006";
    public static final String CODE_CPF_INVALIDO = CODE_PREFIX+"007";
    public static final String CODE_SALESMAN_EMPTY = CODE_PREFIX+"008";
    public static final String CODE_CREDITCARD_EMPTY = CODE_PREFIX+"009";
    public static final String CODE_NOMECARTAO_DIGITOS = CODE_PREFIX+"010";
    public static final String CODE_NUMCARTAO_INVALIDO = CODE_PREFIX+"011";
    public static final String CODE_NUMCARTAO_TAMANHO_INVALIDO = CODE_PREFIX+"012";
    public static final String CODE_CARTAO_NOT_MAGAZINELUIZA = CODE_PREFIX+"013";
    public static final String CODE_CREDITCARDNAME_EMPTY = CODE_PREFIX+"014";
    public static final String CODE_CREDITCARDNAME_LENGTH = CODE_PREFIX+"015";
    public static final String CODE_PERSON_EMPTY = CODE_PREFIX+"016";
    public static final String CODE_PERSON_CPF_EMPTY = CODE_PREFIX+"017";
    public static final String CODE_PERSON_CPF_INVALID = CODE_PREFIX+"018";
    public static final String CODE_PERSON_CPF_LENGTH = CODE_PREFIX+"019";
    public static final String CODE_PERSON_NAME_EMPTY = CODE_PREFIX+"020";
    public static final String CODE_PERSON_NAME_LENGTH = CODE_PREFIX+"021";
    public static final String CODE_PERSON_NAME_INVALID = CODE_PREFIX+"022";
    public static final String CODE_CONTACTS_EMPTY = CODE_PREFIX+"023";
    public static final String CODE_CONTACT_PHONE_EMPTY = CODE_PREFIX+"024";
    public static final String CODE_CONTACT_PHONENUM_EMPTY = CODE_PREFIX+"025";
    public static final String CODE_CONTACT_PHONE_NONUMERIC = CODE_PREFIX+"026";
    public static final String CODE_CONTACT_PHONE_LENGTH = CODE_PREFIX+"027";
    public static final String CODE_GENDER_EMPTY = CODE_PREFIX+"028";
    public static final String CODE_BIRTHDATE_EMPTY = CODE_PREFIX+"029";
    public static final String CODE_AGE_INVALID = CODE_PREFIX+"030";
    public static final String CODE_MARITALSTATUS_EMPTY = CODE_PREFIX+"031";
    public static final String CODE_ADDRESS_EMPTY = CODE_PREFIX+"032";
    public static final String CODE_ADDRESS_DETAIL_INVALID = CODE_PREFIX+"033";
    public static final String CODE_ADDRESS_NUMBER_EMPTY = CODE_PREFIX+"034";
    public static final String CODE_ADDRESS_NUMBER_LENGTH = CODE_PREFIX+"035";
    public static final String CODE_ADDRESS_NEIGH_INVALID = CODE_PREFIX+"036";
    public static final String CODE_ADDRESS_CITY_INVALID = CODE_PREFIX+"037";
    public static final String CODE_ADDRESS_STATE_INVALID = CODE_PREFIX+"038";
    public static final String CODE_ADDRESS_REFERENCE_INVALID = CODE_PREFIX+"039";
    public static final String CODE_CODEPOSTAL_EMPTY = CODE_PREFIX+"040";
    public static final String CODE_CODEPOSTAL_INVALID = CODE_PREFIX+"041";
    public static final String CODE_CODEPOSTAL_LENGTH = CODE_PREFIX+"042";
    public static final String CODE_CODEPOSTAL_FORMAT1 = CODE_PREFIX+"043";
    public static final String CODE_CODEPOSTAL_FORMAT2 = CODE_PREFIX+"044";
    public static final String CODE_FORM_PAYMENT_INVALID = CODE_PREFIX+"045";
    public static final String CODE_CARD_SECCODE_EMPTY = CODE_PREFIX+"046";
    public static final String CODE_CARD_VALUE_EMPTY = CODE_PREFIX+"047";
    public static final String CODE_CARD_VALUE_INVALID = CODE_PREFIX+"048";
    public static final String CODE_DATE_EMPTY = CODE_PREFIX+"049";
    public static final String CODE_DATE_INVALID = CODE_PREFIX+"050";
    public static final String CODE_CARD_NUMBER_EMPTY = CODE_PREFIX+"051";
    public static final String CODE_CARDNUMBER_EMPTY = CODE_PREFIX+"052";
    public static final String CODE_SAVESALESMAN_EMPTY = CODE_PREFIX+"053";
    public static final String CODE_SAVESALESMAN_BIRTH_EMPTY = CODE_PREFIX+"054";
    public static final String CODE_PARTNER_EMPTY = CODE_PREFIX+"055";
    public static final String CODE_PARTNERID_EMPTY = CODE_PREFIX+"056";
    public static final String CODE_ROLE_EMPTY = CODE_PREFIX+"057";
    public static final String CODE_SALESMAN_CODE_EMPTY = CODE_PREFIX+"058";
    public static final String CODE_UP_SALESMAN_EMPTY = CODE_PREFIX+"059";
    public static final String CODE_UP_BHC_EMPTY = CODE_PREFIX+"060";
    public static final String CODE_UP_AUT_EMPTY = CODE_PREFIX+"061";
    public static final String CODE_UP_BHC_ID_EMPTY = CODE_PREFIX+"062";
    public static final String CODE_FIND_SALESMAN_EMPTY = CODE_PREFIX+"063";
    public static final String CODE_FIND_BHC_EMPTY = CODE_PREFIX+"064";
    public static final String CODE_USER_EMPTY = CODE_PREFIX+"065";
    public static final String CODE_PROFILE_EMPTY = CODE_PREFIX+"066";
    public static final String CODE_USERWORD_EMPTY = CODE_PREFIX+"067";
    public static final String CODE_USER_GRP_EMPTY = CODE_PREFIX+"068";
    public static final String CODE_USERCODE_EMPTY = CODE_PREFIX+"069";
    public static final String CODE_USERPWD_EMPTY = CODE_PREFIX+"070";
    public static final String CODE_BHC_LENGTH = CODE_PREFIX+"071";
    public static final String CODE_PAYMENTMETHOD_EMPTY = CODE_PREFIX+"072";
    public static final String CODE_UP_SALESMAN_NOTFOUND = CODE_PREFIX+"073";
    public static final String CODE_SALESMAN_NOTFOUND = CODE_PREFIX+"074";
    public static final String CODE_SALESMAN_IN_BD = CODE_PREFIX+"075";
    public static final String CODE_NO_CAPITALSERIES_USE = CODE_PREFIX+"076";
    public static final String CODE_CAPITALSERIESID_NULL = CODE_PREFIX+"077";
    public static final String CODE_CERTIFICATENUMBER_NULL = CODE_PREFIX+"078";
    public static final String CODE_NOT_UPDATED_CAPOLD = CODE_PREFIX+"079";
    public static final String CODE_PRODUCTPREFIX_EMPTY = CODE_PREFIX+"080";
    public static final String CODE_SALESMANID_NULL = CODE_PREFIX+"081";
    public static final String CODE_CUSTOMERADDRESS_NULL = CODE_PREFIX+"082";
    public static final String CODE_CUSTOMERCONTACT_EMPTY = CODE_PREFIX+"083";
    public static final String CODE_CUSTOMER_NULL = CODE_PREFIX+"084";
    public static final String CODE_CERTIFICATE_NULL = CODE_PREFIX+"085";
    public static final String CODE_PRODUCT_NULL = CODE_PREFIX+"086";
    public static final String CODE_PRODUCTPREFIXID_NULL = CODE_PREFIX+"087";
    public static final String CODE_PRODUCTID_NULL = CODE_PREFIX+"088";
    public static final String CODE_SALESMAN_MOREONE = CODE_PREFIX+"089";
    public static final String CODE_SALESMAN_NOT_UPDATED = CODE_PREFIX+"090";
    public static final String CODE_PERSON_NULL = CODE_PREFIX+"091";
    public static final String CODE_ARGUMENT_PERSON_NULL = CODE_PREFIX+"092";
    public static final String CODE_ARGUMENT_ADDRESS_NULL = CODE_PREFIX+"093";
    public static final String CODE_ARGUMENT_PHONE_NULL = CODE_PREFIX+"094";
    public static final String CODE_INS_CERTIFICATE_NULL = CODE_PREFIX+"095";
    public static final String CODE_INSCERT_CONTRACT_NULL = CODE_PREFIX+"096";
    public static final String CODE_PAYMENTFORM_NULL = CODE_PREFIX+"097";
    public static final String CODE_PAYMENTFORMID_NULL = CODE_PREFIX+"098";
    public static final String CODE_CERTIFICATES_EMPTY = CODE_PREFIX+"099";
    public static final String CODE_UP_CERTIFICATE_NULL = CODE_PREFIX+"100";
    public static final String CODE_UP_PAYMENT_NULL = CODE_PREFIX+"101";
    public static final String CODE_UP_SALESMAN_NULL = CODE_PREFIX+"102";
    public static final String CODE_UP_IDENTITY_NULL = CODE_PREFIX+"103";
    public static final String CODE_NOT_SALE = CODE_PREFIX+"104";
    public static final String CODE_PROCESS_CARD = CODE_PREFIX+"105";
    public static final String CODE_PAYMENT_NULL = CODE_PREFIX+"106";
    public static final String CODE_SALESMAN_NULL = CODE_PREFIX+"107";
    public static final String CODE_OPERATOR_NULL = CODE_PREFIX+"108";
    public static final String CODE_OPERATORNAME_NULL = CODE_PREFIX+"109";
    public static final String CODE_PARTNER_NULL = CODE_PREFIX+"110";
    public static final String CODE_PARTNERID_NULL = CODE_PREFIX+"111";
    public static final String CODE_IDENTITY_NULL = CODE_PREFIX+"112";
    public static final String CODE_DOCUMENT_NULL = CODE_PREFIX+"113";
    public static final String CODE_SALE_NULL = CODE_PREFIX+"114";
    public static final String CODE_CAPITALSERIES_NULL = CODE_PREFIX+"115";
    public static final String CODE_CARD_NULL = CODE_PREFIX+"116";
    public static final String CODE_PAYMENTMETHOD_NULL = CODE_PREFIX+"117";
    public static final String CODE_PREMIUM = CODE_PREFIX+"118";
    public static final String CODE_COSTUMER_NULL = CODE_PREFIX+"119";
    public static final String CODE_COSTUMERADDRESS_NULL = CODE_PREFIX+"120";
    public static final String CODE_COSTUMERCONTACT_EMPTY = CODE_PREFIX+"121";
    public static final String CODE_1_CERTIFICATES_CPF = CODE_PREFIX+"122";
    public static final String CODE_3_CERTIFICATES_CPF = CODE_PREFIX+"123";
    public static final String CODE_SECURITKEY_EMPTY = CODE_PREFIX+"124";
    public static final String CODE_FUNCFAULTMESSAGE = CODE_PREFIX+"125";
    public static final String CODE_TECHFAULTMESSAGE = CODE_PREFIX+"126";
    public static final String CODE_PAYMENTINTEGRATION = CODE_PREFIX+"127";
    public static final String CODE_CC_CREDITCARD_EMPTY = CODE_PREFIX+"128";
    public static final String CODE_CC_SALESMAN_EMPTY = CODE_PREFIX+"129";
    public static final String CODE_CC_CERTIFICATE_EMPTY = CODE_PREFIX+"130";
    public static final String CODE_CC_CONTRACT_EMPTY = CODE_PREFIX+"131";
    public static final String CODE_CC_PAYMENT_EMPTY = CODE_PREFIX+"132";
    public static final String CODE_UP_CERTIFICATE_FAIL = CODE_PREFIX+"133";
    public static final String CODE_UP_CERT_STATUS_FAIL = CODE_PREFIX+"134";
    public static final String CODE_UP_CERT_STS_TMP_FAIL = CODE_PREFIX+"135";
    public static final String CODE_REGION_EMPTY = CODE_PREFIX+"136";
    
}

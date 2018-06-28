package br.com.rubyit.resseler.core.business.validators;

import static br.com.resseler.directsales.commons.persistence.dao.util.CustomerSalesConstants.PRODUCT_CARTAO_SUPERPROTEGIDO;
import static br.com.rubyit.resseler.core.business.validators.ValidCard.isMagazineValid;
import static br.com.rubyit.resseler.core.business.validators.ValidCard.isValidCardName;
import static br.com.rubyit.resseler.core.business.validators.Validations.isEmpty;
import static br.com.rubyit.resseler.core.business.validators.Validations.isValidNumber;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import br.com.rubyit.resseler.commons.kernel.dto.AddressDTO;
import br.com.rubyit.resseler.commons.kernel.dto.ContactDTO;
import br.com.rubyit.resseler.commons.kernel.dto.PersonDTO;
import br.com.rubyit.resseler.core.commons.dto.CardPaymentDTO;
import br.com.rubyit.resseler.core.commons.dto.PaymentMethod;
import br.com.rubyit.resseler.core.commons.dto.Salesman;
import br.com.rubyit.resseler.core.commons.dto.UserDTO;
import br.com.rubyit.resseler.core.commons.exceptions.ExceptionsConstants;
import br.com.rubyit.resseler.core.commons.exceptions.InvalidCardException;
import br.com.rubyit.resseler.core.commons.exceptions.ValidationException;
import br.com.rubyit.resseler.core.enums.ErrorType;

public final class ValidatorBusiness {

    private static final int ADDRESS_REFERENCE_LIMIT = 20;
    private static final int ADDRESS_STATE_LIMIT = 2;
    private static final int ADDRESS_NB_CITY_LIMIT = 30;
    private static final int ADDRESS_NUMBER_LIMIT = 14;
    private static final int ADDRESS_DETAIL_LIMIT = 79;
    private static final int CREDIT_CARD_PAYMENT_METHOD = 8;
    private static final int MAXIMUM_AGE = 65;
    private static final int MINIMUM_AGE = 18;
    private static final int PERSON_NAME_MAX_LENGTH = 80;
    private static final int LIMIT_FOUR_NUMBER = 9999;
    private static final int MAXIMUM_AGE_SUPER_PROTEGIDO = 70;

    private static final Logger LOG = LogManager.getLogger(ValidatorBusiness.class);

    private ValidatorBusiness() {

    }

    public static void validatePersonDocuments(final PersonDTO person) {

        // ------------------------------
        // Validação dos dados do cliente
        // ------------------------------
        if (isEmpty(person)) { throw new ValidationException(
                "É necessário informar os dados do cliente.",
                ExceptionsConstants.CODE_PERSON_EMPTY, ErrorType.VALIDATION); }
        if (isEmpty(person.getIdentity())) { throw new ValidationException(
                "O campo CPF é obrigatório.",
                ExceptionsConstants.CODE_PERSON_CPF_EMPTY,
                ErrorType.VALIDATION); }
        final String cpf = person.getIdentity().getDocumentValue();
        if (!ValidCNP.isValidCPFLength(cpf)) { throw new ValidationException(
                "O campo CPF deve conter 11 caracteres.",
                ExceptionsConstants.CODE_PERSON_CPF_LENGTH,
                ErrorType.VALIDATION); }
        if (!ValidCNP.isValidCPF(cpf)) { throw new ValidationException(
                "O campo CPF não é válido.",
                ExceptionsConstants.CODE_PERSON_CPF_INVALID,
                ErrorType.VALIDATION); }

    }

    public static void validatePersonFullName(final PersonDTO person) {
        final String fullName = person.getFullName();
        if (isEmpty(fullName)) {
            throw new ValidationException(
                    "O campo Nome Completo é obrigatório.",
                    ExceptionsConstants.CODE_PERSON_NAME_EMPTY,
                    ErrorType.VALIDATION);
        } else if (fullName.length() > 80) { throw new ValidationException(
                "O campo Nome Completo não pode conter mais de "
                        + PERSON_NAME_MAX_LENGTH + " de caracteres.",
                ExceptionsConstants.CODE_PERSON_NAME_LENGTH,
                ErrorType.VALIDATION); }
        final String regx = "^[\\p{L} .'-]+$";
        final Pattern pattern = Pattern.compile(regx, Pattern.CASE_INSENSITIVE);
        final Matcher matcher = pattern.matcher(fullName);
        if (!matcher.find()) { throw new ValidationException(
                "O campo Nome Completo não é válido! fullName=[" + fullName
                        + "]",
                ExceptionsConstants.CODE_PERSON_NAME_INVALID,
                ErrorType.VALIDATION); }
    }

    public static void validateContacts(final PersonDTO person) {

        if (isEmpty(person.retrieveContacts().isEmpty())) {
        	throw new ValidationException(
                        "É necessário informar ao menos um contato.",
                        ExceptionsConstants.CODE_CONTACTS_EMPTY,
                        ErrorType.VALIDATION);

        }

        for (final ContactDTO contact : person.retrieveContacts()) {
            validadeAddress(contact.getAddress());

            // Validação do telefone
            if (isEmpty(contact.getPhone())) { throw new ValidationException(
                    "É necessário informar um Telefone.",
                    ExceptionsConstants.CODE_CONTACT_PHONE_EMPTY,
                    ErrorType.VALIDATION); }
            if (isEmpty(contact.getPhone()
                    .getPhoneNumber())) { throw new ValidationException(
                            "O campo Telefone é obrigatório.",
                            ExceptionsConstants.CODE_CONTACT_PHONENUM_EMPTY,
                            ErrorType.VALIDATION); }

            final String phone = contact.getPhone().getPhoneNumber()
                    .replaceAll("-", "");

            if (!isValidNumber(phone)) { throw new ValidationException(
                    "O campo Telefone não é numérico.",
                    ExceptionsConstants.CODE_CONTACT_PHONE_NONUMERIC,
                    ErrorType.VALIDATION); }
            if ((phone.length() != 10)
                    && (phone.length() != 11)) { throw new ValidationException(
                            "O tamanho do campo Telefone está incorreto.",
                            ExceptionsConstants.CODE_CONTACT_PHONE_LENGTH,
                            ErrorType.VALIDATION); }
        }

    }

    public static void validatePersonMoreData(final PersonDTO person, final Long productID) {

        if (isEmpty(person.getGender())) { throw new ValidationException(
                "O campo Sexo é obrigatório.",
                ExceptionsConstants.CODE_GENDER_EMPTY, ErrorType.VALIDATION); }

        final Calendar birthDate = person.getBirthDate();
        if (isEmpty(birthDate)) { throw new ValidationException(
                "O campo Data Nascimento é obrigatório.",
                ExceptionsConstants.CODE_BIRTHDATE_EMPTY,
                ErrorType.VALIDATION); }

        if (isEmpty(person.getMaritalStatus())) { throw new ValidationException(
                "O campo Estado Civil é obrigatório.",
                ExceptionsConstants.CODE_MARITALSTATUS_EMPTY,
                ErrorType.VALIDATION); }

    }

    private static void validadeAddress(final AddressDTO address) {

        if (isEmpty(address)) { throw new ValidationException(
                "É necessário informar um endereço.",
                ExceptionsConstants.CODE_ADDRESS_EMPTY, ErrorType.VALIDATION); }
        if (isEmpty(address.getAddressDetail()) || (address.getAddressDetail()
                .length() > ADDRESS_DETAIL_LIMIT)) { throw new ValidationException(
                        "O campo Endereço é obrigatório e não pode conter mais que "
                                + ADDRESS_DETAIL_LIMIT + " caracteres.",
                        ExceptionsConstants.CODE_ADDRESS_DETAIL_INVALID,
                        ErrorType.VALIDATION); }
        if (isEmpty(
                address.getAddressNumber())) { throw new ValidationException(
                        "O campo Número do Endereço é obrigatório.",
                        ExceptionsConstants.CODE_ADDRESS_NUMBER_EMPTY,
                        ErrorType.VALIDATION); }
        if (isEmpty(address.getAddressNumber()) || (address.getAddressNumber()
                .length() > ADDRESS_NUMBER_LIMIT)) { throw new ValidationException(
                        "O campo Número do Endereço é obrigatório e não pode conter mais que "
                                + ADDRESS_NUMBER_LIMIT + " caracteres.",
                        ExceptionsConstants.CODE_ADDRESS_NUMBER_LENGTH,
                        ErrorType.VALIDATION); }
        if (isEmpty(address.getNeighborhood()) || (address.getNeighborhood()
                .length() > ADDRESS_NB_CITY_LIMIT)) { throw new ValidationException(
                        "O campo Bairro é obrigatório e não pode conter mais que "
                                + ADDRESS_NB_CITY_LIMIT + " caracteres.",
                        ExceptionsConstants.CODE_ADDRESS_NEIGH_INVALID,
                        ErrorType.VALIDATION); }
        if (isEmpty(address.getCity()) || (address.getCity()
                .length() > ADDRESS_NB_CITY_LIMIT)) { throw new ValidationException(
                        "O campo Cidade é obrigatório e não pode conter mais que "
                                + ADDRESS_NB_CITY_LIMIT + " caracteres.",
                        ExceptionsConstants.CODE_ADDRESS_CITY_INVALID,
                        ErrorType.VALIDATION); }

        final String state = address.getState();
        if (isEmpty(state) || (state.length() > ADDRESS_STATE_LIMIT)) {
            final String msg = "O campo Estado é obrigatório e não pode conter mais que "
                    + ADDRESS_STATE_LIMIT + " caracteres.";
            LOG.error(msg);
            throw new ValidationException(msg,
                    ExceptionsConstants.CODE_ADDRESS_STATE_INVALID,
                    ErrorType.VALIDATION);
        }

        if (address.getAddressReference() != null && address
                .getAddressReference()
                .length() > ADDRESS_REFERENCE_LIMIT) { throw new ValidationException(
                        "O campo Complemento não pode conter mais que "
                                + ADDRESS_REFERENCE_LIMIT + " caracteres.",
                        ExceptionsConstants.CODE_ADDRESS_REFERENCE_INVALID,
                        ErrorType.VALIDATION); }

        validateZipcodeCEP(address);

    }

    private static void validateZipcodeCEP(final AddressDTO address) {
        final String cepOrigin = address.getAddressPostalCode();
        if (isEmpty(cepOrigin)) {
            final String msg = "O campo CEP é obrigatório.";
            LOG.error(msg);
            throw new ValidationException(msg,
                    ExceptionsConstants.CODE_CODEPOSTAL_EMPTY,
                    ErrorType.VALIDATION);
        }
        final String cep = cepOrigin.replaceAll("-", "");
        if (!isValidNumber(cep)) {
            final String msg = "O campo CEP não é numérico.";
            LOG.error(msg);
            throw new ValidationException(msg,
                    ExceptionsConstants.CODE_CODEPOSTAL_INVALID,
                    ErrorType.VALIDATION);
        }
        if (cep.length() != 8) {
            final String msg = "O tamanho do campo CEP está incorreto.";
            LOG.error(msg);
            throw new ValidationException(msg,
                    ExceptionsConstants.CODE_CODEPOSTAL_LENGTH,
                    ErrorType.VALIDATION);
        }

        validadeCEPValue(cepOrigin, cep);
    }

    private static void validadeCEPValue(final String cepOrigin,
            final String cep) {
        /*
         * Area Code Range
         * (source:
         *              http://networking.mydesigntool.com/viewtopic.php?tid=419&id=31
         *              http://weber.eti.br/blog/entry/utilidade-faixas-de-cep-por-estado.html
         * )
         *
            Sao Paulo                   01000-000 to 09999-999
            State of Sao Paulo          11000-000 to 19999-999
            State of Rio de Janeiro     20000-000 to 28999-999
            State of Espirito Santo     29000-000 to 29999-999
            State of Minas Gerais       30000-000 to 39999-999
            State of Bahia              40000-000 to 48999-999
            State of Sergipe            49000-000 to 49999-999
            State of Pernambuco         50000-000 to 56999-999
            State of Alagoas            57000-000 to 57999-999
            State of Paraiba            58000-000 to 58999-999
            State of Rio Grande do Norte59000-000 to 59999-999
            State of Ceara              60000-000 to 63999-999
            State of Piaui              64000-000 to 64999-999
            State of Maranhao           65000-000 to 65999-999
            State of Para               66000-000 to 68899-999
            State of Amapa              68900-000 to 68999-999
            State of Amazonas I         69000-000 to 69299-999
            State of Roraima            69300-000 to 69399-999
            State of Amazonas II        69400-000 to 69899-999
            State of Acre               69900-000 to 69999-999
            Federal District I          70000-000 to 72799-999
            State of Goias I            72800-000 to 72999-999
            Federal District II         73000-000 to 73699-999
            State of Goias II           73700-000 to 76799-999
            State of Rondonia           76800-000 to 76999-999
            State of Tocantins          77000-000 to 77999-999
            State of Mato Grosso        78000-000 to 78899-999
            State of Mato Grosso do Sul 79000-000 to 79999-999
            State of Parana             80000-000 to 87999-999
            State of Santa Catarina     88000-000 to 89999-999
            State of Rio Grande do Sul  90000-000 to 99990-970
         */
        final String cepP1 = cep.substring(0, 5);
        final String msg = "O formato do campo CEP está incorreto. CEP=["
                + cepOrigin + ']';
        final Long cepLP1 = Long.parseLong(cepP1);
        if (cepLP1 < 1000) {
            LOG.error(msg);
            throw new ValidationException(msg,
                    ExceptionsConstants.CODE_CODEPOSTAL_FORMAT1,
                    ErrorType.VALIDATION);
        }
        final String cepP2 = cep.substring(5, 8);
        final Long cepLP2 = Long.parseLong(cepP2);
        if ((cepLP1 >= 99990) && (cepLP2 > 970)) {
            LOG.error(msg);
            throw new ValidationException(msg,
                    ExceptionsConstants.CODE_CODEPOSTAL_FORMAT2,
                    ErrorType.VALIDATION);
        }
    }

    public static void validatePaymentMethod(final PaymentMethod paymentMethod,
            final Long productID) {
        if (isEmpty(paymentMethod)) { throw new ValidationException(
                "É necessário informar uma forma de pagamento.",
                ExceptionsConstants.CODE_PAYMENTMETHOD_EMPTY,
                ErrorType.VALIDATION); }
        if ((paymentMethod.getPaymentType() == null) || (paymentMethod
                .getPaymentType() != CREDIT_CARD_PAYMENT_METHOD)) { throw new ValidationException(
                        "Código de forma de pagamento inválida! forma=["
                                + paymentMethod.getPaymentType() + "]",
                        ExceptionsConstants.CODE_FORM_PAYMENT_INVALID,
                        ErrorType.VALIDATION); }

        final CardPaymentDTO card;
        card = paymentMethod.getCardPayment();

        creditCardPaymentValidation(card, productID);
    }

    @edu.umd.cs.findbugs.annotations.SuppressFBWarnings(value = "squid:MethodCyclomaticComplexity", 
            justification = "A complexiddade deste metodo se deve ao processo de validacao envolvido. As "
            + "possiveis refatoracoes ja foram implementadadas e portanto o codigo sera mantido.")
    public static void creditCardPaymentValidation(
            final CardPaymentDTO creditCard, final Long productID) {
        validadeCreditCard(creditCard);

        creditCardNumberValidation(creditCard, productID);

        validadeCreditCardFields(creditCard);
    }

    public static void creditCardPaymentEncriptedValidation(
            final CardPaymentDTO creditCard) {
        validadeCreditCard(creditCard);

        creditCardNumberEncriptedValidation(creditCard);

        validadeCreditCardFields(creditCard);
    }

    private static void validadeCreditCardFields(
            final CardPaymentDTO creditCard) {
        isValidCardName(creditCard.getCardDisplayName());

        validateCreditCardValidity(creditCard);

        final String securityCode = creditCard.getCardSecurityCode();
        if (isEmpty(securityCode)) {
            final String msg = "O campo Código de Segurança do Cartão de Crédito é obrigatório.";
            LOG.error(msg);
            throw new InvalidCardException(msg,
                    ExceptionsConstants.CODE_CARD_SECCODE_EMPTY,
                    ErrorType.VALIDATION);
        }

        final BigDecimal cardValue = creditCard.getCardValue();
        if (isEmpty(cardValue)) {
            final String msg = "O campo valor do Cartão de Crédito é obrigatório.";
            LOG.error(msg);
            throw new InvalidCardException(msg,
                    ExceptionsConstants.CODE_CARD_VALUE_EMPTY,
                    ErrorType.VALIDATION);
        }
        if (ValidCard.isInvalidCardValue(cardValue)) {
            final String msg = "Valor do Cartão de Crédito não é válido!\tcardValue=["
                    + cardValue + "]";
            LOG.error(msg);
            throw new InvalidCardException(msg,
                    ExceptionsConstants.CODE_CARD_VALUE_INVALID,
                    ErrorType.VALIDATION);
        }
    }

    private static void validateCreditCardValidity(
            final CardPaymentDTO creditCard) {
        final Calendar cardValidity = creditCard.getCardValidity();
        if (isEmpty(cardValidity)) {
            final String msg = "O campo Validade do Cartão de Crédito é obrigatório.";
            LOG.error(msg);
            throw new InvalidCardException(msg,
                    ExceptionsConstants.CODE_DATE_EMPTY, ErrorType.VALIDATION);
        }
        final Calendar today = Calendar.getInstance();
        if (cardValidity.before(today)) {
            final String msg = "Cartão de Crédito com Data de Validade Vencida!\tcardValidity=["
                    + cardValidity + "]";
            LOG.error(msg);
            throw new InvalidCardException(msg,
                    ExceptionsConstants.CODE_DATE_INVALID,
                    ErrorType.VALIDATION);
        }
    }

    private static void validadeCreditCard(final CardPaymentDTO creditCard) {
        if (isEmpty(creditCard)) {
            final String msg = "É necessário informar os dados do cartão de crédito.";
            LOG.error(msg);
            throw new InvalidCardException(msg,
                    ExceptionsConstants.CODE_CREDITCARD_EMPTY,
                    ErrorType.VALIDATION);
        }
    }

    public static void creditCardNumberValidation(
            final CardPaymentDTO creditCard, final Long productID) {
        final String cardNumber = creditCard.getCardNumber();

        if (isEmpty(cardNumber)) {
            final String msg = "O campo Número do Cartão de Crédito é obrigatório.";
            LOG.error(msg);
            throw new InvalidCardException(msg,
                    ExceptionsConstants.CODE_CARD_NUMBER_EMPTY,
                    ErrorType.VALIDATION);
        }
        ValidCard.isValidCardNumber(cardNumber);

        if (PRODUCT_CARTAO_SUPERPROTEGIDO.equals(productID)) {
            isMagazineValid(cardNumber);
        }
    }

    public static void creditCardNumberEncriptedValidation(
            final CardPaymentDTO creditCard) {
        final String cardNumber = creditCard.getCardNumber();

        if (isEmpty(cardNumber)) {
            final String msg = "O campo Número do Cartão de Crédito é obrigatório.";
            LOG.error(msg);
            throw new InvalidCardException(msg,
                    ExceptionsConstants.CODE_CARDNUMBER_EMPTY,
                    ErrorType.VALIDATION);
        }
    }

    /**
    * Método responsável por validar se as informações obrigatórias estão
    * preenchidas
    *
    * @param salesmanParamValidate
    */
    public static void validateSaveSalesman(
            final Salesman salesmanParamValidate) {

        if (isEmpty(salesmanParamValidate)) { throw new ValidationException(
                "É necessário informar dados do vendedor.",
                ExceptionsConstants.CODE_SAVESALESMAN_EMPTY,
                ErrorType.VALIDATION); }

        if (isEmpty(salesmanParamValidate
                .getBirthDate())) { throw new ValidationException(
                        "O campo data de nascimento é obrigatório.",
                        ExceptionsConstants.CODE_SAVESALESMAN_BIRTH_EMPTY,
                        ErrorType.VALIDATION); }

        final PersonDTO person = new PersonDTO();
        person.setFullName(salesmanParamValidate.getFullName());
        person.setIdentity(salesmanParamValidate.getIdentity());

        ValidatorBusiness.validatePersonFullName(person);
        ValidatorBusiness.validatePersonDocuments(person);

        validatePerson(salesmanParamValidate);

        //ValidatorBusiness.validateUser(salesmanParamValidate.getUser());
        ValidatorBusiness.validateLengthBch(salesmanParamValidate);
    }

    /**
     * Validar campos obrigatórios da pessoa
     *
     * @param salesmanParamValidate
     */
    public static void validatePerson(final Salesman salesmanParamValidate) {
        if (isEmpty(salesmanParamValidate
                .getPartner())) { throw new ValidationException(
                        "O campo partner é obrigatório.",
                        ExceptionsConstants.CODE_PARTNER_EMPTY,
                        ErrorType.VALIDATION); }

        if (isEmpty(salesmanParamValidate.getPartner()
                .getID())) { throw new ValidationException(
                        "O campo partner é obrigatório.",
                        ExceptionsConstants.CODE_PARTNERID_EMPTY,
                        ErrorType.VALIDATION); }

        if (isEmpty(salesmanParamValidate
                .getRole())) { throw new ValidationException(
                        "O campo função é obrigatório.",
                        ExceptionsConstants.CODE_ROLE_EMPTY,
                        ErrorType.VALIDATION); }

        if (isEmpty(salesmanParamValidate
                .getSalesmanCod())) { throw new ValidationException(
                        "O campo código do vendedor é obrigatório.",
                        ExceptionsConstants.CODE_SALESMAN_CODE_EMPTY,
                        ErrorType.VALIDATION); }
    }

    /**
     * Método responsável por validar se as informações obrigatórias estão
     * preenchidas
     *
     * @param salesmanParam
     */
    public static void validateUpdateSalesmanBch(final Salesman salesmanParam) {

        if (isEmpty(salesmanParam)) { throw new ValidationException(
                "É necessário informar dados do vendedor.",
                ExceptionsConstants.CODE_UP_SALESMAN_EMPTY,
                ErrorType.VALIDATION); }

        final PersonDTO person = new PersonDTO();
        person.setIdentity(salesmanParam.getIdentity());
        ValidatorBusiness.validatePersonDocuments(person);

        if (isEmpty(salesmanParam
                .getSalesmanBch())) { throw new ValidationException(
                        "O campo código da filial é obrigatório.",
                        ExceptionsConstants.CODE_UP_BHC_EMPTY,
                        ErrorType.VALIDATION); }

        if (isEmpty(salesmanParam
                .getSalesmanAtuBch())) { throw new ValidationException(
                        "O campo aut da filial é obrigatório.",
                        ExceptionsConstants.CODE_UP_AUT_EMPTY,
                        ErrorType.VALIDATION); }

        if (isEmpty(salesmanParam.getBchId())) { throw new ValidationException(
                "O campo id da filial é obrigatório.",
                ExceptionsConstants.CODE_UP_BHC_ID_EMPTY,
                ErrorType.VALIDATION); }

        ValidatorBusiness.validateLengthBch(salesmanParam);

    }

    /**
     * Método responsável por validar se as informações obrigatórias estão
     * preenchidas
     *
     * @param salesman
     */
    public static void validateFindSalesmanBch(final Salesman salesman) {

        if (isEmpty(salesman)) { throw new ValidationException(
                "É necessário informar dados para pesquisa.",
                ExceptionsConstants.CODE_FIND_SALESMAN_EMPTY,
                ErrorType.VALIDATION); }

        if (isEmpty(salesman.getSalesmanBch())) { throw new ValidationException(
                "O campo código da filial é obrigatório.",
                ExceptionsConstants.CODE_FIND_BHC_EMPTY,
                ErrorType.VALIDATION); }
        
        if (isEmpty(salesman.getRegionName())) { throw new ValidationException(
                "O campo regional é obrigatório.",
                ExceptionsConstants.CODE_REGION_EMPTY,
                ErrorType.VALIDATION); }
    }

    /**
     * Validação de campos obrigatórios do usuario
     *
     * @param userDTO
     */
    public static void validateUser(final UserDTO userDTO) {
        if (isEmpty(userDTO)) { throw new ValidationException(
                "O campo usuário é obrigatório.",
                ExceptionsConstants.CODE_USER_EMPTY, ErrorType.VALIDATION); }

        if (userDTO.getProfileId() == null) { throw new ValidationException(
                "O campo profile id é obrigatório. ",
                ExceptionsConstants.CODE_PROFILE_EMPTY, ErrorType.VALIDATION); }

        validateUserLogin(userDTO);

        if (isEmpty(userDTO.getUserWord())) { throw new ValidationException(
                "O campo word do usuário é obrigatório.",
                ExceptionsConstants.CODE_USERWORD_EMPTY,
                ErrorType.VALIDATION); }

        if (isEmpty(userDTO.getUserGrp())) { throw new ValidationException(
                "O campo grupo do usuário é obrigatório.",
                ExceptionsConstants.CODE_USER_GRP_EMPTY,
                ErrorType.VALIDATION); }

    }

    /**
     * Validar os campos de login do usuário
     *
     * @param userDTO
     */
    private static void validateUserLogin(final UserDTO userDTO) {

        if (isEmpty(userDTO.getUserCod())) { throw new ValidationException(
                "O campo código do usuário é obrigatório.",
                ExceptionsConstants.CODE_USERCODE_EMPTY,
                ErrorType.VALIDATION); }

        if (isEmpty(userDTO.getUserPwd())) { throw new ValidationException(
                "O campo senha do usuário é obrigatório.",
                ExceptionsConstants.CODE_USERPWD_EMPTY, ErrorType.VALIDATION); }
    }

    /**
     * Validação do tamanho do código do ramo e da filial do vendedor
     *
     * @param salesmanParam
     */
    public static void validateLengthBch(final Salesman salesmanParam) {

        if ((salesmanParam.getBchId() != null) && (salesmanParam
                .getBchId() > LIMIT_FOUR_NUMBER)) { throw new ValidationException(
                        "O campo ramo ultrapassa o limite.",
                        ExceptionsConstants.CODE_BHC_LENGTH,
                        ErrorType.VALIDATION); }
    }

    public static void validatePersonAge(final PersonDTO person, final Integer maximumAge) {
        final Calendar birthDate = person.getBirthDate();
        final Calendar today = Calendar.getInstance();
        final Integer yearToday = today.get(Calendar.YEAR);
        final Integer age = yearToday - (birthDate.get(Calendar.YEAR));

        if ((age < MINIMUM_AGE)
                || (age > maximumAge)) { throw new ValidationException(
                        "Idade inválida! Idade=[" + age + "]",
                        ExceptionsConstants.CODE_AGE_INVALID,
                        ErrorType.VALIDATION); }
        
    }
}

package br.com.resseler.directsales.commons.persistence.dao.util;

import java.math.BigInteger;

public final class CustomerSalesConstants {

    private CustomerSalesConstants() {
    }

    public static final Integer CODE_TIMEOUT = 99;
    public static final String NATIONALITY_DEFAULT = "BRASILEIRO";
    public static final Integer PRODUCT_CARTAO_SUPERPROTEGIDO = 25;
    public static final Integer PRODUCT_PROTECAO_PREMIADA = 26;
    public static final Integer MARITALSTATUSMARRIED = 2;
    public static final Integer MARITALSTATUSSINGLE = 1;
    public static final String INSURED_OBJECT_COD = "INSURED_OBJECT_COD";
    public static final String INSURED_OBJECT_ID = "INSURED_OBJECT_ID";
    public static final String TABLE_INSURED_OBJECT = "INSURED_OBJECT";
    public static final String INST_ID = "INST_ID";
    public static final String INST_NUM_DOC = "INST_NUM_DOC";
    public static final String INST_DUE_DT = "INST_DUE_DT";
    public static final String INST_PRM_VL = "INST_PRM_VL";
    public static final String INST_ORD_NBR = "INST_ORD_NBR";
    public static final String INST_INS_DTM = "INST_INS_DTM";
    public static final String PAYMENTMETHOD_CREDITCARD_TEF_PTBR = "Cartao de Credito TEF";
    public static final String BCH_COD = "BCH_COD";
    public static final String BCH_DSC = "BCH_DSC";
    public static final String TABLE_SALESMAN = "SALESMAN";
    public static final String PRD_DSC = "PRD_DSC";
    public static final String PRD_NME = "PRD_NME";
    public static final String PRD_COD = "PRD_COD";
    public static final String PRD_UPD_USER_ID = "PRD_UPD_USER_ID";
    public static final String PRD_UPD_DTM = "PRD_UPD_DTM";
    public static final String PRD_INS_USER_ID = "PRD_INS_USER_ID";
    public static final String SALESMAN_ATU_BCH = "SALESMAN_ATU_BCH";
    public static final String SALESMAN_BCH = "SALESMAN_BCH";
    public static final String SALESMAN_COD = "SALESMAN_COD";
    public static final String BROKER_ID = "BROKER_ID";
    public static final String ESTADO_CIVIL_SOLTEIRO = "SOLTEIRO";
    public static final String ESTADO_CIVIL_CASADO = "CASADO";
    public static final String FLAG_SEXO_FEMININO = "F";
    public static final String FLAG_SEXO_MASCULINO = "M";
    public static final String MASTERPOLICE_PADRAO = "95169";
    public static final String MASTERPOLICE_PROTECAOPREMIADA = "95298";
    public static final String PARTNER_PADRAO = "142";
    public static final String BANDEIRA_CARTAO_PADRAO = "luiza";
    public static final String BANDEIRA_CARTAO_VISA = "visa";
    public static final String BANDEIRA_CARTAO_MASTER = "mastercard";
    public static final String BANDEIRA_CARTAO_AMEX = "amex";
    public static final String BANDEIRA_CARTAO_AURA = "aura";
    public static final String BANDEIRA_CARTAO_HIPER = "hipercard";
    public static final String BANDEIRA_CARTAO_DINERS = "diners";
    public static final String BANDEIRA_CARTAO_ELO = "elo";
    public static final BigInteger PARCELAS_PADRAO = BigInteger.valueOf(60L);
    public static final String ERRO_TEF = "Erro ao Realizar o processo de  TEF -->";
    public static final String TABLE_CAPITAL_SERIES = "CAPITAL_SERIES";
    public static final String CAP_PRD_ID = "CAP_PRD_ID";
    public static final String CAP_FILE_ID = "cap_file_id";
    public static final String CAPITAL_SERIES_DEL_FG = "capital_series_del_fg";
    public static final String CAPITAL_SERIES_UPD_USER_ID = "capital_series_upd_user_id";
    public static final String CAPITAL_SERIES_UPD_DTM = "capital_series_upd_dtm";
    public static final String CAPITAL_SERIES_INS_USER_ID = "capital_series_ins_user_id";
    public static final String CAPITAL_SERIES_INS_DTM = "capital_series_ins_dtm";
    public static final String CAPITAL_SERIES_POLCRT_EFF_DTM = "capital_series_polcrt_eff_dtm";
    public static final String POLCRT_ENDORS_NO = "polcrt_endors_no";
    public static final String POLICY_NO = "policy_no";
    public static final String CAPITAL_SERIES_VLD_DTM = "capital_series_vld_dtm";
    public static final String CAPITAL_SERIES_EFF_DTM = "capital_series_eff_dtm";
    public static final String CAPITAL_SERIES_VLD = "capital_series_vld";
    public static final String CAPITAL_SERIES_NBR = "capital_series_nbr";
    public static final String CAPITAL_SERIES_LTT = "capital_series_ltt";
    public static final String OWNER_PARTNER_ID = "owner_partner_id";
    public static final String TABLE_CERTIFICATE = "CERTIFICATE";
    public static final String INSURED_ID = "INSURED_ID";
    public static final String INSURED_PERSON_ID = "INSURED_PERSON_ID";
    public static final String SALESMAN_ID = "SALESMAN_ID";
    public static final String SALESMAN__BCH_ID = "BCH_ID";
    public static final String SALESMAN_PERSON_ID = "SALESMAN_PERSON_ID";
    public static final String PRD_ID = "PRD_ID";
    public static final String BENEFICIARY_ID = "BENEFICIARY_ID";
    public static final String BENEFICIARY_NME = "BENEFICIARY_NME";
    public static final String BENEFICIARY_DT_BTH = "BENEFICIARY_DT_BTH";
    public static final String BENEFICIARY_PCT = "BENEFICIARY_PCT";
    public static final String CERTIFICATE_ID = "CERTIFICATE_ID";
    public static final String CERTIFICATE_NO = "CERTIFICATE_NO";
    public static final String CERTIFICATE_PRM_VL = "CERTIFICATE_PRM_VL";
    public static final String CERTIFICATE_EFF_DT = "CERTIFICATE_EFF_DT";
    public static final String CERTIFICATE_EXP_DT = "CERTIFICATE_EXP_DT";
    public static final String CERTIFICATE_STS = "CERTIFICATE_STS";
    public static final String CERTIFICATE_CMS_VL = "CERTIFICATE_CMS_VL";
    public static final String CERTIFICATE_IOF_VL = "CERTIFICATE_IOF_VL";
    public static final String CERTIFICATE_PRM_TOT_VL = "CERTIFICATE_PRM_TOT_VL";
    public static final String CERTIFICATE_SLM_PRT_VL = "CERTIFICATE_SLM_PRT_VL";
    public static final String CERTIFICATE_EMS_DT = "CERTIFICATE_EMS_DT";
    public static final String CERTIFICATE_CNL_DT = "CERTIFICATE_CNL_DT";
    public static final String CERTIFICATE_TRF_DT = "CERTIFICATE_TRF_DT";
    public static final String CERTIFICATE_TRF_ML_DT = "CERTIFICATE_TRF_ML_DT";
    public static final String CERTIFICATE_TRF_STS = "CERTIFICATE_TRF_STS";
    public static final String CERTIFICATE_CAPITAL_SERIES_LTT = "CERTIFICATE_CAPITAL_SERIES_LTT";
    public static final String CERTIFICATE_CAPITAL_SERIES_NBR = "CERTIFICATE_CAPITAL_SERIES_NBR";
    public static final String CERTIFICATE_CAPITAL_SERIES_VLD = "CERTIFICATE_CAPITAL_SERIES_VLD";
    public static final String CERTIFICATE_BCH = "CERTIFICATE_BCH";
    public static final String CERTIFICATE_TMP_STS = "CERTIFICATE_TMP_STS";
    public static final String BCH_ID = "BCH_ID";
    public static final String REG_ID = "REG_ID";
    public static final String REG_DSC = "REG_DSC";
    public static final String CERTIFICATE_BCH_TMP = "CERTIFICATE_BCH_TMP";
    public static final String PRD_FRM_ID = "PRD_FRM_ID";
    public static final String CRD_CARD_ID = "CRD_CARD_ID";
    public static final String CERTIFICATE_CAPITAL_SERIES_VL = "CERTIFICATE_CAPITAL_SERIES_VL";
    public static final String CERTIFICATE_CTR_NBR = "CERTIFICATE_CTR_NBR";
    public static final String CERTIFICATE_EMAIL = "CERTIFICATE_EMAIL";
    public static final String ACCOUNT_ID = "ACCOUNT_ID";
    public static final String CERTIFICATE_INI_VIG_DT = "CERTIFICATE_INI_VIG_DT";
    public static final String CERTIFICATE_TLMKT_CAMPAIGN = "CERTIFICATE_TLMKT_CAMPAIGN";
    public static final String PRDCONF_ID = "PRDCONF_ID";
    public static final String PERSISTENCE_UNIT_NAME_DIRECTSALES = "directSales";
    public static final String PERSISTENCE_UNIT_NAME_CONTRACT = "contract";
    public static final String SP_GET_NEXT_CONTRACT = "usp_GetNextCertificateNumber";
    public static final String NEXT_CERTIFICATE = "certif_current_nbr";
    public static final String RESULTSET_NEXT_CERTIFICATE = "#result-set-1";
    public static final int ACTIVE_CERTIFICATE_STATUS = 1;
    public static final String SELECT_CERTIFICATE_BASE = "SELECT CERT.CERTIFICATE_ID AS CERTIFICATE_ID, "
            + "CERT.INSURED_ID AS INSURED_ID, " + "INN.PERSON_ID AS INSURED_PERSON_ID, "
            + "CERT.SALESMAN_ID AS SALESMAN_ID, " + "SALES.PERSON_ID AS SALESMAN_PERSON_ID, "
            + "CERT.PRD_ID AS PRD_ID, " + "CERT.CERTIFICATE_NO AS CERTIFICATE_NO, "
            + "CERT.CERTIFICATE_PRM_VL AS CERTIFICATE_PRM_VL, " + "CERT.CERTIFICATE_EFF_DT AS CERTIFICATE_EFF_DT, "
            + "CERT.CERTIFICATE_EXP_DT AS CERTIFICATE_EXP_DT, " + "PROD.PRD_DSC FROM CERTIFICATE CERT, "
            + "INSURED INN, " + "PRD_PRODUCT PROD, SALESMAN SALES  WHERE 1 = 1 AND "
            + "CERT.INSURED_ID = INN.INSURED_ID AND "
            + "CERT.PRD_ID = PROD.PRD_ID AND CERT.SALESMAN_ID = SALES.SALESMAN_ID";

    public static final String SELECT_CERTIFICATE_BY_PERSON_ID = SELECT_CERTIFICATE_BASE + " AND INN.PERSON_ID = ?";
    public static final String SELECT_CERTIFICATE_BY_SALESMAN_ID = SELECT_CERTIFICATE_BASE
            + " AND CERT.SALESMAN_ID = ?";

    public static final String SELECT_ACTIVE_CERTIFICATE = SELECT_CERTIFICATE_BASE
            + " AND CERT.CERTIFICATE_STS = 1 AND INN.PERSON_ID = ? AND CERT.PRD_ID = ?";

    public static final String SELECT_ACTIVE_CERTIFICATE_BY_PERSON_CPF = "SELECT INSURED_ID ,"
            + "CERTIFICATE_ID ,CERTIFICATE_NO , INSURED_PERSON_ID ,SALESMAN_ID, SALESMAN_PERSON_ID, PRD_ID ,CERTIFICATE_PRM_VL ,CERTIFICATE_EFF_DT "
            + ",CERTIFICATE_EXP_DT, PRD_DSC FROM ( SELECT MAX(C.INSURED_ID) AS INSURED_ID ,MAX(C.CERTIFICATE_ID) AS CERTIFICATE_ID "
            + ",MAX(C.CERTIFICATE_NO) AS CERTIFICATE_NO ,MAX(I.PERSON_ID) AS INSURED_PERSON_ID ,MAX(C.SALESMAN_ID) AS SALESMAN_ID, MAX(S.PERSON_ID) AS SALESMAN_PERSON_ID "
            + ",MAX(C.PRD_ID) AS PRD_ID, MAX(C.CERTIFICATE_PRM_VL) AS CERTIFICATE_PRM_VL ,MAX(C.CERTIFICATE_EFF_DT) AS "
            + "CERTIFICATE_EFF_DT ,MAX(C.CERTIFICATE_EXP_DT) AS CERTIFICATE_EXP_DT, MAX(PR.PRD_DSC) AS PRD_DSC FROM CERTIFICATE C  (NOLOCK) INNER "
            + "JOIN INSURED I (NOLOCK) ON C.INSURED_ID = I.INSURED_ID INNER JOIN PERSON P (NOLOCK) ON I.PERSON_ID = "
            + "P.PERSON_ID INNER JOIN SALESMAN S ON S.SALESMAN_ID = C.SALESMAN_ID INNER JOIN PRD_PRODUCT PR ON C.PRD_ID = PR.PRD_ID WHERE P.PERSON_CPF = ?  AND C.PRD_ID = ?) AS TEMP";
    public static final String UPDATE_CERTIFICATE_STATUS = "UPDATE " + TABLE_CERTIFICATE + " SET " + CERTIFICATE_STS
            + " = ?" + " WHERE " + CERTIFICATE_ID + " = ?";
    public static final String TABLE_PERSON = "PERSON";
    public static final String PERSON_TYPE = "PERSON_TYPE";
    public static final String PERSON_ROL = "PERSON_ROL";
    public static final String PERSON_ID_ML = "PERSON_ID_ML";
    public static final String PERSON_DRT = "PERSON_DRT";
    public static final String PERSON_MRT = "PERSON_MRT";
    public static final String PERSON_GDR = "PERSON_GDR";
    public static final String PERSON_DT_BTH = "PERSON_DT_BTH";
    public static final String PERSON_CPF = "PERSON_CPF";
    public static final String PERSON_SUP = "PERSON_SUP";
    public static final String PERSON_NME = "PERSON_NME";
    public static final String PARTNER_ID = "PARTNER_ID";
    public static final String USER_ID = "USER_ID";
    public static final String PERSON_ID = "PERSON_ID";
    public static final String queryRetriveByCPF = "select " + PERSON_ID + ", " + PARTNER_ID + ", " + USER_ID + ", "
            + PERSON_NME + ", " + PERSON_SUP + ", " + PERSON_CPF + ", PERSON_RG, PERSON_GDR, " + PERSON_DT_BTH + ", "
            + PERSON_MRT + ", " + "PERSON_DDD, PERSON_PHO, PERSON_DDD_CEL, PERSON_PHO_CEL, PERSON_EML, " + PERSON_DRT
            + ", " + PERSON_ID_ML + ", " + PERSON_ROL + ", PERSON_DDD_COM, PERSON_PHO_COM, " + PERSON_TYPE
            + ", PERSON_FULL_NME_MOTHER, PERSON_RG_ORG_EMS, PERSON_PIS, " + "PERSON_CNS" + " from Person where "
            + PERSON_CPF + " = ?";
    public static final String queryRetriveByID = "select " + PERSON_ID + ", " + PARTNER_ID + ", " + USER_ID + ", "
            + PERSON_NME + ", " + PERSON_SUP + ", " + PERSON_CPF + ", PERSON_RG, PERSON_GDR, " + PERSON_DT_BTH + ", "
            + PERSON_MRT + ", " + "PERSON_DDD, PERSON_PHO, PERSON_DDD_CEL, PERSON_PHO_CEL, PERSON_EML, " + PERSON_DRT
            + ", " + PERSON_ID_ML + ", " + PERSON_ROL + ", PERSON_DDD_COM, PERSON_PHO_COM, " + PERSON_TYPE
            + ", PERSON_FULL_NME_MOTHER, PERSON_RG_ORG_EMS, PERSON_PIS, " + "PERSON_CNS" + " from Person where "
            + PERSON_ID + " = ?";

    public static final String TABLE_INSURED = "INSURED";
    public static final String INSURED_COD = "INSURED_COD";
    public static final String PRD_FRM_DSC = "PRD_FRM_DSC";
    public static final String insertPaymentForm = "INSERT INTO PRD_FORM_PAYMENT (" + PRD_FRM_ID + "," + PRD_FRM_DSC
            + ") VALUES (?,?)";
    public static final String queryRetrievePaymentForm = "SELECT " + PRD_FRM_ID + "," + PRD_FRM_DSC
            + " FROM PRD_FORM_PAYMENT WHERE " + PRD_FRM_ID + " = ?";
    public static final String TABLE_PRD_CREDIT_CARD = "PRD_CREDIT_CARD";
    public static final String TABLE_KEY_CARD = "KEY_CARD";
    public static final String KEY_CARD_ID = "ID";
    public static final String K_ID = "K_ID";
    public static final String TABLE_CARD_DATA = "CARD_DATA";
    public static final String CARD_DATA_ID = "ID";
    public static final String CARD_DATA_ID_FK = "CARD_DATA_ID";
    public static final String CARD_NBR = "CARD_NBR";
    public static final String CARD_NME = "CARD_NME";
    public static final String CARD_EXPIRATION_MONTH_YEAR = "CARD_EXPIRATION_MONTH_YEAR";
    public static final String CARD_SECURITY = "CARD_SECURITY";
    public static final String CARD_VALUE = "CARD_VALUE";
    public static final String CARD_FLAG = "CARD_FLAG";
    public static final String TABLE_CONTRACT_DATA = "CONTRACT_DATA";
    public static final String CONTRACT_DATA_ID = "ID";
    public static final String CONTRACT_NUMBER = "CONTRACT_NUMBER";
    public static final String CONTRACT_INSERT_DATE = "CONTRACT_INSERT_DATE";
    public static final String CONTRACT_UPDATE_DATE = "CONTRACT_UPDATE_DATE";
    public static final String UPDATE_USER_ID = "UPDATE_USER_ID";
    public static final String UPDATE_USER_DOC = "UPDATE_USER_DOC";
    public static final String UPDATE_USER_DOC_TYPE = "UPDATE_USER_DOC_TYPE";
    public static final String TABLE_INSTALLMENT = "INSTALLMENT";
    public static final String CRD_CARD_NBR = "CRD_CARD_NBR";
    public static final String CRD_CARD_FLAG = "CRD_CARD_FLAG";
    public static final String CRD_CARD_MONTH_YEAR = "CRD_CARD_MONTH_YEAR";
    public static final String CRD_CARD_SECURITY = "CRD_CARD_SECURITY";
    public static final String CRD_TERMINAL_NUMBER = "CRD_TERMINAL_NUMBER";
    public static final String CRD_AUTHORIZATION_NUMBER = "CRD_AUTHORIZATION_NUMBER";
    public static final String CRD_NUMBER_OF_TRANSACT = "CRD_NUMBER_OF_TRANSACT";
    public static final String CRD_DATE_OF_TRANSACT = "CRD_DATE_OF_TRANSACT";
    public static final String CRD_HOUR_OF_TRANSACT = "CRD_HOUR_OF_TRANSACT";
    public static final String CRD_CARD_NME = "CRD_CARD_NME";
    public static final String CRD_CARD_PRT = "CRD_CARD_PRT";
    public static final String CRD_CARD_NBR_FORMAT = " \'************\'+ RIGHT(" + "dbo.fcDECRIPT_CREDIT_CARD("
            + CRD_CARD_NBR + " )" + ",4)" + " AS " + CRD_CARD_NBR;
    public static final String queryRetrieveCardPayment = "select " + CRD_CARD_ID + "," + CRD_CARD_NBR_FORMAT + ","
            + CRD_CARD_FLAG + "," + CRD_CARD_MONTH_YEAR + "," + CRD_CARD_SECURITY + "," + CRD_TERMINAL_NUMBER + ","
            + CRD_AUTHORIZATION_NUMBER + "," + CRD_NUMBER_OF_TRANSACT + "," + CRD_DATE_OF_TRANSACT + ","
            + CRD_HOUR_OF_TRANSACT + "," + CRD_CARD_NME + "," + CRD_CARD_PRT + " from PRD_CREDIT_CARD where "
            + CRD_CARD_ID + " = ?";
    public static final int CREDIT_CARD_PAYMENT_TYPE = 8;
    public static final String TABLE_PRD_PRODUCT = "PRD_PRODUCT";
    public static final String TEF_AGREEMENT_ID = "TEF_AGREEMENT_ID";
    public static final String ENTERPRISE_TYPE_ID = "ENTERPRISE_TYPE_ID";
    public static final String PARTNER_COD = "PARTNER_COD";
    public static final String PRD_AGR_FLG_CRT_NBR_FT = "PRD_AGR_FLG_CRT_NBR_FT";
    public static final String PRD_CERT_PREFIX = "PRD_CERT_PREFIX";
    public static final String PRD_EMP_CAP = "PRD_EMP_CAP";
    public static final String PRD_MIN_INSURED_AGE = "PRD_MIN_INSURED_AGE";
    public static final String PRD_MAX_INSURED_AGE = "PRD_MAX_INSURED_AGE";
    public static final String PRD_MAX_CERT_CPF = "PRD_MAX_CERT_CPF";
    public static final String PRD_WORKFLOW_STEPS = "PRD_WORKFLOW_STEPS";
    public static final String PRD_CERTIF_PRINT_FG = "PRD_CERTIF_PRINT_FG";
    public static final String PRD_PLAN_ID = "PRD_PLAN_ID";
    public static final String PRD_AGR_CAP_SER_VL = "PRD_AGR_CAP_SER_VL";
    public static final String PRD_AGR_FLG_CAP_SER = "PRD_AGR_FLG_CAP_SER";
    public static final String PRD_AGR_VL_COMIS = "PRD_AGR_VL_COMIS";
    public static final String PRD_MP_NO = "PRD_MP_NO";
    public static final String PRD_AGR_ID = "PRD_AGR_ID";
    public static final String PRD_PRZ_VIG = "PRD_PRZ_VIG";
    public static final String PRD_SLM_PRT_PCT = "PRD_SLM_PRT_PCT";
    public static final String PRD_SLM_PRT_VL = "PRD_SLM_PRT_VL";
    public static final String PRD_INS_DTM = "PRD_INS_DTM";
    public static final String PRD_EFF_DT = "PRD_EFF_DT";
    public static final String TABLE_USERS = "USERS";
    public static final String PROFILE_ID = "PROFILE_ID";
    public static final String USER_COD = "USER_COD";
    public static final String USER_COD_PRT = "USER_COD_PRT";
    public static final String USER_PASS = "USER_PWD";
    public static final String USER_ACT = "USER_ACT";
    public static final String USER_WORD = "USER_WORD";
    public static final String USER_GRP = "USER_GRP";
    public static final String queryRetrieveProduct = "select " + PRD_ID + ", " + PARTNER_ID + ", " + PRD_COD + ", "
            + PRD_NME + ", " + PRD_DSC + ", " + PRD_EFF_DT + ", PRD_EXP_DT, " + PRD_INS_DTM
            + ", PRD_INS_USER_ID, PRD_UPD_DTM, PRD_UPD_USER_ID" + ", " + PRD_SLM_PRT_VL + ", " + PRD_SLM_PRT_PCT + ", "
            + PRD_PRZ_VIG + ", " + PRD_AGR_ID + ", " + PRD_MP_NO + ", " + PRD_AGR_VL_COMIS + ", " + PRD_AGR_FLG_CAP_SER
            + ", " + PRD_AGR_CAP_SER_VL + ", " + PRD_PLAN_ID + ", " + PRD_CERTIF_PRINT_FG + ", " + PRD_WORKFLOW_STEPS
            + ", " + PRD_MAX_CERT_CPF + ", " + PRD_MAX_INSURED_AGE + ", " + PRD_MIN_INSURED_AGE + ", " + PRD_EMP_CAP
            + ", " + PRD_CERT_PREFIX + ", " + PRD_AGR_FLG_CRT_NBR_FT + ", PRD_SUM_CAR_VL, " + PARTNER_COD
            + ", PRD_ELEGIABLE_CAR_MAX_AGE, PRD_ELEGIABLE_CAR_MAX_FIPE_PRICE"
            + ", PRD_MIN_FINANCING_VALUE, PRD_MAX_FINANCING_VALUE" + ", " + ENTERPRISE_TYPE_ID + ", " + TEF_AGREEMENT_ID
            + " from PRD_PRODUCT where " + PRD_ID + " = ?";
    public static final String queryRetrieveProductPrefix = "select " + PRD_CERT_PREFIX + " from PRD_PRODUCT where "
            + PRD_ID + " = ?";
    public static final String CAPITAL_SERIES_ID = "CAPITAL_SERIES_ID";
    public static final String SP_GET_NEXT_CAPITALSERIES_ID_GARANTIA = "usp_GetNextCapitalSeriesNumberEGarantia_01";
    public static final String SP_GET_NEXT_CAPITALSERIES_ID = "usp_GetNextCapitalSeriesNumber_01";
    public static final String SP_CLEAN_AND_GET_NEWCAPITALSERIES = "";
    public static final String RESULTSET_CAPITAL_SERIES = "#result-set-1";
    public static final String queryRetrieveCapitalSeries = "SELECT " + CAPITAL_SERIES_ID + ", " + OWNER_PARTNER_ID
            + ", " + PARTNER_ID + ", " + CAPITAL_SERIES_LTT + ", " + CAPITAL_SERIES_NBR + ", " + CAPITAL_SERIES_VLD
            + ", " + CAPITAL_SERIES_EFF_DTM + ", " + CAPITAL_SERIES_VLD_DTM + ", " + POLICY_NO + ", " + POLCRT_ENDORS_NO
            + ", " + CAPITAL_SERIES_POLCRT_EFF_DTM + ", " + CAPITAL_SERIES_INS_DTM + ", " + CAPITAL_SERIES_INS_USER_ID
            + ", " + CAPITAL_SERIES_UPD_DTM + ", " + CAPITAL_SERIES_UPD_USER_ID + ", " + CAPITAL_SERIES_DEL_FG + ", "
            + CAP_FILE_ID + ", " + CAP_PRD_ID + "  FROM " + TABLE_CAPITAL_SERIES + " WHERE " + CAPITAL_SERIES_ID
            + " = ?";
    public static final String queryRetrieveCapitalSeriesByCertficate = " SELECT  C.CAPITAL_SERIES_ID,C.OWNER_PARTNER_ID "
            + "  ,C.PARTNER_ID,CAPITAL_SERIES_LTT,C.CAPITAL_SERIES_NBR,C.CAPITAL_SERIES_VLD "
            + "  ,C.CAPITAL_SERIES_EFF_DTM,C.CAPITAL_SERIES_VLD_DTM,C.POLICY_NO,C.POLCRT_ENDORS_NO "
            + "  ,C.CAPITAL_SERIES_POLCRT_EFF_DTM,C.CAPITAL_SERIES_INS_DTM,C.CAPITAL_SERIES_INS_USER_ID "
            + "  ,C.CAPITAL_SERIES_UPD_DTM,C.CAPITAL_SERIES_UPD_USER_ID,C.CAPITAL_SERIES_DEL_FG "
  		    + "  ,C.CAP_FILE_ID,C.CAP_PRD_ID FROM CAPITAL_SERIES C "
  		    + "  	INNER JOIN CERTIFICATE CERT (NOLOCK) ON C.POLICY_NO = CERT.CERTIFICATE_NO "
  		    + "     WHERE  C.POLICY_NO = ? and C.CAPITAL_SERIES_VLD_DTM > DATEADD(MONTH, -6, GETDATE()) "
    		+ "  and DATEPART(YY,CERT.CERTIFICATE_EFF_DT) = DATEPART(YY,GETDATE()) ";
    public static final String TABLE_ADDRESS = "ADDRESS";
    public static final String ADDRESS_ID = "ADDRESS_ID";
    public static final String ADDRESS_ZIP = "ADDRESS_ZIP";
    public static final String ADDRESS_STE = "ADDRESS_STE";
    public static final String ADDRESS_CTY = "ADDRESS_CTY";
    public static final String ADDRESS_NBH = "ADDRESS_NBH";
    public static final String ADDRESS_CMP = "ADDRESS_CMP";
    public static final String ADDRESS_NUM = "ADDRESS_NUM";
    public static final String ADDRESS_NME = "ADDRESS_NME";

    public static final String queryRetriveAddressByPersonID = "SELECT " + ADDRESS_ID + ", " + PERSON_ID + ", "
            + ADDRESS_NME + ", " + ADDRESS_NUM + ", " + ADDRESS_CMP + ", " + ADDRESS_NBH + ", " + ADDRESS_CTY + ", "
            + "ADDRESS_STE" + ", " + "ADDRESS_ZIP" + ", " + "ADDRESS_EML" + ", " + "ADDRESS_CORRESP_FLG "
            + "FROM ADDRESS WHERE " + PERSON_ID + " = ?";

    public static final String SELECT_SALESMAN_BY_PERSONID = "SELECT SM.SALESMAN_ID AS " + SALESMAN_ID
            + ",SM.BROKER_ID AS " + BROKER_ID + ",SM.PERSON_ID AS " + PERSON_ID + ",SM.SALESMAN_COD AS " + SALESMAN_COD
            + ",SM.SALESMAN_BCH AS " + SALESMAN_BCH + ",SM.SALESMAN_ATU_BCH AS " + SALESMAN_ATU_BCH + ",SM.BCH_ID AS "
            + BCH_ID + " FROM SALESMAN SM, PERSON P WHERE SM.PERSON_ID = P.PERSON_ID AND P.PERSON_ID = ?";
    public static final String SELECT_SALESMAN_BY_SALESMANID = "SELECT SM.SALESMAN_ID AS " + SALESMAN_ID
            + ",SM.BROKER_ID AS " + BROKER_ID + ",SM.PERSON_ID AS " + PERSON_ID + ",SM.SALESMAN_COD AS " + SALESMAN_COD
            + ",SM.SALESMAN_BCH AS " + SALESMAN_BCH + ",SM.SALESMAN_ATU_BCH AS " + SALESMAN_ATU_BCH + ",SM.BCH_ID AS "
            + BCH_ID + " FROM SALESMAN SM WHERE " + SALESMAN_ID + " = ?";

    public static final String TABLE_BRANCH = "BRANCH";
    public static final String TABLE_REGION = "REGION";
    public static final String queryRetrieveRegionAndBranchByBranchID = "SELECT " + REG_ID + ", " + BCH_COD + " FROM "
            + TABLE_BRANCH + " WHERE " + BCH_ID + " = ?";
    public static final String queryRetrieveCodRegionForName = "SELECT " + REG_ID + " FROM "
            + TABLE_REGION + " WHERE UPPER(" + REG_DSC + ") = UPPER(?)";
    public static final String DIRECTSALES_JNDI_NAME = "java:jboss/datasources/directSales";
    public static final String CONTRACT_JNDI_NAME = "java:jboss/datasources/contract";
    public static final String PHONE_NUM = "PHONE_NUM";
    public static final String PHONE_TYP = "PHONE_TYP";
    public static final String queryRetrievePhoneByPersonID = "SELECT (CAST(P.PERSON_DDD AS VARCHAR(3)) + CAST(P.PERSON_PHO AS VARCHAR(10))) AS PHONE_NUM, 'RESIDENTIAL' AS PHONE_TYP FROM PERSON P WHERE PERSON_ID = ? UNION "
            + "SELECT (CAST(P.PERSON_DDD_CEL AS VARCHAR(3)) + CAST(P.PERSON_PHO_CEL AS VARCHAR(10))) AS PHONE_NUM, 'MOBILE' AS PHONE_TYP FROM PERSON P WHERE PERSON_ID = ?";

    public static final String queryRetrieveSalesmanIDByCPF = "SELECT  TOP 1      " + SALESMAN_ID
            + ", PRIORITY   FROM (          SELECT                  MAX(C.SALESMAN_ID) AS SALESMAN_ID,              1 "
            + "PRIORITY      FROM            CERTIFICATE C  (NOLOCK)                 INNER JOIN SALESMAN S (NOLOCK) "
            + "ON C.SALESMAN_ID = S.SALESMAN_ID                 INNER JOIN PERSON P (NOLOCK) "
            + "ON S.PERSON_ID = P.PERSON_ID       WHERE P.PERSON_CPF = ?          "
            + "UNION   SELECT                  MAX(S.SALESMAN_ID) AS SALESMAN_ID,              2 PRIORITY      "
            + "FROM            SALESMAN S  (NOLOCK)            INNER JOIN PERSON P (NOLOCK) "
            + "ON S.PERSON_ID = P.PERSON_ID       WHERE P.PERSON_CPF = ? ) AS TEMP WHERE SALESMAN_ID IS NOT NULL "
            + "ORDER BY PRIORITY";
    public static final String queryRetrieveSalesmanPersonIDBySalesmanID = "SELECT PERSON_ID FROM SALESMAN WHERE SALESMAN_ID = ?";
    public static final String queryRetrieveSalesmanScoreStep = " FROM CERTIFICATE (NOLOCK) C                "
            + "  INNER JOIN SALESMAN S (NOLOCK) ON C.SALESMAN_ID = S.SALESMAN_ID"
            + "  INNER JOIN PERSON P  (NOLOCK) ON S.PERSON_ID = P.PERSON_ID"
            + "  INNER JOIN USERS U  (NOLOCK) ON P.USER_ID = U.USER_ID"
            + "  INNER JOIN BRANCH B  (NOLOCK) ON C.BCH_ID = B.BCH_ID"
            + "  INNER JOIN REGION R  (NOLOCK) ON R.REG_ID = B.REG_ID"
            + "  INNER JOIN PRD_PRODUCT PRD (NOLOCK) ON PRD.PRD_ID = C.PRD_ID" + " WHERE " + "  CERTIFICATE_STS = 1 "
            + "  AND B.BCH_ID = C.BCH_ID  " + "  AND S.SALESMAN_ID = C.SALESMAN_ID  ";
    public static final String baseRetrieveCertificate = "SELECT CERT.CERTIFICATE_ID AS CERTIFICATE_ID, CERT.INSURED_ID "
            + "AS INSURED_ID, CERT.SALESMAN_ID AS SALESMAN_ID, CERT.PRD_ID AS PRD_ID, CERT.CERTIFICATE_STS AS CERTIFICATE_STS"
            + ", CERT.CERTIFICATE_NO AS CERTIFICATE_NO, CERT.CERTIFICATE_PRM_VL AS CERTIFICATE_PRM_VL "
            + ", CERT.CERTIFICATE_EFF_DT AS CERTIFICATE_EFF_DT, CERT.CERTIFICATE_EXP_DT AS CERTIFICATE_EXP_DT"
            + ", CERT.CERTIFICATE_INI_VIG_DT AS CERTIFICATE_INI_VIG_DT, CERT.CERTIFICATE_CMS_VL AS CERTIFICATE_CMS_VL"
            + ", CERT.CERTIFICATE_CAPITAL_SERIES_NBR AS CERTIFICATE_CAPITAL_SERIES_NBR, CERT.CRD_CARD_ID AS CRD_CARD_ID, CERT.PRD_FRM_ID AS PRD_FRM_ID "
            + ", PROD.PRD_DSC AS PRD_DSC, PE.PERSON_ID FROM CERTIFICATE CERT (NOLOCK)"
            + ", (SELECT PRD_ID, PRD_DSC FROM PRD_PRODUCT (NOLOCK) ) PROD ";
    public static final String queryRetrievePersonBySalesmanID = "select P.PERSON_ID, P.PARTNER_ID"
            + ", P.USER_ID , P.PERSON_NME, P.PERSON_SUP, P.PERSON_CPF, P.PERSON_RG, P.PERSON_GDR"
            + ", P.PERSON_DT_BTH , P.PERSON_MRT, P.PERSON_DDD, P.PERSON_PHO, P.PERSON_DDD_CEL, P.PERSON_PHO_CEL"
            + ", P.PERSON_EML, P.PERSON_DRT, P.PERSON_ID_ML, P.PERSON_ROL, P.PERSON_DDD_COM, P.PERSON_PHO_COM"
            + ", P.PERSON_TYPE, P.PERSON_FULL_NME_MOTHER, P.PERSON_RG_ORG_EMS, P.PERSON_PIS"
            + ", P.PERSON_CNS from PERSON P (NOLOCK) , SALESMAN S where 1 = 1 "
            + "AND S.PERSON_ID = P.PERSON_ID AND  S.SALESMAN_ID = ?";
    public static final String queryRetrieveSalesmanByPersonID = "SELECT SM.SALESMAN_ID AS " + SALESMAN_ID
            + ",SM.BROKER_ID AS " + BROKER_ID + ",SM.PERSON_ID AS " + PERSON_ID + ",SM.SALESMAN_COD AS " + SALESMAN_COD
            + ",SM.SALESMAN_BCH AS " + SALESMAN_BCH + ",SM.SALESMAN_ATU_BCH AS " + SALESMAN_ATU_BCH + ",SM.BCH_ID AS "
            + BCH_ID + " FROM SALESMAN SM, PERSON P WHERE SM.PERSON_ID = P.PERSON_ID AND P.PERSON_ID = ?";
    public static final String queryRetrieveSalesmanBySalesmanID = "SELECT SM.SALESMAN_ID AS " + SALESMAN_ID
            + ",SM.BROKER_ID AS " + BROKER_ID + ",SM.PERSON_ID AS " + PERSON_ID + ",SM.SALESMAN_COD AS " + SALESMAN_COD
            + ",SM.SALESMAN_BCH AS " + SALESMAN_BCH + ",SM.SALESMAN_ATU_BCH AS " + SALESMAN_ATU_BCH + ",SM.BCH_ID AS "
            + BCH_ID + " FROM SALESMAN SM WHERE " + SALESMAN_ID + " = ?";
    public static final String queryRetrieveCertificateListBySalesmanID = " SELECT CERT.CERTIFICATE_ID AS CERTIFICATE_ID, CERT.INSURED_ID  "
    		+ "  AS INSURED_ID, CERT.SALESMAN_ID AS SALESMAN_ID, CERT.PRD_ID AS PRD_ID, CERT.CERTIFICATE_STS AS CERTIFICATE_STS "
    		+ "  , CERT.CERTIFICATE_NO AS CERTIFICATE_NO, CERT.CERTIFICATE_PRM_VL AS CERTIFICATE_PRM_VL, P.PERSON_ID "
    		+ "  , CERT.CERTIFICATE_EFF_DT AS CERTIFICATE_EFF_DT, CERT.CERTIFICATE_EXP_DT AS CERTIFICATE_EXP_DT, OBJ.INSURED_OBJECT_ID "
    		+ "  , CERT.CERTIFICATE_INI_VIG_DT AS CERTIFICATE_INI_VIG_DT, CERT.CERTIFICATE_CMS_VL AS CERTIFICATE_CMS_VL "
    		+ "  , CERT.CERTIFICATE_CAPITAL_SERIES_NBR AS CERTIFICATE_CAPITAL_SERIES_NBR, CERT.CRD_CARD_ID AS CRD_CARD_ID, CERT.PRD_FRM_ID AS PRD_FRM_ID "
    		+ "  , PRD.PRD_DSC AS PRD_DSC FROM CERTIFICATE CERT (NOLOCK) "
    		+ "	 INNER JOIN INSURED I (NOLOCK) ON CERT.INSURED_ID = I.INSURED_ID "
    		+ "	 INNER JOIN PERSON P  (NOLOCK) ON I.PERSON_ID = P.PERSON_ID "
    		+ "	 INNER JOIN SALESMAN S (NOLOCK) ON S.SALESMAN_ID = CERT.SALESMAN_ID "
    		+ "  LEFT JOIN INSURED_OBJECT OBJ (NOLOCK) ON CERT.CERTIFICATE_ID = OBJ.CERTIFICATE_ID  "
    		+ "	 INNER JOIN PRD_PRODUCT PRD (NOLOCK) ON PRD.PRD_ID = CERT.PRD_ID 	WHERE S.SALESMAN_ID = ? ";
    public static final String queryRetrieveActiveCertificate = baseRetrieveCertificate + "AND CERT.CERTIFICATE_STS = "
            + ACTIVE_CERTIFICATE_STATUS + " AND INN.PERSON_ID = ? AND CERT.PRD_ID = ?";
    public static final String queryRetrieveActiveCertificateByPersonCPF = "SELECT COUNT(C.CERTIFICATE_ID) AS QUANTITY "
            + " FROM CERTIFICATE C (NOLOCK) INNER JOIN INSURED I (NOLOCK) ON C.INSURED_ID = I.INSURED_ID "
            + " INNER JOIN PERSON P (NOLOCK) ON I.PERSON_ID = P.PERSON_ID   "
            + " WHERE P.PERSON_CPF = ? AND C.PRD_ID = ? AND C.CERTIFICATE_STS = " + ACTIVE_CERTIFICATE_STATUS;
    public static final String queryRetrieveCertificateByContractNumber = "SELECT CERT.CERTIFICATE_ID AS CERTIFICATE_ID, CERT.INSURED_ID  "
    		+ "  AS INSURED_ID, CERT.SALESMAN_ID AS SALESMAN_ID, CERT.PRD_ID AS PRD_ID, CERT.CERTIFICATE_STS AS CERTIFICATE_STS "
    		+ "  , CERT.CERTIFICATE_NO AS CERTIFICATE_NO, CERT.CERTIFICATE_PRM_VL AS CERTIFICATE_PRM_VL, P.PERSON_ID "
    		+ "  , CERT.CERTIFICATE_EFF_DT AS CERTIFICATE_EFF_DT, CERT.CERTIFICATE_EXP_DT AS CERTIFICATE_EXP_DT, OBJ.INSURED_OBJECT_ID "
    		+ "  , CERT.CERTIFICATE_INI_VIG_DT AS CERTIFICATE_INI_VIG_DT, CERT.CERTIFICATE_CMS_VL AS CERTIFICATE_CMS_VL "
    		+ "  , CERT.CERTIFICATE_CAPITAL_SERIES_NBR AS CERTIFICATE_CAPITAL_SERIES_NBR, CERT.CRD_CARD_ID AS CRD_CARD_ID, CERT.PRD_FRM_ID AS PRD_FRM_ID "
    		+ "  , PRD.PRD_DSC AS PRD_DSC FROM CERTIFICATE CERT (NOLOCK) "
    		+ "	 INNER JOIN INSURED I (NOLOCK) ON CERT.INSURED_ID = I.INSURED_ID "
    		+ "	 INNER JOIN PERSON P  (NOLOCK) ON I.PERSON_ID = P.PERSON_ID "
    		+ "	 INNER JOIN SALESMAN S (NOLOCK) ON S.SALESMAN_ID = CERT.SALESMAN_ID "
    		+ "  LEFT JOIN INSURED_OBJECT OBJ (NOLOCK) ON CERT.CERTIFICATE_ID = OBJ.CERTIFICATE_ID  "
    		+ "	 INNER JOIN PRD_PRODUCT PRD (NOLOCK) ON PRD.PRD_ID = CERT.PRD_ID 	WHERE CERT.CERTIFICATE_NO = ? AND CERT.CERTIFICATE_STS = 1";
    public static final String updateCertificateStatus = "UPDATE " + TABLE_CERTIFICATE + " SET " + CERTIFICATE_STS
            + " = ?" + " WHERE " + CERTIFICATE_ID + " = ?";
    public static final String updateCertificate =   "UPDATE CERTIFICATE SET INSURED_ID = ?,SALESMAN_ID = ?, " 
    		+ " CERTIFICATE_EFF_DT = ?, CERTIFICATE_EXP_DT = ?, CERTIFICATE_STS = ?, "
    		+ " CERTIFICATE_EMS_DT = ?, CERTIFICATE_BCH = ?, CERTIFICATE_TMP_STS = ?,"
    		+ " BCH_ID = ?,REG_ID = ?,PRD_FRM_ID = ?,CRD_CARD_ID = ?, CERTIFICATE_INI_VIG_DT = ? "
    		+ " WHERE CERTIFICATE_ID = ? ";	
    public static final String updateCertificateStsAndTmpSts = "UPDATE " + TABLE_CERTIFICATE + " SET " + CERTIFICATE_STS
            + " = ?" + "," + CERTIFICATE_TMP_STS + " = ?" + " WHERE " + CERTIFICATE_ID + " = ?";
    public static final String queryRetrieveCertificateListByPersonCpf = "SELECT CERT.CERTIFICATE_ID AS CERTIFICATE_ID, CERT.INSURED_ID  "
            +"  AS INSURED_ID, CERT.SALESMAN_ID AS SALESMAN_ID, CERT.PRD_ID AS PRD_ID, CERT.CERTIFICATE_STS AS CERTIFICATE_STS "
            +"  , CERT.CERTIFICATE_NO AS CERTIFICATE_NO, CERT.CERTIFICATE_PRM_VL AS CERTIFICATE_PRM_VL, P.PERSON_ID "
            +"  , CERT.CERTIFICATE_EFF_DT AS CERTIFICATE_EFF_DT, CERT.CERTIFICATE_EXP_DT AS CERTIFICATE_EXP_DT, OBJ.INSURED_OBJECT_ID "
            +"  , CERT.CERTIFICATE_INI_VIG_DT AS CERTIFICATE_INI_VIG_DT, CERT.CERTIFICATE_CMS_VL AS CERTIFICATE_CMS_VL "
            +"  , CERT.CERTIFICATE_CAPITAL_SERIES_NBR AS CERTIFICATE_CAPITAL_SERIES_NBR, CERT.CRD_CARD_ID AS CRD_CARD_ID, CERT.PRD_FRM_ID AS PRD_FRM_ID "
            +"  , P.PARTNER_ID, P.USER_ID, P.PERSON_NME, P.PERSON_SUP, P.PERSON_CPF, P.PERSON_RG, P.PERSON_GDR, P.PERSON_DT_BTH "
                        +"  , P.PERSON_MRT, P.PERSON_DDD, P.PERSON_PHO, P.PERSON_DDD_CEL, P.PERSON_PHO_CEL, P.PERSON_EML, P.PERSON_DRT "
                        +"  , P.PERSON_ID_ML, P.PERSON_ROL, P.PERSON_DDD_COM, P.PERSON_PHO_COM, P.PERSON_TYPE "
                        +"  , P.PERSON_FULL_NME_MOTHER, P.PERSON_RG_ORG_EMS, P.PERSON_PIS, P.PERSON_CNS "
                        +"  , PRD.PRD_DSC AS PRD_DSC, SM.SALESMAN_BCH, SM.SALESMAN_ATU_BCH, SM.BCH_ID "
                        +"  , SM.SALESMAN_ID, SM.BROKER_ID AS BROKER_ID,SM.PERSON_ID AS PERSON_ID_SM,SM.SALESMAN_COD "
                        +"  , PS.PARTNER_ID AS PARTNER_ID_SM, PS.USER_ID AS USER_ID_SM , PS.PERSON_NME AS PERSON_NME_SM, PS.PERSON_SUP AS PERSON_SUP_SM "
                        +"  , PS.PERSON_CPF AS PERSON_CPF_SM, PS.PERSON_RG AS PERSON_RG_SM, PS.PERSON_GDR AS PERSON_GDR_SM "
                        +"  , PS.PERSON_DT_BTH AS PERSON_DT_BTH_SM , PS.PERSON_MRT AS PERSON_MRT_SM, PS.PERSON_DDD AS PERSON_DDD_SM "
                        +"  , PS.PERSON_PHO AS PERSON_PHO_SM, PS.PERSON_DDD_CEL AS PERSON_DDD_CEL_SM, PS.PERSON_PHO_CEL AS PERSON_PHO_CEL_SM "
                        +"  , PS.PERSON_EML AS PERSON_EML_SM, PS.PERSON_DRT AS PERSON_DRT_SM, PS.PERSON_ID_ML AS PERSON_ID_ML_SM, PS.PERSON_ROL AS PERSON_ROL_SM "
                        +"  , PS.PERSON_DDD_COM AS PERSON_DDD_COM_SM, PS.PERSON_PHO_COM AS PERSON_PHO_COM_SM, PS.PERSON_TYPE AS PERSON_TYPE_SM "
                        +"  , PS.PERSON_FULL_NME_MOTHER AS PERSON_FULL_NME_MOTHER_SM, PS.PERSON_RG_ORG_EMS AS PERSON_RG_ORG_EMS_SM "
                        +"  , PS.PERSON_PIS AS PERSON_PIS_SM, PS.PERSON_CNS AS PERSON_CNS_SM "
                        +"  FROM CERTIFICATE CERT (NOLOCK) "
                        +" INNER JOIN SALESMAN SM (NOLOCK) ON CERT.SALESMAN_ID = SM.SALESMAN_ID "
                        +" INNER JOIN PERSON PS (NOLOCK) ON SM.PERSON_ID = PS.PERSON_ID "
            +" INNER JOIN INSURED I (NOLOCK) ON CERT.INSURED_ID = I.INSURED_ID "
            +" INNER JOIN PERSON P  (NOLOCK) ON I.PERSON_ID = P.PERSON_ID "
            +" LEFT JOIN INSURED_OBJECT OBJ (NOLOCK) ON CERT.CERTIFICATE_ID = OBJ.CERTIFICATE_ID  "
            +" INNER JOIN PRD_PRODUCT PRD (NOLOCK) ON PRD.PRD_ID = CERT.PRD_ID WHERE P.PERSON_CPF =  ?  ";      
    public static final String baseQueryRetrievePerformance = " SELECT  "
    		+ "          PRD.PRD_DSC , "
    		+ "  		U.USER_COD_PRT AS USER_CODE_PRT, "
    		+ "  		P.PERSON_NME AS PERSON_NAME,  "
    		+ "  		P.PERSON_CPF AS PERSON_CPF,  "
    		+ "  		C.SALESMAN_ID AS SALESMAN_ID, "
    		+ "  		C.BCH_ID AS BCH_ID,	"
    		+ "  		C.PRD_ID, "
    		+ "  		B.BCH_COD AS BCH_COD, "
    		+ "  		B.BCH_DSC AS BCH_DSC, "
    		+ "  		R.REG_ID AS REG_ID, "
    		+ "  		R.REG_DSC AS REG_DSC ,"
    		+ "  		SUM(C.CERTIFICATE_PRM_VL) AS PREMIUN_VALUE, "
    		+ "      SUM(C.CERTIFICATE_CMS_VL) AS COMMISSION_VALUE, "
    		+ "  		COUNT(*) AS SOLD_CERTIFICATES           "
    		+ "  			FROM CERTIFICATE (NOLOCK) C       "         
    		+ "  				INNER JOIN SALESMAN S (NOLOCK) ON C.SALESMAN_ID = S.SALESMAN_ID	"
    		+ "  				INNER JOIN PERSON P  (NOLOCK) ON S.PERSON_ID = P.PERSON_ID "
    		+ "  				INNER JOIN USERS U  (NOLOCK) ON P.USER_ID = U.USER_ID "
    		+ "  				INNER JOIN BRANCH B  (NOLOCK) ON C.BCH_ID = B.BCH_ID "
    		+ "  				INNER JOIN REGION R  (NOLOCK) ON R.REG_ID = B.REG_ID "
    		+ "  				INNER JOIN PRD_PRODUCT PRD (NOLOCK) ON PRD.PRD_ID = C.PRD_ID "
    		+ "  			WHERE "
    		+ "  				C.CERTIFICATE_STS = 1   ";
    public static final String queryRetrieveCertificateListByPersonCpfAndPrdIdAndSts = " SELECT CERT.CERTIFICATE_ID AS CERTIFICATE_ID, CERT.INSURED_ID  "
            + "   AS INSURED_ID, CERT.SALESMAN_ID AS SALESMAN_ID, CERT.PRD_ID AS PRD_ID, CERT.CERTIFICATE_STS AS CERTIFICATE_STS "
            + "   , CERT.CERTIFICATE_NO AS CERTIFICATE_NO, CERT.CERTIFICATE_PRM_VL AS CERTIFICATE_PRM_VL, P.PERSON_ID "
            + "   , CERT.CERTIFICATE_EFF_DT AS CERTIFICATE_EFF_DT, CERT.CERTIFICATE_EXP_DT AS CERTIFICATE_EXP_DT, OBJ.INSURED_OBJECT_ID  "
            + "   , CERT.CERTIFICATE_INI_VIG_DT AS CERTIFICATE_INI_VIG_DT, CERT.CERTIFICATE_CMS_VL AS CERTIFICATE_CMS_VL "
            + "   , CERT.CERTIFICATE_CAPITAL_SERIES_NBR AS CERTIFICATE_CAPITAL_SERIES_NBR, CERT.CRD_CARD_ID AS CRD_CARD_ID, CERT.PRD_FRM_ID AS PRD_FRM_ID "
            + "   , PRD.PRD_DSC AS PRD_DSC FROM CERTIFICATE CERT (NOLOCK) "
    		+ "	 INNER JOIN INSURED I (NOLOCK) ON CERT.INSURED_ID = I.INSURED_ID "
    		+ "	 INNER JOIN PERSON P  (NOLOCK) ON I.PERSON_ID = P.PERSON_ID "
    		+ "  LEFT JOIN INSURED_OBJECT OBJ (NOLOCK) ON CERT.CERTIFICATE_ID = OBJ.CERTIFICATE_ID  "  
    		+ "	 INNER JOIN PRD_PRODUCT PRD (NOLOCK) ON PRD.PRD_ID = CERT.PRD_ID "
    		+ "	WHERE P.PERSON_CPF =  ? and PRD.PRD_ID = ? and CERT.CERTIFICATE_STS = ? and CERT.CERTIFICATE_TMP_STS = " + CODE_TIMEOUT;
    public static final String queryRetrieveBeneficiariesByInsuredObjectId = "SELECT BENEFICIARY_ID, BENEFICIARY_NME, BENEFICIARY_DT_BTH, BENEFICIARY_PCT FROM BENEFICIARY WHERE INSURED_OBJECT_ID = ? ";
    public static final String queryfindSalesmanBchByBranch = " SELECT BCH_ID FROM BRANCH "
            + "WHERE " + REG_ID + " = ? AND BCH_COD = ?"; 
    public static final String updateSalesmanBranch = "UPDATE SALESMAN SET SALESMAN_BCH = ?,SALESMAN_ATU_BCH = ?, "
            + "BCH_ID = ? " + " WHERE PERSON_ID = ? ";
    public static final String updateCleanCertificateInCapitalSeries = "UPDATE " + TABLE_CAPITAL_SERIES + " SET " + POLICY_NO
            + " = NULL" + " WHERE " + POLICY_NO + " = ?";
    public static final String queryRetrieveMaxAgeInsuredProduct = "SELECT " + PRD_MAX_INSURED_AGE  + " from PRD_PRODUCT where " + PRD_ID + " = ?";
}

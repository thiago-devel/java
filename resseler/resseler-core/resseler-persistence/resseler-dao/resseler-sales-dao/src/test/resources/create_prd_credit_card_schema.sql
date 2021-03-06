CREATE TABLE PRD_CREDIT_CARD (
    CRD_CARD_ID INTEGER IDENTITY PRIMARY KEY,
    CRD_CARD_NBR varchar(20) NOT NULL,
    CRD_CARD_FLAG varchar(23) NULL,
    CRD_CARD_MONTH_YEAR varchar(5) NULL,
    CRD_CARD_SECURITY varchar(4) NULL,
    CRD_TERMINAL_NUMBER varchar(22) NULL,
    CRD_AUTHORIZATION_NUMBER varchar(7) NULL,
    CRD_NUMBER_OF_TRANSACT varchar(20) NULL,
    CRD_DATE_OF_TRANSACT varchar(10) NULL,
    CRD_HOUR_OF_TRANSACT varchar(5) NULL,
    CRD_CARD_NME varchar(30) NULL,
    CRD_CARD_PRT varchar(20) NULL
)
;;

CREATE FUNCTION udf_EncryptCreditCard(CARD_NBR BIGINT, KEY BIGINT)
   RETURNS varchar(20)
   READS SQL DATA
     RETURN 'F010C12E90A3E096';;

CREATE SCHEMA dbo
;;

CREATE FUNCTION dbo.udf_EncryptCreditCard(CARD_NBR BIGINT, KEY BIGINT)
   RETURNS varchar(20)
   READS SQL DATA
     RETURN 'F010C12E90A3E096';;

CREATE FUNCTION dbo.fcDECRIPT_CREDIT_CARD(CARD_NBR VARCHAR(20))
   RETURNS varchar(20)
   READS SQL DATA
     RETURN '4716005366685846';;

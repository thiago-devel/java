
CREATE TABLE PRD_PRODUCT(
	PRD_ID INTEGER GENERATED BY DEFAULT AS IDENTITY(START WITH 25, INCREMENT BY 1) PRIMARY KEY,
	PARTNER_ID int NULL,
	PRD_COD varchar(50) NULL,
	PRD_NME varchar(50) NULL,
	PRD_DSC varchar(100) NULL,
	PRD_EFF_DT datetime NULL,
	PRD_EXP_DT datetime NULL,
	PRD_INS_DTM datetime NULL,
	PRD_INS_USER_ID int NULL,
	PRD_UPD_DTM datetime NULL,
	PRD_UPD_USER_ID int NULL,
	PRD_SLM_PRT_VL decimal NULL,
	PRD_SLM_PRT_PCT numeric(7, 5) NULL,
	PRD_PRZ_VIG int NULL,
	PRD_AGR_ID int NULL,
	PRD_MP_NO int NULL,
	PRD_AGR_VL_COMIS decimal NULL,
	PRD_AGR_FLG_CAP_SER char(1) NULL,
	PRD_AGR_CAP_SER_VL numeric NULL,
	PRD_PLAN_ID int NULL,
	PRD_CERTIF_PRINT_FG varchar(1) NULL,
	PRD_WORKFLOW_STEPS varchar(10) NULL,
	PRD_MAX_CERT_CPF int NULL,
	PRD_MAX_INSURED_AGE int NULL,
	PRD_MIN_INSURED_AGE int NULL,
	PRD_EMP_CAP varchar(3) NULL,
	PRD_CERT_PREFIX varchar(5) NULL,
	PRD_AGR_FLG_CRT_NBR_FT char(1) NULL,
	PRD_SUM_CAR_VL decimal NULL,
	PARTNER_COD varchar(50) NULL,
	PRD_ELEGIABLE_CAR_MAX_AGE int NULL,
	PRD_ELEGIABLE_CAR_MAX_FIPE_PRICE decimal NULL,
	PRD_MIN_FINANCING_VALUE decimal NULL,
	PRD_MAX_FINANCING_VALUE decimal NULL,
	ENTERPRISE_TYPE_ID int NOT NULL,
	TEF_AGREEMENT_ID int NOT NULL
)
;

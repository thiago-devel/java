CREATE TABLE ADDRESS(
    ADDRESS_ID INTEGER IDENTITY PRIMARY KEY,
    PERSON_ID int NULL,
    ADDRESS_NME varchar(80) NULL,
    ADDRESS_NUM varchar(20) NULL,
    ADDRESS_CMP varchar(20) NULL,
    ADDRESS_NBH varchar(30) NULL,
    ADDRESS_CTY varchar(30) NULL,
    ADDRESS_STE varchar(2) NULL,
    ADDRESS_ZIP varchar(12) NULL,
    ADDRESS_EML varchar(30) NULL,
    ADDRESS_CORRESP_FLG int NULL
)
;

ALTER CATALOG PUBLIC RENAME TO AuditTrail;
create schema dbo;

CREATE TABLE dbo.LOG_SEQUENCES(
    sequence_name varchar(255) NOT NULL,
    next_val INTEGER IDENTITY PRIMARY KEY
);

CREATE TABLE dbo.TB_Key(
    ID bigint IDENTITY PRIMARY KEY,
    Value clob(255) NULL
);

CREATE TABLE dbo.TB_KeyParam(
    ID bigint IDENTITY PRIMARY KEY,
    IdTbKey bigint NULL,
    IdTbParameter bigint NULL
);

CREATE TABLE dbo.TB_KeyValueLogger(
    ID bigint IDENTITY PRIMARY KEY,
    Value clob(255) NULL,
    IdTbKeyParam bigint NULL,
    IdTbLogger bigint NULL
);

CREATE TABLE dbo.TB_Logger(
    ID bigint IDENTITY PRIMARY KEY,
    Country varchar(255) NULL,
    DocumentLog blob NULL,
    DocumentParse varchar(255) NULL,
    requestAddressLog varchar(255) NULL,
    targetLog varchar(255) NULL,
    Token varchar(255) NULL,
    UserLog varchar(255) NULL,
    WhatLog varchar(255) NULL,
    WhenLog datetime NULL
);

CREATE TABLE dbo.TB_Parameter(
    ID bigint IDENTITY PRIMARY KEY,
    Value varchar(255) NULL
);
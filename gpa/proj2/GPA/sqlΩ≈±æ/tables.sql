/*==============================================================*/
/* DBMS name:      MySQL 5.0                                    */
/* Created on:     2015/7/17 0:54:45                            */
/*==============================================================*/


DROP TABLE IF EXISTS GPAINFO;

DROP TABLE IF EXISTS SCOREINFO;

DROP TABLE IF EXISTS SEMINARINFO;

DROP TABLE IF EXISTS STUDENTINFO;

DROP TABLE IF EXISTS USERINFO;

/*==============================================================*/
/* Table: GPAINFO                                               */
/*==============================================================*/
CREATE TABLE GPAINFO
(
   ST_ID                VARCHAR(20) NOT NULL,
   ST_NAME              VARCHAR(20),
   ST_ENAME             VARCHAR(20),
   ST_ENAME_DESC        VARCHAR(20),
   ADDRESS              VARCHAR(100),
   HOUSE                VARCHAR(20),
   SM_ID                VARCHAR(20) NOT NULL,
   SR_ID                CHAR(1),
   FSTDAY_CHECK         CHAR(1),
   FSTDAY_SCORE         VARCHAR(20),
   SECTDAY_CHECK        CHAR(1),
   SECDAY_SCORE         VARCHAR(20),
   THRDAY_CHECK         CHAR(1),
   THRDAY_SCORE         VARCHAR(20),
   CALCULATE_GPA        VARCHAR(20),
   OVER_GPA             VARCHAR(20),
   FINAL_GPA            VARCHAR(20),
   PAPER_SCORE          VARCHAR(20),
   PRIMARY KEY (ST_ID, SM_ID)
);

/*==============================================================*/
/* Table: SCOREINFO                                             */
/*==============================================================*/
CREATE TABLE SCOREINFO
(
   SC_LEVL              VARCHAR(10) NOT NULL,
   SC_NUM               VARCHAR(20),
   PRIMARY KEY (SC_LEVL)
);

/*==============================================================*/
/* Table: SEMINARINFO                                           */
/*==============================================================*/
CREATE TABLE SEMINARINFO
(
   SM_ID                VARCHAR(20) NOT NULL,
   SM_NAME              VARCHAR(50),
   SL_ID                VARCHAR(20),
   SL_NAME              VARCHAR(20),
   SL_ENAME             VARCHAR(20),
   TA_ID                VARCHAR(20),
   TA_NAME              VARCHAR(20),
   TA_ENAME             VARCHAR(20),
   CLS_ID               VARCHAR(20),
   COLUMN_10            VARCHAR(20),
   PRIMARY KEY (SM_ID)
);

/*==============================================================*/
/* Table: STUDENTINFO                                           */
/*==============================================================*/
CREATE TABLE STUDENTINFO
(
   ST_ID                VARCHAR(20) NOT NULL,
   ST_NAME              VARCHAR(20),
   ST_ENAME             VARCHAR(20),
   ADRESS               VARCHAR(100),
   HOUSE                VARCHAR(20),
   SMA_ID               VARCHAR(20),
   SMB_ID               VARCHAR(20),
   SMC_ID               VARCHAR(20),
   SMD_ID               VARCHAR(20),
   PRIMARY KEY (ST_ID)
);

/*==============================================================*/
/* Table: USERINFO                                              */
/*==============================================================*/
CREATE TABLE USERINFO
(
   USER_ID              VARCHAR(20) NOT NULL,
   USER_PASSWD          VARCHAR(20),
   USER_TYPE            CHAR(1),
   PRIMARY KEY (USER_ID)
);


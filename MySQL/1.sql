USE DB_MEMBER;

SHOW TABLES;

DESC TBL_MEMBER;

SELECT * FROM TBL_MEMBER;

-- creat database
CREATE DATABASE DB_SALES;
SHOW DATABASES;
USE DB_SALES;

-- create table
CREATE TABLE TBL_ORDER(
   NO         INT AUTO_INCREMENT,
   PRD_CODE   VARCHAR(50) NOT NULL,
   PRD_PRICE   INT         NOT NULL,
   CNT         INT         NOT NULL,
   REG_DATE   TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
   MOD_DATE   TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
   PRIMARY KEY(NO)
);

DROP DATABASE DB_MEMBER;
SHOW DATABASES;

-- -----------------------------------------------------------
-- --------------- DB_MEMBER START ---------------------------
-- -----------------------------------------------------------

-- create database
CREATE DATABASE DB_MEMBER;
SHOW DATABASES;
USE DB_MEMBER;

-- create table
CREATE TABLE TBL_MEMBER(
   memNo         INT            NOT NULL   AUTO_INCREMENT,
   memId         VARCHAR(50)      NOT NULL   UNIQUE,
   memPw         VARCHAR(100)   NOT NULL,    
   memMail         VARCHAR(50),
   memPhone      VARCHAR(50),
   memRegDate      TIMESTAMP       DEFAULT CURRENT_TIMESTAMP,
   memModDate      TIMESTAMP       DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
   PRIMARY KEY(memNo)
);

SHOW TABLES;


INSERT INTO TBL_MEMBER(memId, memPw, memMail, memPhone) 
VALUES('gildong', '1234', 'gildong@gmail.com', '010-1234-5678');

SELECT * FROM TBL_MEMBER;
SELECT * FROM TBL_MEMBER where memId = 'gildong' and memPw = '1234';


-- --------------------- 백신 접종 현황 DB ----------------------------






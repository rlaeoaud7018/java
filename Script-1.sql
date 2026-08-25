-- --------------------- 백신 접종 현황 DB ----------------------------
-- DB 생성
CREATE DATABASE DB_FLU;
USE DB_FLU;
SHOW DATABASES;

-- 백신 타입 테이블 생성
CREATE TABLE tbl_fv_type(
   fv_type_no            INT      AUTO_INCREMENT    COMMENT '백신 번호',
   fv_type               VARCHAR(20)   NOT NULL COMMENT '백신 타입',
   fv_type_manuf_country   VARCHAR(20)   NOT NULL COMMENT '백신 제조국',
   fv_type_manuf_date      VARCHAR(20) NOT NULL COMMENT '백신 제조일',
   PRIMARY KEY(fv_type_no)
);

DESC tbl_fv_type;
SELECT * FROM tbl_fv_type;

INSERT INTO tbl_fv_type(fv_type, fv_type_manuf_country, fv_type_manuf_date) 
VALUES('A', '한국', '2023-01-10');

INSERT INTO tbl_fv_type(fv_type, fv_type_manuf_country, fv_type_manuf_date) 
VALUES('B', '미국', '2023-01-15');

INSERT INTO tbl_fv_type(fv_type, fv_type_manuf_country, fv_type_manuf_date) 
VALUES('C', '영국', '2023-02-07');

INSERT INTO tbl_fv_type(fv_type, fv_type_manuf_country, fv_type_manuf_date) 
VALUES('D', '스웨덴', '2023-01-17');

INSERT INTO tbl_fv_type(fv_type, fv_type_manuf_country, fv_type_manuf_date) 
VALUES('E', '덴마크', '2022-12-22');


-- 백신 접종처 테이블 생성
CREATE TABLE tbl_fv_location(
   fv_location_no            INT            NOT NULL    COMMENT '접종처 번호',
   fv_location_name         VARCHAR(20)    NOT NULL   COMMENT '접종처 이름',
   fv_location_addr         VARCHAR(30)      NOT NULL   COMMENT '접종처 주소',
   fv_location_phone         VARCHAR(20)    NOT NULL   COMMENT '접종처 연락처',
   fv_location_manager         VARCHAR(20)      NOT NULL   COMMENT '담당자 이름',
   primary key(fv_location_no)
);

ALTER TABLE tbl_fv_location 
MODIFY COLUMN fv_location_no VARCHAR(20) NOT NULL COMMENT '접종처 번호';

DESC tbl_fv_location;

INSERT INTO tbl_fv_location(fv_location_no, fv_location_name, fv_location_addr, 
                     fv_location_phone, fv_location_manager) 
VALUES(1001,'A접종처', 'AAA시 aaa구 471-013', '010-8410-8426', 'A담당');

INSERT INTO tbl_fv_location(fv_location_no, fv_location_name, fv_location_addr, 
                     fv_location_phone, fv_location_manager) 
VALUES(1002,'B접종처', 'BBB시 bbb구 789-123', '010-9147-8523', 'B담당');

INSERT INTO tbl_fv_location(fv_location_no, fv_location_name, fv_location_addr, 
                     fv_location_phone, fv_location_manager) 
VALUES(1003,'C접종처', 'CCC시 ccc구 456-789', '010-6901-4785', 'C담당');

INSERT INTO tbl_fv_location(fv_location_no, fv_location_name, fv_location_addr, 
                     fv_location_phone, fv_location_manager) 
VALUES(1004,'D접종처', 'DDD시 ddd구 120-345', '010-2369-0123', 'D담당');

INSERT INTO tbl_fv_location(fv_location_no, fv_location_name, fv_location_addr, 
                     fv_location_phone, fv_location_manager) 
VALUES(1005,'E접종처', 'EEE시 eee구 678-901', '010-4782-9514', 'E담당');

SELECT * FROM tbl_fv_location;
update tbl_fv_location set fv_location_no = '1001' where fv_location_name = 'A접종처';

-- 주민등록 번호 테이블 생성
CREATE TABLE tbl_social_number(
   ss_number      VARCHAR(20)      NOT NULL   COMMENT '주민등록 번호',
   ss_name         VARCHAR(20)      NOT NULL   COMMENT '이름',
   ss_addr         VARCHAR(30)      NOT NULL   COMMENT '주소',
   ss_phone      VARCHAR(20)      NOT NULL   COMMENT '연락처',
   ss_reg_date      TIMESTAMP               COMMENT '등록일',
   PRIMARY KEY(ss_number)
);

DESC tbl_social_number;

INSERT INTO tbl_social_number  
VALUES('720520-1018547', '홍길동', 'AAA시 BBB구 CCC동 123-10', '010-458-8541', '2022-10-15');

INSERT INTO tbl_social_number  
VALUES('710210-2084561', '박찬호', 'DDD시 EEE구 FFF동 456-20', '010-412-9514', '2022-10-16');

INSERT INTO tbl_social_number  
VALUES('941230-1075214', '박세리', 'GGG시 HHH구 III동 789-30', '010-958-7536', '2022-10-17');

INSERT INTO tbl_social_number  
VALUES('950215-2050128', '이병규', 'JJJ시 KKK구 LLL동 987-40', '010-528-1478', '2022-10-18');

INSERT INTO tbl_social_number  
VALUES('800710-1025698', '손흥민', 'MMM시 NNN구 OOO동 654-50', '010-296-5236', '2022-10-19');

SELECT * FROM tbl_social_number;
DELETE FROM tbl_social_number;

-- 접종 현황 테이블 생성
CREATE TABLE tbl_flu_vaccin_person(
   fvp_no         INT       AUTO_INCREMENT         COMMENT '접종자 번호',
   fvp_name      VARCHAR(20) NOT NULL            COMMENT '접종자 이름',
   ss_number      VARCHAR(20)   NOT NULL            COMMENT '주민등록 번호',
   fvp_mail      VARCHAR(30)   NOT NULL            COMMENT '접종자 메일',
   fvp_phone      VARCHAR(30)   NOT NULL            COMMENT '접종자 연락처',
   fv_location_no   VARCHAR(20)   NOT NULL            COMMENT '접종처 번호',
   fv_type         VARCHAR(20)   NOT NULL            COMMENT '백신 타입',
   fvp_reg_date   TIMESTAMP DEFAULT CURRENT_TIMESTAMP COMMENT '등록일',
   PRIMARY KEY(fvp_no)
);

ALTER TABLE tbl_flu_vaccin_person ADD COLUMN fvp_mod_date TIMESTAMP 
DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '수정일';

DESC tbl_flu_vaccin_person;
SELECT * FROM tbl_flu_vaccin_person;

SELECT * FROM tbl_flu_vaccin_person fp
JOIN tbl_fv_location fl
on fp.fv_location_no = fl.fv_location_no
join tbl_fv_type ft
on fp.fv_type = ft.fv_type 
WHERE fvp_name = '홍길동';

-- tbl_flu_vaccin_person + tbl_fv_location

SELECT * FROM tbl_flu_vaccin_person;
SELECT * FROM tbl_fv_location;


select * from tbl_flu_vaccin_person fp 
join tbl_fv_location fl 
on fp.fv_location_no = fl.fv_location_no 
join tbl_fv_type ft 
on fp.fv_type = ft.fv_type;




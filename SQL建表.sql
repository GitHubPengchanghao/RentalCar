--�û���
CREATE TABLE t_user(
  id NUMBER(11),
  username VARCHAR2(40) CONSTRAINT t_user_name_nn NOT NULL,
  password VARCHAR2(20) CONSTRAINT t_user_password_nn NOT NULL,
  sex NUMBER(1) DEFAULT 0,
  id_number VARCHAR2(18),
  tel VARCHAR2(11),
  addr VARCHAR2(100),
  type NUMBER(1) DEFAULT 0,
  CONSTRAINT t_user_id_pk PRIMARY KEY(id),
  CONSTRAINT t_user_username_uk UNIQUE(username),
  CONSTRAINT t_user_sex_ck CHECK(sex=0 OR sex=1),
  CONSTRAINT t_user_id_number_uk UNIQUE(id_number)
);

--Ʒ�Ʊ�
CREATE TABLE t_brand(
  id NUMBER(11),
  name VARCHAR2(50),
  CONSTRAINT t_brand_id_pk PRIMARY KEY(id),
  CONSTRAINT t_brand_name_uk UNIQUE(name)
);

--���ͱ�
CREATE TABLE t_category(
  id NUMBER(11),
  name VARCHAR2(50),
  CONSTRAINT t_category_id_pk PRIMARY KEY(id),
  CONSTRAINT t_category_name_uk UNIQUE(name)
);

--������
--model:�����ͺţ�t_comments:����
CREATE TABLE t_car(
  id NUMBER(11),
  car_number VARCHAR2(10),
  brand_id NUMBER(11),
  model VARCHAR2(40),
  color VARCHAR2(20),
  category_id NUMBER(11),
  t_comments VARCHAR2(100),
  price NUMBER(11,2),
  rent NUMBER(9,2),
  status NUMBER(1) DEFAULT 0,
  useable NUMBER(1) DEFAULT 0,
  CONSTRAINT t_car_id_pk PRIMARY KEY(id),
  CONSTRAINT t_car_number_uk UNIQUE(car_number),
  CONSTRAINT t_car_brand_id_fk FOREIGN KEY(brand_id) REFERENCES t_brand(id),
  CONSTRAINT t_car_category_id_fk FOREIGN KEY(category_id) REFERENCES t_category(id),
  CONSTRAINT t_car_status_ck CHECK(status=0 OR status=1),
  CONSTRAINT t_car_useable_ck CHECK(useable=0 OR useable=1)
);

ALTER TABLE t_car DROP CONSTRAINT t_car_brand_id_fk;
ALTER TABLE t_car DROP CONSTRAINT t_car_category_id_fk;
ALTER TABLE t_record DROP CONSTRAINT t_record_user_id_fk;
ALTER TABLE t_record DROP CONSTRAINT t_record_car_id_fk;

alter table t_user drop constraint t_user_id_pk;
alter table t_brand drop constraint t_brand_id_pk;
alter table t_category drop constraint t_category_id_pk;
alter table t_car drop constraint t_car_id_pk;
alter table t_record drop constraint t_record_id_pk;

drop table t_user;
drop table t_brand;
drop table t_category;
drop table t_record;
drop table t_car;

--���ޱ�
CREATE TABLE t_record(
  id NUMBER(11),
  user_id NUMBER(11),
  car_id NUMBER(11),
  start_date DATE,
  return_date DATE,
  payment NUMBER(9,2),
  CONSTRAINT t_record_id_pk PRIMARY KEY(id),
  CONSTRAINT t_record_user_id_fk FOREIGN KEY(user_id) REFERENCES t_user(id),
  CONSTRAINT t_record_car_id_fk FOREIGN KEY(car_id) REFERENCES t_car(id)
);

select * from t_user;
select * from t_brand;
select * from t_category;
select * from t_car;
select * from t_record;

--��Ӳ�������
INSERT INTO t_user VALUES(1,'xiaoming','111111',0,'15647641312345','1515445554','����',0);
INSERT INTO t_user VALUES(2,'tomy','111111',0,'15647641312346','1515445555','�ձ�',0);
INSERT INTO t_user VALUES(3,'lucy','111111',0,'15647641312347','1515445556','�Ϻ�',0);
INSERT INTO t_user VALUES(4,'samy','111111',0,'15647641312348','1515445557','����',0);
INSERT INTO t_user VALUES(5,'leoy','111111',0,'15647641312349','1515445558','����',0);
INSERT INTO t_user VALUES(6,'marry','111111',0,'15647641312340','1515445559','�Ͼ�',1);
INSERT INTO t_user (id,username,password,type) VALUES(7,'admin','admin',1);

INSERT INTO t_brand VALUES(1,'����');
INSERT INTO t_brand VALUES(2,'����');
INSERT INTO t_brand VALUES(3,'�µ�');
INSERT INTO t_brand VALUES(4,'����');
INSERT INTO t_brand VALUES(5,'����');

INSERT INTO t_category VALUES(1,'������');
INSERT INTO t_category VALUES(2,'������');
INSERT INTO t_category VALUES(3,'SUV(+)');
INSERT INTO t_category VALUES(4,'��Ӣ��');

INSERT INTO t_car (id,car_number,brand_id,model,color,category_id,t_comments,price,rent,status,useable)
VALUES(1,'��A11C32',2,'����','��ɫ',2,'�Զ�1.6L',140000,72,0,0);
INSERT INTO t_car 
VALUES(2,'��B6888',2,';��','��ɫ',3,'�Զ�1.8T',220000,200,0,0);
INSERT INTO t_car 
VALUES(3,'��A1686',3,'A4L-','��ɫ',4,'�Զ�2.0T',400000,359,0,0);
INSERT INTO t_car 
VALUES(4,'��D11C32',1,'308-','��ɫ',1,'�ֶ�1.6L',130000,56,0,0);
INSERT INTO t_car 
VALUES(5,'��E11C99',1,'308S','��ɫ',2,'�Զ�1.2T',160000,70,0,0);
INSERT INTO t_car 
VALUES(6,'��F11C21',2,'�߶���','��ɫ',1,'�Զ�1.4T',200000,69,0,0);
INSERT INTO t_car 
VALUES(7,'��F1324',5,'320Li','��ɫ',4,'�Զ�2.0T',380000,500,0,0);
INSERT INTO t_car 
VALUES(8,'��F6666',4,'B200','��ɫ',4,'�Զ�1.6T',320000,300,0,0);

INSERT INTO t_record 
VALUES(1,2,2,to_date('2017-01-24','yyyy-mm-dd'),to_date('2017-01-26','yyyy-mm-dd'),400);
INSERT INTO t_record 
VALUES(2,3,4,to_date('2017-02-20','yyyy-mm-dd'),to_date('2017-02-28','yyyy-mm-dd'),448);
INSERT INTO t_record 
VALUES(3,4,5,to_date('2017-03-01','yyyy-mm-dd'),to_date('2017-03-03','yyyy-mm-dd'),210);
INSERT INTO t_record 
VALUES(4,1,1,to_date('2017-03-02','yyyy-mm-dd'),to_date('2017-03-06','yyyy-mm-dd'),288);


COMMIT;

CREATE SEQUENCE t_user_id_seq START WITH 8 INCREMENT BY 1;
CREATE SEQUENCE t_brand_id_seq START WITH 6 INCREMENT BY 1;
CREATE SEQUENCE t_category_id_seq START WITH 5 INCREMENT BY 1;
CREATE SEQUENCE t_car_id_seq START WITH 9 INCREMENT BY 1;
CREATE SEQUENCE t_record_id_seq START WITH 5 INCREMENT BY 1;

DROP SEQUENCE t_user_id_seq;
DROP SEQUENCE t_brand_id_seq;
DROP SEQUENCE t_category_id_seq;
DROP SEQUENCE t_car_id_seq;
DROP SEQUENCE t_record_id_seq;

select t_car.id,t_car.model,t_car.t_comments,t_brand.name,t_category.name,t_car.rent,t_car.status,t_car.useable from
t_car,t_brand,t_category where t_brand.id = t_car.brand_id and t_category.id = t_car.category_id order by t_car.id asc;

SELECT t_record.id,t_car.id,t_car.model,t_record.payment,t_car.t_comments,t_brand.name,t_category.name,t_record.start_date,t_record.return_date
FROM t_record,t_car,t_brand,t_category WHERE t_brand.id = t_car.id AND t_category.id = t_car.id AND t_car.id = t_record.car_id
AND t_record.user_id = 1 ORDER BY t_record.id DESC;
       
SELECT t_car.id,t_car.model,t_car.t_comments,t_brand.name,'(',t_brand.id,')',t_category.name,'(',t_category.id,')',t_car.rent,'/��',t_car.status 
FROM t_car,t_brand,t_category WHERE t_category.id = 1 AND t_brand.id = t_car.brand_id AND t_category.id = t_car.category_id ORDER BY t_car.id ASC;
        
SELECT t_record.id,t_car.id,t_car.model,t_record.payment,t_car.t_comments,t_brand.name,t_category.name,t_record.start_date,t_record.return_date
FROM t_record,t_car,t_brand,t_category,t_user WHERE t_brand.id = t_car.brand_id AND t_category.id = t_car.category_id AND t_record.user_id = t_user.id
AND t_user.username = 'xiaoming' AND t_car.id = t_record.car_id ORDER BY t_record.id ASC;

select t_record_id_seq.currval from dual;
select t_user_id_seq.currval from dual;
select t_brand_id_seq.currval from dual;
select t_category_id_seq.currval from dual;
select t_car_id_seq.currval from dual;


       


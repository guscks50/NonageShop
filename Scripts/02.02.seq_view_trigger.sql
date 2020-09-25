-- 접속자 확인
SELECT USER FROM DUAL;


/*****************************************
VIEW 생성 
******************************************/

-- 신상품
create or replace view new_pro_view
as
SELECT no, name, saleprice, image 
FROM (select rownum, no, name, saleprice, image 
      from product  
      where del_yn='y' 
      order by reg_date desc)
where  rownum <=4;


-- 베스트 상품
create or replace view best_pro_view
as
select no, name, saleprice, image 
from( select rownum, no, name, saleprice, image 
      from product  
      where best_yn='y' 
      order by reg_date desc)
where  rownum <=4;


-- 장바구니(cart)
create or replace view cart_view
as
SELECT c.NO , c.MEMBERID , c.PNO , m.NAME mname, p.NAME pname, c.QUANTITY, c.REG_DATE, p.SALEPRICE , c.RESULT 
  FROM cart c JOIN MEMBER m ON c.MEMBERID = m.ID JOIN PRODUCT p ON c.PNO =p."NO" 
 WHERE result = '1';


--order view
create or replace view order_view
as
select d.NO dno, o.NO ono, o.id mid, o.ORDER_DATE , d.PNO pno, d.quantity, m.name mname,
       m.zip_num, m.address, m.phone, p.name pname, p.SALEPRICE , d.RESULT_YN result   
  from orders o JOIN order_detail d ON o.NO = d.ONO JOIN member m ON o.ID =m.ID 
       JOIN product p ON d.PNO = p.NO;

/*****************************************
시퀀스 삭제
******************************************/
DROP SEQUENCE PRODUCT_NO_SEQ; 
DROP SEQUENCE CART_NO_SEQ;
DROP SEQUENCE ORDERS_NO_SEQ;
DROP SEQUENCE ORDER_DETAIL_NO_SEQ;
DROP SEQUENCE QNA_NO_SEQ;
  
  
/*****************************************
시퀀스 생성
******************************************/

-- product(no), 
CREATE SEQUENCE PRODUCT_NO_SEQ
	START WITH 1
	INCREMENT BY 1;

CREATE OR REPLACE TRIGGER TRI_PRODUCT_NO_SEQ
BEFORE INSERT ON PRODUCT
FOR EACH ROW 
BEGIN 
	IF Inserting AND :NEW.NO IS NULL THEN 
		SELECT PRODUCT_NO_SEQ.NEXTVAL INTO :NEW.NO FROM DUAL;
	END IF;
END; 


--cart(no),
CREATE SEQUENCE CART_NO_SEQ
	START WITH 1
	INCREMENT BY 1;

CREATE OR REPLACE TRIGGER TRI_CART_NO_SEQ
BEFORE INSERT ON CART
FOR EACH ROW 
BEGIN 
	IF Inserting AND :NEW.NO IS NULL THEN 
		SELECT CART_NO_SEQ.NEXTVAL INTO :NEW.NO FROM DUAL;
	END IF;
END; 

--orders
CREATE SEQUENCE ORDERS_NO_SEQ
	START WITH 1
	INCREMENT BY 1;

CREATE OR REPLACE TRIGGER TRI_ORDERS_NO_SEQ
BEFORE INSERT ON ORDERS
FOR EACH ROW 
BEGIN 
	IF Inserting AND :NEW.NO IS NULL THEN 
		SELECT ORDERS_NO_SEQ.NEXTVAL INTO :NEW.NO FROM DUAL;
	END IF;
END; 

--order_detail(no),
CREATE SEQUENCE ORDER_DETAIL_NO_SEQ
	START WITH 1
	INCREMENT BY 1;

CREATE OR REPLACE TRIGGER TRI_ORDER_DETAIL_NO_SEQ
BEFORE INSERT ON ORDER_DETAIL
FOR EACH ROW 
BEGIN 
	IF Inserting AND :NEW.NO IS NULL THEN 
		SELECT ORDER_DETAIL_NO_SEQ.NEXTVAL INTO :NEW.NO FROM DUAL;
	END IF;
END; 

--qna(no)
CREATE SEQUENCE QNA_NO_SEQ
	START WITH 1
	INCREMENT BY 1;

CREATE OR REPLACE TRIGGER TRI_QNA_NO_SEQ
BEFORE INSERT ON QNA
FOR EACH ROW 
BEGIN 
	IF Inserting AND :NEW.NO IS NULL THEN 
		SELECT QNA_NO_SEQ.NEXTVAL INTO :NEW.NO FROM DUAL;
	END IF;
END; 



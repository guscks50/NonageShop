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



/*****************************************
시퀀스 생성 
******************************************/

-- product(no), 
DROP SEQUENCE PRODUCT_NO_SEQ;
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
DROP SEQUENCE CART_NO_SEQ;
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


--order_detail(no),
DROP SEQUENCE ORDER_DETAIL_NO_SEQ;
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
DROP SEQUENCE QNA_NO_SEQ;
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



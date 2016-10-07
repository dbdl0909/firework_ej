--------------------------------------------------------
--  파일이 생성됨 - 수요일-9월-21-2016   
--------------------------------------------------------
--------------------------------------------------------
--  DDL for Table GOODS
--------------------------------------------------------

  CREATE TABLE "FIREWORK"."GOODS" 
   (	"G_CODE" VARCHAR2(20 BYTE), 
	"G_NAME" VARCHAR2(20 BYTE), 
	"G_ID" VARCHAR2(20 BYTE), 
	"G_CATE" VARCHAR2(20 BYTE), 
	"G_SANGSE" VARCHAR2(80 BYTE), 
	"G_PRICE" NUMBER, 
	"G_DATE" DATE, 
	"G_AGREE" CHAR(1 BYTE) DEFAULT 'N', 
	"G_IMAGE" VARCHAR2(500 BYTE)
   ) SEGMENT CREATION IMMEDIATE 
  PCTFREE 10 PCTUSED 40 INITRANS 1 MAXTRANS 255 NOCOMPRESS LOGGING
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "SYSTEM" ;
REM INSERTING into FIREWORK.GOODS
SET DEFINE OFF;
Insert into FIREWORK.GOODS (G_CODE,G_NAME,G_ID,G_CATE,G_SANGSE,G_PRICE,G_DATE,G_AGREE,G_IMAGE) values ('gcode_2','b','id002','b','
안녕',2,to_date('16/09/19','RR/MM/DD'),'Y',null);
Insert into FIREWORK.GOODS (G_CODE,G_NAME,G_ID,G_CATE,G_SANGSE,G_PRICE,G_DATE,G_AGREE,G_IMAGE) values ('gcode_1','a','id002','a','dddd',1,to_date('16/09/19','RR/MM/DD'),'Y',null);
Insert into FIREWORK.GOODS (G_CODE,G_NAME,G_ID,G_CATE,G_SANGSE,G_PRICE,G_DATE,G_AGREE,G_IMAGE) values ('gcode_3','1','id002','1','7',1,to_date('16/09/19','RR/MM/DD'),'N',null);
Insert into FIREWORK.GOODS (G_CODE,G_NAME,G_ID,G_CATE,G_SANGSE,G_PRICE,G_DATE,G_AGREE,G_IMAGE) values ('gcode_4','asdf','id002','asdf','asdf',11234,to_date('16/09/19','RR/MM/DD'),'N',null);
Insert into FIREWORK.GOODS (G_CODE,G_NAME,G_ID,G_CATE,G_SANGSE,G_PRICE,G_DATE,G_AGREE,G_IMAGE) values ('gcode_5','1','id002','qwer','qwewe',123,to_date('16/09/19','RR/MM/DD'),'N',null);
Insert into FIREWORK.GOODS (G_CODE,G_NAME,G_ID,G_CATE,G_SANGSE,G_PRICE,G_DATE,G_AGREE,G_IMAGE) values ('gcode_6','asdf','id002','asdf','asdf',123,to_date('16/09/19','RR/MM/DD'),'N',null);
Insert into FIREWORK.GOODS (G_CODE,G_NAME,G_ID,G_CATE,G_SANGSE,G_PRICE,G_DATE,G_AGREE,G_IMAGE) values ('gcode_7','asdf','id002','asdf','asdf',12344,to_date('16/09/19','RR/MM/DD'),'N',null);
Insert into FIREWORK.GOODS (G_CODE,G_NAME,G_ID,G_CATE,G_SANGSE,G_PRICE,G_DATE,G_AGREE,G_IMAGE) values ('gcode_8','a','id002','s','d',12,to_date('16/09/19','RR/MM/DD'),'N','D:\leeeunjin\firework\.metadata\.plugins\org.eclipse.wst.server.core\tmp0\wtpwebapps\minimall\goodsImage\test4.PNG');
Insert into FIREWORK.GOODS (G_CODE,G_NAME,G_ID,G_CATE,G_SANGSE,G_PRICE,G_DATE,G_AGREE,G_IMAGE) values ('gcode_9','1','id002','1','1',1,to_date('16/09/20','RR/MM/DD'),'Y','D:\leeeunjin\firework\.metadata\.plugins\org.eclipse.wst.server.core\tmp0\wtpwebapps\minimall\goodsImage\test5.PNG');
--------------------------------------------------------
--  DDL for Index SYS_C007002
--------------------------------------------------------

  CREATE UNIQUE INDEX "FIREWORK"."SYS_C007002" ON "FIREWORK"."GOODS" ("G_CODE") 
  PCTFREE 10 INITRANS 2 MAXTRANS 255 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "SYSTEM" ;
--------------------------------------------------------
--  Constraints for Table GOODS
--------------------------------------------------------

  ALTER TABLE "FIREWORK"."GOODS" ADD PRIMARY KEY ("G_CODE")
  USING INDEX PCTFREE 10 INITRANS 2 MAXTRANS 255 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "SYSTEM"  ENABLE;
  ALTER TABLE "FIREWORK"."GOODS" MODIFY ("G_DATE" NOT NULL ENABLE);
  ALTER TABLE "FIREWORK"."GOODS" MODIFY ("G_PRICE" NOT NULL ENABLE);
  ALTER TABLE "FIREWORK"."GOODS" MODIFY ("G_SANGSE" NOT NULL ENABLE);
  ALTER TABLE "FIREWORK"."GOODS" MODIFY ("G_CATE" NOT NULL ENABLE);
  ALTER TABLE "FIREWORK"."GOODS" MODIFY ("G_ID" NOT NULL ENABLE);
  ALTER TABLE "FIREWORK"."GOODS" MODIFY ("G_NAME" NOT NULL ENABLE);
  ALTER TABLE "FIREWORK"."GOODS" MODIFY ("G_CODE" NOT NULL ENABLE);

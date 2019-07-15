drop table COUNTRIES cascade constraints;

/*==============================================================*/
/* Table: COUNTRIES                                             */
/*==============================================================*/
create table COUNTRIES 
(
   ITEM_ID              INT                  not null,
   ITEM_NM              VARCHAR2(200)        not null,
   ITEM_CD              VARCHAR2(15)         not null,
   constraint PK_COUNTRIES primary key (ITEM_ID)
);

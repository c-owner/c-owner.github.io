---
layout: post
title:  "Team-Project-day03"
date: 2021-03-19 10:10:00 +0900
comments: true # 코멘트 허용
categories: Lecture jsp

---



1. 팀장   - 박태호
2. 팀명
3. 주제   - 이벤트 모아 / / 여행 
4. 기획
5. ERD(DB 설계)
6. 템플릿(프론트)
7. 백단 설계
8. 업무 분담 



-------



쿼츠 라이브러리



### 이벤트 모아

페이퍼리스

소상공인 자영업을 위한..

지역경제 활성화 



신규 회원 포인트 300포인트 

게시글 등록할때마다 





---

## DB 

#### 유저 

```sql
create table TABLE_USER (
  memberId varchar(100) not null,
  memberPw varchar(100) not null,
  memberEmail varchar(200) not null,
  memberEmailHash varchar(200),
  memberName  varchar(100),
  memberZipcode    varchar(20) not null,
  memberAddress    varchar(300)not null,
  memberAddressDETAIL varchar(300),
  memberAddressEtc  varchar(100),
  constraint event_user_pk primary key (memberId)
);

select * from table_user;
```

#### 게시판

```sql
-- board --------

CREATE SEQUENCE BOARD_SEQ;

CREATE TABLE TABLE_BOARD(
	BOARDNUM INT,
	BOARDTITLE varchar(1000),
	BOARDCONTENT TEXT,
	BOARDID varchar(100),
	BOARDDATE DATE,
	CONSTRAINT BOARD_PK PRIMARY KEY(BOARDNUM),
	CONSTRAINT BOARD_FK FOREIGN KEY(BOARDID) REFERENCES TABLE_USER(MEMBERID)
);

ALTER TABLE TABLE_BOARD ADD READCOUNT INT DEFAULT 0;

SELECT * FROM TABLE_BOARD ORDER BY 1 DESC;
SELECT * FROM table_user;

```

```sql
--첨부파일 SQL

CREATE TABLE TABLE_FILES (	
	FILENAME varchar(2000),
	BOARDNUM int,
	CONSTRAINT FILES_PK PRIMARY KEY(FILENAME),
	CONSTRAINT FILES_FK FOREIGN KEY(BOARDNUM) REFERENCES TABLE_BOARD(BOARDNUM)
);


SELECT * FROM TABLE_FILES;
```





#### 댓글

```sql
-- comment Table (댓글)
CREATE SEQUENCE REPLY_SEQ;

CREATE TABLE TABLE_REPLY(
   REPLYNUM INT,
   BOARDNUM INT,
   MEMBERID VARCHAR(100),
   REPLYCONTENT VARCHAR(4000),
   CONSTRAINT REPLY_PK PRIMARY KEY(REPLYNUM),
   CONSTRAINT REPLY_BOARD_FK FOREIGN KEY(BOARDNUM) REFERENCES TABLE_BOARD(BOARDNUM),
   CONSTRAINT REPLY_MEMBER_FK FOREIGN KEY(MEMBERID) REFERENCES TABLE_USER(MEMBERID)
);

ALTER TABLE TABLE_REPLY DROP CONSTRAINT REPLY_BOARD_FK;

ALTER TABLE TABLE_REPLY ADD CONSTRAINT REPLY_BOARD_FK
FOREIGN KEY(BOARDNUM) REFERENCES TABLE_BOARD(BOARDNUM)
ON DELETE CASCADE;

SELECT * FROM TABLE_REPLY;
```



-----------





## 게시글 폼 



컨텐츠 내용 ( 사진 & 부가설명 )

지도 api ( 상세 주소 ) 



## 회원가입

이메일 인증

(중복 검사 - 아이디 중복검사 )






---
layout: post
title: "Oracle 설치(for Windows)"
date: 2020-12-28 18:00:00 +0900
comments: true # 코멘트 허용
---



## Oracle Database 11g Express Edition

[Download](https://www.oracle.com/technetwork/database/database-technologies/express-edition/downloads/xe-prior-releases-5172097.html)

**설치 -> 경로 지정 -> 포트 설정 **

---

Corner 포트 세팅

**TNS Port : 1025**

**MTS Port : 2030**

**HTTP Port : 8080**

---

원래의 포트

**TNS : 1521**

**MTS : 2030**

**HTTP Port :8080**

---

암호 : [* * * *]

---

dir : H:\Oracleexe\app\oracle\product\11.2.0\server\

---

**SQL Developer 설치**

[SQL Developer Download](https://www.oracle.com/technetwork/developer-tools/sql-developer/downloads/index.html)

---



### Oracle 설정

#### CMD :

```
 sqlplus / as sysdba
```

- 다음과 같은 순서로 명령어를 실행

(sys 계정으로 로그인하는 명령어, 여기서 oracle은 데이터베이스를 설치할 때 설정해주었던 비밀번호)

```
alter user corner identified by password account unlock
```

- corner 계정의 비밀번호를 password로 설정하고 잠겨있던 계정의 잠금을 해제하는 명령어이다.

  



---

```java
if sql Developer 오류 시
```

### SQL Developer 실행 시 java path, msvcr100.dll 관련 오류가 나올 때

Java\jdk1.8.0_271\jre\bin 경로에 넣어주면 된다.



### SQL Developer JDK 경로 변경하고 싶을 때

- 사용자\AppData\Roaming\sqldeveloper\버전\product.conf 파일을 메모장이나 코드 프로그램으로 열어서 
  경로에 맞게 지정, ex) java\jdk1.8.0_271



---


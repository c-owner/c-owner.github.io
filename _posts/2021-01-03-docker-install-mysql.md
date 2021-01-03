---
layout: post
title:  "(mac os)Docker install MySQL"
date: 2021-01-03 16:16:00 +0900
categories: Database MySQL
---



### 1. 도커 이미지 다운받기

```
docker pull mariadb (or mysql)
```

### 2. 컨테이너 실행

localhost의 3306포트 사용시 바로 mysql포트로 연결한다.

```
docker run -d -p 3306:3306 -e MYSQL_ROOT_PASSWORD=1234 --name mariadb mariadb
```

### 3. 도커 확인

```
docker container ls 
```

### 4. 접속

```
docker exec -i -t mariadb bash 
mysql -uroot -p1234
```

### 5. my.cnf 수정

vi 편집기를 먼저 설치해야 한다. 설치후 my.cnf를 적절히 수정한다.

```
apt-get update
apt-get install vim 
vi /etc/mysql/my.cnf 
```

### 5. 컨테이너 재시작

```
docker stop 컨테이너명
docker start 컨테이너명
```





### 6. My.cnf

```
[mysqld]
character-set-server=utf8
collation-server=utf8_general_ci
init_connect=SET collation_connection=utf8_general_ci
init_connect=SET NAMES utf8
[client]
default-character-set=utf8
[mysql]
default-character-set=utf8
```


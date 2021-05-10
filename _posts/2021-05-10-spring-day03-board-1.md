---
layout: post
title:  "스프링(Spring) -(1) 게시판 만들기 - MyBatis + Oracle DB 연동"
date: 2021-05-10 09:00:21 +0900
categories: JAVA Lecture

---



![img](https://blog.kakaocdn.net/dn/beLAj5/btq4sqhfBul/fx3i8EMJYsZyKrSScGoUkk/img.png)

```xml
<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN" "http://mybatis.org/dtd/mybatis-3-mapper.dtd">

<mapper namespace="com.koreait.mapper.BoardMapper">
	<select id="getList" resultType="com.koreait.domain.BoardVO">
		<![CDATA[ 
			SELECT * FROM TBL_BOARD WHERE BNO > 0
		]]>
	</select>
</mapper>
```



## (1) 게시판 만들기 - 프로젝트 설정



\1. 프로젝트 생성 - Legacy, Spring MVC로 생성.





테이블 생성

```sql
-- 
CREATE SEQUENCE SEQ_BOARD;

DROP TABLE TBL_BOARD;
CREATE TABLE TBL_BOARD(
	BNO NUMBER(10),
	TITLE VARCHAR2(200) NOT NULL,
	CONTENT VARCHAR2(2000) NOT NULL,
	WRITER VARCHAR2(200) NOT NULL,
	REGDATE DATE DEFAULT SYSDATE,
	UPDATEDATE DATE DEFAULT SYSDATE
);

ALTER TABLE TBL_BOARD ADD CONSTRAINT PK_BOARD PRIMARY KEY(BNO);

INSERT INTO TBL_BOARD
(BNO, TITLE, CONTENT, WRITER, REGDATE)
VALUES(SEQ_BOARD.NEXTVAL, '테스트 제목', '테스트 내용', 'user001', SYSDATE
);

SELECT * FROM TBL_BOARD;
```



VO 생성 

BoardVO.java

```java
package com.koreait.domain;

import lombok.Data;

@Data
public class BoardVO { 
	// VO는 컬럼에 매핑된 것을 사용할 때 
	// DTO는 사용자 정의 모델된 것을 사용할 때
	
	private Long bno;
	private String title;
	private String content;
	private String writer;
	private String regDate;
	private String updateDate;
	
	
	
}
```



![img](https://blog.kakaocdn.net/dn/60tpu/btq4pAStUoS/vQcDLKEvcW4ykenKTw35p1/img.png)

아웃라인 확인





BoardMapper.java (Interface)

```java
package com.koreait.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Select;

import com.koreait.domain.BoardVO;

public interface BoardMapper {
	
//	@Select("SELECT * FROM TBL_BOARD WHERE BNO > 0")
	public List<BoardVO> getList();
	
}
```







BoardMapperTest.java

```java
package com.koreait.mapper;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import lombok.Setter;
import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class) //테스트 코드가 스프링을 실행
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")//지정된 클래스나 문자열을 이용해서 필요한 객체들을 스프링 내에 객체로 등록
@Log4j
public class BoardMapperTests {
	
	@Setter(onMethod_ = @Autowired)
	private BoardMapper mapper;
	
	@Test
	public void testGetList() {
		mapper.getList().forEach(board -> log.info(board));
	}
}
```







root-context.xml 에 스캔 추가

```xml
<mybatis-spring:scan base-package="com.koreait.mapper" />
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
   xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
   xmlns:context="http://www.springframework.org/schema/context"
   xmlns:mybatis-spring="http://mybatis.org/schema/mybatis-spring"
   xsi:schemaLocation="http://mybatis.org/schema/mybatis-spring http://mybatis.org/schema/mybatis-spring-1.2.xsd
      http://www.springframework.org/schema/beans https://www.springframework.org/schema/beans/spring-beans.xsd
      http://www.springframework.org/schema/context http://www.springframework.org/schema/context/spring-context-4.3.xsd">

   <!-- Root Context: defines shared resources visible to all other web components -->
   
   
   <!-- HikariConfig hikariConfig = new HikariConfig() -->
   <!-- hikariConfig.setDriverClassName("") -->
   <!-- hikariConfig.jdbcUrl("") -->
   <!-- hikariConfig.username("") -->
   <!-- hikariConfig.password("") -->
   <bean id="hikariConfig" class="com.zaxxer.hikari.HikariConfig">
      <!-- <property name="driverClassName" value="oracle.jdbc.driver.OracleDriver"/> 
         <property name="jdbcUrl" value="jdbc:oracle:thin:@localhost:1521:XE"/> -->
      <property name="driverClassName"
         value="net.sf.log4jdbc.sql.jdbcapi.DriverSpy" />
      <property name="jdbcUrl"
         value="jdbc:log4jdbc:oracle:thin:@localhost:1521:XE" />
      <property name="username" value="hr" />
      <property name="password" value="hr" />
   </bean>

   <!-- DataSource dataSource = new DataSource(hikariConfig) -->
   <bean id="dataSource" class="com.zaxxer.hikari.HikariDataSource"
      destroy-method="close">
      <constructor-arg ref="hikariConfig" />
   </bean>
   <bean id="sqlSessionFactory"
      class="org.mybatis.spring.SqlSessionFactoryBean">
      <property name="dataSource" ref="dataSource" />
<!--       <property name="mapperLocations"
         value="classpath:/META-INF/com/koreait/mappera/**/*.xml" />
 -->   
<!-- <property name="configLocation" value="classpath:/META-INF/com/koreait/config/MapperConfig.xml"/> -->
 	</bean>
 	
   <mybatis-spring:scan base-package="com.koreait.mapper" />
 
</beans>
```





BoardMapper.xml 추가

```xml
<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN" "http://mybatis.org/dtd/mybatis-3-mapper.dtd">

<mapper namespace="com.koreait.mapper.BoardMapper">
	<select id="getList" resultType="com.koreait.domain.BoardVO">
		<![CDATA[ 
			SELECT * FROM TBL_BOARD WHERE BNO > 0
		]]>
	</select>
    
    <insert id="insert">
		INSERT INTO TBL_BOARD (BNO, TITLE, CONTENT, WRITER) 
		VALUES(SEQ_BOARD.NEXTVAL, #{title}, #{content}, #{writer})
	</insert>
    
</mapper>
```

CDATA를 사용하는 이유는  ' > ' 부등호 연산자를 이용하기 위해서 이다.



BoardMapperTests.java

```java
	@Test
	public void testInsert() {
		BoardVO board = new BoardVO(); 
		// 메소드 안에서는 의존성 주입(DI)가 되지않기 때문이다. 
		// 그렇기 때문에 전역변수에만 주입하는 것이다.
		// 메소드 안에서는 new로 할당한다.
		board.setTitle("새로 작성한 글 제목");
		board.setContent("새로 작성한 글 내용");
		board.setWriter("newbie");
		
		mapper.insert(board);
		log.info("board .... : "+ board);
		
	}
	
```





```xml
	<!--
		PK값을 미리 SQL을 통해서 처리하는 방식
		SQL을 한 번 더 실행하는 부담이 있기는 하지만 자동으로 추가되는 PK값을 확인해야 하는 상황에서는 
		유용하게 사용될 수 있다.
	-->
	<insert id="insertSelectKey_bno">
	<!-- BEFORE라는 속성은 먼저 실행되기 위함이다.-->
		<selectKey keyProperty="bno" order="BEFORE" resultType="long">
			SELECT SEQ_BOARD.NEXTVAL FROM DUAL
		</selectKey>
		INSERT INTO TBL_BOARD (BNO, TITLE, CONTENT, WRITER) 
		VALUES(#{bno}, #{title}, #{content}, #{writer})
	</insert>
```





BoardMapper.java (인터페이스)

```java
// read() 선언 후 테스트 : 게시글 상세보기 
	public void read(long bno);
    
    //delete() 선언 후 테스트 : 게시글 삭제
	// 게시글 삭제 시 1 이상의 값 리턴 , 없으면 0 리턴
	public boolean delete(long bno);
	
```



 mapper.xml

```xml
	<select id="read">
		SELECT * FROM TBL_BOARD WHERE bno = #{bno} 
	</select>
    
	  <delete id="delete">
		DELETE FROM TBL_BOARD WHERE bno = #{bno}
	</delete>
```



```java
	@Test
	public void delete() {
		if(mapper.delete(2l) > 0) {
			log.info("check : 성공 ");
		} else {
			log.info("check : 실패 ");
		}
	}
```

------





**전체 코드**



root-context.xml



```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
	xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
	xmlns:context="http://www.springframework.org/schema/context"
	xmlns:mybatis-spring="http://mybatis.org/schema/mybatis-spring"
	xsi:schemaLocation="http://mybatis.org/schema/mybatis-spring http://mybatis.org/schema/mybatis-spring-1.2.xsd
		http://www.springframework.org/schema/beans https://www.springframework.org/schema/beans/spring-beans.xsd
		http://www.springframework.org/schema/context http://www.springframework.org/schema/context/spring-context-4.3.xsd">
	
	<!-- Root Context: defines shared resources visible to all other web components -->
	
	
	<!-- Root Context: defines shared resources visible to all other web components -->
	<!-- HikariConfig hikariConfig = new HikariConfig(); -->
	<!-- hikariConffig.setDriverClassName(""); -->
	<!-- hikariConffig.jdbcUrl(""); -->
	<!-- hikariConffig.username(""); -->
	<!-- hikariConffig.password(""); -->
	<bean id="hikariConfig" class="com.zaxxer.hikari.HikariConfig">
		<!-- <property name="driverClassName" value="oracle.jdbc.driver.OracleDriver"/>
		<property name="jdbcUrl" value="jdbc:oracle:thin:@132.226.169.45:1521:XE"/> -->
		<property name="driverClassName" value="net.sf.log4jdbc.sql.jdbcapi.DriverSpy"/>
		<property name="jdbcUrl" value="jdbc:log4jdbc:oracle:thin:@localhost:1521:XE"/>
		<property name="username" value="hr"/>
		<property name="password" value="hr"/>
	</bean>
	
	<!-- DataSource dataSource = new DataSource(hikariConfig) -->
	<bean id="dataSource" class="com.zaxxer.hikari.HikariDataSource" destroy-method="close">
		<constructor-arg ref="hikariConfig"/>
	</bean>
	
	<bean id="sqlSessionFactory" class="org.mybatis.spring.SqlSessionFactoryBean">
		<property name="dataSource" ref="dataSource"/>
		<property name="mapperLocations" value="classpath:/META-INF/com/koreait/mapper/**/*.xml"/>
		<!-- <property name="configLocation" value="classpath:/META-INF/com/koreait/config/MapperConfig.xml"/> -->
	</bean>
	
	<mybatis-spring:scan base-package="com.koreait.mapper"/>
	
</beans>
```





log4j.xml

```xml
<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE log4j:configuration SYSTEM "http://logging.apache.org/log4j/1.2/apidocs/org/apache/log4j/xml/doc-files/log4j.dtd">
<log4j:configuration xmlns:log4j="http://jakarta.apache.org/log4j/">

	<!-- Appenders -->
	<appender name="console" class="org.apache.log4j.ConsoleAppender">
		<param name="Target" value="System.out" />
		<layout class="org.apache.log4j.PatternLayout">
			<param name="ConversionPattern" value="%-5p: %c - %m%n" />
		</layout>
	</appender>
	
	<!-- Application Loggers -->
	<logger name="com.koreait.controller">
		<level value="info" />
	</logger>
	
	<!-- 3rdparty Loggers -->
	<logger name="org.springframework.core">
		<level value="info" />
	</logger>	
	
	<logger name="org.springframework.beans">
		<level value="info" />
	</logger>
	
	<logger name="org.springframework.context">
		<level value="info" />
	</logger>

	<logger name="org.springframework.web">
		<level value="info" />
	</logger>
	
	<logger name="jdbc.audit">
		<level value="warn"/>
	</logger>
	
	<logger name="jdbc.resultset">
		<level value="warn"/>
	</logger>
	
	<logger name="jdbc.connection">
		<level value="warn"/>
	</logger>

	<!-- Root Logger -->
	<root>
		<priority value="info" />
		<appender-ref ref="console" />
	</root>
	
</log4j:configuration>
```



BoardMapperTests.java

```java
package com.koreait.mapper;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.koreait.domain.BoardVO;

import lombok.Setter;
import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class) //테스트 코드가 스프링을 실행
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")//지정된 클래스나 문자열을 이용해서 필요한 객체들을 스프링 내에 객체로 등록
@Log4j
public class BoardMapperTests {
	
	@Setter(onMethod_ = @Autowired)
	private BoardMapper mapper;
	
	@Test
	public void update() {
		BoardVO board = new BoardVO();
		board.setBno(1l);
		board.setTitle("수정된 글 제목");
		board.setContent("수정된 글 내용");
		board.setWriter("어드민");
		log.info(" 수정 성공 : "+ mapper.update(board));
	}
	
//	@Test
//	public void delete() {
//		if(mapper.delete(2l) > 0) {
//			log.info("check : 성공 ");
//		} else {
//			log.info("check : 실패 ");
//		}
//	}
	
//	@Test
//	public void read() {
//		Long bno = 2l;
//		log.info(mapper.read(bno));
//	}
	
//	@Test
//	public void testInsertSelectKey_bno() {
//		BoardVO board = new BoardVO(); 
//		board.setTitle("새로 작성한 글 제목2");
//		board.setContent("새로 작성한 글 내용2");
//		board.setwriter("newbie2");
//		
//		mapper.insert(board);
//		log.info("board .... : "+ board);
//	}
	
//	@Test
//	public void testInsert() {
//		BoardVO board = new BoardVO(); 
//		// 메소드 안에서는 의존성 주입(DI)가 되지않기 때문이다. 
//		// 그렇기 때문에 전역변수에만 주입하는 것이다.
//		// 메소드 안에서는 new로 할당한다.
//		board.setTitle("새로 작성한 글 제목");
//		board.setContent("새로 작성한 글 내용");
//		board.setwriter("newbie");
//		
//		mapper.insert(board);
//		log.info("board .... : "+ board);
//	}
	
//	@Test
//	public void testGetList() {
//		mapper.getList().forEach(board -> log.info(board));
//	}
}
```

BoardMapper.xml

```xml
<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN" "http://mybatis.org/dtd/mybatis-3-mapper.dtd">

<mapper namespace="com.koreait.mapper.BoardMapper">
	<select id="getList" resultType="com.koreait.domain.BoardVO">
		<![CDATA[ 
			SELECT * FROM TBL_BOARD WHERE BNO > 0
		]]>
	</select>
	
	<insert id="insert">
		INSERT INTO TBL_BOARD (BNO, TITLE, CONTENT, WRITER) 
		VALUES(SEQ_BOARD.NEXTVAL, #{title}, #{content}, #{writer})
	</insert>
	
	<!--
		PK값을 미리 SQL을 통해서 처리하는 방식
		SQL을 한 번 더 실행하는 부담이 있기는 하지만 자동으로 추가되는 PK값을 확인해야 하는 상황에서는 
		유용하게 사용될 수 있다.
	-->
	<insert id="insertSelectKey_bno">
	<!-- BEFORE라는 속성은 먼저 실행되기 위함이다.-->
		<selectKey keyProperty="bno" order="BEFORE" resultType="long">
			SELECT SEQ_BOARD.NEXTVAL FROM DUAL
		</selectKey>
		INSERT INTO TBL_BOARD (BNO, TITLE, CONTENT, WRITER) 
		VALUES(#{bno}, #{title}, #{content}, #{writer})
	</insert>
	
	<select id="read" resultType="com.koreait.domain.BoardVO">
		SELECT * FROM TBL_BOARD WHERE bno = #{bno} 
	</select>
	
	<delete id="delete">
		DELETE FROM TBL_BOARD WHERE bno = #{bno}
	</delete>
    
	<update id="update">
		UPDATE TBL_BOARD
		SET TITLE = #{title}, CONTENT = #{content}, WRITER = #{writer}, UPDATEDATE = SYSDATE 
		WHERE BNO = #{bno} 
	</update>
	
	
</mapper>
```



BoardMapper.java

```java
package com.koreait.mapper;

import java.util.List;

import com.koreait.domain.BoardVO;

public interface BoardMapper {
	
//	@Select("SELECT * FROM TBL_BOARD WHERE BNO > 0")
	public List<BoardVO> getList();
	
	public void insert(BoardVO board); // 추가 
	
	// insert 하면서 select
	public void insertSelectKey_bno(BoardVO board); 

	// read() 선언 후 테스트 : 게시글 상세보기 
	public BoardVO read(long bno);
	
	//delete() 선언 후 테스트 : 게시글 삭제
	// 게시글 삭제 시 1 이상의 값 리턴 , 없으면 0 리턴
	public int delete(long bno);
	
	// update() 게시글 수정
    public int update(BoardVO board);
	
}
```





pom.xml

```xml
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0"
	xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
	xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 https://maven.apache.org/maven-v4_0_0.xsd">
	<modelVersion>4.0.0</modelVersion>
	<groupId>com.koreait</groupId>
	<artifactId>controller</artifactId>
	<name>ex02</name>
	<packaging>war</packaging>
	<version>1.0.0-BUILD-SNAPSHOT</version>
	<properties>
		<java-version>1.8</java-version>
		<org.springframework-version>5.0.7.RELEASE</org.springframework-version>
		<org.aspectj-version>1.6.10</org.aspectj-version>
		<org.slf4j-version>1.6.6</org.slf4j-version>
	</properties>
	<dependencies>
		<!-- Spring -->
		<dependency>
			<groupId>org.springframework</groupId>
			<artifactId>spring-context</artifactId>
			<version>${org.springframework-version}</version>
			<exclusions>
				<!-- Exclude Commons Logging in favor of SLF4j -->
				<exclusion>
					<groupId>commons-logging</groupId>
					<artifactId>commons-logging</artifactId>
				</exclusion>
			</exclusions>
		</dependency>
		<dependency>
			<groupId>org.springframework</groupId>
			<artifactId>spring-webmvc</artifactId>
			<version>${org.springframework-version}</version>
		</dependency>

		<!-- AspectJ -->
		<dependency>
			<groupId>org.aspectj</groupId>
			<artifactId>aspectjrt</artifactId>
			<version>${org.aspectj-version}</version>
		</dependency>

		<!-- Logging -->
		<dependency>
			<groupId>org.slf4j</groupId>
			<artifactId>slf4j-api</artifactId>
			<version>${org.slf4j-version}</version>
		</dependency>
		<dependency>
			<groupId>org.slf4j</groupId>
			<artifactId>jcl-over-slf4j</artifactId>
			<version>${org.slf4j-version}</version>
			<scope>runtime</scope>
		</dependency>
		<dependency>
			<groupId>org.slf4j</groupId>
			<artifactId>slf4j-log4j12</artifactId>
			<version>${org.slf4j-version}</version>
			<scope>runtime</scope>
		</dependency>
		<dependency>
			<groupId>org.projectlombok</groupId>
			<artifactId>lombok</artifactId>
			<version>1.18.0</version>
			<scope>1.2.17</scope>
		</dependency>
		<dependency>
			<groupId>log4j</groupId>
			<artifactId>log4j</artifactId>
			<version>1.2.17</version>
		</dependency>

		<!-- @Inject -->
		<dependency>
			<groupId>javax.inject</groupId>
			<artifactId>javax.inject</artifactId>
			<version>1</version>
		</dependency>

		<!-- Servlet -->
		<dependency>
			<groupId>javax.servlet</groupId>
			<artifactId>servlet-api</artifactId>
			<version>2.5</version>
			<scope>provided</scope>
		</dependency>
		<dependency>
			<groupId>javax.servlet.jsp</groupId>
			<artifactId>jsp-api</artifactId>
			<version>2.1</version>
			<scope>provided</scope>
		</dependency>
		<dependency>
			<groupId>javax.servlet</groupId>
			<artifactId>jstl</artifactId>
			<version>1.2</version>
		</dependency>

		<!-- Test -->
		<dependency>
			<groupId>org.springframework</groupId>
			<artifactId>spring-test</artifactId>
			<version>5.1.1.RELEASE</version>
		</dependency>
		<dependency>
			<groupId>junit</groupId>
			<artifactId>junit</artifactId>
			<version>4.12</version>
			<scope>test</scope>
		</dependency>
		<!-- https://mvnrepository.com.artifact/com.zaxxer/HikariCP -->
		<dependency>
			<groupId>com.zaxxer</groupId>
			<artifactId>HikariCP</artifactId>
			<version>2.7.4</version>
		</dependency>
		<!-- https://mvnrepository.com/artifact/org.mybatis/mybatis -->
		<dependency>
			<groupId>org.mybatis</groupId>
			<artifactId>mybatis</artifactId>
			<version>3.4.6</version>
		</dependency>
		<!-- https://mvnrepository.com/artifact/org.mybatis/mybatis-spring -->
		<dependency>
			<groupId>org.mybatis</groupId>
			<artifactId>mybatis-spring</artifactId>
			<version>1.3.2</version>
		</dependency>
		<dependency>
			<groupId>org.springframework</groupId>
			<artifactId>spring-tx</artifactId>
			<version>${org.springframework-version}</version>
		</dependency>
		<dependency>
			<groupId>org.springframework</groupId>
			<artifactId>spring-jdbc</artifactId>
			<version>${org.springframework-version}</version>
		</dependency>
		<!-- https://mvnrepository.com/artifact/org.bgee.log4jdbc-log4j2 -->
		<dependency>
			<groupId>org.bgee.log4jdbc-log4j2</groupId>
			<artifactId>log4jdbc-log4j2-jdbc4</artifactId>
			<version>1.16</version>
		</dependency>
	</dependencies>
	<build>
		<plugins>
			<plugin>
				<artifactId>maven-eclipse-plugin</artifactId>
				<version>2.9</version>
				<configuration>
					<additionalProjectnatures>
						<projectnature>org.springframework.ide.eclipse.core.springnature</projectnature>
					</additionalProjectnatures>
					<additionalBuildcommands>
						<buildcommand>org.springframework.ide.eclipse.core.springbuilder</buildcommand>
					</additionalBuildcommands>
					<downloadSources>true</downloadSources>
					<downloadJavadocs>true</downloadJavadocs>
				</configuration>
			</plugin>
			<plugin>
				<groupId>org.apache.maven.plugins</groupId>
				<artifactId>maven-compiler-plugin</artifactId>
				<version>3.5.1</version>
				<configuration>
					<source>1.8</source>
					<target>1.8</target>
					<compilerArgument>-Xlint:all</compilerArgument>
					<showWarnings>true</showWarnings>
					<showDeprecation>true</showDeprecation>
				</configuration>
			</plugin>
			<plugin>
				<groupId>org.codehaus.mojo</groupId>
				<artifactId>exec-maven-plugin</artifactId>
				<version>1.2.1</version>
				<configuration>
					<mainClass>org.test.int1.Main</mainClass>
				</configuration>
			</plugin>
		</plugins>
	</build>
</project>
```

BoardVO.java

```java
package com.koreait.domain;

import lombok.Data;

@Data
public class BoardVO { 
	// VO는 컬럼에 매핑된 것을 사용할 때 
	// DTO는 사용자 정의 모델된 것을 사용할 때
	
	private Long bno;
	private String title;
	private String content;
	private String writer;
	private String regDate;
	private String updateDate;
	
	
	
}
```



![img](https://blog.kakaocdn.net/dn/clssnI/btq4rHDhN0A/pHKkvHkQhteLGjwchn525k/img.png)


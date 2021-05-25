```
layout: post
title:  "스프링 - AOP"
date: 2021-05-24 13:00:21 +0900
categories: JAVA Spring
```

---

## AOP(Aspect Oriented Programming)

(관점 지향 프로그래밍)

관점이란 개발에 있어서, 관심사(concern)를 의미한다.

관심사는 개발 시 필요한 것들을 생각하는 일이며, 아래와 같다.



1. 파라미터가 잘 전달 되었는가?
2. 이 로직에서 발생할 수 있는 모든 예외를 처리하자.
3. 적절한 권한을 가진 사용자가 작업하고 있는가?



핵심 로직은 아니지만 반복적으로 개발에 필요한 관심사들이다.

따라서 AOP는 이러한 것들을 모듈로 분리하여 작성하고 

**핵심 비지니스 로직만을 작성할 것을 권장한다.**



예) 

나눗셈 프로그램 개발 시 두개의 숫자를 나누는 것은 **핵심로직**(종단관심사), 

0으로 나누는 지 체크하는 것을 **주변로직**(횡단관심사)이라고 한다.



**즉, 반복적으로 나타나는 횡단관심사를 모듈로 분리한 후 적절한 곳에 로직을 주입하는 것이 AOP이다.**

**스프링에서는 별도의 복잡한 설정 없이 간편하게 AOP의 기능들을 구현할 수 있기 때문에 중요한 특징 중 하나이다.**



---

## AOP를 사용할 수 있을 때

- Around(전 구역) 
- Before(메소드 시작 직후)
- After(메소드 종료 직전)



- AfterReturning(메소드 정상종료 후)

- AfterThrowing(메소드에서 예외 발생 종료 후)



---



## 코드 확인

Spring Leagcy Project 생성 (Spring MVC)



`pom.xml`

```xml
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
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
      <org.aspectj-version>1.9.0</org.aspectj-version>
      <org.slf4j-version>1.7.25</org.slf4j-version>
   </properties>
   <dependencies>
      <dependency>
         <groupId>org.springframework</groupId>
         <artifactId>spring-test</artifactId>
         <version>${org.springframework-version}</version>
      </dependency>
      
      <dependency>
         <groupId>org.projectlombok</groupId>
         <artifactId>lombok</artifactId>
         <version>1.18.0</version>
         <scope>provided</scope>
      </dependency>
   
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
      
      <dependency>
         <groupId>org.aspectj</groupId>
         <artifactId>aspectjweaver</artifactId>
         <version>${org.aspectj-version}</version>
      </dependency>   
      <!-- JSON -->
      <dependency>
         <groupId>com.fasterxml.jackson.core</groupId>
         <artifactId>jackson-databind</artifactId>
         <version>2.9.6</version>
      </dependency>
      
      <dependency>
         <groupId>com.fasterxml.jackson.dataformat</groupId>
         <artifactId>jackson-dataformat-xml</artifactId>
         <version>2.9.6</version>
      </dependency>
      
      <dependency>
         <groupId>com.google.code.gson</groupId>
         <artifactId>gson</artifactId>
         <version>2.8.2</version>
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

      <!-- Lombok -->
      <dependency>
         <groupId>org.projectlombok</groupId>
         <artifactId>lombok</artifactId>
         <version>1.18.0</version>
         <scope>1.2.17</scope>
      </dependency>

      <!-- Log4j -->
      <dependency>
         <groupId>log4j</groupId>
         <artifactId>log4j</artifactId>
         <version>1.2.17</version>
      </dependency>
      
      <!-- https://mvnrepository.com/artifact/org.bgee.log4jdbc-log4j2 -->
      <dependency>
         <groupId>org.bgee.log4jdbc-log4j2</groupId>
         <artifactId>log4jdbc-log4j2-jdbc4</artifactId>
         <version>1.16</version>
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
         <artifactId>javax.servlet-api</artifactId>
         <version>3.1.0</version>
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
      
      <!-- mybatis -->
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

`com.ooo.service.SampleService Interface` 생성

```java
package com.koreait.service;

public interface SampleService {
	public Integer doAdd(String str1, String str2) throws Exception;
}

```

`com.ooo.service.SampleServiceImple class`  생성

```java
package com.koreait.service;

import org.springframework.stereotype.Service;

@Service
public class SampleServiceImple implements SampleService {

	@Override
	public Integer doAdd(String str1, String str2) throws Exception {
		return Integer.parseInt(str1) + Integer.parseInt(str2);
	}

}

```



`com.koreait.aop.LogAdvice` 클래스 생성

```java
package com.koreait.aop;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import lombok.extern.log4j.Log4j;

@Aspect // 횡단관심사
@Log4j
@Component
public class LogAdvice {
	
// 접근자 (*) 상관없이 모든 패키지에 SampleService 클래스 안에 있는 모든(*)메소드 들이 호출
	
//	execution...은 AspectJ의 표현식이며, 맨 앞의 * 은 접근제어자를 의미하고
//	맨 마지막의 *은 클래스의 이름과 메소드의 이름을 의미한다.
//	.. 은 0개 이상이라는 의미이다.
	
//	모든 접근 제어자의 SampleService이름이 붙은 모든 클래스에서 모든 메소드 중 
//	매개변수가 0개 이상이라는 뜻
	@Before("execution(* com.koreait.service.SampleService*.*(..))")
	public void logBefore() {
		log.info("==========Before===========");
	}
	@After("execution(* com.koreait.service.SampleService*.*(..))")
	public void logAfter() {
		log.info("===========After===========");
	}
	@AfterReturning("execution(* com.koreait.service.SampleService*.*(..))")
	public void logAfterReturning() {
		log.info("===========AfterReturning===========");
	}
	
	@Before("execution(* com.koreait.service.SampleService*.doAdd(String, String)) && args(str1, str2)")
	// 매개변수 2개를 받기 때문에, 메소드에서도 매개변수를 받아야한다.
	// && args(..)는 logBeforeWithParam(str1, str2) 메소드 매개변수의 이름이다. 
	public void logBeforeWithParam(String str1, String str2) {
		log.info("str1 : " + str1);
		log.info("str2 : " + str2);
	}
	
}

```



`src/test/java` `com.koreait.aop.SamplerServiceTests.java` 테스트 클래스 생성

```java
package com.koreait.aop;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.koreait.service.SampleService;

import lombok.Setter;
import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class)
@Log4j
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
public class SampleServiceTests {
	
	@Setter (onMethod_ = @Autowired)
	private SampleService service;
	
	// 프록시 객체가 잘 연결 됐는지, 프록시 객체 확인
	@Test
	public void testClass() {
		log.info(service);
		log.info(service.getClass().getName());
	}
	
	@Test
	public void testAdd() throws Exception {
		log.info(service.doAdd("123", "456"));
	}
	
	
}

```

---

## @AfterThrowing



`LogAdvice.java`  메소드 추가

```java
	@AfterThrowing(pointcut="execution(* com.koreait.service.SampleService*.*(..))",
			throwing="exception")
	public void logException(Exception exception) {
		log.info("Exception........!");
		log.info("Exception : " + exception);
	}
```



`SampleServiceTests.java` 메소드 수정

```java
	@Test
	public void testAdd() throws Exception {
//		log.info(service.doAdd("123", "456"));
		log.info(service.doAdd("ABC", "456"));
	}
```



---

## @Around





---

## 트랜잭션

하나의 쿼리만 사용한다면 예외 발생 후 DB에는 변화가 없다.

하지만 여러 개의 쿼리(하나의 트랜잭션)을 순차적으로 실행 후 문제 발생 시 

성공한 쿼리문만 반영되어 다시 직접 복구해야하는 문제가 발생한다.

이러한 문제를 막기 위해 스프링에서는 `@Transactional`  어노테이션을 사용하여 한 개의 트랜잭션에 문제 발생 시 롤백을 통해 전체 트랜잭션이 모두 DB에 반영되지 못하도록 막아준다.





---

## 댓글과 댓글 수에 대한 처리






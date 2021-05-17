---
layout: post
title:  "스프링 - 스프링 기본 베이스 설명"
date: 2021-05-18 09:00:21 +0900
categories: JAVA Spring
---



#### 스프링 설치부터 기본 베이스 설명



## 01. 설치

JDK 1.8버전설치
	- 버전은 1.8의 최신버전을 사용하는 것이 좋타
	- 2020년 5월 18일 기준 1.8_251
	- 설치 위치를 잘 기억해놓을 것



IDE(Eclipse, STS, IntelliJ) 설치
	-Eclipse : 가장 인기 많은 JAVA 통합 개발 환경
	-STS : Spring 개발팀이 Eclipse에 미리 스프링을 쓰기 편하도록
	         설정을 해둔 프로그램
	-IntelliJ : 유료

	Eclipse에 스프링 관련 플러그인을 추가해서 사용할 수 있다
	다만, 버전이나 기타 다른 플러그인과의 충돌 문제가 발생할 수 있으므로
	주의해서 사용해야 한다.

Eclipse 설치
	- JAVA EE로 설치
	-인코딩 UTF-8로 설정 (workspace, html, css, jsp)
	-eclipse.ini에 추가
		-vm
		 C:\Program Files\Java\jdk1.8.0_251\bin\javaw.exe

STS3 혹은 Spring Framework 플러그인 설치
	-Google에 spring 검색		
	-아무 생각없이 최신버전을 깔면 스프링부트를 설치한다.
	-STS3를 찾아서 설치하거나 P2 Repo를 복사한다
	-Help -> install new software ->Add로 복사한 P2Repo를 추가
		->select All로 모두 체크 후 Next 눌러서 설치

P2 : 이클립스에서 플러그인을 추가할 때 P2 Repository를 이용한다
Spring Boot
	-자주 쓰는 초기 세팅을 미리 구축해놓은 스피링 버전
	- 공부하기에 적합하지 않아서 스프링을 다 공부한뒤 나중에 써본다

Tomcat 설치 
	-Apache Tomcat 홈페이지의 which Version을 통해 알맞은 버전을 선택하자
	-Eclipse에서 톰켓의 경로를 등록해야 한다
	
	Windows -> Preferences -> Server -> Rantime Environment -> Add

OracleXE 11g 설치
	- 오라클과 톰캣이 모두 8080 포트를 사용하기 때문에 충동이 발생한다.
	둘 중 하나의 사용 포트번호를 변경해줘야 한다

```sql
- 오라클에서 포트번호 설정 명령어
	exec dbms_xdb.sethttpport(9090);
	select dbms_xdb.gethttpport() from dual;
```

OracleXE 명령어
	
```sql
- sqlplus : 콘솔에서 실행
- sys as sysdba : 관리자 계정으로 실행

- 계정 생성 및 권한 설정 테이블 영역 설정
GRANT CONNECT,RESOURCE,UNLIMITED TABLESPACE 
	TO SCOTT IDENTIFIED BY TIGER;
ALTER USER SCOTT DEFAULT TABLESPACE USERS;
ALTER USER SCOTT TEMPORARY TABLESPACE TEMP;
```



# 

# 02.스프링 프로젝트 



src/main/java : 자바 코드를 작성하는 곳
src/main/resources : 실행할 때 참고하는 기본 경로 (주로 설정 파일들을 넣는다)
src/test/java : 테스트 코드를 작성하는 곳 (JUnit을 이용)
src/test/resources : 테스트 관련 설정 파일 보관 경로
JRE System Library : 우리가 설치한 JDK와 함께 설치된 JRE
					(Eclipse 설치 할때 설정했음)
Maven Dependencies : 이 프로젝트에서 사용하는 라이브러리들의 정보
		    (Spring Legacy Project는 Maven Project이다)
src/main/webapp : Apache가 사용하는 영역 (여기에 html 코드를 올리면 접속 할 수 있음)
WEB-INF/views : jsp 코드를 작성하는 곳

servlet-context.xml : 웹과 관련된 스프링 설정 파일
root-context.xml : 스프링 설정 파일
web.xml : 톰캣의 설정 파일
pom.xml : 메이븐의 설정파일

### XML이란 무엇인가?
	- 마크업 언어
	- HTTP처럼 열고 닫는 언어
	- 여러가지 프로그램 혹은 프레임워크의 설정에 이용된다
	- 여는 태그와 닫는 태그 사이에 값을 넣는다





### 03. 메이븐 프로젝트

03_메이븐 프로젝트.txt

Maven이란?
	- 프로젝트 관리 도구
	- 프로젝트의 시작부터 프로젝트를 마무리하기까지에 필요한
	  여러 이슈들을 손쉽게 관리할 수 있는 프로그램
	
pom.xml
	- Project Object Model
	- 프로젝트 전체의 설정이 들어있는 파일 
	- 파일은 프로젝트마다 1개이며, 플젝트의 root에 있어야 한다
	- pom.xml보면 프로젝트의 모든 설정, 의존성 등을 알 수 있다
	- pom.xml에 프로젝트에 필요한 플러그인을 등록하면
	  자동으로 다운받아서 관리해준다
POM 파일에서 사용하는 태그들

`<groupId>` : 그룹 ID
	- Maven으로 제작된 모든 프로젝트 중에서 유일하게 식별할 수 있는 ID
	groupId는 패키지 명명 규칙을 따르는 것이 좋다.ex)(com.kg, org.spring 등)

`<artifactId>` : 버전 정보를 생략한 jar 파일 이름
	소문자로만 작성하는 것이 좋다
	버전 정보는 생략
 	특수문자는 사용하지 않는 것이 좋다

`<version>` : 버전

`<packaging>` : 패키징 타입 설정. war, jar 등

`<properties>` : 밑에서 사용할 변수처럼 사용할 태그들을 정의한다
	       밑에서는 ${태그명}으로 가져다 쓸 수 있다

`<dependencies>` : 프로젝트에서 의존할 플러그인들을 정의하면 자동으로 관리해준다

`<dependency>` : 의존하는 프로젝트의 POM 정보를 적는다
`	<groupId>` : 의존할 프로젝트의 그룹 ID
	`<artifactId>` : 의존할 프로젝트의 artifact ID
	`<version>` : 의존할 프로젝트의 버전
	`<scope>` : 의존하는 범위를 설정한다
	`<exclusions>` : 의존할 프로젝트에서 사용됐지만 의존에서 제외하고 싶은 
		       라이브러리를 설정한다

`<build>` : 이 메이븐 프로젝트를 빌드할 때 사용할 도구들을 정의한다

* 빌드 태그 내부의 maven-compiler-plugin의 버전이 사용할 JDK의 버전이다



scope의 종류 : compile, runtime, provided, test

complie : 컴파일 할 때 필요. 테스트 및 런타임에 포함된다
	`<scope>`를 설정하지 않을 경우 기본값이 complie이다

runtime : 런타임에 필요. 프로젝트의 코드를 컴파일 할 때는 필요하지 않지만
	  실행할 때 필요하다는 뜻이다. ex: ojdbc6.jar
          배포시 포함된다. 

provided : 컴파일 할 때는 필요하지만, 실제 실행때에는 필요없는 모듈임을 의미한다.
	   배포시 제외된다.
	   
test : 테스트 코드를 컴파일 할 때 필요.
       테스트시 이용되며, 배포시 제외된다.

원격 레포지토리와 로컬 레포지토리
	
Maven은 컴파일이나 패키징(배포준비)를 실행할 때 필요한
`<dependecy>`에 설정한 모듈을 Maven 중앙 레포지토리(원격 레포지토리)에서 다운로드 받는다

다운로드 받은 모듈은 로컬 레포지토리에 저장된다.
로컬 레포지토리의 경로는 [USER_HOME]/.m2.repository 폴더에 생성된다.

C:\Users\User\.m2\repository 지운 후 다시 다운로드하면 사용이 가능하다

*`<org.springframework-version>5.2.5.RELEASE</org.springframework-version>`* 5.2.5 자리에 버전만 찾아서 입력하면 이클립스안에서 알아서 업데이트 진행



---

# 04. JUnit

04_JUnit.txt

JUnit이란?
	- 단위 테스트를 위한 편리한 기능들이 구현되어 있는 라이브러리
	- 스프링 프레임워크에 포함될 정도로 좋다

1. 사용하기 전에 pom.xml에서 가장 많이 사용되는 버전으로 교체한다
	- maven repo에서 확인
	- 2020-05-20일 기준 4.12버전

2. Spring 프로젝트를 생성하면 기본적으로 Maven 프로젝트의 관례에 따라
테스트하기 편하게 디렉토리가 구성되어있다.

/src/main/java/에 자바 코드를 보관하고
/src/test/java/에 테스트 코드를 보관한다

JUnit Test를 진행하기 위해서는 테스트 대상이 되는 클래스와
테스트 클래스가 같은 패키지 이름을 사용해야 한다.

#올바른 테스트 프로그램 작성 요령
	1.테스트 클래스와 테스트 메서드는 public으로 선언해야 한다
	2.Run As ..-> JUnit Test를 하면 @Test 어노테이션이 붙은
  	  메서드들로 테스트가 진행된다.
	3.org.junit.Assert 클래스에 포함된 여러가지 static 메서드를 이용하여
	  적절한 테스트 프로그램을 작성할 수 있다. 
	
	assertArrayEquals(expected, actual) : 두 배열이 일치하는지 확인한다.
	assertEquals(e, a) : 두 객체가 일치하는지 확인한다
	assertSame(e, a) : 두 객체가 같은 객체인지 확인한다
	assertTrue(a) : a가 참인지 확인한다
	assertNotNull(a) : a가 null이 아닌지 확인한다
	...
	
	Google에 junit assert docs 검색하면 더 많은 assert method를 확인할 수 있다



---

# 05.Log4j

05_log4j.txt

### Log4j란?

	편리하게 로그를 남길 수 있는 자바 라이브러리
	로그란? 개발 도중 확인하고 싶은 사항을 텍스트로 찍어보는 것
	(System.out.println()도 로그라고 할 수 있다)

### Log4j의 구성요소

	Logger : 로그 메세지를 Appender에게 전달한다.
	Appender : 전달받은 메세지를 해당 Appender에 추가한다
		ex: 콘솔 어펜더는 콘솔에 추가, 파일 어펜더는 파일에 추가,
		DB Appender는 DB에 추가..
		Socket 어펜더는 Socket에 추가..
	Layout : 로그를 어떤 형태로 출력할지 결정한다.

### Logging 레벨

	OFF : 모든 로그를 끔
	FATAL : 가장 심각한 에러 등급
	ERROR : 일반적인 에러
	WARN: 에러는 아니지만 주의할 필요가 있는 경우
	INFO : 일반적인 정보
	DEBUG : 디버깅 할때 필요한 정보
	TRACE : 아주 자세한 로그
	ALL : 모든 로그를 킴
	
	개발자가 설정한 로그에 등급을 설정해 두고
	로그 레벨을 설정하여 보고싶은 등급만 볼 수 있다
	
	로깅 레벨을 설정하면 설정한 레벨보다 중요한 로그만 보여주게 된다.
	
	ALL < TARCE < DEBUG < INFO < WARN < ERROR < FATAL < OFF
	
	ex: 로깅레벨이 INFO일 때는 INFO, WARN, ERROR, FATAL을 볼 수 있다.

##### getLoggger() 메서드를 이용하여 로그를 기록할 클래스의 Logger를 가져온다.

##### `org.apace.log4j.Logger Logger = Logger.getLogger(클래스명.class);`



### Java에서 Logging Level 설정

	logger.setLevel(Level.로깅레벨);
	  * org.apache.log4j.Level 클래스의 final static field로 로깅레벨이 선언되어 있다.

### 로그 메세지 남기기

	logger.fatal(msg);
	logger.error(msg);
	logger.warn(msg);
	logger.info(msh);
	...
	
	해당 로깅레벨 메서드로 원하는 레벨의 로그를 남길 수 있다.



### log4j의 설정은 프로그램에서도 할 수 있지만, xml 파일로 할 수도 있다.

  Spring에서 테스트 폴더와 메인 포더의 resource에 log4j.xml 파일이 만들어져있다.



### Appender 종류

`   ConsoleAppender - org.apache.log4j.ConsoleAppender`

콘솔에 로그 메세지를 출력한다

immediateFlush : 버퍼가 차는 것을 기다리지 않고 바로 flush
encoding : 기본 인코딩 타입을 다른 인코딩 타입으로 수정할 수 있다.
target : System.out 혹은 System.err를 선택 할 수 있다. 기본값은 System.out

` RollingFileAppender - org.apache.log4j.RollingFileappender`

파일 크기가 일정 수준 이상이 되면 기존 파일을 백업 파일로 두고 다시 기록하는 어펜더

file : 로그 파일의 경로
MaxFileSize : 파일의 최대 크기
MaxBackupIndex : 보관할 백업 로그의 개수

`  DailyRollingFileAppender - org.apach.log4j.DailyRollingFileAppender`

매일 새로운 로그 파일을 생성하고 기록한다.

Socket...
DB...	

다른 어펜더가 궁금하다면 Apache Log4j 문서 참조



### Layout의 종류

`  PatternLayout - org.apache.log4j.PatternLayout`
	

패턴을 이용하여 로그를 출력하는 형태를 만들 수 있다	

%M - 로그가 발생한 메서드의 이름 출력
%m - 로그 메세지 출력
%n - 줄바꿈(\n)
%p - debug, info, warn 등등의 로깅레벨(priority) 출력
%r - 어플리케이션 실행 후 이벤트가 발생한 시점까지의 경과시간.(millisec)
%c - package명 출력
%C - 클래스명 출력
%d - 날자 출력
%t - 쓰레드 이름 출력

`  HTMLLayout - org.apache.log4j.HTMLLayout`

로그를 HTML 형식으로 출력해준다.



---



# 06. 자바빈(Beans)

06_자바빈.txt


### JavaBean (VO, DTO, Bean..)

자바빈 스타일의 오브젝트를 의미한다.

자바빈은 두 가지 뜻이 있다.

1. 요즘은 아무도 안쓰는 망한 자바 비쥬얼 컴포넌트의 이름이 자바빈이다.

2. 우리가 JSP에서 많이 사용했던 자바빈 스타일의 객체 (private, setter, getter)

우리가 스프링을 공부할 때 자바빈 혹은 빈 객체라는 용어를 사용하게 된다면
2번을 의미한다.



### 자바빈 객체의 관례

- 기본 생성자가 반드시 존재해야 한다.

자바빈 객체는 파라미터(매개변수)가 없는 기본 생성자를 반드시 갖고 있어야 한다.
툴이나 프레임워크에서 기본 생성자를 이용해 오브젝트를 생성하기 때문에 반드시 필요하다.



- 속성 (Properties)

자바빈의 필드값(멤버 변수)들을 속성이라 부른다.
속성은 set으로 시작하는 setter와 get으로 시작하는 getter를 이용해
수정 또는 조회한다.

Spring 프레임워크는 빈객체의 setter와 getter를 이용하도록 설계되어 있기 때문에
필요에 따라 setter와 getter를 잘 생성 해놓아야 한다.

setter와 getter만으로 접근할 수 있도록 속성의 접근제어자는 private으로 설정한다.


### 자바빈 객체를 @Component로 등록 후 스프링에서 관리하기

root-context.xml

1. NameSpace에서 context를 체크한다.

2. `<context:component-scan>`을 이용해 @Component 어노테이션이 적용된 빈들을
       검색하고 XML 파일에 등록한다.

### 실제로 스프링에서 일어나는 일

1. 스프링 프레임워크가 실행되면서 스프링의 메모리 영역을 생성한다
   (ApplicationContext)

2. ApplicationContext에 올리고 관리할 빈들을 root-context.xml에서 찾는다.

3. `<context:component-scan>`은 
       @Component 어노테이션이 설정된 객체를 검색하여 등록한다.

4. 이후 필요할 때마다 ApplicationContext에 등록된 빈 클래스의 인스턴스를 생성한다.
   (필요할 때 : 프로그램을 실행하던 도중 @Autowired를 발견했을 때)





---



# 07. 스프링 테스트 진행

07_스프링 테스트 진행.txt


### 1. pom.xml에 스프링 테스트 추가
```xml
<dependency>
    <groupId>org.springframework</groupId>
    <artifactId>spring-test</artifactId>
    <version>${org.springframework-version}</version>
    <scope>test</scope>
</dependency>
```

​		

### 2. 테스트하려는 클래스에 RunWith(), ContextConfiguration() 설정

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")

※ 빈 객체를 이용한 테스트 진행에 필요한 정보들 





---

# 08. 스프링__MVC_

08_스프링 MVC.txt

### MVC Model 2

- 로직(controller)과 화면(view)을 분리하는 개발 방식
- 비지니스 로직 : 컨트롤러 (Controller)
- 화면 : 뷰 (View)
- 데이터 처리에 이용되는 객체 : 모델(Model)
- Request는 알맞은 컨트롤러로 전달된다
- 컨트롤러는 알맞는 View를 선택하고 처리된 데이터를 전송한다.
  (처리 도중 필요하다면 DAO를 이용하여 DB에 접속한다)
- View에서 HTML코드를 생성한 뒤 최종적으로 사용자에게 응답한다.

### DAO(Data Access Object)

- DB에 접속할 때 필요한 기능들을 구현해놓은 객체
- 데이터를 조회하거나 조작하는 기능을 이곳에서 전담한다

### 스프링 프로젝트의 로딩 구조

- 스프링은 프로젝트 구종 시 관여하는 여러 설정파 XML파일들로 MVC를 구현한다.
- web.xml/ root-context.xml/ servlet-context.xml
- 우선 서버가 시작되면 web.xml에서 톰캣 구동과 관련된 설정이 시작된다

가장 먼저 `<context-param>` : root-context.xml의 결로가 설정되어 있다.
root-context.xml이 처리되면서, 그곳에 등록된 빈 설정들이 동작하게 된다.
root-context.xml에 적의된 객체(Bean)들은 설정된 의존성대로
스프링의 WebApplictionContext에 생성된다.

`<listener>`에 ContextLoaderListener가 등록되어 있다.
ContextLoaderListener는 WebApplictionContext 생성된 빈 객체들을
필요할 때 알맞게 꺼내 사용하는 클래스다.

`<serlvet>`에 DispatcherServlet이 등록되어 있다.
DispatcherServlet은 스프링 MVC 구조에서 가장 핵심적인 역할을 하는 클래스다.
그 DispatcherServlet의 초기화 파라미터로 servlet-context.xml이 설정되어있다.

즉,DispatcherServlet의 초기화 작업에 servlet-context.xml에 설정한 값들이 
적용된다는 것을 알 수 있다.

servlet-context.xml을 로딩하여 처리하고 나면, 
servlet-context.xml에 인해 새로 만들어진 빈(Bean) 객체들은
기존에 만들어진 빈 객체들(root-context.xml)과 연동된다.





### 스프링 MVC의 동작 순서

원래라면 JSP로 하루종일 구현해야 하는(문제도 많이 생기는)MVC Model2 방식을
손쉽게 이용할 수 있도록 만들어 놓은 것이 Spring Framework다.
잘 이용하면 JSP에 비해 코드양이 현저히 줄어들게 된다.

어노테이션과 XML설정, 약간의 코드만으로 MVC Model2방식의 개발을 진행할 수 있다.



1. Request는 가장 먼저 DispatcherServlet을 통해 처리된다.
   생성된 web.xml을 보면 모든 url-pattern이
   DispatcherServlet에 연결된 것을 확인할 수 있다.

2. DispatcherServlet에서 HandlerMapping클래스를 이용해
   Request의 처리를 담당하는 알맞은 컨트롤러를 찾아준다.

   적절한 컨트롤러를 찾았다면 HandlerAdapter를 이용해 해당 컨트롤러를 동작시킨다.
   
3. 컨트롤러는 개발자가 로직을 작성하는 곳이다.
   이곳에 작성한 개발자의 코드는 HandlerAdapter에 의해 적절한 타이밍에 실행된다.
   
   개발자는 이곳에 알맞은 처리를 구현한 뒤 결과 데이터는 Model이라는 개체에 담는다.
   로직에 따라 다양한 타입의 결과를 반환할 필요가 있는데 이ㅔ 대한 처리는
   ViewResolver를 이용한다.

4. ViewResolver는 컨트롤러가 처리한 결과를 어떤 View에 전달할지 결정하는 클래스다.
   가장 흔하게 사용하는 설정은 servlet-context.xml에 정의된 
   InternalResourceViewResolver이다.

5. View는 실제로 응답해야 하는 HTML 코드를 JSP를 이용해 생성하는 곳이다.
   만들어진 HTML코드는 DispatcherServlet을 통해 최종적으로 사용자에게 응답된다.





---

# 09. 컨트롤러 



`servlet-context.xml`에서

`<context:componet-scan>`을 이용해 base-package를 스캔한다.
base-package에 지정된 패키지를 스캔하면서 어노테이션이 붙은 것들을 파악하고 관리한다.

컨트롤러 크래스의 선언부에는 @Controller와 @RequestMapping을 많이 이용한다.



클래스 선언부 위에 선언한 @ReqestMapping은
해당 컨트롤러 클래스의 모든 메서드들의 기본 URL 경로가 된다.



기본으로 생성되는 HomeController 클래스는 @RequestMapping 어노테이션을 사용하지 않았는데.
이렇게 설정하지 않으면 tomcat의 server.xml에 설정된 기본context 경로를 이용한다.



### @RequestMapping의 추가적인 옵션들

value : 매핑할  url을 설정하는 파라미터
method : GET/POST/PUT ...등 여러가지 HTTP 요청방식을 구분지어서 받을수 있다.



### @RequestParam에 ArrayList와 배열 이용하기

동일한 이름의 파라미터를 여러개 수집할 때 ArrayList<> 혹은 배열을 이용하면
자동으로 수집해준다.



### @RequestParam에 자바빈 객체를 여러개 받기





---

# 10. InitBinder 

10_InitBinder.txt

변환이 가능한 데이터는 스프링이 자동으로 변환해주지만
자동으로 변환되지 않는 경우에는 직접 파라미터를 변환해서 처리해야 한다

ex) : 날짜를 나타내는 문자열을 Date타입으로 변환해야 하는 경우,
yyyy/mm/dd/는 자동으로 변환해주지만 다른 형태는 자동으로 변환해주지 못하는 문제
(yyyy.mm.dd,  yyyy-mm-dd...)x

@InitBinder 어노테이션을 붙인 메서드는 파라미터 바인딩시에 자동으로 호출된다

```java
//InitBinder 정책 범위 설정
// - 범위 설정시 설정한 객체만 이 메서드를 거쳐간다
//설정에 사용되는 이름: 해당 클래스 이름 소문자(기본 인스턴스 이름)
//				 혹은 따로 설정한 모델 속성명	
//	@InitBinder("animal")
//	public void AnimalBinder(WebDataBinder binder) {
//		// Animal 객체를 사용하는 요청만 이곳의 정책을 따른다
//		
//		// Animal 객체의 joinDate 필드에만 적용되는 정책
//		binder.registerCustomEditor(
//				java.util.Date.class, 
//				"joinDate", // 필드명
//				new CustomDateEditor(
//						new SimpleDateFormat("yyyy.MM.dd"),
//						false));

예로 들었던 날짜 포맷 정책은 어노테이션 한줄로도 해결이 가능하다
@DateTimeFormat(pattern = "yyyy.MM.dd")
```



---

# 11. Model



### Model
- 컨트롤러에서 생성된 데이터를 담아서 View(JSP)에 전달하는 역할을 하는 객체
- 메서드의 파라미터에 Model 타입이 지정된 경우 스프링이 Model타입 객체를 생성하여
  메서드의 파라미터에 주입해준다
- JSP의 request.setAttribute()와 비슷한 역할을 한다

### Servlet Model2

```java
request.setAttribute("title", "DB에서 가져온 글 제목");
RequestDispatcher dispatcher = request.getRequestDispatcher("/home.jsp");
dispatcher.forward(request, reponse);
```

### Spring Model2

```java
@RequestMapping("/home")
public String home(Model model){
	model.addAttribute("title", "DB에서 가져온 글 제목");
	return "home";
}
```

---
layout: post
title:  "스프링 설치 및 환경설정"
date: 2021-05-06 09:00:21 +0900
categories: JAVA Lecture
---





## MAC / Windows OS 스프링 STS 이클립스 Market Place 설치, 환경 설정

![img](https://blog.kakaocdn.net/dn/3GSxg/btq4kUPdvBU/G6I2bde2HoYYba2NtWIMqK/img.png)

##### [[Coding/스프링(Spring) Framework\] - 스프링 - 프레임 워크란? 특징, 장점, 라이브러리 개념 등﻿](https://iu-corner.tistory.com/entry/스프링-프레임-워크)



## 스프링 STS 이클립스 Market Place 설치, 환경 설정

#### STS(Spring Tool Suite) 설치

- spring boot (쉬움)
- eclipse 플러그인 (선택)
  - 이클립스 실행 > Help > Install New Software-> Select All > Next > Next > progress 창 확인
  - -> [http://download.springsource.com/release/TOOLS/update/e4.18/](http://download.springsource.com/release/TOOLS/update/e4.8/)
    -> Add > Name : STS > Ok
    **링크의 e4.18은 이클립스의 버전을 뜻한다. 즉, Eclipse IDE 버전이 Photon이라면 4.8이다.
    \****본인은 2020-12 버전을 사용중이기 때문에 e4.18이다. 확인은 이클립스 다운로드 사이트에서 확인 가능.**

![img](https://blog.kakaocdn.net/dn/tC6IF/btq4hHQSB2t/CUkMyaQAqYlAesF4bA3lr1/img.png)

![img](https://blog.kakaocdn.net/dn/bGO9X3/btq4kGcu9Rq/u3Q1fQjC6tx8YARzPfhSF1/img.png)

![img](https://blog.kakaocdn.net/dn/RSMRa/btq4eJV511e/Z7PLhwoVXgwkH1PQVACQTK/img.png)

![img](https://blog.kakaocdn.net/dn/bOzVdM/btq4lMDogHa/GTMDxH9UaVfaCY4cIFbJe0/img.png)

![img](https://blog.kakaocdn.net/dn/bkMrAi/btq4dLT8Ml2/wzXKmiksn2lfO3MOUkR51k/img.png)

![img](https://blog.kakaocdn.net/dn/VA5RB/btq4hqPfLQw/OApj2z5yKffWEzyw9HNOQk/img.png)

![img](https://blog.kakaocdn.net/dn/3qKRE/btq4f1WmgBo/5r4aZYkT7JO8gozpkStpC1/img.png)대기

![img](https://blog.kakaocdn.net/dn/dPHKDG/btq4iNQZhGz/VmOavLQO5VIfn5XobMfzG1/img.png)

Restart Now를 누른다.

![img](https://blog.kakaocdn.net/dn/bYgwAv/btq4dcxvL0d/PpYkSBkDnk1rYCjBrSRjhK/img.png)

![img](https://blog.kakaocdn.net/dn/cEIOT5/btq4eLTXPdz/3YmjyPgI6K1KMPCAK5bKkk/img.png)

Spring Legacy Project 클릭

![img](https://blog.kakaocdn.net/dn/oPrHd/btq4f1aYnhv/3TFocobKfObMERGJKJk9uK/img.png)

Spring MVC Project 클릭 후 Next

![img](https://blog.kakaocdn.net/dn/dhEgQL/btq4jOu9yaV/iTxDPMB7Fyv7Ca7RrClKS1/img.png)

yes를 누르고 넘어간다.

**프로젝트 처음 생성 후 빨간 오류가 뜨는 것은 당연하다. 아래와 같이 설정을 따라한다.**

- 프로젝트 생성(Maven, Project Object Model)
- Maven은 필요한 라이브러리를 특정 문서(pom.xml)에 정의해 놓으면 사용할 라이브러리 뿐만 아니라 해당 라이브러리가 작동하는 데에 필요한 다른 라이브러리들까지 관리하여 네트워크를 통해서 자동으로 다운받아 준다.

## 초기 세팅 / 환경 설정

```
    <version>1.0.0-BUILD-SNAPSHOT</version>
    <properties>
        <java-version>1.8</java-version>
        <org.springframework-version>5.0.7.RELEASE</org.springframework-version>
        <org.aspectj-version>1.6.10</org.aspectj-version>
        <org.slf4j-version>1.6.6</org.slf4j-version>
    </properties>
-----------------------------------------------------------
             // 137번줄 
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
```

설정 후 저장

![img](https://blog.kakaocdn.net/dn/Kli9Q/btq4jP8EI0I/K72jOfFpazWeoEy0dnfXFk/img.png)

프로젝트 우클릭 후 Update Project를 누른다.

![img](https://blog.kakaocdn.net/dn/dhEcbz/btq4jQfq9dx/NZVrebIQKxb8KqWWJgTPi0/img.png)

프로젝트 우클릭 -> Maven -> update project

**force update of snapshots/releases 체크**

![img](https://blog.kakaocdn.net/dn/bS2HZ5/btq4jOhCnTs/qI43QG8igKkbyktQ6xv5Q1/img.png)

**오류가 사라진 것을 확인해야 합니다.**





------

### Lombok 라이브러리 설치

이클립스와 스프링 플러그인만으로도 스프링 개발이 가능하지만,

Lombok(롬복)을 이용하면 Java 개발 시 getter/setter, toString(), 생성자 등을 자동으로 생성 해주므로 설치가 필요한 라이브러리 입니다.

**이클립스 종료 후 **

https://projectlombok.org/download

[

Download

projectlombok.org

](https://projectlombok.org/download)

위 링크에서 다운로드를 한다.

lombok.jar 이클립스가 실행된 경로에 jar파일을 두고,

jar 파일이 있는 경로에서 (Mac은 상관없이 바로 jar경로에서 터미널로 실행 하면 됨. 이클립스로 옮기지 않아도 된다.)

```
$ java -jar lombok.jar
```

![img](https://blog.kakaocdn.net/dn/rheHQ/btq4ddwrn0j/7GMAeJ8ykk10pNdx85Uftk/img.png)

커맨드로 실행해도 되고, jar파일을 직접 실행해도 됨

실행하면 아래와 같이 설치창이 실행되는데, Eclipse를 모두 체크 해제 후

![img](https://blog.kakaocdn.net/dn/cqE0Sk/btq4jXr02Pi/8kKRaVT9s1w1oTGU8pEguK/img.png)

Specify location.. 을 클릭

![img](https://blog.kakaocdn.net/dn/qDZH6/btq4kaLyqoS/vrXf2wjrCF8uzZKKZ1UOxK/img.png)

**아래와 같은 Eclipse.app 경로에 eclipse.ini를 선택한다.**

![img](https://blog.kakaocdn.net/dn/buTQnp/btq4lNPOTWv/8ED1j3MksKmKP3Q3mYrqe0/img.png)

![img](https://blog.kakaocdn.net/dn/UUGJ7/btq4jXr02SE/1XFXklkBFwtNHZub5P8wb0/img.png)

![img](https://blog.kakaocdn.net/dn/WdxBK/btq4db6rThT/6AYetCYc2amghsStVKT1KK/img.png)

위와 같이 했다면 설치 종료.

### 주의!! MAC은 /Users/corner/Eclipse.app/Contents/Eclipse/eclipse.ini 로 경로를 잡고 설치해야한다.(윈도우도 에러날 경우 이 방법을 사용해야 한다.)

pom.xml 61번줄에 기존꺼 삭제하고 삽입

![img](https://blog.kakaocdn.net/dn/BJTrx/btq4ddJXA94/iYp0hgCvoisZAPYGkHjlLk/img.png)위 코드를 삭제

위 코드를 삭제하고 아래 코드를 삽입한다.

![img](https://blog.kakaocdn.net/dn/WHO8d/btq4jYxJrxs/dSy7YgdWeJJy2WnV1Zejd1/img.png)

```
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
```

**98번 줄 삽입 및 수정**

![img](https://blog.kakaocdn.net/dn/cv6MoA/btq4hpW7GBG/jwvd0eXcxQnVN1UPFLJ2N1/img.png)

위 코드를 아래 코드로 변경

![img](https://blog.kakaocdn.net/dn/yF1xK/btq4jOBTQjS/4tzjTd4VFmHk1eG98hd661/img.png)

```
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
```

저장 후 -> Maven Update

톰캣 server 생성

![img](https://blog.kakaocdn.net/dn/1gb9s/btq4dwP2tvp/EkscuOI3UY8NWjaR1HSQfK/img.png)

톰캣 생성은 JDK Build Path와 맞아야 한다.

------

## [프로젝트 기본 구성 요소]

![img](https://blog.kakaocdn.net/dn/bl9FB3/btq4kVgfPwa/4kkfFPLj7S8cdcCL6VruZ1/img.png)

`src/main/java` : 작성되는 코드의 경로

`src/main/resources` : 실행할 때 참고하는 기본 경로

`src/test/java` : 테스트 코드를 넣는 경로

`src/test/resources` : 테스트 관련 설정 파일 보관 경로

웹과 관련된 스프링 설정 파일 (Resources, View Resolver)

 `src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml`

스프링 설정 파일 (Bean, IoC 컨테이너)

 `src/main/webapp/WEB-INF/spring/root-context.xml`

Tomcat의 web.xml 파일 (Front Controller, DispatcherServlet)

 `src/main/webapp/WEB-INF/web.xml`

템플릿 프로젝트의 jsp파일 경로

 `src/main/webapp/WEB-INF/views`

Maven이 사용하는 pom.xml

 `[Project]/pom.xml`

※만약 프로젝트에 오류 발생 시
C:\Users\여러분계정폴더.m2\repository 폴더 삭제 후 이클립스 재실행

------

[[Coding/스프링(Spring) Framework\] - 스프링 - 프레임 워크란? 특징, 장점, 라이브러리 개념 등](https://iu-corner.tistory.com/entry/스프링-프레임-워크)

[
스프링 - 프레임 워크란? 특징, 장점, 라이브러리 개념 등학습 목표 이 수업을 들으려면 할 줄 알아야하는 것. 스프링 프레임워크를 이용해서 '의존성 주입'에 대한 이해와 테스트 스프링에서 XML을 이용하는 객체 관리 방법 스프링의 테스트 환경 구축iu-corner.tistory.com](https://iu-corner.tistory.com/entry/스프링-프레임-워크)
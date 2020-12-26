---
layout: post
title: "[Web] Servlet이란?"
date: 2020-12-26 11:05:00 +0900 # +0900 세계협정시(UTC)기준 서울이라는 뜻
comments: true # 코멘트 허용

categories: JSP Lecture # 카테고리


---



[Servlet과 JSP의 차이](/jsp/lecture/2020/12/26/jsp-servlet-차이.html)



# 🌱 Servlet이란?



### Web Service의 기본적인 동작 과정

HTML Form -> Servlet -> HTML Page



1. 사용자가 웹 페이지 form(HTML Form)을 통해 자신의 정보를 입력한다. (input)
2. Servlet의 doGet() 또는 doPost() 메소드는 입력한 form data에 맞게 DB 또는 다른 소스에서 관련된 정보를 검색한다.
3. 이 정보를 이용하여 사용자의 요청에 맞는 적절한 동적 콘텐츠(HTML Page)를 만들어서 제공 (Output)

[1.HTML Form](#1.html-form)

---

### 1.HTML Form

**<span style="color:grey"> input elements(Ex. 텍스트 상자)가 포함된 웹피이지의 한 부분(section)</span>**

- 사용자가 입력한 정보 (form contents)를 웹 서버로 전송하기 위한 submit element(Ex, 버튼)가 존재한다.
- action에는 form을 처리하는 서버 쪽 URL을 명시한다.

#### 

#### 클라이언트(browser)가 요청하는 URL 정보

- **요청을 보낼 서버의 IP 주소 : Port 번호 / App 이름 / 달라고 요청하는 HTML** 
  - <code>Ex) localhost:8080/FormHandingServlet/LoginForm.html</code>
- IP 주소
  - 요청을 보낼 서버의 "위치"를 의미한다.
  - browser와 WAS(tomcat)가 같은 PC에 있으면 "IP 주소 = localhost " or "127.0.0.1"
- Port 번호
  - 해당 위치 안의 "특정 사람"을 의미한다.
- HTML 이름
  - HTML 이름에 해당하는 HTML 문서를 받아온다.
  - broweser를 받아온 HTML 문서 안의 html 태그들 (ex, h2, br 등)을 parsing(기계어 번역)한다.
  - 이 내용을 browser에서 rendering(그리기)을 한다.

```jsp
<form name="loginForm" method="post" action"loginServlet">
  Username: <input type="text" name="username"/> <br/>
  Password: <input type="password" name="password"/><br/>
  <input type="submit"value="Login" />
</form>
```

- method = "post"
  - 원하는 동작에 따라 HTTP 메소드를 사용한다.
  - method="get"
- action="loginServlet"
  - URL of the servlet
  - 해당 url로 request가 간다. 즉, WAS에서의 어떤 Servlet인지 지정하는 것이다.
- type="submit"
  - 버튼을 누르면 사용자가 입력한 인자들이 Servlet으로 넘어간다.



#### Form Tag 속성

Form Tag 속성을 이용하여 어디로, 어떤 방식으로 전송할지 정한다.

- **action:** form을 전송할 서버 쪽 스크립트 파일 지정
- **name:** form을 식별하기 위한 이름 지정
- **accept-charset:** form 전송에 사용할 문자 인코딩을 지정
- **target:** action에서 지정한 스크립트 파일을 현재 창이 아닌 다른 위치에서 열리도록 지정
- **method:** form을 서버에 전송하는 방식으로, HTTP 메소드 지정 (GET 또는 POST)
- **enctype:** 넘기는 Content의 Type 지정 (주로 파일을 넘길 때 사용) 
  - type은 <code>multipart/form-data</code> 로 지정해서 사용



#### Form Methods

form을 서버에 전송하는 방식으로, 두 가지 HTTP 메소드 지정할 수 있다.



1. GET Method
   -  사용자가 입력한 내용(form data)이 URL 뒤이 텍스트 문자열로 추가된다.
     - 크기 제한: 1024 characters
     - data는 ?를 기준으로 action URL과 분리된다.
     - ex. <code>http://www.test.com/hello?key1=value1&key2=value2</code>
   - 브라우저에서 웹 서버로 정보를 전달하는 기본 메서드(Default Method)
     - HTTP 메서드를 지정하지 않으면 GET Method를 호출한다.
   - 서버에 전달하는 data에 암호와 같은 민감한 정보가 있는 경우 GET Method를 사용하지 않는다.
     - URL은 모두에게 노출되는 정보이기 때문에 보안상 적절하지 않다.
   - GET 메서드의 사용
     - Query-Type actions: DB에 영향을 주지 안흔ㄴ 단순히 읽기 위주(read operation)의 작업
     - Idempotemt actions: 몇 번이고 같은 연산을 반복해도 같은 값이 나오는 작업
2. POST Method
   - 사용자가 입력한 내용(form data)을 별도의 메시지로 보낸다.
   - Request Body에 data를 추가한다.
     - URL에 직접적으로 data가 노출되지 않기 때문에 GET Method보다 보안상으로 조금 더 안전하다.
   - POST 메서드의 사용
     - actions with side-effects: DB에 영향을 주는 작업



---

## Servlet이란



#### Servlet의 개념 

<span style="color:purple">웹 기반의 요청에 대한 동적인 처리가 가능한 하나의 클래스</span>

- Server Side에서 돌아가는 Java Program
- 개발자가 작성해야 하는 부분



Servlet Program의 기본적인 동작 과정

![Servlet Programming](servlet-program.png)



1. Web Server는 HTTP request를 Web Container(Servlet Container)에게 위임한다.
   - web.xml 설정에서 어떤 URL과 매핑되어 있는지 확인
   - 클라이언트(browser)의 요청 URL을 보고 적절한 Servlet을 실행
2. Web Container는 service() 메소드를 호출하기 전에 Servlet 객체를 메모리에 올린다.
   - Web Container는 적절한 Servlet 파일을 컴파일(.class 파일 생성) 한다.
   - .class 파일을 메모리에 올려 servlet 객체를 만든다.
   - 메모리에 로드될 때 Servlet 객체를 초기화하는 init() 메소드가 실행된다.
3. Web Container는 Request가 올 때마다 thread를 생성하여 처리한다.
   -  각 thread는 Servlet의 단일 객체에 대한 service() 메소드를 실행한다.



#### Servlet Program에서 Thread의 역할

- **Trhead란	** 운영체제로부터 시스템 자원을 할당받는 작업의 단위
- Servlet Program에서 thread가 수행할 메소드가 지정/할당되면
  - thread는 생성 후 즉시 해당 메소드만 열심히 수행한다.
  - 해당 메소드가 return하면 thread는 종료되고 제거된다.
  - 즉, 실제로 thread의 역할: **Servlet의 doGet() 또는 doPost()를 호출**하는 것이다.
- Web Container(Servlet Container)는 thread의 생성과 제거를 담당한다.
  - 하지만 thread의 생성과 제거의 반복은 큰 오버헤드를 만든다.
  - 이를 위해 Tomcat(WAS)은 "Thread pool" (미리 thread를 만들어 놓음) 이라는 적절한 메커니즘을 사용하여 오버헤드를 줄인다.
- 즉, WAS는 Servlet의 life cycle을 담당한다.
  - 웹 브라우저 클라이언트의 요청이 들어왔을 때 Servlet 객체 생성은 WAS가 알아서 처리한다.
  - WAS 위에서 Servlet이 돌아다니고 개발자는 이 Servlet을 만들어야 한다.

##### Servlet Life Cycle

![Life Cycle](servlet-life-cycle.png)













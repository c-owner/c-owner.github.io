---
layout: post
title:  "(Spring MVC) Front-Controller 패턴"
date: 2021-05-07 09:00:21 +0900
categories: JAVA Lecture


---

# (Spring MVC) Front-Controller 패턴

​    					  HandlerMapping

​      	     ①       		   ↕②       				③        				                ⓸

Client      ↔      DispatcherServlet      ↔      HandlerAdapter      ↔      Controller

​          	 ⓺      	     ↕⓹

​                  		   ViewResolver

사용자(Client)가 요청 req --> servlet-context.xml ---->(URL) DispatcherServlet -->
HandlerMapping( 컨트롤러 검색) --> DispatcherServlet --> HandlerAdapter -> Controller -->
DispatcherServlet --> ViewResolver (전달받은 문자열 앞뒤로 " "경로와 확장자로 응답)(Default 응답페이지는 .JSP이다.



1. 사용자의 **모든** Request는 Front-Controller인 DispatcherServlet을 통해 처리한다.(web.xml 참고)

2. HandlerMapping은 Request의 처리를 담당하는 컨트롤러를 찾기 위해 존재한다.
   HandlerMapping 인터페이스를 구현한 여러 객체 중 `@RequestMapping`, `@Controller` 어노테이션이 적용된 것을 기준으로 판단하며, 적절한 컨트롤러가 찾아졌다면 HandlerAdapter를 이용해서 해당 컨트롤러를 동작시킨다.

3. Controller는 Request를 처리하는 비지니스 로직을 작성하며, View에 전달해야 하는 데이터는 주로 Model 객체에 담아서 전달한다. 이에 대한 처리는 ViewResolver를 이용하게 된다.

4. ViewResolver는 Controller가 리턴한 결과를 어떤 View에서 처리하는 것이 좋을 지 해석하는 역할이다.

   (servlet-context.xml 참고)

   ![img](https://blog.kakaocdn.net/dn/bh0Awt/btq4ga0Evkc/PvW1QnvHpsyowbry4zM910/img.png)

5. 만들어진 응답은 DispatcherServlet을 통해서 전송된다.



------

## Spring MVC Controller 특징

- HttpServletRequest, HttpServletResponse를 거의 사용할 필요 없이 기능을 구현할 수 있다.
- 다양한 타입의 파라미터 처리, 다양한 타입의 리턴 타입 사용 가능 (.jsp)

![img](https://blog.kakaocdn.net/dn/El3Cb/btq4nubSsW3/QjVMdsSQ099MKaNbJZBhA1/img.png)

- GET방식, POST방식 등 전송 방식에 대한 처리를 어노테이션으로 처리 가능
- 상속 / 인터페이스 방식 대신 어노테이션만으로도 설정 가능 



## Model(데이터 전달자)

Model 객체는 컨트롤러에서 생성된 데이터를 담아서 JSP에 전달하는 역할을 한다.

반면 기본 자료형의 경우에는 파라미터로 선언하더라도 화면까지는 전달되지 않는다.



------



![img](https://blog.kakaocdn.net/dn/cjYsFs/btq4kbqCp6A/16tIUYIp7rHE4AS2RPUQ50/img.png)

SampleController.java

```java
package com.koreait.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.koreait.domain.SampleDTO;

import lombok.extern.log4j.Log4j;

@Controller // 자동으로 스프링의 객체(Bean)로 등록된다.
@RequestMapping("/sample/*") // 현재 클래스의 모든 메소드들의 기본 경로 설정(예: /sample/aaa, /sample/bbb 등)
@Log4j
public class SampleController {
	@RequestMapping("") // GET, POST 상관없이 Controller 로직을 수행한다면 method 옵션을 사용하지 않는다.
	public void basic() {
		log.info("basic.............");
	}
	
	// localhost:8080/sample/basic
	@RequestMapping(value="/basic", method= {RequestMethod.GET, RequestMethod.POST}) 
	public void basicGet() {
		log.info("basic GET.............");
	}
	
	@GetMapping("/basicOnlyGet")
	public void basicGet2() {
		log.info("basic....get only get..........");
	}
	@PostMapping("/basicOnlyPost")
	public void basicPost() {
		log.info("basic...get only post......");
	}
	
	@GetMapping("/ex01")
	public String ex01(SampleDTO dto) {
		log.info(dto);
		return "ex01";
	}
  
  @GetMapping("/ex02")
	public String ex02(@RequestParam("data1") String name,@RequestParam("data2") int age) {
		log.info("name : " + name);
		log.info("age : " + age);
		return "ex02";
	}
	
}
```

![img](https://blog.kakaocdn.net/dn/bYTUiY/btq4ouQfNGs/w2kjcjZSduiHA0vCbqzHV1/img.png)

![img](https://blog.kakaocdn.net/dn/xQdIo/btq4f1P76cO/YjQ4vTEeawKsWSxX2TT3Ck/img.png)

URL을 요청하고 로그 찍히는것을 확인한다.



![img](https://blog.kakaocdn.net/dn/YiwMZ/btq4iND4Exr/85MglCi3Eam5nMywv8CESK/img.png)





```shell
http://localhost:8080/sample/ex01?name=AAA&age=10
```

INFO : com.koreait.controller.SampleController - SampleDTO(name=AAA, age=10)

GET 방식으로 넘김.

![img](https://blog.kakaocdn.net/dn/JzF26/btq4jEz9glY/NqkHbKXQlYtPI2A6aTZE11/img.png)

 **Controller는 파라미터 타입에 따라 자동으로 매핑된다.**





---

------

```
	@GetMapping("/ex02")
	public String ex02(@RequestParam("data1") String name,@RequestParam("data2") int age) {
		log.info("name : " + name);
		log.info("age : " + age);
		return "ex02";
	}
```

컨트롤러에 ex02를 추가하는데 param 데이터 이름을 data1 과 data2로 지정한다



url에 http://localhost:8080/sample/ex02?data1=안녕&data2=13 라고 입력하여 param을 전달한다.

![img](https://blog.kakaocdn.net/dn/cPPY8R/btq4lNpsGvD/gJkMNWgnjNDK4gKlkasxDK/img.png)





------

ArrayList형으로 data 전달받기 

```
@GetMapping("/ex03")
	public String ex03(@RequestParam("data") ArrayList<String> datas) {
		log.info("datas :  "+ datas);
		return "ex03";
	}
```



```
localhost:8080/sample/ex03?data=12&data=아니&data=굳
```

URL을 전송하면  콘솔에서 

![img](https://blog.kakaocdn.net/dn/VTy6g/btq4kVOOKGA/NuwpopI7EWNciqDe68lwv1/img.png)

값이 정상적으로 잘 전달 받아지는 것을 확인할 수 있다.



------

배열에 파라미터로 받기

http://localhost:8080/sample/ex04?arr=1&arr=2&arr=3

```java
	// 배열에 파라미터 담기
	@GetMapping("/ex04")
	public String ex04(@RequestParam(value="arr") String[] arr) {
		for (int i = 0; i < arr.length; i++ ) {
			log.info(" arr : ");
			log.info(arr[i]);
		}
		log.info(Arrays.toString(arr));
		return "ex04";
	}
```

Arrays로 문자열 출력을 할 수 있고, 반복으로 출력해도 된다.

![img](https://blog.kakaocdn.net/dn/chDvgC/btq4kGdlMyo/EOwEJCmHrpvvqmMaepW8y0/img.png)



------

## Model(데이터 전달자)



view 폴더에 ex02.jsp 생성

```jsp
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>EX 01</title>
</head>
<body>

<h1>EX01</h1>

<h2>
	Model : name(${sampleDTO.name}) age(${sampleDTO.age})
</h2>
</body>
</html>
```

해당 클래스 명의 앞글자를 소문자로만 바꾸면 가져올 수 있다.  
ex)  String -> string , MemberDTO -> memberDTO



ex02.jsp 생성

```jsp
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ex02</title>
</head>
<body>

<h1>EX02</h1>
<h2>
	Model : name(${string}) age(${int})

<!-- 직접 정의한 모델 객체만 사용이 가능하다. -->
</h2>

</body>
</html>
```

![img](https://blog.kakaocdn.net/dn/cl3xJI/btq4oyyFFXm/ef2kmE8pFyNgdw3vM8l0K1/img.png)

 직접 정의한  모델 객체만 사용이 가능하다. 아래와 같이 한다.

```
http://localhost:8080/sample/ex02?data1=킹기훈&data2=23
```

ex02 jsp 수정

```jsp
Model : name(${name}) age(${age})
```

컨트롤러 수정

```java
@GetMapping("/ex02")
	public String ex02(@RequestParam("data1") String name,@RequestParam("data2") int age, Model model) {
		log.info("name : " + name);
		log.info("age : " + age);
		model.addAttribute("name", name);
		model.addAttribute("age", age);
		return "ex02";
	}
```





------

ex03 컨트롤러

```java
	@GetMapping("/ex03")
	public String ex03(@RequestParam("data") ArrayList<String> datas, Model model) {
		log.info("datas :  "+ datas);
		for(int i = 0; i < datas.size(); i++) {
			model.addAttribute("datas", datas);
		}
		return "ex03";
	}
```

ex03.jsp

```jsp
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ex03</title>
</head>
<body>

<h1>EX03</h1>
<h2>
	Model : name(${datas}) <!-- 이렇게 해도되고, EL문으로 forEach 사용 해도된다. -->

	<c:forEach var="data" items="${datas}">
		<c:out value="${data}"/> &nbsp; &nbsp;
	</c:forEach>
<!-- 직접 정의한 모델 객체만 사용이 가능하다. -->
</h2>

</body>
</html>
```



url

```
http://localhost:8080/sample/ex03?data=킹기훈&data=23
```

------

ex04 컨트롤러

```java
@GetMapping("/ex04")
	public String ex04(@RequestParam(value="arr") String[] arr, Model model) {
		for (int i = 0; i < arr.length; i++ ) {
			log.info(" arr : " + arr[i]);
		}
		model.addAttribute("datas", arr);
		log.info(Arrays.toString(arr));
		return "ex04";
	}
```



ex04.jsp

```jsp
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ex04</title>
</head>
<body>

<h1>ex04</h1>
<h2>
	Model : 
	<c:forEach var="data" items="${datas}">
		<c:out value="${data}"/> &nbsp; &nbsp;
	</c:forEach>
<!-- 직접 정의한 모델 객체만 사용이 가능하다. -->
</h2>

</body>
</html>
```



url : http://localhost:8080/sample/ex04?arr=킹기훈&arr=23



------

ex05 컨트롤러

```java
@GetMapping("/ex05")
	public /*String*/void ex05(SampleDTO dto, @ModelAttribute("gender") String gender) {
		log.info(dto);
		log.info("gender : "+ gender);
//		return "/sample/ex05";
	}
```

ex05.jsp

```jsp
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>EX05</title>
</head>
<body>

<h1>EX05</h1>
<h2>
	Model : name(${sampleDTO.name}) age(${sampleDTO.age})  gender : ${gender}
</h2>

</body>
</html>
```



url : http://localhost:8080/sample/ex05?name=킹기훈&age=23&gender=남자

------











전체 코드



```java
package com.koreait.controller;

import java.util.ArrayList;
import java.util.Arrays;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.koreait.domain.SampleDTO;

import lombok.extern.log4j.Log4j;

@Controller // 자동으로 스프링의 객체(Bean)로 등록된다.
@RequestMapping("/sample/*") // 현재 클래스의 모든 메소드들의 기본 경로 설정(예: /sample/aaa, /sample/bbb 등)
@Log4j
public class SampleController {
	@RequestMapping("") // GET, POST 상관없이 Controller 로직을 수행한다면 method 옵션을 사용하지 않는다.
	public void basic() {
		log.info("basic.............");
	}
	
	// localhost:8080/sample/basic
	@RequestMapping(value="/basic", method= {RequestMethod.GET, RequestMethod.POST}) 
	public void basicGet() {
		log.info("basic GET.............");
	}
	
	@GetMapping("/basicOnlyGet")
	public void basicGet2() {
		log.info("basic....get only get..........");
	}
	@PostMapping("/basicOnlyPost")
	public void basicPost() {
		log.info("basic...get only post......");
	}
	
	// Controller는 파라미터 타입에 따라 자동으로 매핑된다.
	@GetMapping("/ex01")
	public String ex01(SampleDTO dto) {
		log.info(dto);
		return "ex01";
	}
	
	@GetMapping("/ex02")
	public String ex02(@RequestParam("data1") String name,@RequestParam("data2") int age, Model model) {
		log.info("name : " + name);
		log.info("age : " + age);
		model.addAttribute("name", name);
		model.addAttribute("age", age);
		return "ex02";
	}
	
	@GetMapping("/ex03")
	public String ex03(@RequestParam("data") ArrayList<String> datas, Model model) {
		log.info("datas :  "+ datas);
		for(int i = 0; i < datas.size(); i++) {
			model.addAttribute("datas", datas);
		}
		return "ex03";
	}
	
	// 배열에 파라미터 담기
	@GetMapping("/ex04")
	public String ex04(@RequestParam(value="arr") String[] arr, Model model) {
		for (int i = 0; i < arr.length; i++ ) {
			log.info(" arr : " + arr[i]);
		}
		model.addAttribute("datas", arr);
		log.info(Arrays.toString(arr));
		return "ex04";
	}
	
	
	@GetMapping("/ex05")
	public /*String*/void ex05(SampleDTO dto, @ModelAttribute("gender") String gender) {
		log.info(dto);
		log.info("gender : "+ gender);
//		return "/sample/ex05";
	}
	
}

```






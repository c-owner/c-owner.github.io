---
layout: post
title:  "Javascript- 3"
date: 2021-02-17 10:10:00 +0900
comments: true # 코멘트 허용
categories: Lecture

---

## 브라우저 객체 모델 BOM



자바스크립트를 이용하면 브라우저의 정보에 접근하거나 브라우저의 여러 기능들을 제어할 수 있다.
이 때 사용할수 있는 객체 모델을 **브라우저 객체 모델(BOM, Browser Object Model)**이라고 한다.
브라우저 객체 모델은 DOM과 달리 W3C의 표준 객체 모델은 아니다.

하지만 자바스크립트가 브라우저의 기능적인 요소들을 직접 제어하고 관리할 방법을 제공해준다.
이러한 BOM객체들은 전역 객체로 사용할 수 있다.





---


- <span style="color: purple">**window 객체**</span>

웹 브라우저의 창을 나타내는 객체로, 대부분의 웹 브라우저에서 지원한다.
자바스크립트의 모든 객체, 전역 함수, 전역 변수들은 자동으로 window객체의 프로퍼티가 되고 
window객체의 메소드는 전역함수, window객체의 프로퍼티는 전역변수가 된다.
DOM요소들도 모두 window객체의 프로퍼티가 된다.



```javascript
window.onload = function() {}
```

스크립트 언어는 위에서 아래로 해석되기 때문에 DOM에서 HTML요소를 추출할 때 body보다 위에 있으면 해석 순서에 오류가 생길 수 있다.

이 경우 자바스크립트 문서를 밑으로 옮겨야 하는데, HTML문서가 길어지면, 

가독성도 좋지 않고 불편할 수 있다. 따라서 window.onload를 사용하면 

문서가 준비된 상황 이후에도 발동시킬 수 있다.



```javascript
window.open()
```

window 객체의 open() 메소드를 이용하면, 새로운 브라우저 창을 열 수 있으며,

새로운 브라우저 창의 세부적인 옵션들도 일일이 설정할 수 있다.



```javascript
var 객체명 = window.open(url, name, specs, replace);
```

1) **객체명** : 새로 만들어진 창 객체가 반환되고 창의 생성에 실패하면 null을 리턴한다.
					이 객체를 통해서 새창을 제어할 수 있고, <span style="color: green">객체명.close();</span>로 창을 닫을 수 있다.

2) **url** : 주소

3) **name** : 

> ​			_blank(default) : 새 창  
> ​			_parent : 부모 프레임     
> ​			_child : 현재 페이지의 자식 페이지(서브 창)  
> ​			_self : 현재 페이지

  

4) **specs** : 선택적인 값으로 창의 크기, 스크롤 여부, 리사이즈 가능 등의 속성을 지정한다.

>  - heigt = 픽셀 : 창의 높이    
>    width = 픽셀 : 창의 너비   



5) **replace** : 히스토리 목록에 새 항목을 만들지, 현재 항목을 대체할 지 지정한다.

> - true : 현재 히스토리 대체  
>   false : 히스토리에 새 항목 만들기 



**window.setTimeout(function(){}, 밀리초);**
		밀리초만큼 멈춘 후 실행하는 문법

```javascript
	window.onload = function(){
		window.setTimeout(function(){ // 3초를 쉬고 function 함수 실행
			// false 보류 (확인을 제대로 하기 어려움..) default 값 
			// true : 현재 히스토리 대체 (뒤로가기 방지)
			// var w = window.open("https://www.naver.com", "_self", false);
			var w = window.open("https://www.naver.com", "_child", "width=600, height=300",true);
		}, 3000);
	}
```



---

<span style="color: purple">**location객체**</span>

location객체는 현재 브라우저에 표시된 HTML문서의 주소를 얻거나, 브라우저에 새 문서를 불러올 때 사용할 수 있다.

location객체의 프로퍼티와 메소드를 이용하면, 현재 문서의 URL 주소를 다양하게 해석하여 처리할 수 있게 된다.

> - href : 페이지의 URL 전체 정보 반환. URL을 지정하여 페이지 이동 가능.

1. **<span style="color: #4dd0e1">document.write(location.href);</span>** : 현재 페이지 정보 반환
2. **<span style="color: #4dd0e1">location.href = "주소 또는 파일 경로";</span>** : 작성한 경로 페이지로 이동

```javascript
	with(document) {
		write("현재 문서의 URL 주소는 "+ location.href+ "입니다.<br>");
		write("현재 문서의 호스트 이름은 "+ location.hostname+ "입니다.<br>");
		write("현재 문서의 파일 경로명은 "+ location.pathname+ "입니다.<br>");
		write("현재 문서의 포트명은 "+ location.port+ "입니다.<br>");
	}
```

> - pathname : URL경로 부분의 정보를 반환한다.
> - port : 포트 번호를 반환한다.
> - reload() : 새로고침



```html
<title>location 객체 예제 2</title>
</head>
<body>
	<form name ="myform">
		<p>
				<label>네이버<input type="radio" value="naver" name="site"></label>
			</p>
		<p>
				<label>다음<input type="radio" value="daum" name="site"></label>
			</p>
		<p>
				<label>구글<input type="radio" value="google" name="site"></label>
			</p>
		<p>
			<label><input type="button" onclick="send_it()" value="이동"></label>
		</p>
	</form>
</body>
<script>
	function send_it(){
		var frm = document.myForm; // form태그의 name은 객체로 생성되기 때문에 바로 담을 수 있다.
		var choice = frm.site.value;
		var url = "";
		switch(choice){
			case "naver":
				url = "https://www.naver.com";
				break;
			case "daum":
				url = "https://www.daum.net";
				break;
			case "google":
				url = "https://www.google.com";
				break;
			default:
				alert("다시 시도해주세요.");
				location.reload();
		}
		location.href = url;
	}
```





> - assign() : 현재 URL을 지정한 URL로 바꿔 페이지 이동.
> - replace() : 현재 URL을 지정한 URL로 바꾸고 이전 페이지로 돌아갈 수 없게 한다.

<span style="color: #4dd0e1">assign()</span> 

​	A페이지 -> B페이지 -> C페이지

​										<- 뒤로가기 시 B 페이지로 이동

<span style="color: #4dd0e1">replace()</span> 

​	A페이지 -> B페이지 -> C페이지

​									<- 뒤로가기 시 A페이지로 이동 



```html
<title>location 객체 예제 3</title>
</head>
<body>
		<h1>히스토리 목록의 개수</h1>
		<p>아래 버튼을 눌러 새로운 문서를 연 후, 브라우저의 뒤로가기 버튼을 눌러보세요!</p>
		<button onclick="openDocument()">새로운 문서열기 </button>
		<p id="text"></p>
</body>
<script>
	function openDocument(){
		//응답방식
		
		// assign : 뒤로가기 가능
		// location.assign("history_test.html");
		
		// replace : 뒤로가기 불가능
		location.replace("history_test.html");
	}
	
	document.getElementById("text").innerHTML = 
				"현재 브라우저의 히스토리 목록의 개수는 " + history.length + "개 입니다."
</script>
```

```html
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>history 객체 예제</title>
</head>
<body>
	<h1>히스토리 목록의 접근</h1>
	<p>아래 버튼을 눌러보세요.</p>
	<button onclick="goBack()">뒤로</button>
	<button onclick="go()">원하는 곳으로 이동</button>
	<button onclick="goForward()">앞으로</button>
</body>
<script>
	function goBack(){
		window.history.back();
	}
	function go(){ 
		history.go(-1); //   window의 프로퍼티이기 때문에 생략이 가능하다.
	}
	function goForward(){
		window.history.goForward();
	}
</script>
</html>
```



---

<span style="color: purple">**history 객체**</span>

history 객체는 브라우저의 히스토리 정보를 문서와 문서 상태 목록으로 저장하는 객체이다.

> - go(n) : 0부터 시작     
>
>   > go(1) : 앞 페이지 이동(n은 n페이지 앞으로 이동)  
>   > go(-1) : 뒤 페이지로 이동 (-n은 n페이지 뒤로 이동)  



---

##### 
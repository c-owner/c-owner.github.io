---
layout: post
title:  "Javascript - 1"
date: 2021-02-09 10:10:00 +0900
comments: true # 코멘트 허용
categories: Lecture

---

# 자바스크립트(Javascript)



## 자바 스크립트 언어란?

1. 퍼즐 조각처럼 코드 형태로 HTML페이지에 내장된다.
2. 컴파일 과정 없이 브라우저 내부의 자바스크립트 처리기(인터프리터)에 의해 실행된다.

```javascript
[index.html]  					자바스크립트 처리기
<script>				->							-> 		결과출력 
    자바스크립트 코드 			인터프리터	
    </script>				 컴파일 없이 바로 실행 
```



#### 웹 페이지에서 자바스크립트의 역할

​	웹 페이지는 3가지 코드(HTML, CSS, JS)가 결합되어 작성된다.

​	자바스크립트는 사용자의 입력을 처리하거나 웹 애플리케이션을 작성하는 등

​	웹 페이지의 동적 제어에 사용된다.



**자바스크립트를 배우는 이유 5가지**



- **사용자의 입력 및 계산**
  - HTML폼은 입력 창만 제공하고
    키, 마우스의 입력과 계산은 오직 자바스크립트로만 처리가 가능하다.

- **웹 페이지 내용 및 모양의 동적 제어**(DOM)
  - HTML태그의 속성이나 콘텐츠, CSS 속성 값을 변경하여
    웹 페이지에 동적인 변화를 일으키는 데에 활용된다.

- **브라우저 제어**(BOM)
  - 브라우저 윈도우의 크기나 모양 변경, 새 윈도우나 탭 열기, 다른 웹사이트 접속
    브라우저의 히스토리 제어 등 브라우저의 작동을 제어하는 데 활용된다.

- **웹 서버와의 통신(Ajax)**
  - 웹 페이지가 웹 서버와 데이터를 주고 받을 때 활용된다.

- **웹 애플리케이션 작성**
  - 자바 스크립트 언어로 활용할 수 있는 많은 API를 제공하므로,
    웹 브라우저에서 실행되는 다양한 웹 애플리케이션을 개발할 수 있다.

---

#### 자바스크립트 코드의 위치

1. HTML 태그의 이벤트 리스터 속성에 작성

   - HTML 태그에는 마우스가 클린되는 등 이벤트가 발생할 때 처리하는 코드를
     등록하는 리스너 속성이 있다. 이 속성에 자바스크립트 코드를 작성할 수 있다.

2. ```javascript
   <script></script> 내에 작성
   <script></script>는 <head></head>, <body></body>, body태그 밖 등
   어디든 들어갈 수 있다.
   웹 페이지 내에서 여러 번 작성할 수 있다.
   ```

   

3. 자바스크립트 파일에 작성

   - 자바스크립트 코드를 확장자가 .js인 별도의 파일로 저장하고,

     ```javascript
     <script src=".js경로"></script>를 통해 불러서 사용한다.
     ```

4. URL 부분에 작성

   ```javascript
   <a> 태그의 href 속성 등에도 자바스크립트 코드를 작성할 수 있다.
   ```

   

---

### 자바스크립트로 HTML 콘텐츠 출력

자바스크립트 코드로 HTML 콘텐츠를 웹 페이지에 직접 삽입하여

브라우저 윈도우에 출력되게 할 수 있다.

이 때 document.write() 혹은 document.writeln()을 사용한다.



※ writeln()은 줄바꿈(\n)문자가 삽입된다. 하지만 HTML에서 줄바꿈으로 표현되지 않는다.

```javascript
<pre>태그
```

를 활용해주면 여러번의 공백과 개행문자가 인식된다.

---

### 자바스크립트 다이얼로그 : 사용자 입력 및 메시지 출력

​	사용자에게 메세지를 출력하거나, 입력을 받을 수 있는 3가지 다이얼로그가 있다.



- 프롬프트 다이얼로그

  > ​	prompt("메세지", "디폴트 입력값");

  디폴트 입력값은 생략이 가능하다. 

  사용자가 입력한 문자열 값을 리턴하지만
  아무 값도 입력되지 않으면 ""를 리턴, 취소나 강제로 닫으면 null을 리턴한다.

- 확인 다이얼로그

  > confirm("메세지");

  확인/취소 버튼을 가진 다이얼로그를 출력한다.

  ```html
  <script>
  		if (confirm("전송할까요?")){
  				alert("전송 완료");
  		}
  		else {
  				alert("전송 실패");
  		}
  	</script>
  ```




​	
​	
​	
​	

- 경고 다이얼로그

  > alert('메세지');

```javascript
<script>
	var data = prompt("이름을 입력하세요.", "홍길동");
	/*  data는 사용자가 입력한 이름 값이 될 것이다. */
	var msg ="";
	
	if (data == null ) {
		msg = "취소 버튼을 누르셨거나 강제로 닫았습니다.";
	}
	else if (data == ""){
		msg = "입력 없이 확인 버튼을 눌렀습니다.";
	}
	else {
		msg = "성함 : "+data;	
	}
/* 	if(data == null ){
		document.write("취소 버튼을 누르셨거나 강제로 닫았습니다.");
	}
	else if ( data == "" ){
		document.write("입력 없이 확인 버튼을 눌렀습니다.");
	}
	else {
		document.write("성함 : "+data);
	}  */
	</script>
```







---

## 데이터 타입과 변수

1. #### 자바스크립트 식별자

   식별자 (identifier)란 자바스크립트 개발자가 변수, 상수, 함수에 붙이는 이름이다.
   식별자를 만들 때 다음 규칙을 준수해야 한다.

   > 첫 번째 문자 		: 알파벳, 언더바, $문자만 사용 가능
   >
   > 두 번째 이상 문자 : 알파벳, 언더바, 0-9, $ 사용가능
   >
   > 대소문자 구분 	 : data와 dAta는 다른 식별자이다. 대소문자 구별한다.
   >
   > + $로 시작되는 식별자는  jQuery 객체를 나타낸다는 의미이다.
   >
   > ```javascript
   > 6variable 	(x)
   > student_ID	(O)
   > _code				(O) : 언더바로 시작하는 이름은 좋지 않다.
   > if					(X) : 키워드 사용 불가
   > %calc				(X) : % 사용불가  
   > bar 				(O) : 
   > ```
   >
   > 

2. #### 문장 구분

   세미콜론으로 문장과 문장을 구분한다.
   한 줄에 한 문장만 있는 경우 세미콜론을 생략할 수 있다.

   ```javascript
   i = i + 1 						(O)
   j = j + 1;						(O)
   k = k + 1; m = m + 1; (O)
   n = n + 1 p = p + 1		(X)
   ```

3. ####  주석

   ```javascript
   // 한줄 주석    ==> 보통 한줄주석을 잘 안되어서 범위주석을 많이 사용한다.
   /* 범위 주석 */ 
   ```

4. #### 데이터 타입

   - 숫자 타입(Number) : 40, 132.4865, •••

   - 논리 타입 : true, false

   - 문자열 타입 : '안녕', "a", "345", "1+2", •••

   - 객체 레퍼런스 타입(Object) : 객체를 가리킴. C언어 포인터와 유사

   - undefined, null

     -  undefined : 타입이 정해지지 않은 것을 의미
        	**var data;** 
     -  null : 값이 정해지지 않은 것을 의미

     > undefined는 초기화되지 않은 변수나 존재하지 않는 값에 접근할 때 반환된다.

5. ####  **변수**

   - var 키워드로 변수를 선언한다.

     ```javascript
     var score; 						// undefined라는 값으로 초기화
     var year, month, day; // 3개의 변수를 한 번에 선언
     var address = "서울시"; // 문자열 타입
     ```

   - var 키워드 없이 변수를 선언한다.

     ```javascript
     age = 20; // 이중적인 의미 : 이미 age가 선언된 변수라면 존재하는 age 값 수정
     ```

     > var로 변수를 선언하는 것이 명료하고, 실수를 막을 수 있다.

   - **변수의 특징**

     - 타입이 정해진 상태로 할당되지 않기 때문에 어떠한 타입의 값이라도 
       하나의 변수에 담을 수 있다.
       data = 10; 
       data = "한동석";

   - **지역변수와 전역변수**(global scope, block scope 라고하는데, 지역변수 전역변수는 이해를 돕기 위한 설명일 뿐이다.) 

     - 변수의 사용범위(scope)에 따라서 전역변수(global)와 지역변수(local)로 나뉜다.

       - 전역 변수 : 함수 밖에서 선언되거나 함수 안에서 var 키워드 없이 선언.
       - 지역 변수 : 함수 안에서 var 키워드로 선언. 선언된 함수 내에서만 사용가능.

       ```javascript
       var x; // 전역 변수 
       
       function f() {
         var y; 	// 지역 변수
         x = 10; // 전역 변수
         z = 10;	// 전역 변수
         y = 10; // 지역 변수
       }
       ```

   - ###### this로 전역변수 접근

     ```javascript
     var x; 		// 전역 변수
     function f(){
     		var x; // 지역 변수
     	  x = 1; // 지역 변수
       this.x = 1; // 전역 변수
     }
     ```

   ```javascript
   <!DOCTYPE html>
   <html>
   <head>
   <meta charset="UTF-8">
   <title>지역변수와 전역변수</title>
   </head>
   <body>
   	<H3>지역 변수와 전역변수</H3>
   	<hr>
   </body>
   
   <script>
   	var x = 100; // 전역변수
   	function f() {
   		var x = 1; // 지역 변수
   		with(document){
   			write("<pre>");
   			writeln("지역변수 x = "+ x);
   			writeln("전역변수 x = "+this.x);
   			write("</pre>");
   		}
   	}
   	
   	f(); // 함수 실행
   </script>
   
   </html>
   ```

   

6. #### 상수

   let : 재할당가능
   const : 재할당 불가능

   

   *let

   ```javascript
   var a = 'test';
   var a = 'test2';
   c = 'test';
   var c;
   
   *let 
   let a = 'test'
   let a = 'test2' // 오류
   a = 'test3' // 가능
   ```

   *const (상수)

   ```javascript
   const b = 'test'
   const b = 'test2' // 오류 
   b = 'test3' 	// 오류
   ```


##### 예제 - const, let

```javascript
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>상수(let)</title>
</head>
<body>
</body>
<script>
	/* for(var i = 0; i<10; i++ ){ */
// var로 선언하게 되면 for문 밖에서도 사용이 가능하다.(변경될 가능성이 있다.)
	for(let i = 0; i<10; i++ ){ // let으로 선언하게되면 해당지역 밖에서는 사용할 수 없다.(변경 혹은 사용 시 오류) 
		console.log(i + 1);
	}
	console.log(i);   // let으로 선언 될 경우 사용시 오류가 발생한다.
	// let.html:14 Uncaught ReferenceError: i is not defined
	
		// let이라는 친구는 다른지역에서 사용할 수 없다.
	
	</script>
</html>
```

```javascript
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>상수</title>
</head>
<body>

</body>
<script>
			var a = 'test';
			var a = 'test2';
			b = 'test';
			var c;
			
			let l = 'test';
			// let l = 'test2'; // 오류, (재선언 불가능)
			l = 'test3'; // 재할당 가능
			
			const con = 'test';
//			const con = 'test2';  // 오류, (재선언 불가능)
// 		Uncaught SyntaxError: Identifier 'con' has already been declared
			
			// con = 'test2'; // 오류 (재할당도 불가능)
			
			
			
</script>
</html>
```





---



## 함수(function)

```javascript
function 식별자(arg1, arg2, ... argn) {
    코드 작성
    return 리턴값;
}
```

**function** : 함수 선언 표시
**식별자** : 함수명(동사로 작성)
**매개변수** : 여러 개 있을 때 콤마(,)로 분리하고, 생략도 가능하다.
**return** : 함수의 실행 종료 후 리턴 값을 반환.

기초함수는 위와 같이 사용한다.

매개변수에는 함수도 들어올 수 있다, 함수를 전달 받으면 그것을 **콜백함수**라고 한다.

함수에 저장공간이 존재하는데, 코드 작성한 부분에 결과를 전달받은 함수의 매개변수로 전달한다.

이것을 스프링에서 이용한다.

```javascript
var data = function a(t1, t2);
f(1, data);
data.a // 를 따로 t1,t2 를 사용할 수 있게된다.

function f(data, callback) {
    if(callback){
		callback(3, 5);
    }
}
```



##### 기초 함수 예제

```javascript
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>구구단</title>
</head>
<body>
			<h1>구구단</h1>
		<h3>1~9사이의 구구단을 출력</h3>
</body>
<script>
	
	function printGugudan(dan) {
		// 사용자가 입력한 값이 실수인지 정수인지 판단
		
		// check가 true일 때 실수, false라면 정수
		var check = dan - parseInt(dan) != 0.0; // 소수점 분리 함수
		
		// 하지만 false일 지라도 2.0, 3.0, .. 인 값을 false로 판단한다.
		// false일 때에는 무조건 정수로 형변환해준다.
		// 2.0 * 2가 아니라 2 * 2가 나올 수 있게 해준다.
		
		if(!check){
			dan = parseInt(dan);
		}
		// is Not a Number , 숫자가 아니면 true (잘못입력한 경우)
	 	if(isNaN(dan) || dan < 1 || dan > 9 || check) {
			alert("잘못 입력하셨습니다.");
			location.reload(); // 새로고침
			return; // 함수 종료
		}
		
		for(let i=1; i<=9; i++) {
			document.write(dan + " * " + i + " = " + (dan) * i + "<br>");
		}
		
	}
	
	var dan = prompt("몇 단을 보고 싶으세요?", "1~9");
	printGugudan(dan);
	
</script>
</html>
```



---

### 자바스크립트의 전역 함수

- parseInt() 
  : 문자열을 숫자로 변환한다. 
  parseInt("32") === 32 (자바 스크립트에서 === 연산은 타입과 값이 모두 같은 지 비교한다. )

  ```javascript
  <title>등호 비교</title>
  </head>
  <body>
  </body>
  <script>
  	console.log("32" == 32); // true
  	console.log("32" === 32); // false
  	console.log(parseInt("32") === 32 ); //true
  </script>
  ```

  

- isNaN()
  : 숫자가 아니면 true, 숫자가 맞으면 false. do~while문과 비슷하다. (참일 경우 반복돌리듯, 이것도 참일 경우 isNan함수 코드를 실행한다. )
  NaN은 숫자가 아님을 나타내는 상수 키워드이다.

  isNaN(32) : false
  isNaN("32") : false
  isNaN("hello") : true // 숫자가 아니므로 참
  isNaN(NaN) : true // 숫자가 아니므로 참

- isFinite() 
  : 숫자면 true, 아니면 false  
  **isNaN() 함수의 반대이다.**

- eval()
  : 문자열 형태로 수식을 전달받아서 수식을 계산한다.
  eval("2*3+4+6") == 30 

```javascript
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>전역 함수</title>
</head>
<body>
	<h3>전역 함수</h3>
	<hr>
</body>
<script>
		function checkGlobalFunctions() {
			var result = eval("2*3+4*6");
			var num = parseInt("32");
			var value = parseInt("hello");
			
			document.write("eval('2*3+4*6')은 "+ result + "<br>");
			document.write("parseInt('32')은 "+ num + "<br>");
			
			console.log(value);
			
			if(isNaN(value)){
				document.write(value + '는 숫자가 아닙니다.');
			}
			
		}
		
		checkGlobalFunctions();
</script>
</html>
```





---



### 실습 (1~4)



문제1

마우스를 올려보세요.

여기에 마우스를 올리면 배경이 노란색으로 변합니다.
	힌트: 
		"여기에 마우스를..." 이 부분에 마우스 이벤트를 사용하여 배경색을 변경한다.
		초기 배경색은 흰색으로 한다.


```javascript
<body>
		<!--  
			마우스를 올랴보세요.
			---------------
			여기에 마우스를 올리면 배경이 노란색으로 변합니다.
			
			**
			"여기에 마우스를..." 이 부분에 마우스 이벤트를 사용하여 배경색을 변경한다.
			초기 배경색은 흰색으로 한다.
			
		-->
			<h3> 마우스를 올려보세요.</h3>
	<hr>
	<div onmouseover="body.style.background='yellow' "  onmouseout="body.style.backgorund='white' ">
				여기에 마우스를 올리면 배경이 노란색으로 변합니다.</div> 
	
</body>
```

---

**문제2**



월화수목금토일

====================

000은 출근



* 사용자에게 요일을 입력받고 월~목은 출근을, 다른 날은 휴일을 출력한다.
* 요일은 "월","화",.... 형식으로 입력 받는다. switch문을 이용한다.

```javascript
<body>
	<h3>월화수목금토일 </h3>
	<Hr>
	
</body>
<script>
	var day = prompt("요일을 입력하세요", "월,화,수,목,금,토,일");
	switch (day) {
	case "월":
	case "화":
	case "수":
	case "목":
		document.write(day + "요일은 정상출근");
		break;
	case "금":
	case "토":
	case "일":
		document.write(day + "요일은 휴일");
		break;
	default:
		document.write("잘못 입력 하셨습니다");
	}
</script>
```



---

**문제3**



분석 결과

============

통과!



*정확한 암호가 입력될 때 까지 계속 prompt()를 사용하여 암호를 입력받는다. 
암호는 "호랑이"이다. while문으로 사용한다.



```javascript
<body>
    		<h3>분석 결과</h3>
		<hr>
</body>
<script>
	var code = '호랑이';
	var check = false;
	var cnt = -1;
	while(!check){
		cnt++;
		var pw = prompt('암구호를 입력하세요.'); // 입력 창이 나왔을 때 새로고침을 하면 입력이 된 것으로 인식된다.
		/* check = pw == code; */
		if(code == pw) {
			check = true;
		} else {
			alert('잘못 입력하셨습니다. 다시 입력하세요.');
		}
		if(cnt > 2) {
			alert('너무 많이 틀렸어.');
			document.write("너무 많이 틀렸어~");
			break;
		}
	}
	if(check)alert('PASS!');
	if(check)document.write('통과!');
	
</script>
```





---

**문제4**

pr("%", 5);
			결과 : %%%%%
		

위의 결과가 나오는 함수를 선언하여 사용한다.

단, 최소 한 번은 출력되어야 한다.





```javascript
<script>
	function pr(str, cnt) {
			msg = "결과 : ";
			for(let i = 0; i < cnt; i++ ){
				msg += str;			
			}
			if(cnt < 1 ) {
				msg = "최소 한 번은 출력되어야 합니다.";
			}	
			return msg;
	}
	document.write(pr("%", 5));
	pr("%", cnt);
</script>

```



---







---

자바스크립트 객체(프로퍼티, JSON)

DOM - 실습 

BOM

웹 표준(HTML, CSS, JS) 전체 예제 및 실습

프론트 개인 프로젝트 진행

**이렇게하면 웹표준 끝**

-------------------------------

서블릿, JSP

---


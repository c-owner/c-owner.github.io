---
layout: post
title:  "Javascript-2 "
date: 2021-02-10 10:10:00 +0900
comments: true # 코멘트 허용
categories: Lecture

---

### 2020-02-10

## 자바스크립트 객체

- 객체의 고유한 속성을 프로퍼티(property)라고 부르며, 여러 프로퍼티와 값의 쌍으로 표현된다.
  객체가 호출하는 함수는 메소드라고 부른다.



예시

[객체] 

​		account.money 

[프로퍼티]

​		money : 3000,
​		date : '2021-02-10',
​		owner : '한동석',
​		number : 110-1212-12345,
​		code : '1234'



[메소드]

​	deposit()
​	withdraw()
​	show_balance()



[문법]

  var account = {

​	  money : 3000,

​	  date : '2021-02-10',

​	  owner : '홍길동',

​	  number : 110-1212-12345,

​	  code : '1234',

​	  deposit : function() { . . . },

​	  // deposit 이라는 메소드에 함수를 저장하는 것이다. 즉, 메소드는 저장공간이라는 것임을 알 수 있다.

​	  withdraw : function() { ... }, 

​	  show_balance : function() { ... }

}



```html
<title>사용자 객체 선언 및 사용</title>
</head>
<body>
</body>
<script>
/*  
 자바 스크립트에서 직접 만들어서 객체에 담는 것은 자주 사용하지 않는다.
 외부에서 전달받은 JSON 타입의 데이터를 추출할 때 주로 사용되며,
 Ajax 통신으로 여러 개의 값을 서버에 전달할 때 {}로 묶어서 전달하는 방식으로 자주 사용된다.
따라서 .와 ['key'] 방시그 {key : 'value'} 방식, 이렇게 두 가지 방식만 연습하면 된다. 
*/
	var account = { /* account 객체 */
			owner : "홍길동",
			code : "1234",
			balance : 100000,
			deposit : function(money){ 
				this.balance += money; /* 객체안에서 사용할 때는 무조건 this를 사용하여 객체 내의 프로퍼티를 인식한다. */ 
			},
			withdraw : function(money){
				this.balance -= money;				
			}, /* 프로퍼티 */
			show_balance : function(){
				return this.balance;
			}
	}
	
	with(document){
		write("<b>account : </b>");
		write(account.owner + ", ");
		write(account.code + ", ");
		write(account['balance']+'원 <br>'); /* 쓰는방법 두 가지, 요것은 Java에서 HashMap 구조 */
		/* 반드시 문자열 값으로 키값을 전송해주어야 한다.  */	
	}
		account.deposit(50000); /* 함수 프로퍼티, 즉 메소드를 사용할 때는 소괄호를 붙여야한다. */
		document.write("현재 잔액 : "+account.show_balance());
</script>
</html>
```



---

## 자바스크립트 객체의 유형

- 코어 객체 (내장 객체)
  - Array, Date 타입 등..
  - 웹 페이지나 웹 서버 응용프로그램 어디서나 사용할 수 있다.
  - 코어 객체의 생성은 항상 new 키워드를 사용한다.



Javascript 배열로 별 찍기 예제

```javascript
<title>[]로 배열 만들기</title>
</head>
<body>
		<h3>[]로 배열 만들기</h3>
		<hr>
</body>
<script>
		var datas = [20, 5, 7, 15, 10];  /* 배열 선언 */ 
		document.write("var datas = [20, 5, 7, 15, 10]<br>");
		// datas.length : 해당 배열의 길이(변수)
		// 대괄호로 각 인덱스에 접근한다.(datas[0])
		
		/* 요소 안에 있는 정수 만큼 별(*) 출력 */
		var star = "*";
		for(let i=0; i < datas.length; i++ ){
			for (let j = 0; j < datas[i]; j++) {
				document.write("*");
			}
				document.write("<br>");
		}
		
</script>
</html>
```



### Date 객체의 주요 메소드

---

   var 객체명 = new Date();

   getFullYear()   : 4자리 년도 리턴
   getMonth()   : 0~11사이의 정ㅅ수 리턴(0:1월, 1:2월,...)
   getDate()   : 한 달 내의 날짜 리턴(28~31)
   getDay()   : 한 주 내 요일을 정수로 리턴(일요일 : 0,...)
   getHours()   : 0~23사이의 정수 리턴
   getMinutes()   : 0~59사이의 정수 리턴
   getSeconds()   : 0~59사이의 정수 리턴
   getMilliseconds() : 0~999사이의 정수 리턴
   getTime()   : 1970년 1월 1일 0시 0분 0초 기준 현재까지

####            경과된 밀리초 리턴

   setFullYear(year) : 년도 저장
   setMonth(month)   : 월 저장
   setDate(date)   : 한 달 내의 날짜 값 저장
   setHours(hour)   : 시간 저장
   setMinutes(minute) : 분 저장
   setSeconds(second) : 초 저장
   setMilliseconds(ms) : 밀리초 저장

####    setTime(t)   : 밀리초 단위인 t 값으로 시간 저장

   toUTCString()   : UTC문자열로 리턴
   toLocaleString() : 로컬 표현의 문자열로 리턴
   toLocaleTimeString() : 로컬 시간 표현

---



#### **예제**

```javascript
<script>
	var week = new Array("월", "화", "수", "목", "금", "토", "일");
	
	week.length = 3;
	console.log(week.length);
	for (let i = 0; i < week.length; i++ ){
		document.write(week[i]);		
	}
	document.write("<br>"+week.join()); 
	document.write("<br>"+week.join("-")); 
	/* join() 메소드를 통해 구분점을 이용할 수 있으며, 매개변수 값에 구분점을 넣어주면 원한 구분점으로 가능하다. */
</script>
```



#### 예제

초를 기준으로 짝수이면 문서의 배경색을 violet 색상으로,
	홀수이면 lightskyblue 색상으로 변경

document.body.style.backgroundColor = "색상명";

```javascript
<body>
			<h3>방문 시간에 따라 변하는 배경</h3>
			<hr>
</body>
<script>
			var now = new Date();
			if(now.getSeconds() % 2 == 0){
				document.body.style.backgroundColor = "violet";
			} else {
				document.body.style.backgroundColor = "lightskyblue";
			}
			with(document) {
				write("현재 시간 : ");
				write(now.getHours() + "시 ");
				write(now.getMinutes() + "분 ");
				write(now.getSeconds() + "초 ");
				write(now.toUTCString());
			}
</script>
```



#### 예제

Math.floor(Math.random() * n ) 
	floor는 소수점 버림

 n * m 정답은 ?  
 사용자 입력한 값과 정답비교 
 정답, 오답 시 각각 알맞는 메세지 출력



방법 1 

```html
<title>Math를 이용한 구구단 연습</title>
</head>
<body>
	<h3>Math를 이용한 구구단 연습</h3>
	<hr>
</body>
<script>
		var check = true;
	while (check) { 
		var n = Math.floor(Math.random() * 9) + 1;
		var m = Math.floor(Math.random() * 9)+ 1; // 0이 안나오게 하기 위해 +1을 했음.
		var result = n * m;
		var que = prompt(String(n) + " * " + String(m) + "정답은?");
		
		if (result == Number(que)) {
			alert('정답입니다.');
			if (confirm('다음 문제를 푸시겠습니까? ')) {
				continue; // 새로고침이 아닌, continue를 통해 반복문으로 보내야 
				// confirm 에서 취소할 때, 문제를 한 번더 진행하지 않고도 한 번에 취소가 가능하다.
			} else {
				alert('gg');
				check = false; // 구구단 종료지점
			}
		} else {
			alert('틀렸습니다!! 공부하세요!');
			window.location.href="https://namu.wiki/w/%EA%B5%AC%EA%B5%AC%EB%8B%A8";		
			check = false; // 구구단 종료지점
		}
	}
</script>
```



방법 2 

```javascript
	function getRandomInt(){
		return {num1 : Math.floor(Math.random() * 9 ) + 1, num2 : Math.floor(Math.random() * 9) + 1 };
	}
	var nums = getRandomInt();
	var q = nums.num1 + " * " + nums['num2'];
	var answer = eval(q);
	var msg = "";
	
	var myAnswer = prompt(q + " 값은 얼마일까요? ", 0);
	
	if(myAnswer == null ) {
		msg = "연습종료";
	} else {
		msg = answer == myAnswer ? "정답!" : "오답..";						
	}
	msg += "<br>" + q + " = " + "<strong>" + answer + "</strong>";
	
	document.write(msg);
```



---

#### object

```html
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>사용자 객체</title>
</head>
<body>
</body>
<script>
		function show_balance(){
			return this.balance;
		}
		
		function deposit(money){
			this.balance += money;
		}
		function withdraw(money){
			this.balance -= money;
			return money;
		}

		var account = new Object();	
		// 비어 있는 account
		account.owner = "홍길동";
		account.code = "1234";
		account.balance = 100000;
		// 3개의 프로퍼티 직접 선언
		
		account.show_balance = show_balance; // 기존에 있는 함수를 집어 넣을땐 함수 이름만 써주면 된다.
		account.deposit = deposit;
		account.withdraw = withdraw;
		
		with(document){
				write("account : ");
				write(account.owner + ", ");
				write(account.code + ", ");
				write(account.balance + "원 <br>");
		}
		
		account.deposit(10000);
		document.write("10,000원 입금 후 잔액 : "+ account.show_balance() + "원 <br>");
		document.write(account.withdraw(500000) + "원 출금 후 잔액 : "+account.show_balance() + "원");
</script>
</html>
```



```html
<title>프로토타입을 사용한 객체 선언 및 초기화</title>
<!-- 자바 스크립트에서는 생성자라고 하지 않고, 프로토타입이라고 한다.  -->
</head>
<body>

</body>
<script>
		/*  
			프로토 타입
			new 뒤에 나오는 생성자를 자바스크립트에서는 프로토타입이라고 부른다.
			프로토타입은 함수로 선언하여 사용한다.
		*/
		function Account(owner, code, balance) { // 프로토타입은 대문자로 시작해야 한다. 안그러면 혼란을 야기한다.
			this.owner = owner;
			this.code = code;
			this.balance = balance;
			
			this.deposit = function(money){this.balance += money;}  // 메소드 초기화
			this.show_balance = function(){return this.balance;}
		}	
		
		var account = new Account("홍길동", "1234", 100000);
		
		with(document){
			write("account : ");
			write(account.owner + ", ");
			write(account.code + ", ");
			write(account.balance + "원 <br>");
	}
	
	account.deposit(10000);
	document.write("10,000원 입금 후 잔액 : "+ account.show_balance() + "원 <br>");		
		</script>
```





---

## HTML DOM 객체

- HTML 태그들을 하나씩 객체화 한 것.
- HTML 페이지의 내용과 모양을 제어하기 위해 사용되는 객체들이다.
- HTML태그의 포함관계에 따라서 부모 자식 관계로 구성된다.

![img](https://blog.kakaocdn.net/dn/xylmU/btqWGL03V1h/yWUJmdaDTm6glPYShuOmEK/img.png)

![img](https://blog.kakaocdn.net/dn/b1c2wT/btqWQ90h8cx/mLcfNp1fKgIlVPfFL55r7K/img.png)

![img](https://blog.kakaocdn.net/dn/AHcRD/btqWKkosn07/fGjJGce5ZkE1F0t8sh5mYk/img.png)



dom1.html

```html
<body>
	<h2>CSS 스타일 동적 변경</h2>
	<hr>
	<p style="color: blue;">
			이것은 <span id="mySpan" style="color: red;">문장입니다.</span>
	</p>
	<input type="button" value="스타일 변경" onclick="change()">
</body>
<script>
	function change() {
		var span = document.getElementById("mySpan");
		
		with(span.style){
			color = "green";
			fontSize = "30px";
			display = "block";
			width = "6em";
			border = "3px dotted magenta;"
			margin = "20px";
		}
	}
</script>
```



dom2.html

```html
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>스타일 동적 변경</title>
</head>
<body>
			<h3>스타일 동적 변경</h3>
			<hr>
			<p id="firstP" style="color: blue;" onclick="change()">
					여기를<span style="color: red;">클릭하세요</span>
			</p>
</body>
<script>
		function change() {
			var p = document.getElementById("firstP");
			
			p.innerHTML = "<img src='https://ssl.pstatic.net/imgfinance/chart/item/area/day/009830.png?sidcode=1612923674813' width='400px' height='200px'> 이미지 등장";
			
		/*	var img = document.createElement("img");
			img.innerHTML = "이미지 등장";
			img.setAttribute("src", "icon1.png");
			img.setAttribute("width", "80px");
			img.setAttribute("height", "80px");
			p.appendChild(img); 
			*/
		}	
</script>
</html>
```





---



dom03.html

```html
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>DOM 이벤트</title>
</head>
<body>
   <button id="btn" onclick="change('red', '30px')">버튼</button>
</body>
<script>
   function change(color, size){
      var btn = document.getElementById("btn");
      
      btn.addEventListener("click", function(){
         this.style.color = color;
         this.style.fontSize = size;
      });
   }
</script>
</html>
```



dom04.html

```html
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>객체를 동적으로 생성, 삽입, 삭제</title>
</head>
<body id="parent">
		<h3>DIV 객체를 동적으로 생성, 삽입, 삭제하는 예제</h3>
		<hr>
		<p>
				DOM 트리에 동적으로 객체를 삽입할 수 있다.<br>
				createElement(), appendChild(), removeChild() 메소도를 이용해서<br>
				새로운 객체를 생성, 삽입, 삭제한다.
				<a href="javascript:createDIV()">DIV 생성</a>
		</p>
</body>
<script>
		function createDIV(){
			var body = document.getElementById("parent");
			var new_div = document.createElement("div");
			
			new_div.innerHTML = "새로운 생성된 div 입니다.";
			new_div.setAttribute("id","myDiv");
			new_div.style.backgroundColor = "yellow";			
			
			new_div.addEventListener("click", function(){
				var body = this.parentElement; // this = 지금 클릭된 객체 = 클릭된 div => parentElement = 부모
				body.removeChild(this); // 클릭하면 부모를 통해서 나 자신을 지우는 것이다. 
			});
			
			body.appendChild(new_div);
		}
		
</script>
</html>
```

---



## 실습



 input태그에 사용자가 작성한 값을 가져올 때에는

input객체.value로 가져올 수 있다. 

*힌트 : 배열 사용

![img](https://blog.kakaocdn.net/dn/bhWPKi/btqWLX0UGDP/KNipbHQmKlIDg5G8kNgG9k/img.png)

```html
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>요금표</title>
<link rel="preconnect" href="https://fonts.gstatic.com">
<link
	href="https://fonts.googleapis.com/css2?family=Nanum+Pen+Script&display=swap"
	rel="stylesheet">
<style>
table {
	margin: 0 auto;
	font-family: 'Nanum Pen Script', bold !important;
	text-align: center;
	width: 30%;
}

thead {
	background: #fff9c4;
}
</style>
</head>
<body>
	<table border="1">
		<caption>요금표</caption>
		<thead>
			<tr>
				<th>구분</th>
				<th>요금</th>
			</tr>
		</thead>
		<tr class="data">
			<td>아동</td>
			<td>무료</td>
		</tr>
		<tr class="data">
			<td>청소년</td>
			<td>2000</td>
		</tr>
		<tr class="data">
			<td>성인</td>
			<td>5000</td>
		</tr>
	</table>
	<fieldset style="text-align: center; width: 30%; margin: 0 auto;">
		<legend>구분</legend>
		<input type="text" id="choice">
		<button onclick="confirm()">확인</button>
	</fieldset>

</body>
<script>
	//input태그에 사용자가 작성한 값을 가져올 때에는
	//input객체.value 로 가져올 수 있다.
	//*힌트 : 배열
	var tempTd;
	var tempText;
	
	//아동, 청소년, 성인 각 tr태그에 동일한 class명을 준다.
	//테이블의 다른 곳에는 어떠한 구분자를 주지 않는다.
	function confirm() {
		var input = document.getElementById("choice").value;
		var datas = document.getElementsByClassName("data");
		var check = false;
		
		if(tempTd != undefined ) {
			tempTd.parentElement.style.background = "#fff";
			tempTd.innerHTML = tempText;
		}
		for(let i = 0; i < datas.length; i++ ){
			var td = datas[i].children[0]; 	
			if (input == td.innerHTML){
				tempTd = td;
				tempText = td.innerHTML;
				
				td.parentElement.style.background = "#ef5350";
				td.innerHTML = "★" + td.innerHTML;
				check = true;
				break;
			}
		}
		if(!check) {
			alert('다시 시도해주세요.');
			history.go(0);
		}
	}
	
	
</script>
</html>
```




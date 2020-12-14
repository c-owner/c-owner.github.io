[[Programmer/JAVA] - JAVA(자바) - 람다식이란?](https://github.com/eight-corner/blog/Posts/Lamda.md)

[[Programmer/JAVA] - JAVA(자바) - 타겟 함수와 함수 인터페이스](http://github.com/eight-corner/blog/Posts/Lamda3.md)

---

#JAVA (자바) - 람다식 기본 문법(2)

####✓함수적 스타일의 람다식을 작성하는 방법

```
(타입 매개변수, ...) -> { 실행문; ... }
```

```
(int a) -> { System.out.println(a); }   
```

#가장 기본적인 람다식

6가지의 약식 표현

익혀두면 좋다.

-	1. 매개 타입은 런타임시에 대입값에 따라 자동으로 인식하기 때문에 생략 가능하다.  

```
(a) -> { System.out.println(a); }	  
```

-	2. 매개변수가 없다면 괄호 ()를 생략할 수 없다.  

```
( ) - > { 실행문; ... }   
```

-	3. 하나의 매개변수만 있을 경우에는 괄호() 생략 가능

```
a -> { System.out.println(a); }	   
```

-	4. 리턴값이 있는 경우, return 문을 사용  

```
(x, y) -> { return x+y; };   
```

-	5. 하나의 실행문만 있다면 중괄호 { } 생략 가능  

```
a -> System.out.println(a)    
```

-	6. 중괄호 { }에 return 문만 있을 경우, 중괄호를 생략 가능  

```
(x, y) -> x + y    
```

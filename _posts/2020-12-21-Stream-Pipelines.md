---
layout: post
title:  "📝 JAVA- Stream pipeline(스트림 파이프라인)"
date:   2020-12-21 10:38:00 +0900
categories: JAVA LECTURE


---

# 📝 JAVA- Stream pipeline(스트림 파이프라인)

### 중간 처리와 최종 처리

스트림은 데이터의 **필터링, 매핑, 정렬, 그룹핑** 등의 중간처리와 

**합계, 평균, 카운팅, 최대값, 최소값**  등의 최종 처리를 파이프라인(pipelines)으로 해결한다.

![img](https://blog.kakaocdn.net/dn/cVOACv/btqv96hDxeX/xenUqX0TkqnzyRWC4L2FZk/img.png)



스트림 = 중간처리 + 최종처리로 구성

파이프라인 => 여러 개의 스트림이 연결되어 있는 구조를 말한다.



중간 스트림이 생성될 때 바로 요소들이 중간 처리되는 것이 아니라, 최종 처리가 설정(시작)되기 전 까지 중간처리는 지연된다.

최종처리가 시작되면 비로소 컬렉션 요소가 하나씩 중간 스트림에서 처리되고 최종 처리까지 오게 된다.





---



#### **중간 처리 메소드와 최종 처리 메소드**

![img](https://blog.kakaocdn.net/dn/naspS/btqv8mZ2VCE/FDpdQRNn182KztcChguWVK/img.png)

**sorted**

![a1](https://cafeptthumb-phinf.pstatic.net/MjAyMDEyMTlfODMg/MDAxNjA4MzMwMDk2MDI0.dfH6M2oN7DX7v7QrZ09cEl5NYW5nInSTt_042g1592Mg.qWjLs5qOcSWnvBakf8gLIqqgZmqs4X6BxyKTqyn6voEg.PNG/a1.png?type=w1600)



---



```java
package sortEx;

public class Student implements Comparable<Student>{
   private String name;
   private int score;
   public Student(String name, int score) {
      this.name = name;
      this.score = score;
   }
   @Override
   public int compareTo(Student o) {
      return Integer.compare(score, o.score);
   }
   public String getName() {
      return name;
   }
   public int getScore() {
      return score;
   }
}
```



```java
package sortEx;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.IntStream;

public class SortEx {
	public static void main(String[] args) {
		// 숫자일 경우 
		IntStream intStream = Arrays.stream(new int[] {5,3,2,1,4,1});
		intStream.distinct().sorted().forEach(a->System.out.println(a+" "));
		System.out.println("-----------");
		
		// 객체일 경우
		List<Student> list = Arrays.asList(new Student("홍길동", 10),
				new Student("리준리", 30),
				new Student("박명수", 25)
				);
		list.stream().sorted().forEach(a->System.out.println(a.getName()+" "+a.getScore()));
		
		System.out.println("----------");
		// 점수 내림차순 정렬하기
		list.stream().sorted(Comparator.reverseOrder()).forEach(a->System.out.println(a.getName()+" "+a.getScore()));
		
		
	}
}

```

---

```java
package 루핑;

import java.util.Arrays;
import java.util.stream.IntStream;

public class LoopingEx {
// peek() : 요소 전체를 반복하는 것, forEach()
	public static void main(String[] args) {
//		int[] intArr = {1,2,3,4,5};
		
		/*
//		Arrays.stream(intArr).filter(a-> a%2 == 0) // # 주석처리
//		.peek(n -> System.out.println(n)); // # 주석처리, 중간 처리 스트림
		Arrays.stream(intArr).filter(a-> a%2 == 0) // # 주석처리
//		.peek(n -> System.out.println(n)); // # 중간처리 스트림
		.forEach(a->System.out.println(a));
		
		
		// 최종 스트림에 붙지 않았기 때문에 동작하지 않는다.
		// peek의 return 타입은 IntStream 타입이 리턴타입
		// peek는 지연되어 있어 결과가 출력되지 않는다.
		
		IntStream ins = Arrays.stream(intArr).filter(a-> a%2 == 0)
		.peek(n -> System.out.println(n)); // 중간처리 스트림
		
//		int s = ins.sum();
//		System.out.println("총합 : "+s);
		
		// 이미 스트림을 사용중인데 또 사용하면 런타임 에러가 발생한다. 사용중이던 스트림을 주석처리 한다.
		
		double avg = ins.average().getAsDouble();
		System.out.println("평균 : "+avg);
		
		*/
		int[] intArr = {2,4,6}; // 모두가 2의 배수이면 true 그게 아니면 false
		boolean result = Arrays.stream(intArr).allMatch(a->a%2==0);
		System.out.println(result);
		
		boolean result2 = Arrays.stream(intArr).noneMatch(a->a%2==0);
		System.out.println(result2); // 하나도 2의 배수가 아니여야한다.
		
		
		
	}
}
```

---

#### **기본 집계**

**집계는 최종 처리 기능으로 요소들을 처리해서 카운팅, 합계, 평균값, 최대값, 최소값 등과 같이 하나의 값으로 산출한다.**



**집계 메소드**

![a2](https://cafeptthumb-phinf.pstatic.net/MjAyMDEyMTlfNjMg/MDAxNjA4MzMxMDQwNDgy.X9lqSou5gjrunUK1_06Hj9yZVzAeTYeCST0u3xy9RUog.D4xVOojMKYWPjSdd2glBVvWyrwrtDZY2I7zdxIb_yeIg.PNG/a1.png?type=w1600)



**3) 사용 예**

```java
package 집계메소드;

import java.util.Arrays;

public class AggregateEx {
	public static void main(String[] args) {
		long count = Arrays.stream(new int[] {1,2,3,4,5})
				.filter(n -> n%2==0).count();
		System.out.println("2의 배수 개수 "+count);
		
		long sum = Arrays.stream(new int[] {1,2,3,4,5})
				.filter(n -> n%2==0).count();
		System.out.println("2의 배수 총 합 : "+sum);
		
		double avg= Arrays.stream(new int[] {1,2,3,4,5})
				.filter(n -> n%2==0).average().getAsDouble();
		System.out.println("2의 배수 평균 "+avg);
		
		int max = Arrays.stream(new int[] {1,2,3,4,5})
				.filter(n -> n%2==0).max().getAsInt();
		System.out.println("2의 배수 개수 "+max);
		
		int first = Arrays.stream(new int[] {1,2,3,4,5})
				.filter(n->n%3==0)
				.findFirst().getAsInt();
		System.out.println("첫 번째 3의 배수 : "+first);
		
	}
}
```



---



### **Optional 클래스**

단순히 집계 값만 저장하는 것이 아니라 집계 값이 존재하지 않을 경우 디폴트 값을 설정할 수도 있다.

집계값을 처리하는 Consumer도 등록할 수 있다.



**메소드들**

![Optional](https://cafeptthumb-phinf.pstatic.net/MjAyMDEyMTlfOTUg/MDAxNjA4MzMxMTkwODE3.PYC2GWGwIS6G8m9ro7kYtXf-FZ_ROLgBlf2tnAf8lAwg.F5sD69M1aRf7QFvnWDOtvhyA1Nvc4NDR7qKN0PSLxlMg.PNG/a1.png?type=w1600)



#### **문제점**

컬렉션 요소는 동적으로 추가되는 경우가 많다.

만약 컬렉션의 요소가 추가되지 않아 저장된 요소가 없을 경우 다음 코드는 에러가 발생한다.

```java
		List<Integer> list = new ArrayList();
		double avg = list.stream().mapToInt(Integer::intValue).average().getAsDouble();
		// NoSuchElementException 컴파일 에러 발생
	
```



**처리방법1**

```java
// 처리 방법 1 ) Optional 객체를 얻어 isPresent() 메소드로 평균값 여부를 확인하여
//		true가 리턴할 때 getAsDouble()메소드로 평균값을 얻기
		OptionalDouble optional = list.stream()
				.mapToInt(Integer::intValue)
				.average();
		if(optional.isPresent()) {
			System.out.println("방법1_ 평균 : "+optional.getAsDouble());
		} else System.out.println("방법1_ 평균: 0.0");
```



**처리방법2**

```java
/* 처리방법 2) orElse() 메소드로 디폴트 값을 정해 놓고 평균값을 구할 수 없는 경우는 orElse()의 매개값이 디폴트값이 된다. */
		double avg = list.stream().mapToInt(Integer::intValue).average().orElse(0.0);
		System.out.println("방법2_ 평균 : "+avg);
```



**처리방법3**

```java
	/* 처리방법 3) ifPresent()메소드로 평균값이 있을 경우에만 값을 이용하는 람다식을 실행한다. */
		list.stream().mapToInt(Integer::intValue)
			.average()
			.ifPresent(a->System.out.println("방법3_평균 : "+a));
```



---



### 커스텀 집계( reduce() )

스트림은 기본 집계 메소드인 sum(), average(), count(), max(), min()를 제공하지만, 프로그램화해서 다양한 집계 결과물을 만들 수도 있도록 reduce() 메소드도 제공하고 있다.



![reduce](https://cafeptthumb-phinf.pstatic.net/MjAyMDEyMjBfMzEg/MDAxNjA4NDMyODk2NDA0.FEXzQpizDSUwcm5N1WZ6tDkbC8OgSsH0R7PmnEdncT8g.84kT-ADFaB9awZW9Z3AwyHN5MCRMOKdpZthJ_VdNZ1gg.PNG/a1.png?type=w1600)



**사용 예제**



```java
package 커스텀집계Reduce;

import java.util.Arrays;
import java.util.List;

import sortEx.Student;

public class ReductionEx {
	public static void main(String[] args) {
		List<Student> studentList = Arrays.asList(
				new Student("홍길동", 92),
				new Student("신용권", 95),
				new Student("김자바", 88)
				);
				
		int sum1 = studentList.stream()
				.mapToInt(Student::getScore).sum();
		System.out.println(sum1º);
		
		int sum2 = studentList.stream()
				.map(Student::getScore)
				.reduce((a,b)->a+b)
			// apply라는 추상메소드를 람다식으로 재정의 하고 있다. 
				// t가 a고 u가 b이다. a와 b를 더해서 리턴해야한다.
				// 결국 = 덧셈
				
//		BinaryOperator 추상메소드 R apply(T t, U u)
				
				.get();
			// Optional --> Int로 바꾸는 메소드
		System.out.println(sum2);
		
		int sum3 = studentList.stream()
				.map(Student::getScore)
				.reduce(0, (a,b) -> a+b);
//				identity -> 0 , 
		System.out.println(sum3);
		
		
	}
}
```

---



### 수집( collect() )

최종 처리 메소드인 <span style="color:green">collect()</span>를 제공하고 있다.
필요한 요소만 컬렉션으로 담을 수 있고 요소들을 그룹핑한 후 집계할 수 있다.



- 필터링한 요소 수집

Stream의 collect(Collector<T,A,R> collector) 메소드는 필터링 또는 매핑된 요소들을 새로운 컬렉션에 수집하고 이 컬렉션을 리턴한다.

![collect1](https://cafeptthumb-phinf.pstatic.net/MjAyMDEyMjBfMjM0/MDAxNjA4NDMzMjUwMDQw.eK2zSQ0plan4TAaBo14wHpGadhthIbejb7rmeVG-RQ8g.jjQf4uMNTNTEZ8fLPVQHxgwgBe04j86w5XvU7A7Mpi4g.PNG/a1.png?type=w1600)



매개값인  Collector는 어떤 요소를 어떤 컬렉션에 수집할 것인지 결정한다.

 **T=요소,  A=누적기, R=요소가 저장될 컬렉션**

T요소를 A누적기가  R에 저장한다는 뜻 



**Collector 클래스의 다양한 정적 메소드**

![collector](https://cafeptthumb-phinf.pstatic.net/MjAyMDEyMjBfMTU3/MDAxNjA4NDMzNDA3MjIx.3bKeSEabMU87NBNOlp_hZ-vNIUfpzPdbZohZ9YnXN4Ig.oy4eSDU0rXX7gNRLqsh4Lmo-H1MqXMwSYYi69kUTYB8g.PNG/a1.png?type=w1600)



**사용 예 **

- main class

```java
package 수집collect;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class ToListEx {
	public static void main(String[] args) {
		List<Student> totalList = Arrays.asList(
				new Student("홍길동", 10, Student.Sex.MALE),
				new Student("김수애", 6, Student.Sex.FEMALE),
				new Student("신용권", 10, Student.Sex.MALE),
				new Student("박수미", 6, Student.Sex.FEMALE)
				);
		
		// 남학생들만 묶어 List 생성
		List<Student> maleList = totalList.stream()
				.filter(s->s.getSex()==Student.Sex.MALE)
				.collect(Collectors.toList());
		maleList.stream().forEach(s->System.out.println(s.getName()));
		
		System.out.println("--------");
		// 여학생들만 묶어 HashSet 생성
		Set<Student> femaleSet = totalList.stream()
				.filter(s -> s.getSex() == Student.Sex.FEMALE)
//				.collect(Collectors.toCollection(HashSet::new)); // 방법1
				
//				.collect(Collectors.toCollection( () -> {
//					return new HashSet<Student>();
//				})); // 방법2
				
				.collect(Collectors.toCollection(() -> new HashSet<Student>())); 
		// 방법3
		
		femaleSet.stream().forEach(s -> System.out.println(s.getName()));

    //------------------------------------- ex
    	List<Student> totalList = Arrays.asList(
				new Student("홍길동", 10, Student.Sex.MALE, Student.City.Seoul),
				new Student("김수애", 6, Student.Sex.FEMALE,Student.City.Seoul),
				new Student("신용권", 10, Student.Sex.MALE, Student.City.Busan),
				new Student("박수미", 6, Student.Sex.FEMALE,Student.City.Busan)
				);
		System.out.println("----서울----");
		// 서울에 사는 사람만 List 생성 
		List<Student> seoulList = totalList.stream()
				.filter(s->s.getCity()==Student.City.Seoul)
				.collect(Collectors.toList());
		seoulList.stream().forEach(s->System.out.println(s.getName()));
		
		System.out.println("-----------부산---------");
		// 부산에 사는 사람만 List 생성
		List<Student> busanList = totalList.stream()
				.filter(s->s.getCity()==Student.City.Busan)
				.collect(Collectors.toList());
		busanList.stream().forEach(s->System.out.println(s.getName()));
      
    	System.out.println("--------점수 8 이상");
		// 점수 8 이상만 List 생성
		List<Student> scoreList = totalList.stream()
				.filter(s->s.getScore() >= 8)
				.collect(Collectors.toList());
		scoreList.stream().forEach(s->System.out.println(s.getName()));
		
		
		
	}
}
```

- student 클래스

```java
package 수집collect;

public class Student {
	public enum Sex { MALE,FEMALE}
	public enum City { Seoul, Busan}
	
	private String name;
	private int score;
	private Sex sex;
	private City city;
	public Student(String name, int score, Sex sex) {
		this.name=name; this.score=score; this.sex=sex;
	}
	
	public Student(String name, int score, Sex sex, City city) {
		this.name=name; this.score=score; this.sex=sex; this.city=city;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public Sex getSex() {
		return sex;
	}

	public void setSex(Sex sex) {
		this.sex = sex;
	}

	public City getCity() {
		return city;
	}

	public void setCity(City city) {
		this.city = city;
	}	
}
```




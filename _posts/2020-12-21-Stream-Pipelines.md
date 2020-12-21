---
layout: post
title:  "📝 JAVA- Stream pipeline(스트림 파이프라인)"
date:   2020-12-21 12:38:00 +0900
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

![a1](https://github.com/Eight-Corner/eight-corner.github.io/blob/master/_posts/StreamImg/a1.png?raw=true)



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

![a2](https://github.com/Eight-Corner/eight-corner.github.io/blob/master/_posts/StreamImg/a2.png?raw=true)



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

![a3](https://github.com/Eight-Corner/eight-corner.github.io/blob/master/_posts/StreamImg/a3.png?raw=true)



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



![a3-2](https://github.com/Eight-Corner/eight-corner.github.io/blob/master/_posts/StreamImg/a3-2.png?raw=true)



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

![a4](https://github.com/Eight-Corner/eight-corner.github.io/blob/master/_posts/StreamImg/a4.png?raw=true)



매개값인  Collector는 어떤 요소를 어떤 컬렉션에 수집할 것인지 결정한다.

 **T=요소,  A=누적기, R=요소가 저장될 컬렉션**

T요소를 A누적기가  R에 저장한다는 뜻 



**Collector 클래스의 다양한 정적 메소드**

![a5](https://github.com/Eight-Corner/eight-corner.github.io/blob/master/_posts/StreamImg/a5.png?raw=true)



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





---

# 사용자 정의 컨테이너에 수집하기

- 사용자 정의 컨테이너 객체에 수집하는 방법

![a6](https://github.com/Eight-Corner/eight-corner.github.io/blob/master/_posts/StreamImg/a6.png?raw=true)

- 첫번째 Supplier는 요소들이 수집될 컨테이너 객체(R)를 생성하는 역할을 한다.
  순차처리(싱글 스레드) 스트림에서는 단 한 번 Supplier가 실행되고 하나의 컨테이너 객체를 생성한다.
  스트림에서는 여러번 Supplier가 실행되고 스레드별로 여러 개의 컨테이너 객체를 생성한다.
  하지만 최종적으로 하나의 컨테이너 객체로 결합된다.

- 두번재 XXXConsumer는 컨테이너 객체(R)에 요소(T)를 수집하는 역할을 한다. 스트림에서 요소를 컨테이너에 수집할 때마다 XXXConsumer가 실행된다.
- 세번째  BiConsumer는 컨테이너 객체(R)를 결합하는 역할을 한다. 순차처리 스트림에서는 호출되지 않고 병렬처리 스트림에서만 호출되어 스레드 별로 생성된 컨테이너 객체를 결합해서 최종 컨테이너 객체를 완성한다.

```java
//전체 학생 List에서 stream을 얻는다.
Stream<Student> totalStream = totalList.stream();

//남학생만 필터링해서 Stream을 얻는다.
 Stream<Student> maleStream = totalStream.filter(s->s.getSex() == Student.Sex.MALE);

//MaleStudent를 공급하는   Supplier를 얻는다. 
Supplier<MaleStudnet> supplier = ()->new MaleStudent();

///MaleStudent의 accumulate()메소드로 Student를 수집하는 BiConsumer를 얻는다.
BiConsumer<MaleStudent, Student> accumulator = (ms, s) -> ms.acculate(s);


// MaleStudent를 매개값으로 받아 combine()메소드로 결합하는 BiConsumer를 얻는다.
BiConsumer<MaleStudent, MaleStudent> combiner = (ms1, ms2) -> ms1.combine(ms2);

//supplier가 제공하는 MaleStudent에  accumulator가  Studnet를 수집해서 최종 처리된 MaleStudent를 얻는다.
MaleStudent maleStudent = maleStream.collect(supplier, accumulator, combiner);

==> 람다식 표현
MaleStudent maleStudent = totalList.stream()
      .filter(s->s.getSEx() == Student.Sex.MALE)
      .collect(
         ()->new MaleStudent(), 
         (r, t) -> r.accumulate(t),
         (r1, r2) -> r1.combine(r2) );

==> 메소드 참조로 표현
MaleStudent maleStudent = totalList.stream()
     .filter(s->s.getSEx() == Student.Sex.MALE)
    .collect(MaleStudent::new, MaleStudent::accumulate, MaleStudent::combine);
```



#### 사용 예

- MaleStudent Class (**이전의 수집(collect()) 부분에서 사용한  Student 클래스를 임포트하여 사용한다.**)

```java
package 컨테이너수집;

import java.util.ArrayList;
import java.util.List;

import 수집collect.Student;
// (이전의 수집(collect()) 부분에서 사용한  Student 클래스를 임포트하여 사용한다.)

public class MaleStudent {
	private List<Student> list;
	public MaleStudent() {
		list = new ArrayList<Student>();
		System.out.println("[ "+Thread.currentThread().getName()+" ] MaleStudent()");
		// 생성자를 호출하는 스레드 이름
	}
	
	// 요소를 수집하는 메소드
	public void accumulate(Student student) { 
		list.add(student);
		System.out.println("["+Thread.currentThread().getName()+"] accumulate()");
		// accumulate() 메소드를 호출할 스레드 이름
	}
	
	// 두 MaleStudent를 결합하는 메소드(병렬처리 시에만 호출)
	public void combine(MaleStudent other) { 
		list.addAll(other.getList());
		System.out.println("["+Thread.currentThread().getName()+"] combine()");
		// combine()을 호출한 스레드 이름
	}
	
	//요소가 저장된 컬렉션을 리턴
	public List<Student> getList(){ 
		return list;
	}
	
}

```

- **MaleStudentEx 메인 클래스**

```java
package 컨테이너수집;

import java.util.Arrays;
import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.Supplier;
import java.util.stream.Stream;

import 수집collect.Student;

public class MaleStudentEx {
	public static void main(String[] args) {
		List<Student> totalList = Arrays.asList(
				new Student("홍길동", 10, Student.Sex.MALE, Student.City.Busan),
				new Student("김수애", 6, Student.Sex.FEMALE, Student.City.Seoul),
				new Student("신용권", 10, Student.Sex.MALE, Student.City.Busan),
				new Student("박수미", 6, Student.Sex.FEMALE, Student.City.Seoul)
				);
		/* 1.  
		Stream<Student> totalStream = totalList.stream();// 오리지널 스트림 
		Stream<Student> maleStream = totalStream.filter(s->s.getSex()==Student.Sex.MALE);//남자만
		Supplier<MaleStudent> supplier = () -> new MaleStudent(); // 매개변수 정의 후 객체 생성
		// Supplier<T> t.get()이라는 메소드 오버라이드 한거임. 
		// #공급
		
		BiConsumer<MaleStudent, Student> accmulator = (m,s) -> m.accumulate(s);
		// 전체 학생에서 남자만 뽑아서 리스트에 넣어주는 거임!
		// #수집
		
		BiConsumer<MaleStudent, MaleStudent> combiner = (ms1,ms2)-> ms1.combine(ms2);
		// MaleStudent를 매개값으로 받아 combine() 메소드로 결합하는 BiConsumer를 얻는다.
		// #결합
		
//		MaleStudent maleStudent = maleStream.collect(supplier, accmulator, combiner);
		// #최종수집
System.out.println("--------------");
System.out.println(maleStream);		
System.out.println("---------------");
//System.out.println(maleStudent.getList());
		*/ // 1. 주석

		/*
		// ==> 2. 람다식으로 표현
		MaleStudent maleStudent = totalList.stream()
				.filter(s-> s.getSex()==Student.Sex.MALE)
				.collect( () -> new MaleStudent(), (m,s)->m.accumulate(s), (ms1, ms2) -> ms1.combine(ms2) );
		System.out.println(maleStudent.getList()+"~~~");
*/		
		
		// ==> 3. 메소드 참조로 표현
		// ------ 남학생을 MaleStudent에 누적 
		MaleStudent maleStudent = totalList.stream() 
				.filter(s -> s.getSex() == Student.Sex.MALE) // 남자만 필터링
		// 방법1
//				.collect(MaleStudent::new, MaleStudent::accumulate, MaleStudent:: combine);
		// 방법2
				.collect( () -> new MaleStudent(), (r,t) -> r.accumulate(t), (r1, r2) -> r1.combine(r2));
		maleStudent.getList().stream()
		.forEach(s -> System.out.println(s.getName()));
		
	
				
	}
}

```



---

### 

### 요소를 그룹핑(groupingBy)해서 수집하기

#1

- <span style="color:green">collect()</span>메소드는 컬렉션의 요소들을 그룹핑해서  Map객체를 생성하는 기능을 제공한다.
- <span style="color:green">collect()</span>를 호출할 때 <span style="color:purple">Collectors</span>의 <span style="color:green">groupingBy()</span>또는 <span style="color:green">groupingByConcurrent()</span>가 리턴하는 <span style="color:green">Collector()</span>를 매개값으로 대입하면 된다.
- <span style="color:green">groupingBy</span>는 스레드에 안전하지 않은 Map을 생성하지만 ,<span style="color:green">groupingByConcurrent()</span>는 스레드에 안전한 ConcurrentMap에 생성한다.





![a7](https://github.com/Eight-Corner/eight-corner.github.io/blob/master/_posts/StreamImg/a7.png?raw=true)



### 사용 예  

```java
package 그룹핑;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import 수집collect.Student; // 이전에 사용한 Student를 사용한다.
public class GroupingByEx {
	public static void main(String[] args) {
		List<Student> totalList = Arrays.asList(
				new Student("홍길동", 10, Student.Sex.MALE, Student.City.Busan),
				new Student("김수애", 6, Student.Sex.FEMALE, Student.City.Seoul),
				new Student("신용권", 10, Student.Sex.MALE, Student.City.Busan),
				new Student("박수미", 6, Student.Sex.FEMALE, Student.City.Seoul),
				new Student("박수호", 6, Student.Sex.MALE, Student.City.Seoul)
				);
		
		Stream<Student> totalStream = totalList.stream();
		//Student 객체가 입력되어서 Student.City가 리턴됨
		Function<Student, Student.City> classisfier = Student :: getCity;
		
		// Student 객체가 입력되어서 Student의 name이 리턴됨
		Function<Student, String> mapper = Student :: getName;
		
		
		// 이름을 List에 수집하는 Collector를 얻는다.
		Collector<String, ?, List<String>> collector1 = Collectors.toList();
		
		// Collectors의 mapping() 메소드로 Student 이름을 List에 수집하는 Colletor를 얻는다.
		Collector<Student, ?, List<String>> collector2 = Collectors.mapping(mapper, collector1);

		// Student.City가 키이고 그룹핑된 이름 List가 값이 Map을 생성하는 Collector를 얻는다.
		Collector<Student, ?, Map<Student.City, List<String>>> collector3 =
					Collectors.groupingBy(classisfier,collector2);
		
		// Stream의 collect()메소드로 Student를 Student.City별로 그룹핑해서 Map을 얻는다.
		Map<Student.City, List<String>> mapByCity = totalStream.collect(collector3);
		
		System.out.println("서울 사람들 : "+mapByCity.get(Student.City.Seoul));
		System.out.println("부산 싸나이 : "+mapByCity.get(Student.City.Busan));
		
	}
}

```





---

[@CHANGELOG](CHANGELOG.md)






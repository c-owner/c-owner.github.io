---
layout: post
title:  "스프링(Spring) -(2) 게시판 만들기 - Service , ServiceImple"
date: 2021-05-10 09:00:21 +0900
categories: JAVA Lecture

---



![img](https://blog.kakaocdn.net/dn/bbDSBe/btq4rcDGGNk/at6lewZG697AoOXoVLyKk0/img.png)



root-context.xml 에 매퍼 추가 

```xml
<context:component-scan base-package="com.koreait.service"/>
```



BoardService.java Interface를 만드는 이유는 재사용성, 결합성 느슨함을 하기 위해서이다.

```java
package com.koreait.service;

import java.util.List;

import com.koreait.domain.BoardVO;

public interface BoardService {
	
	// 서비스에서의 메소드 이름은 좀 더 서비스 이름다운 것으로 작성한다.
	
	// 게시물 등록
	public void register(BoardVO board);
	
	// 특정 게시물 가져오기
	public BoardVO get(Long bno);
	
	// 게시물 수정
	public boolean modify(BoardVO board);
	
	// 게시물 삭제
	public boolean remove(Long bno);
	
	// 전체 게시물 가져오기
	public List<BoardVO> getList();
}
```



BoardServiceImple.java 는 Service라는 인터페이스에 주입하기 위해서 사용된다.

```java
package com.koreait.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.koreait.domain.BoardVO;
import com.koreait.mapper.BoardMapper;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;

@Service
@Log4j
@AllArgsConstructor
public class BoardServiceImple implements BoardService {
	
	private BoardMapper mapper;
	

	@Override
	public void register(BoardVO board) {
		log.info("register........."+ board);
		mapper.insertSelectKey_bno(board);
	}

	@Override
	public BoardVO get(Long bno) {
		log.info("get........" + bno);
		return mapper.read(bno);
	}

	@Override
	public boolean modify(BoardVO board) {
		log.info("modify.........."+ board);
		return mapper.update(board) == 1;
	}

	@Override
	public boolean remove(Long bno) {
		log.info("remove..........." + bno);
		return mapper.delete(bno) == 1;
	}

	@Override
	public List<BoardVO> getList() {
		log.info("getList...............");
		return mapper.getList();
	}

}
```



BoardServiceTests.java 는 주입이 잘 되었는지 테스트하기 위함이다.

```java
package com.koreait.service;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import lombok.Setter;
import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class) //테스트 코드가 스프링을 실행
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")//지정된 클래스나 문자열을 이용해서 필요한 객체들을 스프링 내에 객체로 등록
@Log4j
public class BoardServiceTests {
	
	@Setter(onMethod_ = @Autowired)
	private BoardService service;
	
	@Test
	public void testExist() {
		assertNotNull(service);
		log.info(service);
	}
}
```

JUnit 으로 실행 후 확인



![img](https://blog.kakaocdn.net/dn/JpPBU/btq4z11S54y/4k14np5SApa4kLRAuSeQ4k/img.png)





------



## 테스트 결과 확인 



registerTest()

![img](https://blog.kakaocdn.net/dn/deIEdv/btq4CiWtpSt/6XPVu1KfRUOrEpPQtPubkk/img.png)

get()

![img](https://blog.kakaocdn.net/dn/rgAzE/btq4rHcqEpU/tAHWEZpqI0KR8QZVpomT4k/img.png)

modify()

![img](https://blog.kakaocdn.net/dn/bp2FEU/btq4rcDJmPT/dkuxSpwIxjpWOiMqpKaajK/img.png)



remove()

![img](https://blog.kakaocdn.net/dn/4jyxd/btq4uH4edNx/bGIQtCVKGowmIBK6cvTro1/img.png)

getList()

![img](https://blog.kakaocdn.net/dn/sDgUM/btq4z2s0vGC/037pdGKJgixDh2cghMZ1O1/img.png)



------

BoardServiceTests.java 전체 코드 

주석 하나씩 풀면서 테스트 해야한다.

BoardServiceImple.java가 BoardService.java 서비스 인터페이스로 DI(의존성 주입)가 잘되는 것을 확인 할 수 있다.

BoardService는 인터페이스이므로 주입을 할 수 있는객체가 아니므로 어노테이션이 필요로 하지 않다. 



```java
package com.koreait.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.koreait.domain.BoardVO;

import lombok.Setter;
import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class) //테스트 코드가 스프링을 실행
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")//지정된 클래스나 문자열을 이용해서 필요한 객체들을 스프링 내에 객체로 등록
@Log4j
public class BoardServiceTests {
	
	@Setter(onMethod_ = @Autowired)
	private BoardService service;
	
	
//	@Test
//	public void serviceGetList() {
//		service.getList();
//	} // 테스트 결과 이상없음
	
	
//	@Test
//	public void serviceRemove() {
//		service.remove(6l);
//	} // 테스트 결과 이상없음
	
//	@Test
//	public void serviceModify() {
//		BoardVO board = new BoardVO();
//		board.setTitle("서비스 수정 제목 .... ");
//		board.setContent("서비스 수정 내용 ....");
//		board.setWriter("newbie2");
//		board.setBno(6l);
//		if(service.modify(board)) {
//			log.info(service);
//		}
//	} // 테스트 결과 이상없음.
	
//	@Test
//	public void serviceGet() {
//		service.get(6l);
//	} // 테스트 결과 이상없음.
	
	
//	@Test
//	public void registerTest() {
//		BoardVO board = new BoardVO();
//		board.setTitle("서비스 제목 .... ");
//		board.setContent("서비스 내용 ....");
//		board.setWriter("newbie..");
//		service.register(board);
//	} // 테스트 완료
	
	
//	@Test
//	public void testExist() {
//		assertNotNull(service);
//		log.info(service);
//	}
}
```


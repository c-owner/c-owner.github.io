---
layout: post
title: "JAVA - 입출력 패키지 "
date: 2020-12-22 13:22:00 +0900 # +0900 세계협정시(UTC)기준 서울이라는 뜻
comments: true # 코멘트 허용
categories: JAVA Lecture # 카테고리

---

<!-- 본문 -->

# JAVA - 입출력 패키지



### -개요



- 프로그램에서 데이터를 외부에서 읽고 다시 외부로 출력하는 작업이 빈번히 일어난다. 데이터는 사용자로부터 키보드를 통해 입력될 수도 잇고, 파일 또는 네트워크로부터 입력 될 수도 있다.
- 모니터로 출력될 수도 있고, 파일로 출력되어 저장될 수도 있으며 네트워크로 출력되어 전송 될 수도 있다.
- 자바에서 데이터는 스트림으로 통해 입출력되므로 스트림의 특징은 단일 방향으로 연속적으로 흘러가는 것을 말한다.



### -입력 스트림과 출력 스트림

![1-1](/Users/corner/Sites/_posts/file-input-output/1-1.png)





- 입력 스트림 : 항상 프로그램을 기준으로 데이터가 들어오면 입력 스트림이다.
- 출력 스트림 : 데이터가 나가는 것
- 스트림의 특성이 단방향이므로 하나의 스트림으로 입력과 출력을 모두 할 수 없기 때문이다.



![1-2](/Users/corner/Sites/_posts/file-input-output/1-2.png)



#### java.io 패키지의 주요 클래스

![1-3](/Users/corner/Sites/_posts/file-input-output/1-3.png)



### 스트림 클래스 종류



1) 바이트 기반 스트림

-  그림, 멀티미디어, 문자 등 모든 종류의 데이터를 받고 보낼 수 있다.

2) 문자 기반 스트림

- 문자만 받고 보낼 수 있도록 특화되어 있다.

![1-4](/Users/corner/Sites/_posts/file-input-output/1-4.png)

![1-5](/Users/corner/Sites/_posts/file-input-output/1-5.png)





### InpustStream

- 개요  

  * 바이트 기반 입력 스트림의 최상위 클래스로 추상 클래스이다.
  * 모든 바이트 기반 입력 스트림은 이 클래스를 상속 받아서 만들어진다.

  ![1-6](/Users/corner/Sites/_posts/file-input-output/1-6.png)

  ![1-7](/Users/corner/Sites/_posts/file-input-output/1-7.png)



**(1) <span style="color:green">read()</span> 메소드**

- 입력 스트림으로부터 1byte를 읽고 4byte를 int타입으로 리턴한다. 따라서 리턴된 4byte 중 끝의 1byte만 데이터가 들어있다.
- 더 이상 입력 스트림으로부터 바이트를 읽을 수 없다면  <span style="color:green">read()</span>메소드는 -1을 리턴한다.



**(2)  <span style="color:green">read(byte[] b)</span> 메소드**

- 입력 스트림으로부터 매개값으로 주어진 바이트 배열의 길이만큼 바이트를 읽고 배열에 저장한다.
- 그리고 읽은 바이트 수를 리턴한다.



**(3)  <span style="color:green">read(byte[] b, int off, int len)</span> 메소드**

- 입력 스트림으로부터 length개의 바이트만큼 읽고, 매개값으로 주어진 바이트 배열b[off]부터 len개 까지 저장한다.
- 읽은 바이트 수인 len개를 리턴한다.



---



### 키보드로 입력된 데이터 처리하기

``` java
import java.io.FileInputStream;
import java.io.InputStream;

public class ReadExample1 {
	public static void main(String[] args) throws Exception {
			InputStream is = new FileInputStream("C:/Temp/test.txt");
				int readByte;
		while(true) {
          readByte = is.read();
          if(readByte == -1) break;
          System.out.println((char)readByte);
		}
		is.close();
	}
}
```

----

```java
import java.io.FileInputStream;
import java.io.InputStream;

public class ReadExample2 {

	public static void main(String[] args) throws Exception {
        InputStream is = new FileInputStream("C:/Temp/test.txt");
            int readByteNo;
            byte[] readBytes = new byte[3];
            String data = "";
        while( true ) {
              readByteNo = is.read(readBytes);
              if(readByteNo == -1) break;
              data += new String(readBytes, 0, readByteNo);
        }
        System.out.println(data);
        is.close();
	}
}
```



```java
import java.io.FileInputStream;
import java.io.InputStream;

public class ReadExample3 {

	public static void main(String[] args) throws Exception {
    InputStream is = new FileInputStream("C:/Temp/test.txt");
    int readByteNo;
    byte[] readBytes = new byte[8];
    readByteNo = is.read(readBytes, 2, 3);
   
    for(int i=0; i<readBytes.length; i++) {
    		System.out.println(readBytes[i]);
    }
    
    	is.close();
    }
}
```



-----

## OutputStream



#### **개요**



![2-1](/Users/corner/Sites/_posts/file-input-output/2-1.png)

![2-2](/Users/corner/Sites/_posts/file-input-output/2-2.png)



**2) 사용 예**

```java
import java.io.FileOutputStream;
import java.io.OutputStream;

public class WriteExample1 {

		public static void main(String[] args) throws Exception {
      OutputStream os = new FileOutputStream("C:/Temp/test.txt");
      byte[] data = "ABC".getBytes();
			for(int i=0; i<data.length; i++) {
					os.write(data[i]);
			}
		os.flush();
		os.close();

	}
}
```

```java
public class WriteExample2 {

		public static void main(String[] args) throws Exception {
			OutputStream os = new FileOutputStream("C:/Temp/test.txt");
			byte[] data = "ABC".getBytes();
        os.write(data);
        os.flush();
        os.close();
			}
}
```

```java
import java.io.FileOutputStream;
import java.io.OutputStream;
	
public class WriteExample3 {
		public static void main(String[] args) throws Exception {
      
      OutputStream os = new FileOutputStream("C:/Temp/test.txt");
      byte[] data = "ABC".getBytes();
      os.write(data, 1, 2);
      os.flush();
      os.close();
		}
}
```



----



### Reader 





![2-3](/Users/corner/Sites/_posts/file-input-output/2-3.png)

![2-4](/Users/corner/Sites/_posts/file-input-output/2-4.png)



**사용 예**

```java
import java.io.FileReader;
import java.io.Reader;

public class ReadExample1 {

	public static void main(String[] args) throws Exception {
      Reader reader = new FileReader("C:/Temp/test.txt");
      int readData;
    
    while( true ) {
      readData = reader.read();
      
      if(readData == -1) break;
      	System.out.print((char)readData);
      }
      reader.close();

		}
}
```



```java
package Reader;

import java.io.FileReader;
import java.io.Reader;

public class ReadExample2 {
	public static void main(String[] args) throws Exception {
		
		Reader reader = new FileReader("/Users/corner/trash/test.txt");
		
		int readCharNo;
		char[] cbuf = new char[2];
		
		String data = "";
		
		while(true) {
			readCharNo = reader.read(cbuf);
			if(readCharNo == -1 ) break;
			
			data += new String(cbuf, 0, readCharNo);
		}
		System.out.println(data);
		
		reader.close();

	}
}
```

```java
package Reader;

import java.io.FileReader;
import java.io.Reader;

public class ReadExample3 {
	public static void main(String[] args) throws Exception{
		Reader reader = new FileReader("/Users/corner/trash/test.txt");
		
		int readCharNo;
		char[] cbuf = new char[4];
		
		readCharNo = reader.read(cbuf, 1, 2);
		
		for(int i = 0; i <cbuf.length; i++ ) {
			System.out.println(cbuf[i]);
			
		}
		reader.close();
		
	}
}
```



---



### **Writer 클래스**

**개요**

![2-5](/Users/corner/Sites/_posts/file-input-output/2-5.png)





![2-6](/Users/corner/Sites/_posts/file-input-output/2-6.png)

#### 사용 예

```java
package Writer;

import java.io.FileWriter;
import java.io.Writer;

public class WriterExample1 {
	public static void main(String[] args) throws Exception{
		
		Writer writer = new FileWriter("/Users/corner/trash/test.txt");
		char[] data = "홍길동".toCharArray();
		
		for(int i=0; i<data.length; i++ ) {
			writer.write(data[i]);
		}
		
		writer.flush();
		writer.close();
		
	}
}
```



---

### 콘솔 입출력

콘솔로부터 데이터를 입력 받을 때 System.in

콘솔에 데이터를 출력할 때 System.out

에러를 출력할 때 System.err



![2-7](/Users/corner/Sites/_posts/file-input-output/2-7.png)





1) System.in 필드

- 프로그램이 콘솔로부터 데이터를 입력 받을 수 있도록 System클래스의 in 정적 필드를 제공한다.

(1) 1 바이트를 읽고 1 바이트 리턴 

```java
InputStream is = System.in; // 키보드 입력 스트림 얻기
char inputChar = (char) is.read(); // 아스키코드를 읽고 문자로 리턴
```



#### **사용 예**

```java
public static void main(String[] args) throws Exception {
		
		System.out.println("== 메뉴 ==");
		System.out.println("1. 예금 조회");
		System.out.println("2. 예금 출금");
		System.out.println("3. 예금 입금");
		System.out.println("4. 종료 하기");
		System.out.println("메뉴를 선택하세요.");
		
		InputStream is = System.in;
		char inputChar = (char) is.read();
		
		switch(inputChar) {
		case '1': System.out.println("예금 조회를 선택하셨습니다.");
		break;
		case '2': System.out.println("예금 출금을 선택하셨습니다.");
		break;
		case '3': System.out.println("예금 입금을 선택하셨습니다.");
		break;
		case '4': System.out.println("종료 하기를 선택하셨습니다.");
		break;
		
		}	
	}
```

**(2) 한글과 같이 2바이트를 필요로 하는 유니코드는 read(byte[] b) 메소드 사용함** 

byte[] byteData = new byte[15]; // 한글과 같이 2바이트를 필요로하는 유니코드는 read(byte[] b) 메소드 사용함

int readByteNo = System.inread(byteData);



이름과 하고 싶은말을 키보드로 입력받아 다시 출력하기

```java
package Console;

import java.io.InputStream;

public class SystemInExample2 {
	public static void main(String[] args) throws Exception{
	InputStream is = System.in;
	
	byte[] datas = new byte[100];
	
	System.out.print("이름 : ");
	int nameBytes = is.read(datas);
	String name = new String(datas, 0, nameBytes-2);
	
	System.out.print("하고 싶은 말: ");
	int commentBytes = is.read(datas);
	String comment = new String(datas, 0, commentBytes-2);
	
	System.out.println("입릭한 이름 : "+name);
	System.out.println("입력한 하고 싶은말 : "+comment);
	
	}
}
```



**(3) 바이트 배열에 저장된 아스키 코드를 사용하려면 문자열로 변환해야 한다.**

읽은 바이트수 -2 ==> -2를 하는 이유는 Enter키에 해당하는 마지막 두 바이트를 제외하기 위해서이다.



**String strDate = new String(byteData, 0, readByteNo-2);**

​												 **바이트 배열, 시작 인덱스, 읽은 바이트수-2**



---



#### **System.out 필드**



콘솔로 데이터를 출력하기

```java
package Console;

import java.io.OutputStream;

public class SystemOutExample {
	public static void main(String[] args) throws Exception {
		
		OutputStream os = System.out;
		
		for(byte b=48; b<58; b++) {
			os.write(b);
		}
		
		os.write(10);
		
		
		for(byte b = 97; b<123; b++ ) {
			os.write(b);
		}
		os.write(10);
		
		String hangul = "가나다라마바사아자차카타파하";
		byte[] hangulBytes = hangul.getBytes();
		os.write(hangulBytes);
		
		os.flush();
	}
}
```








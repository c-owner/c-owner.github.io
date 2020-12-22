---
layout: post
title: "JAVA - 파일 입출력 "
date: 2020-12-22 10:17:00 +0900 # +0900 세계협정시(UTC)기준 서울이라는 뜻
comments: true # 코멘트 허용
categories: JAVA Lecture # 카테고리

---

<!-- 본문 -->

# JAVA - 파일 입출력 - Mac 이클립스



## File 클래스



### 개요-

- IO패키지에서 제공하는 File클래스는 파일 크기, 파일 속성, 파일 이름 등의 정보를 얻어내는 기능과 파일 생성 및 삭제 기능을 제공한다.
- 디렉토리를 생성하고 디렉토리에 존재하는 파일 리스트를 얻어내는 기능
- 파일의 데이터를 읽고 쓰는 기능을 지원하지 않는다.



#### 메소드-

파일 및 디렉토리 생성 및 삭제 메소드

![a1](/Users/corner/Sites/_posts/file-input-output/a1.png)



파일 및 디렉토리의 정보를 리턴하는 메소드

![a2](/Users/corner/Sites/_posts/file-input-output/a2.png)



#### 사용 예

```java
package File;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class FileEx {
	public static void main(String[] args) {
		
//		File dir = new File("C:/Temp/Dir"); // 윈도우 방식
		File dir = new File("/Users/corner/trash/input.txt");
		File file1 = new File("/Users/corner/trash/file1.txt");
		File file2 = new File("/Users/corner/trash/file2.txt");
		try {
			File file3 = new File(new URI("file:///Users/corner/trash/file3.txt"));
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(dir.exists() == false) {
			dir.mkdirs(); // 폴더생성
		}
		if(file1.exists() == false ) {
			try {
				file1.createNewFile();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		if(file2.exists() == false ) {
			try {
				file2.createNewFile();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		File temp = new File("/Users/corner/trash");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd a HH:mm");
		File[] contents = temp.listFiles();
		System.out.println("날짜 시간 형태 크기 이름");
		System.out.println("-------------------------");
		
		for(File file : contents) {
			System.out.print(sdf.format(new Date(file.lastModified())));
			if(file.isDirectory()) {
				System.out.print("\t<DIR>\t\t\t"+file.getName());
			} else {
				System.out.println("\t\t\t"+file.length()+"\t"+file.getName());
			}
			System.out.println();
		}
		
		
	}
}

```



---

#### FileInputStream

- 파일로부터 바이트 단위로 읽어들일 때 사용하는 바이트 기반 입력 스트림
- 그림, 오디오, 비디오, 텍스트 파일 등 모든 종류의 파일을 읽어올 수 있다.

```java
	try {
			FileInputStream fis = new 
					FileInputStream
					("/Users/corner/Desktop/Github/koreaIT/파일 입출력/src/File/FileInputStreamEx.java");
    // 파일을 읽고 싶은 경로와 파일명을 입력한다.
			int data;
			while ( (data = fis.read() ) != -1 ) {
				System.out.write(data);
			}
			fis.close();
		}catch (Exception e) {
			// TODO: handle exception
		}
```



FileOutputStream

```java
		
		String originalFileName = "/Users/corner/trash/logo.png";
		String targetFileName = "/Users/corner/trash/logo2.png";
		
		FileInputStream fis = new FileInputStream(originalFileName);
		FileOutputStream fos = new FileOutputStream(targetFileName);
		
		int readByteNo;
		byte[] readBytes = new byte[100];
		while ((readByteNo = fis.read(readBytes)) != -1 ) {
			fos.write(readBytes, 0, readByteNo);
		}
		fos.flush();
		fos.close();
		fis.close();
		System.out.println("복사가 잘 되었습니다.");
		
```



4. FileReader

```java

```


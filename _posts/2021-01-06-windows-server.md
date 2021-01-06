---
layout: post
title:  "(Windows 10 os) tomcat server 오류 발생시"
date: 2021-01-06 10:21:00 +0900
comments: true # 코멘트 허용
categories: Server
---



# 이클립스 tomcat server 실행시 오류 발생



#### <span style="color: red">**오류 내용**</span>

```java
'Starting Tomcat v9.0 Server at localhost' has encountered a problem.
  
  Server ports (8005, 80) required by tomcat v9.0 Server at localhost are already in use....
  ~~~
  
```

위와 같은 내용으로 볼 때 이미 포트를 사용중이므로 겹쳐서 실행할 수 없다는 뜻의 내용으로 에러 로그를 출력해준다.



#### CMD를 관리자 권한으로 열기

```markdown
netstat -a -n -o -p tcp
명령어 실행

0.0.0.0:80 
```

TCP 0.0.0.0 중에서 오류에서 발생하는 8005, 80, 8009, 8080 의 포트를 찾아 프로세스를 종료해주어야 한다.

포트를 찾았다면 PID 의 번호를 입력하여 아래와 같이 명령어를 수행한다.



```tex
taskkill /f /pid 6340 (6340 부분이 PID 번호가 되겠다.)
```




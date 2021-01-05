---
layout: post
title:  "(mac os)Apache2와 Tomcat 연동하기"
date: 2021-01-05 09:11:00 +0900
comments: true # 코멘트 허용
categories: Server
---

# MacOS(맥) 아파치 톰캣 연동

**환경**

- OS : Mac OS BigSur

- 톰캣 : apache-tomcat-9.0.41

- 아파치 : 2.4.46

- mod_jk : tomcat-connectors-1.2.48



톰캣은 설치가 되어있다고 가정하겠습니다.

 

다음 명령어로 아파치를 설치합니다.

```
brew install httpd
```

---





아파치와 톰캣 연동을 위해 JK Connector를 이용하겠습니다.

 

### [JK Connector 설치](https://joolib.tistory.com/13#JK Connector 설치)

------

[tomcat.apache.org/download-connectors.cgi](https://tomcat.apache.org/download-connectors.cgi)

 

현재 최신 버전은 1.2.48입니다.

 

```
tar -xvf tomcat-connectors-1.2.48-src.tar
```

압축을 해제하고 native 폴더로 이동합니다.

 

설치하기 전에 apxs의 위치를 찾습니다.

```
find / -name apxs
```

 

저는 다음과 같은 경로가 나왔습니다.

 

/usr/local/bin/apxs

/usr/local/Cellar/httpd/2.4.46/bin/apxs

```
ll /usr/local/bin/apxs

lrwxr-xr-x  1 joo  admin    31B 10 21 10:39 /usr/local/bin/apxs -> ../Cellar/httpd/2.4.46/bin/apxs
```

같은 링크가 걸려 있습니다. 따라서 둘 중 아무거나 사용하셔도 됩니다.

 

다시 native 폴더로 이동하여 설치를 진행하겠습니다.

 

```
./configure --with-apxs=/usr/local/bin/apxs
make
make install
```

설치가 바로 되면 좋은데 다음과 같은 오류가 발생했습니다.

더보기

 

이를 해결하기 위해 native/configure 파일을 수정합니다.

다음과 같이 크기를 측정하는 곳에 #include<stdlib.h> 헤더를 추가합니다.

```
#include <stdio.h>
#include <sys/types.h>
#include <stdlib.h>		/* 추가한 부분 */
main()
{
   FILE *f=fopen("conftestval","w");
   if (!f) exit(1);
   fprintf(f, "%d\n", sizeof(pid_t));                                      
   exit(0);
}

...

#include <stdio.h>
#include <pthread.h>
#include <stdlib.h>		/* 추가한 부분 */
main()
{
  FILE *f=fopen("conftestval","w");
  if (!f) exit(1);
  fprintf(f, "%d\n", sizeof(pthread_t));  
  exit(0);
}
```

다시 컴파일을 진행하면 크기가 잘 인식되는 것을 확인할 수 있습니다.

```
checking size of char... 1
checking size of int... 4
checking size of long... 8
checking size of short... 2
checking size of long double... 16
checking size of long long... 8
checking size of longlong... 0
checking size of pid_t... 4
checking size of pthread_t... 8
```

그리고 make를 진행하면 다음 결과가 보입니다.

더보기

warning이므로 다음 make install을 진행합니다.

 

모두 진행하면 native/apache-2.0 폴더에 mod_jk.so 파일이 생성됩니다.

 

또한 /usr/local/Cellar/httpd/2.4.46/lib/httpd/modules에 자동으로 파일이 생성됩니다.

파일이 없다면 mod_jk.so을 /usr/local/Cellar/httpd/2.4.46/lib/httpd/modules에 복사합니다.

 

### [톰캣 설정](https://joolib.tistory.com/13#톰캣 설정)

------

 톰캣의 server.xml을 수정합니다.

```
/usr/local/apache-tomcat-9.0.37/conf/server.xml

#다음과 같이 주석을 제거합니다.

<!-- Define an AJP 1.3 Connector on port 8009 -->  
<Connector protocol="AJP/1.3"
           port="8009"
           redirectPort="8443" />
```

 

 

### [아파치 설정](https://joolib.tistory.com/13#아파치 설정)

------

아파치가 설치된 곳에 conf 폴더와 workers.properties 파일을 추가합니다.

저는 /etc/apache2 폴더에 추가했습니다. 로그 파일을 위해 logs 폴더도 생성합니다.

 

workers.properties를 다음과 같이 수정합니다.

```
worker.list=worker1          

worker.worker1.type=ajp13
worker.worker1.host=localhost
worker.worker1.port=8009
```

 

### [아파치 httpd.conf 파일 수정](https://joolib.tistory.com/13#아파치 httpd.conf 파일 수정)

------

/usr/local/etc/httpd/httpd.conf 파일을 수정합니다.

```
LoadModule jk_module lib/httpd/modules/mod_jk.so
JkWorkersFile /etc/apache2/conf/workers.properties                 
JkLogFile /etc/apache2/logs/mod_jk.log
JkShmFile /etc/apache2/logs/mod_jk.shm
JkMount /* worker1
```

입력 후 다음 명령어로 구문 오류가 없는지 확인합니다.

```
apachectl -t
```

 

이제 확인해보겠습니다.

 

```
#톰캣 실행 # 설치된 톰캣내 bin폴더에서 실행합니다. /usr/local/apache-tomcat-9.0.37/bin
./startup.sh

#아파치 실행
sudo apachectl start
```

 

주소창에 http://localhost를 입력하여 http://localhost:8080에서 나오던 톰캣 화면이 나오는지 확인합니다.

 

tomcat 8.5.51 버전 이상에서는 오류가 발생합니다.

 

오류 관련하여 다음 블로그를 참조하였습니다.

https://nirsa.tistory.com/131

 

이를 참고하여 다음과 같이 수정합니다.

 

 

**연결 오류 설정**

 

```
<Connector protocol="AJP/1.3"
           address="0.0.0.0"		/* 추가한 부분 */
           secretRequired="false"	/* 추가한 부분 */
           port="8009"
           redirectPort="8443" />
```

 

위와 같이 설정하여 오류를 해결하였습니다.

 

다시 주소창에 http://localhost를 입력하면 정상적으로 동작하는 모습을 확인할 수 있습니다.







---

### **아파치 톰캣 연동하는 과정중 에러 발생** 

톰캣 커넥터/native/ 에서

➜ native sudo ./configure --with-apxs=/usr/local/bin/apxs

명령어 수행 시 

configure: error: C compiler cannot create executables

에러 발생 



<span style="color:red">**\* 해결** </span>

xcode-select —install 

명령어 수행하여 설치 후 진행하면 됨 !



———————

새로운 에러를 직면하였다.



**./jk_types.h:56:2:** **error:** **Can not determine the proper size for pid_t**

\#error Can not determine the proper size for pid_t

 **^**

**./jk_types.h:62:2:** **error:** **Can not determine the proper size for pthread_t**

\#error Can not determine the proper size for pthread_t

 **^**

2 errors generated.

make[1]: *** [jk_ajp12_worker.lo] Error 1

make: *** [all-recursive] Error 1





—>

<span style="color:red">**\* 해결** </span>

먼저, find apr_lib.h를 찾는다. 

sudo find / -name “apr_lib.h”



apr 경로를 아래 명령어에 적용 시킨다.

**➜** **native** sudo ./configure CFLAGS='-arch x86_64' APXSLDFLAGS='-arch x86_64' --with-apxs=/usr/local/bin/apxs \LDFLAGS='-L/usr/local/Cellar/apr/1.7.0/libexec/include/apr-1'



sudo ./configure CFLAGS='-arch x86_64' APXSLDFLAGS='-arch x86_64' LDFLAGS='-L/usr/local/Cellar/apr/1.7.0/libexec/include/apr-1' CFLAGS='-I/usr/local/Cellar/apr/1.7.0/libexec/include/apr-1' --with-apxs=/usr/local/bin/apxs



configure 파일을 열어서 <stdlib.h> 헤더파일 추가해줘야함

```c
#include <stdio.h>
#include <sys/types.h>
#include <stdlib.h>		/* 추가한 부분 */
main()
{
  FILE *f=fopen("conftestval","w");
  if (!f) exit(1);
  fprintf(f, "%d\n", sizeof(pid_t));                    
  exit(0);
}

...

#include <stdio.h>
#include <pthread.h>
#include <stdlib.h>		/* 추가한 부분 */
main()
{
 FILE *f=fopen("conftestval","w");
 if (!f) exit(1);
 fprintf(f, "%d\n", sizeof(pthread_t));  
 exit(0);
}
```


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

**파일이 없다면 mod_jk.so을 /usr/local/Cellar/httpd/2.4.46/lib/httpd/modules에 복사합니다.**

**Mac에서 아파치 모듈 경로 /usr/libexec/apache2/**

 

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







----



## 다른 방식

# JK Connector 설치하기

아래의 톰캣 홈페이지에서 하거나 curl을 통해서 다운로드하셔도 됩니다.

- [링크: JK Connector 다운로드](http://tomcat.apache.org/download-connectors.cgi)

```bash
curl -O http://archive.apache.org/dist/tomcat/tomcat-connectors/jk/tomcat-connectors-1.2.41-src.tar.gz
```

Bash

tar 압축을 해제하고 `cd 압축해제위치/native` 명령어로 이동합니다. 그리고 다음과 같은 명령어를 실행합니다.

```bash
./configure --with-apxs=/usr/sbin/apxs 
# 또는 아래 명령어를 실행한다.
./configure CFLAGS='-arch x86_64' APXSLDFLAGS='-arch x86_64' --with-apxs=/usr/sbin/apxs
```

Bash

혹시나 위 과정에서 에러가 발생했다면, 대부분 컴파일러를 못 찾아서 발생하는 경우가 많습니다. 다음과 같은 명령어를 타이핑하여 소프트 링크를 만듭니다. (OSX El Capitan 기준)

```bash
cd /Applications/Xcode.app/Contents/Developer/Toolchains
sudo ln -s XcodeDefault.xctoolchain ./OSX10.11.xctoolchain

# 1과 2를 묶어서 타이핑하셔도 됩니다. 1번 과정의 주소를 2번 과정의 src와 dest 앞에 두면 됩니다.
# 그리고 다시 위의 ./configure ~ 과정을 진행합니다.
```

Bash

마지막으로 아래와 같이 `make` 명령어를 실행합니다.

```bash
# 먼저 타이핑하여 실행합니다.
make

# sudo 명령어는 설치되는 경로의 접근을 위함입니다. 
sudo make install
```

Bash

`make` 과정에서도 apr_lib.h 오류가 나는 경우가 있습니다. 아래 방법으로 해결합시다.

```bash
# apr_lib.h 경로를 확인합니다.
sudo find / -name "apr_lib.h"

# 다음 명령어를 실행합니다.
./configure CFLAGS='-arch x86_64' APXSLDFLAGS='-arch x86_64' LDFLAGS='-L/usr/include/apr-1' CFLAGS='-I/usr/include/apr-1' --with-apxs=/usr/sbin/apxs

# 이후 make와 sudo make install을 진행합니다.
```

Bash

정상적으로 진행되었다면 native 디렉터리 밑 apache-2.0 디렉터리 내에 mod_jk.so가 생성됩니다. `install: /usr/libexec/apache2/mod_jk.so: Operation not permitted` 에러가 발생해도 생성됩니다

**아파치가 설치된 경로의 modules 디렉터리 밑에 mod_jk를 넣도록 합니다.** modules가 없다면 생성하면 됩니다. 다른 이름으로 하는 경우 httpd.conf의 mod_jk.so 위치와 일치해야 합니다.



# 톰캣 설정하기

톰캣에서는 설정할 부분이 적습니다. 설치 후에 변경이 없었다면 이 과정은 진행하지 않아도 됩니다. 그래도 혹시 모르니 확인은 해봅시다. 다음 명령어를 통해 톰캣의 설정 부분으로 진입합니다.

```bash
# 설치한 톰캣 디렉터리가 Desktop에 존재한다고 가정
cd /Desktop/설치된 톰캣 디렉터리 이름/conf
```

Bash

이후 `server.xml`을 열어 아래 설정이 있는지 확인합니다.

```bash
vi server.xml

# 위 명령어를 통해 에디터를 열은 후 다음과 같은 설정 부분이 있는지 확인합니다.

<!-- Define an AJP 1.3 Connector on port 8009 -->
<Connector port="8009" protocol="AJP/1.3" redirectPort="8443" />

# 주석은 없어도 됩니다. 포트 번호가 8009인지 확인하면 됩니다.
# 아예 위 부분이 통째로 주석처리 되있다면 해제해주면 되겠지요.
```

Bash



# 아파치 설정하기

이제 아파치를 설정하면 됩니다. 연동할 톰캣의 리스트를 나타낼 `workers.properties` 파일을 하나 만들어줍니다. 위치는 아파치가 설치된 디렉터리 하위의 conf 디렉터리 밑으로 하지요.

```bash
cd /etc/apache2/conf
```

Bash

여기서 `vi workers.properties` 명령어를 통해 에디터를 열고 아래와 같이 입력합니다.

```bash
worker.list=worker1

worker.worker1.type=ajp13
worker.worker1.host=localhost
worker.worker1.port=8009

# 만일, 2개 이상의 톰캣을 사용하는 경우 다음과 같이 작성합니다.

worker.list=worker1,worker2

worker.worker1.type=ajp13
worker.worker1.host=localhost
worker.worker1.port=8009 # 포트번호
worker.worker1.lbfactor=2 # 서버 밸런스 비율

worker.worker2.type=ajp13
worker.worker2.host=localhost
worker.worker2.port=8010 # 포트번호
worker.worker2.lbfactor=1 # 서버 밸런스 비율
```

Bash

여기서 worker의 이름은 임의로 지정한 것입니다. 바꾸셔도 됩니다. 서버 밸런스 비율을 뜻하는 lbfactor의 경우도 마찬가지입니다. 또한 port 번호의 경우도 worker끼리 중첩되지 않으면 됩니다.



# 아파치 httpd.conf 수정

명령어 `cd /etc/apache2`를 통해 apache가 설치된 디렉터리로 이동합니다. 디렉터리 이동 후 `vi httpd.conf` 명령어를 통해서 httpd.conf에 mod_jk를 추가합니다.

```bash
# 위 명령어로 에디터가 열리면 다음 문장을 추가해줍니다.
# 아무것도 변경하지 않았을 때, 맥에 설치된 아파치의 기본 ServerRoot는 '/usr' 입니다.
# 기존 설정을 유지한채 앞에 /etc/apache2/를 붙이는 것으로 진행합니다.


LoadModule jk_module /etc/apache2/modules/mod_jk.so
# mod_jk.so의 위치입니다. modules는 위의 install 과정에서의 디렉터리 이름입니다.

JkWorkersFile /etc/apache2/conf/workers.properties
# workers 설정 파일 위치

JkLogFile /etc/apache2/logs/mod_jk.log
# 로그파일 위치

JkShmFile /etc/apache2/logs/mod_jk.shm
# Load balancing workers will not function properly 오류 대응, httpd의 권한

JkMount /* worker1
# URL에 따른 요청 처리 설정
```

Bash

**중요하게 살펴봐야 하는 부분은 JkMount 입니다.** 이 부분에서 어떤 URL로 오는 경우 어떤 worker(톰캣)가 처리할지 설정할 수 있습니다. `/*.jsp`도 설정할 수 있고 `/*.php`도 가능합니다. 톰캣을 여러 대 설정하였다면 workers.properties에서 설정한 worker.list의 각 worker 이름에 따라서 설정합니다.



# 확인해보기

설정은 모두 끝났고 이제 브라우저를 열어서 확인해보면 됩니다. Tomcat의 포트는 8080이고 Apache는 80포트입니다.

```bash
# 톰캣 실행 : 톰캣의 bin 디렉터리에서 아래처럼 스크립트를 실행하면 됩니다.
./startup.sh

# 아파치 실행 : 아래 명령어를 터미널에서 실행하면 됩니다.
sudo apachectl start
```

Bash

`http://localhost` 만을 입력했을 때, Tomcat의 Web Root의 index.jsp가 보여야 합니다. 연동 전에는 포트 번호를 붙여야 하지만, 이후에는 아래 비교된 이미지처럼 포트 번호 없이 접속이 가능해야 합니다.

![연동 전 후](https://madplay.github.io/img/post/2018-01-05-apache-tomcat-modjk-2.png)
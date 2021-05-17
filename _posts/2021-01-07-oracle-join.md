---
layout: post
title:  "오라클Oracle - SQL : JOIN"
date: 2021-01-07 11:30:00 +0900
comments: true # 코멘트 허용
categories: Database
---



# Oracle SQL : JOIN 

 

```html

oracle-lecture0107.sql

-- 사원번호, 사원이름, 부서이름
-- Equi join : 서로 다른 테이블에 자료를 외래키로 연결된 것으로 이용하여 검색할 때
select empno, ename, dname from emp, dept where emp.deptno = dept.deptno;
 
8     -- test data eng sql import , 2021-01-07
9     
10    
11    
12    select * FROM student;
13    SELECT
14        *
15    FROM professor;
16    
17    -- 학생이름과 담당 지도교수 명 출력
18    select s.name 학생이름, p.name 지도교수이름
19    from student s, department d, professor p
20    where s.deptno1 = d.deptno
21    and s.profno = p.profno;
22    
23    -- ANSI Join 
24    select s.name as "학생이름", p.name as "지도교수이름"
25    from student s join professor p on s.profno = p.profno;
26   
27   -- 학생이름과 학과 담당 지도교수 명 출력
28   -- 학생 테이블과 학과 테이블, 교수 테이블을 join 하여 학생명,학과명, 교수명 출력
29   --Oracle inner join
30   SELECT s.name, d.dname, p.name from student s, professor p, department d
31       where s.deptno1 = d.deptno and s.profno = p.profno;
32   -- Ansi Join
33    select s.name 학생이름, d.dname 학과, p.name 지도교수
34    from student s 
35    JOIN department d ON s.deptno1 = d.deptno
36     JOIN professor p ON s.profno = p.profno;
37    
38    -- student 전공이 101번인 학생들의 학생이름과 지도교수 이름을 출력
39   
40   -- oracle join 구문
41   select s.name 학생명, p.name 교수명
42   from student s, PROFESSOR p
43   where s.profno = p.PROFNO --조인조건
44   and s.deptno1 = 101; -- 검색조건
45   
46   -- Ansi Join 구문
47   select s.name 학생명, p.name 교수명
48   from student s JOIN PROFESSOR p
49   on ( s.profno = p.PROFNO )
50   and s.DEPTNO1 = 101;
51   
52   select * from student where deptno1=101; -- 101번이 몇명인지 확인해보기
53   
54   -- Non Equi Join
55   select * from CUSTOMER;
56   select * from gift;
57   
58   -- customer 테이블과 gift 테이블을 join 하여 고객의 마일리지 포인트별로 받을 수 있는 상품 조회
59   select c.gname 고객명, to_char(c.point, '999,999') 포인트, g.gname 상품명
60   from CUSTOMER c, gift g
61   where c.POINT between g.g_start and g.g_end;
62   
63   --Ansi 구문
64   select c.gname 고객명, to_char(c.point, '999,999') 포인트, g.gname 상품명
65   from CUSTOMER c join GIFT G on c.POINT between g.G_START and g.G_END;
66   
67   --student 테이블과 score 테이블, hakjum테이블 조회하여 학생들의 이름과 점수 , 학점 출력
68   select * from STUDENT;
69   select * from score;
70   select * from hakjum;
71   
72   -- inner join 중에서
73   -- Non Equi Join
74   select s.name 학생명, sc.total 점수, h.grade 학점
75   
76   from student s, score sc,HAKJUM h
77   where s.STUDNO = sc.studno
78   and sc.total between h.MIN_POINT and h.MAX_POINT;
79   
80   -- ANSI 구문
81   select s.name 학생명, sc.total 점수, h.grade 학점
82   from student s JOIN score sc on s.STUDNO = sc.STUDNO
83                   JOIN hakjum h on sc.total between h.min_point and h.max_point;
84   
85   
86   -- student 테이블과 Professor 테이블을 Join하여 학생이름과 지도교수 이름을 출력
87   -- Oracle join 구문
88   select s.name 학생이름, p.name 지도교수
89   from student s, professor p
90   where s.profno = p.profno(+); -- 지도교수가 없는쪽에 +표시
91   
92   -- ANSI join 구문
93   select s.name 학생이름, p.name 지도교수
94   from student s LEFT OUTER JOIN professor p
95   on s.profno = p.profno; -- =기준으로 데이터가 있는 쪽에 방향에 따라 LEFT OUTER JOIN / RIGHT OUTER JOIN이 됨
96   
97   select s.name 학생이름, p.name 지도교수
98   from student s, professor p
99   where s.profno(+) = p.profno;
100  -- 데이터가 없는 학생쪽에 +표시
101  
102  select s.name 학생이름, p.name 지도교수
103  from student s RIGHT OUTER JOIN professor p -- =을 기준으로 데이터가 있는 오른쪽 방향으로 아우터 조인
104  on s.profno = p.profno;-- (+) 방향의 반대 데이터
105  
106  
107  -- student 테이블과 professor 테이블을 join하여 학생이름과 지도교수 이름을 출력
108  -- 단, 지도학생이 결정 안된 명단과 지도교수가 결정되지 않은 학생 명단을 한꺼번에 출력하라.
109  -- ** Oracle Join 구문에서 where s.profno(+) = p.profno(+) 와 같이 사용할 수 없다. ERROR
110  -- Oracle Join
111  
112  select s.name 학생, p.name 교수
113  from student s, professor p
114  where s.PROFNO = p.PROFNO(+)
115  union
116  select s.name 학생, p.name 교수
117  from student s,PROFESSOR p
118  where s.profno(+) = p.PROFNO; -- 앞의 두 문제의 결과를 합쳐서 출력하는 구문
119  
120  -- ANSI Join 구문
121  select s.name 학생, p.name 교수
122  from student s FULL OUTER JOIN professor p
123  on s.profno = p.profno;
124  
125  
126  -- SELF JOIN
127  -- 원하는 데이터가 하나의 테이블에 다 들어있는 경우 사용하는 방법
128  -- 하나의 테이블을 메모리상에서 별명을 두 개로 사용하여 가상으로 2개의 테이블로 만든 후 Join 작업을 수행
129  -- 반드시 별명을 사용해야 함
130  
131  -- emp 테이블에서 사원명, 매니저명 출력
132  select e.ENAME, m.ename from emp e, emp m where e.mgr = m.EMPNO;
133  select e.ENAME, m.ename from emp e JOIN emp m on e.mgr = m.empno;
134  
```


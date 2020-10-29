import java.util.Scanner;

/* 메모장 
 * 
 *  
 *  */ 
public class Day2 {

public static void main(String[] args) {
Scanner sc = new Scanner(System.in);
/* 
// 비교 연산자 boolean
System.out.println("비교 연산자 boolean <> <= >= == !=");
int a = 10;
int b = 20;
System.out.println(a>b);
System.out.println(a<b); 
// true or false
System.out.println("6가지 < > <= >= == != ");
System.out.println(a==b);
System.out.println(a<=b);
System.out.println(a>=b);
System.out.println(a!=b); // 다름?

// 논리 연산자 -> 집합
// AND = && 교집합: 그리고,~이고
// OR = 합집합 . 또는 , ~이거나..
System.out.println("1<2 && 2<3");
System.out.println(1<2 && 2<3);
System.out.println("1<2 || 2>3");
System.out.println(1<2 || 2>3);

// 정수 3개 입력 , 종합 구하여 출력 , 종합에서 - 1 
// - 1  > 10 true , false
int x,y,z;
Scanner sc = new Scanner(System.in); 
x = sc.nextInt();
y = sc.nextInt();
z = sc.nextInt();
int sum = 0;
sum+=x;
sum+=y;
sum+=z;
System.out.println(sum);
sum--;
System.out.println(sum>10);

// int 3개
// 3 개 평균 -> 소수점 까지 
int a,b,c;
Scanner sc = new Scanner(System.in);
a = sc.nextInt();
b = sc.nextInt();
c = sc.nextInt();
double avg=(a+b+c)/3.0; 
System.out.println(avg);


// 아스키코드
char c='a'; // 97
c++;
System.out.println(c+"=98");

// 제어문 2교시
// 조건문 vs 반복문  
// 조건: if , switch 
int a;
a = sc.nextInt();
if(a%2==0){
System.out.println("짝수");
}
else {
System.out.println("홀수");
}

// 정수 1개 -> 양수 / 음수 
int a;
a = sc.nextInt();
if(a==0) {
System.out.println("0");
}
else if(a>=1) { 
System.out.println("양수");
}
else {
System.out.println("음수");
}

// 3개 정수 
int a,b,c;
a = sc.nextInt();
b = sc.nextInt();
c = sc.nextInt();
if (a > b && a > c) {
System.out.println(a);
} else if (b>c && b>a) { 
System.out.println(b);
} else {
System.out.println(c);
}
*/ /* 
// 정수 2개 입력/ 5 50 -> 1시간 20분 전 -> 4시 30분
// 2 10 -> 12 50 
int h,m;
h = sc.nextInt();
m = sc.nextInt();
h--;
m-=20;
if(m<0) {
m+=60;
h--; // 
} if(h<=0) {
h+=12;
}
System.out.print(h+":"+m);
*/ /* // 스위치(변수) 
int num = sc.nextInt();
switch(num) {
case 1:
System.out.println("일");
break;
case 2:
System.out.println("이");
break;
case 3:
System.out.println("삼");
break;
default:
System.out.println("해당없음");
break;
} 
int num=sc.nextInt();
num/=10;
switch(num) {
case 10:
case 9:
System.out.println("A");
break;
case 8:
System.out.println("B");
break;
case 7:
System.out.println("C");
break;
default:
System.out.println("F");
break;
}
*/ // 3교시
// System.out.println("1.코카콜라 2.토레토 3.TOP 4.우유");
// int b;
// b=sc.nextInt();
// // 1-> 1200원 
// // 2-> 1100원
// // 3-> 1000원
// // 4-> 600원
// 
// String m1="1.";
// String m2="2.";
// String m3="3.";
// String m4="4.";
// int p1=1200;
// int p2=1100;
// int p3=1000;
// int p4=600;
// 
// String menu=m1;
// int price=p1;
// switch(b) {
// case 2:
// menu=m2;
// price=p2;
// break;
// case 3:
// menu=m3;
// price=p3;
// break;
// case 4:
// menu=m4;
// price=p4;
// break;
// }
// System.out.println("돈을 넣어주세요! :");
// int money=sc.nextInt();
// 
// if(money>=price) {
// System.out.println(menu+"가 나옵니다.");
// money-=price;
// } else {
// System.out.println("돈이 부족함");
// }
// if(money>0) {
// System.out.println(money+"원 반환");
// }
// 


// 기능을 여러번 수행하고 싶을때 -> 반복문 
// while, for
int i = 0;
while(i<10) {
if(i%2==0) {
System.out.println(i); 
}
i++;
}
// 디버깅표 그림판으로 그리기

}

}
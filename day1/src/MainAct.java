import java.util.Scanner;
// ctrl+shift+O -> 자동임포트

public class MainAct {
	
	public static void main(String[] args) {
		/*
		System.out.print("저는 ㅁㅁㅁ입니다.");
		System.out.println("안녕하세요!");
		
		// 변수 -> 자료형
		int i=10; // 정수
		double d=3.14; // 실수
		char c='A'; // 문자
		String s="abc"; // 문자열
		Boolean b=true; // 진위형
		// 자료형 변수명=값;
		// 식별자와 키워드(예약어)
		
		int x=10;
		int y=20;
		int sum=x+y;
		System.out.println(sum);
		
		// Scanner 클래스에서 sc를 생성
		Scanner sc=new Scanner(System.in);
		int num1=sc.nextInt();
		double num2=sc.nextDouble();
		System.out.println(num1);
		System.out.println(num2);
		
		// 상수 -> 변하지않는수
		// 함수(),변수
		final double rate=0.3;
		final double PI=3.141592;
		
		int a=1;
		System.out.println(a);
		a=11;
		// 대입연산자: =오른쪽의 값을 =왼쪽의 변수에 대입
		System.out.println(a);
		
		// 형변환
		int number=(int)10.123;
		// 명시적형변환
		System.out.println(number);
		double number2=3;
		// 자동형변환
		System.out.println(number2);
		// 데이터를 다룰때에는 반드시 자료형을 맞춰서 작성!!!
		*/
		// 실수 2개 입력
		// 합 출력하기!~~
		Scanner sc=new Scanner(System.in);
		double d1=sc.nextDouble();
		double d2=sc.nextDouble();
		double sum=d1+d2;
		/*
		System.out.println(sum);
		float f1=sc.nextFloat();
		float f2=sc.nextFloat();
		float sum2=f1+f2;
		System.out.println(sum2);
		*/
		
		int a=10;
		int b=2;
		int c=3;
		System.out.println(a/b);
		System.out.println(a*1.0/c); // 몫
		System.out.println(a%c); // 나머지->MOD
		// 같은 자료형끼리 연산시,
		// 답도 같은 자료형으로 리턴됨
		// 답을 다른 자료형으로 보고싶으면,
		// 해당 자료형을 연산중에 추가해야한다!
		
		b+=c; // 복합대입연산자
		// b=b+c;
		System.out.println(b);
		System.out.println(c);
		
		b++; // 증감연산자
		++b;
		b--;
		--b;
		// b++ = b값을 먼저 사용한 후, 1을 증가
		// ++b = b 값을 먼저 1을 증가 시킨 후 사용
		int num1=10;
		int num2=num1++;
		// 후위증감연산자는 우선순위 매우 낮다!
		System.out.println(num1+" / "+num2);
		num2=++num1;
		// 전위증감연산자는 우선순위 매우 높다!
		System.out.println(num1+" / "+num2);

	}

}

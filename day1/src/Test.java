import java.util.Scanner; // command+shift+o 

public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
/*
		System.out.println("hello.");
	// 변수 -> 자료형
		int i=10; // 정수
		double d=3.14; // 실수
		char c='A'; //문자
		String s="abc"; // 문자열 
		Boolean b=true; // 불리언(진위형)
		// 자형 변수명=값;
		// 식별자와 키워드(예약어)
		
		int x=10;
		int y=20;
		int sum=x+y;
		System.out.println(sum);
//		1. 합계(SUM)
//		2. 카운트(COUNT)
//		3. 평균(AVG)
//		4. 최댓값(MAX)
//		5. 최솟값(MIN)
//		6. 가까운값(NEAR)
//		7. 최빈값(MODE)
//		8. 순위(RANK)
//		9. 정렬(SORT)
//		10. 병합(MERGE)
//		11. 검색(SEARCH)
//		12. 그룹(GROUP)
		// Scanner 클래스에서 sc를 생성
//		Scanner sc=new Scanner(System.in);
//		int num1=sc.nextInt();
//		double num2=sc.nextDouble();
//		System.out.println(num1);
//		System.out.println(num2); // 정수도 되지만 소수점이 붙음! 이것이 자동 형변환
//		
		// 상수 -> 변하지 않는 수 
		// 함수(), 변수 
//		final double rate=0.3;
//		final double PI=3.141592;
//		System.out.println(rate+PI);
		
		/*		
		int a = 1;
		System.out.println(a);
		a = 11;
		//대입연산자는 ' = '오른쪽 값을 ' = '왼쪽 변수에 대입
		System.out.println(a);
		
		// 형변환
		int number = (int)10.123;
		System.out.println(number);
		//명시적 형변 int라는 정수를 명시 해놨기 때문에 실수는 나오지 않는다.
		double number2 = 3;
		System.out.println(number2);
		// 자동 형변환! 결과 값이 3.0
//		데이터를 다룰때는반드시 자료형을 맞춰서 작성!!
//	실수 2개 입력하여 합을 출력해보기 
		Scanner sc2=new Scanner(System.in);
		double num1 = sc2.nextDouble();
		double num2 = sc2.nextDouble();
		System.out.println(num1+num2); 
		
//		double j=1.2;
//		double k=2.3;
//		double sum2=j+k;
//		System.out.println(sum2);
		*/ 
		
		int a=10;
		int b=2;
		int c=3;
		System.out.println(a/b); // 5
		System.out.println(a*1.0/c); // 정확한 몫 (실수형까지)
		System.out.println(a%c); // 나머지 값.  MOD

		b+=c; // 복합대입연산자
		// b = b+c;
		System.out.println(b); 
		System.out.println(c);
		
		///////////// 증감연산자 ////////////
		b++;
		++b;
		b--;
		--b;
		int num1 = 10;
		int num2 = num1++;
		// 후위증감연산자는 우선순위 매우 낮다!
		System.out.println(num1+" / "+num2);
		num2=++num1;
		// 전위증감연산자는 우선순위 매우 높다!
		System.out.println(num1+" / "+num2);
	}

}

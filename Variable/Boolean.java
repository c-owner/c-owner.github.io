package day03;

import java.util.Scanner;
 
public class Boolean {
// Boolean 참 과 거짓을 의미함. 진위형
	// 데이터 타입 bool  
	// 정수 문자같은 데이터 타입 
	// true 참 vs false 거짓 = 두가지 값을 가지고 있어
	// 
	
	public static void main(String[] args) {

		// == 같냐 
		// != 부정을 의미함 ,그렇지 않다.
		// 
		 System.out.println(1!= 2); // false
		 System.out.println(1!=1); // true
		 System.out.println("one"!="tow"); // false
		 System.out.println("one"!="one"); // true
		
		 // > 부등호  1 > 3 false
		 
		 System.out.println(10>=20); // false
		 System.out.println(10>=2); // true
		 System.out.println(10>=10); // true
		 // 부등호는 < > 같은 크기를 크거나 작다로 판단x
		 
//		좌항 >= 우항 
		 // 1 >= 3  1: 좌항 3: 우항
		 // 숫자에서 쓰는 비교 연산 >= ==
		 
//		  [ .equals ]
//		 .equals 문자열을 비교할 때 사용하는 '메소드'
// 	 	메소드란 : 클래스가 가지고 있는 기능.	
		 //     수학의 함수와 비슷한 개념 
		 //  └ 매개 변수 (parameter) 변수 의미
		 //  └  인자값 
		 Scanner sc = new Scanner(System.in);
	 System.out.println("====");
		String a = "노두희";
		// 
		String b = new String("노두희");
		// 
		String c = "빡빡이";
		System.out.println(a == b ); 
		System.out.println(a.equals(b));
		String ac = sc.nextLine();
		if (ac.equals(a)) {
			System.out.println("노두희는 빡빡이다.");
		} else if (ac.equals(b)){
			System.out.println("두희 젖가슴");
		} else if (ac.equals(c)) {
			System.out.println("두희 빡빡이");
		} else {
			System.out.println("system out");
		}
		
		 
	}

}



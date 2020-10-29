package day03;

public class Demo {

	public static void main(String[] args) {
		// 연산자 다소 난이도가 나머지 
		
		int a = 3 ; 
		System.out.println(0%a); // 0     
		System.out.println(1%a); // 1 
		System.out.println(2%a); // 2     
		System.out.println(3%a); // 0      
		System.out.println(4%a); // 1
		System.out.println(5%a); // 2
		System.out.println(6%a); // 0
		
		// 연산자는 숫자와 숫자를 더할 때 사용되지만
		// 문자열과 문자열을 결합할 때도 사용됨
		
		String firstString = "this is";
		String secString = " a rlatnghksan string ";
		String thirdString = firstString + secString;
		
		System.out.println(thirdString);
		
		// &&  그리고 || 또는  
		// boolean = 비교 연산 = 진위형 
		
	}

}

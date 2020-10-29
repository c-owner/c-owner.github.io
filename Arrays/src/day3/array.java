package day3;

public class array {

	public static void main(String[] args) {

		// 학생이 5명있다.
		// 이 학생들이 순서대로 상담을 받는다.-출력

		String [] stu = {"유재석","이광수","지석진","송지효","김종국"};

		for(int i = 0; i<stu.length; i++) {
			System.out.println(stu[i]);
		}

		for ( int i = 0; i < stu.length; i++ ) {
			String member = stu[i];
			System.out.println("["+member+"] 이(가) 상담을 받았습니다.");
		}
		// for-each 문 
		String [] abc = {"a","bc","def","qwer"};
		for (String e : abc) { 
			System.out.println(e + "이(가) 상담을 받았습니다.");
		}

		String[] mem = new String[3];
		// 배열을 선언 할 때 이 배열의 크기를 3개의 문자열을 수용할 수 있는
		// 크기로 지정했는데 더 많은 데이터를 추가하려고 하면 에러가 뜬다.
		mem[0] = "홍길동";
		mem[1] ="김수환";
		mem[2] = "무거북";
		//			mem[3] = "asd";

		int i;
		int[][] gugu = new int[10][10];
		while (true) {
		
			for (i = 2; i < gugu.length; i++ ) {
				System.out.println(i + "단");
				for (int j = 1; j < gugu[i].length; j++) {
					gugu[i][j] = i * j; // j가 9가 될때까지 곱셈을 수행할것이다.
					System.out.print(i+" * "+ j + " = " + gugu[i][j]+"\n");
				}
			}
		}



	}

}

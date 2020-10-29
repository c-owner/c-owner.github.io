package day3;

import java.util.Arrays;
import java.util.Scanner;

public class array_exam {

	public static void main(String[] args) {
		
		// 크기가 5인 int 배열 선언,
		// 요소를 입력 (자연수 범위에서), 짝수의 개수를 출력하라!
		// 정리 : 배열에 있는 정수가 있고, 내가 뭐 숫자를 입력하면 그 
		// 배열안에있는 짝수 개수만 출력
		// 2,4 = [2개]
		Scanner sc = new Scanner (System.in);
		/*
		System.out.print("자연수 입력 : ");
		int [] arr = new int[5]; // 크기 5 int배열 선언
		int arr2=0;
		
//		[3 | 8 | 1 | 2 |  3]

		// 내가 배열에 요소를 넣겠다.
		// for문을 쓸건데 -> 크기 5 만큼의 요소를 반복해서 넣을거임.
		for (int i=0; i<arr.length; i++) {
			arr[i] = sc.nextInt();
			arr2 = arr[i];	
		} 
		int count = 0; //카운트 변수 선언(=개수 카운팅)
		for (int v : arr) {
			if (v%2 == 0) { //짝수라는 조건을
			count ++;
			}
			System.out.println("짝수 개수는 = "+count+"개 입니다.");
		}		
		*/
		
		// 배열안에 
		int[] data = new int[] {2,3,5,6,8,1,15,4,7,10};
		// 최대값 찾기 알고리즘 
		// 최소값 찾기 
		int max = data[0]; //최대값을 찾을 변수
		int maxIndex=1;
		
		
		for ( int i = 1; i < data.length; i++) {
			if ( max < data[i]) {
					//max의 값보다 data[i]이 크면 max = data[i] 
					max=data[i]; // 다음의 index값이 크면 대입
					maxIndex=i; // 최대값이 몇번째 index에 있는지 추적
			} 
			System.out.println("찾고 있는중 .. "+i+" 회");
		}	System.out.println("최대값 ="+max);
			System.out.println("최대값이 몇번째 index에 위치해있는가 = "+maxIndex);
		
			//최소값 찾기 알고리즘 
			int [] data2 = new int [] {13,15,3,5,4,6,7,1,20,9};

	        int min = data2[0];
	        int minindex = 1;

	        for(int i=0; i<data2.length; i++) {
	        	  if ( min > data2[i] ) {
	        		  min = data2[i];
	        		  minindex = i;
		        	}
	            System.out.println("찾는중."+i);
	        }
		       System.out.println("최소값 = "+min);
	        System.out.println("최소값은 몇번째 위치 인가 :"+minindex);
		// 숙제
	    // 정수를 여러번 입력해서 -> 0보다 큰 (양수) 짝수만 배열에 저장
	    // -> 5개 모두를 채우면 입력 종료 -> 10 이상인 정수의 개수를 출력해라.
	      int[] arr = new int[5];
	      int index = 0;
	      while (true) { // 무한 루프
	    	  int x = sc.nextInt(); //내가 입력한 x 값	
	    	  
	    	  
	      		} 
	      System.out.println(Arrays.toString(arr)); // 스캐너처럼 컨+쉬프트+o 임포트
	      int cnt = 0 ; //카운트 변수 선언
	      for (int v:arr) {
	    	  }
	      }
	      System.out.println("10 이상인 정수 개수 값 = "+cnt+" 개 입니다.");
	      
	      
	      

	        
	        
	       
	        
}

}

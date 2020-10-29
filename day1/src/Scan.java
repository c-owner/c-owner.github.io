import java.util.Scanner;

public class Scan {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

        String message;
        Scanner scan = new Scanner(System.in);      // 문자 입력을 인자로 Scanner 생성
        
        System.out.println("메시지를 입력하세요:");
        
        message = scan.nextLine();            // 키보드 문자 입력
        
        System.out.println("입력 메시지: \""+ message + "\"");
        // 입력 문자 출력
        
        int kilometer;
        double liter, mpg;
        
        System.out.println("거리(km) 값 정수를 입력하세요 : ");
        
        kilometer = scan.nextInt();        // 키보드 숫자 정수 입력
        
        System.out.println("리터 값 를 입력하세요 : ");
        
        liter = scan.nextDouble();        // 키보드 숫자 Double형 입력
        
        mpg = kilometer / liter;          // 입력받은 kilometer와 liter 계산
        
        System.out.println("Kilometer per liter : " + mpg);

	}

}

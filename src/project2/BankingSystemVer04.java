package project2;

import java.util.InputMismatchException;
import java.util.Scanner;
import project2.ver03.AccountManager;
import project2.ver03.MenuSelectException;

public class BankingSystemVer04 {
	
	static final int MAKE=1;
	static final int DEPOSIT=2;
	static final int WITHDRAW=3;
	static final int INQUIRE=4;
	static final int EXIT=5;

	public static void main(String[] args) {
		
		AccountManager acc = new AccountManager(50);
		
		while(true) {
			
			int choice=0;
			
			try {
			//메뉴출력을 위한 메소드호출
				acc.showMenu();
				Scanner scan = new Scanner(System.in);
				choice = scan.nextInt();
			
				switch(choice) {
				case MAKE://생성
					acc.makeAccount();
					break;
				case DEPOSIT://입금
					acc.depositMoney();
					break; 
				case WITHDRAW://출금
					acc.withdrawMoney();
					break;
				case INQUIRE://계좌전체출력
					acc.showAccInfo();
					break;
				case EXIT:
					System.out.println("프로그램종료");
					return; //main메서드의 종료이므로 프로그램 자체의 종료로 이어진다.
				}//switch 끝
				
				if(choice<1 || choice>5) {//사용자지정 예외처리
					MenuSelectException e= new MenuSelectException();
					throw e;
				}
			}
			catch (InputMismatchException e) {//문자입력시
				System.out.println("정상적인 숫자를 입력하세요.");
				e.printStackTrace();
			}
			catch (MenuSelectException e) {
					e.printStackTrace();
			}
			
		}//while 끝
	}//main 끝
	
}

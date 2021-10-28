package project2;

import java.util.Scanner;

import project2.ver05.Account;

public class BankingSystemVer05 {
	
	static final int MAKE=1;
	static final int DEPOSIT=2;
	static final int WITHDRAW=3;
	static final int INQUIRE=4;
	static final int EXIT=5;

	public static void main(String[] args) {
		
		Account ac = new Account();
		
		while(true) {
			
			//메뉴출력을 위한 메소드호출
			ac.showMenu();
			Scanner scan = new Scanner(System.in);
			int choice = scan.nextInt();
			
			switch(choice) {
			case 0://테이블생성
				new Account().table();
				break;
			case MAKE://생성
				ac.makeAccount();
				break;
			case DEPOSIT://입금
				ac.depositMoney();
				break; 
			case WITHDRAW://출금
				ac.withdrawMoney();
				break;
			case INQUIRE://계좌전체출력
				ac.showAccInfo();
				break;
			case EXIT:
				System.out.println("프로그램종료");
				ac.close();
				return; //main메서드의 종료이므로 프로그램 자체의 종료로 이어진다.
			}//switch 끝
		}
	}//main 끝
	

}

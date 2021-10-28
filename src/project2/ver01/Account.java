package project2.ver01;

import java.util.Scanner;

public class Account  {
	
	private String accountID;//계좌번호
	private String customName;//고객이름
	private int accMoney;//잔고
	
	//생성자
	public Account(String accountID, String customName, int accMoney) {
		
		this.accountID = accountID;
		this.customName = customName;
		this.accMoney = accMoney;
	}
	
	public Account[] account;
	public int accountnum;
	
	public Account(int num) {
		
		//num의 크기의 객체배열 생성
		account = new Account[num];
		//최초 실행시 저장된 객체가 없으므로 0으로 초기화
		accountnum = 0;
	}
		
	// 계좌정보출력
	public void showAllData() {
		System.out.println("계좌번호:"+ accountID);
		System.out.println("예금주:"+ customName);
		System.out.println("잔고:"+ accMoney);	
	}
	
	//----------------------기능-------------------------------------------
	public void showMenu() {
		System.out.println("-----Menu------");
		System.out.println("1.계좌개설  2.입금  3.출금");
		System.out.println("4.계좌정보출력  5.프로그램종료");
		System.out.printf("선택:");
	}
	
public void makeAccount() {
		
		Scanner scan = new Scanner(System.in);
		String acID, csName;
		int acMoney;
		
		System.out.println("***신규계좌개설***");
		
		System.out.printf("계좌번호:");acID = scan.nextLine();
		System.out.printf("고객이름:");csName = scan.nextLine();
		System.out.printf("잔고:");acMoney = scan.nextInt();
		
		account[accountnum++] = new Account(acID, csName, acMoney);
		System.out.println("계좌계설이 완료되었습니다.");
	}
		
	public void depositMoney() { //입금
		
		Scanner scan = new Scanner(System.in);
		String acID;
		int money;
		
		System.out.println("계좌번호와 입금할 금액을 입력하세요");
		System.out.printf("계좌번호:");acID = scan.nextLine();
		System.out.printf("입금액:");money = scan.nextInt();
		
		for(int i=0; i<accountnum; i++) {
			if(acID.compareTo(account[i].accountID)==0) 
				account[i].accMoney+=money;
		}
		System.out.println("입금이 완료되었습니다.");
	}
	public void withdrawMoney() { //출금
		
		Scanner scan = new Scanner(System.in);
		String acID;
		int money;
		
		System.out.println("계좌번호와 출금할 금액을 입력하세요");
		System.out.printf("계좌번호:");acID = scan.nextLine();
		System.out.printf("출금액:");money = scan.nextInt();
		
		for(int i=0; i<accountnum; i++) {
			if(acID.compareTo(account[i].accountID)==0) 
				account[i].accMoney-=money;
		}
		System.out.println("출금이 완료되었습니다.");
	}
	public void showAccInfo() {
		
		System.out.println("\n***계좌정보출력***");
		for(int i=0; i<accountnum; i++) {
			System.out.println("---------------");
			account[i].showAllData();
			System.out.println("---------------");
		}
		System.out.println("전체 계좌정보출력이 완료되었습니다.");
	}
	
}

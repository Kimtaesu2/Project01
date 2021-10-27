package project2.ver04;

import java.util.InputMismatchException;
import java.util.Scanner;
import project2.ver04.Account;

public class AccountManager {
	
	public Account[] account;
	public int accountnum;
	
	//생성자
	public AccountManager(int num) {
		//num의 크기의 객체배열 생성
		account = new Account[num];
		//최초 실행시 저장된 객체가 없으므로 0으로 초기화
		accountnum = 0;
	}
	
	public void showMenu() {
		System.out.println("-----Menu------");
		System.out.println("1.계좌개설  2.입금  3.출금");
		System.out.println("4.계좌정보출력  5.프로그램종료");
		System.out.printf("선택:");
	}
	
	public void makeAccount() {
		
		Scanner scan = new Scanner(System.in);
		String acID, csName;
		int acMoney, type, interest;
		char grade;
		
		System.out.println("***신규계좌개설***");
		System.out.println("-----계좌선택------");
		System.out.println("1.보통계좌");
		System.out.println("2.신용신뢰계좌");
		type = scan.nextInt(); scan.nextLine();
		
		System.out.printf("계좌번호:");acID = scan.nextLine();
		System.out.printf("고객이름:");csName = scan.nextLine();
		System.out.printf("잔고:");acMoney = scan.nextInt();
		System.out.println("기본이자%(정수형태로입력):");interest = scan.nextInt(); scan.nextLine();
		
		if(type==1)
			account[accountnum++] = new NormalAccount(acID, csName, acMoney, interest);
		if(type==2) {
			System.out.println("신용등급(A,B,C등급):");
			grade = scan.next().charAt(0);
			account[accountnum++] = new HighCreditAccount(acID, csName, acMoney, interest, grade);
		}
		
		System.out.println("계좌계설이 완료되었습니다.");
		
	}
	
	public void depositMoney() { //입금
		
		try {
			Scanner scan = new Scanner(System.in);
			String acID;
			int money=0;
			String dms;
			
			System.out.println("계좌번호와 입금할 금액을 입력하세요");
			System.out.printf("계좌번호:");acID = scan.nextLine();
			System.out.printf("입금액:");money = scan.nextInt();
			
			if(money % 500 !=0 || money<0) { //예외처리
				throw new Exception();
			}
			
			for(int i=0; i<accountnum; i++) {
				if(acID.compareTo(account[i].getAccountID())==0) {
					
					account[i].setAccMoney(
							account[i].getAccMoney()+
							account[i].acc(account[i].getAccMoney())+money);
				}
			}
			System.out.println("입금이 완료되었습니다.");

		}
		catch (InputMismatchException e) {//문자입력시
			System.out.println("정상적인 숫자를 입력하세요.");
			e.printStackTrace();
		}
		catch (Exception e) {
			System.out.println("500원 단위의 금액만 입금하세요.");
		}
		
	}
	public void withdrawMoney() { //출금
		
		try {
			Scanner scan = new Scanner(System.in);
			String acID;
			int money=0;
			
			System.out.println("계좌번호와 출금할 금액을 입력하세요");
			System.out.printf("계좌번호:");acID = scan.nextLine();
			System.out.printf("출금액:");money = scan.nextInt();
			
			if(money % 1000 !=0 || money<0) { //예외처리
				throw new Exception();
			}
			
			for(int i=0; i<accountnum; i++) {
				if(acID.compareTo(account[i].getAccountID())==0) {
					
					if(money>account[i].getAccMoney()) { //예외처리
						WithdrawException e = new WithdrawException(account, i);
						throw e;
					}
					account[i].setAccMoney(account[i].getAccMoney()-money);
				}
			}
			System.out.println("출금이 완료되었습니다.");
		}
		catch (WithdrawException e) {
			Scanner scan = new Scanner(System.in);
			char select = scan.next().charAt(0);
			
		}
		catch (Exception e) {
			System.out.println("1000원 단위의 금액만 출금하세요.");
			e.printStackTrace();
		}
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

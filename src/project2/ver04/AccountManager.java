package project2.ver04;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashSet;
import java.util.InputMismatchException;
import java.util.Iterator;
import java.util.Scanner;

import project2.ver04.Account;

public class AccountManager {
	
	static int selectsave=0;
	
	HashSet<Account> set;
	
	//생성자
	public AccountManager() {
		set = new HashSet<Account>();
		
		objectInput();
	}
	
	public void showMenu() {
		System.out.println("-----Menu------");
		System.out.println("1.계좌개설  2.입금  3.출금");
		System.out.println("4.계좌정보출력  5.저장옵션  6.프로그램종료");
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
		
		if(type==1) {
			
			NormalAccount noaml = new NormalAccount(acID, csName, acMoney, interest);
			if(!(set.add(noaml))) {
				System.out.println("이미 등록된 계좌번호입니다. 덮어쓸까요?(y or n)");
				char choice = scan.next().charAt(0);
				
				if(choice=='Y' || choice=='y') {
					set.remove(noaml);
					set.add(noaml);
				}
			}
		}
		
		if(type==2) {
			
			System.out.println("신용등급(A,B,C등급):");
			grade = scan.next().charAt(0);
			
			HighCreditAccount High = new HighCreditAccount(acID, csName, acMoney, interest, grade);
			if(!(set.add(High))) {
				System.out.println("이미 등록된 계좌번호입니다. 덮어쓸까요?(y or n)");
				char choice = scan.next().charAt(0);
				
				if(choice=='Y' || choice=='y') {
					set.remove(High);
					set.add(High);
				}
			}
		}
		
		System.out.println("계좌계설이 종료됬습니다.");
		
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
			
			Iterator itr = set.iterator(); //이터레이터 객체 생성 및 준비
			while(itr.hasNext()) { //추출할 객체가 있는지 확인 후
				Account account = (Account) itr.next(); //추출
				
				if(account.getAccountID().equals(acID)) {	//계좌번호 확인후 입금
					
					account.setAccMoney(
							account.getAccMoney()+
							account.acc(account.getAccMoney())+money);
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
			
			
			Iterator itr = set.iterator(); //이터레이터 객체 생성 및 준비
			while(itr.hasNext()) { //추출할 객체가 있는지 확인 후
				Account acc = (Account) itr.next(); //추출
				
				if(acc.getAccountID().equals(acID)) {
					if(acc.getAccMoney()<money) {
						System.out.println("잔고가 부족합니다. 금액전체를 출금할까요? y/n");
						char select = scan.next().charAt(0);
						
						if(select=='y')
							acc.setAccMoney(acc.getAccMoney()-acc.getAccMoney());
						else {
							System.out.println("출금요청 취소");
							throw new Exception();
						}
					}
					else
						acc.setAccMoney(acc.getAccMoney()-money);
				}
			}
			System.out.println("출금이 완료되었습니다.");
		}
		catch (Exception e) {
			System.out.println("1000원 단위의 가진 금액만 출금하세요.");
			e.printStackTrace();
		}
	}
	public void showAccInfo() {
		
		System.out.println("\n***계좌정보출력***");
		Iterator itr = set.iterator();
		while(itr.hasNext()) {
			Account acc = (Account) itr.next();
			
			System.out.println("---------------");
			acc.showAllData();
			System.out.println("---------------");
		}
		System.out.println("전체 계좌정보출력이 완료되었습니다.");
	}
	
	public void saveOption(AutoSaverT save) {
		
		System.out.println("1.자동저장On, 2.자동저장Off");
		Scanner scan = new Scanner(System.in);
		int select = scan.nextInt();
		
		//자동저장
		if(select == 1) {
			if(!(save.isAlive())) {
				save.setDaemon(true);
				save.start();
			}
			else {
				System.out.println("이미 자동저장이 실행중입니다");
				return;
			}
		}
		//자동저장끄기
		else if(select == 2) {
			System.out.println("자동저장 종료");
			if(save.isAlive())
				save.interrupt();
		}
	}
	
	public void objectOutput(String file) {
		
		try {
			//인스턴스를 파일에 저장하기 위해 출력스트림을 생성한다.
			ObjectOutputStream out = new ObjectOutputStream
					(new FileOutputStream(file));
			
			Iterator itr = set.iterator(); //이터레이터 객체 생성 및 준비
			while(itr.hasNext()) { //추출할 객체가 있는지 확인 후
				Account acc = (Account)itr.next(); 
				out.writeObject(acc);
			}
		}
		catch(Exception e) {
			System.out.println("예외발생");
			e.printStackTrace();
		}
	}
	
	public void objectInput() {
		try {
			ObjectInputStream in = 
					new ObjectInputStream(
							new FileInputStream("src/project2/ver04/AccountInfo.obj"));
			
			while(true) {
				Account account = (Account)in.readObject();
				set.add(account);
				if(account==null) break;
			}
		}
		catch (Exception e) {
			System.out.println("더 이상 읽을 객체가 없습니다.");
		}
		
	}

}

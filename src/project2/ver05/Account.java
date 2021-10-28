package project2.ver05;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class Account extends IConnectImpl {
	
	private String accountID;//계좌번호
	private String customName;//고객이름
	private int accMoney;//잔고
	
	//생성자
	public Account() {
		super(ORACLE_DRIVER, "kosmo","1234");
	}
	
	//생성자
	public Account(String accountID, String customName, int accMoney) {
		
		this.accountID = accountID;
		this.customName = customName;
		this.accMoney = accMoney;
	}
	
	@Override //테이블생성
	public void table() {
		try {
			stmt = con.createStatement();
			
			String sql ="create table banking_tb( "
					+ "accnum number(10), "
					+ "accountID varchar2(20) primary key, "
					+ "customName varchar2(20), "
					+ "accMoney number) ";
			
			stmt.executeQuery(sql);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		finally {
			close();
		}
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
	
	@Override
	public void makeAccount() {
		
		Scanner scan = new Scanner(System.in);
		String acID, csName;
		int acMoney;
		
		System.out.println("***신규계좌개설***");
		
		System.out.printf("계좌번호:");acID = scan.nextLine();
		System.out.printf("고객이름:");csName = scan.nextLine();
		System.out.printf("잔고:");acMoney = scan.nextInt();
		try {
			String sql = "insert into banking_tb(accnum, accountID, customName, accMoney) "
					+ "values(seq_banking.nextval, ?, ?, ?)";
			
			psmt = con.prepareStatement(sql);
			
			psmt.setString(1, acID);
			psmt.setString(2, csName);
			psmt.setInt(3, acMoney);
			
			int affected = psmt.executeUpdate();
			System.out.println(affected +"행이 입력되었습니다.");
		}
		catch (Exception e){
			e.printStackTrace();
		}
		
		System.out.println("계좌계설이 완료되었습니다.");
	}
	
	@Override
	public void depositMoney() { //입금
		
		Scanner scan = new Scanner(System.in);
		String acID;
		int money;
		
		System.out.println("계좌번호와 입금할 금액을 입력하세요");
		System.out.printf("계좌번호:");acID = scan.nextLine();
		System.out.printf("입금액:");money = scan.nextInt();
		
		String sql = "UPDATE banking_tb SET accMoney=accMoney+? WHERE accountID=?"; //계정찾아서 돈넣기
		try {
			psmt = con.prepareStatement(sql);
			psmt.setString(2, acID);
			psmt.setInt(1, money);
				
			int affected = psmt.executeUpdate();
			System.out.println(affected +"행이 업데이트 되었습니다.");
			
		}
		catch (Exception e){
			e.printStackTrace();
		}
		
		System.out.println("입금이 완료되었습니다.");
	}
	@Override
	public void withdrawMoney() { //출금
		
		Scanner scan = new Scanner(System.in);
		String acID;
		int money;
		
		System.out.println("계좌번호와 출금할 금액을 입력하세요");
		System.out.printf("계좌번호:");acID = scan.nextLine();
		System.out.printf("출금액:");money = scan.nextInt();
		
		String sql = "UPDATE banking_tb SET accMoney=accMoney-? WHERE accountID=?"; //계정찾아서 돈빼기
		try {
			psmt = con.prepareStatement(sql);
			psmt.setString(2, acID);
			psmt.setInt(1, money);
				
			int affected = psmt.executeUpdate();
			System.out.println(affected +"행이 업데이트 되었습니다.");
			
		}
		catch (Exception e){
			e.printStackTrace();
		}
			
		System.out.println("출금이 완료되었습니다.");
	}
	@Override
	public void showAccInfo() {
		
		System.out.println("\n***계좌정보출력***");
		try {
			//statement객체 생성 및 쿼리작성, 실행
			stmt = con.createStatement();
			
			String query = "SELECT * FROM banking_tb";
			
			rs = stmt.executeQuery(query);
			
			
			System.out.printf("ACCNUM ACCOUNTID CUSTOMNAME ACCMONEY	\n");
			while(rs.next()) {
				int acnumm = rs.getInt(1);
				String acID = rs.getString("accountID");
				String name = rs.getString("customName");
				int money = rs.getInt("accMoney");
				
				System.out.printf("%7d  %7s %7s  %7d\n",
						acnumm, acID, name, money);
			}	
		}
		catch (SQLException e) {
			System.out.println("쿼리실행에 문제가 발생하였습니다.");
			e.printStackTrace();
		}
		catch (Exception e) {
			System.out.println("알수 없는 예외 발생");
			e.printStackTrace();
		}
		
		System.out.println("전체 계좌정보출력이 완료되었습니다.");
	}
	
	
}

package project2.ver05;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

/*
인터페이스를 구현한 클래스로 extends 대신 implements를 사용한다.
또한 용어도 '상속' 이 아닌 '구현' 이라 표현한다.
 */
public class IConnectImpl implements IConnect{
	
	public Connection con;
	public PreparedStatement psmt; //동적쿼리 실행을 위한 객체
	public Statement stmt; //정적쿼리
	public ResultSet rs;
	
	
	//기본(디폴트)생성자
	public IConnectImpl() {
		System.out.println("IConnectImpl 기본생성자 호출");
	}
	//인자생성자1 : 아이디, 패스워드를 인자로 받음
	public IConnectImpl(String user, String pass) {
		System.out.println("IConnectImpl 인자생성자 호출");
		try {
			Class.forName(ORACLE_DRIVER);
			connect(user, pass);
		}
		catch (ClassNotFoundException e) {
			System.out.println("오라클 드라이버 로딩 실패");
			e.printStackTrace();
		}
	}
	//인자생성자2 : 드라이버명까지 인자로 받음
	public IConnectImpl(String driver, String user, String pass) {
		System.out.println("IConnectImpl 인자생성자 호출");
		try {
			Class.forName(driver);
			connect(user, pass);
		}
		catch (ClassNotFoundException e) {
			System.out.println("오라클 드라이버 로딩 실패");
			e.printStackTrace();
		}
	}
	//오라클에 연결
	@Override
	public void connect(String user, String pass) {
		try {
			con = DriverManager.getConnection(ORACLE_URL, user, pass);
		}
		catch (SQLException e) {
			System.out.println("DB 연결 실패");
			e.printStackTrace();
		}
	}
	//오버라이딩의 목적으로 정의한 메서드. 쿼리실행은 각 클래스에서 기술함.
	
	@Override
	public void table() {
		//테이블생성
	}
	@Override
	public void makeAccount() {
		//계좌생성
	}
	@Override
	public void depositMoney() {
		//입금
	}
	@Override
	public void withdrawMoney() {
		//출금
	}
	@Override
	public void showAccInfo() {
		//정보보기
	}

	//자원 반납
	@Override
	public void close() {
		try {
			if(psmt!=null) psmt.close();
			if(con!=null) con.close();
			if(rs!=null) rs.close();
			System.out.println("DB자원반납완료");
		}
		catch (SQLException e) {
			System.out.println("자원반납시 오류가 발생하였습니다.");
			e.printStackTrace();
		}
	}
	//사용자로부터 입력값을 받기 위한 메서드
	@Override
	public String scanValue(String title) {
		
		Scanner scan = new Scanner(System.in);
		System.out.println(title +"을(를) 입력(exit->종료):");
		String inputStr = scan.nextLine();
		/*
		equalsIgnoreCase()
			: equals()와 동일하게 문자열이 같은지를 비교하는 메소드로
			대소문자를 구분 없이 비교할 수 있다.
		 */
		if("EXIT".equalsIgnoreCase(inputStr)) {
			System.out.println("프로그램을 종료합니다.");
			close();
			//프로그램 자체 즉시종료
			System.exit(0);
		}
		return inputStr;
	}
}
package project2.ver05;

public interface IConnect {
	
	/*
	멤버상수
		: 인터페이스에 선언된 변수는 무조건 public static final이
		붙어 정적 상수로 선언된다.
	 */
	String ORACLE_DRIVER = "oracle.jdbc.OracleDriver";
	String ORACLE_URL = "jdbc:oracle:thin:@localhost:1521:xe";
	
	/*
	멤버추상메서드
		: public abstract가 붙어 추상메서드로 선언된다.
	 */
	void connect(String user, String pass);//DB연결
	void table(); //테이블 생성
	void makeAccount(); //계좌생성
	void depositMoney(); //입금
	void withdrawMoney(); //출금
	void showAccInfo();//정보보기
	void close();//자원반납
	
	//사용자 입력을 위한 추상메서드 선언
	String scanValue(String title);
}
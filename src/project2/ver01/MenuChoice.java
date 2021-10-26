package project2.ver01;


public class MenuChoice {
	
	public Account[] account;
	public int accountnum;
	
	//생성자
	public MenuChoice(int num) {
		//num의 크기의 객체배열 생성
		account = new Account[num];
		//최초 실행시 저장된 객체가 없으므로 0으로 초기화
		accountnum = 0;
	}
	
	public void makeAccount() {
		System.out.println("***신규계좌개설***");
	}
	
	public void depositMoney() {
		
	}
	public void withdrawMoney() {
		// 출    금
	}
	public void showAccInfo() {
		// 전체계좌정보출력
	}


}

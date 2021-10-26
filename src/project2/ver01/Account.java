package project2.ver01;

public class Account  {
	
	private String accountID;//계좌번호
	private String customName;//고객이름
	private int accMoney;//잔고
	
	private int accountnum;
	
	//생성자
	public Account(String accountID, String customName, int accMoney) {
		
		this.accountID = accountID;
		this.customName = customName;
		this.accMoney = accMoney;
	}
		
	// 계좌정보출력
	public void showAllData() {
		System.out.println("계좌번호:"+ accountID);
		System.out.println("예금주:"+ customName);
		System.out.println("잔고:"+ accMoney);	
	}
	
	//----------------------기능-------------------------------------------
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

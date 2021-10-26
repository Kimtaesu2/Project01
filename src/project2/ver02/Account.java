package project2.ver02;

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
	
	public String getAccountID() {
		return accountID;
	}
	public String getCustomName() {
		return customName;
	}
	public int getAccMoney() {
		return accMoney;
	}
	
	public void setAccMoney(int accMoney) {
		this.accMoney = accMoney;
	}

	// 계좌정보출력
	public void showAllData() {
		System.out.println("계좌번호:"+ accountID);
		System.out.println("예금주:"+ customName);
		System.out.println("잔고:"+ accMoney);	
	}
	
	//이자율
	public int acc(int num) {
		return 1;
	}
	
}

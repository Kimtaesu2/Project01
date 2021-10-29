package project2.ver03;

public class NormalAccount extends Account {
	
	int interest;

	public NormalAccount(String accountID, String customName, int accMoney, int interest) {
		super(accountID, customName, accMoney);
		this.interest = interest;	
	}
	
	
	@Override
	public int acc(int num) {
		System.out.println("nomal account");
		return (int)(interest*num*0.01);
	}
	
	@Override
	public void showAllData() {
		super.showAllData();
		System.out.println("기본이자:" +interest+ "%");
	}
	
	
	

}

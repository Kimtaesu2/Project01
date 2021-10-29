package project2.ver04;

public class HighCreditAccount extends Account {
	
	int interest;
	char grade;
	int grade_num;
	
	public HighCreditAccount(String accountID, String customName, int accMoney, int interest, char grade) {
		super(accountID, customName, accMoney);
		
		this.interest = interest;
		this.grade = grade;
		if(grade=='A' || grade=='a')
			this.grade_num=7;
		else if (grade=='B' || grade=='b')
			this.grade_num=4;
		else if (grade=='C' || grade=='c')
			this.grade_num=2;
		
	}
	
	@Override
	public int acc(int num) {
		System.out.println("HighCredit accountacc");
		return (int)(grade_num*num*0.01) + (int)(interest*num*0.01);
	}
	
	@Override
	public void showAllData() {
		super.showAllData();
		System.out.println("기본이자:" +interest+ "%");
		System.out.println("신용등급:"+grade);
	}
	

}

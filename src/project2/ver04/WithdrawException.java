package project2.ver04;

public class WithdrawException extends Exception {
	
	public WithdrawException(Account[] account, int i) {
		super("잔고가 부족합니다. 금액전체를 출금할까요? y/n");
		
	}

}

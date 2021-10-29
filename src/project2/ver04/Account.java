package project2.ver04;

import java.io.Serializable;
import java.util.Objects;

public abstract class Account implements Serializable {
	
	String accountID;//계좌번호
	String customName;//고객이름
	int accMoney;//잔고
	
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
	
	//컬렉션 중복처리
	@Override
	public int hashCode() {
		return accountID.hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Account other = (Account) obj;
		return Objects.equals(accountID, other.accountID);
	}
	
	
	
	
	
	
}

package project2.ver04;

public class AutoSaverT extends Thread {
	
	AccountManager acc;
	
	public AutoSaverT(AccountManager acc) {
		this.acc = acc;
	}

	@Override
	public void run() {
		while(true) {
			try {
				acc.objectOutput("src/project2/ver04/AutoSaverT.obj");
				System.out.println("5초마다 자동저장");
				sleep(5000);
			}
			catch(InterruptedException e) {
				System.out.println("자동저장시 오류발생");
				break;
			}
		}
	}

}

package project2.ver03;

public class MenuSelectException extends Exception {
	
	public MenuSelectException() {
		super("메뉴의 1~5사이의 숫자만 입력하세요.");
	}

}

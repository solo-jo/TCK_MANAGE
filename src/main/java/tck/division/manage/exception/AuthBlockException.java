package tck.division.manage.exception;

public class AuthBlockException extends CommonException {

	public AuthBlockException(String url) {
		super("잠긴 계정입니다. 관리자에게 문의 부탁드립니다.");
		this.setCode("0110");
		this.setUrl(url);
	}

}
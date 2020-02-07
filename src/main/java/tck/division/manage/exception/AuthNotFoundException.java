package tck.division.manage.exception;

public class AuthNotFoundException extends CommonException {
	
	public AuthNotFoundException(String url) {
		super("존재하지 않는 계정입니다");
		this.setCode("0100");
		this.setUrl(url);
	}

}
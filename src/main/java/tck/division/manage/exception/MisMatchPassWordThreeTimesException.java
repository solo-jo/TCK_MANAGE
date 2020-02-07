package tck.division.manage.exception;

public class MisMatchPassWordThreeTimesException extends CommonException {

	public MisMatchPassWordThreeTimesException(String url) {
		super("패스워드가 3번 불일치하였습니다.<br/>계정이 잠깁니다.<br/>관리자에게 문의 바랍니다.");
		this.setCode("0200");
		this.setUrl(url);
	}
}

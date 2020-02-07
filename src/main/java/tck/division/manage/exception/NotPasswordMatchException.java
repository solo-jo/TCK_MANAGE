package tck.division.manage.exception;

public class NotPasswordMatchException extends CommonException {

	public NotPasswordMatchException(String url, int FailCnt) {
		super("패스워드가 일치하지 않습니다.(" + FailCnt + ")");
		this.setCode("0200");
		this.setUrl(url);
	}

}
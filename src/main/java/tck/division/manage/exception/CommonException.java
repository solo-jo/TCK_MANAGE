package tck.division.manage.exception;

public class CommonException extends RuntimeException {

	private String code;
	private String url;
	
	public CommonException(String message) {
		super(message);
	}
	
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
}

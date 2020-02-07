package tck.division.manage.model;

public class APIResult {

	private String code;
	private String message;
	private String url;
	
	public APIResult() {
		this.code = "0000"; 	// 정상
	}
	
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	
	
}

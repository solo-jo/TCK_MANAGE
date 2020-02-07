package tck.division.manage.model;

import java.util.List;

import tck.division.manage.entity.Member;

/**
 * 인증데이터
 * @author magenta
 *
 */

public class Authentification {

	private String id;
	private String name;
	private String pw;
	private int fail_cnt;			// 로그인 실패 횟수
	private String init_yn;			// 초기회 된 상태
	private String state;			// 상태(1 : 정상 , 0 : 잠김)
	private List<Menu> listMenu;
	
	public Authentification(Member member) {
		this.id = member.getId();
		this.pw = member.getPassword();
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPw() {
		return pw;
	}
	public void setPw(String pw) {
		this.pw = pw;
	}
	public List<Menu> getListMenu() {
		return listMenu;
	}
	public void setListMenu(List<Menu> listMenu) {
		this.listMenu = listMenu;
	}
	public String getInit_yn() {
		return init_yn;
	}
	public void setInit_yn(String init_yn) {
		this.init_yn = init_yn;
	}
	public int getFail_cnt() {
		return fail_cnt;
	}
	public void setFail_cnt(int fail_cnt) {
		this.fail_cnt = fail_cnt;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	
}

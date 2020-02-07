package tck.division.manage.model;

public class Menu {
	private String menu_code;
	private String menu_title;
	private String menu_desc;
	private String menu_parent;
	private int menu_level;
	private String url;
	
	public String getMenu_code() {
		return menu_code;
	}
	public void setMenu_code(String menu_code) {
		this.menu_code = menu_code;
	}
	public String getMenu_title() {
		return menu_title;
	}
	public void setMenu_title(String menu_title) {
		this.menu_title = menu_title;
	}
	public String getMenu_desc() {
		return menu_desc;
	}
	public void setMenu_desc(String menu_desc) {
		this.menu_desc = menu_desc;
	}
	public String getMenu_parent() {
		return menu_parent;
	}
	public void setMenu_parent(String menu_parent) {
		this.menu_parent = menu_parent;
	}
	public int getMenu_level() {
		return menu_level;
	}
	public void setMenu_level(int menu_level) {
		this.menu_level = menu_level;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
}

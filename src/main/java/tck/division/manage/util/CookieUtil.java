package tck.division.manage.util;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;


@Component("CookieUtil")
public class CookieUtil {
	public HttpServletResponse initPage(HttpServletRequest req,HttpServletResponse res) {
		if(req.getCookies() != null) {
			for(Cookie req_cookie :req.getCookies()) {
				if(req_cookie.getName().equals("data_table_page")) {
					req_cookie.setMaxAge(0); // 쿠키의 expiration 타임을 0으로 하여 없앤다.
				    res.addCookie(req_cookie);
				}
			}
		}
		return res;
	}
}
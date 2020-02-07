package tck.division.manage.config;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Component;

import tck.division.manage.model.Authentification;


@Component
public class AuthentificationFilter implements Filter {
	

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		System.out.println("AuthentificationFilter OK!");
		
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;
		HttpSession session = req.getSession();
		ServletContext context = request.getServletContext();
		String uri = req.getRequestURI();
		
		if(uri.contains("/atcl") || uri.contains("/config")  || uri.contains("/plt") || uri.contains("/viewPW") || uri.contains("/initPW")) {
			
			Authentification auth = (Authentification) session.getAttribute("auth");
			
			if(auth == null) {
				
				// 로그인 페이지로 Redirect!!
				res.sendRedirect("/login");
//				context.getRequestDispatcher("/login").forward(request, response);
			} else {
				// 초기 비밀번호 설정하지 않으면
				if(auth.getInit_yn().equals("N")) {
					res.sendRedirect("/initPW");
//					context.getRequestDispatcher("/initPW").forward(request, response);
				}
				
				// 인증되면 진행
				chain.doFilter(req, res);
			}
			
		} else {
			// 인증되면 진행
			chain.doFilter(req, res);
		}
	}
	
	@Override
	public void destroy() {
		
	}

	
}

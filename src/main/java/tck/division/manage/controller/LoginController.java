package tck.division.manage.controller;

import java.io.UnsupportedEncodingException;
import java.security.GeneralSecurityException;
import java.security.NoSuchAlgorithmException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import tck.division.manage.exception.AuthBlockException;
import tck.division.manage.exception.AuthNotFoundException;
import tck.division.manage.exception.MisMatchPassWordThreeTimesException;
import tck.division.manage.exception.NotPasswordMatchException;
import tck.division.manage.model.APIResult;
import tck.division.manage.model.Authentification;
import tck.division.manage.model.Login;
import tck.division.manage.service.AuthService;
import tck.division.manage.util.AES256Util;


/**
 * 로그인 관련
 * @author magenta
 *
 */
@Controller
public class LoginController {

	private Log log = LogFactory.getLog(LoginController.class);
	
	@Autowired
	private AuthService authService;
	
	@Autowired
	private AES256Util util;

	/*
	 * 로그인 화면
	 */
	@RequestMapping("/login")
	public ModelAndView viewLogin(HttpServletRequest req) {

		ModelAndView mav = new ModelAndView("login");
		return mav;
		
	}

	/*
	 * 로그인
	 */
	@ResponseBody
	@RequestMapping("/doLogin")
	public ResponseEntity<APIResult> doLogin(HttpServletRequest req, @RequestBody @Valid Login login, HttpSession session) throws NoSuchAlgorithmException, UnsupportedEncodingException, GeneralSecurityException {
		log.debug("/doLogin");
		
		APIResult result = new APIResult();
		String viewUrl = "/login";

		/*
		 * 계정정보 조회
		 */
		Authentification auth = authService.getAuthentification(login);
		
		if(auth == null) {
			// 계정이 없으면
			throw new AuthNotFoundException("/login");
			
		} else {
			
			// 계정 검증
//			if(auth.getPw().equals(util.encrypt(login.getPassword()))) {
			if(auth.getPw().equals(login.getPassword())) {
			
//				// 로그인 이력 입력
//				Access access = new Access();
//				access.setAcct_no(auth.getAcct_no());
//				access.setIp(getClientIP(req));
//				authService.insertLogin(access);
				
				// 인증정보 및 메뉴 
				session.setAttribute("auth", auth);
				session.setAttribute("menu", authService.getMenus(auth.getId()));
				
				// 90일 체크
//				int days = authService.getRemainPWday(auth.getId());
//				if(days <= 5) {
//					viewUrl = "/viewPW";
//				} else {
//					viewUrl = "/atcl/list";
//				}
				viewUrl = "/work/get";
				
			} else {
				// 패스워드 불일치
				authService.updateFailCnt(login);
				
				if(auth.getFail_cnt() == 2) {	// 3번째 틀림이므로 계정 잠금
					authService.blockAccount(login);
					throw new MisMatchPassWordThreeTimesException("/login");
				}
				throw new NotPasswordMatchException("/login", auth.getFail_cnt() + 1);
			}
		}
		
		result.setMessage("정상로그인");
		result.setUrl(viewUrl);
    	
    	return new ResponseEntity<APIResult>(result, HttpStatus.OK);
	}
	
//	/**
//	 * 클라이언트 실제 IP수집
//	 * 
//	 * @param request
//	 * @return
//	 */
//	private String getClientIP(HttpServletRequest request) {
//
//		String ip = request.getHeader("X-FORWARDED-FOR");
//		System.out.println("X-FORWARDED-FOR : " + ip);
//
//		if (ip == null || ip.length() == 0) {
//			ip = request.getHeader("Proxy-Client-IP");
//			System.out.println("Proxy-Client-IP : " + ip);
//		}
//
//		if (ip == null || ip.length() == 0) {
//			ip = request.getHeader("WL-Proxy-Client-IP"); // 웹로직
//			System.out.println("WL-Proxy-Client-IP : " + ip);
//		}
//
//		if (ip == null || ip.length() == 0) {
//			ip = request.getRemoteAddr();
//			System.out.println("getRemoteAddr : " + ip);
//		}
//
//		return ip;
//
//	}
//	
//	/*
//	 * 로그아웃
//	 */
//	@RequestMapping("/logout")
//	public ResponseEntity<APIResult> logout(HttpServletRequest req, HttpSession session) {
//		
//		APIResult result = new APIResult();
//		result.setUrl("/login");
//		session.invalidate();
//		return new ResponseEntity<APIResult>(result, HttpStatus.OK);
//	}
//	
//	/*
//	 * 패스워드 초기설정(화면)
//	 */
//	@RequestMapping("/initPW")
//	public ModelAndView initPW(HttpServletRequest req) {
//		ModelAndView mav = new ModelAndView("auth/initPW");
//		return mav; 
//	}
//	
//	/*
//	 * 패스워드 초기설정
//	 */
//	@RequestMapping("/setPW")
//	public ResponseEntity<APIResult> doSetPW(HttpServletRequest req, @RequestBody @Valid LoginDto login, HttpSession session) throws NoSuchAlgorithmException, UnsupportedEncodingException, GeneralSecurityException {
//		
//		APIResult result = new APIResult();
//		
//		/*
//		 * 계정정보 조회
//		 */
//		Authentification auth = (Authentification) session.getAttribute("auth");
//		if(auth == null) {
//			auth = authService.getAuthentification(login);
//		}
//		
//		if(auth == null) {
//			// 계정이 없으면
//			throw new AuthNotFoundException("/login");
//		} else {
//			
//			auth.setPw(util.encrypt(login.getPassword()));
//			
//			//추후 변경해야함
//			if(authService.setPW(auth) > 0) {
//				result.setMessage("패스워드가 정상적으로 수정되었습니다.");
//				result.setUrl("/login");
//			} else {
//				throw new FailPasswordUpdateException("viewPW");
//			}
//			
//			authService.setInit(login);
//			
//		}
//		
//		return new ResponseEntity<APIResult>(result, HttpStatus.OK); 
//	}
//	
//	/*
//	 * 패스워드 설정 - 90일
//	 */
//	@RequestMapping("/viewPW")
//	public ModelAndView viewPW(HttpServletRequest req, HttpSession session) {
//		ModelAndView mav = new ModelAndView("auth/viewPW");
//		
//		/*
//		 * 계정정보 조회
//		 */
//		Authentification auth = (Authentification) session.getAttribute("auth");
//		
//		if(auth == null) {
//			// 계정이 없으면
//			throw new AuthNotFoundException("/login");
//		} else {
//			// 닫기시 진행 url
//			mav.addObject("viewUrl", "atcl/list");
//			
//			// 비밀번호가 만료되었는지 확인
//			mav.addObject("rday", authService.getRemainPWday(auth.getAcct_no()));
//		}
//		
//		return mav; 
//	}
//	
//	/*
//	 * 패스워드 수정
//	 */
//	@ResponseBody
//	@RequestMapping("/updatePW")
//	public ResponseEntity<APIResult> doupdatePW(HttpServletRequest req, @RequestBody @Valid LoginDto login, HttpSession session) throws NoSuchAlgorithmException, UnsupportedEncodingException, GeneralSecurityException {
//		
//		APIResult result = new APIResult();
//		
//		/*
//		 * 계정정보 조회
//		 */
//		Authentification auth = (Authentification) session.getAttribute("auth");
//		
//		if(auth == null) {
//			// 계정이 없으면
//			throw new AuthNotFoundException("/login");
//		} else {
//			
//			// 계정 검증
//			if(auth.getPw().equals(util.encrypt(login.getPrePassword()))) {
//			
//				auth.setPw(util.encrypt(login.getPassword()));
//				
//				//추후 변경해야함
//				if(authService.setPW(auth) > 0) {
//					result.setMessage("패스워드가 정상적으로 수정되었습니다.");
//					result.setUrl("/atcl/list");
//				} else {
//					throw new FailPasswordUpdateException("viewPW");
//				}
//			} else {
//				// 패스워드 불일치
//				throw new NotPasswordMatchException("/viewPW", auth.getFail_cnt() + 1);
//			}
//		}
//		
//		return new ResponseEntity<APIResult>(result, HttpStatus.OK); 
//	}

}

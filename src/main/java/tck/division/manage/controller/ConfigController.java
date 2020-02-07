package tck.division.manage.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import tck.division.manage.entity.Member;
import tck.division.manage.model.APIResult;
import tck.division.manage.model.Authentification;
import tck.division.manage.model.Menu;
import tck.division.manage.model.MenuAuth;
import tck.division.manage.model.SearchMemberParam;
import tck.division.manage.service.AuthService;
import tck.division.manage.service.MemberService;
import tck.division.manage.util.CookieUtil;


@RequestMapping("/config")
@Controller
public class ConfigController {
	
	private Log log = LogFactory.getLog(ConfigController.class);
	
	@Autowired
	private MemberService memberService;
	
	@Autowired
	private AuthService authService;

	@Resource(name = "CookieUtil")
	private CookieUtil cookieUtil;
	
	/*
	 * 유저리스트(화면)
	 */
	@RequestMapping("/member")
	public ModelAndView listUserView(HttpServletRequest req, HttpServletResponse res, SearchMemberParam param) {
		log.debug("/config/member");
		res = cookieUtil.initPage(req,res); //데이터 테이블 페이지 쿠키 초기화
		
		ModelAndView mav = new ModelAndView("config/member/list");
		mav.addObject("param", param);
		return mav;
	}
	
	/*
	 * 유저리스트
	 */
	@ResponseBody
	@RequestMapping("/member/list")
	public ResponseEntity<List<Member>> listUser(HttpServletRequest req, SearchMemberParam param) {
		log.debug("/config/user/list");
		return new ResponseEntity<List<Member>>(memberService.listUser(param), HttpStatus.OK);
	}
	
	/*
	 * 유저상세
	 * 이화면에서 업데이트 가능하도록
	 */
	@RequestMapping("/member/view")
	public ModelAndView viewUser(HttpServletRequest req, @RequestParam(value="memberNo", defaultValue="") String memberNo) {
		log.debug("/config/member/view");
		ModelAndView mav = new ModelAndView("config/member/view");
		
		Member member = memberService.getMember(memberNo);
		List<Menu> menus= authService.listAuthMenu();
		
		mav.addObject("member",member);
		mav.addObject("menus",menus);

		return mav;
	}
	
	/*
	 * 유저등록화면
	 */
	@RequestMapping("/member/addView")
	public ModelAndView viewUserAdd(HttpServletRequest req) {
		log.debug("/config/user/addView");
		ModelAndView mav = new ModelAndView("config/user/addview");
		List<Menu> menus= authService.listAuthMenu();
		
		mav.addObject("menus",menus);
		return mav;
	}
	
	
	/*
	 * 유저아이디 중복체크
	 */
	@ResponseBody
	@RequestMapping("/member/overlap")
	public ResponseEntity<APIResult> overlap(HttpServletRequest req, @RequestBody Member member) {
		log.debug("/config/user/overlap");
		
		APIResult res = new APIResult();
		
		if( memberService.overlapMember(member.getId()) != 0 ) {
			res.setCode("9999");
			res.setMessage("중복된 아이디 입니다.");
		}
		
		return new ResponseEntity<APIResult>(res, HttpStatus.OK);
	}
	
	
	/*
	 * 유저등록
	 */
	@ResponseBody
	@RequestMapping("/member/add")
	public ResponseEntity<APIResult> addUser(HttpServletRequest req, @RequestBody @Valid Member member, HttpSession session) {
		log.debug("/config/user/add");
		
		APIResult res = new APIResult();

		if( memberService.overlapMember(member.getId()) != 0 ) {
			res.setCode("9999");
			res.setMessage("중복된 아이디 입니다.");
			return new ResponseEntity<APIResult>(res, HttpStatus.OK);
		}
		
		
		if( memberService.saveMember(member) != null ) {
			res.setCode("9999");
			res.setMessage("유저등록에 실패하였습니다.");
		} else {
			String[] menuCodes = member.getMenu_codes().split(",");
//			if(menuCodes.length != 0) {
//				MenuAuth menuAuth = new MenuAuth();
//				menuAuth.setAcct_no(member.getAcct_no());
//				
//				Authentification auth = (Authentification) session.getAttribute("auth");
//				if (auth != null) menuAuth.setMenu_code(auth.getId());
//				
//				for(String menuCode : menuCodes) {
//					if(menuCode == null || menuCode.isEmpty()) continue;
//					menuAuth.setMenu_code(menuCode);
//					authService.insertMenuAuth(menuAuth);
//				}
//			}
		}
		
		return new ResponseEntity<APIResult>(res, HttpStatus.OK);
	}
	
	/*
	 * 유저수정
	 */
	@ResponseBody
	@RequestMapping("/member/update")
	public ResponseEntity<APIResult> updateUser(HttpServletRequest req, @RequestBody @Valid  Member member, HttpSession session) {
		log.debug("/config/user/update");
		
		APIResult res = new APIResult();
		
		if( memberService.updateMember(member) != 1 ) {
			res.setCode("9999");
			res.setMessage("변경에 실패하였습니다.");
		}  else {
			String[] menuCodes = member.getMenu_codes().split(",");
//			if(menuCodes.length != 0) {
//				
//				// 메뉴 권한 초기화
//				authService.deleteMenuAuth(acct.getAcct_no());
//				
//				MenuAuth menuAuth = new MenuAuth();
//				menuAuth.setAcct_no(acct.getAcct_no());
//				
//				Authentification auth = (Authentification) session.getAttribute("auth");
//				if (auth != null) menuAuth.setMenu_code(auth.getId());
//				
//				for(String menuCode : menuCodes) {
//					if(menuCode == null || menuCode.isEmpty()) continue;
//					menuAuth.setMenu_code(menuCode);
//					authService.insertMenuAuth(menuAuth);
//				}
//			}
		}
		
		return new ResponseEntity<APIResult>(res, HttpStatus.OK);
	}
	
	/*
	 * 유저삭제
	 */
	@ResponseBody
	@RequestMapping("/member/del")
	public ResponseEntity<APIResult> deleteUser(HttpServletRequest req, @RequestBody Member member) {
		log.debug("/config/user/delete");
		
		APIResult res = new APIResult();
		
		if( memberService.deleteMember(member.getId()) != 1 ) {
			res.setCode("9999");
			res.setMessage("유저삭제에 실패하였습니다.");
		} else {
			// 메뉴 권한 초기화
//			authService.deleteMenuAuth(member.getId());
			
		}
		
		return new ResponseEntity<APIResult>(res, HttpStatus.OK);
	}
	
	
	/*
	 * 패스워드 변경
	 */
	@RequestMapping("/pw/view")
	public ModelAndView viewPW(HttpServletRequest req) {
		log.debug("/config/pw/view");
		ModelAndView mav = new ModelAndView("config/viewPW");
		return mav; 
	}
	
	
	
}

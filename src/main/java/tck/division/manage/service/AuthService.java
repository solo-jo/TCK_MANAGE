package tck.division.manage.service;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tck.division.manage.entity.Member;
import tck.division.manage.model.Authentification;
import tck.division.manage.model.Login;
import tck.division.manage.model.Menu;
import tck.division.manage.repository.MemberRepository;


@Service
public class AuthService {
	
	private static final Logger log = LoggerFactory.getLogger(AuthService.class);
	
	@Autowired
	private MemberRepository memberRepository;

	public List<Menu> listAuthMenu() {
		return null;
	}

	public Authentification getAuthentification(@Valid Login login) {
		
		Authentification auth = null;

		Optional<Member> member = memberRepository.findById(login.getId()); 
		
		if( member.isPresent() ) {
			auth = new Authentification(member.get());
		}
		
		return auth;
	}

	public List<Menu> getMenus(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	public int getRemainPWday(String string) {
		// TODO Auto-generated method stub
		return 0;
	}

	public void updateFailCnt(@Valid Login login) {
		// TODO Auto-generated method stub
		
	}

	public void blockAccount(@Valid Login login) {
		// TODO Auto-generated method stub
		
	}
	

	
	
}

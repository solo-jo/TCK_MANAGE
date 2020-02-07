package tck.division.manage.service;

import java.util.List;

import javax.validation.Valid;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tck.division.manage.entity.Member;
import tck.division.manage.model.SearchMemberParam;
import tck.division.manage.repository.MemberRepository;


@Service
public class MemberService {
	
	private Log log = LogFactory.getLog(MemberService.class);
	
	@Autowired
	private MemberRepository memberRepo;
	
	public Member saveMember(Member member) {
		return memberRepo.save(member);
	}

	public List<Member> listUser(SearchMemberParam param) {
		return null;
	}

	public Member getMember(String memberNo) {
		// TODO Auto-generated method stub
		return null;
	}

	public int overlapMember(String id) {
		// TODO Auto-generated method stub
		return 0;
	}

	public int updateMember(@Valid Member member) {
		
		return 0;
	}

	public int deleteMember(String id) {
		// TODO Auto-generated method stub
		return 0;
	}
}

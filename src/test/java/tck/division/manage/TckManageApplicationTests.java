package tck.division.manage;

import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import tck.division.manage.entity.Member;
import tck.division.manage.service.MemberService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TckManageApplicationTests {

	private static Logger logger = LoggerFactory.getLogger(TckManageApplicationTests.class);
	
	@Autowired
	private MemberService memberService;
	
	@Test
	public void contextLoads() {
		
		Member member = new Member();
		member.setId("AA111");
		member.setName("KK");
		member.setName_jp("JP");
		member.setDiv_code("AAA");
		member.setPassword("LLLL");
		member.setReg_dt(new Date());
		
		memberService.saveMember(member);
	}

}

package tck.division.manage.service;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tck.division.manage.mapper.StatisticsMapper;
import tck.division.manage.model.ProjPerMemberForMonth;
import tck.division.manage.model.StatisticMemberProject;

@Service
public class StatisticService {

	private Log log = LogFactory.getLog(MemberService.class);
	
	@Autowired
	private StatisticsMapper sMapper;
	
	public List<StatisticMemberProject> listStatisticMemberProject(String month) {
		
		List<ProjPerMemberForMonth> lPPMM = sMapper.listProjPerMemberForMonth(month);
		
		// 해당 기간동안 발생된 근무 프
		List<String> lPname = sMapper.listPname(month);
		
		for (ProjPerMemberForMonth pm : lPPMM) {
			
		}
		
		
		
		return null;
	}
	
}

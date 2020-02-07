package tck.division.manage.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import tck.division.manage.model.ProjPerMemberForMonth;
import tck.division.manage.model.StatisticMemberProject;

@Mapper
public interface StatisticsMapper {

	List<String> listPname(String month);

	List<StatisticMemberProject> listMname(String month);

	List<ProjPerMemberForMonth> listProjPerMemberForMonth(String month);
	
	

}

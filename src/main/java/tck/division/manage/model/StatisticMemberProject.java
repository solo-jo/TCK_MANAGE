package tck.division.manage.model;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class StatisticMemberProject {

	private String memberNo;
	private String name;
	private List<ProjectTime> lPTime;
	
	public StatisticMemberProject() {
		this.lPTime = new ArrayList<ProjectTime>();
	}
}

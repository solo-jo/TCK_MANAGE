package tck.division.manage.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tck.division.manage.entity.WorkTime;
import tck.division.manage.model.SearchParam;
import tck.division.manage.repository.WorkRepository;

@Service
public class WorkService {
	
	private Log log = LogFactory.getLog(WorkService.class);
	
	@Autowired
	private WorkRepository workRepo; 
	
	public WorkTime saveWorkTime(WorkTime work) {
		return workRepo.save(work);
	}

	public List<WorkTime> listWorkTime(SearchParam param) {
		return workRepo.listWorkTime(param);
	}
}

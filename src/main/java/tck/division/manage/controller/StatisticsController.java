package tck.division.manage.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import tck.division.manage.model.SearchParam;
import tck.division.manage.service.FileStorageService;
import tck.division.manage.service.StatisticService;

@RequestMapping("/statistics")
@Controller
public class StatisticsController {

	private Log log = LogFactory.getLog(StatisticsController.class);
	
	@Autowired
    private StatisticService statisticService;
	
	/**
	 * 개인업무 등록내용 조회(화면)
	 * @param req
	 * @return
	 */
	@RequestMapping("/work/proj_member_month")
	public ModelAndView getWork(HttpServletRequest req, SearchParam param) {
		log.info("/statistics/work/proj_member_month");
		ModelAndView mav = new ModelAndView("work/search");
		
		mav.addObject("param", param);
		
		return mav;
	}
	
}

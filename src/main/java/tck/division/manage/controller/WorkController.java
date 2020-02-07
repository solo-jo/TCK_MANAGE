package tck.division.manage.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import tck.division.manage.entity.WorkTime;
import tck.division.manage.model.SearchParam;
import tck.division.manage.service.FileStorageService;
import tck.division.manage.service.WorkService;
import tck.division.manage.util.ExcelUtil;

@RestController
@RequestMapping("/work")
public class WorkController {

	private Log log = LogFactory.getLog(WorkController.class);
	
	@Value("${file.upload-dir}")
	private String uploadRootPath;
	
	@Autowired
    private FileStorageService fileStorageService;
	
	@Autowired
	private WorkService workService;
	
	@Autowired
	private ExcelUtil excelUtil;
	
	/**
	 * 개인업무 등록내용 조회(화면)
	 * @param req
	 * @return
	 */
	@RequestMapping("/get")
	public ModelAndView getWork(HttpServletRequest req, SearchParam param) {
		log.info("/work/get");
		ModelAndView mav = new ModelAndView("work/search");
		
		mav.addObject("param", param);
		
		return mav;
	}
	
	/**
	 * 개인업무 등록내용 조회
	 * @param req
	 * @param param
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/ajax/get")
	public ResponseEntity<List<WorkTime>> ajaxListWork(HttpServletRequest req, SearchParam param) {
		log.info("/work/ajax/get");
		return new ResponseEntity<List<WorkTime>>(workService.listWorkTime(param), HttpStatus.OK);
	}
	
	/**
	 * 개인업무 등록화면 
	 * @param req
	 * @return
	 */
	@RequestMapping("/addView")
	public ModelAndView addWorkView(HttpServletRequest req, HttpSession session) {
		
		ModelAndView mav = new ModelAndView("work/addView");
		
		List<WorkTime> lWorkTime = (List<WorkTime>)session.getAttribute("listWork");
		
		if(lWorkTime != null) {
			mav.addObject("lWorkTime", lWorkTime);
		} else {
			mav.addObject("lWorkTime", new ArrayList());
		}
		
		return mav;
	}
    
    @PostMapping("/registerWork")
    public ResponseEntity<List<WorkTime>> registerExcelFile(@RequestParam("file") MultipartFile file, HttpSession session) throws Exception {
    	
    	String savePath = "excel";
        String fileName = fileStorageService.storeFile(file, savePath);
        
        List<WorkTime> lWorkTime = excelUtil.readWorkTimeExcelFile(uploadRootPath + "/" + savePath + "/" + fileName);
        
        // 세션에 업무정보 저장 
		session.setAttribute("listWork", lWorkTime);

        return new ResponseEntity<List<WorkTime>>(lWorkTime, HttpStatus.OK);
    }
    
    @PostMapping("/saveWork")
    public String saveWork(HttpSession session) throws Exception {
    	
    	List<WorkTime> lWorkTime = (List<WorkTime>)session.getAttribute("listWork");
        
        if(lWorkTime != null) {
        	for(WorkTime time : lWorkTime) {
            	workService.saveWorkTime(time);
            }
        }
        
        return "success";
    }

}


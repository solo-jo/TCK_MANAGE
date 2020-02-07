package tck.division.manage.util;

import java.io.FileInputStream;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Component;

import tck.division.manage.entity.WorkTime;

@Component
public class ExcelUtil {
	
	static final int MINUTES_PER_HOUR = 60;
    static final int SECONDS_PER_MINUTE = 60;
    static final int SECONDS_PER_HOUR = SECONDS_PER_MINUTE * MINUTES_PER_HOUR;

	public List<WorkTime> readWorkTimeExcelFile(String filename) throws Exception {
		
		String memberNo = null;
		List<WorkTime> lWorkTime = new ArrayList<WorkTime>();
		
		InputStream inputStream = null;
		
		try {
			int cnt = 6; // 
			inputStream = new FileInputStream(filename);
//			HSSFWorkbook workbook = new HSSFWorkbook(inputStream); 	// EXCEL 2003 version
			XSSFWorkbook workbook = new XSSFWorkbook(inputStream);	// EXCEL 2007 이상 
			Sheet sheet = workbook.getSheetAt(0);
			Row row = null;
			
			memberNo = sheet.getRow(2).getCell(7).getStringCellValue();
			
			while((row = sheet.getRow(cnt)) != null) {

				WorkTime workTime = new WorkTime();
				
				Cell cWorkDt = row.getCell(1);  // work_dt
				Cell cStartTime = row.getCell(2);  // start_time
				Cell cEndTime = row.getCell(3);  // end_time
				Cell cProjName = row.getCell(4);  // proj_name
				Cell cWorkCategory = row.getCell(5);  // work_category
				Cell cWorkDetail = row.getCell(6);  // work_detail
				Cell cWorkDesc = row.getCell(7);  // work_desc
				
				if(cWorkDt.getCellType() == CellType.BLANK) {
					break;
				}
				
				workTime.setMember_no(memberNo);
				workTime.setWork_dt(new SimpleDateFormat("yyyyMMdd").format(DateUtil.getJavaDate(cWorkDt.getNumericCellValue())));
				workTime.setStart_time(DateUtil.getLocalDateTime(cWorkDt.getNumericCellValue() + cStartTime.getNumericCellValue()));
				workTime.setEnd_time(DateUtil.getLocalDateTime(cWorkDt.getNumericCellValue() + cEndTime.getNumericCellValue()));
				workTime.setWork_time(getDiffTime(workTime.getStart_time(), workTime.getEnd_time()));
				workTime.setProj_name(cProjName.getStringCellValue());
				workTime.setWork_category(cWorkCategory.getStringCellValue());
				workTime.setWork_detail(cWorkDetail.getStringCellValue());
				workTime.setWork_desc(cWorkDesc.getStringCellValue());
				workTime.setReg_dt(LocalDateTime.now());
				
				cnt++;
				
				lWorkTime.add(workTime);
			}
		
			
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			try { if(inputStream != null) inputStream.close(); } catch(Exception e) {}
		}
		
		return lWorkTime;
	}
	
	
	private int getDiffTime(LocalDateTime start_time, LocalDateTime end_time) {
		
		ZonedDateTime start_zdt = start_time.atZone(ZoneId.of("Asia/Seoul"));
		ZonedDateTime end_zdt = end_time.atZone(ZoneId.of("Asia/Seoul"));
		
		long diffTime = end_zdt.toInstant().toEpochMilli() - start_zdt.toInstant().toEpochMilli();
		
		return (int)(diffTime / (60 * 1000));
	}
	
	
}

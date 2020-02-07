package tck.division.manage.dialect;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * CommonUtil
 * 
 * @author tslee
 *
 */
public class CommonUtil {
	private Log log = LogFactory.getLog(CommonUtil.class);

	public static String getVersion(String Path) {
		Date from = new Date();
		SimpleDateFormat transFormat = new SimpleDateFormat("yyyyMMddHHmmss");
		String versionName = transFormat.format(from);
		Path += "?version=" + versionName;
		return Path;
	}
	
	
	/**
	 * 유저 상태
	 *
	 * @param state
	 * @return String
	 */
	public static String getUserState(String state) {
		if(state == null) return "";
		
		if (state.equals("1"))
			return "사용";
		else if (state.equals("0"))
			return "미사용";
		else
			return "";
	}
	
	
	/**
	 * 참여사 상태
	 *
	 * @param state
	 * @return String
	 */
	public static String getCompanyState(String state) {
		if(state == null) return "";
		
		if (state.equals("1"))
			return "사용";
		else if (state.equals("0"))
			return "미사용";
		else
			return "";
	}
	
	

	/**
	 * 신고 사유
	 *
	 * @param state
	 * @return String
	 */
	public static String getDclRsnCd(String dcl_rsn_cd) {
		if(dcl_rsn_cd == null) return "";
		
		if (dcl_rsn_cd.equals("CMPL"))
			return "거래완료";
		else if (dcl_rsn_cd.equals("AUCT"))
			return "경매매물";
		else if (dcl_rsn_cd.equals("PRICE"))
			return "허위매물가격";
		else if (dcl_rsn_cd.equals("ETC"))
			return "기타";
		else
			return "";
	}
	
	
	/**
	 * 신고 사유
	 *
	 * @param state
	 * @return String
	 */
	public static String getEvdTpCd(String evd_tp_cd) {
		if(evd_tp_cd == null) return "";
		
		if (evd_tp_cd.equals("tel"))
			return "전화로 확인";
		else if (evd_tp_cd.equals("visit"))
			return "방문하여 확인";
		else if (evd_tp_cd.equals("etc"))
			return "기타";
		else
			return "";
	}
}

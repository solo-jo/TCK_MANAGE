/*<![CDATA[*/
var AtclDetail = {
	init : function() {
		
		$("#result_frm").find("#site_vst_ymdt").change(function(){
			 AtclDetail.SetRltrSndMsg($("#result_frm").find("input[name=trt_rslt_cd]:checked").val());
		 });
		
		$("#update_frm").find("#site_vst_ymdt").change(function(){
			 AtclDetail.SetUpdateRltrSndMsg($("#update_frm").find("input[name=trt_rslt_cd]:checked").val());
		 });
		
		$("#result_frm").find("input[name=trt_rslt_cd]").click(function(){
			if(this.value =="NOMAL" || this.value =="IMPL" || this.value =="IMPS" ){
				$("#result_frm").find("input[name=plt_type_cd]").prop("checked",false);
				$("#result_frm").find("input[name=plt_type_cd]").attr("disabled",true);
			}else {
				$("#result_frm").find("input[name=plt_type_cd]").removeAttr("disabled");
			}
			AtclDetail.SetRltrSndMsg(this.value);
		})
		
		$("#update_frm").find("input[name=trt_rslt_cd]").click(function(){
			if(this.value =="NOMAL" || this.value =="IMPL" || this.value =="IMPS" ){
				$("#update_frm").find("input[name=plt_type_cd]").prop("checked",false);
				$("#update_frm").find("input[name=plt_type_cd]").attr("disabled",true);
			}else {
				$("#update_frm").find("input[name=plt_type_cd]").removeAttr("disabled");
			}
			AtclDetail.SetUpdateRltrSndMsg(this.value);
		})
	},
	updateTableToggle : function(type) {
		if(type == 0){
			$('#update_table').hide();
		}
		else if(type == 1){
			$("#update_frm")[0].reset()
			$('#update_table').show();
		}
	},
	updateSumit : function() {
		var trt_rslt_cd =  $("#update_frm").find("input[name=trt_rslt_cd]:checked").val();
		var site_vst_ymdt =  $("#update_frm").find("#site_vst_ymdt").val();
		
		if(site_vst_ymdt == null || site_vst_ymdt == undefined || site_vst_ymdt == ""){
			TckJS.Util.Alert("방문일시를 입력해주세요.");
			return false;
		}
		
		if(trt_rslt_cd == "DENY" || trt_rslt_cd == "FALSE"){
			if($("#update_frm").find("input[name=plt_type_cd]:checked").length == 0){
				TckJS.Util.Alert("페널티구분을 선택해주세요.");
				return false;
			}
		}
		
		//현장검증 재처리
		TckJS.Util.Confirm("재처리하시겠습니까?",
		function () {
			var requestData = {}
	        var frm_data = $("#update_frm").serializeArray();
			for (var index in frm_data) {
				requestData[frm_data[index].name] = frm_data[index].value;
	        }
			
	        TckJS.Ajax.postAjaxLoding("/atcl/update", requestData, function (res) {
	        	TckJS.Util.Alert("처리되었습니다.",function () { location.reload(); });
	        })
		});
	},
	ResultSumit : function() {
		var trt_rslt_cd =  $("#result_frm").find("input[name=trt_rslt_cd]:checked").val();
		var site_vst_ymdt =  $("#result_frm").find("#site_vst_ymdt").val();
		
		if(site_vst_ymdt == null || site_vst_ymdt == undefined || site_vst_ymdt == ""){
			TckJS.Util.Alert("방문일시를 입력해주세요.");
			return false;
		}
		
		
		if(trt_rslt_cd == "DENY" || trt_rslt_cd == "FALSE"){
			if($("#result_frm").find("input[name=plt_type_cd]:checked").length == 0){
				TckJS.Util.Alert("페널티구분을 선택해주세요.");
				return false;
			}
		}
		
		//현장검증 결과확인 처리
		//각 타입별로 따로 검사를 해야함 결과에 따라 페널티 여부 등을 입력해야한다.
		var requestData = {}
		var frm_data = $("#result_frm").serializeArray();
		for (var index in frm_data) {
			requestData[frm_data[index].name] = frm_data[index].value;
        }
		
		/*
		if(requestData["site_vst_ymdt"] != undefined){
			requestData["site_vst_ymdt"] = requestData["site_vst_ymdt"].replace(/(\-)/g, "");
		}
		*/
		
		TckJS.Ajax.postAjaxLoding("/atcl/update", requestData, function (res) {
			TckJS.Util.Alert("처리되었습니다.",function () { location.reload(); });
        })
	},
	SetRltrSndMsg : function (value){
		var RltrSndMsg = "";
		var sp_atcl_no = $("#sp_atcl_no").val();
		var rltr_nm = $("#rltr_nm").val();
		var site_vst_ymdt = $("#site_vst_ymdt").val();
		if(site_vst_ymdt == ""){
			TckJS.Util.Alert("방문일시를 입력해주세요.");
			return false;
		}
		
		site_vst_ymdt = $("#site_vst_ymdt").val().replace("-","년 ").replace("-","월 ")+"일";
		
		if(value == "FALSE"){
			RltrSndMsg = "안녕하세요. KISO부동산클린매물관리센터입니다.";
			RltrSndMsg += "\n"+ site_vst_ymdt +" 중개업소에 방문하여 "+rltr_nm+"님과 상담, 획안결과 매물번호("+sp_atcl_no+")매물은 이미 거래된 매물로써 허위매물임이 확인되었습니다.";
			RltrSndMsg += "\n부동산 매물클린관리센터 '허위 및 거짓매물 관리' 운영규정에 따라 14일간 매물등록이 제한되오니 확인 부탁드립니다.";
			$("#result_frm").find("#plt_type_cd_2").prop("checked",true)
		}
		else if(value == "IMPS"){
			RltrSndMsg = "안녕하세요!~ 한국인터넷자율정책기구(KISO)입니다!~";
			RltrSndMsg += "\n"+site_vst_ymdt+" 중개업소에 방문하여  "+rltr_nm+"님과 상담, 매물번호("+sp_atcl_no+") 매물확인을 해주려 하셨으나 매물의 소유자 (또는 의뢰인)의 확인거부 등의 사유로 검증할 수 없었습니다.";
		}
		else if(value == "DENY"){
			RltrSndMsg = "안녕하세요!~ 한국인터넷자율정책기구(KISO)입니다!~";
			RltrSndMsg += "\n"+site_vst_ymdt+" 중개업소에 방문하여  "+rltr_nm+"님과 상담, 매물번호("+sp_atcl_no+") 매물확인을 요청하였으나 매물검증을 거부하였습니다.";
			$("#result_frm").find("#plt_type_cd_2").prop("checked",true)
		}
		else if(value == "NOMAL"){
			RltrSndMsg = "안녕하세요!~ 한국인터넷자율정책기구(KISO)입니다!~";
			RltrSndMsg += "\n"+site_vst_ymdt+" 중개업소에 방문하여  "+rltr_nm+"님과 상담, 매물번호("+sp_atcl_no+") 매물은 거래가능한 정상매물임이 확인되었습니다.";
		}
		
		$("#result_frm").find("#rltr_snd_msg").val(RltrSndMsg);
	},
	SetUpdateRltrSndMsg : function (value){
		var RltrSndMsg = "";
		var sp_atcl_no = $("#sp_atcl_no").val();
		var rltr_nm = $("#rltr_nm").val();
		var site_vst_ymdt = $("#site_vst_ymdt").val();
		if(site_vst_ymdt == ""){
			TckJS.Util.Alert("방문일시를 입력해주세요.");
			return false;
		}
		
		site_vst_ymdt = $("#site_vst_ymdt").val().replace("-","년 ").replace("-","월 ")+"일";			
		
		if(value == "FALSE"){
			RltrSndMsg = "안녕하세요. KISO부동산클린매물관리센터입니다.";
			RltrSndMsg += "\n"+ site_vst_ymdt +" 중개업소에 방문하여 "+rltr_nm+"님과 상담, 획안결과 매물번호("+sp_atcl_no+")매물은 이미 거래된 매물로써 허위매물임이 확인되었습니다.";
			RltrSndMsg += "\n부동산 매물클린관리센터 '허위 및 거짓매물 관리' 운영규정에 따라 14일간 매물등록이 제한되오니 확인 부탁드립니다.";
			$("#update_frm").find("#plt_type_cd_2").prop("checked",true)
		}
		else if(value == "IMPS"){
			RltrSndMsg = "안녕하세요!~ 한국인터넷자율정책기구(KISO)입니다!~";
			RltrSndMsg += "\n"+site_vst_ymdt+" 중개업소에 방문하여  "+rltr_nm+"님과 상담, 매물번호("+sp_atcl_no+") 매물확인을 해주려 하셨으나 매물의 소유자 (또는 의뢰인)의 확인거부 등의 사유로 검증할 수 없었습니다.";
		}
		else if(value == "DENY"){
			RltrSndMsg = "안녕하세요!~ 한국인터넷자율정책기구(KISO)입니다!~";
			RltrSndMsg += "\n"+site_vst_ymdt+" 중개업소에 방문하여  "+rltr_nm+"님과 상담, 매물번호("+sp_atcl_no+") 매물확인을 요청하였으나 매물검증을 거부하였습니다.";
			$("#update_frm").find("#plt_type_cd_2").prop("checked",true)
		}
		else if(value == "NOMAL"){
			RltrSndMsg = "안녕하세요!~ 한국인터넷자율정책기구(KISO)입니다!~";
			RltrSndMsg += "\n"+site_vst_ymdt+" 중개업소에 방문하여  "+rltr_nm+"님과 상담, 매물번호("+sp_atcl_no+") 매물은 거래가능한 정상매물임이 확인되었습니다.";
		}
		
		$("#update_frm").find("#rltr_snd_msg").val(RltrSndMsg);
	}
}
/* ]]> */
/*<![CDATA[*/ 
var AtclDetail = {
	init : function() {
		$("#result_frm").find("input[name=trt_rslt_cd]").click(function(){
			if(this.value =="NOMAL" || this.value =="IMPL"){
				$("#result_frm").find("input[name=plt_type_cd]").prop("checked",false);
				$("#result_frm").find("input[name=plt_type_cd]").attr("disabled",true);
			}else {
				$("#result_frm").find("input[name=plt_type_cd]").removeAttr("disabled");
			}
			AtclDetail.SetRltrSndMsg(this.value);
		})
		
		$("#update_frm").find("input[name=trt_rslt_cd]").click(function(){
			if(this.value =="NOMAL" || this.value =="IMPL"){
				$("#update_frm").find("input[name=plt_type_cd]").prop("checked",false);
				$("#update_frm").find("input[name=plt_type_cd]").attr("disabled",true);
			}else {
				$("#update_frm").find("input[name=plt_type_cd]").removeAttr("disabled");
			}
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
	ResultSumit : function() {
		//유선검증 결과확인 처리
		//각 타입별로 따로 검사를 해야함 결과에 따라 페널티 여부 등을 입력해야한다. 
		var trt_rslt_cd =  $("#result_frm").find("input[name=trt_rslt_cd]:checked").val();
		if(trt_rslt_cd == "ABEND" || trt_rslt_cd == "FALSE"){
			if($("#result_frm").find("input[name=plt_type_cd]:checked").length == 0){
				TckJS.Util.Alert("페널티구분을 선택해주세요.");
				return false;
			}
		}
		
		var requestData = {}
		
		var frm_data = $("#result_frm").serializeArray();
		for (var index in frm_data) {
			requestData[frm_data[index].name] = frm_data[index].value;
        }
		
		TckJS.Ajax.postAjaxLoding("/atcl/update", requestData, function (res) {
			TckJS.Util.Alert("처리되었습니다.",function () { location.reload(); });
        })
	},
	updateSumit : function() {
		
		var trt_rslt_cd =  $("#update_frm").find("input[name=trt_rslt_cd]:checked").val();
		if(trt_rslt_cd == "ABEND" || trt_rslt_cd == "FALSE"){
			if($("#update_frm").find("input[name=plt_type_cd]:checked").length == 0){
				TckJS.Util.Alert("페널티구분을 선택해주세요.");
				return false;
			}
		}
		
		
		//유선확인 재처리
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
	SetRltrSndMsg : function (value){
		var RltrSndMsg = "";
		var sp_atcl_no = $("#sp_atcl_no").val();
		var trad_tp_nm = $("#trad_tp_nm").val();
		var rlet_tp_nm = $("#rlet_tp_nm").val();
		var cortar_no = $("#cortar_no").val();
		var addr = $("#addr").val();
		var full_addr = cortar_no + " "+ addr;
		
		if(value == "ABEND" || value == "FALSE"){
			RltrSndMsg = "안녕하세요. KISO부동산클린매물관리센터입니다.";
			RltrSndMsg += "\n유선검증 결과 등록해주신 매물 중 ["+full_addr+"] "+ rlet_tp_nm +"-"+trad_tp_nm+"("+sp_atcl_no +")매물이 거래가 불가능한 매물로 확인되었습니다.";
			RltrSndMsg += "\n부동산 매물클린관리센터 '허위 및 거짓매물 관리' 운영규정에 따라 7일간 매물등록이 제한되오니 확인 부탁드립니다.";
			$("#plt_type_cd_2").prop("checked",true)
		}
		
		$("#result_frm").find("#rltr_snd_msg").val(RltrSndMsg);
	}
}
/* ]]> */
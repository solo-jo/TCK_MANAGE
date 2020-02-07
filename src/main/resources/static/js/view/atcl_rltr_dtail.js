/*<![CDATA[*/ 
var AtclDetail = {
	init : function() {
		$("#result_frm").find("input[name=trt_rslt_cd]").click(function(){
			if(this.value =="NOMAL"|| this.value =="CMPL"){
				$("#result_frm").find("input[name=plt_type_cd]").prop("checked",false);
				$("#result_frm").find("input[name=plt_type_cd]").attr("disabled",true);
			}else {
				$("#result_frm").find("input[name=plt_type_cd]").removeAttr("disabled");
			}
		})
		
		$("#update_frm").find("input[name=trt_rslt_cd]").click(function(){
			if(this.value =="NOMAL"|| this.value =="CMPL"){
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
	updateSumit : function() {
		
		var trt_rslt_cd =  $("#update_frm").find("input[name=trt_rslt_cd]:checked").val();
		if(trt_rslt_cd == "FALSE"){
			if($("#update_frm").find("input[name=plt_type_cd]:checked").length == 0){
				TckJS.Util.Alert("페널티구분을 선택해주세요.");
				return false;
			}
		}
		
		//중계사 처리 재처리
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
	}
}
/* ]]> */
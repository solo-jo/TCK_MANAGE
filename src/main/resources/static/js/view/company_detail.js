/*<![CDATA[*/
var CompanyDetail = {
	overlap : function() {
		var id = $("#frm").find("#id").val().trim();
		if(id != ""){
			TckJS.Ajax.postAjax("/config/company/overlap", {id:id}, function (res) {
				$("#frm").find("#overlap").val("1");
	        });
		}else {
			TckJS.Util.Alert("아이디를 입력해주세요.");
		}
	},	
	insertSumit : function() {
		if($("#frm").find("#overlap").val() == "0"){
			TckJS.Util.Alert("아이디를 확인해 주세요.");
			return false;
		}
		
		var ValidateEl = $("#frm [data-required]");
		TckJS.ValidateUtil.ValidateCheck(ValidateEl, function() {
			var requestData = {}
			var frm_data = $("#frm").serializeArray();
			
			for (var index in frm_data) {
				requestData[frm_data[index].name] = frm_data[index].value;
            }
			
			TckJS.Ajax.postAjaxLoding("/config/company/add", requestData, function (res) {
				TckJS.Util.Alert("저장되었습니다.",function () { location.href= '/config/company'; });
	        })
		});
	},
	updateSumit : function() {
		TckJS.Util.Confirm("수정하시겠습니까?",
			function () {
				var requestData = {}
				var frm_data = $("#frm").serializeArray();
				
				for (var index in frm_data) {
					requestData[frm_data[index].name] = frm_data[index].value;
		        }
				
				TckJS.Ajax.postAjaxLoding("/config/company/update", requestData, function (res) {
					TckJS.Util.Alert("수정되었습니다.",function () { location.href= '/config/company'; });
		        })
			});
	},
	deleteSumit : function() {
		TckJS.Util.Confirm("삭제하시겠습니까?",
		function () {
			var requestData = {}
			TckJS.Ajax.postAjaxLoding("/config/company/delete", { id : $("#id").val() }, function (res) {
				TckJS.Util.Alert("삭제되었습니다.",function () { location.href= '/config/company'; });
	        })
		});
	}
}
/* ]]> */
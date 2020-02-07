/*<![CDATA[*/
var UserDetail = {
	overlap : function() {
		var id = $("#frm").find("#id").val().trim();
		if(id != ""){
			TckJS.Ajax.postAjax("/config/user/overlap", {id:id}, function (res) {
				//TckJS.Util.Alert("사용가능한 아이디 입니다.");
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
			var menu_codes = [];
			var frm_data = $("#frm").serializeArray();
			for (var index in frm_data) {
				if(frm_data[index].name == "menu_codes"){
					menu_codes.push(frm_data[index].value);
				}else {
					requestData[frm_data[index].name] = frm_data[index].value;
				}
            }
			requestData["menu_codes"] = menu_codes.join(",");
			TckJS.Ajax.postAjaxLoding("/config/user/add", requestData, function (res) {
				TckJS.Util.Alert("저장되었습니다.",function () { location.href= '/config/user'; });
	        })
		});
	},
	updateSumit : function() {
		TckJS.Util.Confirm("수정하시겠습니까?",
			function () {
				var requestData = {}
				var menu_codes = [];
				var frm_data = $("#frm").serializeArray();
				for (var index in frm_data) {
					if(frm_data[index].name == "menu_codes"){
						menu_codes.push(frm_data[index].value);
					}else {
						requestData[frm_data[index].name] = frm_data[index].value;
					}
		        }
				requestData["menu_codes"] = menu_codes.join(",");
				TckJS.Ajax.postAjaxLoding("/config/user/update", requestData, function (res) {
					TckJS.Util.Alert("수정되었습니다.",function () { location.href= '/config/user'; });
		        })
			});
	},
	deleteSumit : function() {
		TckJS.Util.Confirm("삭제하시겠습니까?",
		function () {
			var requestData = {}
			TckJS.Ajax.postAjaxLoding("/config/user/delete", { acct_no : $("#acct_no").val() }, function (res) {
				TckJS.Util.Alert("삭제되었습니다.",function () { location.href= '/config/user'; });
	        })
		});
	}
}
/* ]]> */
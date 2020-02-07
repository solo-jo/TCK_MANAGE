/*<![CDATA[*/ 
var AtclMemo = { 
	ModeilOpen : function() {
		$("#frm_memo").find("#dcl_com_seq").val("");
		$("#frm_memo").find("#dcl_cmnt").val("");
		$("#divAtclMemo").find("#insert").show();
		$("#divAtclMemo").find("#update").hide();
		$('#divAtclMemo').modal('show');
	},
	ModeilOpen_Update : function (dcl_com_seq) {
		var memo_tr = $("#memo_"+dcl_com_seq);
		$("#frm_memo").find("#dcl_com_seq").val(dcl_com_seq);
		$("#frm_memo").find("#dcl_cmnt").val(memo_tr.find("#dcl_cmnt").text());
		$("#divAtclMemo").find("#insert").hide();
		$("#divAtclMemo").find("#update").show();
		$('#divAtclMemo').modal('show');
	},
	Insert : function () {
		var requestData = {}
        var frm_data = $("#frm_memo").serializeArray();

        for (var index in frm_data) {
        	requestData[frm_data[index].name] = frm_data[index].value;
        }

        TckJS.Ajax.postAjaxLoding("/atcl/cmnt/insert", requestData, function (res) {
        	TckJS.Util.Alert("저장되었습니다.",function () { location.reload(); });
        })
	},
	Update : function () {
		TckJS.Util.Confirm("수정하시겠습니까?",
			function () {
				var requestData = {}
		        var frm_data = $("#frm_memo").serializeArray();
	
		        for (var index in frm_data) {
		        	requestData[frm_data[index].name] = frm_data[index].value;
		        }
	
	        TckJS.Ajax.postAjaxLoding("/atcl/cmnt/update", requestData, function (res) {
	        	TckJS.Util.Alert("수정되었습니다.",function () { location.reload(); });
	        })
		});
	},
	Delete : function (dcl_com_seq) {
		TckJS.Util.Confirm("삭제하시겠습니까?",
		function () {
	
	        TckJS.Ajax.postAjaxLoding("/atcl/cmnt/delete", {dcl_com_seq:dcl_com_seq}, function (res) {
	        	TckJS.Util.Alert("삭제되었습니다.",function () { location.reload(); });
	        })
		});
	}
}
/* ]]> */
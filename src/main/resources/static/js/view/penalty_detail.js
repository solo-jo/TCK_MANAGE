/*<![CDATA[*/
var PenaltyDetail = {
	PltModeilOpen : function(el) {
		
		if($(el).parent().parent()[0].nodeName != "TR") {
			TckJS.Util.Alert("오류가 발생했습니다.");
			return false;
		}
		
		var list_tr = $(el).parent().parent();
		$("#frm_plt_cncl").find("#plt_seq").val(list_tr.find("#plt_seq").text());
		$("#frm_plt_cncl").find("#plt_seq_text").text(list_tr.find("#plt_seq").text());
		$("#frm_plt_cncl").find("#plt_type_cd_text").text(list_tr.find("#plt_type_cd").text());
		$("#frm_plt_cncl").find("#cncl_rsn").val("");
		$('#divPenaltyCncl').modal('show');
	},
	PltModeilSumit : function() {
		var requestData = {}
        var frm_data = $("#frm_plt_cncl").serializeArray();

        for (var index in frm_data) {
        	requestData[frm_data[index].name] = frm_data[index].value;
        }

        TckJS.Ajax.postAjaxLoding("/plt/cancel", requestData, function (res) {
        	TckJS.Util.Alert("취소되었습니다.",function () { location.reload(); });
        })
	},
	WarnModeilOpen : function(el) {
		if($(el).parent().parent()[0].nodeName != "TR") {
			TckJS.Util.Alert("오류가 발생했습니다.");
			return false;
		}
		
		var list_tr = $(el).parent().parent();
		$("#frm_warn_cncl").find("#dcl_seq").val(list_tr.find("#dcl_seq").text());
		$("#frm_warn_cncl").find("#dcl_seq_text").text(list_tr.find("#dcl_seq").text());
		$("#frm_warn_cncl").find("#lst_trt_rslt_cd_text").text(list_tr.find("#lst_trt_rslt_cd").text());
		$("#frm_warn_cncl").find("#cncl_rsn").val("");
		$('#divWarningCncl').modal('show');
	},
	WarnModeilSumit : function() {
		var requestData = {}
        var frm_data = $("#frm_warn_cncl").serializeArray();

        for (var index in frm_data) {
        	requestData[frm_data[index].name] = frm_data[index].value;
        }

        TckJS.Ajax.postAjaxLoding("/plt/warn/cancel", requestData, function (res) {
        	TckJS.Util.Alert("취소되었습니다.",function () { location.reload(); });
        })
	}
	
}
/* ]]> */
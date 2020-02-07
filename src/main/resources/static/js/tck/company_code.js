/*<![CDATA[*/ 
var CompanyCodrUtil = {
	getCompanyCode : function (target,value) {
        TckJS.Ajax.getAjaxLoding("/common/company/list", {}, function (res) {
        	if(res.data){
        		if(res.data.result){
        			var _html_option = "";
        			var compnay_date = res.data.result;
        			for(var i = 0; i < compnay_date.length ; i++ ){
        				var selected = ""
            			if(value == compnay_date[i].id) selected = "selected";
        				_html_option += "<option "+selected+" value='"+compnay_date[i].id+"'>"+ compnay_date[i].name+"</option>";
        			}
        			
        			target.find("option").not("[value='']").remove();
        			target.append(_html_option);
        		}
        	}
        })
	}	
}
/* ]]> */
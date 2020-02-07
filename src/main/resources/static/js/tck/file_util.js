/*<![CDATA[*/ 
var FileUtil = {
	Upload : function (fileEl,PathTargetEl) {
		if(fileEl.files.length == 0 ) return false;
		
		var formData = new FormData();
		formData.append("file",fileEl.files[0]);
		
		TckJS.Ajax.postformAjaxLoding("/common/file/upload", formData, function (res) {
			PathTargetEl.val(res.upload_path);
        })
		
	},
	Down : function (filePath,fileName) {
		if($("#download").length == 1){
			$("#download").find("#filePath").val(filePath);
			$("#download").find("#fileName").val(fileName);
		}else {
			var form = document.createElement("form");
			form.id = "download";
			form.method = "post";
			form.target = "_blank";
			form.action = "/common/file/download";
			
			var filePath_input = document.createElement("input");
			filePath_input.id = "filePath";
			filePath_input.name = "filePath";
			filePath_input.value = filePath;
			form.append(filePath_input);
			
			var fileName_input = document.createElement("input");
			fileName_input.id = "fileName";
			fileName_input.name = "fileName";
			fileName_input.value = fileName;
			form.append(fileName_input);
			
			$("body").append(form);
		}
		
		$("#download").submit();
		
	}
	
}
/* ]]> */
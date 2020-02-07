/*<![CDATA[*/
var AddrCodrUtil = {
	init : function(target1, target2, target3, codeEl, levelEl) {
		target1.change(function () {
			codeEl.val(this.value);
			levelEl.val("1");
		})
		
		target2.change(function () {
			codeEl.val(this.value);
			levelEl.val("2");
		})
		
		target3.change(function () {
			codeEl.val(this.value);
			levelEl.val("3");
		})

	},
	getAddrCode : function(level, legalcode, target, resetEl_arr, value) {
		if (resetEl_arr) {
			for (var i = 0; i < resetEl_arr.length; i++) {
				resetEl_arr[i].find("option").not("[value='']").remove();
			}
		}

		var requestData = {
			level : level,
			legalcode : legalcode
		}
		TckJS.Ajax.getAjaxLoding("/common/addr/list", requestData,
				function(res) {
					if (res.data) {
						if (res.data.result) {
							var _html_option = "";
							var addr_date = res.data.result;
							for (var i = 0; i < addr_date.length; i++) {
								var selected = ""
								if (value == addr_date[i].code)
									selected = "selected";

								if (level == 1) {
									_html_option += "<option " + selected
											+ " value='" + addr_date[i].code
											+ "'>" + addr_date[i].addr1
											+ "</option>";
								} else if (level == 2) {
									_html_option += "<option " + selected
											+ " value='" + addr_date[i].code
											+ "'>" + addr_date[i].addr2
											+ "</option>";
								} else if (level == 3) {
									_html_option += "<option " + selected
											+ " value='" + addr_date[i].code
											+ "'>" + addr_date[i].addr3
											+ "</option>";
								}
							}

							target.find("option").not("[value='']").remove();
							target.append(_html_option);
						}
					}

				})
	},
	getValue : function(target1, target2, target3, levelEl) {
		var Value = "";
		if (target3.val() != "") {
			Value = target3.val();
			levelEl.val("3");
		} else if (target2.val() != "") {
			Value = target2.val();
			levelEl.val("2");
		} else if (target1.val() != "") {
			Value = target1.val();
			levelEl.val("1");
		}
		return Value;
	}
}
/* ]]> */
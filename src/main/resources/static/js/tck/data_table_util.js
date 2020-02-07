/*<![CDATA[*/ 
var DataTableUtil = {
	dataTable : null,
	init : function(target,url,serach,columns,order) {
		DataTableUtil.dataTable =  target.DataTable({
			"search": {"search": ""},
			"pagingType": "full_numbers",
			"ajax": {
			    "url": url,
			    dataSrc: '', 
			    "type": "GET",
			    "data": serach
		    },
            "columns": columns,
            order: order,
            ordering: true,
            serverSide: false
		});
		
		DataTableUtil.dataTable.on( 'init.dt', function () {
			//페이지 유지를 위한 작업
			if($.cookie('data_table_page') != undefined){
				DataTableUtil.dataTable.page(parseInt($.cookie('data_table_page'))).draw('page');
			}
		} );
		
	},
	Search : function () {
		DataTableUtil.dataTable.ajax.reload();
	},
	EnterSubmit : function (target) {
		target.keyup(function(){
			if(event.keyCode == 13) DataTableUtil.dataTable.ajax.reload();
		})
	}
}
/* ]]> */
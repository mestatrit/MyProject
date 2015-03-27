$(function(){
		 var grid_selector = "#grid-table";
			var pager_selector = "#grid-pager";
			var path=$("#root").val();
		
			var gs="";
			$.ajax({
				type : "POST",
				url :path+"/ac/queryCompany",
				async:false,
				contentType : 'application/x-www-form-urlencoded;charset=utf-8',
				success : function(data) {
					$.each(data,function(i,o) {   
						gs+=o.ID+":"+o.NAME+";";
					});
					if(gs.length>2){
						gs=gs.substring(0, gs.length-1);
					}
				}
			});
			
		jQuery("#grid-table").jqGrid({
			//direction: "rtl",
			url:path+"/tr/show",
			datatype: "json",
			height:$('#iframe', parent.document).height()-140,
			jsonReader : {
				id: "ID",
				root: "list",
				page: "pageNumber",
				total: "totalPage",
				records: "totalRow",
				repeatitems: false,
				},
			colNames:[' ','奖励主题','奖励内容'],
			colModel:[
				{name:'myac',index:'', width:80, fixed:true, sortable:false, resize:false,
					formatter:'actions', 
					formatoptions:{ 
						keys:true, 
						delOptions:{recreateForm: true, beforeShowForm:beforeDeleteCallback},
						//editformbutton:true, editOptions:{recreateForm: true, beforeShowForm:beforeEditCallback}
					}
				},
				{name:'TITLE',index:'TITLE', width:60, editable: true,editrules:{required:true}},
				{name:'CONTENT',index:'CONTENT', width:120, editable: true,edittype:"textarea",editoptions:{rows:"6",cols:"20"},editrules:{required:true}},
				/* {name:'name',index:'name', width:150,editable: true,editoptions:{size:"20",maxlength:"30"}},
				{name:'stock',index:'stock', width:70, editable: true,edittype:"checkbox",editoptions: {value:"Yes:No"},unformat: aceSwitch},
				{name:'ship',index:'ship', width:90, editable: true,edittype:"select",editoptions:{value:"FE:FedEx;IN:InTime;TN:TNT;AR:ARAMEX"}},
				{name:'note',index:'note', width:150, sortable:false,editable: true,edittype:"textarea", editoptions:{rows:"2",cols:"10"}}  */
			], 
			viewrecords : true,
			rowNum:10,
			rowList:[10,20,30],
			pager : pager_selector,
			altRows: true,
			//toppager: true,
			
			multiselect: true,
			//multikey: "ctrlKey",
	        multiboxonly: true,

			loadComplete : function() {
				var table = this;
				setTimeout(function(){
					styleCheckbox(table);
					updateActionIcons(table);
					updatePagerIcons(table);
					enableTooltips(table);
				}, 0);
			},
			editurl: path+"/tr/col",//nothing is saved 
			caption: "奖励措施",
			autowidth: true,
		});
	  
		jQuery(grid_selector).jqGrid('navGrid',pager_selector,
				{ 	//navbar options
					edit: true,
					editicon : 'icon-pencil blue',
					edittext:'编辑',
					add: true,
					addicon : 'icon-plus-sign purple',
					addtext:'添加',
					del: true,
					delicon : 'icon-trash red',
					deltext:'删除',
					search: false,
					searchicon : 'icon-search orange', 
					refresh: true,
					refreshicon : 'icon-refresh green',
					refreshtext:'刷新',
					view: false,
					viewicon : 'icon-zoom-in grey',
					viewtext:'查看',
				},
				{
					//edit record form
					closeAfterEdit: true,
					recreateForm: true,
					beforeShowForm : function(e) {
						var form = $(e[0]);
						form.closest('.ui-jqdialog').find('.ui-jqdialog-titlebar').wrapInner('<div class="widget-header" />')
						style_edit_form(form);
					}
				},
				{
					//new record form
					closeAfterAdd: true,
					recreateForm: true,
					viewPagerButtons: false,
					beforeShowForm : function(e) {
						var form = $(e[0]);
						form.closest('.ui-jqdialog').find('.ui-jqdialog-titlebar').wrapInner('<div class="widget-header" />')
						style_edit_form(form);
					},
				},
				{
					//delete record form
					recreateForm: true,
					beforeShowForm : function(e) {
						var form = $(e[0]);
						if(form.data('styled')) return false;
						
						form.closest('.ui-jqdialog').find('.ui-jqdialog-titlebar').wrapInner('<div class="widget-header" />')
						style_delete_form(form);
						
						form.data('styled', true);
					},
					onClick : function(e) {
						alert(1);
					}
				},

				{
					//view record form
					recreateForm: true,
					beforeShowForm: function(e){
						var form = $(e[0]);
						form.closest('.ui-jqdialog').find('.ui-jqdialog-title').wrap('<div class="widget-header" />')
					}
				}
			);
}); 

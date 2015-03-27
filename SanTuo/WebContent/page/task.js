$(function(){
		 var grid_selector = "#grid-table";
			var pager_selector = "#grid-pager";
			var path=$("#root").val();
			
		jQuery("#grid-table").jqGrid({
			//direction: "rtl",
			url:path+"/task/show",
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
			colNames:[' ','任务名称','派单量','留电量','代访量','开始时间','结束时间','任务地点',''],
			colModel:[
				{name:'myac',index:'', width:80, fixed:true, sortable:false, resize:false,
					formatter:'actions', 
					formatoptions:{ 
						keys:true, 
						delOptions:{recreateForm: true, beforeShowForm:beforeDeleteCallback},
						//editformbutton:true, editOptions:{recreateForm: true, beforeShowForm:beforeEditCallback}
					}
				},
				{name:'NAME',index:'NAME', width:60, editable: true,editrules:{required:true}},
				{name:'PAIDAN',index:'PAIDAN',width:60, editable: true,hidden:true,editrules:{edithidden:true,number:true}},
				{name:'LIUDIAN',index:'LIUDIAN', width:60, editable: true,hidden:true,editrules:{edithidden:true,number:true}},
				{name:'DAIFANG',index:'DAIFANG', width:60, editable: true,hidden:true,editrules:{edithidden:true,number:true}},
				{name:'STARTTIME',index:'STARTTIME',width:90, editable:true,unformat: pickDate},
				{name:'ENDTIME',index:'ENDTIME',width:90, editable:true,unformat: pickDate},
				{name:'ADDRESS',index:'ADDRESS', width:120, editable: true},
				{name:'ID',index:'ID',width:20,formatter: function(cellvalue, options, cell){
					return "<button class='btn btn-default' onclick='fasong(\""+cellvalue+"\")'>发送</button>";
				}},
				/* {name:'name',index:'name', width:150,editable: true,editoptions:{size:"20",maxlength:"30"}},
				 hidden:true,editrules:{edithidden:true}
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
			editurl: path+"/task/col",//nothing is saved 
			caption: "任务管理",
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
					view: true,
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

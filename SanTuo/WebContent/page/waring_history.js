$(function(){
		 var grid_selector = "#grid-table";
			var pager_selector = "#grid-pager";
			var path=$("#root").val();
		jQuery("#grid-table").jqGrid({
			//direction: "rtl",
			url:path+"/warn/show",
			datatype: "json",
			height:$('#iframe', parent.document).height()-140,
			jsonReader : {
				id:"ID",
				root: "list",
				page: "pageNumber",
				total: "totalPage",
				records: "totalRow",
				repeatitems: false,
				},
			colNames:[' ', '报警人名称','报警地址','报警时间','报警状态'],
			colModel:[
				{name:'myac',index:'', width:80, fixed:true, sortable:false, resize:false,
					formatter:'actions', 
					formatoptions:{ 
						keys:true, 
						delOptions:{recreateForm: true, beforeShowForm:beforeDeleteCallback},
						//editformbutton:true, editOptions:{recreateForm: true, beforeShowForm:beforeEditCallback}
					}
				},
				{name:'USERNAME',index:'USERNAME', width:60,},
				{name:'ADDRESS',index:'ADDRESS',width:120, },
				{name:'CREATETIME',index:'CREATETIME',width:90, },
				{name:'STATUS',index:'STATUS',width:80, editable:true,editrules:{required:true},editoptions:{value:'0:未读;1:已读'},edittype:"select",formatter:'select'}
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
			editurl: path+"/warn/col",//nothing is saved 
			caption: "报警历史记录",
			autowidth: true,
		});
	  
		jQuery(grid_selector).jqGrid('navGrid',pager_selector,
				{ 	//navbar options
					edit: true,
					editicon : 'icon-pencil blue',
					edittext:'审阅',
					add: false,
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
			)
			
			function rFormat(){
			return "s";
		}
		
		function diyu( cellvalue, options, cell ) {
			setTimeout(function(){
				$(cell).find('input[type=text]').attr("readonly",true);
				$(cell).find('input[type=text]').click(function(){
					$("#myModal").modal('show');
				});
			}, 0);
		}
		
		function timecheck(){
			var b=true;
			if($("#ENDTIME").val()!=""){
				if($("#STARTTIME").val()==""){
					alert("请填写开始时间");
					b=false;
				}
			}else if(strToTime($("#ENDTIME").val())<strToTime($("#STARTTIME").val())){
				alert("结束时间请大于开始时间");
				b=false;
			}
			return b;
		}
		
	/*  $.each($("#ulList").find("a"),function(i,o) { 
			$(o).click(function(){
				$.each($("#ulList").find("li"),function(e,f) { 
					$(this).removeClass("active");
				});
				$(this).parent("li").addClass("active");
			});
		});  */
	
}); 

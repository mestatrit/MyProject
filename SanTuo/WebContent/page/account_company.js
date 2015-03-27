$(function(){
		 var grid_selector = "#grid-table";
			var pager_selector = "#grid-pager";
			var path=$("#root").val();
		jQuery("#grid-table").jqGrid({
			//direction: "rtl",
			url:path+"/ac/show",
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
			colNames:[ '公司名称','小蜜蜂数量','地图区域','开始时间','结束时间','创建时间'],
			colModel:[
				{name:'NAME',index:'NAME', width:60, editable: true,editrules:{required:true}},
				{name:'XMFNUMBER',index:'XMFNUMBER',width:40, editable:true,editrules:{number:true,required:true}},
				{name:'AREA',index:'AREA',width:150, editable:true,unformat: diyu},
				{name:'STARTTIME',index:'STARTTIME',width:90, editable:true,unformat: pickDate},
				{name:'ENDTIME',index:'ENDTIME',width:90, editable:true,unformat: pickDate,editrules:{custom:true, custom_func:function(val,col){
//					if(val!=""){
//						if($("#STARTTIME").val()==""){
//							return [false,'请填写开始时间'];
//						}
//					}else if(strToTime($("#ENDTIME").val())<strToTime($("#STARTTIME").val())){
//						return [false,'结束时间请大于开始时间'];
//					}
					return [true,""];
				}}},
				{name:'CREATETIME',index:'CREATETIME',width:90},
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
			editurl: path+"/ac/col",//nothing is saved 
			caption: "公司管理",
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
					del: false,
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
						$("#AREA").attr("readonly",true);
						$("#AREA").click(function (){
							$("#myModal").modal('show');
						});
						area_setChecked($("#AREA").val());
					},
					beforeSubmit:function(){
						return jqCheckTime();
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
						$("#AREA").attr("readonly",true);
						$("#AREA").click(function (){
							$("#myModal").modal('show');
						});
					},
					beforeSubmit:function(){
						return jqCheckTime();
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
			
		
		function diyu( cellvalue, options, cell ) {
			setTimeout(function(){
			    $("#diyuTable").find("input[type='checkbox']").prop("checked",false);
				area_setChecked(cellvalue);
				$("#area_id").val($(cell).find('input[type=text]').attr("id"));
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

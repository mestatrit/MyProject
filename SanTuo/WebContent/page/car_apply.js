$(function(){
		 var grid_selector = "#grid-table";
			var pager_selector = "#grid-pager";
			var path=$("#root").val();
		jQuery("#grid-table").jqGrid({
			//direction: "rtl",
			url:path+"/carapply/show",
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
			colNames:['用户名称','客户名称','客户电话','用车地址','随行人数','备注','申请时间','审核状态'],
			colModel:[
				{name:'UNAME',index:'UNAME', width:60, editrules:{required:true}},
				{name:'CUSTOMERNAME',index:'CUSTOMERNAME',width:90, editrules:{required:true}},
				{name:'CUSTOMERPHONE',index:'CUSTOMERPHONE',width:120, editrules:{required:true}},
				{name:'CUSTOMERADDRESS',index:'CUSTOMERADDRESS',width:90, editrules:{required:true}},
				{name:'PEOPLENUMBER',index:'PEOPLENUMBER',width:90, editrules:{required:true}},
				{name:'REMARK',index:'REMARK',width:90, editrules:{required:true}},
				{name:'CREATETIME',index:'CREATETIME',width:90,editrules:{required:true},unformat: pickDate}, 
				{name:'STATUS',index:'STATUS',width:90,editable:true,editoptions:{value:'0:未审核;1:通过;2:驳回'},edittype:"select",formatter:'select',}
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
			editurl: path+"/carapply/col",//nothing is saved 
			caption: "用车申请",
			autowidth: true,
		});
	  
		jQuery(grid_selector).jqGrid('navGrid',pager_selector,
				{ 	//navbar options
					edit: true,
					editicon : 'icon-pencil blue',
					edittext:'审核',
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
						form.closest('.ui-jqdialog').find('.ui-jqdialog-title').wrap('<div class="widget-header" />');
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

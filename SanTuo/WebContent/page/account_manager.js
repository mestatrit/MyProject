$(function(){
		 var grid_selector = "#grid-table";
			var pager_selector = "#grid-pager";
			var path=$("#root").val();
			var zhu="";
			var juese="";
			$.ajax({
						type : "POST",
						url :path+"/au/zhu",
						async:false,
						contentType : 'application/x-www-form-urlencoded;charset=utf-8',
						success : function(data) {
							$.each(data,function(i,o) {   
								zhu+=o.ID+":"+o.NAME+";";
							});
							if(zhu.length>2){
								zhu=zhu.substring(0, zhu.length-1);
							}
						}
					});
			$.ajax({
				type : "POST",
				url :path+"/au/juese",
				async:false,
				contentType : 'application/x-www-form-urlencoded;charset=utf-8',
				success : function(data) {
					console.log(data);
					$.each(data,function(i,o) {   
						juese+=o.ID+":"+o.NAME+";";
					});
					if(juese.length>2){
						juese=juese.substring(0, juese.length-1);
					}
				}
			});
		jQuery("#grid-table").jqGrid({
			//direction: "rtl",
			url:path+"/au/show",
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
			colNames:['用户名','用户登录名','密码','电话','角色','所属组','创建时间'],
			colModel:[
				
				{name:'UNAME',index:'UNAME', width:60, editable: true,editrules:{required:true}},
				{name:'LNAME',index:'LNAME',width:90,editable: true,editoptions:{size:"20",maxlength:"30"},editrules:{required:true}},
				{name:'PWD',index:'PWD',width:90, editable:true,editrules:{required:true}},
				{name:'PHONE',index:'PHONE',width:90, editable:true,editrules:{number:true}},
				{name:'RID',index:'RID',width:90, editable:true,edittype:"select",editoptions:{value:juese},formatter:'select',},
				{name:'GID',index:'GID',width:90, editable:true,edittype:"select",editoptions:{value:zhu},formatter:'select',},
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
			editurl: path+"/au/col",//nothing is saved 
			caption: "账号管理",
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
						$("#LNAME").attr("readonly",true);
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
					beforeSubmit:function(e,id){
						var s=true;
						$.ajax({
							type : "POST",
							url :$("#root").val()+"/au/isExist",
							async:false, 
							data :{lname:$("#LNAME").val()},
							contentType : 'application/x-www-form-urlencoded;charset=utf-8',
							success : function(data) {
								s=!data;
							}
						});
						if(!s){
							return [s,'登录名称重复']; 
						}
						$.ajax({
							type : "POST",
							url :$("#root").val()+"/au/isExistU",
							async:false, 
							data :{uname:$("#UNAME").val()},
							contentType : 'application/x-www-form-urlencoded;charset=utf-8',
							success : function(data) {
								s=!data;
							}
						});
						return [s,'用户名称重复']; 
					}
				},
				{
					//delete record form
					recreateForm: true,
					beforeShowForm : function(e) {
						var form = $(e[0]);
						if(form.data('styled')) return false;
						$("#LNAME").attr("readonly",true);
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
			).jqGrid('navButtonAdd',pager_selector,{
				caption:"随机添加小蜜蜂账号",
				buttonicon:"icon-plus-sign blue",
				onClickButton: function(){
					$("#myModal").modal("show");
				}
			});
	/*  $.each($("#ulList").find("a"),function(i,o) { 
			$(o).click(function(){
				$.each($("#ulList").find("li"),function(e,f) { 
					$(this).removeClass("active");
				});
				$(this).parent("li").addClass("active");
			});
		});  */
	
}); 

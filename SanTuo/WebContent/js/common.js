/**
 * 
 * @param name
 * @returns url参数值
 */
 function getUrlParam(name) {
         var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)"); //构造一个含有目标参数的正则表达式对象
         var r = window.location.search.substr(1).match(reg);  //匹配目标参数
         if (r != null) return unescape(r[2]); return null; //返回参数值
     }

function windowClose(){
	window.close();
}

function goUrl(url){
	location.href=encodeURI(encodeURI(url));
}
function fanhui(){
	history.go(-1);
}


function goifm(url){
	$("#iframe").attr("src",url);
}

function getval(id){
	return $("#"+id).val();
}

function checkd(id){  
	$("#che"+id).prop("checked", true);
}
function cheRadio(id){ 
	$("#che"+id).attr("checked", true);
	$("input[id^=cge]").attr("checked", false);
	
}
function addInfos(id,msg){
	("#"+id).addClass("has-error");
	$("#"+id).append('<label name="infos" class="control-label" >'+msg+'</label>');
}
function removeInfos(){
	$("label[name='infos']").remove();
}
function strToTime(time){
	return time.replace("-","").replace("-","").replace(" ","").replace(":","").replace(":","")*1;
}





//enable search/filter toolbar
//jQuery(grid_selector).jqGrid('filterToolbar',{defaultSearch:true,stringResult:true})

//switch element when editing inline
function jqCheckTime(){
	var s=$("#STARTTIME").val();
	var n=$("#ENDTIME").val();
	var b=true;
	var msg="";
	if(n!=""&&s!=""){
		console.log(1);
		if(strToTime(n)<strToTime(s)){
			console.log(2);
			b=false,msg="结束时间请大于开始时间";
		}
	}else if(n!=""&&s==""){
		console.log(3);
		b=false,msg="请填写开始时间";
	}
	else if(n==""&&s!=""){
		console.log(4);
		b=false,msg="请填写结束时间";
	}
	console.log(5);
	return [b,msg];
}

function compareTime(s,e){
	s.repal
}

function aceSwitch( cellvalue, options, cell ) {
	setTimeout(function(){
		$(cell) .find('input[type=checkbox]')
				.wrap('<label class="inline" />')
			.addClass('ace ace-switch ace-switch-5')
			.after('<span class="lbl"></span>');
	}, 0);
}
//enable datepicker
function pickDate( cellvalue, options, cell ) {
	setTimeout(function(){
		$(cell).find('input[type=text]').datepicker({format:'yyyy-mm-dd' ,multidate: false,
	        autoclose: true,
	        todayHighlight: true}); 
	}, 0);
}



function style_edit_form(form) {
	//enable datepicker on "sdate" field and switches for "stock" field
	form.find('input[name*=TIME]').datepicker({format:'yyyy-mm-dd' ,multidate: false,
        autoclose: true, todayHighlight: true});
        //radio样式
		/*.end().find('input[name=stock]')
			  .addClass('ace ace-switch ace-switch-5').wrap('<label class="inline" />').after('<span class="lbl"></span>');
*/
	//update buttons classes
	var buttons = form.next().find('.EditButton .fm-button');
	buttons.addClass('btn btn-sm').find('[class*="-icon"]').remove();//ui-icon, s-icon
	buttons.eq(0).addClass('btn-primary').prepend('<i class="icon-ok"></i>');
	buttons.eq(1).addClass('btn-info').prepend('<i class="icon-remove"></i>')
	
	buttons = form.next().find('.navButton a');
	buttons.find('.ui-icon').remove();
	buttons.eq(0).append('<i class="icon-chevron-left"></i>');
	buttons.eq(1).append('<i class="icon-chevron-right"></i>');		
}

function style_delete_form(form) {
	var buttons = form.next().find('.EditButton .fm-button');
	buttons.addClass('btn btn-sm').find('[class*="-icon"]').remove();//ui-icon, s-icon
	buttons.eq(0).addClass('btn-danger').prepend('<i class="icon-trash"></i>');
	buttons.eq(1).addClass('btn-info').prepend('<i class="icon-remove"></i>')
}

function style_search_filters(form) {
	form.find('.delete-rule').val('X');
	form.find('.add-rule').addClass('btn btn-xs btn-primary');
	form.find('.add-group').addClass('btn btn-xs btn-success');
	form.find('.delete-group').addClass('btn btn-xs btn-danger');
}
function style_search_form(form) {
	var dialog = form.closest('.ui-jqdialog');
	var buttons = dialog.find('.EditTable')
	buttons.find('.EditButton a[id*="_reset"]').addClass('btn btn-sm btn-info').find('.ui-icon').attr('class', 'icon-retweet');
	buttons.find('.EditButton a[id*="_query"]').addClass('btn btn-sm btn-inverse').find('.ui-icon').attr('class', 'icon-comment-alt');
	buttons.find('.EditButton a[id*="_search"]').addClass('btn btn-sm btn-purple').find('.ui-icon').attr('class', 'icon-search');
}

function beforeDeleteCallback(e) {
	var form = $(e[0]);
	if(form.data('styled')) return false;
	
	form.closest('.ui-jqdialog').find('.ui-jqdialog-titlebar').wrapInner('<div class="widget-header" />')
	style_delete_form(form);
	
	form.data('styled', true);
}

function beforeEditCallback(e) {
	
	var form = $(e[0]);
	form.closest('.ui-jqdialog').find('.ui-jqdialog-titlebar').wrapInner('<div class="widget-header" />')
	style_edit_form(form);
}



//it causes some flicker when reloading or navigating grid
//it may be possible to have some custom formatter to do this as the grid is being created to prevent this
//or go back to default browser checkbox styles for the grid
function styleCheckbox(table) {

	$(table).find('input:checkbox').addClass('ace')
	.wrap('<label />')
	.after('<span class="lbl align-top" />')


	$('.ui-jqgrid-labels th[id*="_cb"]:first-child')
	.find('input.cbox[type=checkbox]').addClass('ace')
	.wrap('<label />').after('<span class="lbl align-top" />');

}


//unlike navButtons icons, action icons in rows seem to be hard-coded
//you can change them like this in here if you want
function updateActionIcons(table) {
	
	var replacement = 
	{
		'ui-icon-pencil' : 'icon-pencil blue',
		'ui-icon-trash' : 'icon-trash red',
		'ui-icon-disk' : 'icon-ok green',
		'ui-icon-cancel' : 'icon-remove red'
	};
	$(table).find('.ui-pg-div span.ui-icon').each(function(){
		var icon = $(this);
		var $class = $.trim(icon.attr('class').replace('ui-icon', ''));
		if($class in replacement) icon.attr('class', 'ui-icon '+replacement[$class]);
	})
	
}

//replace icons with FontAwesome icons like above
function updatePagerIcons(table) {
	var replacement = 
	{
		'ui-icon-seek-first' : 'icon-double-angle-left bigger-140',
		'ui-icon-seek-prev' : 'icon-angle-left bigger-140',
		'ui-icon-seek-next' : 'icon-angle-right bigger-140',
		'ui-icon-seek-end' : 'icon-double-angle-right bigger-140'
	};
	$('.ui-pg-table:not(.navtable) > tbody > tr > .ui-pg-button > .ui-icon').each(function(){
		var icon = $(this);
		var $class = $.trim(icon.attr('class').replace('ui-icon', ''));
		
		if($class in replacement) icon.attr('class', 'ui-icon '+replacement[$class]);
	})
}

function enableTooltips(table) {
	$('.navtable .ui-pg-button').tooltip({container:'body'});
	$(table).find('.ui-pg-div').tooltip({container:'body'});
}

//var selr = jQuery(grid_selector).jqGrid('getGridParam','selrow');

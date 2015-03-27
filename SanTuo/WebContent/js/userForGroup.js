	function queryDY(url){
		$.ajax({
					type : "POST",
					url : url,
					async:false,
					data :{},
					contentType : 'application/x-www-form-urlencoded;charset=utf-8',
					success : function(data) {
						var html="";
						$.each(data,function(i,o) { 
							html+='<tr style="margin:10px">'+'<td width="120px;"> <input  onclick="area_checkCity(\''+i+'\')" type="checkbox" value="'+o.group+'" id="areacity'+i+'" >'+o.group+'</td><td>';
							$.each(o.user,function(e,d) {   
								html+='<div style="margin-left: 20px;float:left;"><input style="padding-left: 5px;"  onclick="area_checkDis(this,\''+i+'\')" name="areadis'+i+'" id="areaddis'+d.ID+'" value="'+d.ID+'" type="checkbox">'+d.UNAME+'</div>';
							});
							html+='</td></tr><tr><td height="10px"> </td><td> </td></tr>';
								
						});
						$("#usertable").html(html);
					}
				});
	}
	
	
	function area_setChecked(data){
		$.each($("input[type=checkbox]"),function(i,o) {   
			$.each(data.split(","),function(e,f) {   
			if($(o).val()==f){
				$(o).prop("checked",true);
			}
			});
		});
	}
	
	function area_checkCity(id){
		if($("#areacity"+id).is(":checked")){
			$.each($("input[name=areadis"+id+"]"),function(i,o) {  
				if(!$(o).attr("disabled"))$(o).prop("checked",true);
			});
			
		}else{
			$.each($("input[name=areadis"+id+"]"),function(i,o) {  
				$(o).prop("checked",false);
			});
		}
	}
	function area_checkDis(o,id){
		if($(o).is(":checked")){
			var b=true;
			$.each($("input[name=areadis"+id+"]"),function(i,l) {  
				if(!$(l).is(":checked")){
					b=false;
				}
			});
			$("#areacity"+id).prop("checked",b);
		}else{
			$("#areacity"+id).prop("checked",false);
		}
	}
	
	function area_getCity(){
		var data="";
				$.each($("input[name^=areadis]"),function(e,f) {   
					if($(f).is(":checked")){
						data+=$(f).val()+",";
					}
				});
		if(data.length>0){
			data=data.substring(0, data.length-1);
		}
		return data;
	}
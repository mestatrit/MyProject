	
	function queryDY(url,id){
		$.ajax({
					type : "POST",
					url : url,
					async:false,
					data :{},
					contentType : 'application/x-www-form-urlencoded;charset=utf-8',
					success : function(data) {
						var html="";
						$.each(data,function(i,o) { 
							html+='<tr style="margin:10px">'+'<td width="120px;"> <input  onclick="area_checkCity(\''+i+'\')" type="checkbox" value="'+o.city+'" id="areacity'+i+'" >'+o.city+'</td><td>';
							$.each(o.dis,function(e,d) {   
								html+='<div style="margin-left: 20px;float:left;"><input style="padding-left: 5px;"  onclick="area_checkDis(this,\''+i+'\')" name="areadis'+i+'" value="'+d+'" type="checkbox">'+d+'</div>';
							});
							html+='</td></tr><tr><td height="10px"> </td><td> </td></tr>';
								
						});
						$("#diyuTable").html(html);
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
				$(o).prop("checked",true);
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
		$.each($("input[id^=areacity]"),function(i,o) {   
			if($(o).is(":checked")){
				data+=$(o).val()+",";
			}else{
				$.each($("input[name=areadis"+i+"]"),function(e,f) {   
					if($(f).is(":checked")){
						data+=$(f).val()+",";
					}
				});
			}
		});
		if(data.length>0){
			data=data.substring(0, data.length-1);
		}
		return data;
	}
var list = new Array();
	function getTreeData(id, pId, name,iconOpen,iconClose) {
		this.id = id;
		this.pId = pId;
		this.name = name;
		this.iconOpen=iconOpen;
		this.iconClose=iconClose;
	}
	var setting = {
			check: {
				enable: true,
				chkboxType:{ "Y" : "ps", "N" : "ps" }
			},
			data: {
				simpleData: {
					enable: true,
				}
			}
};
$.ajax({
		type : "POST",
		url :getval('root')+"/au/queryUserForTree",
		async:false,
		contentType : 'application/x-www-form-urlencoded;charset=utf-8',
		success : function(data) {
				var list = new Array();
			$.each(data,function(i,o) {
				list.push(new getTreeData(o.ID,o.PID,o.NAME));
			});
			$.fn.zTree.init($("#treeDemo"), setting,list);
		}
	});
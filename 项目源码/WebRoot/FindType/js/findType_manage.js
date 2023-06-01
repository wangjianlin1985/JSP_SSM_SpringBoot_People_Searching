var findType_manage_tool = null; 
$(function () { 
	initFindTypeManageTool(); //建立FindType管理对象
	findType_manage_tool.init(); //如果需要通过下拉框查询，首先初始化下拉框的值
	$("#findType_manage").datagrid({
		url : 'FindType/list',
		fit : true,
		fitColumns : true,
		striped : true,
		rownumbers : true,
		border : false,
		pagination : true,
		pageSize : 5,
		pageList : [5, 10, 15, 20, 25],
		pageNumber : 1,
		sortName : "findTypeId",
		sortOrder : "desc",
		toolbar : "#findType_manage_tool",
		columns : [[
			{
				field : "findTypeId",
				title : "寻找类型id",
				width : 70,
			},
			{
				field : "findTypeName",
				title : "寻找类型名称",
				width : 140,
			},
		]],
	});

	$("#findTypeEditDiv").dialog({
		title : "修改管理",
		top: "50px",
		width : 700,
		height : 515,
		modal : true,
		closed : true,
		iconCls : "icon-edit-new",
		buttons : [{
			text : "提交",
			iconCls : "icon-edit-new",
			handler : function () {
				if ($("#findTypeEditForm").form("validate")) {
					//验证表单 
					if(!$("#findTypeEditForm").form("validate")) {
						$.messager.alert("错误提示","你输入的信息还有错误！","warning");
					} else {
						$("#findTypeEditForm").form({
						    url:"FindType/" + $("#findType_findTypeId_edit").val() + "/update",
						    onSubmit: function(){
								if($("#findTypeEditForm").form("validate"))  {
				                	$.messager.progress({
										text : "正在提交数据中...",
									});
				                	return true;
				                } else { 
				                    return false; 
				                }
						    },
						    success:function(data){
						    	$.messager.progress("close");
						    	console.log(data);
			                	var obj = jQuery.parseJSON(data);
			                    if(obj.success){
			                        $.messager.alert("消息","信息修改成功！");
			                        $("#findTypeEditDiv").dialog("close");
			                        findType_manage_tool.reload();
			                    }else{
			                        $.messager.alert("消息",obj.message);
			                    } 
						    }
						});
						//提交表单
						$("#findTypeEditForm").submit();
					}
				}
			},
		},{
			text : "取消",
			iconCls : "icon-redo",
			handler : function () {
				$("#findTypeEditDiv").dialog("close");
				$("#findTypeEditForm").form("reset"); 
			},
		}],
	});
});

function initFindTypeManageTool() {
	findType_manage_tool = {
		init: function() {
		},
		reload : function () {
			$("#findType_manage").datagrid("reload");
		},
		redo : function () {
			$("#findType_manage").datagrid("unselectAll");
		},
		search: function() {
			$("#findType_manage").datagrid("load");
		},
		exportExcel: function() {
			$("#findTypeQueryForm").form({
			    url:"FindType/OutToExcel",
			});
			//提交表单
			$("#findTypeQueryForm").submit();
		},
		remove : function () {
			var rows = $("#findType_manage").datagrid("getSelections");
			if (rows.length > 0) {
				$.messager.confirm("确定操作", "您正在要删除所选的记录吗？", function (flag) {
					if (flag) {
						var findTypeIds = [];
						for (var i = 0; i < rows.length; i ++) {
							findTypeIds.push(rows[i].findTypeId);
						}
						$.ajax({
							type : "POST",
							url : "FindType/deletes",
							data : {
								findTypeIds : findTypeIds.join(","),
							},
							beforeSend : function () {
								$("#findType_manage").datagrid("loading");
							},
							success : function (data) {
								if (data.success) {
									$("#findType_manage").datagrid("loaded");
									$("#findType_manage").datagrid("load");
									$("#findType_manage").datagrid("unselectAll");
									$.messager.show({
										title : "提示",
										msg : data.message
									});
								} else {
									$("#findType_manage").datagrid("loaded");
									$("#findType_manage").datagrid("load");
									$("#findType_manage").datagrid("unselectAll");
									$.messager.alert("消息",data.message);
								}
							},
						});
					}
				});
			} else {
				$.messager.alert("提示", "请选择要删除的记录！", "info");
			}
		},
		edit : function () {
			var rows = $("#findType_manage").datagrid("getSelections");
			if (rows.length > 1) {
				$.messager.alert("警告操作！", "编辑记录只能选定一条数据！", "warning");
			} else if (rows.length == 1) {
				$.ajax({
					url : "FindType/" + rows[0].findTypeId +  "/update",
					type : "get",
					data : {
						//findTypeId : rows[0].findTypeId,
					},
					beforeSend : function () {
						$.messager.progress({
							text : "正在获取中...",
						});
					},
					success : function (findType, response, status) {
						$.messager.progress("close");
						if (findType) { 
							$("#findTypeEditDiv").dialog("open");
							$("#findType_findTypeId_edit").val(findType.findTypeId);
							$("#findType_findTypeId_edit").validatebox({
								required : true,
								missingMessage : "请输入寻找类型id",
								editable: false
							});
							$("#findType_findTypeName_edit").val(findType.findTypeName);
							$("#findType_findTypeName_edit").validatebox({
								required : true,
								missingMessage : "请输入寻找类型名称",
							});
						} else {
							$.messager.alert("获取失败！", "未知错误导致失败，请重试！", "warning");
						}
					}
				});
			} else if (rows.length == 0) {
				$.messager.alert("警告操作！", "编辑记录至少选定一条数据！", "warning");
			}
		},
	};
}

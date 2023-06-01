var lostType_manage_tool = null; 
$(function () { 
	initLostTypeManageTool(); //建立LostType管理对象
	lostType_manage_tool.init(); //如果需要通过下拉框查询，首先初始化下拉框的值
	$("#lostType_manage").datagrid({
		url : 'LostType/list',
		fit : true,
		fitColumns : true,
		striped : true,
		rownumbers : true,
		border : false,
		pagination : true,
		pageSize : 5,
		pageList : [5, 10, 15, 20, 25],
		pageNumber : 1,
		sortName : "lostTypeId",
		sortOrder : "desc",
		toolbar : "#lostType_manage_tool",
		columns : [[
			{
				field : "lostTypeId",
				title : "丢失类型id",
				width : 70,
			},
			{
				field : "lostTypeName",
				title : "丢失类型名称",
				width : 140,
			},
		]],
	});

	$("#lostTypeEditDiv").dialog({
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
				if ($("#lostTypeEditForm").form("validate")) {
					//验证表单 
					if(!$("#lostTypeEditForm").form("validate")) {
						$.messager.alert("错误提示","你输入的信息还有错误！","warning");
					} else {
						$("#lostTypeEditForm").form({
						    url:"LostType/" + $("#lostType_lostTypeId_edit").val() + "/update",
						    onSubmit: function(){
								if($("#lostTypeEditForm").form("validate"))  {
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
			                        $("#lostTypeEditDiv").dialog("close");
			                        lostType_manage_tool.reload();
			                    }else{
			                        $.messager.alert("消息",obj.message);
			                    } 
						    }
						});
						//提交表单
						$("#lostTypeEditForm").submit();
					}
				}
			},
		},{
			text : "取消",
			iconCls : "icon-redo",
			handler : function () {
				$("#lostTypeEditDiv").dialog("close");
				$("#lostTypeEditForm").form("reset"); 
			},
		}],
	});
});

function initLostTypeManageTool() {
	lostType_manage_tool = {
		init: function() {
		},
		reload : function () {
			$("#lostType_manage").datagrid("reload");
		},
		redo : function () {
			$("#lostType_manage").datagrid("unselectAll");
		},
		search: function() {
			$("#lostType_manage").datagrid("load");
		},
		exportExcel: function() {
			$("#lostTypeQueryForm").form({
			    url:"LostType/OutToExcel",
			});
			//提交表单
			$("#lostTypeQueryForm").submit();
		},
		remove : function () {
			var rows = $("#lostType_manage").datagrid("getSelections");
			if (rows.length > 0) {
				$.messager.confirm("确定操作", "您正在要删除所选的记录吗？", function (flag) {
					if (flag) {
						var lostTypeIds = [];
						for (var i = 0; i < rows.length; i ++) {
							lostTypeIds.push(rows[i].lostTypeId);
						}
						$.ajax({
							type : "POST",
							url : "LostType/deletes",
							data : {
								lostTypeIds : lostTypeIds.join(","),
							},
							beforeSend : function () {
								$("#lostType_manage").datagrid("loading");
							},
							success : function (data) {
								if (data.success) {
									$("#lostType_manage").datagrid("loaded");
									$("#lostType_manage").datagrid("load");
									$("#lostType_manage").datagrid("unselectAll");
									$.messager.show({
										title : "提示",
										msg : data.message
									});
								} else {
									$("#lostType_manage").datagrid("loaded");
									$("#lostType_manage").datagrid("load");
									$("#lostType_manage").datagrid("unselectAll");
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
			var rows = $("#lostType_manage").datagrid("getSelections");
			if (rows.length > 1) {
				$.messager.alert("警告操作！", "编辑记录只能选定一条数据！", "warning");
			} else if (rows.length == 1) {
				$.ajax({
					url : "LostType/" + rows[0].lostTypeId +  "/update",
					type : "get",
					data : {
						//lostTypeId : rows[0].lostTypeId,
					},
					beforeSend : function () {
						$.messager.progress({
							text : "正在获取中...",
						});
					},
					success : function (lostType, response, status) {
						$.messager.progress("close");
						if (lostType) { 
							$("#lostTypeEditDiv").dialog("open");
							$("#lostType_lostTypeId_edit").val(lostType.lostTypeId);
							$("#lostType_lostTypeId_edit").validatebox({
								required : true,
								missingMessage : "请输入丢失类型id",
								editable: false
							});
							$("#lostType_lostTypeName_edit").val(lostType.lostTypeName);
							$("#lostType_lostTypeName_edit").validatebox({
								required : true,
								missingMessage : "请输入丢失类型名称",
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

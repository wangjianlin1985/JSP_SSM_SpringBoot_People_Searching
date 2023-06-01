var nativePlace_manage_tool = null; 
$(function () { 
	initNativePlaceManageTool(); //建立NativePlace管理对象
	nativePlace_manage_tool.init(); //如果需要通过下拉框查询，首先初始化下拉框的值
	$("#nativePlace_manage").datagrid({
		url : 'NativePlace/list',
		fit : true,
		fitColumns : true,
		striped : true,
		rownumbers : true,
		border : false,
		pagination : true,
		pageSize : 5,
		pageList : [5, 10, 15, 20, 25],
		pageNumber : 1,
		sortName : "placeId",
		sortOrder : "desc",
		toolbar : "#nativePlace_manage_tool",
		columns : [[
			{
				field : "placeId",
				title : "籍贯id",
				width : 70,
			},
			{
				field : "placeName",
				title : "籍贯名称",
				width : 140,
			},
		]],
	});

	$("#nativePlaceEditDiv").dialog({
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
				if ($("#nativePlaceEditForm").form("validate")) {
					//验证表单 
					if(!$("#nativePlaceEditForm").form("validate")) {
						$.messager.alert("错误提示","你输入的信息还有错误！","warning");
					} else {
						$("#nativePlaceEditForm").form({
						    url:"NativePlace/" + $("#nativePlace_placeId_edit").val() + "/update",
						    onSubmit: function(){
								if($("#nativePlaceEditForm").form("validate"))  {
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
			                        $("#nativePlaceEditDiv").dialog("close");
			                        nativePlace_manage_tool.reload();
			                    }else{
			                        $.messager.alert("消息",obj.message);
			                    } 
						    }
						});
						//提交表单
						$("#nativePlaceEditForm").submit();
					}
				}
			},
		},{
			text : "取消",
			iconCls : "icon-redo",
			handler : function () {
				$("#nativePlaceEditDiv").dialog("close");
				$("#nativePlaceEditForm").form("reset"); 
			},
		}],
	});
});

function initNativePlaceManageTool() {
	nativePlace_manage_tool = {
		init: function() {
		},
		reload : function () {
			$("#nativePlace_manage").datagrid("reload");
		},
		redo : function () {
			$("#nativePlace_manage").datagrid("unselectAll");
		},
		search: function() {
			$("#nativePlace_manage").datagrid("load");
		},
		exportExcel: function() {
			$("#nativePlaceQueryForm").form({
			    url:"NativePlace/OutToExcel",
			});
			//提交表单
			$("#nativePlaceQueryForm").submit();
		},
		remove : function () {
			var rows = $("#nativePlace_manage").datagrid("getSelections");
			if (rows.length > 0) {
				$.messager.confirm("确定操作", "您正在要删除所选的记录吗？", function (flag) {
					if (flag) {
						var placeIds = [];
						for (var i = 0; i < rows.length; i ++) {
							placeIds.push(rows[i].placeId);
						}
						$.ajax({
							type : "POST",
							url : "NativePlace/deletes",
							data : {
								placeIds : placeIds.join(","),
							},
							beforeSend : function () {
								$("#nativePlace_manage").datagrid("loading");
							},
							success : function (data) {
								if (data.success) {
									$("#nativePlace_manage").datagrid("loaded");
									$("#nativePlace_manage").datagrid("load");
									$("#nativePlace_manage").datagrid("unselectAll");
									$.messager.show({
										title : "提示",
										msg : data.message
									});
								} else {
									$("#nativePlace_manage").datagrid("loaded");
									$("#nativePlace_manage").datagrid("load");
									$("#nativePlace_manage").datagrid("unselectAll");
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
			var rows = $("#nativePlace_manage").datagrid("getSelections");
			if (rows.length > 1) {
				$.messager.alert("警告操作！", "编辑记录只能选定一条数据！", "warning");
			} else if (rows.length == 1) {
				$.ajax({
					url : "NativePlace/" + rows[0].placeId +  "/update",
					type : "get",
					data : {
						//placeId : rows[0].placeId,
					},
					beforeSend : function () {
						$.messager.progress({
							text : "正在获取中...",
						});
					},
					success : function (nativePlace, response, status) {
						$.messager.progress("close");
						if (nativePlace) { 
							$("#nativePlaceEditDiv").dialog("open");
							$("#nativePlace_placeId_edit").val(nativePlace.placeId);
							$("#nativePlace_placeId_edit").validatebox({
								required : true,
								missingMessage : "请输入籍贯id",
								editable: false
							});
							$("#nativePlace_placeName_edit").val(nativePlace.placeName);
							$("#nativePlace_placeName_edit").validatebox({
								required : true,
								missingMessage : "请输入籍贯名称",
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

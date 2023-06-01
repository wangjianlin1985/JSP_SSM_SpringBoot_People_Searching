$(function () {
	$.ajax({
		url : "FindType/" + $("#findType_findTypeId_edit").val() + "/update",
		type : "get",
		data : {
			//findTypeId : $("#findType_findTypeId_edit").val(),
		},
		beforeSend : function () {
			$.messager.progress({
				text : "正在获取中...",
			});
		},
		success : function (findType, response, status) {
			$.messager.progress("close");
			if (findType) { 
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
				$(".messager-window").css("z-index",10000);
			}
		}
	});

	$("#findTypeModifyButton").click(function(){ 
		if ($("#findTypeEditForm").form("validate")) {
			$("#findTypeEditForm").form({
			    url:"FindType/" +  $("#findType_findTypeId_edit").val() + "/update",
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
                	var obj = jQuery.parseJSON(data);
                    if(obj.success){
                        $.messager.alert("消息","信息修改成功！");
                        $(".messager-window").css("z-index",10000);
                        //location.href="frontlist";
                    }else{
                        $.messager.alert("消息",obj.message);
                        $(".messager-window").css("z-index",10000);
                    } 
			    }
			});
			//提交表单
			$("#findTypeEditForm").submit();
		} else {
			$.messager.alert("错误提示","你输入的信息还有错误！","warning");
			$(".messager-window").css("z-index",10000);
		}
	});
});

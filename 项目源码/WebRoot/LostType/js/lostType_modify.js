$(function () {
	$.ajax({
		url : "LostType/" + $("#lostType_lostTypeId_edit").val() + "/update",
		type : "get",
		data : {
			//lostTypeId : $("#lostType_lostTypeId_edit").val(),
		},
		beforeSend : function () {
			$.messager.progress({
				text : "正在获取中...",
			});
		},
		success : function (lostType, response, status) {
			$.messager.progress("close");
			if (lostType) { 
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
				$(".messager-window").css("z-index",10000);
			}
		}
	});

	$("#lostTypeModifyButton").click(function(){ 
		if ($("#lostTypeEditForm").form("validate")) {
			$("#lostTypeEditForm").form({
			    url:"LostType/" +  $("#lostType_lostTypeId_edit").val() + "/update",
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
			$("#lostTypeEditForm").submit();
		} else {
			$.messager.alert("错误提示","你输入的信息还有错误！","warning");
			$(".messager-window").css("z-index",10000);
		}
	});
});

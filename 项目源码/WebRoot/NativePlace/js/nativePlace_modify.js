$(function () {
	$.ajax({
		url : "NativePlace/" + $("#nativePlace_placeId_edit").val() + "/update",
		type : "get",
		data : {
			//placeId : $("#nativePlace_placeId_edit").val(),
		},
		beforeSend : function () {
			$.messager.progress({
				text : "正在获取中...",
			});
		},
		success : function (nativePlace, response, status) {
			$.messager.progress("close");
			if (nativePlace) { 
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
				$(".messager-window").css("z-index",10000);
			}
		}
	});

	$("#nativePlaceModifyButton").click(function(){ 
		if ($("#nativePlaceEditForm").form("validate")) {
			$("#nativePlaceEditForm").form({
			    url:"NativePlace/" +  $("#nativePlace_placeId_edit").val() + "/update",
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
			$("#nativePlaceEditForm").submit();
		} else {
			$.messager.alert("错误提示","你输入的信息还有错误！","warning");
			$(".messager-window").css("z-index",10000);
		}
	});
});

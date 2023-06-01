$(function () {
	$.ajax({
		url : "FindComment/" + $("#findComment_commentId_edit").val() + "/update",
		type : "get",
		data : {
			//commentId : $("#findComment_commentId_edit").val(),
		},
		beforeSend : function () {
			$.messager.progress({
				text : "正在获取中...",
			});
		},
		success : function (findComment, response, status) {
			$.messager.progress("close");
			if (findComment) { 
				$("#findComment_commentId_edit").val(findComment.commentId);
				$("#findComment_commentId_edit").validatebox({
					required : true,
					missingMessage : "请输入评论id",
					editable: false
				});
				$("#findComment_findPersonObj_findPersonId_edit").combobox({
					url:"FindPerson/listAll",
					valueField:"findPersonId",
					textField:"personName",
					panelHeight: "auto",
					editable: false, //不允许手动输入 
					onLoadSuccess: function () { //数据加载完毕事件
						$("#findComment_findPersonObj_findPersonId_edit").combobox("select", findComment.findPersonObjPri);
						//var data = $("#findComment_findPersonObj_findPersonId_edit").combobox("getData"); 
						//if (data.length > 0) {
							//$("#findComment_findPersonObj_findPersonId_edit").combobox("select", data[0].findPersonId);
						//}
					}
				});
				$("#findComment_content_edit").val(findComment.content);
				$("#findComment_content_edit").validatebox({
					required : true,
					missingMessage : "请输入评论内容",
				});
				$("#findComment_userObj_user_name_edit").combobox({
					url:"UserInfo/listAll",
					valueField:"user_name",
					textField:"name",
					panelHeight: "auto",
					editable: false, //不允许手动输入 
					onLoadSuccess: function () { //数据加载完毕事件
						$("#findComment_userObj_user_name_edit").combobox("select", findComment.userObjPri);
						//var data = $("#findComment_userObj_user_name_edit").combobox("getData"); 
						//if (data.length > 0) {
							//$("#findComment_userObj_user_name_edit").combobox("select", data[0].user_name);
						//}
					}
				});
				$("#findComment_commentTime_edit").val(findComment.commentTime);
			} else {
				$.messager.alert("获取失败！", "未知错误导致失败，请重试！", "warning");
				$(".messager-window").css("z-index",10000);
			}
		}
	});

	$("#findCommentModifyButton").click(function(){ 
		if ($("#findCommentEditForm").form("validate")) {
			$("#findCommentEditForm").form({
			    url:"FindComment/" +  $("#findComment_commentId_edit").val() + "/update",
			    onSubmit: function(){
					if($("#findCommentEditForm").form("validate"))  {
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
			$("#findCommentEditForm").submit();
		} else {
			$.messager.alert("错误提示","你输入的信息还有错误！","warning");
			$(".messager-window").css("z-index",10000);
		}
	});
});

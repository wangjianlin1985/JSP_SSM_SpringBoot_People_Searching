$(function () {
	$("#findComment_findPersonObj_findPersonId").combobox({
	    url:'FindPerson/listAll',
	    valueField: "findPersonId",
	    textField: "personName",
	    panelHeight: "auto",
        editable: false, //不允许手动输入
        required : true,
        onLoadSuccess: function () { //数据加载完毕事件
            var data = $("#findComment_findPersonObj_findPersonId").combobox("getData"); 
            if (data.length > 0) {
                $("#findComment_findPersonObj_findPersonId").combobox("select", data[0].findPersonId);
            }
        }
	});
	$("#findComment_content").validatebox({
		required : true, 
		missingMessage : '请输入评论内容',
	});

	$("#findComment_userObj_user_name").combobox({
	    url:'UserInfo/listAll',
	    valueField: "user_name",
	    textField: "name",
	    panelHeight: "auto",
        editable: false, //不允许手动输入
        required : true,
        onLoadSuccess: function () { //数据加载完毕事件
            var data = $("#findComment_userObj_user_name").combobox("getData"); 
            if (data.length > 0) {
                $("#findComment_userObj_user_name").combobox("select", data[0].user_name);
            }
        }
	});
	//单击添加按钮
	$("#findCommentAddButton").click(function () {
		//验证表单 
		if(!$("#findCommentAddForm").form("validate")) {
			$.messager.alert("错误提示","你输入的信息还有错误！","warning");
			$(".messager-window").css("z-index",10000);
		} else {
			$("#findCommentAddForm").form({
			    url:"FindComment/add",
			    onSubmit: function(){
					if($("#findCommentAddForm").form("validate"))  { 
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
                    //此处data={"Success":true}是字符串
                	var obj = jQuery.parseJSON(data); 
                    if(obj.success){ 
                        $.messager.alert("消息","保存成功！");
                        $(".messager-window").css("z-index",10000);
                        $("#findCommentAddForm").form("clear");
                    }else{
                        $.messager.alert("消息",obj.message);
                        $(".messager-window").css("z-index",10000);
                    }
			    }
			});
			//提交表单
			$("#findCommentAddForm").submit();
		}
	});

	//单击清空按钮
	$("#findCommentClearButton").click(function () { 
		$("#findCommentAddForm").form("clear"); 
	});
});

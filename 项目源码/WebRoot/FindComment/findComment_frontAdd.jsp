<%@ page language="java" import="java.util.*"  contentType="text/html;charset=UTF-8"%> 
<%@ page import="com.chengxusheji.po.FindPerson" %>
<%@ page import="com.chengxusheji.po.UserInfo" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1 , user-scalable=no">
<title>评论添加</title>
<link href="<%=basePath %>plugins/bootstrap.css" rel="stylesheet">
<link href="<%=basePath %>plugins/bootstrap-dashen.css" rel="stylesheet">
<link href="<%=basePath %>plugins/font-awesome.css" rel="stylesheet">
<link href="<%=basePath %>plugins/animate.css" rel="stylesheet">
<link href="<%=basePath %>plugins/bootstrap-datetimepicker.min.css" rel="stylesheet" media="screen">
</head>
<body style="margin-top:70px;">
<jsp:include page="../header.jsp"></jsp:include>
<div class="container">
	<div class="row">
		<div class="col-md-12 wow fadeInUp" data-wow-duration="0.5s">
			<div>
				<!-- Nav tabs -->
				<ul class="nav nav-tabs" role="tablist">
			    	<li role="presentation" ><a href="<%=basePath %>FindComment/frontlist">评论列表</a></li>
			    	<li role="presentation" class="active"><a href="#findCommentAdd" aria-controls="findCommentAdd" role="tab" data-toggle="tab">添加评论</a></li>
				</ul>
				<!-- Tab panes -->
				<div class="tab-content">
				    <div role="tabpanel" class="tab-pane" id="findCommentList">
				    </div>
				    <div role="tabpanel" class="tab-pane active" id="findCommentAdd"> 
				      	<form class="form-horizontal" name="findCommentAddForm" id="findCommentAddForm" enctype="multipart/form-data" method="post"  class="mar_t15">
						  <div class="form-group">
						  	 <label for="findComment_findPersonObj_findPersonId" class="col-md-2 text-right">被评寻人信息:</label>
						  	 <div class="col-md-8">
							    <select id="findComment_findPersonObj_findPersonId" name="findComment.findPersonObj.findPersonId" class="form-control">
							    </select>
						  	 </div>
						  </div>
						  <div class="form-group">
						  	 <label for="findComment_content" class="col-md-2 text-right">评论内容:</label>
						  	 <div class="col-md-8">
							    <textarea id="findComment_content" name="findComment.content" rows="8" class="form-control" placeholder="请输入评论内容"></textarea>
							 </div>
						  </div>
						  <div class="form-group">
						  	 <label for="findComment_userObj_user_name" class="col-md-2 text-right">评论用户:</label>
						  	 <div class="col-md-8">
							    <select id="findComment_userObj_user_name" name="findComment.userObj.user_name" class="form-control">
							    </select>
						  	 </div>
						  </div>
						  <div class="form-group">
						  	 <label for="findComment_commentTime" class="col-md-2 text-right">评论时间:</label>
						  	 <div class="col-md-8">
							    <input type="text" id="findComment_commentTime" name="findComment.commentTime" class="form-control" placeholder="请输入评论时间">
							 </div>
						  </div>
				          <div class="form-group">
				             <span class="col-md-2""></span>
				             <span onclick="ajaxFindCommentAdd();" class="btn btn-primary bottom5 top5">添加</span>
				          </div>
						</form> 
				        <style>#findCommentAddForm .form-group {margin:10px;}  </style>
					</div>
				</div>
			</div>
		</div>
	</div> 
</div>

<jsp:include page="../footer.jsp"></jsp:include> 
<script src="<%=basePath %>plugins/jquery.min.js"></script>
<script src="<%=basePath %>plugins/bootstrap.js"></script>
<script src="<%=basePath %>plugins/wow.min.js"></script>
<script src="<%=basePath %>plugins/bootstrapvalidator/js/bootstrapValidator.min.js"></script>
<script type="text/javascript" src="<%=basePath %>plugins/bootstrap-datetimepicker.min.js" charset="UTF-8"></script>
<script type="text/javascript" src="<%=basePath %>plugins/locales/bootstrap-datetimepicker.zh-CN.js" charset="UTF-8"></script>
<script>
var basePath = "<%=basePath%>";
	//提交添加评论信息
	function ajaxFindCommentAdd() { 
		//提交之前先验证表单
		$("#findCommentAddForm").data('bootstrapValidator').validate();
		if(!$("#findCommentAddForm").data('bootstrapValidator').isValid()){
			return;
		}
		jQuery.ajax({
			type : "post",
			url : basePath + "FindComment/add",
			dataType : "json" , 
			data: new FormData($("#findCommentAddForm")[0]),
			success : function(obj) {
				if(obj.success){ 
					alert("保存成功！");
					$("#findCommentAddForm").find("input").val("");
					$("#findCommentAddForm").find("textarea").val("");
				} else {
					alert(obj.message);
				}
			},
			processData: false, 
			contentType: false, 
		});
	} 
$(function(){
	/*小屏幕导航点击关闭菜单*/
    $('.navbar-collapse a').click(function(){
        $('.navbar-collapse').collapse('hide');
    });
    new WOW().init();
	//验证评论添加表单字段
	$('#findCommentAddForm').bootstrapValidator({
		feedbackIcons: {
			valid: 'glyphicon glyphicon-ok',
			invalid: 'glyphicon glyphicon-remove',
			validating: 'glyphicon glyphicon-refresh'
		},
		fields: {
			"findComment.content": {
				validators: {
					notEmpty: {
						message: "评论内容不能为空",
					}
				}
			},
		}
	}); 
	//初始化被评寻人信息下拉框值 
	$.ajax({
		url: basePath + "FindPerson/listAll",
		type: "get",
		success: function(findPersons,response,status) { 
			$("#findComment_findPersonObj_findPersonId").empty();
			var html="";
    		$(findPersons).each(function(i,findPerson){
    			html += "<option value='" + findPerson.findPersonId + "'>" + findPerson.personName + "</option>";
    		});
    		$("#findComment_findPersonObj_findPersonId").html(html);
    	}
	});
	//初始化评论用户下拉框值 
	$.ajax({
		url: basePath + "UserInfo/listAll",
		type: "get",
		success: function(userInfos,response,status) { 
			$("#findComment_userObj_user_name").empty();
			var html="";
    		$(userInfos).each(function(i,userInfo){
    			html += "<option value='" + userInfo.user_name + "'>" + userInfo.name + "</option>";
    		});
    		$("#findComment_userObj_user_name").html(html);
    	}
	});
})
</script>
</body>
</html>

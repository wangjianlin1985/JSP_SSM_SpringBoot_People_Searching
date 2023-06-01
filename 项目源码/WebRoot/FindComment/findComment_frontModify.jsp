<%@ page language="java" import="java.util.*"  contentType="text/html;charset=UTF-8"%> 
<%@ page import="com.chengxusheji.po.FindComment" %>
<%@ page import="com.chengxusheji.po.FindPerson" %>
<%@ page import="com.chengxusheji.po.UserInfo" %>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
    //获取所有的findPersonObj信息
    List<FindPerson> findPersonList = (List<FindPerson>)request.getAttribute("findPersonList");
    //获取所有的userObj信息
    List<UserInfo> userInfoList = (List<UserInfo>)request.getAttribute("userInfoList");
    FindComment findComment = (FindComment)request.getAttribute("findComment");

%>
<!DOCTYPE html>
<html>
<head>
  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1 , user-scalable=no">
  <TITLE>修改评论信息</TITLE>
  <link href="<%=basePath %>plugins/bootstrap.css" rel="stylesheet">
  <link href="<%=basePath %>plugins/bootstrap-dashen.css" rel="stylesheet">
  <link href="<%=basePath %>plugins/font-awesome.css" rel="stylesheet">
  <link href="<%=basePath %>plugins/animate.css" rel="stylesheet"> 
</head>
<body style="margin-top:70px;"> 
<div class="container">
<jsp:include page="../header.jsp"></jsp:include>
	<div class="col-md-9 wow fadeInLeft">
	<ul class="breadcrumb">
  		<li><a href="<%=basePath %>index.jsp">首页</a></li>
  		<li class="active">评论信息修改</li>
	</ul>
		<div class="row"> 
      	<form class="form-horizontal" name="findCommentEditForm" id="findCommentEditForm" enctype="multipart/form-data" method="post"  class="mar_t15">
		  <div class="form-group">
			 <label for="findComment_commentId_edit" class="col-md-3 text-right">评论id:</label>
			 <div class="col-md-9"> 
			 	<input type="text" id="findComment_commentId_edit" name="findComment.commentId" class="form-control" placeholder="请输入评论id" readOnly>
			 </div>
		  </div> 
		  <div class="form-group">
		  	 <label for="findComment_findPersonObj_findPersonId_edit" class="col-md-3 text-right">被评寻人信息:</label>
		  	 <div class="col-md-9">
			    <select id="findComment_findPersonObj_findPersonId_edit" name="findComment.findPersonObj.findPersonId" class="form-control">
			    </select>
		  	 </div>
		  </div>
		  <div class="form-group">
		  	 <label for="findComment_content_edit" class="col-md-3 text-right">评论内容:</label>
		  	 <div class="col-md-9">
			    <textarea id="findComment_content_edit" name="findComment.content" rows="8" class="form-control" placeholder="请输入评论内容"></textarea>
			 </div>
		  </div>
		  <div class="form-group">
		  	 <label for="findComment_userObj_user_name_edit" class="col-md-3 text-right">评论用户:</label>
		  	 <div class="col-md-9">
			    <select id="findComment_userObj_user_name_edit" name="findComment.userObj.user_name" class="form-control">
			    </select>
		  	 </div>
		  </div>
		  <div class="form-group">
		  	 <label for="findComment_commentTime_edit" class="col-md-3 text-right">评论时间:</label>
		  	 <div class="col-md-9">
			    <input type="text" id="findComment_commentTime_edit" name="findComment.commentTime" class="form-control" placeholder="请输入评论时间">
			 </div>
		  </div>
			  <div class="form-group">
			  	<span class="col-md-3""></span>
			  	<span onclick="ajaxFindCommentModify();" class="btn btn-primary bottom5 top5">修改</span>
			  </div>
		</form> 
	    <style>#findCommentEditForm .form-group {margin-bottom:5px;}  </style>
      </div>
   </div>
</div>


<jsp:include page="../footer.jsp"></jsp:include>
<script src="<%=basePath %>plugins/jquery.min.js"></script>
<script src="<%=basePath %>plugins/bootstrap.js"></script>
<script src="<%=basePath %>plugins/wow.min.js"></script>
<script src="<%=basePath %>plugins/bootstrap-datetimepicker.min.js"></script>
<script src="<%=basePath %>plugins/locales/bootstrap-datetimepicker.zh-CN.js"></script>
<script type="text/javascript" src="<%=basePath %>js/jsdate.js"></script>
<script>
var basePath = "<%=basePath%>";
/*弹出修改评论界面并初始化数据*/
function findCommentEdit(commentId) {
	$.ajax({
		url :  basePath + "FindComment/" + commentId + "/update",
		type : "get",
		dataType: "json",
		success : function (findComment, response, status) {
			if (findComment) {
				$("#findComment_commentId_edit").val(findComment.commentId);
				$.ajax({
					url: basePath + "FindPerson/listAll",
					type: "get",
					success: function(findPersons,response,status) { 
						$("#findComment_findPersonObj_findPersonId_edit").empty();
						var html="";
		        		$(findPersons).each(function(i,findPerson){
		        			html += "<option value='" + findPerson.findPersonId + "'>" + findPerson.personName + "</option>";
		        		});
		        		$("#findComment_findPersonObj_findPersonId_edit").html(html);
		        		$("#findComment_findPersonObj_findPersonId_edit").val(findComment.findPersonObjPri);
					}
				});
				$("#findComment_content_edit").val(findComment.content);
				$.ajax({
					url: basePath + "UserInfo/listAll",
					type: "get",
					success: function(userInfos,response,status) { 
						$("#findComment_userObj_user_name_edit").empty();
						var html="";
		        		$(userInfos).each(function(i,userInfo){
		        			html += "<option value='" + userInfo.user_name + "'>" + userInfo.name + "</option>";
		        		});
		        		$("#findComment_userObj_user_name_edit").html(html);
		        		$("#findComment_userObj_user_name_edit").val(findComment.userObjPri);
					}
				});
				$("#findComment_commentTime_edit").val(findComment.commentTime);
			} else {
				alert("获取信息失败！");
			}
		}
	});
}

/*ajax方式提交评论信息表单给服务器端修改*/
function ajaxFindCommentModify() {
	$.ajax({
		url :  basePath + "FindComment/" + $("#findComment_commentId_edit").val() + "/update",
		type : "post",
		dataType: "json",
		data: new FormData($("#findCommentEditForm")[0]),
		success : function (obj, response, status) {
            if(obj.success){
                alert("信息修改成功！");
                location.reload(true);
                $("#findCommentQueryForm").submit();
            }else{
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
    findCommentEdit("<%=request.getParameter("commentId")%>");
 })
 </script> 
</body>
</html>


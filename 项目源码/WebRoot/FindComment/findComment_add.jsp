<%@ page language="java" import="java.util.*"  contentType="text/html;charset=UTF-8"%>
<jsp:include page="../check_logstate.jsp"/>

<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/findComment.css" />
<div id="findCommentAddDiv">
	<form id="findCommentAddForm" enctype="multipart/form-data"  method="post">
		<div>
			<span class="label">被评寻人信息:</span>
			<span class="inputControl">
				<input class="textbox" type="text" id="findComment_findPersonObj_findPersonId" name="findComment.findPersonObj.findPersonId" style="width: auto"/>
			</span>
		</div>
		<div>
			<span class="label">评论内容:</span>
			<span class="inputControl">
				<textarea id="findComment_content" name="findComment.content" rows="6" cols="80"></textarea>

			</span>

		</div>
		<div>
			<span class="label">评论用户:</span>
			<span class="inputControl">
				<input class="textbox" type="text" id="findComment_userObj_user_name" name="findComment.userObj.user_name" style="width: auto"/>
			</span>
		</div>
		<div>
			<span class="label">评论时间:</span>
			<span class="inputControl">
				<input class="textbox" type="text" id="findComment_commentTime" name="findComment.commentTime" style="width:200px" />

			</span>

		</div>
		<div class="operation">
			<a id="findCommentAddButton" class="easyui-linkbutton">添加</a>
			<a id="findCommentClearButton" class="easyui-linkbutton">重填</a>
		</div> 
	</form>
</div>
<script src="${pageContext.request.contextPath}/FindComment/js/findComment_add.js"></script> 

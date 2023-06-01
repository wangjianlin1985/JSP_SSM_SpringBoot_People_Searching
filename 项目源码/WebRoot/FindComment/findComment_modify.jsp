<%@ page language="java" contentType="text/html;charset=UTF-8"%>
<jsp:include page="../check_logstate.jsp"/>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/findComment.css" />
<div id="findComment_editDiv">
	<form id="findCommentEditForm" enctype="multipart/form-data"  method="post">
		<div>
			<span class="label">评论id:</span>
			<span class="inputControl">
				<input class="textbox" type="text" id="findComment_commentId_edit" name="findComment.commentId" value="<%=request.getParameter("commentId") %>" style="width:200px" />
			</span>
		</div>

		<div>
			<span class="label">被评寻人信息:</span>
			<span class="inputControl">
				<input class="textbox"  id="findComment_findPersonObj_findPersonId_edit" name="findComment.findPersonObj.findPersonId" style="width: auto"/>
			</span>
		</div>
		<div>
			<span class="label">评论内容:</span>
			<span class="inputControl">
				<textarea id="findComment_content_edit" name="findComment.content" rows="8" cols="60"></textarea>

			</span>

		</div>
		<div>
			<span class="label">评论用户:</span>
			<span class="inputControl">
				<input class="textbox"  id="findComment_userObj_user_name_edit" name="findComment.userObj.user_name" style="width: auto"/>
			</span>
		</div>
		<div>
			<span class="label">评论时间:</span>
			<span class="inputControl">
				<input class="textbox" type="text" id="findComment_commentTime_edit" name="findComment.commentTime" style="width:200px" />

			</span>

		</div>
		<div class="operation">
			<a id="findCommentModifyButton" class="easyui-linkbutton">更新</a> 
		</div>
	</form>
</div>
<script src="${pageContext.request.contextPath}/FindComment/js/findComment_modify.js"></script> 

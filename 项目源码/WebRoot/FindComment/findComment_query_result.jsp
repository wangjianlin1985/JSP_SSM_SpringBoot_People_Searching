<%@ page language="java"  contentType="text/html;charset=UTF-8"%>
<jsp:include page="../check_logstate.jsp"/> 
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/findComment.css" /> 

<div id="findComment_manage"></div>
<div id="findComment_manage_tool" style="padding:5px;">
	<div style="margin-bottom:5px;">
		<a href="#" class="easyui-linkbutton" iconCls="icon-edit-new" plain="true" onclick="findComment_manage_tool.edit();">修改</a>
		<a href="#" class="easyui-linkbutton" iconCls="icon-delete-new" plain="true" onclick="findComment_manage_tool.remove();">删除</a>
		<a href="#" class="easyui-linkbutton" iconCls="icon-reload" plain="true"  onclick="findComment_manage_tool.reload();">刷新</a>
		<a href="#" class="easyui-linkbutton" iconCls="icon-redo" plain="true" onclick="findComment_manage_tool.redo();">取消选择</a>
		<a href="#" class="easyui-linkbutton" iconCls="icon-export" plain="true" onclick="findComment_manage_tool.exportExcel();">导出到excel</a>
	</div>
	<div style="padding:0 0 0 7px;color:#333;">
		<form id="findCommentQueryForm" method="post">
			被评寻人信息：<input class="textbox" type="text" id="findPersonObj_findPersonId_query" name="findPersonObj.findPersonId" style="width: auto"/>
			评论用户：<input class="textbox" type="text" id="userObj_user_name_query" name="userObj.user_name" style="width: auto"/>
			评论时间：<input type="text" class="textbox" id="commentTime" name="commentTime" style="width:110px" />
			<a href="#" class="easyui-linkbutton" iconCls="icon-search" onclick="findComment_manage_tool.search();">查询</a>
		</form>	
	</div>
</div>

<div id="findCommentEditDiv">
	<form id="findCommentEditForm" enctype="multipart/form-data"  method="post">
		<div>
			<span class="label">评论id:</span>
			<span class="inputControl">
				<input class="textbox" type="text" id="findComment_commentId_edit" name="findComment.commentId" style="width:200px" />
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
	</form>
</div>
<script type="text/javascript" src="FindComment/js/findComment_manage.js"></script> 

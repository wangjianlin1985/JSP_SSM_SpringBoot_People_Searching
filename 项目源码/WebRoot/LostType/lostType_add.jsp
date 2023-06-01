<%@ page language="java" import="java.util.*"  contentType="text/html;charset=UTF-8"%>
<jsp:include page="../check_logstate.jsp"/>

<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/lostType.css" />
<div id="lostTypeAddDiv">
	<form id="lostTypeAddForm" enctype="multipart/form-data"  method="post">
		<div>
			<span class="label">丢失类型名称:</span>
			<span class="inputControl">
				<input class="textbox" type="text" id="lostType_lostTypeName" name="lostType.lostTypeName" style="width:200px" />

			</span>

		</div>
		<div class="operation">
			<a id="lostTypeAddButton" class="easyui-linkbutton">添加</a>
			<a id="lostTypeClearButton" class="easyui-linkbutton">重填</a>
		</div> 
	</form>
</div>
<script src="${pageContext.request.contextPath}/LostType/js/lostType_add.js"></script> 

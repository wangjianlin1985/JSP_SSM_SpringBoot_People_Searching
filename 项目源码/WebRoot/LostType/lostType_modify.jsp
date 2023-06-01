<%@ page language="java" contentType="text/html;charset=UTF-8"%>
<jsp:include page="../check_logstate.jsp"/>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/lostType.css" />
<div id="lostType_editDiv">
	<form id="lostTypeEditForm" enctype="multipart/form-data"  method="post">
		<div>
			<span class="label">丢失类型id:</span>
			<span class="inputControl">
				<input class="textbox" type="text" id="lostType_lostTypeId_edit" name="lostType.lostTypeId" value="<%=request.getParameter("lostTypeId") %>" style="width:200px" />
			</span>
		</div>

		<div>
			<span class="label">丢失类型名称:</span>
			<span class="inputControl">
				<input class="textbox" type="text" id="lostType_lostTypeName_edit" name="lostType.lostTypeName" style="width:200px" />

			</span>

		</div>
		<div class="operation">
			<a id="lostTypeModifyButton" class="easyui-linkbutton">更新</a> 
		</div>
	</form>
</div>
<script src="${pageContext.request.contextPath}/LostType/js/lostType_modify.js"></script> 

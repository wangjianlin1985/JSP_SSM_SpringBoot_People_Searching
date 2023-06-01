<%@ page language="java" contentType="text/html;charset=UTF-8"%>
<jsp:include page="../check_logstate.jsp"/>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/findType.css" />
<div id="findType_editDiv">
	<form id="findTypeEditForm" enctype="multipart/form-data"  method="post">
		<div>
			<span class="label">寻找类型id:</span>
			<span class="inputControl">
				<input class="textbox" type="text" id="findType_findTypeId_edit" name="findType.findTypeId" value="<%=request.getParameter("findTypeId") %>" style="width:200px" />
			</span>
		</div>

		<div>
			<span class="label">寻找类型名称:</span>
			<span class="inputControl">
				<input class="textbox" type="text" id="findType_findTypeName_edit" name="findType.findTypeName" style="width:200px" />

			</span>

		</div>
		<div class="operation">
			<a id="findTypeModifyButton" class="easyui-linkbutton">更新</a> 
		</div>
	</form>
</div>
<script src="${pageContext.request.contextPath}/FindType/js/findType_modify.js"></script> 

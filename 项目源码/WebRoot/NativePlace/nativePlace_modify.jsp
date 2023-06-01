<%@ page language="java" contentType="text/html;charset=UTF-8"%>
<jsp:include page="../check_logstate.jsp"/>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/nativePlace.css" />
<div id="nativePlace_editDiv">
	<form id="nativePlaceEditForm" enctype="multipart/form-data"  method="post">
		<div>
			<span class="label">籍贯id:</span>
			<span class="inputControl">
				<input class="textbox" type="text" id="nativePlace_placeId_edit" name="nativePlace.placeId" value="<%=request.getParameter("placeId") %>" style="width:200px" />
			</span>
		</div>

		<div>
			<span class="label">籍贯名称:</span>
			<span class="inputControl">
				<input class="textbox" type="text" id="nativePlace_placeName_edit" name="nativePlace.placeName" style="width:200px" />

			</span>

		</div>
		<div class="operation">
			<a id="nativePlaceModifyButton" class="easyui-linkbutton">更新</a> 
		</div>
	</form>
</div>
<script src="${pageContext.request.contextPath}/NativePlace/js/nativePlace_modify.js"></script> 

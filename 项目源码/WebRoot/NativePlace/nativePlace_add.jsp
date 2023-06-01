<%@ page language="java" import="java.util.*"  contentType="text/html;charset=UTF-8"%>
<jsp:include page="../check_logstate.jsp"/>

<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/nativePlace.css" />
<div id="nativePlaceAddDiv">
	<form id="nativePlaceAddForm" enctype="multipart/form-data"  method="post">
		<div>
			<span class="label">籍贯名称:</span>
			<span class="inputControl">
				<input class="textbox" type="text" id="nativePlace_placeName" name="nativePlace.placeName" style="width:200px" />

			</span>

		</div>
		<div class="operation">
			<a id="nativePlaceAddButton" class="easyui-linkbutton">添加</a>
			<a id="nativePlaceClearButton" class="easyui-linkbutton">重填</a>
		</div> 
	</form>
</div>
<script src="${pageContext.request.contextPath}/NativePlace/js/nativePlace_add.js"></script> 

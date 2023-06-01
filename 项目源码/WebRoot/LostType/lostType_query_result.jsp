<%@ page language="java"  contentType="text/html;charset=UTF-8"%>
<jsp:include page="../check_logstate.jsp"/> 
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/lostType.css" /> 

<div id="lostType_manage"></div>
<div id="lostType_manage_tool" style="padding:5px;">
	<div style="margin-bottom:5px;">
		<a href="#" class="easyui-linkbutton" iconCls="icon-edit-new" plain="true" onclick="lostType_manage_tool.edit();">修改</a>
		<a href="#" class="easyui-linkbutton" iconCls="icon-delete-new" plain="true" onclick="lostType_manage_tool.remove();">删除</a>
		<a href="#" class="easyui-linkbutton" iconCls="icon-reload" plain="true"  onclick="lostType_manage_tool.reload();">刷新</a>
		<a href="#" class="easyui-linkbutton" iconCls="icon-redo" plain="true" onclick="lostType_manage_tool.redo();">取消选择</a>
		<a href="#" class="easyui-linkbutton" iconCls="icon-export" plain="true" onclick="lostType_manage_tool.exportExcel();">导出到excel</a>
	</div>
	<div style="padding:0 0 0 7px;color:#333;">
		<form id="lostTypeQueryForm" method="post">
		</form>	
	</div>
</div>

<div id="lostTypeEditDiv">
	<form id="lostTypeEditForm" enctype="multipart/form-data"  method="post">
		<div>
			<span class="label">丢失类型id:</span>
			<span class="inputControl">
				<input class="textbox" type="text" id="lostType_lostTypeId_edit" name="lostType.lostTypeId" style="width:200px" />
			</span>
		</div>
		<div>
			<span class="label">丢失类型名称:</span>
			<span class="inputControl">
				<input class="textbox" type="text" id="lostType_lostTypeName_edit" name="lostType.lostTypeName" style="width:200px" />

			</span>

		</div>
	</form>
</div>
<script type="text/javascript" src="LostType/js/lostType_manage.js"></script> 

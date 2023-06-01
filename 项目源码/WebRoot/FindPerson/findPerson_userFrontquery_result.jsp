<%@ page language="java" import="java.util.*"  contentType="text/html;charset=UTF-8"%> 
<%@ page import="com.chengxusheji.po.FindPerson" %>
<%@ page import="com.chengxusheji.po.FindType" %>
<%@ page import="com.chengxusheji.po.LostType" %>
<%@ page import="com.chengxusheji.po.NativePlace" %>
<%@ page import="com.chengxusheji.po.UserInfo" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
    List<FindPerson> findPersonList = (List<FindPerson>)request.getAttribute("findPersonList");
    //获取所有的findTypeObj信息
    List<FindType> findTypeList = (List<FindType>)request.getAttribute("findTypeList");
    //获取所有的lostTypeObj信息
    List<LostType> lostTypeList = (List<LostType>)request.getAttribute("lostTypeList");
    //获取所有的nativePlaceObj信息
    List<NativePlace> nativePlaceList = (List<NativePlace>)request.getAttribute("nativePlaceList");
    //获取所有的userObj信息
    List<UserInfo> userInfoList = (List<UserInfo>)request.getAttribute("userInfoList");
    int currentPage =  (Integer)request.getAttribute("currentPage"); //当前页
    int totalPage =   (Integer)request.getAttribute("totalPage");  //一共多少页
    int recordNumber =   (Integer)request.getAttribute("recordNumber");  //一共多少记录
    String personName = (String)request.getAttribute("personName"); //被寻人姓名查询关键字
    String sex = (String)request.getAttribute("sex"); //性别查询关键字
    NativePlace nativePlaceObj = (NativePlace)request.getAttribute("nativePlaceObj");
    FindType findTypeObj = (FindType)request.getAttribute("findTypeObj");
    LostType lostTypeObj = (LostType)request.getAttribute("lostTypeObj");
    String bornDate = (String)request.getAttribute("bornDate"); //出生日期查询关键字
    String lostDate = (String)request.getAttribute("lostDate"); //失踪日期查询关键字
    UserInfo userObj = (UserInfo)request.getAttribute("userObj");
    String addTime = (String)request.getAttribute("addTime"); //发布时间查询关键字
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1 , user-scalable=no">
<title>寻人信息查询</title>
<link href="<%=basePath %>plugins/bootstrap.css" rel="stylesheet">
<link href="<%=basePath %>plugins/bootstrap-dashen.css" rel="stylesheet">
<link href="<%=basePath %>plugins/font-awesome.css" rel="stylesheet">
<link href="<%=basePath %>plugins/animate.css" rel="stylesheet">
<link href="<%=basePath %>plugins/bootstrap-datetimepicker.min.css" rel="stylesheet" media="screen">
</head>
<body style="margin-top:70px;">
<div class="container">
<jsp:include page="../header.jsp"></jsp:include>
	<div class="col-md-9 wow fadeInLeft">
		<ul class="breadcrumb">
  			<li><a href="<%=basePath %>index.jsp">首页</a></li>
  			<li><a href="<%=basePath %>FindPerson/frontlist">寻人信息信息列表</a></li>
  			<li class="active">查询结果显示</li>
  			<a class="pull-right" href="<%=basePath %>FindPerson/findPerson_frontAdd.jsp" style="display:none;">添加寻人信息</a>
		</ul>
		<div class="row">
			<%
				/*计算起始序号*/
				int startIndex = (currentPage -1) * 5;
				/*遍历记录*/
				for(int i=0;i<findPersonList.size();i++) {
            		int currentIndex = startIndex + i + 1; //当前记录的序号
            		FindPerson findPerson = findPersonList.get(i); //获取到寻人信息对象
            		String clearLeft = "";
            		if(i%4 == 0) clearLeft = "style=\"clear:left;\"";
			%>
			<div class="col-md-3 bottom15" <%=clearLeft %>>
			  <a  href="<%=basePath  %>FindPerson/<%=findPerson.getFindPersonId() %>/frontshow"><img class="img-responsive" src="<%=basePath%><%=findPerson.getPersonPhoto()%>" /></a>
			     <div class="showFields">
			     	<div class="field">
	            		被寻人姓名:<%=findPerson.getPersonName() %>
			     	</div>
			     	<div class="field">
	            		性别:<%=findPerson.getSex() %>
			     	</div>
			     	<!-- 
			     	<div class="field">
	            		血型:<%=findPerson.getBloodType() %>
			     	</div> -->
			     	<div class="field">
	            		籍贯:<%=findPerson.getNativePlaceObj().getPlaceName() %>
			     	</div>
			     	<div class="field">
	            		寻找类型:<%=findPerson.getFindTypeObj().getFindTypeName() %>
			     	</div>
			     	<div class="field">
	            		失踪类型:<%=findPerson.getLostTypeObj().getLostTypeName() %>
			     	</div>
			     	<div class="field">
	            		出生日期:<%=findPerson.getBornDate() %>
			     	</div>
			     	<div class="field">
	            		失踪日期:<%=findPerson.getLostDate() %>
			     	</div>
			     	<div class="field">
	            		失踪时身高:<%=findPerson.getLostHeight() %>
			     	</div>
			     	<div class="field">
	            		失踪地点:<%=findPerson.getLostPlace() %>
			     	</div>
			     	<!-- 
			     	<div class="field">
	            		联系人:<%=findPerson.getConnectPerson() %>
			     	</div>
			     	<div class="field">
	            		联系电话:<%=findPerson.getConnectPhone() %>
			     	</div>
			     	<div class="field">
	            		发布时间:<%=findPerson.getAddTime() %>
			     	</div> -->
			        <a class="btn btn-primary top5" href="<%=basePath %>FindPerson/<%=findPerson.getFindPersonId() %>/frontshow">详情</a>
			        <a class="btn btn-primary top5" onclick="findPersonEdit('<%=findPerson.getFindPersonId() %>');">修改</a>
			        <a class="btn btn-primary top5" onclick="findPersonDelete('<%=findPerson.getFindPersonId() %>');">删除</a>
			     </div>
			</div>
			<%  } %>

			<div class="row">
				<div class="col-md-12">
					<nav class="pull-left">
						<ul class="pagination">
							<li><a href="#" onclick="GoToPage(<%=currentPage-1 %>,<%=totalPage %>);" aria-label="Previous"><span aria-hidden="true">&laquo;</span></a></li>
							<%
								int startPage = currentPage - 5;
								int endPage = currentPage + 5;
								if(startPage < 1) startPage=1;
								if(endPage > totalPage) endPage = totalPage;
								for(int i=startPage;i<=endPage;i++) {
							%>
							<li class="<%= currentPage==i?"active":"" %>"><a href="#"  onclick="GoToPage(<%=i %>,<%=totalPage %>);"><%=i %></a></li>
							<%  } %> 
							<li><a href="#" onclick="GoToPage(<%=currentPage+1 %>,<%=totalPage %>);"><span aria-hidden="true">&raquo;</span></a></li>
						</ul>
					</nav>
					<div class="pull-right" style="line-height:75px;" >共有<%=recordNumber %>条记录，当前第 <%=currentPage %>/<%=totalPage %> 页</div>
				</div>
			</div>
		</div>
	</div>

	<div class="col-md-3 wow fadeInRight">
		<div class="page-header">
    		<h1>寻人信息查询</h1>
		</div>
		<form name="findPersonQueryForm" id="findPersonQueryForm" action="<%=basePath %>FindPerson/userFrontlist" class="mar_t15" method="post">
			<div class="form-group">
				<label for="personName">被寻人姓名:</label>
				<input type="text" id="personName" name="personName" value="<%=personName %>" class="form-control" placeholder="请输入被寻人姓名">
			</div>
			<div class="form-group">
				<label for="sex">性别:</label>
				<input type="text" id="sex" name="sex" value="<%=sex %>" class="form-control" placeholder="请输入性别">
			</div>
            <div class="form-group">
            	<label for="nativePlaceObj_placeId">籍贯：</label>
                <select id="nativePlaceObj_placeId" name="nativePlaceObj.placeId" class="form-control">
                	<option value="0">不限制</option>
	 				<%
	 				for(NativePlace nativePlaceTemp:nativePlaceList) {
	 					String selected = "";
 					if(nativePlaceObj!=null && nativePlaceObj.getPlaceId()!=null && nativePlaceObj.getPlaceId().intValue()==nativePlaceTemp.getPlaceId().intValue())
 						selected = "selected";
	 				%>
 				 <option value="<%=nativePlaceTemp.getPlaceId() %>" <%=selected %>><%=nativePlaceTemp.getPlaceName() %></option>
	 				<%
	 				}
	 				%>
 			</select>
            </div>
            <div class="form-group">
            	<label for="findTypeObj_findTypeId">寻找类型：</label>
                <select id="findTypeObj_findTypeId" name="findTypeObj.findTypeId" class="form-control">
                	<option value="0">不限制</option>
	 				<%
	 				for(FindType findTypeTemp:findTypeList) {
	 					String selected = "";
 					if(findTypeObj!=null && findTypeObj.getFindTypeId()!=null && findTypeObj.getFindTypeId().intValue()==findTypeTemp.getFindTypeId().intValue())
 						selected = "selected";
	 				%>
 				 <option value="<%=findTypeTemp.getFindTypeId() %>" <%=selected %>><%=findTypeTemp.getFindTypeName() %></option>
	 				<%
	 				}
	 				%>
 			</select>
            </div>
            <div class="form-group">
            	<label for="lostTypeObj_lostTypeId">失踪类型：</label>
                <select id="lostTypeObj_lostTypeId" name="lostTypeObj.lostTypeId" class="form-control">
                	<option value="0">不限制</option>
	 				<%
	 				for(LostType lostTypeTemp:lostTypeList) {
	 					String selected = "";
 					if(lostTypeObj!=null && lostTypeObj.getLostTypeId()!=null && lostTypeObj.getLostTypeId().intValue()==lostTypeTemp.getLostTypeId().intValue())
 						selected = "selected";
	 				%>
 				 <option value="<%=lostTypeTemp.getLostTypeId() %>" <%=selected %>><%=lostTypeTemp.getLostTypeName() %></option>
	 				<%
	 				}
	 				%>
 			</select>
            </div>
            <!-- 
			<div class="form-group">
				<label for="bornDate">出生日期:</label>
				<input type="text" id="bornDate" name="bornDate" class="form-control"  placeholder="请选择出生日期" value="<%=bornDate %>" onclick="SelectDate(this,'yyyy-MM-dd')" />
			</div>
			 -->
			<div class="form-group">
				<label for="lostDate">失踪日期:</label>
				<input type="text" id="lostDate" name="lostDate" class="form-control"  placeholder="请选择失踪日期" value="<%=lostDate %>" onclick="SelectDate(this,'yyyy-MM-dd')" />
			</div>
			<!-- 
            <div class="form-group">
            	<label for="userObj_user_name">发布用户：</label>
                <select id="userObj_user_name" name="userObj.user_name" class="form-control">
                	<option value="">不限制</option>
	 				<%
	 				for(UserInfo userInfoTemp:userInfoList) {
	 					String selected = "";
 					if(userObj!=null && userObj.getUser_name()!=null && userObj.getUser_name().equals(userInfoTemp.getUser_name()))
 						selected = "selected";
	 				%>
 				 <option value="<%=userInfoTemp.getUser_name() %>" <%=selected %>><%=userInfoTemp.getName() %></option>
	 				<%
	 				}
	 				%>
 			</select>
            </div>
			<div class="form-group">
				<label for="addTime">发布时间:</label>
				<input type="text" id="addTime" name="addTime" class="form-control"  placeholder="请选择发布时间" value="<%=addTime %>" onclick="SelectDate(this,'yyyy-MM-dd')" />
			</div>
			 -->
            <input type=hidden name=currentPage value="<%=currentPage %>" />
            <button type="submit" class="btn btn-primary">查询</button>
        </form>
	</div>

		</div>
</div>
<div id="findPersonEditDialog" class="modal fade" tabindex="-1" role="dialog">
  <div class="modal-dialog" style="width:900px;" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title"><i class="fa fa-edit"></i>&nbsp;寻人信息信息编辑</h4>
      </div>
      <div class="modal-body" style="height:450px; overflow: scroll;">
      	<form class="form-horizontal" name="findPersonEditForm" id="findPersonEditForm" enctype="multipart/form-data" method="post"  class="mar_t15">
		  <div class="form-group">
			 <label for="findPerson_findPersonId_edit" class="col-md-3 text-right">寻人id:</label>
			 <div class="col-md-9"> 
			 	<input type="text" id="findPerson_findPersonId_edit" name="findPerson.findPersonId" class="form-control" placeholder="请输入寻人id" readOnly>
			 </div>
		  </div> 
		  <div class="form-group">
		  	 <label for="findPerson_personName_edit" class="col-md-3 text-right">被寻人姓名:</label>
		  	 <div class="col-md-9">
			    <input type="text" id="findPerson_personName_edit" name="findPerson.personName" class="form-control" placeholder="请输入被寻人姓名">
			 </div>
		  </div>
		  <div class="form-group">
		  	 <label for="findPerson_sex_edit" class="col-md-3 text-right">性别:</label>
		  	 <div class="col-md-9">
			    <input type="text" id="findPerson_sex_edit" name="findPerson.sex" class="form-control" placeholder="请输入性别">
			 </div>
		  </div>
		  <div class="form-group">
		  	 <label for="findPerson_personPhoto_edit" class="col-md-3 text-right">被寻人照片:</label>
		  	 <div class="col-md-9">
			    <img  class="img-responsive" id="findPerson_personPhotoImg" border="0px"/><br/>
			    <input type="hidden" id="findPerson_personPhoto" name="findPerson.personPhoto"/>
			    <input id="personPhotoFile" name="personPhotoFile" type="file" size="50" />
		  	 </div>
		  </div>
		  <div class="form-group">
		  	 <label for="findPerson_bloodType_edit" class="col-md-3 text-right">血型:</label>
		  	 <div class="col-md-9">
			    <input type="text" id="findPerson_bloodType_edit" name="findPerson.bloodType" class="form-control" placeholder="请输入血型">
			 </div>
		  </div>
		  <div class="form-group">
		  	 <label for="findPerson_nativePlaceObj_placeId_edit" class="col-md-3 text-right">籍贯:</label>
		  	 <div class="col-md-9">
			    <select id="findPerson_nativePlaceObj_placeId_edit" name="findPerson.nativePlaceObj.placeId" class="form-control">
			    </select>
		  	 </div>
		  </div>
		  <div class="form-group">
		  	 <label for="findPerson_findTypeObj_findTypeId_edit" class="col-md-3 text-right">寻找类型:</label>
		  	 <div class="col-md-9">
			    <select id="findPerson_findTypeObj_findTypeId_edit" name="findPerson.findTypeObj.findTypeId" class="form-control">
			    </select>
		  	 </div>
		  </div>
		  <div class="form-group">
		  	 <label for="findPerson_lostTypeObj_lostTypeId_edit" class="col-md-3 text-right">失踪类型:</label>
		  	 <div class="col-md-9">
			    <select id="findPerson_lostTypeObj_lostTypeId_edit" name="findPerson.lostTypeObj.lostTypeId" class="form-control">
			    </select>
		  	 </div>
		  </div>
		  <div class="form-group">
		  	 <label for="findPerson_bornDate_edit" class="col-md-3 text-right">出生日期:</label>
		  	 <div class="col-md-9">
                <div class="input-group date findPerson_bornDate_edit col-md-12" data-link-field="findPerson_bornDate_edit" data-link-format="yyyy-mm-dd">
                    <input class="form-control" id="findPerson_bornDate_edit" name="findPerson.bornDate" size="16" type="text" value="" placeholder="请选择出生日期" readonly>
                    <span class="input-group-addon"><span class="glyphicon glyphicon-remove"></span></span>
                    <span class="input-group-addon"><span class="glyphicon glyphicon-calendar"></span></span>
                </div>
		  	 </div>
		  </div>
		  <div class="form-group">
		  	 <label for="findPerson_lostDate_edit" class="col-md-3 text-right">失踪日期:</label>
		  	 <div class="col-md-9">
                <div class="input-group date findPerson_lostDate_edit col-md-12" data-link-field="findPerson_lostDate_edit" data-link-format="yyyy-mm-dd">
                    <input class="form-control" id="findPerson_lostDate_edit" name="findPerson.lostDate" size="16" type="text" value="" placeholder="请选择失踪日期" readonly>
                    <span class="input-group-addon"><span class="glyphicon glyphicon-remove"></span></span>
                    <span class="input-group-addon"><span class="glyphicon glyphicon-calendar"></span></span>
                </div>
		  	 </div>
		  </div>
		  <div class="form-group">
		  	 <label for="findPerson_lostHeight_edit" class="col-md-3 text-right">失踪时身高:</label>
		  	 <div class="col-md-9">
			    <input type="text" id="findPerson_lostHeight_edit" name="findPerson.lostHeight" class="form-control" placeholder="请输入失踪时身高">
			 </div>
		  </div>
		  <div class="form-group">
		  	 <label for="findPerson_lostPlace_edit" class="col-md-3 text-right">失踪地点:</label>
		  	 <div class="col-md-9">
			    <input type="text" id="findPerson_lostPlace_edit" name="findPerson.lostPlace" class="form-control" placeholder="请输入失踪地点">
			 </div>
		  </div>
		  <div class="form-group">
		  	 <label for="findPerson_personDesc_edit" class="col-md-3 text-right">相貌特征描述:</label>
		  	 <div class="col-md-9">
			 	<textarea name="findPerson.personDesc" id="findPerson_personDesc_edit" style="width:100%;height:500px;"></textarea>
			 </div>
		  </div>
		  <div class="form-group">
		  	 <label for="findPerson_connectPerson_edit" class="col-md-3 text-right">联系人:</label>
		  	 <div class="col-md-9">
			    <input type="text" id="findPerson_connectPerson_edit" name="findPerson.connectPerson" class="form-control" placeholder="请输入联系人">
			 </div>
		  </div>
		  <div class="form-group">
		  	 <label for="findPerson_connectPhone_edit" class="col-md-3 text-right">联系电话:</label>
		  	 <div class="col-md-9">
			    <input type="text" id="findPerson_connectPhone_edit" name="findPerson.connectPhone" class="form-control" placeholder="请输入联系电话">
			 </div>
		  </div>
		  <div class="form-group">
		  	 <label for="findPerson_email_edit" class="col-md-3 text-right">电子邮箱:</label>
		  	 <div class="col-md-9">
			    <input type="text" id="findPerson_email_edit" name="findPerson.email" class="form-control" placeholder="请输入电子邮箱">
			 </div>
		  </div>
		  <div class="form-group">
		  	 <label for="findPerson_connectAddress_edit" class="col-md-3 text-right">联系地址:</label>
		  	 <div class="col-md-9">
			    <input type="text" id="findPerson_connectAddress_edit" name="findPerson.connectAddress" class="form-control" placeholder="请输入联系地址">
			 </div>
		  </div>
		  <div class="form-group">
		  	 <label for="findPerson_postcode_edit" class="col-md-3 text-right">邮政编码:</label>
		  	 <div class="col-md-9">
			    <input type="text" id="findPerson_postcode_edit" name="findPerson.postcode" class="form-control" placeholder="请输入邮政编码">
			 </div>
		  </div>
		  <div class="form-group">
		  	 <label for="findPerson_otherWay_edit" class="col-md-3 text-right">其他联系方式:</label>
		  	 <div class="col-md-9">
			    <input type="text" id="findPerson_otherWay_edit" name="findPerson.otherWay" class="form-control" placeholder="请输入其他联系方式">
			 </div>
		  </div>
		  <div class="form-group">
		  	 <label for="findPerson_memo_edit" class="col-md-3 text-right">备注:</label>
		  	 <div class="col-md-9">
			    <textarea id="findPerson_memo_edit" name="findPerson.memo" rows="8" class="form-control" placeholder="请输入备注"></textarea>
			 </div>
		  </div>
		  <div class="form-group" style="display:none;">
		  	 <label for="findPerson_userObj_user_name_edit" class="col-md-3 text-right">发布用户:</label>
		  	 <div class="col-md-9">
			    <select id="findPerson_userObj_user_name_edit" name="findPerson.userObj.user_name" class="form-control">
			    </select>
		  	 </div>
		  </div>
		  <div class="form-group" style="display:none;">
		  	 <label for="findPerson_addTime_edit" class="col-md-3 text-right">发布时间:</label>
		  	 <div class="col-md-9">
                <div class="input-group date findPerson_addTime_edit col-md-12" data-link-field="findPerson_addTime_edit">
                    <input class="form-control" id="findPerson_addTime_edit" name="findPerson.addTime" size="16" type="text" value="" placeholder="请选择发布时间" readonly>
                    <span class="input-group-addon"><span class="glyphicon glyphicon-remove"></span></span>
                    <span class="input-group-addon"><span class="glyphicon glyphicon-calendar"></span></span>
                </div>
		  	 </div>
		  </div>
		</form> 
	    <style>#findPersonEditForm .form-group {margin-bottom:5px;}  </style>
      </div>
      <div class="modal-footer"> 
      	<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
      	<button type="button" class="btn btn-primary" onclick="ajaxFindPersonModify();">提交</button>
      </div>
    </div><!-- /.modal-content -->
  </div><!-- /.modal-dialog -->
</div><!-- /.modal -->
<jsp:include page="../footer.jsp"></jsp:include> 
<script src="<%=basePath %>plugins/jquery.min.js"></script>
<script src="<%=basePath %>plugins/bootstrap.js"></script>
<script src="<%=basePath %>plugins/wow.min.js"></script>
<script src="<%=basePath %>plugins/bootstrap-datetimepicker.min.js"></script>
<script src="<%=basePath %>plugins/locales/bootstrap-datetimepicker.zh-CN.js"></script>
<script type="text/javascript" src="<%=basePath %>js/jsdate.js"></script>
<script type="text/javascript" charset="utf-8" src="${pageContext.request.contextPath}/ueditor1_4_3/ueditor.config.js"></script>
<script type="text/javascript" charset="utf-8" src="${pageContext.request.contextPath}/ueditor1_4_3/ueditor.all.min.js"> </script>
<script type="text/javascript" charset="utf-8" src="${pageContext.request.contextPath}/ueditor1_4_3/lang/zh-cn/zh-cn.js"></script>
<script>
//实例化编辑器
var findPerson_personDesc_edit = UE.getEditor('findPerson_personDesc_edit'); //相貌特征描述编辑器
var basePath = "<%=basePath%>";
/*跳转到查询结果的某页*/
function GoToPage(currentPage,totalPage) {
    if(currentPage==0) return;
    if(currentPage>totalPage) return;
    document.findPersonQueryForm.currentPage.value = currentPage;
    document.findPersonQueryForm.submit();
}

/*可以直接跳转到某页*/
function changepage(totalPage)
{
    var pageValue=document.findPersonQueryForm.pageValue.value;
    if(pageValue>totalPage) {
        alert('你输入的页码超出了总页数!');
        return ;
    }
    document.findPersonQueryForm.currentPage.value = pageValue;
    documentfindPersonQueryForm.submit();
}

/*弹出修改寻人信息界面并初始化数据*/
function findPersonEdit(findPersonId) {
	$.ajax({
		url :  basePath + "FindPerson/" + findPersonId + "/update",
		type : "get",
		dataType: "json",
		success : function (findPerson, response, status) {
			if (findPerson) {
				$("#findPerson_findPersonId_edit").val(findPerson.findPersonId);
				$("#findPerson_personName_edit").val(findPerson.personName);
				$("#findPerson_sex_edit").val(findPerson.sex);
				$("#findPerson_personPhoto").val(findPerson.personPhoto);
				$("#findPerson_personPhotoImg").attr("src", basePath +　findPerson.personPhoto);
				$("#findPerson_bloodType_edit").val(findPerson.bloodType);
				$.ajax({
					url: basePath + "NativePlace/listAll",
					type: "get",
					success: function(nativePlaces,response,status) { 
						$("#findPerson_nativePlaceObj_placeId_edit").empty();
						var html="";
		        		$(nativePlaces).each(function(i,nativePlace){
		        			html += "<option value='" + nativePlace.placeId + "'>" + nativePlace.placeName + "</option>";
		        		});
		        		$("#findPerson_nativePlaceObj_placeId_edit").html(html);
		        		$("#findPerson_nativePlaceObj_placeId_edit").val(findPerson.nativePlaceObjPri);
					}
				});
				$.ajax({
					url: basePath + "FindType/listAll",
					type: "get",
					success: function(findTypes,response,status) { 
						$("#findPerson_findTypeObj_findTypeId_edit").empty();
						var html="";
		        		$(findTypes).each(function(i,findType){
		        			html += "<option value='" + findType.findTypeId + "'>" + findType.findTypeName + "</option>";
		        		});
		        		$("#findPerson_findTypeObj_findTypeId_edit").html(html);
		        		$("#findPerson_findTypeObj_findTypeId_edit").val(findPerson.findTypeObjPri);
					}
				});
				$.ajax({
					url: basePath + "LostType/listAll",
					type: "get",
					success: function(lostTypes,response,status) { 
						$("#findPerson_lostTypeObj_lostTypeId_edit").empty();
						var html="";
		        		$(lostTypes).each(function(i,lostType){
		        			html += "<option value='" + lostType.lostTypeId + "'>" + lostType.lostTypeName + "</option>";
		        		});
		        		$("#findPerson_lostTypeObj_lostTypeId_edit").html(html);
		        		$("#findPerson_lostTypeObj_lostTypeId_edit").val(findPerson.lostTypeObjPri);
					}
				});
				$("#findPerson_bornDate_edit").val(findPerson.bornDate);
				$("#findPerson_lostDate_edit").val(findPerson.lostDate);
				$("#findPerson_lostHeight_edit").val(findPerson.lostHeight);
				$("#findPerson_lostPlace_edit").val(findPerson.lostPlace);
				findPerson_personDesc_edit.setContent(findPerson.personDesc, false);
				$("#findPerson_connectPerson_edit").val(findPerson.connectPerson);
				$("#findPerson_connectPhone_edit").val(findPerson.connectPhone);
				$("#findPerson_email_edit").val(findPerson.email);
				$("#findPerson_connectAddress_edit").val(findPerson.connectAddress);
				$("#findPerson_postcode_edit").val(findPerson.postcode);
				$("#findPerson_otherWay_edit").val(findPerson.otherWay);
				$("#findPerson_memo_edit").val(findPerson.memo);
				$.ajax({
					url: basePath + "UserInfo/listAll",
					type: "get",
					success: function(userInfos,response,status) { 
						$("#findPerson_userObj_user_name_edit").empty();
						var html="";
		        		$(userInfos).each(function(i,userInfo){
		        			html += "<option value='" + userInfo.user_name + "'>" + userInfo.name + "</option>";
		        		});
		        		$("#findPerson_userObj_user_name_edit").html(html);
		        		$("#findPerson_userObj_user_name_edit").val(findPerson.userObjPri);
					}
				});
				$("#findPerson_addTime_edit").val(findPerson.addTime);
				$('#findPersonEditDialog').modal('show');
			} else {
				alert("获取信息失败！");
			}
		}
	});
}

/*删除寻人信息信息*/
function findPersonDelete(findPersonId) {
	if(confirm("确认删除这个记录")) {
		$.ajax({
			type : "POST",
			url : basePath + "FindPerson/deletes",
			data : {
				findPersonIds : findPersonId,
			},
			success : function (obj) {
				if (obj.success) {
					alert("删除成功");
					$("#findPersonQueryForm").submit();
					//location.href= basePath + "FindPerson/frontlist";
				}
				else 
					alert(obj.message);
			},
		});
	}
}

/*ajax方式提交寻人信息信息表单给服务器端修改*/
function ajaxFindPersonModify() {
	$.ajax({
		url :  basePath + "FindPerson/" + $("#findPerson_findPersonId_edit").val() + "/update",
		type : "post",
		dataType: "json",
		data: new FormData($("#findPersonEditForm")[0]),
		success : function (obj, response, status) {
            if(obj.success){
                alert("信息修改成功！");
                $("#findPersonQueryForm").submit();
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

    /*出生日期组件*/
    $('.findPerson_bornDate_edit').datetimepicker({
    	language:  'zh-CN',  //语言
    	format: 'yyyy-mm-dd',
    	minView: 2,
    	weekStart: 1,
    	todayBtn:  1,
    	autoclose: 1,
    	minuteStep: 1,
    	todayHighlight: 1,
    	startView: 2,
    	forceParse: 0
    });
    /*失踪日期组件*/
    $('.findPerson_lostDate_edit').datetimepicker({
    	language:  'zh-CN',  //语言
    	format: 'yyyy-mm-dd',
    	minView: 2,
    	weekStart: 1,
    	todayBtn:  1,
    	autoclose: 1,
    	minuteStep: 1,
    	todayHighlight: 1,
    	startView: 2,
    	forceParse: 0
    });
    /*发布时间组件*/
    $('.findPerson_addTime_edit').datetimepicker({
    	language:  'zh-CN',  //语言
    	format: 'yyyy-mm-dd hh:ii:ss',
    	weekStart: 1,
    	todayBtn:  1,
    	autoclose: 1,
    	minuteStep: 1,
    	todayHighlight: 1,
    	startView: 2,
    	forceParse: 0
    });
})
</script>
</body>
</html>


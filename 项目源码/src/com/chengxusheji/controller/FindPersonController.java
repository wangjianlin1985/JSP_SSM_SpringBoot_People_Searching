package com.chengxusheji.controller;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.chengxusheji.utils.ExportExcelUtil;
import com.chengxusheji.utils.UserException;
import com.chengxusheji.service.FindCommentService;
import com.chengxusheji.service.FindPersonService;
import com.chengxusheji.po.FindComment;
import com.chengxusheji.po.FindPerson;
import com.chengxusheji.service.FindTypeService;
import com.chengxusheji.po.FindType;
import com.chengxusheji.service.LostTypeService;
import com.chengxusheji.po.LostType;
import com.chengxusheji.service.NativePlaceService;
import com.chengxusheji.po.NativePlace;
import com.chengxusheji.service.UserInfoService;
import com.chengxusheji.po.UserInfo;

//FindPerson管理控制层
@Controller
@RequestMapping("/FindPerson")
public class FindPersonController extends BaseController {

    /*业务层对象*/
    @Resource FindPersonService findPersonService;
    @Resource FindCommentService findCommentService;
    @Resource FindTypeService findTypeService;
    @Resource LostTypeService lostTypeService;
    @Resource NativePlaceService nativePlaceService;
    @Resource UserInfoService userInfoService;
	@InitBinder("nativePlaceObj")
	public void initBindernativePlaceObj(WebDataBinder binder) {
		binder.setFieldDefaultPrefix("nativePlaceObj.");
	}
	@InitBinder("findTypeObj")
	public void initBinderfindTypeObj(WebDataBinder binder) {
		binder.setFieldDefaultPrefix("findTypeObj.");
	}
	@InitBinder("lostTypeObj")
	public void initBinderlostTypeObj(WebDataBinder binder) {
		binder.setFieldDefaultPrefix("lostTypeObj.");
	}
	@InitBinder("userObj")
	public void initBinderuserObj(WebDataBinder binder) {
		binder.setFieldDefaultPrefix("userObj.");
	}
	@InitBinder("findPerson")
	public void initBinderFindPerson(WebDataBinder binder) {
		binder.setFieldDefaultPrefix("findPerson.");
	}
	 
	
	  

	/*ajax方式按照查询条件分页查询寻人信息信息*/
	@RequestMapping(value = { "/listAll" }, method = {RequestMethod.GET,RequestMethod.POST})
	public void listAll(HttpServletResponse response) throws Exception {
		List<FindPerson> findPersonList = findPersonService.queryAllFindPerson();
        response.setContentType("text/json;charset=UTF-8"); 
		PrintWriter out = response.getWriter();
		JSONArray jsonArray = new JSONArray();
		for(FindPerson findPerson:findPersonList) {
			JSONObject jsonFindPerson = new JSONObject();
			jsonFindPerson.accumulate("findPersonId", findPerson.getFindPersonId());
			jsonFindPerson.accumulate("personName", findPerson.getPersonName());
			jsonArray.put(jsonFindPerson);
		}
		out.println(jsonArray.toString());
		out.flush();
		out.close();
	}

	/*前台按照查询条件分页查询寻人信息信息*/
	@RequestMapping(value = { "/frontlist" }, method = {RequestMethod.GET,RequestMethod.POST})
	public String frontlist(String personName,String sex,@ModelAttribute("nativePlaceObj") NativePlace nativePlaceObj,@ModelAttribute("findTypeObj") FindType findTypeObj,@ModelAttribute("lostTypeObj") LostType lostTypeObj,String bornDate,String lostDate,@ModelAttribute("userObj") UserInfo userObj,String addTime,Integer currentPage, Model model, HttpServletRequest request) throws Exception  {
		if (currentPage==null || currentPage == 0) currentPage = 1;
		if (personName == null) personName = "";
		if (sex == null) sex = "";
		if (bornDate == null) bornDate = "";
		if (lostDate == null) lostDate = "";
		if (addTime == null) addTime = "";
		List<FindPerson> findPersonList = findPersonService.queryFindPerson(personName, sex, nativePlaceObj, findTypeObj, lostTypeObj, bornDate, lostDate, userObj, addTime, currentPage);
	    /*计算总的页数和总的记录数*/
	    findPersonService.queryTotalPageAndRecordNumber(personName, sex, nativePlaceObj, findTypeObj, lostTypeObj, bornDate, lostDate, userObj, addTime);
	    /*获取到总的页码数目*/
	    int totalPage = findPersonService.getTotalPage();
	    /*当前查询条件下总记录数*/
	    int recordNumber = findPersonService.getRecordNumber();
	    request.setAttribute("findPersonList",  findPersonList);
	    request.setAttribute("totalPage", totalPage);
	    request.setAttribute("recordNumber", recordNumber);
	    request.setAttribute("currentPage", currentPage);
	    request.setAttribute("personName", personName);
	    request.setAttribute("sex", sex);
	    request.setAttribute("nativePlaceObj", nativePlaceObj);
	    request.setAttribute("findTypeObj", findTypeObj);
	    request.setAttribute("lostTypeObj", lostTypeObj);
	    request.setAttribute("bornDate", bornDate);
	    request.setAttribute("lostDate", lostDate);
	    request.setAttribute("userObj", userObj);
	    request.setAttribute("addTime", addTime);
	    List<FindType> findTypeList = findTypeService.queryAllFindType();
	    request.setAttribute("findTypeList", findTypeList);
	    List<LostType> lostTypeList = lostTypeService.queryAllLostType();
	    request.setAttribute("lostTypeList", lostTypeList);
	    List<NativePlace> nativePlaceList = nativePlaceService.queryAllNativePlace();
	    request.setAttribute("nativePlaceList", nativePlaceList);
	    List<UserInfo> userInfoList = userInfoService.queryAllUserInfo();
	    request.setAttribute("userInfoList", userInfoList);
		return "FindPerson/findPerson_frontquery_result"; 
	}
	
	
	/*前台按照查询条件分页查询寻人信息信息*/
	@RequestMapping(value = { "/userFrontlist" }, method = {RequestMethod.GET,RequestMethod.POST})
	public String userFrontlist(String personName,String sex,@ModelAttribute("nativePlaceObj") NativePlace nativePlaceObj,@ModelAttribute("findTypeObj") FindType findTypeObj,@ModelAttribute("lostTypeObj") LostType lostTypeObj,String bornDate,String lostDate,@ModelAttribute("userObj") UserInfo userObj,String addTime,Integer currentPage, Model model, HttpServletRequest request,HttpSession session) throws Exception  {
		if (currentPage==null || currentPage == 0) currentPage = 1;
		if (personName == null) personName = "";
		if (sex == null) sex = "";
		if (bornDate == null) bornDate = "";
		if (lostDate == null) lostDate = "";
		if (addTime == null) addTime = "";
		
		userObj = new UserInfo();
		userObj.setUser_name(session.getAttribute("user_name").toString()); 
		
		List<FindPerson> findPersonList = findPersonService.queryFindPerson(personName, sex, nativePlaceObj, findTypeObj, lostTypeObj, bornDate, lostDate, userObj, addTime, currentPage);
	    /*计算总的页数和总的记录数*/
	    findPersonService.queryTotalPageAndRecordNumber(personName, sex, nativePlaceObj, findTypeObj, lostTypeObj, bornDate, lostDate, userObj, addTime);
	    /*获取到总的页码数目*/
	    int totalPage = findPersonService.getTotalPage();
	    /*当前查询条件下总记录数*/
	    int recordNumber = findPersonService.getRecordNumber();
	    request.setAttribute("findPersonList",  findPersonList);
	    request.setAttribute("totalPage", totalPage);
	    request.setAttribute("recordNumber", recordNumber);
	    request.setAttribute("currentPage", currentPage);
	    request.setAttribute("personName", personName);
	    request.setAttribute("sex", sex);
	    request.setAttribute("nativePlaceObj", nativePlaceObj);
	    request.setAttribute("findTypeObj", findTypeObj);
	    request.setAttribute("lostTypeObj", lostTypeObj);
	    request.setAttribute("bornDate", bornDate);
	    request.setAttribute("lostDate", lostDate);
	    request.setAttribute("userObj", userObj);
	    request.setAttribute("addTime", addTime);
	    List<FindType> findTypeList = findTypeService.queryAllFindType();
	    request.setAttribute("findTypeList", findTypeList);
	    List<LostType> lostTypeList = lostTypeService.queryAllLostType();
	    request.setAttribute("lostTypeList", lostTypeList);
	    List<NativePlace> nativePlaceList = nativePlaceService.queryAllNativePlace();
	    request.setAttribute("nativePlaceList", nativePlaceList);
	    List<UserInfo> userInfoList = userInfoService.queryAllUserInfo();
	    request.setAttribute("userInfoList", userInfoList);
		return "FindPerson/findPerson_userFrontquery_result"; 
	}
	
	  
	}
    /*删除寻人信息信息*/
	@RequestMapping(value="/{findPersonId}/delete",method=RequestMethod.GET)
	public String delete(@PathVariable Integer findPersonId,HttpServletRequest request) throws UnsupportedEncodingException {
		  try {
			  findPersonService.deleteFindPerson(findPersonId);
	            request.setAttribute("message", "寻人信息删除成功!");
	            return "message";
	        } catch (Exception e) { 
	            e.printStackTrace();
	            request.setAttribute("error", "寻人信息删除失败!");
				return "error";

	        }

	}

	/*ajax方式删除多条寻人信息记录*/
	@RequestMapping(value="/deletes",method=RequestMethod.POST)
	public void delete(String findPersonIds,HttpServletRequest request,HttpServletResponse response) throws IOException, JSONException {
		String message = "";
    	boolean success = false;
        try { 
        	int count = findPersonService.deleteFindPersons(findPersonIds);
        	success = true;
        	message = count + "条记录删除成功";
        	writeJsonResponse(response, success, message);
        } catch (Exception e) { 
            //e.printStackTrace();
            message = "有记录存在外键约束,删除失败";
            writeJsonResponse(response, success, message);
        }
	}

	/*按照查询条件导出寻人信息信息到Excel*/
	@RequestMapping(value = { "/OutToExcel" }, method = {RequestMethod.GET,RequestMethod.POST})
	public void OutToExcel(String personName,String sex,@ModelAttribute("nativePlaceObj") NativePlace nativePlaceObj,@ModelAttribute("findTypeObj") FindType findTypeObj,@ModelAttribute("lostTypeObj") LostType lostTypeObj,String bornDate,String lostDate,@ModelAttribute("userObj") UserInfo userObj,String addTime, Model model, HttpServletRequest request,HttpServletResponse response) throws Exception {
        if(personName == null) personName = "";
        if(sex == null) sex = "";
        if(bornDate == null) bornDate = "";
        if(lostDate == null) lostDate = "";
        if(addTime == null) addTime = "";
        List<FindPerson> findPersonList = findPersonService.queryFindPerson(personName,sex,nativePlaceObj,findTypeObj,lostTypeObj,bornDate,lostDate,userObj,addTime);
        ExportExcelUtil ex = new ExportExcelUtil();
        String _title = "FindPerson信息记录"; 
        String[] headers = { "被寻人姓名","性别","被寻人照片","血型","籍贯","寻找类型","失踪类型","出生日期","失踪日期","失踪时身高","失踪地点","联系人","联系电话","发布时间"};
        List<String[]> dataset = new ArrayList<String[]>(); 
        for(int i=0;i<findPersonList.size();i++) {
        	FindPerson findPerson = findPersonList.get(i); 
        	dataset.add(new String[]{findPerson.getPersonName(),findPerson.getSex(),findPerson.getPersonPhoto(),findPerson.getBloodType(),findPerson.getNativePlaceObj().getPlaceName(),findPerson.getFindTypeObj().getFindTypeName(),findPerson.getLostTypeObj().getLostTypeName(),findPerson.getBornDate(),findPerson.getLostDate(),findPerson.getLostHeight(),findPerson.getLostPlace(),findPerson.getConnectPerson(),findPerson.getConnectPhone(),findPerson.getAddTime()});
        }
        /*
        OutputStream out = null;
		try {
			out = new FileOutputStream("C://output.xls");
			ex.exportExcel(title,headers, dataset, out);
		    out.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		*/
		OutputStream out = null;//创建一个输出流对象 
		try { 
			out = response.getOutputStream();//
			response.setHeader("Content-disposition","attachment; filename="+"FindPerson.xls");//filename是下载的xls的名，建议最好用英文 
			response.setContentType("application/msexcel;charset=UTF-8");//设置类型 
			response.setHeader("Pragma","No-cache");//设置头 
			response.setHeader("Cache-Control","no-cache");//设置头 
			response.setDateHeader("Expires", 0);//设置日期头  
			String rootPath = request.getSession().getServletContext().getRealPath("/");
			ex.exportExcel(rootPath,_title,headers, dataset, out);
			out.flush();
		} catch (IOException e) { 
			e.printStackTrace(); 
		}finally{
			try{
				if(out!=null){ 
					out.close(); 
				}
			}catch(IOException e){ 
				e.printStackTrace(); 
			} 
		}
    }
}

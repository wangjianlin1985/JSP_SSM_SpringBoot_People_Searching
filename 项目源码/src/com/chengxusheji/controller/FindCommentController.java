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
import com.chengxusheji.po.FindComment;
import com.chengxusheji.service.FindPersonService;
import com.chengxusheji.po.FindPerson;
import com.chengxusheji.service.UserInfoService;
import com.chengxusheji.po.UserInfo;

//FindComment管理控制层
@Controller
@RequestMapping("/FindComment")
public class FindCommentController extends BaseController {

    /*业务层对象*/
    @Resource FindCommentService findCommentService;

    @Resource FindPersonService findPersonService;
    @Resource UserInfoService userInfoService;
	@InitBinder("findPersonObj")
	public void initBinderfindPersonObj(WebDataBinder binder) {
		binder.setFieldDefaultPrefix("findPersonObj.");
	}
	@InitBinder("userObj")
	public void initBinderuserObj(WebDataBinder binder) {
		binder.setFieldDefaultPrefix("userObj.");
	}
	@InitBinder("findComment")
	public void initBinderFindComment(WebDataBinder binder) {
		binder.setFieldDefaultPrefix("findComment.");
	}
	/*跳转到添加FindComment视图*/
	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public String add(Model model,HttpServletRequest request) throws Exception {
		model.addAttribute(new FindComment());
		/*查询所有的FindPerson信息*/
		List<FindPerson> findPersonList = findPersonService.queryAllFindPerson();
		request.setAttribute("findPersonList", findPersonList);
		/*查询所有的UserInfo信息*/
		List<UserInfo> userInfoList = userInfoService.queryAllUserInfo();
		request.setAttribute("userInfoList", userInfoList);
		return "FindComment_add";
	}

	/*客户端ajax方式提交添加评论信息*/
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public void add(@Validated FindComment findComment, BindingResult br,
			Model model, HttpServletRequest request,HttpServletResponse response) throws Exception {
		String message = "";
		boolean success = false;
		if (br.hasErrors()) {
			message = "输入信息不符合要求！";
			writeJsonResponse(response, success, message);
			return ;
		}
        findCommentService.addFindComment(findComment);
        message = "评论添加成功!";
        success = true;
        writeJsonResponse(response, success, message);
	}
	
	/*客户端ajax方式提交添加评论信息*/
	@RequestMapping(value = "/userAdd", method = RequestMethod.POST)
	public void userAdd(FindComment findComment, BindingResult br,
			Model model, HttpServletRequest request,HttpServletResponse response,HttpSession session) throws Exception {
		String message = "";
		boolean success = false; 
		
		String user_name = (String) session.getAttribute("user_name");
		if(user_name == null) {
			message = "请先登录后才能评论！";
			writeJsonResponse(response, success, message);
			return ;
		}
		
		UserInfo userObj = new UserInfo();
		userObj.setUser_name(user_name);
		findComment.setUserObj(userObj);
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		findComment.setCommentTime(sdf.format(new java.util.Date())); 
		
        findCommentService.addFindComment(findComment);
        
        message = "评论添加成功!";
        success = true;
        writeJsonResponse(response, success, message);
	}
	
	
	/*ajax方式按照查询条件分页查询评论信息*/
	@RequestMapping(value = { "/list" }, method = {RequestMethod.GET,RequestMethod.POST})
	public void list(@ModelAttribute("findPersonObj") FindPerson findPersonObj,@ModelAttribute("userObj") UserInfo userObj,String commentTime,Integer page,Integer rows, Model model, HttpServletRequest request,HttpServletResponse response) throws Exception {
		if (page==null || page == 0) page = 1;
		if (commentTime == null) commentTime = "";
		if(rows != 0)findCommentService.setRows(rows);
		List<FindComment> findCommentList = findCommentService.queryFindComment(findPersonObj, userObj, commentTime, page);
	    /*计算总的页数和总的记录数*/
	    findCommentService.queryTotalPageAndRecordNumber(findPersonObj, userObj, commentTime);
	    /*获取到总的页码数目*/
	    int totalPage = findCommentService.getTotalPage();
	    /*当前查询条件下总记录数*/
	    int recordNumber = findCommentService.getRecordNumber();
        response.setContentType("text/json;charset=UTF-8");
		PrintWriter out = response.getWriter();
		//将要被返回到客户端的对象
		JSONObject jsonObj=new JSONObject();
		jsonObj.accumulate("total", recordNumber);
		JSONArray jsonArray = new JSONArray();
		for(FindComment findComment:findCommentList) {
			JSONObject jsonFindComment = findComment.getJsonObject();
			jsonArray.put(jsonFindComment);
		}
		jsonObj.accumulate("rows", jsonArray);
		out.println(jsonObj.toString());
		out.flush();
		out.close();
	}

	/*ajax方式按照查询条件分页查询评论信息*/
	@RequestMapping(value = { "/listAll" }, method = {RequestMethod.GET,RequestMethod.POST})
	public void listAll(HttpServletResponse response) throws Exception {
		List<FindComment> findCommentList = findCommentService.queryAllFindComment();
        response.setContentType("text/json;charset=UTF-8"); 
		PrintWriter out = response.getWriter();
		JSONArray jsonArray = new JSONArray();
		for(FindComment findComment:findCommentList) {
			JSONObject jsonFindComment = new JSONObject();
			jsonFindComment.accumulate("commentId", findComment.getCommentId());
			jsonFindComment.accumulate("content", findComment.getContent());
			jsonArray.put(jsonFindComment);
		}
		out.println(jsonArray.toString());
		out.flush();
		out.close();
	}

	/*前台按照查询条件分页查询评论信息*/
	@RequestMapping(value = { "/frontlist" }, method = {RequestMethod.GET,RequestMethod.POST})
	public String frontlist(@ModelAttribute("findPersonObj") FindPerson findPersonObj,@ModelAttribute("userObj") UserInfo userObj,String commentTime,Integer currentPage, Model model, HttpServletRequest request) throws Exception  {
		if (currentPage==null || currentPage == 0) currentPage = 1;
		if (commentTime == null) commentTime = "";
		List<FindComment> findCommentList = findCommentService.queryFindComment(findPersonObj, userObj, commentTime, currentPage);
	    /*计算总的页数和总的记录数*/
	    findCommentService.queryTotalPageAndRecordNumber(findPersonObj, userObj, commentTime);
	    /*获取到总的页码数目*/
	    int totalPage = findCommentService.getTotalPage();
	    /*当前查询条件下总记录数*/
	    int recordNumber = findCommentService.getRecordNumber();
	    request.setAttribute("findCommentList",  findCommentList);
	    request.setAttribute("totalPage", totalPage);
	    request.setAttribute("recordNumber", recordNumber);
	    request.setAttribute("currentPage", currentPage);
	    request.setAttribute("findPersonObj", findPersonObj);
	    request.setAttribute("userObj", userObj);
	    request.setAttribute("commentTime", commentTime);
	    List<FindPerson> findPersonList = findPersonService.queryAllFindPerson();
	    request.setAttribute("findPersonList", findPersonList);
	    List<UserInfo> userInfoList = userInfoService.queryAllUserInfo();
	    request.setAttribute("userInfoList", userInfoList);
		return "FindComment/findComment_frontquery_result"; 
	}

     /*前台查询FindComment信息*/
	@RequestMapping(value="/{commentId}/frontshow",method=RequestMethod.GET)
	public String frontshow(@PathVariable Integer commentId,Model model,HttpServletRequest request) throws Exception {
		/*根据主键commentId获取FindComment对象*/
        FindComment findComment = findCommentService.getFindComment(commentId);

        List<FindPerson> findPersonList = findPersonService.queryAllFindPerson();
        request.setAttribute("findPersonList", findPersonList);
        List<UserInfo> userInfoList = userInfoService.queryAllUserInfo();
        request.setAttribute("userInfoList", userInfoList);
        request.setAttribute("findComment",  findComment);
        return "FindComment/findComment_frontshow";
	}

	/*ajax方式显示评论修改jsp视图页*/
	@RequestMapping(value="/{commentId}/update",method=RequestMethod.GET)
	public void update(@PathVariable Integer commentId,Model model,HttpServletRequest request,HttpServletResponse response) throws Exception {
        /*根据主键commentId获取FindComment对象*/
        FindComment findComment = findCommentService.getFindComment(commentId);

        response.setContentType("text/json;charset=UTF-8");
        PrintWriter out = response.getWriter();
		//将要被返回到客户端的对象 
		JSONObject jsonFindComment = findComment.getJsonObject();
		out.println(jsonFindComment.toString());
		out.flush();
		out.close();
	}

	/*ajax方式更新评论信息*/
	@RequestMapping(value = "/{commentId}/update", method = RequestMethod.POST)
	public void update(@Validated FindComment findComment, BindingResult br,
			Model model, HttpServletRequest request,HttpServletResponse response) throws Exception {
		String message = "";
    	boolean success = false;
		if (br.hasErrors()) { 
			message = "输入的信息有错误！";
			writeJsonResponse(response, success, message);
			return;
		}
		try {
			findCommentService.updateFindComment(findComment);
			message = "评论更新成功!";
			success = true;
			writeJsonResponse(response, success, message);
		} catch (Exception e) {
			e.printStackTrace();
			message = "评论更新失败!";
			writeJsonResponse(response, success, message); 
		}
	}
    /*删除评论信息*/
	@RequestMapping(value="/{commentId}/delete",method=RequestMethod.GET)
	public String delete(@PathVariable Integer commentId,HttpServletRequest request) throws UnsupportedEncodingException {
		  try {
			  findCommentService.deleteFindComment(commentId);
	            request.setAttribute("message", "评论删除成功!");
	            return "message";
	        } catch (Exception e) { 
	            e.printStackTrace();
	            request.setAttribute("error", "评论删除失败!");
				return "error";

	        }

	}

	/*ajax方式删除多条评论记录*/
	@RequestMapping(value="/deletes",method=RequestMethod.POST)
	public void delete(String commentIds,HttpServletRequest request,HttpServletResponse response) throws IOException, JSONException {
		String message = "";
    	boolean success = false;
        try { 
        	int count = findCommentService.deleteFindComments(commentIds);
        	success = true;
        	message = count + "条记录删除成功";
        	writeJsonResponse(response, success, message);
        } catch (Exception e) { 
            //e.printStackTrace();
            message = "有记录存在外键约束,删除失败";
            writeJsonResponse(response, success, message);
        }
	}

	/*按照查询条件导出评论信息到Excel*/
	@RequestMapping(value = { "/OutToExcel" }, method = {RequestMethod.GET,RequestMethod.POST})
	public void OutToExcel(@ModelAttribute("findPersonObj") FindPerson findPersonObj,@ModelAttribute("userObj") UserInfo userObj,String commentTime, Model model, HttpServletRequest request,HttpServletResponse response) throws Exception {
        if(commentTime == null) commentTime = "";
        List<FindComment> findCommentList = findCommentService.queryFindComment(findPersonObj,userObj,commentTime);
        ExportExcelUtil ex = new ExportExcelUtil();
        String _title = "FindComment信息记录"; 
        String[] headers = { "评论id","被评寻人信息","评论内容","评论用户","评论时间"};
        List<String[]> dataset = new ArrayList<String[]>(); 
        for(int i=0;i<findCommentList.size();i++) {
        	FindComment findComment = findCommentList.get(i); 
        	dataset.add(new String[]{findComment.getCommentId() + "",findComment.getFindPersonObj().getPersonName(),findComment.getContent(),findComment.getUserObj().getName(),findComment.getCommentTime()});
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
			response.setHeader("Content-disposition","attachment; filename="+"FindComment.xls");//filename是下载的xls的名，建议最好用英文 
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

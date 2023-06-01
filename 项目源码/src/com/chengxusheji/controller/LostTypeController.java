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
import com.chengxusheji.service.LostTypeService;
import com.chengxusheji.po.LostType;

//LostType管理控制层
@Controller
@RequestMapping("/LostType")
public class LostTypeController extends BaseController {

    /*业务层对象*/
    @Resource LostTypeService lostTypeService;

	@InitBinder("lostType")
	public void initBinderLostType(WebDataBinder binder) {
		binder.setFieldDefaultPrefix("lostType.");
	}
	/*跳转到添加LostType视图*/
	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public String add(Model model,HttpServletRequest request) throws Exception {
		model.addAttribute(new LostType());
		return "LostType_add";
	}

	/*客户端ajax方式提交添加失踪类型信息*/
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public void add(@Validated LostType lostType, BindingResult br,
			Model model, HttpServletRequest request,HttpServletResponse response) throws Exception {
		String message = "";
		boolean success = false;
		if (br.hasErrors()) {
			message = "输入信息不符合要求！";
			writeJsonResponse(response, success, message);
			return ;
		}
        lostTypeService.addLostType(lostType);
        message = "失踪类型添加成功!";
        success = true;
        writeJsonResponse(response, success, message);
	}
	/*ajax方式按照查询条件分页查询失踪类型信息*/
	@RequestMapping(value = { "/list" }, method = {RequestMethod.GET,RequestMethod.POST})
	public void list(Integer page,Integer rows, Model model, HttpServletRequest request,HttpServletResponse response) throws Exception {
		if (page==null || page == 0) page = 1;
		if(rows != 0)lostTypeService.setRows(rows);
		List<LostType> lostTypeList = lostTypeService.queryLostType(page);
	    /*计算总的页数和总的记录数*/
	    lostTypeService.queryTotalPageAndRecordNumber();
	    /*获取到总的页码数目*/
	    int totalPage = lostTypeService.getTotalPage();
	    /*当前查询条件下总记录数*/
	    int recordNumber = lostTypeService.getRecordNumber();
        response.setContentType("text/json;charset=UTF-8");
		PrintWriter out = response.getWriter();
		//将要被返回到客户端的对象
		JSONObject jsonObj=new JSONObject();
		jsonObj.accumulate("total", recordNumber);
		JSONArray jsonArray = new JSONArray();
		for(LostType lostType:lostTypeList) {
			JSONObject jsonLostType = lostType.getJsonObject();
			jsonArray.put(jsonLostType);
		}
		jsonObj.accumulate("rows", jsonArray);
		out.println(jsonObj.toString());
		out.flush();
		out.close();
	}

	/*ajax方式按照查询条件分页查询失踪类型信息*/
	@RequestMapping(value = { "/listAll" }, method = {RequestMethod.GET,RequestMethod.POST})
	public void listAll(HttpServletResponse response) throws Exception {
		List<LostType> lostTypeList = lostTypeService.queryAllLostType();
        response.setContentType("text/json;charset=UTF-8"); 
		PrintWriter out = response.getWriter();
		JSONArray jsonArray = new JSONArray();
		for(LostType lostType:lostTypeList) {
			JSONObject jsonLostType = new JSONObject();
			jsonLostType.accumulate("lostTypeId", lostType.getLostTypeId());
			jsonLostType.accumulate("lostTypeName", lostType.getLostTypeName());
			jsonArray.put(jsonLostType);
		}
		out.println(jsonArray.toString());
		out.flush();
		out.close();
	}

	/*前台按照查询条件分页查询失踪类型信息*/
	@RequestMapping(value = { "/frontlist" }, method = {RequestMethod.GET,RequestMethod.POST})
	public String frontlist(Integer currentPage, Model model, HttpServletRequest request) throws Exception  {
		if (currentPage==null || currentPage == 0) currentPage = 1;
		List<LostType> lostTypeList = lostTypeService.queryLostType(currentPage);
	    /*计算总的页数和总的记录数*/
	    lostTypeService.queryTotalPageAndRecordNumber();
	    /*获取到总的页码数目*/
	    int totalPage = lostTypeService.getTotalPage();
	    /*当前查询条件下总记录数*/
	    int recordNumber = lostTypeService.getRecordNumber();
	    request.setAttribute("lostTypeList",  lostTypeList);
	    request.setAttribute("totalPage", totalPage);
	    request.setAttribute("recordNumber", recordNumber);
	    request.setAttribute("currentPage", currentPage);
		return "LostType/lostType_frontquery_result"; 
	}

     /*前台查询LostType信息*/
	@RequestMapping(value="/{lostTypeId}/frontshow",method=RequestMethod.GET)
	public String frontshow(@PathVariable Integer lostTypeId,Model model,HttpServletRequest request) throws Exception {
		/*根据主键lostTypeId获取LostType对象*/
        LostType lostType = lostTypeService.getLostType(lostTypeId);

        request.setAttribute("lostType",  lostType);
        return "LostType/lostType_frontshow";
	}

	/*ajax方式显示失踪类型修改jsp视图页*/
	@RequestMapping(value="/{lostTypeId}/update",method=RequestMethod.GET)
	public void update(@PathVariable Integer lostTypeId,Model model,HttpServletRequest request,HttpServletResponse response) throws Exception {
        /*根据主键lostTypeId获取LostType对象*/
        LostType lostType = lostTypeService.getLostType(lostTypeId);

        response.setContentType("text/json;charset=UTF-8");
        PrintWriter out = response.getWriter();
		//将要被返回到客户端的对象 
		JSONObject jsonLostType = lostType.getJsonObject();
		out.println(jsonLostType.toString());
		out.flush();
		out.close();
	}

	/*ajax方式更新失踪类型信息*/
	@RequestMapping(value = "/{lostTypeId}/update", method = RequestMethod.POST)
	public void update(@Validated LostType lostType, BindingResult br,
			Model model, HttpServletRequest request,HttpServletResponse response) throws Exception {
		String message = "";
    	boolean success = false;
		if (br.hasErrors()) { 
			message = "输入的信息有错误！";
			writeJsonResponse(response, success, message);
			return;
		}
		try {
			lostTypeService.updateLostType(lostType);
			message = "失踪类型更新成功!";
			success = true;
			writeJsonResponse(response, success, message);
		} catch (Exception e) {
			e.printStackTrace();
			message = "失踪类型更新失败!";
			writeJsonResponse(response, success, message); 
		}
	}
    /*删除失踪类型信息*/
	@RequestMapping(value="/{lostTypeId}/delete",method=RequestMethod.GET)
	public String delete(@PathVariable Integer lostTypeId,HttpServletRequest request) throws UnsupportedEncodingException {
		  try {
			  lostTypeService.deleteLostType(lostTypeId);
	            request.setAttribute("message", "失踪类型删除成功!");
	            return "message";
	        } catch (Exception e) { 
	            e.printStackTrace();
	            request.setAttribute("error", "失踪类型删除失败!");
				return "error";

	        }

	}

	/*ajax方式删除多条失踪类型记录*/
	@RequestMapping(value="/deletes",method=RequestMethod.POST)
	public void delete(String lostTypeIds,HttpServletRequest request,HttpServletResponse response) throws IOException, JSONException {
		String message = "";
    	boolean success = false;
        try { 
        	int count = lostTypeService.deleteLostTypes(lostTypeIds);
        	success = true;
        	message = count + "条记录删除成功";
        	writeJsonResponse(response, success, message);
        } catch (Exception e) { 
            //e.printStackTrace();
            message = "有记录存在外键约束,删除失败";
            writeJsonResponse(response, success, message);
        }
	}

	/*按照查询条件导出失踪类型信息到Excel*/
	@RequestMapping(value = { "/OutToExcel" }, method = {RequestMethod.GET,RequestMethod.POST})
	public void OutToExcel( Model model, HttpServletRequest request,HttpServletResponse response) throws Exception {
        List<LostType> lostTypeList = lostTypeService.queryLostType();
        ExportExcelUtil ex = new ExportExcelUtil();
        String _title = "LostType信息记录"; 
        String[] headers = { "丢失类型id","丢失类型名称"};
        List<String[]> dataset = new ArrayList<String[]>(); 
        for(int i=0;i<lostTypeList.size();i++) {
        	LostType lostType = lostTypeList.get(i); 
        	dataset.add(new String[]{lostType.getLostTypeId() + "",lostType.getLostTypeName()});
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
			response.setHeader("Content-disposition","attachment; filename="+"LostType.xls");//filename是下载的xls的名，建议最好用英文 
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

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
import com.chengxusheji.service.NativePlaceService;
import com.chengxusheji.po.NativePlace;

//NativePlace管理控制层
@Controller
@RequestMapping("/NativePlace")
public class NativePlaceController extends BaseController {

    /*业务层对象*/
    @Resource NativePlaceService nativePlaceService;

	@InitBinder("nativePlace")
	public void initBinderNativePlace(WebDataBinder binder) {
		binder.setFieldDefaultPrefix("nativePlace.");
	}
	/*跳转到添加NativePlace视图*/
	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public String add(Model model,HttpServletRequest request) throws Exception {
		model.addAttribute(new NativePlace());
		return "NativePlace_add";
	}

	/*客户端ajax方式提交添加籍贯信息*/
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public void add(@Validated NativePlace nativePlace, BindingResult br,
			Model model, HttpServletRequest request,HttpServletResponse response) throws Exception {
		String message = "";
		boolean success = false;
		if (br.hasErrors()) {
			message = "输入信息不符合要求！";
			writeJsonResponse(response, success, message);
			return ;
		}
        nativePlaceService.addNativePlace(nativePlace);
        message = "籍贯添加成功!";
        success = true;
        writeJsonResponse(response, success, message);
	}
	/*ajax方式按照查询条件分页查询籍贯信息*/
	@RequestMapping(value = { "/list" }, method = {RequestMethod.GET,RequestMethod.POST})
	public void list(Integer page,Integer rows, Model model, HttpServletRequest request,HttpServletResponse response) throws Exception {
		if (page==null || page == 0) page = 1;
		if(rows != 0)nativePlaceService.setRows(rows);
		List<NativePlace> nativePlaceList = nativePlaceService.queryNativePlace(page);
	    /*计算总的页数和总的记录数*/
	    nativePlaceService.queryTotalPageAndRecordNumber();
	    /*获取到总的页码数目*/
	    int totalPage = nativePlaceService.getTotalPage();
	    /*当前查询条件下总记录数*/
	    int recordNumber = nativePlaceService.getRecordNumber();
        response.setContentType("text/json;charset=UTF-8");
		PrintWriter out = response.getWriter();
		//将要被返回到客户端的对象
		JSONObject jsonObj=new JSONObject();
		jsonObj.accumulate("total", recordNumber);
		JSONArray jsonArray = new JSONArray();
		for(NativePlace nativePlace:nativePlaceList) {
			JSONObject jsonNativePlace = nativePlace.getJsonObject();
			jsonArray.put(jsonNativePlace);
		}
		jsonObj.accumulate("rows", jsonArray);
		out.println(jsonObj.toString());
		out.flush();
		out.close();
	}

	/*ajax方式按照查询条件分页查询籍贯信息*/
	@RequestMapping(value = { "/listAll" }, method = {RequestMethod.GET,RequestMethod.POST})
	public void listAll(HttpServletResponse response) throws Exception {
		List<NativePlace> nativePlaceList = nativePlaceService.queryAllNativePlace();
        response.setContentType("text/json;charset=UTF-8"); 
		PrintWriter out = response.getWriter();
		JSONArray jsonArray = new JSONArray();
		for(NativePlace nativePlace:nativePlaceList) {
			JSONObject jsonNativePlace = new JSONObject();
			jsonNativePlace.accumulate("placeId", nativePlace.getPlaceId());
			jsonNativePlace.accumulate("placeName", nativePlace.getPlaceName());
			jsonArray.put(jsonNativePlace);
		}
		out.println(jsonArray.toString());
		out.flush();
		out.close();
	}

	/*前台按照查询条件分页查询籍贯信息*/
	@RequestMapping(value = { "/frontlist" }, method = {RequestMethod.GET,RequestMethod.POST})
	public String frontlist(Integer currentPage, Model model, HttpServletRequest request) throws Exception  {
		if (currentPage==null || currentPage == 0) currentPage = 1;
		List<NativePlace> nativePlaceList = nativePlaceService.queryNativePlace(currentPage);
	    /*计算总的页数和总的记录数*/
	    nativePlaceService.queryTotalPageAndRecordNumber();
	    /*获取到总的页码数目*/
	    int totalPage = nativePlaceService.getTotalPage();
	    /*当前查询条件下总记录数*/
	    int recordNumber = nativePlaceService.getRecordNumber();
	    request.setAttribute("nativePlaceList",  nativePlaceList);
	    request.setAttribute("totalPage", totalPage);
	    request.setAttribute("recordNumber", recordNumber);
	    request.setAttribute("currentPage", currentPage);
		return "NativePlace/nativePlace_frontquery_result"; 
	}

     /*前台查询NativePlace信息*/
	@RequestMapping(value="/{placeId}/frontshow",method=RequestMethod.GET)
	public String frontshow(@PathVariable Integer placeId,Model model,HttpServletRequest request) throws Exception {
		/*根据主键placeId获取NativePlace对象*/
        NativePlace nativePlace = nativePlaceService.getNativePlace(placeId);

        request.setAttribute("nativePlace",  nativePlace);
        return "NativePlace/nativePlace_frontshow";
	}

	/*ajax方式显示籍贯修改jsp视图页*/
	@RequestMapping(value="/{placeId}/update",method=RequestMethod.GET)
	public void update(@PathVariable Integer placeId,Model model,HttpServletRequest request,HttpServletResponse response) throws Exception {
        /*根据主键placeId获取NativePlace对象*/
        NativePlace nativePlace = nativePlaceService.getNativePlace(placeId);

        response.setContentType("text/json;charset=UTF-8");
        PrintWriter out = response.getWriter();
		//将要被返回到客户端的对象 
		JSONObject jsonNativePlace = nativePlace.getJsonObject();
		out.println(jsonNativePlace.toString());
		out.flush();
		out.close();
	}

	/*ajax方式更新籍贯信息*/
	@RequestMapping(value = "/{placeId}/update", method = RequestMethod.POST)
	public void update(@Validated NativePlace nativePlace, BindingResult br,
			Model model, HttpServletRequest request,HttpServletResponse response) throws Exception {
		String message = "";
    	boolean success = false;
		if (br.hasErrors()) { 
			message = "输入的信息有错误！";
			writeJsonResponse(response, success, message);
			return;
		}
		try {
			nativePlaceService.updateNativePlace(nativePlace);
			message = "籍贯更新成功!";
			success = true;
			writeJsonResponse(response, success, message);
		} catch (Exception e) {
			e.printStackTrace();
			message = "籍贯更新失败!";
			writeJsonResponse(response, success, message); 
		}
	}
    /*删除籍贯信息*/
	@RequestMapping(value="/{placeId}/delete",method=RequestMethod.GET)
	public String delete(@PathVariable Integer placeId,HttpServletRequest request) throws UnsupportedEncodingException {
		  try {
			  nativePlaceService.deleteNativePlace(placeId);
	            request.setAttribute("message", "籍贯删除成功!");
	            return "message";
	        } catch (Exception e) { 
	            e.printStackTrace();
	            request.setAttribute("error", "籍贯删除失败!");
				return "error";

	        }

	}

	/*ajax方式删除多条籍贯记录*/
	@RequestMapping(value="/deletes",method=RequestMethod.POST)
	public void delete(String placeIds,HttpServletRequest request,HttpServletResponse response) throws IOException, JSONException {
		String message = "";
    	boolean success = false;
        try { 
        	int count = nativePlaceService.deleteNativePlaces(placeIds);
        	success = true;
        	message = count + "条记录删除成功";
        	writeJsonResponse(response, success, message);
        } catch (Exception e) { 
            //e.printStackTrace();
            message = "有记录存在外键约束,删除失败";
            writeJsonResponse(response, success, message);
        }
	}

	/*按照查询条件导出籍贯信息到Excel*/
	@RequestMapping(value = { "/OutToExcel" }, method = {RequestMethod.GET,RequestMethod.POST})
	public void OutToExcel( Model model, HttpServletRequest request,HttpServletResponse response) throws Exception {
        List<NativePlace> nativePlaceList = nativePlaceService.queryNativePlace();
        ExportExcelUtil ex = new ExportExcelUtil();
        String _title = "NativePlace信息记录"; 
        String[] headers = { "籍贯id","籍贯名称"};
        List<String[]> dataset = new ArrayList<String[]>(); 
        for(int i=0;i<nativePlaceList.size();i++) {
        	NativePlace nativePlace = nativePlaceList.get(i); 
        	dataset.add(new String[]{nativePlace.getPlaceId() + "",nativePlace.getPlaceName()});
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
			response.setHeader("Content-disposition","attachment; filename="+"NativePlace.xls");//filename是下载的xls的名，建议最好用英文 
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

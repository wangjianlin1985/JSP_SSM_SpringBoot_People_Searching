package com.chengxusheji.service;

import java.util.ArrayList;
import javax.annotation.Resource; 
import org.springframework.stereotype.Service;
import com.chengxusheji.po.NativePlace;

import com.chengxusheji.mapper.NativePlaceMapper;
@Service
public class NativePlaceService {

	@Resource NativePlaceMapper nativePlaceMapper;
    /*每页显示记录数目*/
    private int rows = 10;;
    public int getRows() {
		return rows;
	}
	public void setRows(int rows) {
		this.rows = rows;
	}

    /*保存查询后总的页数*/
    private int totalPage;
    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }
    public int getTotalPage() {
        return totalPage;
    }

    /*保存查询到的总记录数*/
    private int recordNumber;
    public void setRecordNumber(int recordNumber) {
        this.recordNumber = recordNumber;
    }
    public int getRecordNumber() {
        return recordNumber;
    }

    /*添加籍贯记录*/
    public void addNativePlace(NativePlace nativePlace) throws Exception {
    	nativePlaceMapper.addNativePlace(nativePlace);
    }

    /*按照查询条件分页查询籍贯记录*/
    public ArrayList<NativePlace> queryNativePlace(int currentPage) throws Exception { 
     	String where = "where 1=1";
    	int startIndex = (currentPage-1) * this.rows;
    	return nativePlaceMapper.queryNativePlace(where, startIndex, this.rows);
    }

    /*按照查询条件查询所有记录*/
    public ArrayList<NativePlace> queryNativePlace() throws Exception  { 
     	String where = "where 1=1";
    	return nativePlaceMapper.queryNativePlaceList(where);
    }

    /*查询所有籍贯记录*/
    public ArrayList<NativePlace> queryAllNativePlace()  throws Exception {
        return nativePlaceMapper.queryNativePlaceList("where 1=1");
    }

    /*当前查询条件下计算总的页数和记录数*/
    public void queryTotalPageAndRecordNumber() throws Exception {
     	String where = "where 1=1";
        recordNumber = nativePlaceMapper.queryNativePlaceCount(where);
        int mod = recordNumber % this.rows;
        totalPage = recordNumber / this.rows;
        if(mod != 0) totalPage++;
    }

    /*根据主键获取籍贯记录*/
    public NativePlace getNativePlace(int placeId) throws Exception  {
        NativePlace nativePlace = nativePlaceMapper.getNativePlace(placeId);
        return nativePlace;
    }

    /*更新籍贯记录*/
    public void updateNativePlace(NativePlace nativePlace) throws Exception {
        nativePlaceMapper.updateNativePlace(nativePlace);
    }

    /*删除一条籍贯记录*/
    public void deleteNativePlace (int placeId) throws Exception {
        nativePlaceMapper.deleteNativePlace(placeId);
    }

    /*删除多条籍贯信息*/
    public int deleteNativePlaces (String placeIds) throws Exception {
    	String _placeIds[] = placeIds.split(",");
    	for(String _placeId: _placeIds) {
    		nativePlaceMapper.deleteNativePlace(Integer.parseInt(_placeId));
    	}
    	return _placeIds.length;
    }
}

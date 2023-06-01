package com.chengxusheji.service;

import java.util.ArrayList;
import javax.annotation.Resource; 
import org.springframework.stereotype.Service;
import com.chengxusheji.po.LostType;

import com.chengxusheji.mapper.LostTypeMapper;
@Service
public class LostTypeService {

	@Resource LostTypeMapper lostTypeMapper;
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

    /*添加失踪类型记录*/
    public void addLostType(LostType lostType) throws Exception {
    	lostTypeMapper.addLostType(lostType);
    }

    /*按照查询条件分页查询失踪类型记录*/
    public ArrayList<LostType> queryLostType(int currentPage) throws Exception { 
     	String where = "where 1=1";
    	int startIndex = (currentPage-1) * this.rows;
    	return lostTypeMapper.queryLostType(where, startIndex, this.rows);
    }

    /*按照查询条件查询所有记录*/
    public ArrayList<LostType> queryLostType() throws Exception  { 
     	String where = "where 1=1";
    	return lostTypeMapper.queryLostTypeList(where);
    }

    /*查询所有失踪类型记录*/
    public ArrayList<LostType> queryAllLostType()  throws Exception {
        return lostTypeMapper.queryLostTypeList("where 1=1");
    }

    /*当前查询条件下计算总的页数和记录数*/
    public void queryTotalPageAndRecordNumber() throws Exception {
     	String where = "where 1=1";
        recordNumber = lostTypeMapper.queryLostTypeCount(where);
        int mod = recordNumber % this.rows;
        totalPage = recordNumber / this.rows;
        if(mod != 0) totalPage++;
    }

    /*根据主键获取失踪类型记录*/
    public LostType getLostType(int lostTypeId) throws Exception  {
        LostType lostType = lostTypeMapper.getLostType(lostTypeId);
        return lostType;
    }

    /*更新失踪类型记录*/
    public void updateLostType(LostType lostType) throws Exception {
        lostTypeMapper.updateLostType(lostType);
    }

    /*删除一条失踪类型记录*/
    public void deleteLostType (int lostTypeId) throws Exception {
        lostTypeMapper.deleteLostType(lostTypeId);
    }

    /*删除多条失踪类型信息*/
    public int deleteLostTypes (String lostTypeIds) throws Exception {
    	String _lostTypeIds[] = lostTypeIds.split(",");
    	for(String _lostTypeId: _lostTypeIds) {
    		lostTypeMapper.deleteLostType(Integer.parseInt(_lostTypeId));
    	}
    	return _lostTypeIds.length;
    }
}

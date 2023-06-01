package com.chengxusheji.service;

import java.util.ArrayList;
import javax.annotation.Resource; 
import org.springframework.stereotype.Service;
import com.chengxusheji.po.FindType;

import com.chengxusheji.mapper.FindTypeMapper;
@Service
public class FindTypeService {

	@Resource FindTypeMapper findTypeMapper;
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

    /*添加寻找类型记录*/
    public void addFindType(FindType findType) throws Exception {
    	findTypeMapper.addFindType(findType);
    }

    /*按照查询条件分页查询寻找类型记录*/
    public ArrayList<FindType> queryFindType(int currentPage) throws Exception { 
     	String where = "where 1=1";
    	int startIndex = (currentPage-1) * this.rows;
    	return findTypeMapper.queryFindType(where, startIndex, this.rows);
    }

    /*按照查询条件查询所有记录*/
    public ArrayList<FindType> queryFindType() throws Exception  { 
     	String where = "where 1=1";
    	return findTypeMapper.queryFindTypeList(where);
    }

    /*查询所有寻找类型记录*/
    public ArrayList<FindType> queryAllFindType()  throws Exception {
        return findTypeMapper.queryFindTypeList("where 1=1");
    }

    /*当前查询条件下计算总的页数和记录数*/
    public void queryTotalPageAndRecordNumber() throws Exception {
     	String where = "where 1=1";
        recordNumber = findTypeMapper.queryFindTypeCount(where);
        int mod = recordNumber % this.rows;
        totalPage = recordNumber / this.rows;
        if(mod != 0) totalPage++;
    }

    /*根据主键获取寻找类型记录*/
    public FindType getFindType(int findTypeId) throws Exception  {
        FindType findType = findTypeMapper.getFindType(findTypeId);
        return findType;
    }

    /*更新寻找类型记录*/
    public void updateFindType(FindType findType) throws Exception {
        findTypeMapper.updateFindType(findType);
    }

    /*删除一条寻找类型记录*/
    public void deleteFindType (int findTypeId) throws Exception {
        findTypeMapper.deleteFindType(findTypeId);
    }

    /*删除多条寻找类型信息*/
    public int deleteFindTypes (String findTypeIds) throws Exception {
    	String _findTypeIds[] = findTypeIds.split(",");
    	for(String _findTypeId: _findTypeIds) {
    		findTypeMapper.deleteFindType(Integer.parseInt(_findTypeId));
    	}
    	return _findTypeIds.length;
    }
}

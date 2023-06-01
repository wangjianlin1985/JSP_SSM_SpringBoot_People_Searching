package com.chengxusheji.service;

import java.util.ArrayList;
import javax.annotation.Resource; 
import org.springframework.stereotype.Service;
import com.chengxusheji.po.NativePlace;
import com.chengxusheji.po.FindType;
import com.chengxusheji.po.LostType;
import com.chengxusheji.po.UserInfo;
import com.chengxusheji.po.FindPerson;

import com.chengxusheji.mapper.FindPersonMapper;
@Service
public class FindPersonService {

	@Resource FindPersonMapper findPersonMapper;
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

    /*添加寻人信息记录*/
    public void addFindPerson(FindPerson findPerson) throws Exception {
    	findPersonMapper.addFindPerson(findPerson);
    }

    /*按照查询条件分页查询寻人信息记录*/
    public ArrayList<FindPerson> queryFindPerson(String personName,String sex,NativePlace nativePlaceObj,FindType findTypeObj,LostType lostTypeObj,String bornDate,String lostDate,UserInfo userObj,String addTime,int currentPage) throws Exception { 
     	String where = "where 1=1";
    	if(!personName.equals("")) where = where + " and t_findPerson.personName like '%" + personName + "%'";
    	if(!sex.equals("")) where = where + " and t_findPerson.sex like '%" + sex + "%'";
    	if(null != nativePlaceObj && nativePlaceObj.getPlaceId()!= null && nativePlaceObj.getPlaceId()!= 0)  where += " and t_findPerson.nativePlaceObj=" + nativePlaceObj.getPlaceId();
    	if(null != findTypeObj && findTypeObj.getFindTypeId()!= null && findTypeObj.getFindTypeId()!= 0)  where += " and t_findPerson.findTypeObj=" + findTypeObj.getFindTypeId();
    	if(null != lostTypeObj && lostTypeObj.getLostTypeId()!= null && lostTypeObj.getLostTypeId()!= 0)  where += " and t_findPerson.lostTypeObj=" + lostTypeObj.getLostTypeId();
    	if(!bornDate.equals("")) where = where + " and t_findPerson.bornDate like '%" + bornDate + "%'";
    	if(!lostDate.equals("")) where = where + " and t_findPerson.lostDate like '%" + lostDate + "%'";
    	if(null != userObj &&  userObj.getUser_name() != null  && !userObj.getUser_name().equals(""))  where += " and t_findPerson.userObj='" + userObj.getUser_name() + "'";
    	if(!addTime.equals("")) where = where + " and t_findPerson.addTime like '%" + addTime + "%'";
    	int startIndex = (currentPage-1) * this.rows;
    	return findPersonMapper.queryFindPerson(where, startIndex, this.rows);
    }

    /*按照查询条件查询所有记录*/
    public ArrayList<FindPerson> queryFindPerson(String personName,String sex,NativePlace nativePlaceObj,FindType findTypeObj,LostType lostTypeObj,String bornDate,String lostDate,UserInfo userObj,String addTime) throws Exception  { 
     	String where = "where 1=1";
    	if(!personName.equals("")) where = where + " and t_findPerson.personName like '%" + personName + "%'";
    	if(!sex.equals("")) where = where + " and t_findPerson.sex like '%" + sex + "%'";
    	if(null != nativePlaceObj && nativePlaceObj.getPlaceId()!= null && nativePlaceObj.getPlaceId()!= 0)  where += " and t_findPerson.nativePlaceObj=" + nativePlaceObj.getPlaceId();
    	if(null != findTypeObj && findTypeObj.getFindTypeId()!= null && findTypeObj.getFindTypeId()!= 0)  where += " and t_findPerson.findTypeObj=" + findTypeObj.getFindTypeId();
    	if(null != lostTypeObj && lostTypeObj.getLostTypeId()!= null && lostTypeObj.getLostTypeId()!= 0)  where += " and t_findPerson.lostTypeObj=" + lostTypeObj.getLostTypeId();
    	if(!bornDate.equals("")) where = where + " and t_findPerson.bornDate like '%" + bornDate + "%'";
    	if(!lostDate.equals("")) where = where + " and t_findPerson.lostDate like '%" + lostDate + "%'";
    	if(null != userObj &&  userObj.getUser_name() != null && !userObj.getUser_name().equals(""))  where += " and t_findPerson.userObj='" + userObj.getUser_name() + "'";
    	if(!addTime.equals("")) where = where + " and t_findPerson.addTime like '%" + addTime + "%'";
    	return findPersonMapper.queryFindPersonList(where);
    }

    /*查询所有寻人信息记录*/
    public ArrayList<FindPerson> queryAllFindPerson()  throws Exception {
        return findPersonMapper.queryFindPersonList("where 1=1");
    }

    /*当前查询条件下计算总的页数和记录数*/
    public void queryTotalPageAndRecordNumber(String personName,String sex,NativePlace nativePlaceObj,FindType findTypeObj,LostType lostTypeObj,String bornDate,String lostDate,UserInfo userObj,String addTime) throws Exception {
     	String where = "where 1=1";
    	if(!personName.equals("")) where = where + " and t_findPerson.personName like '%" + personName + "%'";
    	if(!sex.equals("")) where = where + " and t_findPerson.sex like '%" + sex + "%'";
    	if(null != nativePlaceObj && nativePlaceObj.getPlaceId()!= null && nativePlaceObj.getPlaceId()!= 0)  where += " and t_findPerson.nativePlaceObj=" + nativePlaceObj.getPlaceId();
    	if(null != findTypeObj && findTypeObj.getFindTypeId()!= null && findTypeObj.getFindTypeId()!= 0)  where += " and t_findPerson.findTypeObj=" + findTypeObj.getFindTypeId();
    	if(null != lostTypeObj && lostTypeObj.getLostTypeId()!= null && lostTypeObj.getLostTypeId()!= 0)  where += " and t_findPerson.lostTypeObj=" + lostTypeObj.getLostTypeId();
    	if(!bornDate.equals("")) where = where + " and t_findPerson.bornDate like '%" + bornDate + "%'";
    	if(!lostDate.equals("")) where = where + " and t_findPerson.lostDate like '%" + lostDate + "%'";
    	if(null != userObj &&  userObj.getUser_name() != null && !userObj.getUser_name().equals(""))  where += " and t_findPerson.userObj='" + userObj.getUser_name() + "'";
    	if(!addTime.equals("")) where = where + " and t_findPerson.addTime like '%" + addTime + "%'";
        recordNumber = findPersonMapper.queryFindPersonCount(where);
        int mod = recordNumber % this.rows;
        totalPage = recordNumber / this.rows;
        if(mod != 0) totalPage++;
    }

    /*根据主键获取寻人信息记录*/
    public FindPerson getFindPerson(int findPersonId) throws Exception  {
        FindPerson findPerson = findPersonMapper.getFindPerson(findPersonId);
        return findPerson;
    }

    /*更新寻人信息记录*/
    public void updateFindPerson(FindPerson findPerson) throws Exception {
        findPersonMapper.updateFindPerson(findPerson);
    }

    /*删除一条寻人信息记录*/
    public void deleteFindPerson (int findPersonId) throws Exception {
        findPersonMapper.deleteFindPerson(findPersonId);
    }

    /*删除多条寻人信息信息*/
    public int deleteFindPersons (String findPersonIds) throws Exception {
    	String _findPersonIds[] = findPersonIds.split(",");
    	for(String _findPersonId: _findPersonIds) {
    		findPersonMapper.deleteFindPerson(Integer.parseInt(_findPersonId));
    	}
    	return _findPersonIds.length;
    }
}

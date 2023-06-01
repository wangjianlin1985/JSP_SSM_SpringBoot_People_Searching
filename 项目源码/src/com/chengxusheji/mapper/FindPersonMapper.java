package com.chengxusheji.mapper;

import java.util.ArrayList;
import org.apache.ibatis.annotations.Param;
import com.chengxusheji.po.FindPerson;

public interface FindPersonMapper {
	/*添加寻人信息信息*/
	public void addFindPerson(FindPerson findPerson) throws Exception;

	/*按照查询条件分页查询寻人信息记录*/
	public ArrayList<FindPerson> queryFindPerson(@Param("where") String where,@Param("startIndex") int startIndex,@Param("pageSize") int pageSize) throws Exception;

	/*按照查询条件查询所有寻人信息记录*/
	public ArrayList<FindPerson> queryFindPersonList(@Param("where") String where) throws Exception;

	/*按照查询条件的寻人信息记录数*/
	public int queryFindPersonCount(@Param("where") String where) throws Exception; 

	/*根据主键查询某条寻人信息记录*/
	public FindPerson getFindPerson(int findPersonId) throws Exception;

	/*更新寻人信息记录*/
	public void updateFindPerson(FindPerson findPerson) throws Exception;

	/*删除寻人信息记录*/
	public void deleteFindPerson(int findPersonId) throws Exception;

}

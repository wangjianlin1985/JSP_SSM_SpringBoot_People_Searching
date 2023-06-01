package com.chengxusheji.mapper;

import java.util.ArrayList;
import org.apache.ibatis.annotations.Param;
import com.chengxusheji.po.FindType;

public interface FindTypeMapper {
	/*添加寻找类型信息*/
	public void addFindType(FindType findType) throws Exception;

	/*按照查询条件分页查询寻找类型记录*/
	public ArrayList<FindType> queryFindType(@Param("where") String where,@Param("startIndex") int startIndex,@Param("pageSize") int pageSize) throws Exception;

	/*按照查询条件查询所有寻找类型记录*/
	public ArrayList<FindType> queryFindTypeList(@Param("where") String where) throws Exception;

	/*按照查询条件的寻找类型记录数*/
	public int queryFindTypeCount(@Param("where") String where) throws Exception; 

	/*根据主键查询某条寻找类型记录*/
	public FindType getFindType(int findTypeId) throws Exception;

	/*更新寻找类型记录*/
	public void updateFindType(FindType findType) throws Exception;

	/*删除寻找类型记录*/
	public void deleteFindType(int findTypeId) throws Exception;

}

package com.chengxusheji.mapper;

import java.util.ArrayList;
import org.apache.ibatis.annotations.Param;
import com.chengxusheji.po.LostType;

public interface LostTypeMapper {
	/*添加失踪类型信息*/
	public void addLostType(LostType lostType) throws Exception;

	/*按照查询条件分页查询失踪类型记录*/
	public ArrayList<LostType> queryLostType(@Param("where") String where,@Param("startIndex") int startIndex,@Param("pageSize") int pageSize) throws Exception;

	/*按照查询条件查询所有失踪类型记录*/
	public ArrayList<LostType> queryLostTypeList(@Param("where") String where) throws Exception;

	/*按照查询条件的失踪类型记录数*/
	public int queryLostTypeCount(@Param("where") String where) throws Exception; 

	/*根据主键查询某条失踪类型记录*/
	public LostType getLostType(int lostTypeId) throws Exception;

	/*更新失踪类型记录*/
	public void updateLostType(LostType lostType) throws Exception;

	/*删除失踪类型记录*/
	public void deleteLostType(int lostTypeId) throws Exception;

}

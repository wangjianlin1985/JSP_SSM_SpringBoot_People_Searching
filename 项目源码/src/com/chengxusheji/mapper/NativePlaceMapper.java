package com.chengxusheji.mapper;

import java.util.ArrayList;
import org.apache.ibatis.annotations.Param;
import com.chengxusheji.po.NativePlace;

public interface NativePlaceMapper {
	/*添加籍贯信息*/
	public void addNativePlace(NativePlace nativePlace) throws Exception;

	/*按照查询条件分页查询籍贯记录*/
	public ArrayList<NativePlace> queryNativePlace(@Param("where") String where,@Param("startIndex") int startIndex,@Param("pageSize") int pageSize) throws Exception;

	/*按照查询条件查询所有籍贯记录*/
	public ArrayList<NativePlace> queryNativePlaceList(@Param("where") String where) throws Exception;

	/*按照查询条件的籍贯记录数*/
	public int queryNativePlaceCount(@Param("where") String where) throws Exception; 

	/*根据主键查询某条籍贯记录*/
	public NativePlace getNativePlace(int placeId) throws Exception;

	/*更新籍贯记录*/
	public void updateNativePlace(NativePlace nativePlace) throws Exception;

	/*删除籍贯记录*/
	public void deleteNativePlace(int placeId) throws Exception;

}

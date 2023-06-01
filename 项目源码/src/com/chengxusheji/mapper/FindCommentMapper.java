package com.chengxusheji.mapper;

import java.util.ArrayList;
import org.apache.ibatis.annotations.Param;
import com.chengxusheji.po.FindComment;

public interface FindCommentMapper {
	/*添加评论信息*/
	public void addFindComment(FindComment findComment) throws Exception;

	/*按照查询条件分页查询评论记录*/
	public ArrayList<FindComment> queryFindComment(@Param("where") String where,@Param("startIndex") int startIndex,@Param("pageSize") int pageSize) throws Exception;

	/*按照查询条件查询所有评论记录*/
	public ArrayList<FindComment> queryFindCommentList(@Param("where") String where) throws Exception;

	/*按照查询条件的评论记录数*/
	public int queryFindCommentCount(@Param("where") String where) throws Exception; 

	/*根据主键查询某条评论记录*/
	public FindComment getFindComment(int commentId) throws Exception;

	/*更新评论记录*/
	public void updateFindComment(FindComment findComment) throws Exception;

	/*删除评论记录*/
	public void deleteFindComment(int commentId) throws Exception;

}

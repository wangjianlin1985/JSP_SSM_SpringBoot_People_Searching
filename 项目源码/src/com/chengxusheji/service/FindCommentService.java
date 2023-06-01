package com.chengxusheji.service;

import java.util.ArrayList;
import javax.annotation.Resource; 
import org.springframework.stereotype.Service;
import com.chengxusheji.po.FindPerson;
import com.chengxusheji.po.UserInfo;
import com.chengxusheji.po.FindComment;

import com.chengxusheji.mapper.FindCommentMapper;
@Service
public class FindCommentService {

	@Resource FindCommentMapper findCommentMapper;
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

    /*添加评论记录*/
    public void addFindComment(FindComment findComment) throws Exception {
    	findCommentMapper.addFindComment(findComment);
    }

    /*按照查询条件分页查询评论记录*/
    public ArrayList<FindComment> queryFindComment(FindPerson findPersonObj,UserInfo userObj,String commentTime,int currentPage) throws Exception { 
     	String where = "where 1=1";
    	if(null != findPersonObj && findPersonObj.getFindPersonId()!= null && findPersonObj.getFindPersonId()!= 0)  where += " and t_findComment.findPersonObj=" + findPersonObj.getFindPersonId();
    	if(null != userObj &&  userObj.getUser_name() != null  && !userObj.getUser_name().equals(""))  where += " and t_findComment.userObj='" + userObj.getUser_name() + "'";
    	if(!commentTime.equals("")) where = where + " and t_findComment.commentTime like '%" + commentTime + "%'";
    	int startIndex = (currentPage-1) * this.rows;
    	return findCommentMapper.queryFindComment(where, startIndex, this.rows);
    }

    /*按照查询条件查询所有记录*/
    public ArrayList<FindComment> queryFindComment(FindPerson findPersonObj,UserInfo userObj,String commentTime) throws Exception  { 
     	String where = "where 1=1";
    	if(null != findPersonObj && findPersonObj.getFindPersonId()!= null && findPersonObj.getFindPersonId()!= 0)  where += " and t_findComment.findPersonObj=" + findPersonObj.getFindPersonId();
    	if(null != userObj &&  userObj.getUser_name() != null && !userObj.getUser_name().equals(""))  where += " and t_findComment.userObj='" + userObj.getUser_name() + "'";
    	if(!commentTime.equals("")) where = where + " and t_findComment.commentTime like '%" + commentTime + "%'";
    	return findCommentMapper.queryFindCommentList(where);
    }

    /*查询所有评论记录*/
    public ArrayList<FindComment> queryAllFindComment()  throws Exception {
        return findCommentMapper.queryFindCommentList("where 1=1");
    }

    /*当前查询条件下计算总的页数和记录数*/
    public void queryTotalPageAndRecordNumber(FindPerson findPersonObj,UserInfo userObj,String commentTime) throws Exception {
     	String where = "where 1=1";
    	if(null != findPersonObj && findPersonObj.getFindPersonId()!= null && findPersonObj.getFindPersonId()!= 0)  where += " and t_findComment.findPersonObj=" + findPersonObj.getFindPersonId();
    	if(null != userObj &&  userObj.getUser_name() != null && !userObj.getUser_name().equals(""))  where += " and t_findComment.userObj='" + userObj.getUser_name() + "'";
    	if(!commentTime.equals("")) where = where + " and t_findComment.commentTime like '%" + commentTime + "%'";
        recordNumber = findCommentMapper.queryFindCommentCount(where);
        int mod = recordNumber % this.rows;
        totalPage = recordNumber / this.rows;
        if(mod != 0) totalPage++;
    }

    /*根据主键获取评论记录*/
    public FindComment getFindComment(int commentId) throws Exception  {
        FindComment findComment = findCommentMapper.getFindComment(commentId);
        return findComment;
    }

    /*更新评论记录*/
    public void updateFindComment(FindComment findComment) throws Exception {
        findCommentMapper.updateFindComment(findComment);
    }

    /*删除一条评论记录*/
    public void deleteFindComment (int commentId) throws Exception {
        findCommentMapper.deleteFindComment(commentId);
    }

    /*删除多条评论信息*/
    public int deleteFindComments (String commentIds) throws Exception {
    	String _commentIds[] = commentIds.split(",");
    	for(String _commentId: _commentIds) {
    		findCommentMapper.deleteFindComment(Integer.parseInt(_commentId));
    	}
    	return _commentIds.length;
    }
}

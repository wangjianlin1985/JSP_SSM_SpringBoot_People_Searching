package com.chengxusheji.po;

import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.NotEmpty;
import org.json.JSONException;
import org.json.JSONObject;

public class FindComment {
    /*评论id*/
    private Integer commentId;
    public Integer getCommentId(){
        return commentId;
    }
    public void setCommentId(Integer commentId){
        this.commentId = commentId;
    }

    /*被评寻人信息*/
    private FindPerson findPersonObj;
    public FindPerson getFindPersonObj() {
        return findPersonObj;
    }
    public void setFindPersonObj(FindPerson findPersonObj) {
        this.findPersonObj = findPersonObj;
    }

    /*评论内容*/
    @NotEmpty(message="评论内容不能为空")
    private String content;
    public String getContent() {
        return content;
    }
    public void setContent(String content) {
        this.content = content;
    }

    /*评论用户*/
    private UserInfo userObj;
    public UserInfo getUserObj() {
        return userObj;
    }
    public void setUserObj(UserInfo userObj) {
        this.userObj = userObj;
    }

    /*评论时间*/
    private String commentTime;
    public String getCommentTime() {
        return commentTime;
    }
    public void setCommentTime(String commentTime) {
        this.commentTime = commentTime;
    }

    public JSONObject getJsonObject() throws JSONException {
    	JSONObject jsonFindComment=new JSONObject(); 
		jsonFindComment.accumulate("commentId", this.getCommentId());
		jsonFindComment.accumulate("findPersonObj", this.getFindPersonObj().getPersonName());
		jsonFindComment.accumulate("findPersonObjPri", this.getFindPersonObj().getFindPersonId());
		jsonFindComment.accumulate("content", this.getContent());
		jsonFindComment.accumulate("userObj", this.getUserObj().getName());
		jsonFindComment.accumulate("userObjPri", this.getUserObj().getUser_name());
		jsonFindComment.accumulate("commentTime", this.getCommentTime());
		return jsonFindComment;
    }}
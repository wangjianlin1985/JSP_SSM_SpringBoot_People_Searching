package com.chengxusheji.po;

import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.NotEmpty;
import org.json.JSONException;
import org.json.JSONObject;

public class FindType {
    /*寻找类型id*/
    private Integer findTypeId;
    public Integer getFindTypeId(){
        return findTypeId;
    }
    public void setFindTypeId(Integer findTypeId){
        this.findTypeId = findTypeId;
    }

    /*寻找类型名称*/
    @NotEmpty(message="寻找类型名称不能为空")
    private String findTypeName;
    public String getFindTypeName() {
        return findTypeName;
    }
    public void setFindTypeName(String findTypeName) {
        this.findTypeName = findTypeName;
    }

    public JSONObject getJsonObject() throws JSONException {
    	JSONObject jsonFindType=new JSONObject(); 
		jsonFindType.accumulate("findTypeId", this.getFindTypeId());
		jsonFindType.accumulate("findTypeName", this.getFindTypeName());
		return jsonFindType;
    }}
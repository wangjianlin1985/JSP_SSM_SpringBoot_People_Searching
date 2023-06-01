package com.chengxusheji.po;

import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.NotEmpty;
import org.json.JSONException;
import org.json.JSONObject;

public class LostType {
    /*丢失类型id*/
    private Integer lostTypeId;
    public Integer getLostTypeId(){
        return lostTypeId;
    }
    public void setLostTypeId(Integer lostTypeId){
        this.lostTypeId = lostTypeId;
    }

    /*丢失类型名称*/
    @NotEmpty(message="丢失类型名称不能为空")
    private String lostTypeName;
    public String getLostTypeName() {
        return lostTypeName;
    }
    public void setLostTypeName(String lostTypeName) {
        this.lostTypeName = lostTypeName;
    }

    public JSONObject getJsonObject() throws JSONException {
    	JSONObject jsonLostType=new JSONObject(); 
		jsonLostType.accumulate("lostTypeId", this.getLostTypeId());
		jsonLostType.accumulate("lostTypeName", this.getLostTypeName());
		return jsonLostType;
    }}
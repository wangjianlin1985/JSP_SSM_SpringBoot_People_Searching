package com.chengxusheji.po;

import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.NotEmpty;
import org.json.JSONException;
import org.json.JSONObject;

public class NativePlace {
    /*籍贯id*/
    private Integer placeId;
    public Integer getPlaceId(){
        return placeId;
    }
    public void setPlaceId(Integer placeId){
        this.placeId = placeId;
    }

    /*籍贯名称*/
    @NotEmpty(message="籍贯名称不能为空")
    private String placeName;
    public String getPlaceName() {
        return placeName;
    }
    public void setPlaceName(String placeName) {
        this.placeName = placeName;
    }

    public JSONObject getJsonObject() throws JSONException {
    	JSONObject jsonNativePlace=new JSONObject(); 
		jsonNativePlace.accumulate("placeId", this.getPlaceId());
		jsonNativePlace.accumulate("placeName", this.getPlaceName());
		return jsonNativePlace;
    }}
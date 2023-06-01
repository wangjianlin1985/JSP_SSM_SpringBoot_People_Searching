package com.chengxusheji.po;

import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.NotEmpty;
import org.json.JSONException;
import org.json.JSONObject;

public class FindPerson {
    /*寻人id*/
    private Integer findPersonId;
    public Integer getFindPersonId(){
        return findPersonId;
    }
    public void setFindPersonId(Integer findPersonId){
        this.findPersonId = findPersonId;
    }

    /*被寻人姓名*/
    @NotEmpty(message="被寻人姓名不能为空")
    private String personName;
    public String getPersonName() {
        return personName;
    }
    public void setPersonName(String personName) {
        this.personName = personName;
    }

    /*性别*/
    @NotEmpty(message="性别不能为空")
    private String sex;
    public String getSex() {
        return sex;
    }
    public void setSex(String sex) {
        this.sex = sex;
    }

    /*被寻人照片*/
    private String personPhoto;
    public String getPersonPhoto() {
        return personPhoto;
    }
    public void setPersonPhoto(String personPhoto) {
        this.personPhoto = personPhoto;
    }

    /*血型*/
    @NotEmpty(message="血型不能为空")
    private String bloodType;
    public String getBloodType() {
        return bloodType;
    }
    public void setBloodType(String bloodType) {
        this.bloodType = bloodType;
    }

    /*籍贯*/
    private NativePlace nativePlaceObj;
    public NativePlace getNativePlaceObj() {
        return nativePlaceObj;
    }
    public void setNativePlaceObj(NativePlace nativePlaceObj) {
        this.nativePlaceObj = nativePlaceObj;
    }

    /*寻找类型*/
    private FindType findTypeObj;
    public FindType getFindTypeObj() {
        return findTypeObj;
    }
    public void setFindTypeObj(FindType findTypeObj) {
        this.findTypeObj = findTypeObj;
    }

    /*失踪类型*/
    private LostType lostTypeObj;
    public LostType getLostTypeObj() {
        return lostTypeObj;
    }
    public void setLostTypeObj(LostType lostTypeObj) {
        this.lostTypeObj = lostTypeObj;
    }

    /*出生日期*/
    @NotEmpty(message="出生日期不能为空")
    private String bornDate;
    public String getBornDate() {
        return bornDate;
    }
    public void setBornDate(String bornDate) {
        this.bornDate = bornDate;
    }

    /*失踪日期*/
    @NotEmpty(message="失踪日期不能为空")
    private String lostDate;
    public String getLostDate() {
        return lostDate;
    }
    public void setLostDate(String lostDate) {
        this.lostDate = lostDate;
    }

    /*失踪时身高*/
    @NotEmpty(message="失踪时身高不能为空")
    private String lostHeight;
    public String getLostHeight() {
        return lostHeight;
    }
    public void setLostHeight(String lostHeight) {
        this.lostHeight = lostHeight;
    }

    /*失踪地点*/
    @NotEmpty(message="失踪地点不能为空")
    private String lostPlace;
    public String getLostPlace() {
        return lostPlace;
    }
    public void setLostPlace(String lostPlace) {
        this.lostPlace = lostPlace;
    }

    /*相貌特征描述*/
    @NotEmpty(message="相貌特征描述不能为空")
    private String personDesc;
    public String getPersonDesc() {
        return personDesc;
    }
    public void setPersonDesc(String personDesc) {
        this.personDesc = personDesc;
    }

    /*联系人*/
    @NotEmpty(message="联系人不能为空")
    private String connectPerson;
    public String getConnectPerson() {
        return connectPerson;
    }
    public void setConnectPerson(String connectPerson) {
        this.connectPerson = connectPerson;
    }

    /*联系电话*/
    @NotEmpty(message="联系电话不能为空")
    private String connectPhone;
    public String getConnectPhone() {
        return connectPhone;
    }
    public void setConnectPhone(String connectPhone) {
        this.connectPhone = connectPhone;
    }

    /*电子邮箱*/
    private String email;
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    /*联系地址*/
    private String connectAddress;
    public String getConnectAddress() {
        return connectAddress;
    }
    public void setConnectAddress(String connectAddress) {
        this.connectAddress = connectAddress;
    }

    /*邮政编码*/
    private String postcode;
    public String getPostcode() {
        return postcode;
    }
    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }

    /*其他联系方式*/
    private String otherWay;
    public String getOtherWay() {
        return otherWay;
    }
    public void setOtherWay(String otherWay) {
        this.otherWay = otherWay;
    }

    /*备注*/
    private String memo;
    public String getMemo() {
        return memo;
    }
    public void setMemo(String memo) {
        this.memo = memo;
    }

    /*发布用户*/
    private UserInfo userObj;
    public UserInfo getUserObj() {
        return userObj;
    }
    public void setUserObj(UserInfo userObj) {
        this.userObj = userObj;
    }

    /*发布时间*/
    @NotEmpty(message="发布时间不能为空")
    private String addTime;
    public String getAddTime() {
        return addTime;
    }
    public void setAddTime(String addTime) {
        this.addTime = addTime;
    }

    public JSONObject getJsonObject() throws JSONException {
    	JSONObject jsonFindPerson=new JSONObject(); 
		jsonFindPerson.accumulate("findPersonId", this.getFindPersonId());
		jsonFindPerson.accumulate("personName", this.getPersonName());
		jsonFindPerson.accumulate("sex", this.getSex());
		jsonFindPerson.accumulate("personPhoto", this.getPersonPhoto());
		jsonFindPerson.accumulate("bloodType", this.getBloodType());
		jsonFindPerson.accumulate("nativePlaceObj", this.getNativePlaceObj().getPlaceName());
		jsonFindPerson.accumulate("nativePlaceObjPri", this.getNativePlaceObj().getPlaceId());
		jsonFindPerson.accumulate("findTypeObj", this.getFindTypeObj().getFindTypeName());
		jsonFindPerson.accumulate("findTypeObjPri", this.getFindTypeObj().getFindTypeId());
		jsonFindPerson.accumulate("lostTypeObj", this.getLostTypeObj().getLostTypeName());
		jsonFindPerson.accumulate("lostTypeObjPri", this.getLostTypeObj().getLostTypeId());
		jsonFindPerson.accumulate("bornDate", this.getBornDate().length()>19?this.getBornDate().substring(0,19):this.getBornDate());
		jsonFindPerson.accumulate("lostDate", this.getLostDate().length()>19?this.getLostDate().substring(0,19):this.getLostDate());
		jsonFindPerson.accumulate("lostHeight", this.getLostHeight());
		jsonFindPerson.accumulate("lostPlace", this.getLostPlace());
		jsonFindPerson.accumulate("personDesc", this.getPersonDesc());
		jsonFindPerson.accumulate("connectPerson", this.getConnectPerson());
		jsonFindPerson.accumulate("connectPhone", this.getConnectPhone());
		jsonFindPerson.accumulate("email", this.getEmail());
		jsonFindPerson.accumulate("connectAddress", this.getConnectAddress());
		jsonFindPerson.accumulate("postcode", this.getPostcode());
		jsonFindPerson.accumulate("otherWay", this.getOtherWay());
		jsonFindPerson.accumulate("memo", this.getMemo());
		jsonFindPerson.accumulate("userObj", this.getUserObj().getName());
		jsonFindPerson.accumulate("userObjPri", this.getUserObj().getUser_name());
		jsonFindPerson.accumulate("addTime", this.getAddTime().length()>19?this.getAddTime().substring(0,19):this.getAddTime());
		return jsonFindPerson;
    }}
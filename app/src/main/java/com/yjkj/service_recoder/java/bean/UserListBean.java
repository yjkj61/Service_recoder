package com.yjkj.service_recoder.java.bean;

import java.io.Serializable;
import java.util.List;

public class UserListBean implements Serializable{

    /**
     * total : 1
     * rows : [{"createBy":null,"createTime":"2023-12-22 09:24:59","updateBy":null,"updateTime":null,"remark":null,"ownerId":1,"ownerUsername":null,"ownerAccountName":"admin","ownerPassword":null,"ownerName":"潘远","ownerPic":"http://47.116.36.196:9300/statics/2024/04/13/0f6f82ed-12f1-478d-b384-b078030988cb_20240413175241A001.jpg","ownerSex":"0","ownerAge":80,"ownerPhone":"13955547914","ownerCarId":"15","ownerCarNumber":"苏A69698","ownerCardNumber":"310255202311247745","ownerArea":"河南省/洛阳市/新安县","ownerAddress":"安民街道","ownerCommunity":"白马小区","ownerBuilding":"5","ownerUnit":"6","ownerFloor":"4","ownerRoomNum":"102","ownerBedNum":"1","ownerRemainMoney":9999999,"ownerAttribute":null,"ownerBehavior":null,"ownerStatus":0,"ownerFamilyMemberId":"21","ownerFamilyMemberName":"李浩浩","ownerFile":null,"ownerRole":"02","ownerRoleName":null,"ownerVip":1,"ownerVipGrowthValue":null,"ownerVipStartTime":"2022-12-09 00:37:49","ownerVipEndTime":"2023-12-26 00:37:43","ownerLiving":"00","ownerIllness":"02,03","ownerBirth":"2023-11-24","ownerXuexing":1,"ownerCanji":"02","ownerGuomin":"02,01","ownerGuominOther":null,"ownerZongjiao":null,"ownerWenhua":null,"ownerHunyin":null,"ownerMianmao":null,"ownerTuixiu":null,"ownerYuanzhiwu":null,"ownerLaiyuan":"01,03","ownerYiliao":"02,01","ownerBeizhu":null,"ownerHeight":null,"ownerWeight":null,"ownerNursingId":null,"ownerNursingName":"护工A","ownerNursingPhone":"18936959680","ownerNursingIdTow":null,"ownerNursingPhoneTow":null,"ownerNursingNameTow":null,"ownerDoctorId":null,"ownerDoctorName":null,"ownerDoctorPhone":null,"ownerSelfAssess":null,"ownerNurseAssess":null,"markerId":1,"markerName":null,"userId":1,"userType":"04","ownerEntryTime":null,"ownerMonthPrice":100000,"ownerSupervisorDoctorId":null,"ownerSupervisorDoctorName":"医生A","ownerSupervisorDoctorPhone":"15370337632","ownerManagerId":null,"ownerManagerName":"主管A","ownerManagerPhone":"15370337637","registrationId":null,"ownerNurseId":null,"ownerNurse":"护士A","ownerNursePhone":"15370337639","ownerNurseIdTow":null,"ownerNurseTow":null,"ownerNursePhoneTow":null,"minAge":null,"minMoney":null,"province":"河南省","city":"洛阳市","area":"新安县","dangerType":null,"physiologicalState":null,"shopIntegral":9999}]
     * code : 200
     * msg : 查询成功
     */

    private int total;
    private int code = 0;
    private String msg;
    private List<RowsBean> rows;

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public List<RowsBean> getRows() {
        return rows;
    }

    public void setRows(List<RowsBean> rows) {
        this.rows = rows;
    }

    public static class RowsBean implements Serializable {
        /**
         * createBy : null
         * createTime : 2023-12-22 09:24:59
         * updateBy : null
         * updateTime : null
         * remark : null
         * ownerId : 1
         * ownerUsername : null
         * ownerAccountName : admin
         * ownerPassword : null
         * ownerName : 潘远
         * ownerPic : http://47.116.36.196:9300/statics/2024/04/13/0f6f82ed-12f1-478d-b384-b078030988cb_20240413175241A001.jpg
         * ownerSex : 0
         * ownerAge : 80
         * ownerPhone : 13955547914
         * ownerCarId : 15
         * ownerCarNumber : 苏A69698
         * ownerCardNumber : 310255202311247745
         * ownerArea : 河南省/洛阳市/新安县
         * ownerAddress : 安民街道
         * ownerCommunity : 白马小区
         * ownerBuilding : 5
         * ownerUnit : 6
         * ownerFloor : 4
         * ownerRoomNum : 102
         * ownerBedNum : 1
         * ownerRemainMoney : 9999999
         * ownerAttribute : null
         * ownerBehavior : null
         * ownerStatus : 0
         * ownerFamilyMemberId : 21
         * ownerFamilyMemberName : 李浩浩
         * ownerFile : null
         * ownerRole : 02
         * ownerRoleName : null
         * ownerVip : 1
         * ownerVipGrowthValue : null
         * ownerVipStartTime : 2022-12-09 00:37:49
         * ownerVipEndTime : 2023-12-26 00:37:43
         * ownerLiving : 00
         * ownerIllness : 02,03
         * ownerBirth : 2023-11-24
         * ownerXuexing : 1
         * ownerCanji : 02
         * ownerGuomin : 02,01
         * ownerGuominOther : null
         * ownerZongjiao : null
         * ownerWenhua : null
         * ownerHunyin : null
         * ownerMianmao : null
         * ownerTuixiu : null
         * ownerYuanzhiwu : null
         * ownerLaiyuan : 01,03
         * ownerYiliao : 02,01
         * ownerBeizhu : null
         * ownerHeight : null
         * ownerWeight : null
         * ownerNursingId : null
         * ownerNursingName : 护工A
         * ownerNursingPhone : 18936959680
         * ownerNursingIdTow : null
         * ownerNursingPhoneTow : null
         * ownerNursingNameTow : null
         * ownerDoctorId : null
         * ownerDoctorName : null
         * ownerDoctorPhone : null
         * ownerSelfAssess : null
         * ownerNurseAssess : null
         * markerId : 1
         * markerName : null
         * userId : 1
         * userType : 04
         * ownerEntryTime : null
         * ownerMonthPrice : 100000
         * ownerSupervisorDoctorId : null
         * ownerSupervisorDoctorName : 医生A
         * ownerSupervisorDoctorPhone : 15370337632
         * ownerManagerId : null
         * ownerManagerName : 主管A
         * ownerManagerPhone : 15370337637
         * registrationId : null
         * ownerNurseId : null
         * ownerNurse : 护士A
         * ownerNursePhone : 15370337639
         * ownerNurseIdTow : null
         * ownerNurseTow : null
         * ownerNursePhoneTow : null
         * minAge : null
         * minMoney : null
         * province : 河南省
         * city : 洛阳市
         * area : 新安县
         * dangerType : null
         * physiologicalState : null
         * shopIntegral : 9999
         */

        private Object createBy;
        private String createTime;
        private Object updateBy;
        private Object updateTime;
        private Object remark;
        private int ownerId;
        private Object ownerUsername;
        private String ownerAccountName;
        private Object ownerPassword;
        private String ownerName;
        private String ownerPic;
        private String ownerSex;
        private int ownerAge;
        private String ownerPhone;
        private String ownerCarId;
        private String ownerCarNumber;
        private String ownerCardNumber;
        private String ownerArea;
        private String ownerAddress;
        private String ownerCommunity;
        private String ownerBuilding;
        private String ownerUnit;
        private String ownerFloor;
        private String ownerRoomNum;
        private String ownerBedNum;
        private int ownerRemainMoney;
        private Object ownerAttribute;
        private Object ownerBehavior;
        private int ownerStatus;
        private String ownerFamilyMemberId;
        private String ownerFamilyMemberName;
        private Object ownerFile;
        private String ownerRole;
        private Object ownerRoleName;
        private int ownerVip;
        private Object ownerVipGrowthValue;
        private String ownerVipStartTime;
        private String ownerVipEndTime;
        private String ownerLiving;
        private String ownerIllness;
        private String ownerBirth;
        private int ownerXuexing;
        private String ownerCanji;
        private String ownerGuomin;
        private Object ownerGuominOther;
        private Object ownerZongjiao;
        private Object ownerWenhua;
        private Object ownerHunyin;
        private Object ownerMianmao;
        private Object ownerTuixiu;
        private Object ownerYuanzhiwu;
        private String ownerLaiyuan;
        private String ownerYiliao;
        private Object ownerBeizhu;
        private Object ownerHeight;
        private Object ownerWeight;
        private Object ownerNursingId;
        private String ownerNursingName;
        private String ownerNursingPhone;
        private Object ownerNursingIdTow;
        private Object ownerNursingPhoneTow;
        private Object ownerNursingNameTow;
        private Object ownerDoctorId;
        private Object ownerDoctorName;
        private Object ownerDoctorPhone;
        private Object ownerSelfAssess;
        private Object ownerNurseAssess;
        private Long markerId;
        private Object markerName;
        private int userId;
        private String userType;
        private Object ownerEntryTime;
        private int ownerMonthPrice;
        private Object ownerSupervisorDoctorId;
        private String ownerSupervisorDoctorName;
        private String ownerSupervisorDoctorPhone;
        private Object ownerManagerId;
        private String ownerManagerName;
        private String ownerManagerPhone;
        private Object registrationId;
        private Object ownerNurseId;
        private String ownerNurse;
        private String ownerNursePhone;
        private Object ownerNurseIdTow;
        private Object ownerNurseTow;
        private Object ownerNursePhoneTow;
        private Object minAge;
        private Object minMoney;
        private String province;
        private String city;
        private String area;
        private Object dangerType;
        private Object physiologicalState;
        private int shopIntegral;

        public Object getCreateBy() {
            return createBy;
        }

        public void setCreateBy(Object createBy) {
            this.createBy = createBy;
        }

        public String getCreateTime() {
            return createTime;
        }

        public void setCreateTime(String createTime) {
            this.createTime = createTime;
        }

        public Object getUpdateBy() {
            return updateBy;
        }

        public void setUpdateBy(Object updateBy) {
            this.updateBy = updateBy;
        }

        public Object getUpdateTime() {
            return updateTime;
        }

        public void setUpdateTime(Object updateTime) {
            this.updateTime = updateTime;
        }

        public Object getRemark() {
            return remark;
        }

        public void setRemark(Object remark) {
            this.remark = remark;
        }

        public int getOwnerId() {
            return ownerId;
        }

        public void setOwnerId(int ownerId) {
            this.ownerId = ownerId;
        }

        public Object getOwnerUsername() {
            return ownerUsername;
        }

        public void setOwnerUsername(Object ownerUsername) {
            this.ownerUsername = ownerUsername;
        }

        public String getOwnerAccountName() {
            return ownerAccountName;
        }

        public void setOwnerAccountName(String ownerAccountName) {
            this.ownerAccountName = ownerAccountName;
        }

        public Object getOwnerPassword() {
            return ownerPassword;
        }

        public void setOwnerPassword(Object ownerPassword) {
            this.ownerPassword = ownerPassword;
        }

        public String getOwnerName() {
            return ownerName;
        }

        public void setOwnerName(String ownerName) {
            this.ownerName = ownerName;
        }

        public String getOwnerPic() {
            return ownerPic;
        }

        public void setOwnerPic(String ownerPic) {
            this.ownerPic = ownerPic;
        }

        public String getOwnerSex() {
            return ownerSex;
        }

        public void setOwnerSex(String ownerSex) {
            this.ownerSex = ownerSex;
        }

        public int getOwnerAge() {
            return ownerAge;
        }

        public void setOwnerAge(int ownerAge) {
            this.ownerAge = ownerAge;
        }

        public String getOwnerPhone() {
            return ownerPhone;
        }

        public void setOwnerPhone(String ownerPhone) {
            this.ownerPhone = ownerPhone;
        }

        public String getOwnerCarId() {
            return ownerCarId;
        }

        public void setOwnerCarId(String ownerCarId) {
            this.ownerCarId = ownerCarId;
        }

        public String getOwnerCarNumber() {
            return ownerCarNumber;
        }

        public void setOwnerCarNumber(String ownerCarNumber) {
            this.ownerCarNumber = ownerCarNumber;
        }

        public String getOwnerCardNumber() {
            return ownerCardNumber;
        }

        public void setOwnerCardNumber(String ownerCardNumber) {
            this.ownerCardNumber = ownerCardNumber;
        }

        public String getOwnerArea() {
            return ownerArea;
        }

        public void setOwnerArea(String ownerArea) {
            this.ownerArea = ownerArea;
        }

        public String getOwnerAddress() {
            return ownerAddress;
        }

        public void setOwnerAddress(String ownerAddress) {
            this.ownerAddress = ownerAddress;
        }

        public String getOwnerCommunity() {
            return ownerCommunity;
        }

        public void setOwnerCommunity(String ownerCommunity) {
            this.ownerCommunity = ownerCommunity;
        }

        public String getOwnerBuilding() {
            return ownerBuilding;
        }

        public void setOwnerBuilding(String ownerBuilding) {
            this.ownerBuilding = ownerBuilding;
        }

        public String getOwnerUnit() {
            return ownerUnit;
        }

        public void setOwnerUnit(String ownerUnit) {
            this.ownerUnit = ownerUnit;
        }

        public String getOwnerFloor() {
            return ownerFloor;
        }

        public void setOwnerFloor(String ownerFloor) {
            this.ownerFloor = ownerFloor;
        }

        public String getOwnerRoomNum() {
            return ownerRoomNum;
        }

        public void setOwnerRoomNum(String ownerRoomNum) {
            this.ownerRoomNum = ownerRoomNum;
        }

        public String getOwnerBedNum() {
            return ownerBedNum;
        }

        public void setOwnerBedNum(String ownerBedNum) {
            this.ownerBedNum = ownerBedNum;
        }

        public int getOwnerRemainMoney() {
            return ownerRemainMoney;
        }

        public void setOwnerRemainMoney(int ownerRemainMoney) {
            this.ownerRemainMoney = ownerRemainMoney;
        }

        public Object getOwnerAttribute() {
            return ownerAttribute;
        }

        public void setOwnerAttribute(Object ownerAttribute) {
            this.ownerAttribute = ownerAttribute;
        }

        public Object getOwnerBehavior() {
            return ownerBehavior;
        }

        public void setOwnerBehavior(Object ownerBehavior) {
            this.ownerBehavior = ownerBehavior;
        }

        public int getOwnerStatus() {
            return ownerStatus;
        }

        public void setOwnerStatus(int ownerStatus) {
            this.ownerStatus = ownerStatus;
        }

        public String getOwnerFamilyMemberId() {
            return ownerFamilyMemberId;
        }

        public void setOwnerFamilyMemberId(String ownerFamilyMemberId) {
            this.ownerFamilyMemberId = ownerFamilyMemberId;
        }

        public String getOwnerFamilyMemberName() {
            return ownerFamilyMemberName;
        }

        public void setOwnerFamilyMemberName(String ownerFamilyMemberName) {
            this.ownerFamilyMemberName = ownerFamilyMemberName;
        }

        public Object getOwnerFile() {
            return ownerFile;
        }

        public void setOwnerFile(Object ownerFile) {
            this.ownerFile = ownerFile;
        }

        public String getOwnerRole() {
            return ownerRole;
        }

        public void setOwnerRole(String ownerRole) {
            this.ownerRole = ownerRole;
        }

        public Object getOwnerRoleName() {
            return ownerRoleName;
        }

        public void setOwnerRoleName(Object ownerRoleName) {
            this.ownerRoleName = ownerRoleName;
        }

        public int getOwnerVip() {
            return ownerVip;
        }

        public void setOwnerVip(int ownerVip) {
            this.ownerVip = ownerVip;
        }

        public Object getOwnerVipGrowthValue() {
            return ownerVipGrowthValue;
        }

        public void setOwnerVipGrowthValue(Object ownerVipGrowthValue) {
            this.ownerVipGrowthValue = ownerVipGrowthValue;
        }

        public String getOwnerVipStartTime() {
            return ownerVipStartTime;
        }

        public void setOwnerVipStartTime(String ownerVipStartTime) {
            this.ownerVipStartTime = ownerVipStartTime;
        }

        public String getOwnerVipEndTime() {
            return ownerVipEndTime;
        }

        public void setOwnerVipEndTime(String ownerVipEndTime) {
            this.ownerVipEndTime = ownerVipEndTime;
        }

        public String getOwnerLiving() {
            return ownerLiving;
        }

        public void setOwnerLiving(String ownerLiving) {
            this.ownerLiving = ownerLiving;
        }

        public String getOwnerIllness() {
            return ownerIllness;
        }

        public void setOwnerIllness(String ownerIllness) {
            this.ownerIllness = ownerIllness;
        }

        public String getOwnerBirth() {
            return ownerBirth;
        }

        public void setOwnerBirth(String ownerBirth) {
            this.ownerBirth = ownerBirth;
        }

        public int getOwnerXuexing() {
            return ownerXuexing;
        }

        public void setOwnerXuexing(int ownerXuexing) {
            this.ownerXuexing = ownerXuexing;
        }

        public String getOwnerCanji() {
            return ownerCanji;
        }

        public void setOwnerCanji(String ownerCanji) {
            this.ownerCanji = ownerCanji;
        }

        public String getOwnerGuomin() {
            return ownerGuomin;
        }

        public void setOwnerGuomin(String ownerGuomin) {
            this.ownerGuomin = ownerGuomin;
        }

        public Object getOwnerGuominOther() {
            return ownerGuominOther;
        }

        public void setOwnerGuominOther(Object ownerGuominOther) {
            this.ownerGuominOther = ownerGuominOther;
        }

        public Object getOwnerZongjiao() {
            return ownerZongjiao;
        }

        public void setOwnerZongjiao(Object ownerZongjiao) {
            this.ownerZongjiao = ownerZongjiao;
        }

        public Object getOwnerWenhua() {
            return ownerWenhua;
        }

        public void setOwnerWenhua(Object ownerWenhua) {
            this.ownerWenhua = ownerWenhua;
        }

        public Object getOwnerHunyin() {
            return ownerHunyin;
        }

        public void setOwnerHunyin(Object ownerHunyin) {
            this.ownerHunyin = ownerHunyin;
        }

        public Object getOwnerMianmao() {
            return ownerMianmao;
        }

        public void setOwnerMianmao(Object ownerMianmao) {
            this.ownerMianmao = ownerMianmao;
        }

        public Object getOwnerTuixiu() {
            return ownerTuixiu;
        }

        public void setOwnerTuixiu(Object ownerTuixiu) {
            this.ownerTuixiu = ownerTuixiu;
        }

        public Object getOwnerYuanzhiwu() {
            return ownerYuanzhiwu;
        }

        public void setOwnerYuanzhiwu(Object ownerYuanzhiwu) {
            this.ownerYuanzhiwu = ownerYuanzhiwu;
        }

        public String getOwnerLaiyuan() {
            return ownerLaiyuan;
        }

        public void setOwnerLaiyuan(String ownerLaiyuan) {
            this.ownerLaiyuan = ownerLaiyuan;
        }

        public String getOwnerYiliao() {
            return ownerYiliao;
        }

        public void setOwnerYiliao(String ownerYiliao) {
            this.ownerYiliao = ownerYiliao;
        }

        public Object getOwnerBeizhu() {
            return ownerBeizhu;
        }

        public void setOwnerBeizhu(Object ownerBeizhu) {
            this.ownerBeizhu = ownerBeizhu;
        }

        public Object getOwnerHeight() {
            return ownerHeight;
        }

        public void setOwnerHeight(Object ownerHeight) {
            this.ownerHeight = ownerHeight;
        }

        public Object getOwnerWeight() {
            return ownerWeight;
        }

        public void setOwnerWeight(Object ownerWeight) {
            this.ownerWeight = ownerWeight;
        }

        public Object getOwnerNursingId() {
            return ownerNursingId;
        }

        public void setOwnerNursingId(Object ownerNursingId) {
            this.ownerNursingId = ownerNursingId;
        }

        public String getOwnerNursingName() {
            return ownerNursingName;
        }

        public void setOwnerNursingName(String ownerNursingName) {
            this.ownerNursingName = ownerNursingName;
        }

        public String getOwnerNursingPhone() {
            return ownerNursingPhone;
        }

        public void setOwnerNursingPhone(String ownerNursingPhone) {
            this.ownerNursingPhone = ownerNursingPhone;
        }

        public Object getOwnerNursingIdTow() {
            return ownerNursingIdTow;
        }

        public void setOwnerNursingIdTow(Object ownerNursingIdTow) {
            this.ownerNursingIdTow = ownerNursingIdTow;
        }

        public Object getOwnerNursingPhoneTow() {
            return ownerNursingPhoneTow;
        }

        public void setOwnerNursingPhoneTow(Object ownerNursingPhoneTow) {
            this.ownerNursingPhoneTow = ownerNursingPhoneTow;
        }

        public Object getOwnerNursingNameTow() {
            return ownerNursingNameTow;
        }

        public void setOwnerNursingNameTow(Object ownerNursingNameTow) {
            this.ownerNursingNameTow = ownerNursingNameTow;
        }

        public Object getOwnerDoctorId() {
            return ownerDoctorId;
        }

        public void setOwnerDoctorId(Object ownerDoctorId) {
            this.ownerDoctorId = ownerDoctorId;
        }

        public Object getOwnerDoctorName() {
            return ownerDoctorName;
        }

        public void setOwnerDoctorName(Object ownerDoctorName) {
            this.ownerDoctorName = ownerDoctorName;
        }

        public Object getOwnerDoctorPhone() {
            return ownerDoctorPhone;
        }

        public void setOwnerDoctorPhone(Object ownerDoctorPhone) {
            this.ownerDoctorPhone = ownerDoctorPhone;
        }

        public Object getOwnerSelfAssess() {
            return ownerSelfAssess;
        }

        public void setOwnerSelfAssess(Object ownerSelfAssess) {
            this.ownerSelfAssess = ownerSelfAssess;
        }

        public Object getOwnerNurseAssess() {
            return ownerNurseAssess;
        }

        public void setOwnerNurseAssess(Object ownerNurseAssess) {
            this.ownerNurseAssess = ownerNurseAssess;
        }

        public Long getMarkerId() {
            return markerId;
        }

        public void setMarkerId(Long markerId) {
            this.markerId = markerId;
        }

        public Object getMarkerName() {
            return markerName;
        }

        public void setMarkerName(Object markerName) {
            this.markerName = markerName;
        }

        public int getUserId() {
            return userId;
        }

        public void setUserId(int userId) {
            this.userId = userId;
        }

        public String getUserType() {
            return userType;
        }

        public void setUserType(String userType) {
            this.userType = userType;
        }

        public Object getOwnerEntryTime() {
            return ownerEntryTime;
        }

        public void setOwnerEntryTime(Object ownerEntryTime) {
            this.ownerEntryTime = ownerEntryTime;
        }

        public int getOwnerMonthPrice() {
            return ownerMonthPrice;
        }

        public void setOwnerMonthPrice(int ownerMonthPrice) {
            this.ownerMonthPrice = ownerMonthPrice;
        }

        public Object getOwnerSupervisorDoctorId() {
            return ownerSupervisorDoctorId;
        }

        public void setOwnerSupervisorDoctorId(Object ownerSupervisorDoctorId) {
            this.ownerSupervisorDoctorId = ownerSupervisorDoctorId;
        }

        public String getOwnerSupervisorDoctorName() {
            return ownerSupervisorDoctorName;
        }

        public void setOwnerSupervisorDoctorName(String ownerSupervisorDoctorName) {
            this.ownerSupervisorDoctorName = ownerSupervisorDoctorName;
        }

        public String getOwnerSupervisorDoctorPhone() {
            return ownerSupervisorDoctorPhone;
        }

        public void setOwnerSupervisorDoctorPhone(String ownerSupervisorDoctorPhone) {
            this.ownerSupervisorDoctorPhone = ownerSupervisorDoctorPhone;
        }

        public Object getOwnerManagerId() {
            return ownerManagerId;
        }

        public void setOwnerManagerId(Object ownerManagerId) {
            this.ownerManagerId = ownerManagerId;
        }

        public String getOwnerManagerName() {
            return ownerManagerName;
        }

        public void setOwnerManagerName(String ownerManagerName) {
            this.ownerManagerName = ownerManagerName;
        }

        public String getOwnerManagerPhone() {
            return ownerManagerPhone;
        }

        public void setOwnerManagerPhone(String ownerManagerPhone) {
            this.ownerManagerPhone = ownerManagerPhone;
        }

        public Object getRegistrationId() {
            return registrationId;
        }

        public void setRegistrationId(Object registrationId) {
            this.registrationId = registrationId;
        }

        public Object getOwnerNurseId() {
            return ownerNurseId;
        }

        public void setOwnerNurseId(Object ownerNurseId) {
            this.ownerNurseId = ownerNurseId;
        }

        public String getOwnerNurse() {
            return ownerNurse;
        }

        public void setOwnerNurse(String ownerNurse) {
            this.ownerNurse = ownerNurse;
        }

        public String getOwnerNursePhone() {
            return ownerNursePhone;
        }

        public void setOwnerNursePhone(String ownerNursePhone) {
            this.ownerNursePhone = ownerNursePhone;
        }

        public Object getOwnerNurseIdTow() {
            return ownerNurseIdTow;
        }

        public void setOwnerNurseIdTow(Object ownerNurseIdTow) {
            this.ownerNurseIdTow = ownerNurseIdTow;
        }

        public Object getOwnerNurseTow() {
            return ownerNurseTow;
        }

        public void setOwnerNurseTow(Object ownerNurseTow) {
            this.ownerNurseTow = ownerNurseTow;
        }

        public Object getOwnerNursePhoneTow() {
            return ownerNursePhoneTow;
        }

        public void setOwnerNursePhoneTow(Object ownerNursePhoneTow) {
            this.ownerNursePhoneTow = ownerNursePhoneTow;
        }

        public Object getMinAge() {
            return minAge;
        }

        public void setMinAge(Object minAge) {
            this.minAge = minAge;
        }

        public Object getMinMoney() {
            return minMoney;
        }

        public void setMinMoney(Object minMoney) {
            this.minMoney = minMoney;
        }

        public String getProvince() {
            return province;
        }

        public void setProvince(String province) {
            this.province = province;
        }

        public String getCity() {
            return city;
        }

        public void setCity(String city) {
            this.city = city;
        }

        public String getArea() {
            return area;
        }

        public void setArea(String area) {
            this.area = area;
        }

        public Object getDangerType() {
            return dangerType;
        }

        public void setDangerType(Object dangerType) {
            this.dangerType = dangerType;
        }

        public Object getPhysiologicalState() {
            return physiologicalState;
        }

        public void setPhysiologicalState(Object physiologicalState) {
            this.physiologicalState = physiologicalState;
        }

        public int getShopIntegral() {
            return shopIntegral;
        }

        public void setShopIntegral(int shopIntegral) {
            this.shopIntegral = shopIntegral;
        }
    }
}

package com.meidp.itcradle.model;

import java.io.Serializable;
import java.util.List;

/**
 * Created by John on 2016/7/4.
 */
public class Information implements Serializable {

    /**
     * PageIndex : 1
     * RecordCount : 1
     * DataList : [{"IsExpire":0,"LeaveSecond":0,"LeaveTimeStr":null,"AreaId":16,"ArticleID":1,"ChannelID":1005,"ClassID":0,"TypeID":0,"ImgPath":"http://itcradle.meidp.com/upload/201607/dbc95a022b614ce38a0083b3d775c4de.jpg","IsDeleted":0,"IsRelease":1,"QRCode":"","Summary":"三防二合一 松下TOUGHBOOK CF","Title":"三防二合一 松下TOUGHBOOK CF-20评测","Hits":0,"CreateUserID":14,"CreateDate":"2016-07-04 16:23:54","UpdatedDate":"2016-07-04 16:24:20","TotalRead":null,"TotalCollect":null,"ChannelName":"户外促销","CnName":"管理员","UserName":"admin","StartDate":null,"EndDate":null,"ClassName":null,"IsPublic":1,"MinMoney":0,"MaxMoney":0,"ExemptCheck":0,"PF_Check":null,"PF_CheckTime":null,"ArticleCode":null}]
     */

    private int PageIndex;
    private int RecordCount;
    /**
     * IsExpire : 0
     * LeaveSecond : 0
     * LeaveTimeStr : null
     * AreaId : 16
     * ArticleID : 1
     * ChannelID : 1005
     * ClassID : 0
     * TypeID : 0
     * ImgPath : http://itcradle.meidp.com/upload/201607/dbc95a022b614ce38a0083b3d775c4de.jpg
     * IsDeleted : 0
     * IsRelease : 1
     * QRCode :
     * Summary : 三防二合一 松下TOUGHBOOK CF
     * Title : 三防二合一 松下TOUGHBOOK CF-20评测
     * Hits : 0
     * CreateUserID : 14
     * CreateDate : 2016-07-04 16:23:54
     * UpdatedDate : 2016-07-04 16:24:20
     * TotalRead : null
     * TotalCollect : null
     * ChannelName : 户外促销
     * CnName : 管理员
     * UserName : admin
     * StartDate : null
     * EndDate : null
     * ClassName : null
     * IsPublic : 1
     * MinMoney : 0
     * MaxMoney : 0
     * ExemptCheck : 0
     * PF_Check : null
     * PF_CheckTime : null
     * ArticleCode : null
     */

    private List<DataListBean> DataList;

    public int getPageIndex() {
        return PageIndex;
    }

    public void setPageIndex(int PageIndex) {
        this.PageIndex = PageIndex;
    }

    public int getRecordCount() {
        return RecordCount;
    }

    public void setRecordCount(int RecordCount) {
        this.RecordCount = RecordCount;
    }

    public List<DataListBean> getDataList() {
        return DataList;
    }

    public void setDataList(List<DataListBean> DataList) {
        this.DataList = DataList;
    }

    public static class DataListBean implements Serializable {
        private int IsExpire;
        private int LeaveSecond;
        private int AreaId;
        private int ArticleID;
        private int ChannelID;
        private int ClassID;
        private int TypeID;
        private String ImgPath;
        private int IsDeleted;
        private int IsRelease;
        private String QRCode;
        private String Summary;
        private String Title;
        private int Hits;
        private int CreateUserID;
        private String CreateDate;
        private String UpdatedDate;
        private Object TotalRead;
        private String ChannelName;
        private String CnName;
        private String UserName;
        private int IsPublic;
        private int MinMoney;
        private int MaxMoney;
        private int ExemptCheck;

        public int getIsExpire() {
            return IsExpire;
        }

        public void setIsExpire(int IsExpire) {
            this.IsExpire = IsExpire;
        }

        public int getLeaveSecond() {
            return LeaveSecond;
        }

        public void setLeaveSecond(int LeaveSecond) {
            this.LeaveSecond = LeaveSecond;
        }

        public int getAreaId() {
            return AreaId;
        }

        public void setAreaId(int AreaId) {
            this.AreaId = AreaId;
        }

        public int getArticleID() {
            return ArticleID;
        }

        public void setArticleID(int ArticleID) {
            this.ArticleID = ArticleID;
        }

        public int getChannelID() {
            return ChannelID;
        }

        public void setChannelID(int ChannelID) {
            this.ChannelID = ChannelID;
        }

        public int getClassID() {
            return ClassID;
        }

        public void setClassID(int ClassID) {
            this.ClassID = ClassID;
        }

        public int getTypeID() {
            return TypeID;
        }

        public void setTypeID(int TypeID) {
            this.TypeID = TypeID;
        }

        public String getImgPath() {
            return ImgPath;
        }

        public void setImgPath(String ImgPath) {
            this.ImgPath = ImgPath;
        }

        public int getIsDeleted() {
            return IsDeleted;
        }

        public void setIsDeleted(int IsDeleted) {
            this.IsDeleted = IsDeleted;
        }

        public int getIsRelease() {
            return IsRelease;
        }

        public void setIsRelease(int IsRelease) {
            this.IsRelease = IsRelease;
        }

        public String getQRCode() {
            return QRCode;
        }

        public void setQRCode(String QRCode) {
            this.QRCode = QRCode;
        }

        public String getSummary() {
            return Summary;
        }

        public void setSummary(String Summary) {
            this.Summary = Summary;
        }

        public String getTitle() {
            return Title;
        }

        public void setTitle(String Title) {
            this.Title = Title;
        }

        public int getHits() {
            return Hits;
        }

        public void setHits(int Hits) {
            this.Hits = Hits;
        }

        public int getCreateUserID() {
            return CreateUserID;
        }

        public void setCreateUserID(int CreateUserID) {
            this.CreateUserID = CreateUserID;
        }

        public String getCreateDate() {
            return CreateDate;
        }

        public void setCreateDate(String CreateDate) {
            this.CreateDate = CreateDate;
        }

        public String getUpdatedDate() {
            return UpdatedDate;
        }

        public void setUpdatedDate(String UpdatedDate) {
            this.UpdatedDate = UpdatedDate;
        }

        public Object getTotalRead() {
            return TotalRead;
        }

        public void setTotalRead(Object TotalRead) {
            this.TotalRead = TotalRead;
        }

        public String getChannelName() {
            return ChannelName;
        }

        public void setChannelName(String ChannelName) {
            this.ChannelName = ChannelName;
        }

        public String getCnName() {
            return CnName;
        }

        public void setCnName(String CnName) {
            this.CnName = CnName;
        }

        public String getUserName() {
            return UserName;
        }

        public void setUserName(String UserName) {
            this.UserName = UserName;
        }

        public int getIsPublic() {
            return IsPublic;
        }

        public void setIsPublic(int IsPublic) {
            this.IsPublic = IsPublic;
        }

        public int getMinMoney() {
            return MinMoney;
        }

        public void setMinMoney(int MinMoney) {
            this.MinMoney = MinMoney;
        }

        public int getMaxMoney() {
            return MaxMoney;
        }

        public void setMaxMoney(int MaxMoney) {
            this.MaxMoney = MaxMoney;
        }

        public int getExemptCheck() {
            return ExemptCheck;
        }

        public void setExemptCheck(int ExemptCheck) {
            this.ExemptCheck = ExemptCheck;
        }
    }
}

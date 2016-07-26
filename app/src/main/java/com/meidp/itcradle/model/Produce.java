package com.meidp.itcradle.model;

import java.io.Serializable;
import java.util.List;

/**
 * Created by John on 2016/7/6.
 */
public class Produce implements Serializable {

    private int PageIndex;
    private int RecordCount;

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
        private int id;
        private int categoryId;
        private int brandId;
        private String goodName;
        private String goodNo;
        private boolean isBest;
        private boolean isNew;
        private boolean isPromote;
        private int sortOrder;
        private String keywords;
        private String description;
        private int createUserId;
        private String createTime;
        private String modifyTime;
        private int isDeleted;
        private String ReleaseTime;
        private int status;
        private String thumbImg;
        private int totalCollect;
        private int totalRead;
        private int hits;
        private int totalComment;
        private int buyCount;
        private String brandName;
        private String categoryName;
        private String CnName;
        private int seriesId;
        private String imgList;
        private String goodArtno;
        private int salePrice;
        private int marketPrice;
        private int costPrice;
        private String barCode;
        private String union_keyword;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getCategoryId() {
            return categoryId;
        }

        public void setCategoryId(int categoryId) {
            this.categoryId = categoryId;
        }

        public int getBrandId() {
            return brandId;
        }

        public void setBrandId(int brandId) {
            this.brandId = brandId;
        }

        public String getGoodName() {
            return goodName;
        }

        public void setGoodName(String goodName) {
            this.goodName = goodName;
        }

        public String getGoodNo() {
            return goodNo;
        }

        public void setGoodNo(String goodNo) {
            this.goodNo = goodNo;
        }

        public boolean isIsBest() {
            return isBest;
        }

        public void setIsBest(boolean isBest) {
            this.isBest = isBest;
        }

        public boolean isIsNew() {
            return isNew;
        }

        public void setIsNew(boolean isNew) {
            this.isNew = isNew;
        }

        public boolean isIsPromote() {
            return isPromote;
        }

        public void setIsPromote(boolean isPromote) {
            this.isPromote = isPromote;
        }

        public int getSortOrder() {
            return sortOrder;
        }

        public void setSortOrder(int sortOrder) {
            this.sortOrder = sortOrder;
        }

        public String getKeywords() {
            return keywords;
        }

        public void setKeywords(String keywords) {
            this.keywords = keywords;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public int getCreateUserId() {
            return createUserId;
        }

        public void setCreateUserId(int createUserId) {
            this.createUserId = createUserId;
        }

        public String getCreateTime() {
            return createTime;
        }

        public void setCreateTime(String createTime) {
            this.createTime = createTime;
        }

        public String getModifyTime() {
            return modifyTime;
        }

        public void setModifyTime(String modifyTime) {
            this.modifyTime = modifyTime;
        }

        public int getIsDeleted() {
            return isDeleted;
        }

        public void setIsDeleted(int isDeleted) {
            this.isDeleted = isDeleted;
        }

        public String getReleaseTime() {
            return ReleaseTime;
        }

        public void setReleaseTime(String ReleaseTime) {
            this.ReleaseTime = ReleaseTime;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public String getThumbImg() {
            return thumbImg;
        }

        public void setThumbImg(String thumbImg) {
            this.thumbImg = thumbImg;
        }

        public int getTotalCollect() {
            return totalCollect;
        }

        public void setTotalCollect(int totalCollect) {
            this.totalCollect = totalCollect;
        }

        public int getTotalRead() {
            return totalRead;
        }

        public void setTotalRead(int totalRead) {
            this.totalRead = totalRead;
        }

        public int getHits() {
            return hits;
        }

        public void setHits(int hits) {
            this.hits = hits;
        }

        public int getTotalComment() {
            return totalComment;
        }

        public void setTotalComment(int totalComment) {
            this.totalComment = totalComment;
        }

        public int getBuyCount() {
            return buyCount;
        }

        public void setBuyCount(int buyCount) {
            this.buyCount = buyCount;
        }

        public String getBrandName() {
            return brandName;
        }

        public void setBrandName(String brandName) {
            this.brandName = brandName;
        }

        public String getCategoryName() {
            return categoryName;
        }

        public void setCategoryName(String categoryName) {
            this.categoryName = categoryName;
        }

        public String getCnName() {
            return CnName;
        }

        public void setCnName(String CnName) {
            this.CnName = CnName;
        }

        public int getSeriesId() {
            return seriesId;
        }

        public void setSeriesId(int seriesId) {
            this.seriesId = seriesId;
        }

        public String getImgList() {
            return imgList;
        }

        public void setImgList(String imgList) {
            this.imgList = imgList;
        }

        public String getGoodArtno() {
            return goodArtno;
        }

        public void setGoodArtno(String goodArtno) {
            this.goodArtno = goodArtno;
        }

        public int getSalePrice() {
            return salePrice;
        }

        public void setSalePrice(int salePrice) {
            this.salePrice = salePrice;
        }

        public int getMarketPrice() {
            return marketPrice;
        }

        public void setMarketPrice(int marketPrice) {
            this.marketPrice = marketPrice;
        }

        public int getCostPrice() {
            return costPrice;
        }

        public void setCostPrice(int costPrice) {
            this.costPrice = costPrice;
        }

        public String getBarCode() {
            return barCode;
        }

        public void setBarCode(String barCode) {
            this.barCode = barCode;
        }

        public String getUnion_keyword() {
            return union_keyword;
        }

        public void setUnion_keyword(String union_keyword) {
            this.union_keyword = union_keyword;
        }
    }
}

package com.zhoupu.commonsdb.dto;

import java.util.Date;

/**
 * @author xchen
 * @date 2019-04-26
 **/
public class RentCloudWarehouse {

    private Long id;

    /**
     * 租户的sysuserid
     */
    private Long rentsuid;

    /**
     * 租户的cid
     */
    private Long rentcid;

    /**
     * 运营商id
     */
    private Long operatorId;

    /**
     * 是否开通云仓云配 0:开通， 1，未开通
     */
    private Byte isOpenCloud;

    /**
     * 三证合一
     */
    private String papers;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 修改时间
     */
    private Date updateTime;

    /**
     * 调用地址
     */
    private String callUrl;

    /**
     * 配送方式
     */
    private String deliveryType;

    /**
     * 同步MP字段
     */
    private String rentName;

    private String companyName;

    private String address;

    private String phone;

    @Override
    public String toString() {
        return "RentCloudWarehouse{" +
                "id=" + id +
                ", rentsuid=" + rentsuid +
                ", rentcid=" + rentcid +
                ", operatorId=" + operatorId +
                ", isOpenCloud=" + isOpenCloud +
                ", papers='" + papers + '\'' +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                ", callUrl='" + callUrl + '\'' +
                ", deliveryType='" + deliveryType + '\'' +
                ", rentName='" + rentName + '\'' +
                ", companyName='" + companyName + '\'' +
                ", address='" + address + '\'' +
                ", phone='" + phone + '\'' +
                '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getRentsuid() {
        return rentsuid;
    }

    public void setRentsuid(Long rentsuid) {
        this.rentsuid = rentsuid;
    }

    public Long getRentcid() {
        return rentcid;
    }

    public void setRentcid(Long rentcid) {
        this.rentcid = rentcid;
    }

    public Long getOperatorId() {
        return operatorId;
    }

    public void setOperatorId(Long operatorId) {
        this.operatorId = operatorId;
    }

    public Byte getIsOpenCloud() {
        return isOpenCloud;
    }

    public void setIsOpenCloud(Byte isOpenCloud) {
        this.isOpenCloud = isOpenCloud;
    }

    public String getPapers() {
        return papers;
    }

    public void setPapers(String papers) {
        this.papers = papers;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getCallUrl() {
        return callUrl;
    }

    public void setCallUrl(String callUrl) {
        this.callUrl = callUrl;
    }

    public String getDeliveryType() {
        return deliveryType;
    }

    public void setDeliveryType(String deliveryType) {
        this.deliveryType = deliveryType;
    }

    public String getRentName() {
        return rentName;
    }

    public void setRentName(String rentName) {
        this.rentName = rentName;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}

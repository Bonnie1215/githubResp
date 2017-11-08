package com.buleocean_health.springboot.domain;

import javax.persistence.*;

/**
 * 省市区
 * @author huyanqiu
 *
 */
public class Region {
    @Id
    @Column(name = "REGION_ID")
    private Double regionId;

    /**
     * 编码
     */
    @Column(name = "REGION_CODE")
    private String regionCode;

    /**
     * 名称
     */
    @Column(name = "REGION_NAME")
    private String regionName;

    /**
     * 上级ID
     */
    @Column(name = "PARENT_ID")
    private Double parentId;

    /**
     * 
     */
    @Column(name = "REGION_LEVEL")
    private Double regionLevel;

    /**
     * 
     */
    @Column(name = "REGION_ORDER")
    private Double regionOrder;

    /**
     * 中文拼音
     */
    @Column(name = "REGION_NAME_EN")
    private String regionNameEn;

    /**
     * 英文拼音
     */
    @Column(name = "REGION_SHORTNAME_EN")
    private String regionShortnameEn;

    /**
     * @return REGION_ID
     */
    public Double getRegionId() {
        return regionId;
    }

    /**
     * @param regionId
     */
    public void setRegionId(Double regionId) {
        this.regionId = regionId;
    }

    /**
     * @return REGION_CODE
     */
    public String getRegionCode() {
        return regionCode;
    }

    /**
     * @param regionCode
     */
    public void setRegionCode(String regionCode) {
        this.regionCode = regionCode;
    }

    /**
     * @return REGION_NAME
     */
    public String getRegionName() {
        return regionName;
    }

    /**
     * @param regionName
     */
    public void setRegionName(String regionName) {
        this.regionName = regionName;
    }

    /**
     * @return PARENT_ID
     */
    public Double getParentId() {
        return parentId;
    }

    /**
     * @param parentId
     */
    public void setParentId(Double parentId) {
        this.parentId = parentId;
    }

    /**
     * @return REGION_LEVEL
     */
    public Double getRegionLevel() {
        return regionLevel;
    }

    /**
     * @param regionLevel
     */
    public void setRegionLevel(Double regionLevel) {
        this.regionLevel = regionLevel;
    }

    /**
     * @return REGION_ORDER
     */
    public Double getRegionOrder() {
        return regionOrder;
    }

    /**
     * @param regionOrder
     */
    public void setRegionOrder(Double regionOrder) {
        this.regionOrder = regionOrder;
    }

    /**
     * @return REGION_NAME_EN
     */
    public String getRegionNameEn() {
        return regionNameEn;
    }

    /**
     * @param regionNameEn
     */
    public void setRegionNameEn(String regionNameEn) {
        this.regionNameEn = regionNameEn;
    }

    /**
     * @return REGION_SHORTNAME_EN
     */
    public String getRegionShortnameEn() {
        return regionShortnameEn;
    }

    /**
     * @param regionShortnameEn
     */
    public void setRegionShortnameEn(String regionShortnameEn) {
        this.regionShortnameEn = regionShortnameEn;
    }
}
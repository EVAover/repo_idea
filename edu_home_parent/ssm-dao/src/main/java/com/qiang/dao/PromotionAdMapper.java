package com.qiang.dao;

import com.qiang.domain.PromotionAd;

import java.util.List;

public interface PromotionAdMapper {
    /*
        分页查询广告信息
     */
    public List<PromotionAd> findAllPromotionAdByPage();

    /*
        广告动态上下线
     */
    public void updatePromotionAdStatus(PromotionAd promotionAd);

    /**
     * 保存广告信息
     */
    public void savePromotionAd(PromotionAd promotionAd);
    /**
     * 修改广告信息
     */
    public void updatePromotionAd(PromotionAd promotionAd);
    /**
     *回显广告信息接口
     */
    public PromotionAd findPromotionAdById(Integer promotionAdId);
}

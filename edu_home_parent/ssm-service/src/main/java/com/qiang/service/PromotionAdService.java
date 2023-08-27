package com.qiang.service;

import com.github.pagehelper.PageInfo;
import com.qiang.domain.PromotionAd;
import com.qiang.domain.PromotionAdVo;

public interface PromotionAdService {
    /*
       分页查询广告信息
    */
    public PageInfo<PromotionAd> findAllPromotionAdByPage(PromotionAdVo promotionAdVo);

    /*
         广告动态上下线
     */
    public void  updatePromotionAdStatus(int id,int status);
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

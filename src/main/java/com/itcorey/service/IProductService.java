package com.itcorey.service;

import com.itcorey.common.ServerResponse;
import com.itcorey.pojo.Product;
import com.itcorey.vo.ProductDetailVo;

/**
 * Created by ：Corey
 * 15:53 2018/12/25
 * 商品接口
 */
public interface IProductService {

    /**
     * 新增或跟新产品失败
     * @param product
     * @return
     */
    ServerResponse saveOrUpdateProduct(Product product);

    /**
     * 修改产品销售状态
     * @param productId
     * @param status
     * @return
     */
    ServerResponse<String> setSaleStatus(Integer productId,Integer status);

    /**
     * 商品详情信息
     * @param productId
     * @return
     */
    ServerResponse<ProductDetailVo> mamageProductDetail(Integer productId);

}

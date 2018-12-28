package com.itcorey.service;

import com.github.pagehelper.PageInfo;
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

    /**
     * 商品list
     * @param PageNum
     * @param pageSize
     * @return
     */
    ServerResponse<PageInfo> getProductList(int PageNum, int pageSize);

    /**
     * 产品搜索
     * @param productName
     * @param productId
     * @param pageNum
     * @param pageSize
     * @return
     */
    ServerResponse<PageInfo> searchProduct(String productName,Integer productId,int pageNum,int pageSize);

    /**
     * 获取产品详情
     * @param productId
     * @return
     */
    ServerResponse<ProductDetailVo> getProductDetail(Integer productId);

    /**
     * 商品搜索
     * @param keyword
     * @param categoryId
     * @param pageNum
     * @param pageSize
     * @param orderBy
     * @return
     */
    ServerResponse<PageInfo> getProductByKeywordCategory(String keyword, Integer categoryId, int pageNum, int pageSize, String orderBy);

}

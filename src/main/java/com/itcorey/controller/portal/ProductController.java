package com.itcorey.controller.portal;

import com.github.pagehelper.PageInfo;
import com.itcorey.common.ServerResponse;
import com.itcorey.service.IProductService;
import com.itcorey.vo.ProductDetailVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by ：Corey
 * 16:08 2018/12/27
 * 门户商品
 */
@Controller
@RequestMapping("/product/")
public class ProductController {

    @Autowired
    private IProductService iProductService;


    /**
     * 商品详情
     * @param productId
     * @return
     */
    @RequestMapping("detail.do")
    @ResponseBody
    private ServerResponse<ProductDetailVo> detail(Integer productId){
        return iProductService.getProductDetail(productId);
    }

    /**
     * 商品搜索
     * @param keyword
     * @param categoryId
     * @param pageNum
     * @param pageSize
     * @param orderBy
     * @return
     */
    @RequestMapping("list.do")
    @ResponseBody
    public ServerResponse<PageInfo> list(@RequestParam(value = "keyword",required = false)String keyword,
                                         @RequestParam(value = "categoryId",required = false)Integer categoryId,
                                         @RequestParam(value = "pageNum",defaultValue = "1") int pageNum,
                                         @RequestParam(value = "pageSize",defaultValue = "10") int pageSize,
                                         @RequestParam(value = "orderBy",defaultValue = "") String orderBy){
        return iProductService.getProductByKeywordCategory(keyword,categoryId,pageNum,pageSize,orderBy);
    }



}

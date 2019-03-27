package com.itcorey.vo;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created by ：Corey
 * 15:11 2019/3/27
 */
public class OrderProductVo {

    private List<OrderItemVo> orderItemVoList;
    private BigDecimal productTotalPricr;
    private String imageHost;

    public List<OrderItemVo> getOrderItemVoList() {
        return orderItemVoList;
    }

    public void setOrderItemVoList(List<OrderItemVo> orderItemVoList) {
        this.orderItemVoList = orderItemVoList;
    }

    public BigDecimal getProductTotalPricr() {
        return productTotalPricr;
    }

    public void setProductTotalPricr(BigDecimal productTotalPricr) {
        this.productTotalPricr = productTotalPricr;
    }

    public String getImageHost() {
        return imageHost;
    }

    public void setImageHost(String imageHost) {
        this.imageHost = imageHost;
    }
}

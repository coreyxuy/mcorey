package com.itcorey.common;

import com.google.common.collect.Sets;

import java.util.Set;

/**
 * Created by ：Corey
 * 18:05 2018/12/20
 * 常用类
 */
public class Const {

    public static final String CURRENT_USER = "currentUser";
    public static final String EMAIL = "email";
    public static final String USERNAME = "username";

    public interface ProductListOrderBy {
        Set<String> PRICE_ASC_DESC = Sets.newHashSet("price_desc", "price_asc");
    }

    public interface Cart {
        int CHECKED = 1; //购物车选中状态
        int UN_CHECKED = 0;//购物车中未选中状态
        String LIMIT_NUM_FAIL = "LIMIT_NUM_FAIL"; //限制失败
        String LIMIT_NUM_SUCCESS = "LIMIT_NUM_SUCCESS";//限制成功
    }


    public interface Role {
        int ROLE_CUSTOMER = 0; //普通用户
        int ROLE_ADMIN = 1; //管理员
    }

    public enum ProductStatusEnum {
        ON_SALE(1, "在售!");
        private String value;
        private int code;

        ProductStatusEnum(int code, String value) {
            this.code = code;
            this.value = value;
        }

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }

        public int getCode() {
            return code;
        }

        public void setCode(int code) {
            this.code = code;
        }
    }


    /**
     * 订单的构造器
     */
    public enum OrderStatusEnum {
        CANCELED(0, "已取消"),
        NO_PAY(10, "未支付"),
        PAID(20, "已付款"),
        SHIPPED(40, "已发货"),
        ORDER_SUCCESS(50, "订单完成"),
        ORDER_CLOSE(60, "订单关闭");

        //构造器
        OrderStatusEnum(int code, String value) {
            this.Code = code;
            this.value = value;
        }

        private String value;
        private int Code;

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }

        public int getCode() {
            return Code;
        }

        public void setCode(int code) {
            Code = code;
        }
    }

    public interface  AlipayCallback{
        String TRADE_STATUS_WAIT_BUYER_PAY = "WAIT_BUYER_PAY";    //交易创建，等待买家付款
        String TRADE_STATUS_TRADE_SUCCESS = "TRADE_SUCCESS";      //交易支付成功

        String RESPONSE_SUCCESS = "success";
        String RESPONSE_FAILED = "failed";

    }

    public enum  payPlatFormEnum{
       AliPay(1,"支付宝");

       payPlatFormEnum(int code,String value){
           this.code = code;
           this.value = value;
       }

       private String value;
       private int code;

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }

        public int getCode() {
            return code;
        }

        public void setCode(int code) {
            this.code = code;
        }
    }


}

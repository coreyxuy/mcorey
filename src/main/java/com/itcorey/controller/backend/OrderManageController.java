package com.itcorey.controller.backend;

import com.github.pagehelper.PageInfo;
import com.itcorey.common.ServerResponse;
import com.itcorey.service.IOrderService;
import com.itcorey.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

/**
 * Created by ：Corey
 * 10:53 2019/2/26
 * 后台订单管理
 */
@Controller
public class OrderManageController {

    @Autowired
    private IUserService iUserService;


    @Autowired
    private IOrderService iOrderService;

    /**
     * 后台商品列表
     * @param session
     * @param pageNum
     * @param pageSize
     * @return
     */
//    @RequestMapping("list.do")
//    @ResponseBody
//    public ServerResponse<PageInfo> OrderList(HttpSession session, @RequestParam(value = "pageNum",defaultValue = "1") int pageNum,
//                                              @RequestParam(value = "pageSize",defaultValue = "10")int pageSize){
//
//
//
//    }



}

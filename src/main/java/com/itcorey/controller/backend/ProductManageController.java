package com.itcorey.controller.backend;

import com.google.common.collect.Maps;
import com.itcorey.common.Const;
import com.itcorey.common.ResponseCode;
import com.itcorey.common.ServerResponse;
import com.itcorey.pojo.Product;
import com.itcorey.pojo.User;
import com.itcorey.service.IFileService;
import com.itcorey.service.IProductService;
import com.itcorey.service.IUserService;
import com.itcorey.util.PropertiesUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Map;

/**
 * Created by ：Corey
 * 15:43 2018/12/25
 * 商品类信息
 */
@Controller
@RequestMapping("/manage/product")
public class ProductManageController {

    @Autowired
    private IUserService iUserService;
    @Autowired
    private IProductService iProductService;
    @Autowired
    private IFileService iFileService;


    /**
     * 保存商品信息
     *
     * @param session
     * @param product
     * @return
     */
    @RequestMapping("productSave.do")
    @ResponseBody
    public ServerResponse productSave(HttpSession session, Product product) {
        User user = (User) session.getAttribute(Const.CURRENT_USER);
        if (user == null) {
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(), "用户为登录,请登录后再试!");
        }
        //判断管理员权限
        if (iUserService.checkAdminRole(user).isSuccess()) {
            //填充产品的增加业务逻辑
            return iProductService.saveOrUpdateProduct(product);
        } else {
            return ServerResponse.createByErrorMessage("无权限操作!");
        }
    }


    /**
     * 产品上下架(产品的销售状态)
     *
     * @param session
     * @param productId
     * @return
     */
    @RequestMapping("set_sale_status.do")
    @ResponseBody
    public ServerResponse setSlaeStatus(HttpSession session, Integer productId, Integer status) {
        User user = (User) session.getAttribute(Const.CURRENT_USER);
        if (user == null) {
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(), "用户为登录,请登录后再试!");
        }
        //判断管理员权限
        if (iUserService.checkAdminRole(user).isSuccess()) {
            //填充产品的增加业务逻辑
            return iProductService.setSaleStatus(productId, status);
        } else {
            return ServerResponse.createByErrorMessage("无权限操作!");
        }
    }


    /**
     * 获取商品的详情
     *
     * @param session
     * @param productId
     * @return
     */
    @RequestMapping("detail.do")
    @ResponseBody
    public ServerResponse getDetail(HttpSession session, Integer productId) {
        User user = (User) session.getAttribute(Const.CURRENT_USER);
        if (user == null) {
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(), "用户为登录,请登录后再试!");
        }
        //判断管理员权限
        if (iUserService.checkAdminRole(user).isSuccess()) {
            //填充产品的增加业务逻辑
            return iProductService.mamageProductDetail(productId);
        } else {
            return ServerResponse.createByErrorMessage("无权限操作!");
        }
    }

    /**
     *
     * @param session
     * @param pageNum
     * @param pageSize
     * @return
     */
    @RequestMapping("list.do")
    @ResponseBody
    public ServerResponse getList(HttpSession session, @RequestParam(value = "pageNum", defaultValue = "1") int pageNum, @RequestParam(value = "pageSize", defaultValue = "10") int pageSize) {
        User user = (User) session.getAttribute(Const.CURRENT_USER);
        if (user == null) {
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(), "用户为登录,请登录后再试!");
        }
        //判断管理员权限
        if (iUserService.checkAdminRole(user).isSuccess()) {
            //动态分页
            return iProductService.getProductList(pageNum, pageSize);
        } else {
            return ServerResponse.createByErrorMessage("无权限操作!");
        }
    }

    /**
     * 后台产品搜索
     *
     * @param session
     * @param productName
     * @param productId
     * @return
     */
    @RequestMapping("search.do")
    @ResponseBody
    public ServerResponse productSerch(HttpSession session, String productName, Integer productId,
                                       @RequestParam(value = "pageNum", defaultValue = "1") int pageNum,
                                       @RequestParam(value = "pageSize", defaultValue = "10") int pageSize) {
        User user = (User) session.getAttribute(Const.CURRENT_USER);
        if (user == null) {
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(), "用户为登录,请登录后再试!");
        }
        //判断管理员权限
        if (iUserService.checkAdminRole(user).isSuccess()) {
            //动态分页
            return iProductService.searchProduct(productName,productId,pageNum,pageSize);
        } else {
            return ServerResponse.createByErrorMessage("无权限操作!");
        }
    }


    /**
     * 文件上传
     *
     * @param file
     * @param request
     * @return
     */
    @RequestMapping("upload.do")
    @ResponseBody
    public ServerResponse upload(HttpSession session, @RequestParam(value = "upload_file", required = false) MultipartFile file, HttpServletRequest request) {
        //判断权限
        User user = (User) session.getAttribute(Const.CURRENT_USER);
        if (user == null) {
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(), "用户为登录,请登录后再试!");
        }
        if (iUserService.checkAdminRole(user).isSuccess()) {
            String path = request.getSession().getServletContext().getRealPath("upload");
            String targeFileName = iFileService.upload(file, path);
            String url = PropertiesUtil.getProperty("ftp.server.http.prefix") + targeFileName;

            Map fileMap = Maps.newHashMap();
            fileMap.put("url", targeFileName);
            fileMap.put("url", url);
            return ServerResponse.createBySuccess(fileMap);
        } else {
            return ServerResponse.createByErrorMessage("无权限操作!");
        }
    }


    /**
     * 富文本上传
     *
     * @param file
     * @param request
     * @return
     */
    @RequestMapping("richtext_img_upload.do")
    @ResponseBody
    public Map richtextImgUpload(HttpServletResponse response , HttpSession session, @RequestParam(value = "upload_file", required = false) MultipartFile file, HttpServletRequest request) {
        Map resultMap = Maps.newHashMap();
        //判断权限
        User user = (User) session.getAttribute(Const.CURRENT_USER);
        if (user == null) {
            resultMap.put("success", false);
            resultMap.put("msg", "请登录管理员账号");
            return resultMap;
        }
        //富文本插件 针对 simditor 插件
        if (iUserService.checkAdminRole(user).isSuccess()) {
            String path = request.getSession().getServletContext().getRealPath("upload");
            String targeFileName = iFileService.upload(file, path);
            if (StringUtils.isBlank(targeFileName)) {
                resultMap.put("success", false);
                resultMap.put("msg", "上传失败");
                return resultMap;
            }
            String url = PropertiesUtil.getProperty("ftp.server.http.prefix") + targeFileName;
            resultMap.put("success", true);
            resultMap.put("msg", false);
            resultMap.put("file_path", url);
            response.addHeader("Access-Control-Allow-Headers","X-File-Name");
            return resultMap;
        } else {
            resultMap.put("success", false);
            resultMap.put("msg", "无权限操作");
            return resultMap;
        }
    }


}

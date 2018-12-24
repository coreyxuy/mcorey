package com.itcorey.service.Impl;

import com.itcorey.common.ServerResponse;
import com.itcorey.dao.CategoryMapper;
import com.itcorey.pojo.Category;
import com.itcorey.service.ICategoryService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by ：Corey
 * 15:31 2018/12/24
 */
@Service("ICategoryService")
public class ICategoryServiceImpl implements ICategoryService {

    @Autowired
    private CategoryMapper categoryMapper;


    /**
     * 添加品类
     *
     * @param categoryName
     * @param parentId
     * @return
     */
    public ServerResponse addCategory(String categoryName, Integer parentId) {
        if (parentId == null || StringUtils.isBlank(categoryName)) {
            return ServerResponse.createByErrorMessage("添加品类信息错误");
        }
        Category category = new Category();
        category.setName(categoryName);
        category.setParentId(parentId);
        category.setStatus(true);
        int insert = categoryMapper.insert(category);
        if (insert > 0) {
            return ServerResponse.createBySuccess("添加品类信息成功");
        }
        return ServerResponse.createByErrorMessage("添加品类失败");
    }

    /**
     * 跟新用户信息
     * @param categroyId
     * @param categoryName
     * @return
     */
    public ServerResponse updateCategory(Integer categroyId,String categoryName){
        if (categroyId == null || StringUtils.isBlank(categoryName)) {
            return ServerResponse.createByErrorMessage("添加品类信息错误");
        }

        //TODO 跟新品类信息
        return null;
    }

}

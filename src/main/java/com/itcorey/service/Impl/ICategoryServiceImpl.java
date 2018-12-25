package com.itcorey.service.Impl;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import com.itcorey.common.ServerResponse;
import com.itcorey.dao.CategoryMapper;
import com.itcorey.pojo.Category;
import com.itcorey.service.ICategoryService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Set;

/**
 * Created by ：Corey
 * 15:31 2018/12/24
 */
@Service("ICategoryService")
public class ICategoryServiceImpl implements ICategoryService {

    private Logger logger = LoggerFactory.getLogger(ICategoryServiceImpl.class);

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
     *
     * @param categroyId
     * @param categoryName
     * @return
     */
    public ServerResponse updateCategory(Integer categroyId, String categoryName) {
        if (categroyId == null || StringUtils.isBlank(categoryName)) {
            return ServerResponse.createByErrorMessage("添加品类信息错误");
        }
        Category category = new Category();
        category.setId(categroyId);
        category.setName(categoryName);
        int rowCount = categoryMapper.updateByPrimaryKeySelective(category);
        if (rowCount > 0) {
            return ServerResponse.createBySuccess("跟新品类信息成功");
        }
        return ServerResponse.createByErrorMessage("跟新品类信息失败!");
    }

    public ServerResponse<List<Category>> getChildrenParallelCategory(Integer categoryId) {
        List<Category> categories = categoryMapper.selectCategoreyChildrenByParentId(categoryId);
        if (CollectionUtils.isEmpty(categories)) {
            logger.info("未找到当前分类的子类信息！");
        }
        return ServerResponse.createBySuccess(categories);
    }

    /**
     * 递归查询本节点的id和子节点的id
     *
     * @param categoryId
     * @return
     */
    public ServerResponse<List<Integer>> selectCategoryAndChildrenById(Integer categoryId) {
        Set<Category> categorySet = Sets.newHashSet();
        findChildCategory(categorySet,categoryId);
        List<Integer> catagoreyList = Lists.newArrayList();
        if (catagoreyList != null) {
            for (Category categoryItem : categorySet) {
                catagoreyList.add(categoryItem.getId());
            }
        }
        return ServerResponse.createBySuccess(catagoreyList);
    }


    //递归算法 算出子节点(品类的子节点)
    public Set<Category> findChildCategory(Set<Category> categorySet, Integer categoryId) {
        Category category = categoryMapper.selectByPrimaryKey(categoryId);
        if (category != null) {
            categorySet.add(category);
        }
        //查找子节点,递归算法一定有一个退出条件
        List<Category> categories = categoryMapper.selectCategoreyChildrenByParentId(categoryId);
        for (Category categoryitem : categories) {
            findChildCategory(categorySet, categoryitem.getId());
        }
        return categorySet;

    }


}

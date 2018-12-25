package com.itcorey.service;

import com.itcorey.common.ServerResponse;
import com.itcorey.pojo.Category;

import java.util.List;

/**
 * Created by ：Corey
 * 15:31 2018/12/24
 * 品类接口
 */
public interface ICategoryService {

    /**
     * 添加品类信息
     * @param categoryName
     * @param parentId
     * @return
     */
    ServerResponse addCategory(String categoryName, Integer parentId);

    /**
     * 跟新品类信息
     * @param categroyId
     * @param categoryName
     * @return
     */
    ServerResponse updateCategory(Integer categroyId, String categoryName);

    /**
     * 获取categoryId 信息获取子节点平级信息  不递归
     * @param categoryId
     * @return
     */
    ServerResponse<List<Category>> getChildrenParallelCategory(Integer categoryId);

    /**
     * 递归查询子节点信息和子节点以下信息
     * @param categoryId
     * @return
     */
    ServerResponse<List<Integer>> selectCategoryAndChildrenById(Integer categoryId);
}

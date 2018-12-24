package com.itcorey.service;

import com.itcorey.common.ServerResponse;

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
}

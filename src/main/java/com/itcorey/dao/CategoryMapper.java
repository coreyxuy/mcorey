package com.itcorey.dao;

import com.itcorey.common.ServerResponse;
import com.itcorey.pojo.Category;

import java.util.List;

public interface CategoryMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Category record);

    int insertSelective(Category record);

    Category selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Category record);

    int updateByPrimaryKey(Category record);

    /**
     * 获取平级用户信息
     * @param  parentId
     * @return
     */
   List<Category> selectCategoreyChildrenByParentId(Integer parentId);
}
package org.fox.service.impl;

import org.fox.dao.CategoryMapper;
import org.fox.entity.Category;
import org.fox.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryMapper categoryMapper;
    /**
     * 新增客房类型
     * @param category
     */
    @Override
    public boolean AddCategory(Category category) {
        return categoryMapper.InsertCategory(category);
    }

    @Override
    public void DeleteCategory(int roomCategoryId) {

    }

    @Override
    public void ModifyCategory(Category category) {

    }

    /**
     * 根据roomCategoryId获取客房信息
     * @return Category
     */
    @Override
    public Category SerachCategory(int roomCategoryId) {
        return categoryMapper.SerachCategory(roomCategoryId);
    }

    /**
     * 获取所有客房类型信息
     * @return List<Category>
     */
    @Override
    public List<Category> SerachAllCategory() {
        return categoryMapper.SerachAllCategory();
    }

    @Override
    public List<String> queryAllImg() {
        return categoryMapper.SerachAllImg();
    }

    @Override
    public int SearchRoomCount(int roomCategoryId) {
        return 0;
    }
}

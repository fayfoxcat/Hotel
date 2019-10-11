package org.fox.service;

import org.fox.entity.Category;

import java.util.List;

public interface CategoryService {
    /**
     * 插入新Category类型
     * @param category
     */
    public boolean AddCategory(Category category);

    /**
     * 根据roomCategroyid删除客房类型
     * @param roomCategoryId
     */
    public void DeleteCategory(int roomCategoryId);

    /**
     * 修改Category客房类型
     * @param category
     */
    public void ModifyCategory(Category category);

    /**
     * 根据roomCategroyid查询客房类型
     * @param roomCategoryId
     * @return Category
     */
    public Category SerachCategory(int roomCategoryId);

    /**
     * 查询所有客房类型
     * @return Category
     */
    public List<Category> SerachAllCategory();

    /**
     * 返回所有客房图片
     * @return
     */
    public List<String> queryAllImg();

    /**
     * 根据roomCategroyid查询客房类型剩余数
     * @param roomCategoryId
     * @return
     */
    public int SearchRoomCount(int roomCategoryId);
}

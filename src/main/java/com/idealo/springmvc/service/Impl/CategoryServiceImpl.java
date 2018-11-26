package com.idealo.springmvc.service.Impl;

import java.util.Optional;
import java.util.Set;
import java.util.HashSet;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.idealo.springmvc.dao.ICategoryDAO;
import com.idealo.springmvc.model.Category;
import com.idealo.springmvc.model.Item;
import com.idealo.springmvc.service.CategoryService;


@Service("categoryService")
@Transactional
public class CategoryServiceImpl implements CategoryService {

  @Autowired
  private ICategoryDAO categoryDAO;

  public ICategoryDAO getCategoryDAO() {
    return categoryDAO;
  }

  public void setCategoryDAO(final ICategoryDAO categoryDAO) {
    this.categoryDAO = categoryDAO;
  }

  @Override
  public Optional<Category> findById(final int categoryId) {
    return categoryDAO.findById(categoryId);
  }

  @Override
  public void addCategory(final Category category) {
    createOrUpdateCategory(category);
    categoryDAO.save(category);
  }

  @Override
  public List<Category> listCategories() {
    return categoryDAO.findAllCategories();
  }

  @Override
  public void updateCategory(final Category category) {
    createOrUpdateCategory(category);
    categoryDAO.updateCategory(category);
  }

  @Override
  public void saveItemForCategory(final Category category) {
    categoryDAO.saveItemForCategory(category);
  }

  @Override
  public Optional<List<Item>> listItemsForCategory(final Category category) {
    return categoryDAO.findItemByCategory(category.getCategoryId());
  }

  private void createOrUpdateCategory(Category category) {
    Set<Item> items = new HashSet<>();
    for (Item item : category.getItems()) {
      item.setCategory(category);
      items.add(item);
    }
    category.setItems(items);
  }
}

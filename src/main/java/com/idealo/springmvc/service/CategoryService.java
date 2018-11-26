package com.idealo.springmvc.service;

import java.util.List;
import java.util.Optional;

import com.idealo.springmvc.model.Category;
import com.idealo.springmvc.model.Item;

public interface CategoryService {

  public Optional<Category> findById(int categoryId);

  public void addCategory(Category category);

  public List<Category> listCategories();

  public void updateCategory(Category category);

  public void saveItemForCategory(Category category);

  public Optional<List<Item>> listItemsForCategory(Category category);

}
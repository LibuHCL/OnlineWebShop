package com.idealo.springmvc.dao;

import java.util.List;
import java.util.Optional;

import com.idealo.springmvc.model.Category;
import com.idealo.springmvc.model.Item;

/**
 * Repository interface that creates and list categories on user input.
 *
 * @author Libu Mathew.
 * @version $Id:$
 */

public interface ICategoryDAO {

  Optional<Category> findById(int categoryId);

  public void save(Category category);

  public List<Category> findAllCategories();

  public void updateCategory(Category category);

  public void saveItemForCategory(Category category);

  Optional<List<Item>> findItemByCategory(int categoryId);

}



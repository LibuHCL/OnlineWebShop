package com.idealo.springmvc.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.core.convert.converter.Converter;

import com.idealo.springmvc.model.Category;
import com.idealo.springmvc.service.CategoryService;


import java.util.Optional;

@Component
public class CategoryConverter implements Converter<Object, Category> {


  @Autowired
  CategoryService categoryService;

  @Override
  public Category convert(final Object element) {
    Category initCategory = (Category) element;
    Optional<Category> category = categoryService.findById(initCategory.getCategoryId());
    if (category.isPresent()) {
      return category.get();
    } else {
      return initCategory;
    }
  }
}

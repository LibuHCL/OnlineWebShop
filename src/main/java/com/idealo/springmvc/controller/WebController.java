package com.idealo.springmvc.controller;

import java.util.HashSet;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.http.HttpHeaders;

import java.util.Optional;
import java.util.Set;


import javax.validation.Valid;


import com.idealo.springmvc.model.Category;
import com.idealo.springmvc.model.Item;
import com.idealo.springmvc.service.CategoryService;

@RestController
@RequestMapping("/idealo/rest")
public class WebController {

  @Autowired
  CategoryService categoryService;

  @Autowired
  MessageSource messageSource;

  @GetMapping(path = { "/categorys" })
  public ResponseEntity<List<Category>> listRestCategories() {
    List<Category> categories = categoryService.listCategories();
    if (categories == null || categories.isEmpty()) {
      return new ResponseEntity<List<Category>>(HttpStatus.NO_CONTENT);
    }
    return new ResponseEntity<List<Category>>(categories, HttpStatus.OK);
  }

  @PostMapping(path = "/add/category")
  public ResponseEntity<Void> create(@RequestBody Category category, UriComponentsBuilder ucBuilder) {
    HttpHeaders headers = new HttpHeaders();
    if (category != null && category.getTitle().isEmpty()) {
      return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
    if (category != null && !category.getTitle().isEmpty()) {
      if (category.getCategoryId() == 0) {
        this.categoryService.addCategory(category);
      } else {
        this.categoryService.updateCategory(category);
        return new ResponseEntity<>(headers, HttpStatus.OK);
      }
      headers.setLocation(ucBuilder.path("/add/category").buildAndExpand(category.getCategoryId()).toUri());
    }
    return new ResponseEntity<>(headers, HttpStatus.CREATED);
  }

  @PostMapping(path = "/add/item")
  public ResponseEntity<Void> saveItemWithCategory(@RequestBody @Valid Category category, @Valid Item item) {
    Set<Item> items = new HashSet<>();
    if (category != null) {
      if (category.getTitle().isEmpty()) {
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
      } else {
        Optional<Integer> categoryId = Optional.of(category.getCategoryId());
        if (categoryId.isPresent()) {
          item.setCategory(category);
          items.add(item);
          category.setItems(items);
          categoryService.saveItemForCategory(category);
          return new ResponseEntity<>(HttpStatus.CREATED);
        } else {
          return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
      }
    }
    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
  }

  @GetMapping(path = "/items")
  public ResponseEntity<List<Item>> listItemsForCategory(@RequestBody @Valid Category category) {
    Optional<Integer> categoryId = Optional.of(category.getCategoryId());
    if (!categoryId.isPresent()) {
      return new ResponseEntity<List<Item>>(HttpStatus.NO_CONTENT);
    } else {
      Optional<List<Item>> items = categoryService.listItemsForCategory(category);
      if (items.isPresent()) {
        return new ResponseEntity<List<Item>>(items.get(), HttpStatus.OK);
      }
    }
    return new ResponseEntity<List<Item>>(HttpStatus.BAD_REQUEST);
  }
}

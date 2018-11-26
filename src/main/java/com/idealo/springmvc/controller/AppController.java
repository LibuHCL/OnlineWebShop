package com.idealo.springmvc.controller;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;

import javax.validation.Valid;

import com.idealo.springmvc.model.Category;
import com.idealo.springmvc.model.Item;
import com.idealo.springmvc.service.CategoryService;

import java.util.List;
import java.util.Set;
import java.util.HashSet;
import java.util.Optional;
import java.util.Calendar;


@Controller
@RequestMapping("/idealo/onlinewebshop")
public class  AppController {

  private static final Logger logger = LoggerFactory.getLogger(AppController.class);

  @Autowired
  CategoryService categoryService;

  @Autowired
  MessageSource messageSource;

  /**
   * This method will list all the categories.
   */
  @GetMapping(path = { "/", "/categorys" })
  public String listCategories(Model model) {
    model.addAttribute("listCategories", this.categoryService.listCategories());
    return "categorys";
  }

  /**
   * This method will create a new category on category listing pages.
   */
  @GetMapping(path = "/new/category")
  public String newCategory(ModelMap model) {
    Category category = new Category();
    model.addAttribute("category", category);
    model.addAttribute("edit", false);
    return "registration";
  }

  /**
   * This method will provide the medium to add a new user.
   */
  @PostMapping(path = "/add/category")
  public String saveCategory(@ModelAttribute("category")@RequestBody @Valid Category category, BindingResult result, ModelMap model) {
    if (result.hasErrors()) {
      logger.debug("The request has errors for the category {} at time {}.", category.getCategoryId() , Calendar.getInstance().getTime());

      return "registration";
    }
    if (category != null) {
      if (category.getCategoryId() == 0) {
        this.categoryService.addCategory(category);
        logger.debug("new category added sucessfully with the category Id {} with the title {}.", category.getCategoryId() , category.getTitle());
      } else {
        this.categoryService.updateCategory(category);
        logger.debug("new category updated sucessfully with the category Id {} with the title {}.", category.getCategoryId() , category.getTitle());
      }
      model.addAttribute("success", "User " + category.getTitle() + "  category registered successfully");
    }
    return "registrationsuccess";

  }

  /**
   * This method will create a new item for category .
   */
  @GetMapping(path = "/new/item")
  public String newItemCategory(ModelMap model) {
    Item item = new Item();
    model.addAttribute("item", item);
    model.addAttribute("success", "User " + item.getTitle() + "  item registered successfully");
    return "item";
  }

  /**
   * This method will provide the medium to add a item with category of new user.
   */
  @PostMapping(path = "/add/item")
  public String saveItemWithCategory(@ModelAttribute("category") @RequestBody @Valid Category category, @ModelAttribute("item") @Valid Item item, BindingResult result,
                                     ModelMap model) {
    Set<Item> items = new HashSet<>();
    if (result.hasErrors()) {
      logger.debug("The request has validation errors for the category Id {} and the title {}.", category.getCategoryId() , category.getTitle());
      return "item";
    }
    Optional<Integer> categoryId = Optional.of(category.getCategoryId());
    if (categoryId.isPresent()) {
      item.setCategory(category);
      items.add(item);
      category.setItems(items);
      categoryService.saveItemForCategory(category);

    } else {
      logger.debug("The category Id for the Item is mandatory for the title {} and the text {}.", item.getTitle() , item.getText());
    }
    model.addAttribute("success", "Item " + item.getTitle() + " for the category " + category.getTitle() + " registered successfully");
    return "itemRegistration";

  }

  /**
   * This method will list all the items based on the category.
   */
  @GetMapping(path = "/items")
  public String listItemsForCategory(@ModelAttribute("category") @RequestBody @Valid Category category, BindingResult result, ModelMap model) {
    if (result.hasErrors()) {
      logger.debug("The request has validation errors for the category Id {} and the title {}.", category.getCategoryId() , category.getTitle());
      return "itemRegistration";
    }
    model.addAttribute("listItems", this.categoryService.listItemsForCategory(category));
    return "listItems";
  }

  /**
   * This method will provide category list to views
   */
  @ModelAttribute("category")
  public List<Category> initializeProfiles() {
    return categoryService.listCategories();
  }

}
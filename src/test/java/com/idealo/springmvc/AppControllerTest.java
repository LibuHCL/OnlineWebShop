package com.idealo.springmvc;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.ViewResolver;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.Matchers.*;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.idealo.springmvc.configuration.AppConfig;
import com.idealo.springmvc.configuration.AppInitializer;
import com.idealo.springmvc.controller.AppController;
import com.idealo.springmvc.filter.CORSFilter;
import com.idealo.springmvc.model.Category;
import com.idealo.springmvc.model.Item;
import com.idealo.springmvc.service.CategoryService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { AppConfig.class, TestContext.class, AppInitializer.class })
@WebAppConfiguration
public class AppControllerTest {

  private MockMvc mockMvc;

  @InjectMocks
  AppController appController;


  @Before
  public void init() {
    MockitoAnnotations.initMocks(this);
    mockMvc = MockMvcBuilders
        .standaloneSetup(appController)
        .addFilters(new CORSFilter())
        .setViewResolvers(viewResolver())
        .build();
  }

  @Autowired
  private CategoryService categoryService;

  private ViewResolver viewResolver() {
    InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();

    viewResolver.setPrefix("classpath:/WEB-INF/views/");
    viewResolver.setSuffix(".jsp");

    return viewResolver;
  }

  @Test
  public void listCategoriesTestView() throws Exception {
    List<Category> categories = Arrays.asList(
        new Category(1, "Clothes"),
        new Category(2, "Mobiles"));

    when(categoryService.listCategories()).thenReturn(categories);

    mockMvc.perform(get("/idealo/onlinewebshop/categorys"))
        .andExpect(view().name("categorys"))
        .andExpect(forwardedUrl("classpath:/WEB-INF/views/categorys.jsp"))
        .andExpect(model().attribute("listCategories", hasSize(2)))
        .andExpect(model().attribute("listCategories", hasItem(
            allOf(
                hasProperty("categoryId", is(1)),
                hasProperty("title", is("Clothes"))
            )
        )))
        .andExpect(model().attribute("listCategories", hasItem(
            allOf(
                hasProperty("categoryId", is(2)),
                hasProperty("title", is("Mobiles"))
            )
        )));
  }

  @Test
  public void listCategoriesTestGenericView() throws Exception {
    List<Category> categories = Arrays.asList(
        new Category(1, "Clothes"));

    when(categoryService.listCategories()).thenReturn(categories);

    mockMvc.perform(get("/idealo/onlinewebshop/"))
        .andExpect(view().name("categorys"))
        .andExpect(forwardedUrl("classpath:/WEB-INF/views/categorys.jsp"))
        .andExpect(model().attribute("listCategories", hasSize(1)))
        .andExpect(model().attribute("listCategories", hasItem(
            allOf(
                hasProperty("categoryId", is(1)),
                hasProperty("title", is("Clothes"))
            )
        )));
  }

  @Test
  public void addNewCategory() throws Exception {
    Category category = new Category();
    mockMvc.perform(get("/idealo/onlinewebshop/new/category"))
        .andExpect(view().name("registration"))
        .andExpect(forwardedUrl("classpath:/WEB-INF/views/registration.jsp"));
  }

  @Test
  public void saveItemWithCategoryEmptyTitle() throws Exception {
    Category category = new Category();
    mockMvc.perform(post("/idealo/onlinewebshop/add/category").
        contentType(MediaType.APPLICATION_JSON).
        content(asJsonString(category)))
        .andExpect(status().isOk())
        .andExpect(view().name("registrationsuccess"))
        .andExpect(forwardedUrl("classpath:/WEB-INF/views/registrationsuccess.jsp"));
  }

  @Test
  public void saveItemWithCategoryAdd() throws Exception {
    Category category = new Category();
    category.setCategoryId(0);
    category.setTitle("Hardware");
    doNothing().when(categoryService).addCategory(category);
    mockMvc.perform(post("/idealo/onlinewebshop/add/category").
        contentType(MediaType.APPLICATION_JSON)
        .content(asJsonString(category)))
        .andExpect(status().isOk())
        .andExpect(view().name("registrationsuccess"))
        .andExpect(forwardedUrl("classpath:/WEB-INF/views/registrationsuccess.jsp"));
  }

  @Test
  public void saveItemWithCategoryUpdate() throws Exception {
    Category category = new Category();
    category.setCategoryId(1);
    doNothing().when(categoryService).updateCategory(category);
    mockMvc.perform(post("/idealo/onlinewebshop/add/category").
        contentType(MediaType.APPLICATION_JSON)
        .content(asJsonString(category)))
        .andExpect(status().isOk())
        .andExpect(view().name("registrationsuccess"))
        .andExpect(forwardedUrl("classpath:/WEB-INF/views/registrationsuccess.jsp"));
  }

  @Test
  public void newItemView() throws Exception {
    mockMvc.perform(get("/idealo/onlinewebshop/new/item"))
        .andExpect(view().name("item"))
        .andExpect(forwardedUrl("classpath:/WEB-INF/views/item.jsp"));
  }

  @Test
  public void listItemsView() throws Exception {
    mockMvc.perform(get("/idealo/onlinewebshop/items"))
        .andExpect(view().name("listItems"))
        .andExpect(forwardedUrl("classpath:/WEB-INF/views/listItems.jsp"));
  }


  /*
   * converts a Java object into JSON representation
   */
  public static String asJsonString(final Object obj) {
    try {
      return new ObjectMapper().writeValueAsString(obj);
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }
}

package com.idealo.springmvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.idealo.springmvc.controller.WebController;
import com.idealo.springmvc.filter.CORSFilter;
import com.idealo.springmvc.model.Category;
import com.idealo.springmvc.model.Item;
import com.idealo.springmvc.service.CategoryService;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.*;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.core.Is.is;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class WebControllerUnitTest {


  private MockMvc mockMvc;

  @Mock
  CategoryService categoryService;

  @InjectMocks
  WebController webController;


  @Before
  public void init() {
    ObjectMapper objectMapper = new ObjectMapper();
    objectMapper.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);
    MockitoAnnotations.initMocks(this);
    mockMvc = MockMvcBuilders
        .standaloneSetup(webController)
        .addFilters(new CORSFilter())
        .build();
  }

  @Test
  public void testListRestCategories() throws Exception {
    List<Category> categories = Arrays.asList(
        new Category(1, "Clothes"),
        new Category(2, "Mobiles"));

    when(categoryService.listCategories()).thenReturn(categories);
    mockMvc.perform(get("/idealo/rest/categorys"))
        .andExpect(status().isOk())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
        .andExpect(jsonPath("$", hasSize(2)))
        .andExpect(jsonPath("$[0].categoryId", is(1)))
        .andExpect(jsonPath("$[0].title", is("Clothes")))
        .andExpect(jsonPath("$[1].categoryId", is(2)))
        .andExpect(jsonPath("$[1].title", is("Mobiles")));

    verify(categoryService, times(1)).listCategories();
    verifyNoMoreInteractions(categoryService);

  }

  @Test
  public void testListRestCategoriesEmpty() throws Exception {
    List<Category> categories = new ArrayList<>();

    when(categoryService.listCategories()).thenReturn(categories);
    mockMvc.perform(get("/idealo/rest/categorys"))
        .andExpect(status().isNoContent());
    verify(categoryService, times(1)).listCategories();
    verifyNoMoreInteractions(categoryService);

  }

  @Test
  public void testListRestCategoriesNull() throws Exception {
    List<Category> categories = null;

    when(categoryService.listCategories()).thenReturn(categories);
    mockMvc.perform(get("/idealo/rest/categorys"))
        .andExpect(status().isNoContent());
    verify(categoryService, times(1)).listCategories();
    verifyNoMoreInteractions(categoryService);

  }

  @Test
  public void testAddEmptyCategoryTitleFail() throws Exception {
    Category category = new Category();
    mockMvc.perform(post("/idealo/rest/add/category"))
        .andExpect(status().isBadRequest());
  }

  @Test
  public void testAddEmptyCategoryTitle() throws Exception {
    Category category = new Category(0, "Mobile");
    doNothing().when(categoryService).addCategory(category);
    mockMvc.perform(post("/idealo/rest/add/category").
        contentType(MediaType.APPLICATION_JSON).
        content(asJsonString(category)))
        .andExpect(status().isCreated());
  }

  @Test
  public void testUpdateEmptyCategoryTitle() throws Exception {
    Category category = new Category(1, "Mobile");
    doNothing().when(categoryService).updateCategory(category);
    mockMvc.perform(post("/idealo/rest/add/category").
        contentType(MediaType.APPLICATION_JSON).
        content(asJsonString(category)))
        .andExpect(status().isOk());
  }

  @Test
  public void testAddEmptyItemTitleFail() throws Exception {
    Category category = new Category(1, "");
    doNothing().when(categoryService).saveItemForCategory(category);
    mockMvc.perform(post("/idealo/rest/add/item").
        contentType(MediaType.APPLICATION_JSON).
        content(asJsonString(category)))
        .andExpect(status().isBadRequest());

  }

  @Test
  public void testAddItemTitleSuccess() throws Exception {
    Category category = new Category(1, "Hardware");
    doNothing().when(categoryService).saveItemForCategory(category);
    mockMvc.perform(post("/idealo/rest/add/item").
        contentType(MediaType.APPLICATION_JSON).
        content(asJsonString(category)))
        .andExpect(status().isCreated());

  }

  @Test
  public void testListRestItemsForCategory() throws Exception {
    List<Category> categories = Arrays.asList(
        new Category(1, "Clothes"));
    List<Item> items = Arrays.asList(
        new Item("Mobile", "Hardware", 1),
        new Item("Automobiles", "Electronics", 2));
    mockMvc.perform(get("/idealo/***"))
        .andExpect(status().isNotFound());

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

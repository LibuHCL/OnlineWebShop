package com.idealo.springmvc.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.CascadeType;

import org.hibernate.validator.constraints.NotEmpty;


/**
 * Entity bean with JPA annotations Hibernate provides JPA implementation
 *
 * @author Libu
 */
@Entity
@Table(name = "Category")
public class Category implements Serializable {

  @Id
  @Column(name = "category_id", unique = true, nullable = false)
  @GeneratedValue(strategy = GenerationType.AUTO)
  private int categoryId;

  @NotEmpty
  @Column(name = "category_title", unique = true, nullable = false)
  private String title;

  @OneToMany(fetch = FetchType.EAGER, mappedBy = "category", cascade = CascadeType.ALL)
  private Set<Item> items = new HashSet<>();

  public Integer getCategoryId() {
    return categoryId;
  }

  public void setCategoryId(final Integer categoryId) {
    this.categoryId = categoryId;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(final String title) {
    this.title = title;
  }

  public Set<Item> getItems() {
    return items;
  }

  public void setItems(final Set<Item> items) {
    this.items = items;
  }

  public Category() {
  }

  public Category(final int categoryId,final String title) {
    this.categoryId=categoryId;
    this.title = title;
  }

  @Override
  public boolean equals(final Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    final Category category = (Category) o;
    return Objects.equals(categoryId, category.categoryId);
  }

  @Override
  public int hashCode() {
    return Objects.hash(categoryId);
  }
}

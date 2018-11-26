package com.idealo.springmvc.model;

import java.io.Serializable;
import java.util.Objects;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

/**
 * Entity bean with JPA annotations Hibernate provides JPA implementation
 *
 * @author Libu
 */
@Entity
@Table(name = "Item")
public class Item implements Serializable {

  @Id
  @Column(name = "item_id", unique = true, nullable = false)
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer itemId;

  @NotEmpty
  @Column(name = "item_title", unique = true, nullable = false)
  private String title;

  @NotEmpty
  @Column(name = "item_text", unique = true, nullable = false)
  private String text;

  @NotNull
  @Column(name = "item_price", unique = true, nullable = false)
  private Integer price;

  @NotEmpty
  @ManyToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "category_id", nullable = false)
  private Category category;

  public Integer getItemId() {
    return itemId;
  }

  public void setItemId(final Integer itemId) {
    this.itemId = itemId;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(final String title) {
    this.title = title;
  }

  public String getText() {
    return text;
  }

  public void setText(final String text) {
    this.text = text;
  }

  public Integer getPrice() {
    return price;
  }

  public void setPrice(final Integer price) {
    this.price = price;
  }

  public Category getCategory() {
    return category;
  }

  public void setCategory(final Category category) {
    this.category = category;
  }

  public Item() {

  }

  public Item(final String title, final String text, final Integer price) {
    this.title = title;
    this.text = text;
    this.price = price;
  }

  @Override
  public boolean equals(final Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    final Item item = (Item) o;
    return Objects.equals(itemId, item.itemId);
  }

  @Override
  public int hashCode() {
    return Objects.hash(itemId);
  }
}

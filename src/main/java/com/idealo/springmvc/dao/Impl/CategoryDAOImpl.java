package com.idealo.springmvc.dao.Impl;

import java.util.List;
import java.util.Optional;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.idealo.springmvc.dao.AbstractDao;
import com.idealo.springmvc.dao.ICategoryDAO;
import com.idealo.springmvc.model.Category;
import com.idealo.springmvc.model.Item;

/**
 * Repository implementation that creates and list categories on user input.
 *
 * @author Libu Mathew.
 * @version $Id:$
 */

@Repository("categoryDAO")
public class CategoryDAOImpl extends AbstractDao<Integer, Category> implements ICategoryDAO {


  @Autowired
  private SessionFactory sessionFactory;

  @Override
  public Optional<Category> findById(final int categoryId) {
    Category category = getByKey(categoryId);
    return Optional.ofNullable(category);
  }

  /**
   * Save the category by user.
   */
  @Override
  public void save(final Category category) {
    persist(category);
  }


  /**
   * List the category by user.
   *
   * @return the list of categorys
   */
  @Override
  public List<Category> findAllCategories() {
    Criteria criteria = createEntityCriteria().addOrder(Order.asc("title"));
    criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
    return (List<Category>) criteria.list();
  }

  @Override
  public void updateCategory(final Category category) {
    update(category);
  }

  @Override
  public void saveItemForCategory(final Category category) {
    persist(category);

  }

  @Override
  public Optional<List<Item>> findItemByCategory(final int categoryId) {
    Criteria criteria=sessionFactory.getCurrentSession().createCriteria(Item.class);
    criteria.add(Restrictions.eq("category_id",categoryId));
    List<Item> items=criteria.list();
    return Optional.ofNullable(items);
  }

}

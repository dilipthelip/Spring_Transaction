package com.learnSpringTransaction.myapp.dao;

import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.transaction.annotation.Transactional;

public class BookShopTransactionalAnnotation extends JdbcDaoSupport implements BookShop {

	@Transactional
    public void purchase(String isbn, String username) {

      int price = getJdbcTemplate().queryForInt(
          "SELECT PRICE FROM BOOK WHERE ISBN = ?",
          new Object[] { isbn });

      getJdbcTemplate().update(
          "UPDATE BOOK_STOCK SET STOCK = STOCK - 1 "+
          "WHERE ISBN = ?", new Object[] { isbn });

      getJdbcTemplate().update(
          "UPDATE ACCOUNT SET BALANCE = BALANCE - ? "+
          "WHERE USERNAME = ?",
          new Object[] { price, username });
    }
	
	
	@Override
	public Integer purchaseReturn(String isbn, String username) {
		// TODO Auto-generated method stub
		return null;
	}

}

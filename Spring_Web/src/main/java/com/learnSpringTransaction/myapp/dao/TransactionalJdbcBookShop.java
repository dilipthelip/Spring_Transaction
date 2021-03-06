package com.learnSpringTransaction.myapp.dao;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

public class TransactionalJdbcBookShop  extends JdbcDaoSupport implements BookShop{
	
	  private PlatformTransactionManager transactionManager;

	public PlatformTransactionManager getTransactionManager() {
		return transactionManager;
	}

	public void setTransactionManager(PlatformTransactionManager transactionManager) {
		this.transactionManager = transactionManager;
	}

	@Override
	public void purchase(String isbn, String username) {
		// TODO Auto-generated method stub
		  TransactionDefinition def = new DefaultTransactionDefinition();
	        TransactionStatus status = transactionManager.getTransaction(def);

	        try {
	            int price = getJdbcTemplate().queryForInt(
	                    "SELECT PRICE FROM BOOK WHERE ISBN = ?",
	                    new Object[] { isbn });

	            getJdbcTemplate().update(
	                    "UPDATE BOOK_STOCK SET STOCK = STOCK - 1 "+
	                    "WHERE ISBN = ?", new Object[] { isbn });

	            getJdbcTemplate().update(
	                    "UPDATE ACCOUNT1 SET BALANCE = BALANCE - ? "+
	                    "WHERE USERNAME = ?",
	                    new Object[] { price, username });

	            transactionManager.commit(status);
	        } catch (DataAccessException e) {
	            transactionManager.rollback(status);
	            throw e;
	        }
		
	}

	@Override
	public Integer purchaseReturn(String isbn, String username) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void increaseStock(String isbn, int stock) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int checkStock(String isbn) {
		// TODO Auto-generated method stub
		return 0;
	}

}

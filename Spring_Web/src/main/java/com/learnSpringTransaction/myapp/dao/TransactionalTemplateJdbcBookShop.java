package com.learnSpringTransaction.myapp.dao;

import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;

public class TransactionalTemplateJdbcBookShop extends JdbcDaoSupport implements BookShop {
	
	 private PlatformTransactionManager transactionManager;
	 
	    private TransactionTemplate transactionTemplate;

		public TransactionTemplate getTransactionTemplate() {
			return transactionTemplate;
		}

		public void setTransactionTemplate(TransactionTemplate transactionTemplate) {
			this.transactionTemplate = transactionTemplate;
		}

		public PlatformTransactionManager getTransactionManager() {
			return transactionManager;
		}

		public void setTransactionManager(PlatformTransactionManager transactionManager) {
			this.transactionManager = transactionManager;
		}

	@Override
	public void purchase(final String isbn, final String username) { 
		//TransactionTemplate transactionTemplate =    new TransactionTemplate(transactionManager);
		try {
			transactionTemplate.execute(new TransactionCallbackWithoutResult() {

				protected void doInTransactionWithoutResult(
						TransactionStatus status) {

					int price = getJdbcTemplate().queryForObject(
							"SELECT PRICE FROM BOOK WHERE ISBN = ?",
							new Object[] { isbn }, Integer.class);

					getJdbcTemplate().update(
							"UPDATE BOOK_STOCK SET STOCK = STOCK - 1 "+
									"WHERE ISBN = ?", new Object[] { isbn });

					getJdbcTemplate().update(
							"UPDATE ACCOUNT1 SET BALANCE = BALANCE - ? "+
									"WHERE USERNAME = ?",
									new Object[] { price, username });
				}
			});
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
}

	}
	
	
	@Override
	public Integer purchaseReturn(final String isbn, final String username) { 
		//TransactionTemplate transactionTemplate =    new TransactionTemplate(transactionManager);
		try {
			return transactionTemplate.execute(new TransactionCallback<Integer>() {

				protected void doInTransactionWithoutResult(
						TransactionStatus status) {

					int price = getJdbcTemplate().queryForObject(
							"SELECT PRICE FROM BOOK WHERE ISBN = ?",
							new Object[] { isbn }, Integer.class);

					getJdbcTemplate().update(
							"UPDATE BOOK_STOCK SET STOCK = STOCK - 1 "+
									"WHERE ISBN = ?", new Object[] { isbn });

					getJdbcTemplate().update(
							"UPDATE ACCOUNT1 SET BALANCE = BALANCE - ? "+
									"WHERE USERNAME = ?",
									new Object[] { price, username });
				}

				@Override
				public Integer doInTransaction(TransactionStatus arg0) {
					// TODO Auto-generated method stub
					return new Integer(0);
				}
			});
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
}
		return null;

	}
	
}

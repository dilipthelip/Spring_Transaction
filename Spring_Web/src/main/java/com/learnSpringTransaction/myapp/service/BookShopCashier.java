package com.learnSpringTransaction.myapp.service;

import java.io.IOException;
import java.util.List;

import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.learnSpringTransaction.myapp.dao.BookShop;

public class BookShopCashier implements Cashier {
	
	private BookShop bookShop;
	
	
	public BookShop getBookShop() {
		return bookShop;
	}


	public void setBookShop(BookShop bookShop) {
		this.bookShop = bookShop;
	}
	
	@Transactional(
            propagation = Propagation.REQUIRES_NEW,
            rollbackFor = IOException.class,
            noRollbackFor = ArithmeticException.class)
	public void checkout(List<String> isbns, String username) {
		// TODO Auto-generated method stub
		 for (String isbn : isbns) {
	            bookShop.purchase(isbn, username);
	        }
	}

}

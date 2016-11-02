package com.learnSpringTransaction.myapp.service;

import java.util.List;

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
	
	 @Transactional
	public void checkout(List<String> isbns, String username) {
		// TODO Auto-generated method stub
		 for (String isbn : isbns) {
	            bookShop.purchase(isbn, username);
	        }
	}

}

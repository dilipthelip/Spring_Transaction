package com.learnSpringTransaction.myapp.dao;

public interface BookShop {

	public void purchase(String isbn, String username);

	Integer purchaseReturn(String isbn, String username);

	public void increaseStock(String isbn, int stock);
	public int checkStock(String isbn);

}

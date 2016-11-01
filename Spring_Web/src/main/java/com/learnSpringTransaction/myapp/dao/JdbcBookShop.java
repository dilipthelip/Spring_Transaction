package com.learnSpringTransaction.myapp.dao;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

@Configuration(value="bookShopImpl")
public class JdbcBookShop implements BookShop{
	
	//@Autowired
	private DataSource dataSource;

	@Override
	/**
	 * This method is to purchase a product. 
	 */
	public void purchase(String isbn, String username) {
		// TODO Auto-generated method stub
		 Connection conn = null;
	        try {
	            conn = dataSource.getConnection();

	            PreparedStatement stmt1 = conn.prepareStatement(
	                    "SELECT PRICE FROM BOOK WHERE ISBN = ?");
	            stmt1.setString(1, isbn);
	            ResultSet rs = stmt1.executeQuery();
	            rs.next();
	            int price = rs.getInt("PRICE");
	            stmt1.close();
	            System.out.println("After Select from Book ");
	            
	            PreparedStatement stmt2 = conn.prepareStatement(
	                    "UPDATE BOOK_STOCK SET STOCK = STOCK - 1 "+
	                    "WHERE ISBN = ?");
	            stmt2.setString(1, isbn);
	            stmt2.executeUpdate();
	            stmt2.close();
	            System.out.println("After Update  BOOK_STOCK ");
	            
	            PreparedStatement stmt3 = conn.prepareStatement(
	                    "UPDATE ACCOUNT1 SET BALANCE = BALANCE - ? "+
	                    "WHERE USERNAME = ?");
	            stmt3.setInt(1, price);
	            stmt3.setString(2, username);
	            stmt3.executeUpdate();
	            System.out.println("After Select from Book ");
	            stmt3.close();
	        } catch (SQLException e) {
	        	System.out.println("Inside Exception Block : " + e);
	            throw new RuntimeException(e);
	        } finally {
	            if (conn != null) {
	                try {
	                    conn.close();
	                } catch (SQLException e) {}
	            }
	        }
	    }

	public DataSource getDataSource() {
		return dataSource;
	}

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	@Override
	public Integer purchaseReturn(String isbn, String username) {
		// TODO Auto-generated method stub
		return null;
	}

}

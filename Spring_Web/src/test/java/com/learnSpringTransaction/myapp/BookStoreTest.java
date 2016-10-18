package com.learnSpringTransaction.myapp;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.learnSpringTransaction.myapp.dao.BookShop;

@Configuration
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml"})
public class BookStoreTest {
	
	@Autowired
	private BookShop bookShop;
	
	@Test
	public void purchase(){
		
		bookShop.purchase("0001", "user1");
		
	}
}

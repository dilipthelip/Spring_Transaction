package com.learnSpringTransaction.myapp.datasource;

import java.sql.Driver;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.transaction.PlatformTransactionManager;

import com.learnSpringTransaction.myapp.dao.BookShop;
import com.learnSpringTransaction.myapp.dao.JdbcBookShop;
import com.learnSpringTransaction.myapp.dao.TransactionalJdbcBookShop;

@Configuration
public class BookstoreConfiguration {

	@Bean(name="dataSource")
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(Driver.class.getName());
        dataSource.setUrl("jdbc:mysql://localhost:3306/world");
        dataSource.setUsername("root");
        dataSource.setPassword("password");
        return dataSource;
    }

    /*@Bean(name="bookShop")
    public BookShop bookShop() {
        JdbcBookShop bookShop = new JdbcBookShop();
        bookShop.setDataSource(dataSource());
        return bookShop;
    }*/
	
	@Bean(name="bookShop")
    public BookShop bookShop() {
		 TransactionalJdbcBookShop bookShop = new TransactionalJdbcBookShop();
	      bookShop.setDataSource(dataSource());
	      bookShop.setTransactionManager(transactionManager());
	      return bookShop;
    }
	
    
    @Bean
    public PlatformTransactionManager transactionManager() {
        DataSourceTransactionManager transactionManager = new DataSourceTransactionManager();
        transactionManager.setDataSource(dataSource());
        return transactionManager;
    }
}

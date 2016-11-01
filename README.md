# Spring_Transaction

This repo is about differnt types of Spring Transactions.

TransactionalJdbcBookShop.java -> Check this class for the programmatic level of handling the transaction.
BookstoreConfiguration -> This has the configuration set up to declate the datasource and transaction manager set up.

TransactionTemplate -> The main benefit is that the responsibility of starting, rolling back, or committing the transaction has been removed.  
**TransactionCallbackWithoutResult** -> This is for the dao methods which doesn't return any value.  
**TransactionCallback** -> This is for the dao methods which return some value as a response. 

**Annotation driven Transaction management :**  

You may apply the @Transactional annotation at the method level or the class level.When applying this annotation to a class, all of the public methods within this class will be defined as transactional.  

BookShopTransactionalAnnotation  
BookShopTransactionalAnnotationTest  

In fact, you can omit the transaction-manager attribute in the <tx:annotation-driven> element if your transaction manager has the name transactionManager.  

Xml Based ->     <tx:annotation-driven transaction-manager="transactionManager"/>  

Java Configuratoion -> @EnableTransactionManagement. This annotation is the equivalent of the <tx:annotation-driven />in XML based configuration.  



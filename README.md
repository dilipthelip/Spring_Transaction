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


**Setting the Propagation Transaction Attribute:**  

Different types of Propogation Attributes are listed below:  

**Required**		->	This is by default. This means there will be only one transaction.  
Required_NEW**	->	It indicates that the method must start a new transaction and run within its new transaction. If there’s an existing transaction in progress, it should be suspended first  
**Isolation**		->	When multiple transactions of the same application or different applications are operating concurrently on the same dataset, many unexpected problems may arise.  
**Dirty read:** For two transactions T1 and T2, T1 reads a field that has been updated by T2 but not yet committed. Later, if T2 rolls back, the field read by T1 will be temporary and invalid.  
**Nonrepeatable read:** For two transactions T1 and T2, T1 reads a field and then T2 updates the field. Later, if T1 reads the same field again, the value will be different.  
**Phantom read:** For two transactions T1 and T2, T1 reads some rows from a table and then T2 inserts new rows into the table. Later, if T1 reads the same table again, there will be additional rows.   
**Lost updates:** For two transactions T1 and T2, they both select a row for update, and based on the state of that row, make an update to it. Thus, one overwrites the other when the second transaction to commit should have waited until the first one committed before performing its selection.  

**Note that transaction isolation is supported by the underlying database engine but not an application or a framework. However, not all database engines support all these isolation levels.**  






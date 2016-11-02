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
**Required_NEW**	->	It indicates that the method must start a new transaction and run within its new transaction. If there’s an existing transaction in progress, it should be suspended first  
**Isolation**		->	When multiple transactions of the same application or different applications are operating concurrently on the same dataset, many unexpected problems may arise.The problems are listed below.    
**Dirty read:** For two transactions T1 and T2, T1 reads a field that has been updated by T2 but not yet committed. Later, if T2 rolls back, the field read by T1 will be temporary and invalid.  
**Nonrepeatable read:** For two transactions T1 and T2, T1 reads a field and then T2 updates the field. Later, if T1 reads the same field again, the value will be different.  
**Phantom read:** For two transactions T1 and T2, T1 reads some rows from a table and then T2 inserts new rows into the table. Later, if T1 reads the same table again, there will be additional rows.   
**Lost updates:** For two transactions T1 and T2, they both select a row for update, and based on the state of that row, make an update to it. Thus, one overwrites the other when the second transaction to commit should have waited until the first one committed before performing its selection.  

**Note that transaction isolation is supported by the underlying database engine but not an application or a framework. However, not all database engines support all these isolation levels.**  

Below are the different Isolation levels:  

**DEFAULT:**  
Uses the default isolation level of the underlying database. For most databases, the default isolation level is READ_COMMITTED.  

**READ_UNCOMMITTED:**  

Allows a transaction to read uncommitted changes by other transactions. The dirty read, nonrepeatable read, and phantom read problems may occur.  

**READ_COMMITTED:**  

Allows a transaction to read only those changes that have been committed by other transactions. The dirty read problem can be avoided, but the nonrepeatable read and phantom read problems may still occur.  

**REPEATABLE_READ:**  

Ensures that a transaction can read identical values from a field multiple times. For the duration of this transaction, updates made by other transactions to this field are prohibited. The dirty read and nonrepeatable read problems can be avoided, but the phantom read problem may still occur.  

**SERIALIZABLE:**  

Ensures that a transaction can read identical rows from a table multiple times. For the duration of this transaction, inserts, updates, and deletes made by other transactions to this table are prohibited. All the concurrency problems can be avoided, but the performance will be low.  

In practice, you should always choose the lowest isolation level that can satisfy your requirements.  

**Setting the Rollback Transaction Attribute:**  

By default, only unchecked exceptions (i.e., of type RuntimeException and Error) will cause a transaction to roll back, while checked exceptions will not.  
@Transactional(
            propagation = Propagation.REQUIRES_NEW,
            rollbackFor = IOException.class,
            noRollbackFor = ArithmeticException.class)  

			
**Setting the Timeout and Read-Only Transaction Attributes:**  

Because a transaction may acquire locks on rows and tables, a long transaction will tie up resources and have an impact on overall performance. Besides, if a transaction only reads but does not update data, the database engine could optimize this transaction. You can specify these attributes to increase the performance of your application.  

@Transactional(
            isolation = Isolation.REPEATABLE_READ,
            timeout = 30,
            readOnly = true)  
			
The read-only attribute indicates that this transaction will only read but not update data. The read-only flag is just a hint to enable a resource to optimize the transaction, and a resource might not necessarily cause a failure if a write is attempted.  

The timeout transaction attribute (an integer that describes seconds) indicates how long your transaction can survive before it is forced to roll back.  








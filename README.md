# Spring_Transaction

This repo is about differnt types of Spring Transactions.

TransactionalJdbcBookShop.java -> Check this class for the programmatic level of handling the transaction.
BookstoreConfiguration -> This has the configuration set up to declate the datasource and transaction manager set up.

TransactionTemplate -> The main benefit is that the responsibility of starting, rolling back, or committing the transaction has been removed.  
**TransactionCallbackWithoutResult** -> This is for the methods which doesn't return any value.  
**TransactionCallback** -> This is for the methods which return some value as a response.  
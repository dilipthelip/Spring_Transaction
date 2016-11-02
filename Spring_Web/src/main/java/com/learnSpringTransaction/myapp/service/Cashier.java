/**
 * 
 */
package com.learnSpringTransaction.myapp.service;

import java.util.List;

/**
 * @author z001qgd
 *
 */
public interface Cashier {
	
	public void checkout(List<String> isbns, String username);

}

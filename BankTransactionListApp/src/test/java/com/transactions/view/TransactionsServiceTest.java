/**
 * 
 */
package com.transactions.view;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import com.transactions.view.service.TransactionsService;

/**
 * The TransactionsServiceTest class implements Junit functionality that simply
 * connect to the service layer(TransactionsService) , and test the service
 * layer with some pre-defined/test value. This class also provides the details
 * result for each test cases.
 * 
 * @author Sibsankar Bera
 * @version 1.0
 * @since 2018-08-31
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { TransactionsService.class })

public class TransactionsServiceTest {

	private static final Logger logger = Logger.getLogger(TransactionsServiceTest.class.getName());
	TransactionsService transactionsService = null;
	int recordcounts = 0;
	Double totalAmount = 0.0;

	/**
	 * This Junit test method assigns required resources value before use.
	 */
	@Before
	public void setup() throws Exception {
		transactionsService = new TransactionsService();
	}

	/**
	 * This Junit test method is used to test service layer method,
	 * getAllTransactionListService()
	 */

	@Test
	public void getAllTransactionListService_Test() {
		try {
			logger.info("Logger Name: getAllTransactionListService_Test() :: " + logger.getName());
			recordcounts = transactionsService.getAllTransactionListService().size();
			logger.debug("Junit Response :: recordcounts :: getAllTransactionListService_Test() :: " + recordcounts);
			assertNotEquals(0, recordcounts);
		} catch (Exception e) {
			logger.error("Junit :: Exception :: getAllTransactionListService_Test() :: ", e);
		}
	}

	/**
	 * This Junit test method is used to test service layer method,
	 * getTransactionbyType()
	 */
	@Test
	public void getTransactionbyType_Test() throws Exception {

		try {
			logger.info("Logger Name: getTransactionbyType_Test() :: " + logger.getName());
			// value of transaction type can be changed, currently it is hardcoded as
			// "SANDBOX_TAN"
			recordcounts = transactionsService.getTransactionbyType("SANDBOX_TAN").size();
			logger.debug("Junit Response :: recordcounts :: getTransactionbyType_Test() :: " + recordcounts);
			assertNotEquals(0, recordcounts);
		} catch (Exception e) {
			logger.error("Junit :: Exception :: getTransactionbyType_Test() :: ", e);
		}
	}

	/**
	 * This Junit test method is used to test service layer method,
	 * getTotalAmountbyTransactionType()
	 */
	@Test
	public void getTotalAmountbyTransactionType_Test() throws Exception {
		try {
			logger.info("Logger Name: getTotalAmountbyTransactionType_Test() :: " + logger.getName());
			// value of transaction type can be changed, currently it is hardcoded as
			// "SANDBOX_TAN"
			totalAmount = transactionsService.getTotalAmountbyTransactionType("SANDBOX_TAN");
			logger.debug("Junit Response :: totalAmount :: getTotalAmountbyTransactionType_Test() :: " + totalAmount);
			assertEquals((Double) 10.0, (Double) totalAmount);
		} catch (Exception e) {
			logger.error("Junit :: Exception :: getTotalAmountbyTransactionType_Test() :: ", e);
		}
	}

	/**
	 * This Junit test method cleans up all resources after used.
	 */
	@After
	public void cleanUp() {
		transactionsService = null;
	}
}

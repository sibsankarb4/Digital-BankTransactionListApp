package com.transactions.view;

import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import java.io.IOException;
import java.net.MalformedURLException;

import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.transactions.config.AppConfig;
import com.transactions.config.AppInitializer;
import com.transactions.view.controller.TransactionsController;
import com.transactions.view.service.TransactionsService;
import com.transactions.view.utils.TransactionConstant;

/**
 * The TransactionsControllerTest class implements Junit functionality that
 * simply connect to the controller layer (TransactionsController) and test the
 * controller layer with some pre-defined/test value. This class also provides
 * the details result for each test cases.
 * 
 * @author Sibsankar Bera
 * @version 1.0
 * @since 2018-08-31
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { TransactionsController.class, AppConfig.class, AppInitializer.class })
@EnableWebMvc
public class TransactionsControllerTest {
	private static final Logger logger = Logger.getLogger(TransactionsControllerTest.class.getName());
	private MockMvc mockMvc = null;
	String values = null;

	@InjectMocks
	private WebApplicationContext wac;

	/**
	 * This Junit test method assigns required resources value before use.
	 */
	@Before
	public void setup() throws Exception {
		values = TransactionConstant.AUTHORIZATION_CODE;
		mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
	}

	/**
	 * This Junit test method is used to test controller layer api,
	 * "/transactions/getAllTransactionList"
	 */
	@Test
	public void getAllTransactionList_Test() {
		try {
			logger.info("Logger Name: getAllTransactionList_Test() :: " + logger.getName());
			ObjectMapper mapper = new ObjectMapper();
			MvcResult result = mockMvc.perform(get("/getAllTransactionList").header("authorization_code", values))
					.andReturn();
			JsonNode root = mapper.readTree(result.getResponse().getContentAsString());
			JsonNode resultNodes = root.path("result");
			logger.debug("Junit Response :: resultNodes :: getAllTransactionList_Test() :: " + resultNodes.asText());
			assertEquals(resultNodes.asText(), "success");
		} catch (Exception e) {
			logger.error("Junit :: Exception :: getAllTransactionList_Test() ::  ", e);
		}
	}

	/**
	 * This Junit test method is used to test controller layer api,
	 * "/transactions/getTransactionsListByTransactionType/{transactionType}"
	 */
	@Test
	public void getTransactionsListByTransactionType_Test() {
		try {
			logger.info("Logger Name: getTransactionsListByTransactionType_Test() :: " + logger.getName());
			ObjectMapper mapper = new ObjectMapper();
			// value of transaction type can be changed, currently it is hardcoded as
			// "sandbox-payment"
			MvcResult result = mockMvc
					.perform(get("/getTransactionsListByTransactionType/{transactionType}", "sandbox-payment")
							.header("authorization_code", values))
					.andReturn();
			JsonNode root = mapper.readTree(result.getResponse().getContentAsString());
			JsonNode resultNodes = root.path("result");
			logger.debug("Junit Response :: resultNodes :: getTransactionsListByTransactionType_Test() :: "
					+ resultNodes.asText());
			assertEquals(resultNodes.asText(), "success");
		} catch (Exception e) {
			logger.error("Junit :: Exception :: getTransactionsListByTransactionType_Test() :: ", e);
		}
	}

	/**
	 * This Junit test method is used to test controller layer api,
	 * "/transactions/getTotalAmountByTransactionType/{transactionType}".
	 */
	@Test
	public void getTotalAmountByTransactionType_Test() {

		try {
			logger.info("Logger Name: getTotalAmountByTransactionType_Test() :: " + logger.getName());
			ObjectMapper mapper = new ObjectMapper();
			// value of transaction type can be changed, currently it is hardcoded as
			// "sandbox-payment"
			MvcResult result = mockMvc
					.perform(get("/getTotalAmountByTransactionType/{transactionType}", "sandbox-payment")
							.header("authorization_code", values))
					.andReturn();
			JsonNode root = mapper.readTree(result.getResponse().getContentAsString());
			JsonNode resultNodes = root.path("result");
			logger.debug("Junit Response :: resultNodes :: getTotalAmountByTransactionType_Test() :: "
					+ resultNodes.asText());
			assertEquals(resultNodes.asText(), "success");
		} catch (Exception e) {
			logger.error("Junit :: Exception ::getTotalAmountByTransactionType_Test() ::  ", e);
		}
	}

	/**
	 * This Junit test method cleans up all resources after used.
	 */
	@After
	public void cleanUp() {
		values = null;
		mockMvc = null;
	}
}

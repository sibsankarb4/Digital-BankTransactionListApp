package com.transactions.view.controller;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.transactions.view.service.TransactionsService;
import com.transactions.view.utils.TransactionConstant;

/**
 * The TransactionsController class implements functionality that simply connect
 * to backend service layer and provide the response to the caller, this class
 * is also responsible for control and route/redirect the request from outer
 * world.
 * 
 * @author Sibsankar Bera
 * @version 1.0
 * @since 2018-08-31
 */
@RestController()
@RequestMapping(value = "/transactions")
public class TransactionsController {

	private static final Logger logger = Logger.getLogger(TransactionsController.class.getName());

	@Autowired
	TransactionsService transactionsService;

	/**
	 * This method is responsible to get all the transactions list from backend
	 * service layer and provide the result to caller in JSON format
	 * 
	 * @param request
	 * @return Map<String, Object>
	 */
	@RequestMapping(value = "/getAllTransactionList", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public Map<String, Object> getAllTransactionList(HttpServletRequest request) {

		logger.info("Logger Name: getAllTransactionList() :: " + logger.getName());
		String token = null;

		Map<String, Object> response = new HashMap<String, Object>();

		if (request != null)
			token = request.getHeader("authorization_code");

		if (logger.isDebugEnabled()) {
			logger.debug("authorization_code Value : " + token);
		}

		/* Securing Rest API by validating authorization_code */
		try {
			if (token != null && token.equals(TransactionConstant.AUTHORIZATION_CODE)) {
				response.put("result", "success");
				response.put("data", transactionsService.getAllTransactionListService());
				logger.info("Authorization code is valid :: response prepared successfully.");

			} else {
				response.put("result", "Authorization Error 403");
				response.put("data", null);
				logger.warn("Authorization code is not valid :: Authorization Error 403");
			}
		} catch (JsonProcessingException e) {
			logger.error("JsonProcessingException : ", e);
		} catch (MalformedURLException e) {
			logger.error("MalformedURLException : ", e);
		} catch (IOException e) {
			logger.error("IOException : ", e);
		}

		logger.info("Response from Contyroller layer :: getAllTransactionList() :: " + response.toString());
		return response;

	}

	/**
	 * This method is responsible to get all the transactions list by transaction
	 * type from backend service layer and provide the result to caller in JSON
	 * format
	 * 
	 * @param transactionType
	 * @param request
	 * @return Map<String, Object>
	 */
	@RequestMapping(value = "/getTransactionsListByTransactionType/{transactionType}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public Map<String, Object> getTransactionsListByTransactionType(
			@PathVariable("transactionType") String transactionType, HttpServletRequest request) {
		logger.info("Logger Name: getTransactionsListByTransactionType() :: " + logger.getName());
		String token = null;
		Map<String, Object> response = new HashMap<String, Object>();

		if (request != null)
			token = request.getHeader("authorization_code");

		if (logger.isDebugEnabled()) {
			logger.debug("authorization_code Value : " + token);
		}

		/* Securing Rest API by validating authorization_code */
		try {
			if (token != null && token.equals(TransactionConstant.AUTHORIZATION_CODE)) {
				if (transactionType.isEmpty() || transactionType == "" || transactionType == " ") {
					transactionType = null;
				}
				response.put("result", "success");
				response.put("data", transactionsService.getTransactionbyType(transactionType));
				logger.info("Authorization code is valid :: response prepared successfully.");
			} else {
				response.put("result", "Authorization Error 403");
				response.put("data", null);
				logger.warn("Authorization code is not valid :: Authorization Error 403");
			}
		} catch (JsonProcessingException e) {
			logger.error("JsonProcessingException : ", e);
		} catch (MalformedURLException e) {
			logger.error("MalformedURLException : ", e);
		} catch (IOException e) {
			logger.error("IOException : ", e);
		}

		logger.info(
				"Response from Contyroller layer :: getTransactionsListByTransactionType() :: " + response.toString());
		return response;
	}

	/**
	 * This method is responsible to get all the total amount by transaction type
	 * from backend service layer and provide the result to caller in JSON format
	 * 
	 * @param transactionType
	 * @param request
	 * @return Map<String, Object>
	 */
	@RequestMapping(value = "/getTotalAmountByTransactionType/{transactionType}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public Map<String, Object> getTotalAmountByTransactionType(@PathVariable("transactionType") String transactionType,
			HttpServletRequest request) {

		logger.info("Logger Name: getTotalAmountByTransactionType() :: " + logger.getName());
		String token = null;
		Map<String, Object> response = new HashMap<String, Object>();

		if (request != null)
			token = request.getHeader("authorization_code");

		if (logger.isDebugEnabled()) {
			logger.debug("authorization_code Value : " + token);
		}

		/* Securing Rest API by validating authorization_code */
		try {
			if (token != null && token.equals(TransactionConstant.AUTHORIZATION_CODE)) {
				if (transactionType.isEmpty() || transactionType == "" || transactionType == " ") {
					transactionType = null;
				}
				response.put("result", "success");
				response.put("data",
						transactionType + " : " + transactionsService.getTotalAmountbyTransactionType(transactionType));
				logger.info("Authorization code is valid :: response prepared successfully.");
			} else {
				response.put("result", "Authorization Error 403");
				response.put("data", null);
				logger.warn("Authorization code is not valid :: Authorization Error 403");
			}
		} catch (JsonProcessingException e) {
			logger.error("JsonProcessingException : ", e);
		} catch (MalformedURLException e) {
			logger.error("MalformedURLException : ", e);
		} catch (IOException e) {
			logger.error("IOException : ", e);
		}
		logger.info("Response from Contyroller layer ::Total Amount: getTotalAmountByTransactionType() :: "
				+ response.toString());
		return response;
	}
}

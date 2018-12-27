package com.transactions.view.service;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.transactions.view.controller.TransactionsController;
import com.transactions.view.model.HCLFieldTransaction;
import com.transactions.view.utils.TransactionConstant;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * The TransactionsService class implements business functionality that simply
 * connect to backend data stream/API and provide the response to the controller
 * layer, this class is also responsible for data formating and data gathering
 * with the help of POJO class(HCLFieldTransaction.java).
 * 
 * @author Sibsankar Bera
 * @version 1.0
 * @since 2018-08-31
 */
@Service
public class TransactionsService {

	private static final Logger logger = Logger.getLogger(TransactionsService.class.getName());

	/* Getting api url value from application.properties file */
	@Value("${api.service.url}")
	private String apiUrl;

	/**
	 * This method is responsible to get all the transactions list from backend data
	 * stream/API and provide the result to the controller layer
	 * 
	 * @return List<HCLFieldTransaction>
	 * @throws JsonProcessingException
	 * @throws MalformedURLException
	 * @throws IOException
	 */
	public List<HCLFieldTransaction> getAllTransactionListService()
			throws JsonProcessingException, MalformedURLException, IOException {
		logger.info("Logger Name: getAllTransactionListService() :: " + logger.getName());
		ObjectMapper mapper = new ObjectMapper();

		logger.info("Open Bank API URL :: getTotalAmountbyTransactionType() :: "
				+ TransactionConstant.THIRD_PARTY_REST_API);
		JsonNode root = mapper.readTree(new URL(TransactionConstant.THIRD_PARTY_REST_API));

		List<HCLFieldTransaction> transactionList = new ArrayList<HCLFieldTransaction>();

		/* Traversing Nested JSON node to publish the relevant data */
		try {
			JsonNode transactionsNodes = root.path("transactions");

			for (JsonNode transactionsNode : transactionsNodes) {

				HCLFieldTransaction hclFieldTransaction = new HCLFieldTransaction();
				hclFieldTransaction.setId(transactionsNode.path("id").asText());

				JsonNode thisAccount_Node = transactionsNode.path("this_account");
				hclFieldTransaction.setAccountId(thisAccount_Node.path("id").asText());

				JsonNode otherAccount_Node = transactionsNode.path("other_account");
				hclFieldTransaction.setCounterpartyAccount(otherAccount_Node.path("number").asText());

				JsonNode otherAccount_holder_Node = otherAccount_Node.path("holder");
				hclFieldTransaction.setCounterpartyName(otherAccount_holder_Node.path("name").asText());
				JsonNode metadata = otherAccount_Node.path("metadata");
				hclFieldTransaction.setCounterPartyLogoPath(metadata.path("open_corporates_URL").asText());

				JsonNode details = transactionsNode.path("details");
				hclFieldTransaction.setTransactionType(details.path("type").asText());
				hclFieldTransaction.setDescription(details.path("description").asText());

				JsonNode value = details.path("value");
				hclFieldTransaction.setInstructedAmount(value.path("amount").asDouble());

				hclFieldTransaction.setInstructedCurrency(value.path("currency").asText());

				hclFieldTransaction.setTransactionAmount(value.path("amount").asDouble());
				hclFieldTransaction.setTransactionCurrency(value.path("currency").asText());
				logger.debug("Transaction Field Row Value:: " + hclFieldTransaction.toString());

				/* Adding all transaction list into one ArrayList */
				transactionList.add(hclFieldTransaction);
			}
		} catch (Exception e) {
			logger.error("Exception : getAllTransactionListService() :: ", e);
		}
		logger.info(
				"Return value from service layer :: getAllTransactionListService() :: " + transactionList.toString());
		return transactionList;
	}

	/**
	 * This method is responsible to get all the transactions list by
	 * transactionType from backend data stream/API and provide the result to the
	 * controller layer
	 * 
	 * @return List<HCLFieldTransaction>
	 * @param transactionType
	 * @throws JsonProcessingException
	 * @throws IOException
	 */

	public List<HCLFieldTransaction> getTransactionbyType(String transactionType)
			throws JsonProcessingException, IOException {

		logger.info("Logger Name: getTransactionbyType() :: " + logger.getName());
		logger.debug("Transaction Type : getTransactionbyType() :: " + transactionType);
		ObjectMapper mapper = new ObjectMapper();

		logger.info("Open Bank API URL :: getTotalAmountbyTransactionType() :: "
				+ TransactionConstant.THIRD_PARTY_REST_API);
		JsonNode root = mapper.readTree(new URL(TransactionConstant.THIRD_PARTY_REST_API));

		List<HCLFieldTransaction> transactionList = new ArrayList<HCLFieldTransaction>();

		/* Traversing Nested JSON node to publish the relevant data */
		try {
			JsonNode transactionsNodes = root.path("transactions");

			for (JsonNode transactionsNode : transactionsNodes) {

				HCLFieldTransaction hclFieldTransaction = new HCLFieldTransaction();
				JsonNode details = transactionsNode.path("details");
				hclFieldTransaction.setTransactionType(details.path("type").asText());

				/* When transactionType is null or empty */
				if (transactionType == null && details.path("type").isNull() == true) {
					hclFieldTransaction.setId(transactionsNode.path("id").asText());

					JsonNode thisAccount_Node = transactionsNode.path("this_account");
					hclFieldTransaction.setAccountId(thisAccount_Node.path("id").asText());

					JsonNode otherAccount_Node = transactionsNode.path("other_account");
					hclFieldTransaction.setCounterpartyAccount(otherAccount_Node.path("number").asText());

					JsonNode otherAccount_holder_Node = otherAccount_Node.path("holder");
					hclFieldTransaction.setCounterpartyName(otherAccount_holder_Node.path("name").asText());
					JsonNode metadata = otherAccount_Node.path("metadata");
					hclFieldTransaction.setCounterPartyLogoPath(metadata.path("open_corporates_URL").asText());

					hclFieldTransaction.setTransactionType(details.path("type").asText());
					hclFieldTransaction.setDescription(details.path("description").asText());

					JsonNode value = details.path("value");
					hclFieldTransaction.setInstructedAmount(value.path("amount").asDouble());

					hclFieldTransaction.setInstructedCurrency(value.path("currency").asText());

					hclFieldTransaction.setTransactionAmount(value.path("amount").asDouble());
					hclFieldTransaction.setTransactionCurrency(value.path("currency").asText());
					logger.debug("Transaction Field Row Value:: " + hclFieldTransaction.toString());

					/* Adding specific type of transaction list into one ArrayList */
					transactionList.add(hclFieldTransaction);

				}

				/* When transactionType is not null or empty */
				else if (transactionType != null) {

					if (hclFieldTransaction.getTransactionType().equalsIgnoreCase(transactionType.trim())) {

						logger.debug("Transaction Type :: " + transactionType);
						hclFieldTransaction.setId(transactionsNode.path("id").asText());

						JsonNode thisAccount_Node = transactionsNode.path("this_account");
						hclFieldTransaction.setAccountId(thisAccount_Node.path("id").asText());

						JsonNode otherAccount_Node = transactionsNode.path("other_account");
						hclFieldTransaction.setCounterpartyAccount(otherAccount_Node.path("number").asText());

						JsonNode otherAccount_holder_Node = otherAccount_Node.path("holder");
						hclFieldTransaction.setCounterpartyName(otherAccount_holder_Node.path("name").asText());
						JsonNode metadata = otherAccount_Node.path("metadata");
						hclFieldTransaction.setCounterPartyLogoPath(metadata.path("open_corporates_URL").asText());

						hclFieldTransaction.setTransactionType(details.path("type").asText());
						hclFieldTransaction.setDescription(details.path("description").asText());

						JsonNode value = details.path("value");
						hclFieldTransaction.setInstructedAmount(value.path("amount").asDouble());

						hclFieldTransaction.setInstructedCurrency(value.path("currency").asText());

						hclFieldTransaction.setTransactionAmount(value.path("amount").asDouble());
						hclFieldTransaction.setTransactionCurrency(value.path("currency").asText());
						logger.debug("Transaction Field Row Value:: " + hclFieldTransaction.toString());

						/* Adding specific type of transaction list into one ArrayList */
						transactionList.add(hclFieldTransaction);
					}
				}
			}
		} catch (Exception e) {
			logger.error("Exception : getTransactionbyType() :: ", e);
		}
		logger.info("Return value from service layer:: getTransactionbyType() :: " + transactionList.toString());
		return transactionList;

	}

	/**
	 * This method is responsible to get all the total amount by transactionType
	 * from backend data stream/API and provide the result to the controller layer
	 * 
	 * @return Double
	 * @param transactionType
	 * @throws JsonProcessingException
	 * @throws IOException
	 */
	public Double getTotalAmountbyTransactionType(String transactionType) throws JsonProcessingException, IOException {

		logger.info("Logger Name: getTotalAmountbyTransactionType() :: " + logger.getName());
		logger.debug("Transaction Type : getTotalAmountbyTransactionType() :: " + transactionType);
		ObjectMapper mapper = new ObjectMapper();
		Double totalAmount = 0.00;

		logger.info("Open Bank API URL :: getTotalAmountbyTransactionType() :: "
				+ TransactionConstant.THIRD_PARTY_REST_API);
		JsonNode root = mapper.readTree(new URL(TransactionConstant.THIRD_PARTY_REST_API));

		/* Traversing Nested JSON node to get/add the total amount */
		try {
			JsonNode transactionsNodes = root.path("transactions");
			for (JsonNode transactionsNode : transactionsNodes) {
				JsonNode details = transactionsNode.path("details");

				/* When transactionType is null or empty */
				if (transactionType == null && details.path("type").isNull() == true) {
					JsonNode value = details.path("value");
					Double tempAmount = value.path("amount").asDouble();
					totalAmount = totalAmount + tempAmount;

				}

				/* When transactionType is not null or empty */
				else if (transactionType != null) {

					if ((details.path("type").asText()).equalsIgnoreCase(transactionType.trim())) {
						JsonNode value = details.path("value");
						Double tempAmount = value.path("amount").asDouble();
						totalAmount = totalAmount + tempAmount;
					}
				}
			}
		} catch (Exception e) {
			logger.error("Exception : getTotalAmountbyTransactionType() :: ", e);
		}
		logger.info("Response from Service layer :: Total Amount: getTotalAmountbyTransactionType() :: " + totalAmount);
		return totalAmount;

	}
}

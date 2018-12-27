package com.transactions.view.model;

import org.apache.log4j.Logger;

import com.transactions.view.utils.TransactionConstant;

/**
 * HCLFieldTransaction class is a simple plain old java class(POJO) consists of
 * all getter and setter This class also is responsible for data formating and
 * data gathering with the help of all getter and setter methods.
 * 
 * @author Sibsankar Bera
 * @version 1.0
 * @since 2018-08-31
 */
public class HCLFieldTransaction {

	String id;
	String accountId;
	String counterpartyAccount;
	String counterpartyName;
	String counterPartyLogoPath;
	Double instructedAmount;
	String instructedCurrency;
	Double transactionAmount;
	String transactionCurrency;
	String transactionType;
	String description;
	private static final Logger logger = Logger.getLogger(HCLFieldTransaction.class.getName());

	/* Overriding toString to get/test the object value */
	@Override
	public String toString() {
		logger.info("Logger Name: toString() :: " + logger.getName());
		logger.info("HCLFieldTransaction [id=" + id + ", accountId=" + accountId + ", counterpartyAccount="
				+ counterpartyAccount + ", counterpartyName=" + counterpartyName + ", counterPartyLogoPath="
				+ counterPartyLogoPath + ", instructedAmount=" + instructedAmount + ", instructedCurrency="
				+ instructedCurrency + ", transactionAmount=" + transactionAmount + ", transactionCurrency="
				+ transactionCurrency + ", transactionType=" + transactionType + ", description=" + description
				+ ", toString()=" + super.toString() + "]");

		return ("HCLFieldTransaction [id=" + id + ", accountId=" + accountId + ", counterpartyAccount="
				+ counterpartyAccount + ", counterpartyName=" + counterpartyName + ", counterPartyLogoPath="
				+ counterPartyLogoPath + ", instructedAmount=" + instructedAmount + ", instructedCurrency="
				+ instructedCurrency + ", transactionAmount=" + transactionAmount + ", transactionCurrency="
				+ transactionCurrency + ", transactionType=" + transactionType + ", description=" + description
				+ ", toString()=" + super.toString() + "]");
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getAccountId() {
		return accountId;
	}

	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}

	public String getCounterpartyAccount() {
		return counterpartyAccount;
	}

	public void setCounterpartyAccount(String counterpartyAccount) {
		this.counterpartyAccount = counterpartyAccount;
	}

	public String getCounterpartyName() {
		return counterpartyName;
	}

	public void setCounterpartyName(String counterpartyName) {
		this.counterpartyName = counterpartyName;
	}

	public String getCounterPartyLogoPath() {
		return counterPartyLogoPath;
	}

	public void setCounterPartyLogoPath(String counterPartyLogoPath) {
		this.counterPartyLogoPath = counterPartyLogoPath;
	}

	public String getInstructedCurrency() {
		return instructedCurrency;
	}

	public void setInstructedCurrency(String instructedCurrency) {
		this.instructedCurrency = instructedCurrency;
	}

	public Double getTransactionAmount() {
		return transactionAmount;
	}

	public void setTransactionAmount(Double transactionAmount) {
		this.transactionAmount = transactionAmount;
	}

	public String getTransactionCurrency() {
		return transactionCurrency;
	}

	public void setTransactionCurrency(String transactionCurrency) {
		this.transactionCurrency = transactionCurrency;
	}

	public String getTransactionType() {
		return transactionType;
	}

	public void setTransactionType(String transactionType) {
		this.transactionType = transactionType;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Double getInstructedAmount() {
		return instructedAmount;
	}

	public void setInstructedAmount(Double instructedAmount) {
		this.instructedAmount = instructedAmount;
	}

}
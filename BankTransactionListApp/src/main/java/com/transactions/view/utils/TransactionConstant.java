package com.transactions.view.utils;

import org.apache.log4j.Logger;

/**
 * TransactionConstant class is a simple utility class of constant type, it
 * consists some constant static filed value
 * 
 * @author Sibsankar Bera
 * @version 1.0
 * @since 2018-08-31
 */
public class TransactionConstant {
	private static final Logger logger = Logger.getLogger(TransactionConstant.class.getName());
	public final static String THIRD_PARTY_REST_API = "https://apisandbox.openbankproject.com/obp/v1.2.1/banks/rbs/accounts/savings-kids-john/public/transactions";
	public final static String AUTHORIZATION_CODE = "ABCD1234TEST567XYZ";
}

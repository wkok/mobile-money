package com.wk.mobile.money.client.i18n;


import java.util.Date;

/**
 * User: werner
 * Date: 14/12/11
 * Time: 7:00 AM
 */
public interface Messages extends com.google.gwt.i18n.client.Messages {

	@DefaultMessage("''{0}'' is not a valid symbol.")
	String invalidSymbol(String symbol);

	@DefaultMessage("Last update: {0,date,medium} {0,time,medium}")
	String lastUpdate(Date timestamp);

}

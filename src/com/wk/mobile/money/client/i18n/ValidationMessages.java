package com.wk.mobile.money.client.i18n;


import com.google.gwt.i18n.client.ConstantsWithLookup;

/**
 * User: werner
 * Date: 15/02/08
 * Time: 2:40 PM
 */
public interface ValidationMessages extends ConstantsWithLookup {

	@Key("{firstname.size.message}")
	String firstname_size_message();

	@Key("{lastname.size.message}")
	String lastname_size_message();

	@Key("{email.valid.message}")
	String email_valid_message();

	@Key("{password.notmatch.message}")
	String password_notmatch_message();

	@Key("{password.contains.message}")
	String password_contains_message();

	@Key("{password.size.message}")
	String password_size_message();

	@Key("{payee.size.message}")
	String payee_size_message();

	@Key("{amount.size.message}")
	String amount_size_message();

	@Key("{category.size.message}")
	String category_size_message();

}

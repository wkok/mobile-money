package com.wk.mobile.money.client.place;

import com.google.gwt.place.shared.PlaceTokenizer;
import com.wk.mobile.base.client.place.BaseMobilePlace;

public class AccountsPlace extends BaseMobilePlace {

	public AccountsPlace(String token) {
		super(token);
	}

	public static class Tokenizer implements PlaceTokenizer<AccountsPlace> {

		public String getToken(AccountsPlace place) {
			return place.getToken();
		}

		public AccountsPlace getPlace(String token) {
			return new AccountsPlace(token);
		}
	}

}

package com.wk.mobile.money.client.place;

import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceTokenizer;
import com.wk.mobile.base.client.place.BaseMobilePlace;

public class AccountEditPlace extends BaseMobilePlace {

	public AccountEditPlace(String token) {
		super(token);
	}

	public AccountEditPlace(String token, Place returnPlace) {
		super(token, returnPlace);
	}

	public static class Tokenizer implements PlaceTokenizer<AccountEditPlace> {

		public String getToken(AccountEditPlace place) {
			return place.getToken();
		}

		public AccountEditPlace getPlace(String token) {
			return new AccountEditPlace(token);
		}
	}

}

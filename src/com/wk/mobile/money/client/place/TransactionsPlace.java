package com.wk.mobile.money.client.place;

import com.google.gwt.place.shared.PlaceTokenizer;
import com.wk.mobile.base.client.place.BaseMobilePlace;

public class TransactionsPlace extends BaseMobilePlace {

	public TransactionsPlace(String token) {
		super(token);
	}

	public static class Tokenizer implements PlaceTokenizer<TransactionsPlace> {

		public String getToken(TransactionsPlace place) {
			return place.getToken();
		}

		public TransactionsPlace getPlace(String token) {
			return new TransactionsPlace(token);
		}
	}

}

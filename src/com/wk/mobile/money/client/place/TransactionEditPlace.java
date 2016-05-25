package com.wk.mobile.money.client.place;

import com.google.gwt.place.shared.PlaceTokenizer;
import com.wk.mobile.base.client.place.BaseMobilePlace;

public class TransactionEditPlace extends BaseMobilePlace {

	public TransactionEditPlace(String token) {
		super(token);
	}

	public static class Tokenizer implements PlaceTokenizer<TransactionEditPlace> {

		public String getToken(TransactionEditPlace place) {
			return place.getToken();
		}

		public TransactionEditPlace getPlace(String token) {
			return new TransactionEditPlace(token);
		}
	}

}

package com.wk.mobile.money.client.place;

import com.google.gwt.place.shared.PlaceTokenizer;
import com.wk.mobile.base.client.place.BaseMobilePlace;

public class CategoriesPlace extends BaseMobilePlace {

	public CategoriesPlace(String token) {
		super(token);
	}

	public static class Tokenizer implements PlaceTokenizer<CategoriesPlace> {

		public String getToken(CategoriesPlace place) {
			return place.getToken();
		}

		public CategoriesPlace getPlace(String token) {
			return new CategoriesPlace(token);
		}
	}

}

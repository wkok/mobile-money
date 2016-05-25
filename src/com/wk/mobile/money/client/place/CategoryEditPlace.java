package com.wk.mobile.money.client.place;

import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceTokenizer;
import com.wk.mobile.base.client.place.BaseMobilePlace;

public class CategoryEditPlace extends BaseMobilePlace {

	public CategoryEditPlace(String token) {
		super(token);
	}

	public CategoryEditPlace(String token, Place returnPlace) {
		super(token, returnPlace);
	}

	public static class Tokenizer implements PlaceTokenizer<CategoryEditPlace> {

		public String getToken(CategoryEditPlace place) {
			return place.getToken();
		}

		public CategoryEditPlace getPlace(String token) {
			return new CategoryEditPlace(token);
		}
	}

}

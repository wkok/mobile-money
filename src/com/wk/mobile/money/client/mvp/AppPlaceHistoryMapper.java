package com.wk.mobile.money.client.mvp;

import com.google.gwt.place.shared.WithTokenizers;
import com.wk.mobile.base.client.mvp.BaseAppPlaceHistoryMapper;
import com.wk.mobile.base.client.place.DashPlace;
import com.wk.mobile.money.client.place.AccountsPlace;
import com.wk.mobile.money.client.place.CategoriesPlace;
import com.wk.mobile.money.client.place.TransactionsPlace;
import com.wk.mobile.money.client.place.*;

/**
 * PlaceHistoryMapper interface is used to attach all places which the
 * PlaceHistoryHandler should be aware of. This is done via the @WithTokenizers
 * annotation or by extending PlaceHistoryMapperWithFactory and creating a
 * separate TokenizerFactory.
 */
@WithTokenizers({
        DashPlace.Tokenizer.class,
        AccountsPlace.Tokenizer.class,
        CategoriesPlace.Tokenizer.class,
        TransactionsPlace.Tokenizer.class
})
public interface AppPlaceHistoryMapper extends BaseAppPlaceHistoryMapper {
}
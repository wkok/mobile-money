package com.wk.mobile.money.client.activity;

import com.google.gwt.place.shared.Place;
import com.smartgwt.client.data.DataSource;
import com.smartgwt.client.data.SortSpecifier;
import com.smartgwt.client.types.SortDirection;
import com.wk.mobile.base.client.activity.CRUDActivity;
import com.wk.mobile.base.client.view.CRUDView;
import com.wk.mobile.money.client.ClientFactory;

/**
 * User: werner
 * Date: 15/11/18
 * Time: 3:27 PM
 */
public class TransactionsActivity extends CRUDActivity {


    public TransactionsActivity(Place place, ClientFactory clientFactory) {
        super(place, clientFactory);
    }

    @Override
    protected SortSpecifier[] getSort() {
        return new SortSpecifier[]{new SortSpecifier("trandate", SortDirection.DESCENDING)};
    }


    @Override
    protected CRUDView getDisplay() {
        return ((ClientFactory)clientFactory).getTransactionsView();
    }

    @Override
    protected DataSource getDataSource() {
        return DataSource.get("transaction");
    }
}

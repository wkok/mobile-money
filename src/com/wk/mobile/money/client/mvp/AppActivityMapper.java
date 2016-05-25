package com.wk.mobile.money.client.mvp;

import com.google.gwt.activity.shared.Activity;
import com.google.gwt.place.shared.Place;
import com.wk.mobile.base.client.BaseClientFactory;
import com.wk.mobile.base.client.mvp.BaseAppActivityMapper;
import com.wk.mobile.base.client.place.DashPlace;
import com.wk.mobile.money.client.ClientFactory;
import com.wk.mobile.money.client.activity.*;
import com.wk.mobile.money.client.place.*;
import com.wk.mobile.money.client.activity.*;
import com.wk.mobile.money.client.place.*;
import com.wk.mobile.money.client.activity.*;
import com.wk.mobile.money.client.place.*;

public class AppActivityMapper extends BaseAppActivityMapper {


    /**
     * AppActivityMapper associates each Place with its corresponding {@link Activity}
     *
     * @param clientFactory Factory to be passed to activities
     */
    public AppActivityMapper(BaseClientFactory clientFactory) {
        super(clientFactory);
    }

    @Override
    protected Activity doGetActivity(Place place) {
        if (place instanceof DashPlace)
            return new DashActivity((DashPlace) place, (ClientFactory)clientFactory);
        else if (place instanceof AccountsPlace)
            return new AccountsActivity((AccountsPlace) place, (ClientFactory)clientFactory);
        else if (place instanceof AccountEditPlace)
            return new AccountEditActivity((AccountEditPlace) place, (ClientFactory)clientFactory);
        else if (place instanceof CategoriesPlace)
            return new CategoriesActivity((CategoriesPlace) place, (ClientFactory)clientFactory);
        else if (place instanceof CategoryEditPlace)
            return new CategoryEditActivity((CategoryEditPlace) place, (ClientFactory)clientFactory);
        else if (place instanceof TransactionsPlace)
            return new TransactionsActivity((TransactionsPlace) place, (ClientFactory)clientFactory);
        else if (place instanceof TransactionEditPlace)
            return new TransactionEditActivity((TransactionEditPlace) place, (ClientFactory)clientFactory);
        return null;
    }

}

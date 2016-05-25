package com.wk.mobile.money.client;

import com.google.gwt.activity.shared.ActivityMapper;
import com.google.gwt.core.client.GWT;
import com.google.gwt.place.shared.PlaceController;
import com.google.web.bindery.event.shared.EventBus;
import com.wk.mobile.base.client.BaseClientFactory;
import com.wk.mobile.base.client.BaseLogin;
import com.wk.mobile.base.client.mvp.BaseAppPlaceHistoryMapper;
import com.wk.mobile.money.client.mvp.AppActivityMapper;
import com.wk.mobile.money.client.mvp.AppPlaceHistoryMapper;

/**
 * User: werner
 * Date: 15/11/25
 * Time: 8:50 AM
 */
public class Login extends BaseLogin {

    @Override
    protected String appName() {
        return "My Money";
    }

    @Override
    protected String appDescription() {
        return "Expense Tracking Made Simple";
    }


    @Override
    protected void registerEventHandlers(EventBus eventBus, PlaceController placeController) {

    }

    @Override
    protected String serverURL() {
        return "https://managemy.net/money";
    }


    @Override
    protected BaseClientFactory createClientFactory() {
        return new ClientFactoryImpl();
    }

    @Override
    protected ActivityMapper createAppActivityMapper() {
        return new AppActivityMapper(clientFactory);
    }

    @Override
    protected BaseAppPlaceHistoryMapper createPlaceHistoryMapper() {
        return GWT.create(AppPlaceHistoryMapper.class);
    }

}

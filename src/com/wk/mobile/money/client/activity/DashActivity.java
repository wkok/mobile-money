package com.wk.mobile.money.client.activity;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.place.shared.Place;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.google.gwt.user.datepicker.client.CalendarUtil;
import com.smartgwt.client.data.*;
import com.smartgwt.client.types.OperatorId;
import com.wk.mobile.base.client.MobileDSCallback;
import com.wk.mobile.base.client.Session;
import com.wk.mobile.base.client.mvp.MyAbstractActivity;
import com.wk.mobile.base.client.place.DashPlace;
import com.wk.mobile.base.client.util.DS;
import com.wk.mobile.money.client.ClientFactory;
import com.wk.mobile.money.client.view.DashView;

import java.util.Date;

/**
 * User: werner
 * Date: 15/11/18
 * Time: 3:27 PM
 */
public class DashActivity extends MyAbstractActivity {

    private ClientFactory clientFactory;
    private DashView display;
    private Place place;

    public DashActivity(DashPlace place, ClientFactory clientFactory) {
        this.clientFactory = clientFactory;
        this.place = place;
    }

    public void start(AcceptsOneWidget panel, EventBus eventBus) {
        GWT.log(this.getClass().getName() + " - start");

        display = clientFactory.getDashView();
        panel.setWidget(display);

        setViewHeading();

        clientFactory.getFAB().setVisible(true);

        bind(eventBus);

        doLoad();

        display.updateFAB();
    }

    private void setViewHeading() {
        String appName = clientFactory.getAppName();
        clientFactory.getNavBrand().setText(appName);
        clientFactory.getWaterfallTitle().setText(appName);
    }

    private void doLoad() {
        GWT.log(this.getClass().getName() + " - doLoad");
        display.reset();

        loadIncome();
        loadExpenses();
    }

    private void loadIncome() {

        DSRequest requestProperties = new DSRequest();
        requestProperties.setOperationId("byCategory");

        AdvancedCriteria advancedCriteria = new AdvancedCriteria();
        advancedCriteria.addCriteria("type", "1");
        advancedCriteria.addCriteria("customer_id", Integer.toString(Session.getCustomerID()));

        Date todayLess30 = new Date();
        CalendarUtil.addDaysToDate(todayLess30, -30);
        advancedCriteria.addCriteria("trandate", OperatorId.GREATER_OR_EQUAL, todayLess30);

        DS.fetch(DataSource.get("transaction"), advancedCriteria, new MobileDSCallback(place, clientFactory) {
            @Override
            public void onSuccess(DSResponse dsResponse, Object data, DSRequest dsRequest) {
                GWT.log(this.getClass().getName() + " - onSuccess");
                display.setIncomeData(dsResponse.getData());
                clientFactory.getSplashScreen().hide();
            }
        }, requestProperties, clientFactory);
    }

    private void loadExpenses() {

        DSRequest requestProperties = new DSRequest();
        requestProperties.setOperationId("byCategory");

        AdvancedCriteria advancedCriteria = new AdvancedCriteria();
        advancedCriteria.addCriteria("type", "-1");
        advancedCriteria.addCriteria("customer_id", Integer.toString(Session.getCustomerID()));


        Date todayLess30 = new Date();
        CalendarUtil.addDaysToDate(todayLess30, -30);
        advancedCriteria.addCriteria("trandate", OperatorId.GREATER_OR_EQUAL, todayLess30);

        DS.fetch(DataSource.get("transaction"), advancedCriteria, new MobileDSCallback(place, clientFactory) {
            @Override
            public void onSuccess(DSResponse dsResponse, Object data, DSRequest dsRequest) {
                GWT.log(this.getClass().getName() + " - onSuccess");
                display.setExpensesData(dsResponse.getData());
                clientFactory.getSplashScreen().hide();
            }
        }, requestProperties, clientFactory);
    }

    private void bind(EventBus eventBus) {
    }
}

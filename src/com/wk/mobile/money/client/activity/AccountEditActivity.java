package com.wk.mobile.money.client.activity;

import com.google.gwt.user.client.ui.Widget;
import com.smartgwt.client.data.DataSource;
import com.wk.mobile.base.client.MobileCallback;
import com.wk.mobile.base.client.activity.CRUDEditActivity;
import com.wk.mobile.base.client.place.BaseMobilePlace;
import com.wk.mobile.base.client.view.CRUDEditView;
import com.wk.mobile.money.client.ClientFactory;
import com.wk.mobile.money.client.view.AccountEditView;
import gwt.material.design.client.base.HasError;

/**
 * User: werner
 * Date: 15/11/21
 * Time: 3:59 PM
 */
public class AccountEditActivity extends CRUDEditActivity {

    public AccountEditActivity(BaseMobilePlace place, ClientFactory clientFactory) {
        super(place, clientFactory);
    }

    @Override
    protected CRUDEditView getDisplay() {
        return ((ClientFactory)clientFactory).getAccountEditView();
    }

    @Override
    protected DataSource getDataSource() {
        return DataSource.get("account");
    }


    @Override
    protected void resetDisplay() {
        ((AccountEditView)display).getNameTextBox().setValue("");
    }

    @Override
    protected String getNavTitleForEdit() {
        return clientFactory.getConstants().editAccount();
    }

    @Override
    protected String getNavTitleForAdd() {
        return clientFactory.getConstants().addAccount();
    }

    @Override
    protected void setDisplayFromRecord() {
        ((AccountEditView)display).getNameTextBox().setValue(record.getAttribute("name") != null ? record.getAttribute("name") : "");
    }

    @Override
    protected void setRecordFromDisplay() {
        record.setAttribute("name", ((AccountEditView)display).getNameTextBox().getValue());
    }

    @Override
    protected void showFieldValidationError(String field, String error) {
        Widget item = display.getFormItems().get(field);
        if (item != null && item instanceof HasError) {
            ((HasError)item).setError(error);
        }
        else {
            throw new RuntimeException(field + " - " +error);
        }
    }

    @Override
    protected void bindMore() {

    }

    @Override
    protected void entitiesChanged(MobileCallback callback) {
        ((ClientFactory)clientFactory).refreshAccounts(callback);
    }

    @Override
    protected String updateOperationId() {
        return null;
    }

    @Override
    protected String validateOperationId() {
        return null;
    }


}

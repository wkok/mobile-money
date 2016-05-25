package com.wk.mobile.money.client.activity;

import com.google.gwt.user.client.ui.Widget;
import com.smartgwt.client.data.DataSource;
import com.wk.mobile.base.client.MobileCallback;
import com.wk.mobile.base.client.activity.CRUDEditActivity;
import com.wk.mobile.base.client.place.BaseMobilePlace;
import com.wk.mobile.base.client.view.CRUDEditView;
import com.wk.mobile.money.client.ClientFactory;
import com.wk.mobile.money.client.view.CategoryEditView;
import gwt.material.design.client.base.HasError;

/**
 * User: werner
 * Date: 15/11/21
 * Time: 3:59 PM
 */
public class CategoryEditActivity extends CRUDEditActivity {

    public CategoryEditActivity(BaseMobilePlace place, ClientFactory clientFactory) {
        super(place, clientFactory);
    }

    @Override
    protected CRUDEditView getDisplay() {
        return ((ClientFactory)clientFactory).getCategoryEditView();
    }

    @Override
    protected DataSource getDataSource() {
        return DataSource.get("category");
    }

    @Override
    protected void resetDisplay() {
        ((CategoryEditView)display).getNameTextBox().setValue("");
        ((CategoryEditView)display).getTypeListBox().setSelectedIndex(0);
    }

    @Override
    protected void setDisplayFromRecord() {
        setTypeList();
        ((CategoryEditView)display).getNameTextBox().setValue(record.getAttribute("name") != null ? record.getAttribute("name") : "");
        ((CategoryEditView)display).getTypeListBox().setSelectedIndex(record.getAttribute("type").equals("-1") ? 0 : 1);
    }

    private void setTypeList() {
        ((CategoryEditView)display).getTypeListBox().clear();
        ((CategoryEditView)display).getTypeListBox().addItem(clientFactory.getConstants().expense(), "-1");
        ((CategoryEditView)display).getTypeListBox().addItem(clientFactory.getConstants().income(), "1");
    }


    @Override
    protected void setRecordFromDisplay() {
        record.setAttribute("name", ((CategoryEditView)display).getNameTextBox().getValue());
        record.setAttribute("type", ((CategoryEditView)display).getTypeListBox().getItemsSelected()[0]);
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
        ((ClientFactory)clientFactory).refreshCategories(callback);
    }

    @Override
    protected String updateOperationId() {
        return null;
    }

    @Override
    protected String validateOperationId() {
        return null;
    }


    @Override
    protected String getNavTitleForEdit() {
        return clientFactory.getConstants().editCategory();
    }

    @Override
    protected String getNavTitleForAdd() {
        return clientFactory.getConstants().addCategory();
    }


}

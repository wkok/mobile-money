package com.wk.mobile.money.client.activity;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.user.client.ui.Widget;
import com.smartgwt.client.data.*;
import com.wk.mobile.base.client.MobileCallback;
import com.wk.mobile.base.client.activity.CRUDEditActivity;
import com.wk.mobile.base.client.place.BaseMobilePlace;
import com.wk.mobile.base.client.util.ListUtil;
import com.wk.mobile.base.client.view.CRUDEditView;
import com.wk.mobile.money.client.view.TransactionEditView;
import com.wk.mobile.money.client.ClientFactory;
import com.wk.mobile.money.client.event.AccountAddEvent;
import com.wk.mobile.money.client.event.CategoryAddEvent;
import gwt.material.design.client.base.HasError;
import gwt.material.design.client.ui.MaterialToast;

import java.util.Date;
import java.util.Map;

import static com.wk.mobile.base.client.widget.Midget.link;

/**
 * User: werner
 * Date: 15/11/21
 * Time: 3:59 PM
 */
public class TransactionEditActivity extends CRUDEditActivity {

    public TransactionEditActivity(BaseMobilePlace place, ClientFactory clientFactory) {
        super(place, clientFactory);
    }

    @Override
    protected CRUDEditView getDisplay() {
        return ((ClientFactory)clientFactory).getTransactionEditView();
    }

    @Override
    protected DataSource getDataSource() {
        return DataSource.get("transaction");
    }

    @Override
    protected void resetDisplay() {
        TransactionEditView display = (TransactionEditView) this.display;

        display.getAccountListBox().setSelectedIndex(0);
        display.getDateBox().clear();
        display.getDateBox().setDate(null);
        display.getPayeeTextBox().clear();
        display.getCategoryListBox().setSelectedIndex(0);
        display.getAmountFloatBox().clear();
        display.getTypeListBox().setSelectedIndex(0);
        display.getNotesTextArea().clear();
    }

    @Override
    protected void setDisplayFromRecord() {
        TransactionEditView display = (TransactionEditView) this.display;

        setAccountList();
        setCategoryList();
        setTypeList();

        if (record.getAttribute("account_id") != null) {
            display.getAccountListBox().setSelectedIndex(ListUtil.getSelectedIdx("account_id", record.getAttribute("account_id"), ((ClientFactory) clientFactory).getAccounts()));
        }
        else {
            if (((ClientFactory)clientFactory).getAccounts().length == 1) {
                display.getAccountListBox().setSelectedIndex(1);
            }
        }
        if (record.getAttribute("trandate") != null) {
            display.getDateBox().setDate(record.getAttributeAsDate("trandate"));
        }
        else {
            display.getDateBox().setDate(new Date());
        }
        if (record.getAttribute("payee") != null) {
            display.getPayeeTextBox().setValue(record.getAttribute("payee"));
        }
        else {
            display.getPayeeTextBox().clear();
        }
        if (record.getAttribute("category_id") != null) {
            display.getCategoryListBox().setSelectedIndex(ListUtil.getSelectedIdx("category_id", record.getAttribute("category_id"), ((ClientFactory)clientFactory).getCategories()));
        }
        if (record.getAttribute("amount") != null) {
            display.getAmountFloatBox().setValue(record.getAttributeAsFloat("amount"));
        }
        else {
            display.getAmountFloatBox().clear();
        }
        display.getTypeListBox().setSelectedIndex(record.getAttribute("type").equals("-1") ? 0 : 1);
        if (record.getAttribute("notes") != null && !record.getAttribute("notes").equals("")) {
            display.getNotesTextArea().setValue(record.getAttribute("notes"));
        }
        else {
            display.getNotesTextArea().clear();
        }
    }

    private void setCategoryList() {
        TransactionEditView display = (TransactionEditView) this.display;

        display.getCategoryListBox().clear();
        display.getCategoryListBox().setPlaceholder(clientFactory.getConstants().selectCategory());
        for (Record record : ((ClientFactory)clientFactory).getCategories()) {
            display.getCategoryListBox().addItem(record.getAttribute("name"), record.getAttribute("id"));
        }
        display.getCategoryListBox().addItem("--- "+clientFactory.getConstants().addCategory()+" ---", "0");
    }

    private void setAccountList() {
        TransactionEditView display = (TransactionEditView) this.display;

        display.getAccountListBox().clear();
        display.getAccountListBox().setPlaceholder(clientFactory.getConstants().selectAccount());
        for (Record record : ((ClientFactory)clientFactory).getAccounts()) {
            display.getAccountListBox().addItem(record.getAttribute("name"), record.getAttribute("id"));
        }
        display.getAccountListBox().addItem("--- "+clientFactory.getConstants().addAccount()+" ---", "0");
    }

    private void setTypeList() {
        TransactionEditView display = (TransactionEditView) this.display;
        display.getTypeListBox().clear();
        display.getTypeListBox().addItem(clientFactory.getConstants().expense(), "-1");
        display.getTypeListBox().addItem(clientFactory.getConstants().income(), "1");
    }

    @Override
    protected void setRecordFromDisplay() {
        prepareUpdateRecord();
        record.setAttribute("account_id", ((TransactionEditView)display).getAccountListBox().getItemsSelected()[0]);
        record.setAttribute("trandate", ((TransactionEditView) display).getDateBox().getDate());
        record.setAttribute("payee", ((TransactionEditView)display).getPayeeTextBox().getValue());
        record.setAttribute("category_id", ((TransactionEditView)display).getCategoryListBox().getItemsSelected()[0]);
        record.setAttribute("amount", ((TransactionEditView)display).getAmountFloatBox().getValue());
        record.setAttribute("type", ((TransactionEditView)display).getTypeListBox().getItemsSelected()[0]);
        record.setAttribute("notes", ((TransactionEditView)display).getNotesTextArea().getValue());
    }

    private void prepareUpdateRecord() {
        Map map = record.toMap();
        map.remove("account_name");
        map.remove("category_name");
        record = new Record(map);
    }

    @Override
    protected void showFieldValidationError(String field, String error) {
        Widget item = display.getFormItems().get(field);
        if (item != null && item instanceof HasError) {
            ((HasError)item).setError(error);
        }
        else {
            if (field.equals("category_id")) {
                pleaseSelectCategoryToast();
            }
            else if (field.equals("account_id")) {
                pleaseSelectAccountToast();
            }
            else {
                throw new RuntimeException(field + " - " +error);
            }
        }
    }

    @Override
    protected void bindMore() {
        addHandlerRegistration(((TransactionEditView)display).getCategoryListBox().addValueChangeHandler(new ValueChangeHandler<String>() {
            @Override
            public void onValueChange(ValueChangeEvent<String> event) {

                if (event.getValue().equals("0")) {
                    CategoryAddEvent.fire(clientFactory.getEventBus(), place);
                }
                else {
                    updateIncomeExpense(event.getValue());
                }
            }
        }));


        addHandlerRegistration(((TransactionEditView)display).getAccountListBox().addValueChangeHandler(new ValueChangeHandler<String>() {
            @Override
            public void onValueChange(ValueChangeEvent<String> event) {

                if (event.getValue().equals("0")) {
                    AccountAddEvent.fire(clientFactory.getEventBus(), place);
                }
            }
        }));
    }

    private void updateIncomeExpense(String selectedValue) {
        Record selectedCategory = findSelectedCategory(selectedValue);
        if (selectedCategory.getAttributeAsInt("type") == -1) { // Expense
            ((TransactionEditView)display).getTypeListBox().setSelectedIndex(0);
        }
        else {
            ((TransactionEditView)display).getTypeListBox().setSelectedIndex(1);
        }
    }

    private Record findSelectedCategory(String selectedValue) {
        RecordList recordList = new RecordList(((ClientFactory)clientFactory).getCategories());
        return recordList.find("id", selectedValue);
    }

    @Override
    protected void entitiesChanged(MobileCallback callback) {
        callback.onComplete();
    }

    @Override
    protected String updateOperationId() {
        return "removeIncludedFields";
    }

    @Override
    protected String validateOperationId() {
        return "removeIncludedFields";
    }


    private void pleaseSelectCategoryToast() {
        new MaterialToast(link().text(clientFactory.getConstants().add())
                .addClickHandler(new ClickHandler() {
                    @Override
                    public void onClick(ClickEvent event) {
                        CategoryAddEvent.fire(clientFactory.getEventBus(), place);
                    }
                })
                .get())
                .toast(clientFactory.getConstants().pleaseSelectACatagory());
    }

    private void pleaseSelectAccountToast() {
        new MaterialToast(link().text(clientFactory.getConstants().add())
                .addClickHandler(new ClickHandler() {
                    @Override
                    public void onClick(ClickEvent event) {
                        AccountAddEvent.fire(clientFactory.getEventBus(), place);
                    }
                })
                .get())
                .toast(clientFactory.getConstants().pleaseSelectAccount());
    }


    @Override
    protected String getNavTitleForEdit() {
        return clientFactory.getConstants().editTransaction();
    }

    @Override
    protected String getNavTitleForAdd() {
        return clientFactory.getConstants().addTransaction();
    }

}

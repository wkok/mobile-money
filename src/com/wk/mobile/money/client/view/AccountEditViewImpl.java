package com.wk.mobile.money.client.view;

import com.google.gwt.user.client.ui.HasValue;
import com.wk.mobile.base.client.view.CRUDEditViewImpl;
import com.wk.mobile.base.client.widget.FormItemsContainer;
import com.wk.mobile.money.client.ClientFactory;
import gwt.material.design.client.ui.MaterialTextBox;

import static com.wk.mobile.base.client.widget.Midget.textBox;

public class AccountEditViewImpl extends CRUDEditViewImpl implements AccountEditView {

    private MaterialTextBox nameTextBox;

    public AccountEditViewImpl(ClientFactory clientFactory) {
        super(clientFactory);
    }

    @Override
    protected void createFormItems(FormItemsContainer panel) {
        panel.add("name", createNameTextBox());
    }

    private MaterialTextBox createNameTextBox() {
        nameTextBox = textBox().placeHolder(clientFactory.getConstants().accountName()).get();
        return nameTextBox;
    }


    @Override
    public HasValue<String> getNameTextBox() {
        return nameTextBox;
    }

}

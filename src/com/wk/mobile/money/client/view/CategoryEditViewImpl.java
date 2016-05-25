package com.wk.mobile.money.client.view;

import com.google.gwt.user.client.ui.HasValue;
import com.wk.mobile.base.client.view.CRUDEditViewImpl;
import com.wk.mobile.base.client.widget.FormItemsContainer;
import com.wk.mobile.money.client.ClientFactory;
import gwt.material.design.client.ui.MaterialListBox;
import gwt.material.design.client.ui.MaterialTextBox;

import static com.wk.mobile.base.client.widget.Midget.listBox;
import static com.wk.mobile.base.client.widget.Midget.textBox;

public class CategoryEditViewImpl extends CRUDEditViewImpl implements CategoryEditView {

    private MaterialTextBox nameTextBox;
    private MaterialListBox typeListBox;


    public CategoryEditViewImpl(ClientFactory clientFactory) {
        super(clientFactory);
    }

    @Override
    protected void createFormItems(FormItemsContainer panel) {
        panel.add("name", createNameTextBox());
        panel.add("type", createTypeTextBox());
    }

    private MaterialTextBox createNameTextBox() {
        nameTextBox = textBox().placeHolder(clientFactory.getConstants().categoryName()).get();
        return nameTextBox;
    }

    private MaterialListBox createTypeTextBox() {
        typeListBox = listBox().get();
        return typeListBox;
    }


    @Override
    public HasValue<String> getNameTextBox() {
        return nameTextBox;
    }

    @Override
    public MaterialListBox getTypeListBox() {
        return typeListBox;
    }

}

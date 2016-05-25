package com.wk.mobile.money.client.view;

import com.wk.mobile.base.client.view.CRUDEditViewImpl;
import com.wk.mobile.base.client.widget.FormItemsContainer;
import com.wk.mobile.money.client.ClientFactory;
import gwt.material.design.client.ui.*;

import static com.wk.mobile.base.client.widget.Midget.*;

public class TransactionEditViewImpl extends CRUDEditViewImpl implements TransactionEditView {

    private MaterialListBox accountListBox;
    private MaterialDatePicker dateBox;
    private MaterialTextBox payeeTextBox;
    private MaterialListBox categoryListBox;
    private MaterialFloatBox amountFloatBox;
    private MaterialListBox typeListBox;
    private MaterialTextArea notesTextArea;


    public TransactionEditViewImpl(ClientFactory clientFactory) {
        super(clientFactory);
    }

    protected void createFormItems(FormItemsContainer panel) {
        panel.add("account_id", createAccountListBox());
        panel.add("trandate", createDatePicker());
        panel.add("payee", createNameTextBox());
        panel.add("category_id", createCategoryListBox());
        panel.add("amount", createAmountTextBox());
        panel.add("type", createTypeListBox());
        panel.add("notes", createNotesTextArea());
    }

    private MaterialListBox createAccountListBox() {
        accountListBox = listBox().get();
        return accountListBox;
    }

    private MaterialListBox createTypeListBox() {
        typeListBox = listBox().get();
        return typeListBox;
    }

    private MaterialListBox createCategoryListBox() {
        categoryListBox = listBox().get();
        return categoryListBox;
    }

    private MaterialTextBox createNameTextBox() {
        payeeTextBox = textBox().placeHolder(clientFactory.getConstants().payee()).get();
        return payeeTextBox;
    }

    private MaterialTextArea createNotesTextArea() {
        notesTextArea = textArea().placeHolder(clientFactory.getConstants().notes()).get();
        return notesTextArea;
    }

    private MaterialFloatBox createAmountTextBox() {
        amountFloatBox = floatBox().placeHolder(clientFactory.getConstants().amount()).get();
        return amountFloatBox;
    }

    private MaterialDatePicker createDatePicker() {
        dateBox = datePicker().id("mypicker").placeHolder(clientFactory.getConstants().date()).selectionType(MaterialDatePicker.MaterialDatePickerType.DAY).get();
        return dateBox;
    }


    @Override
    public MaterialTextBox getPayeeTextBox() {
        return payeeTextBox;
    }

    @Override
    public MaterialListBox getCategoryListBox() {
        return categoryListBox;
    }

    @Override
    public MaterialFloatBox getAmountFloatBox() {
        return amountFloatBox;
    }

    @Override
    public MaterialListBox getTypeListBox() {
        return typeListBox;
    }

    @Override
    public MaterialTextArea getNotesTextArea() {
        return notesTextArea;
    }

    @Override
    public MaterialListBox getAccountListBox() {
        return accountListBox;
    }

    @Override
    public MaterialDatePicker getDateBox() {
        return dateBox;
    }
}

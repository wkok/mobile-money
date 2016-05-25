package com.wk.mobile.money.client.view;

import com.wk.mobile.base.client.view.CRUDEditView;
import gwt.material.design.client.ui.*;


public interface TransactionEditView extends CRUDEditView {
    MaterialListBox getAccountListBox();
    MaterialDatePicker getDateBox();
    MaterialTextBox getPayeeTextBox();
    MaterialListBox getCategoryListBox();
    MaterialFloatBox getAmountFloatBox();
    MaterialListBox getTypeListBox();
    MaterialTextArea getNotesTextArea();
}

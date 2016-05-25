package com.wk.mobile.money.client.view;

import com.google.gwt.user.client.ui.HasValue;
import com.wk.mobile.base.client.view.CRUDEditView;
import gwt.material.design.client.ui.MaterialListBox;

public interface CategoryEditView extends CRUDEditView {
    HasValue<String> getNameTextBox();
    MaterialListBox getTypeListBox();
}

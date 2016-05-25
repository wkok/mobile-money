package com.wk.mobile.money.client.view;

import com.google.gwt.user.client.ui.HasValue;
import com.wk.mobile.base.client.view.CRUDEditView;

public interface AccountEditView extends CRUDEditView {
    HasValue<String> getNameTextBox();
}

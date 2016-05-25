package com.wk.mobile.money.client.view;

import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.Panel;
import com.google.gwt.user.client.ui.Widget;
import com.smartgwt.client.data.Record;

public interface DashView extends IsWidget {
    void reset();
    void setExpensesData(Record[] records);
    void setIncomeData(Record[] records);
    void updateFAB();
}

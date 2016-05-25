package com.wk.mobile.money.client;

import com.smartgwt.client.data.Record;
import com.wk.mobile.base.client.BaseClientFactory;
import com.wk.mobile.base.client.MobileCallback;
import com.wk.mobile.money.client.view.*;
import com.wk.mobile.money.client.view.*;

public interface ClientFactory extends BaseClientFactory {

	Record[] getCategories();
	void refreshCategories(MobileCallback callback);

	Record[] getAccounts();
	void refreshAccounts(MobileCallback callback);

	DashView getDashView();
	AccountsView getAccountsView();
	AccountEditView getAccountEditView();
	CategoriesView getCategoriesView();
	CategoryEditView getCategoryEditView();
	TransactionsView getTransactionsView();
	TransactionEditView getTransactionEditView();
}

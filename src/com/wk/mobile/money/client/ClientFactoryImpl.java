package com.wk.mobile.money.client;

import com.smartgwt.client.data.*;
import com.smartgwt.client.types.SortDirection;
import com.wk.mobile.base.client.BaseClientFactoryImpl;
import com.wk.mobile.base.client.MobileCallback;
import com.wk.mobile.base.client.MobileDSCallback;
import com.wk.mobile.base.client.util.DS;
import com.wk.mobile.money.client.view.*;
import com.wk.mobile.money.client.view.*;

public class ClientFactoryImpl extends BaseClientFactoryImpl implements ClientFactory {

	private Record[] accounts = new Record[0];
	private Record[] categories = new Record[0];

	private DashView dashView;
	private AccountEditView accountEditView;
	private AccountsView accountsView;
	private CategoryEditView categoryEditView;
	private CategoriesView categoriesView;
	private TransactionEditView transactionEditView;
	private TransactionsView transactionsView;


    public Record[] getCategories() {
        return categories;
    }


	public void refreshCategories(final MobileCallback callback) {
		DSRequest requestProperties = new DSRequest();
		requestProperties.setSortBy(new SortSpecifier[]{new SortSpecifier("name", SortDirection.ASCENDING)});
		DS.fetch(DataSource.get("category"), new Criteria(), new MobileDSCallback(null, this) {
            @Override
            public void onSuccess(DSResponse dsResponse, Object data, DSRequest dsRequest) {
                categories = dsResponse.getData();
                callback.onComplete();
            }
        }, requestProperties, this);
	}

    public Record[] getAccounts() {
        return accounts;
    }


	public void refreshAccounts(final MobileCallback callback) {
		DSRequest requestProperties = new DSRequest();
		requestProperties.setSortBy(new SortSpecifier[]{new SortSpecifier("name", SortDirection.ASCENDING)});
		DS.fetch(DataSource.get("account"), new Criteria(), new MobileDSCallback(null, this) {
			@Override
			public void onSuccess(DSResponse dsResponse, Object data, DSRequest dsRequest) {
				accounts = dsResponse.getData();
				callback.onComplete();
			}
		}, requestProperties, this);
	}


	public DashView getDashView() {
		if (dashView == null) {
			dashView = new DashViewImpl(this);
		}
		return dashView;
	}

	public AccountsView getAccountsView() {
		if (accountsView == null) {
			accountsView = new AccountsViewImpl(this);
		}
		return accountsView;
	}

	public AccountEditView getAccountEditView() {
		if (accountEditView == null) {
            accountEditView = new AccountEditViewImpl(this);
		}
		return accountEditView;
	}

    public CategoriesView getCategoriesView() {
        if (categoriesView == null) {
            categoriesView = new CategoriesViewImpl(this);
        }
        return categoriesView;
    }

    public CategoryEditView getCategoryEditView() {
        if (categoryEditView == null) {
            categoryEditView = new CategoryEditViewImpl(this);
        }
        return categoryEditView;
    }

    public TransactionsView getTransactionsView() {
        if (transactionsView == null) {
            transactionsView = new TransactionsViewImpl(this);
        }
        return transactionsView;
    }

    public TransactionEditView getTransactionEditView() {
        if (transactionEditView == null) {
            transactionEditView = new TransactionEditViewImpl(this);
        }
        return transactionEditView;
    }


}

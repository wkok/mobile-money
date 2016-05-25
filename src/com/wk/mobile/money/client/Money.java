package com.wk.mobile.money.client;

import com.google.gwt.activity.shared.ActivityMapper;
import com.google.gwt.core.client.GWT;
import com.google.gwt.place.shared.PlaceController;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.web.bindery.event.shared.EventBus;
import com.smartgwt.client.data.*;
import com.smartgwt.client.rpc.RPCManager;
import com.wk.mobile.base.client.Base;
import com.wk.mobile.base.client.BaseClientFactory;
import com.wk.mobile.base.client.MobileCallback;
import com.wk.mobile.base.client.event.DashEvent;
import com.wk.mobile.base.client.mvp.BaseAppPlaceHistoryMapper;
import com.wk.mobile.base.client.place.DashPlace;
import com.wk.mobile.money.client.event.*;
import com.wk.mobile.money.client.mvp.AppActivityMapper;
import com.wk.mobile.money.client.mvp.AppPlaceHistoryMapper;
import com.wk.mobile.money.client.place.*;
import gwt.material.design.client.ui.MaterialToast;

import javax.servlet.ServletContext;


/**
 * User: werner
 * Date: 15/11/15
 * Time: 12:15 PM
 */
public class Money extends Base {


    @Override
    protected String appName() {
        return "My Money";
    }

    @Override
    protected String appDescription() {
        return "Expense Tracking Made Simple";
    }

    @Override
    protected String serverURL() {
        return "https://managemy.net/money";
    }

    @Override
    protected String smartClientURLPath() {
        return "/money/sc";
    }

    @Override
    public IsWidget createSiteWrapper() {
        return new SiteWrapper(viewWrapper, clientFactory, appName());
    }


    @Override
    protected void registerEventHandlers(EventBus eventBus, final PlaceController placeController) {
        DashEvent.register(eventBus, new DashEvent.Handler() {
            public void onEvent(DashEvent event) {
                placeController.goTo(new DashPlace(""));
            }
        });

        registerAccountEvents(eventBus, placeController);
        registerCategoryEvents(eventBus, placeController);
        registerTransactionEvents(eventBus, placeController);
    }


    private void registerAccountEvents(EventBus eventBus, final PlaceController placeController) {
        AccountsEvent.register(eventBus, new AccountsEvent.Handler() {
            public void onEvent(AccountsEvent event) {
                placeController.goTo(new AccountsPlace(""));
            }
        });

        AccountAddEvent.register(eventBus, new AccountAddEvent.Handler() {
            public void onEvent(AccountAddEvent event) {
                if (((ClientFactory) clientFactory).getAccounts().length > 0) {
                    MaterialToast.fireToast(clientFactory.getConstants().atThisTimeOnlyOneAccountIsSupportedMoreAccountsWillBeAvailableSoon());
                } else {
                    placeController.goTo(new AccountEditPlace("", event.getReturnPlace()));
                }
            }
        });

        AccountEditEvent.register(eventBus, new AccountEditEvent.Handler() {
            public void onEvent(AccountEditEvent event) {
                placeController.goTo(new AccountEditPlace(event.getId().toString()));
            }
        });
    }

    private void registerCategoryEvents(EventBus eventBus, final PlaceController placeController) {
        CategoriesEvent.register(eventBus, new CategoriesEvent.Handler() {
            public void onEvent(CategoriesEvent event) {
                placeController.goTo(new CategoriesPlace(""));
            }
        });

        CategoryAddEvent.register(eventBus, new CategoryAddEvent.Handler() {
            public void onEvent(CategoryAddEvent event) {
                placeController.goTo(new CategoryEditPlace("", event.getReturnPlace()));
            }
        });

        CategoryEditEvent.register(eventBus, new CategoryEditEvent.Handler() {
            public void onEvent(CategoryEditEvent event) {
                placeController.goTo(new CategoryEditPlace(event.getId().toString()));
            }
        });
    }


    private void registerTransactionEvents(EventBus eventBus, final PlaceController placeController) {
        TransactionsEvent.register(eventBus, new TransactionsEvent.Handler() {
            public void onEvent(TransactionsEvent event) {
                placeController.goTo(new TransactionsPlace(""));
            }
        });

        TransactionAddEvent.register(eventBus, new TransactionAddEvent.Handler() {
            public void onEvent(TransactionAddEvent event) {
                placeController.goTo(new TransactionEditPlace(""));
            }
        });

        TransactionEditEvent.register(eventBus, new TransactionEditEvent.Handler() {
            public void onEvent(TransactionEditEvent event) {
                placeController.goTo(new TransactionEditPlace(event.getId().toString()));
            }
        });
    }



    @Override
    protected BaseClientFactory createClientFactory() {
        return new ClientFactoryImpl();
    }

    @Override
    protected ActivityMapper createAppActivityMapper() {
        return new AppActivityMapper(clientFactory);
    }


    @Override
    protected BaseAppPlaceHistoryMapper createPlaceHistoryMapper() {
        return GWT.create(AppPlaceHistoryMapper.class);
    }


    public void loadLookupsInBackground() {
        GWT.log(this.getClass().getName() + " - loadLookupsInBackground");
        ((ClientFactory)clientFactory).refreshCategories(new MobileCallback() {
            @Override
            public void onComplete() {
                // Yay
            }
        });
        ((ClientFactory)clientFactory).refreshAccounts(new MobileCallback() {
            @Override
            public void onComplete() {
                // Yay
            }
        });
    }

}

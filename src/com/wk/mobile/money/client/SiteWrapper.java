package com.wk.mobile.money.client;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.wk.mobile.base.client.BaseClientFactory;
import com.wk.mobile.base.client.BaseSiteWrapper;
import com.wk.mobile.base.client.widget.Midget;
import com.wk.mobile.money.client.event.AccountsEvent;
import com.wk.mobile.money.client.event.CategoriesEvent;
import com.wk.mobile.money.client.event.TransactionsEvent;
import com.wk.mobile.money.client.event.*;
import gwt.material.design.client.constants.*;
import gwt.material.design.client.ui.*;

import static com.wk.mobile.base.client.widget.Midget.*;

public class SiteWrapper extends BaseSiteWrapper {


    public SiteWrapper(com.wk.mobile.base.client.widget.OneWidget viewWrapper, BaseClientFactory clientFactory, String appName) {
        super(viewWrapper, clientFactory, appName);
    }


    @Override
    protected void addSideNavExtras(Midget<MaterialSideNav> midgetSideNav) {

        midgetSideNav
                .add(link().iconType(IconType.RECEIPT).iconPosition(IconPosition.LEFT)
                        .text(clientFactory.getConstants().transactions()).textColor("blue")
                        .waves(WavesType.LIGHT)
                        .addClickHandler(new ClickHandler() {
                            public void onClick(ClickEvent event) {
                                TransactionsEvent.fire(clientFactory.getEventBus());
                                clientFactory.getSideNav().hide(clientFactory.getSideNav().getElement());
                            }
                        })
                        .get())
                .add(link().iconType(IconType.COLLECTIONS).iconPosition(IconPosition.LEFT)
                        .text(clientFactory.getConstants().categories()).textColor("blue")
                        .waves(WavesType.LIGHT)
                        .addClickHandler(new ClickHandler() {
                            public void onClick(ClickEvent event) {
                                CategoriesEvent.fire(clientFactory.getEventBus());
                                clientFactory.getSideNav().hide(clientFactory.getSideNav().getElement());
                            }
                        })
                        .get())
                .add(link().iconType(IconType.CREDIT_CARD).iconPosition(IconPosition.LEFT)
                        .text(clientFactory.getConstants().accounts()).textColor("blue")
                        .waves(WavesType.LIGHT)
                        .addClickHandler(new ClickHandler() {
                            public void onClick(ClickEvent event) {
                                AccountsEvent.fire(clientFactory.getEventBus());
                                clientFactory.getSideNav().hide(clientFactory.getSideNav().getElement());
                            }
                        })
                        .get());

    }


}

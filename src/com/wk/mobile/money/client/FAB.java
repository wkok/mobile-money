package com.wk.mobile.money.client;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Widget;
import com.wk.mobile.base.client.BaseClientFactory;
import com.wk.mobile.base.client.BaseFAB;
import com.wk.mobile.base.client.widget.Midget;
import com.wk.mobile.money.client.event.AccountAddEvent;
import com.wk.mobile.money.client.event.CategoryAddEvent;
import com.wk.mobile.money.client.event.TransactionAddEvent;
import gwt.material.design.client.constants.ButtonSize;
import gwt.material.design.client.constants.ButtonType;
import gwt.material.design.client.constants.IconType;
import gwt.material.design.client.constants.Position;
import gwt.material.design.client.ui.MaterialButton;
import gwt.material.design.client.ui.MaterialTooltip;

/**
 * User: werner
 * Date: 15/12/11
 * Time: 8:09 PM
 */
public class FAB extends BaseFAB {


    public static Widget createTransactionAddFABItem(int index, final BaseClientFactory clientFactory) {

        MaterialButton button = Midget.button().type(ButtonType.FLOATING)
                .backgroundColor(calcBackgroundColor(index))
                .iconType(index == 0 ? IconType.ADD : IconType.RECEIPT)
                .addClickHandler(new ClickHandler() {
                    public void onClick(ClickEvent event) {
                        TransactionAddEvent.fire(clientFactory.getEventBus());
                    }
                })
                .get();

        if (index == 0) {
            button.setSize(ButtonSize.LARGE);
        }

        Midget<MaterialTooltip> toolTip = Midget.toolTip();
        toolTips.add(toolTip);

        return toolTip
                .add(button)
                .position(Position.LEFT)
                .text(clientFactory.getConstants().addTransaction()).get().asWidget();
    }

    public static Widget createCategoryAddFABItem(int index, final BaseClientFactory clientFactory) {

        MaterialButton button = Midget.button().type(ButtonType.FLOATING)
                .backgroundColor(calcBackgroundColor(index))
                .iconType(index == 0 ? IconType.ADD : IconType.COLLECTIONS)
                .addClickHandler(new ClickHandler() {
                    public void onClick(ClickEvent event) {
                        CategoryAddEvent.fire(clientFactory.getEventBus());
                    }
                })
                .get();

        if (index == 0) {
            button.setSize(ButtonSize.LARGE);
        }

        Midget<MaterialTooltip> toolTip = Midget.toolTip();
        toolTips.add(toolTip);

        return toolTip
                .add(button)
                .position(Position.LEFT)
                .text(clientFactory.getConstants().addCategory()).get().asWidget();
    }

    public static Widget createAccountAddFABItem(int index, final BaseClientFactory clientFactory) {

        MaterialButton button = Midget.button().type(ButtonType.FLOATING)
                .backgroundColor(calcBackgroundColor(index))
                .iconType(index == 0 ? IconType.ADD : IconType.CREDIT_CARD)
                .addClickHandler(new ClickHandler() {
                    public void onClick(ClickEvent event) {
                        AccountAddEvent.fire(clientFactory.getEventBus());
                    }
                })
                .get();

        if (index == 0) {
            button.setSize(ButtonSize.LARGE);
        }

        Midget<MaterialTooltip> toolTip = Midget.toolTip();
        toolTips.add(toolTip);

        return toolTip
                .add(button)
                .position(Position.LEFT)
                .text(clientFactory.getConstants().addAccount()).get().asWidget();
    }

}

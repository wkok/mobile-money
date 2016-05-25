package com.wk.mobile.money.client.view;

import com.google.gwt.user.client.ui.Widget;
import com.smartgwt.client.data.Record;
import com.wk.mobile.money.client.FAB;
import com.wk.mobile.base.client.view.CRUDViewImpl;
import com.wk.mobile.money.client.ClientFactory;
import com.wk.mobile.money.client.event.AccountEditEvent;

import static com.wk.mobile.base.client.widget.Midget.fabList;
import static com.wk.mobile.base.client.widget.Midget.label;

public class AccountsViewImpl extends CRUDViewImpl implements AccountsView {

    public AccountsViewImpl(ClientFactory clientFactory) {
        super(clientFactory);
    }

    @Override
    protected Widget displayLine1(Record record) {
        return label().text(record.getAttribute("name")).get();
    }

    @Override
    protected Widget displayLine2(Record record) {
        return label().get();
    }

    @Override
    protected Widget displayLine3(Record record) {
        return label().get();
    }

    protected Widget displayLine4(Record record) {
        return label().get();
    }

    @Override
    protected void editClicked(Record record) {
        AccountEditEvent.fire(record.getAttributeAsInt("id"), clientFactory.getEventBus());
    }



    @Override
    public void updateFAB() {
        clientFactory.getFAB().clear();
        clientFactory.getFAB().add(FAB.createAccountAddFABItem(0, clientFactory));
        clientFactory.getFAB().add(fabList()
                .add(FAB.createCategoryAddFABItem(2, clientFactory))
                .add(FAB.createTransactionAddFABItem(1, clientFactory))
                .get());
    }

    @Override
    protected String cutOutDescription() {
        return clientFactory.getConstants().createNewTransactionalAccountsUsingTheRedAddButtonAtTheBottomRightOfTheScreen();
    }

    @Override
    protected String cutOutTitle() {
        return clientFactory.getConstants().noAccountsYet();
    }

}

package com.wk.mobile.money.client.view;

import com.google.gwt.dom.client.Style;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.i18n.client.NumberFormat;
import com.google.gwt.user.client.ui.Widget;
import com.smartgwt.client.data.Record;
import com.wk.mobile.money.client.FAB;
import com.wk.mobile.base.client.view.CRUDViewImpl;
import com.wk.mobile.money.client.event.TransactionEditEvent;
import com.wk.mobile.money.client.ClientFactory;

import static com.wk.mobile.base.client.widget.Midget.*;

public class TransactionsViewImpl extends CRUDViewImpl implements TransactionsView {

    public TransactionsViewImpl(ClientFactory clientFactory) {
        super(clientFactory);
    }

    @Override
    protected Widget displayLine1(Record record) {
        return label().fontSize(0.7, Style.Unit.EM)
                .text(DateTimeFormat.getFormat("EEE, MMM d, yyyy").format(record.getAttributeAsDate("trandate")))
                .get();
    }

    @Override
    protected Widget displayLine2(Record record) {
        return label().fontSize(1.1, Style.Unit.EM)
                .text(NumberFormat.getFormat("0.00").format(record.getAttributeAsFloat("amount")))
                .get();
    }

    @Override
    protected Widget displayLine3(Record record) {
        return label().fontSize(0.8, Style.Unit.EM).text(record.getAttribute("payee")).get();
    }

    @Override
    protected Widget displayLine4(Record record) {
        return label().fontSize(0.7, Style.Unit.EM).text(record.getAttribute("category_name")).get();
    }

    @Override
    public void updateFAB() {
        clientFactory.getFAB().clear();
        clientFactory.getFAB().add(FAB.createTransactionAddFABItem(0, clientFactory));
        clientFactory.getFAB().add(fabList()
                .add(FAB.createAccountAddFABItem(2, clientFactory))
                .add(FAB.createCategoryAddFABItem(1, clientFactory))
                .get());
    }

    @Override
    protected void editClicked(Record record) {
        TransactionEditEvent.fire(record.getAttributeAsInt("id"), clientFactory.getEventBus());
    }


    @Override
    protected String cutOutDescription() {
        return clientFactory.getConstants().captureNewTransactionsUsingTheRedAddButtonAtTheBottomRightOfTheScreen();
    }

    @Override
    protected String cutOutTitle() {
        return clientFactory.getConstants().noTransactionsYet();
    }


}

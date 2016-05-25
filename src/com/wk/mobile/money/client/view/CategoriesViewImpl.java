package com.wk.mobile.money.client.view;

import com.google.gwt.dom.client.Style;
import com.google.gwt.user.client.ui.Widget;
import com.smartgwt.client.data.Record;
import com.wk.mobile.base.client.view.CRUDViewImpl;
import com.wk.mobile.money.client.event.CategoryEditEvent;
import com.wk.mobile.money.client.ClientFactory;
import com.wk.mobile.money.client.FAB;

import static com.wk.mobile.base.client.widget.Midget.fabList;
import static com.wk.mobile.base.client.widget.Midget.label;

public class CategoriesViewImpl extends CRUDViewImpl implements CategoriesView {

    public CategoriesViewImpl(ClientFactory clientFactory) {
        super(clientFactory);
    }

    @Override
    protected void editClicked(Record record) {
        CategoryEditEvent.fire(record.getAttributeAsInt("id"), clientFactory.getEventBus());
    }

    @Override
    protected Widget displayLine1(Record record) {
        return label().text(record.getAttribute("name")).get();
    }

    @Override
    protected Widget displayLine2(Record record) {
        if (record.getAttributeAsInt("type") == -1) {
            return label().fontSize(0.7, Style.Unit.EM).text(clientFactory.getConstants().expense()).get();
        }
        else {
            return label().fontSize(0.7, Style.Unit.EM).text(clientFactory.getConstants().income()).get();
        }
    }

    @Override
    protected Widget displayLine4(Record record) {
        return label().get();
    }

    @Override
    protected Widget displayLine3(Record record) {
        return label().get();
    }


    @Override
    public void updateFAB() {
        clientFactory.getFAB().clear();
        clientFactory.getFAB().add(FAB.createCategoryAddFABItem(0, clientFactory));
        clientFactory.getFAB().add(fabList()
                .add(FAB.createAccountAddFABItem(2, clientFactory))
                .add(FAB.createTransactionAddFABItem(1, clientFactory))
                .get());
    }


    @Override
    protected String cutOutDescription() {
        return clientFactory.getConstants().createNewExpenseOrIncomeCategoriesUsingTheRedAddButtonAtTheBottomRightOfTheScreen();
    }

    @Override
    protected String cutOutTitle() {
        return clientFactory.getConstants().noCategoriesYet();
    }


}

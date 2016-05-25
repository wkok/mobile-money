package com.wk.mobile.money.client.event;

import com.google.gwt.place.shared.Place;
import com.google.web.bindery.event.shared.Event;
import com.google.web.bindery.event.shared.EventBus;
import com.google.web.bindery.event.shared.HandlerRegistration;

public class AccountAddEvent extends Event<AccountAddEvent.Handler> {

    public static Type<Handler> TYPE = new Type<Handler>();

    private Place returnPlace;

    public AccountAddEvent() {
    }

    public AccountAddEvent(Place returnPlace) {
        this.returnPlace = returnPlace;
    }

    public interface Handler {
        void onEvent(AccountAddEvent event);
    }

    public static void fire(EventBus eventBus) {
        eventBus.fireEvent(new AccountAddEvent());
    }

    public static void fire(EventBus eventBus, Place returnPlace) {
        eventBus.fireEvent(new AccountAddEvent(returnPlace));
    }

    public static HandlerRegistration register(EventBus eventBus, Handler handler) {
        return eventBus.addHandler(TYPE, handler);
    }

    @Override
    public Type<Handler> getAssociatedType() {
        return TYPE;
    }

    @Override
    protected void dispatch(Handler handler) {
        handler.onEvent(this);
    }

    public Place getReturnPlace() {
        return returnPlace;
    }
}

package com.wk.mobile.money.client.event;

import com.google.gwt.place.shared.Place;
import com.google.web.bindery.event.shared.Event;
import com.google.web.bindery.event.shared.EventBus;
import com.google.web.bindery.event.shared.HandlerRegistration;

public class CategoryAddEvent extends Event<CategoryAddEvent.Handler> {

    public static Type<Handler> TYPE = new Type<Handler>();

    private Place returnPlace;

    public CategoryAddEvent() {
    }

    public CategoryAddEvent(Place returnPlace) {
        this.returnPlace = returnPlace;
    }

    public interface Handler {
        void onEvent(CategoryAddEvent event);
    }

    public static void fire(EventBus eventBus) {
        eventBus.fireEvent(new CategoryAddEvent());
    }

    public static void fire(EventBus eventBus, Place returnPlace) {
        eventBus.fireEvent(new CategoryAddEvent(returnPlace));
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

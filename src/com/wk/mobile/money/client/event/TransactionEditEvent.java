package com.wk.mobile.money.client.event;

import com.google.web.bindery.event.shared.Event;
import com.google.web.bindery.event.shared.EventBus;
import com.google.web.bindery.event.shared.HandlerRegistration;

public class TransactionEditEvent extends Event<TransactionEditEvent.Handler> {

    private Long id;

    public static Type<Handler> TYPE = new Type<Handler>();

    public TransactionEditEvent(Long id) {
        this.id = id;
    }

    public interface Handler {
        void onEvent(TransactionEditEvent event);
    }

    public static void fire(long id, EventBus eventBus) {
        eventBus.fireEvent(new TransactionEditEvent(id));
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

    public Long getId() {
        return id;
    }
}

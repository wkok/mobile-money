package com.wk.mobile.money.client.event;

import com.google.web.bindery.event.shared.Event;
import com.google.web.bindery.event.shared.EventBus;
import com.google.web.bindery.event.shared.HandlerRegistration;

public class AccountsEvent extends Event<AccountsEvent.Handler> {

  public static Type<Handler> TYPE = new Type<Handler>();

	public interface Handler {
		void onEvent(AccountsEvent event);
	}

	public static void fire(EventBus eventBus) {
		eventBus.fireEvent(new AccountsEvent());
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


}

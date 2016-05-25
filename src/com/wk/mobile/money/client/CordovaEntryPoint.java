package com.wk.mobile.money.client;

import com.google.gwt.core.client.EntryPoint;

/**
 * User: werner
 * Date: 15/12/01
 * Time: 7:47 PM
 */
public abstract class CordovaEntryPoint implements EntryPoint {

    @Override
    public final native void onModuleLoad() /*-{
        var self = this;
        if ($wnd.isDeviceReady) self.@com.wk.mobile.money.client.CordovaEntryPoint::onDeviceReady()();
        else {
            var listener = $entry(function () {
                $doc.removeEventListener("deviceready", listener, false);
                self.@com.wk.mobile.money.client.CordovaEntryPoint::onDeviceReady()();
            });
            $doc.addEventListener("deviceready", listener, false);
        }
    }-*/;

    protected abstract void onDeviceReady();
}

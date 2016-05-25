package com.wk.mobile.money.server.ds;

import com.isomorphic.datasource.DSRequest;
import com.isomorphic.datasource.DSResponse;
import com.isomorphic.rpc.RPCManager;
import com.wk.enterprise.server.ds.DSDashboard;
import com.wk.enterprise.server.ds.DSUserGroup;
import com.wk.enterprise.server.util.Assigned;
import com.wk.enterprise.server.util.DS;

/**
 * User: werner
 * Date: 16/01/04
 * Time: 12:54 PM
 */
public class DSTransaction {

    public static DSResponse update(DSRequest dsRequest, RPCManager rpcManager) throws Exception {

        removeIncludeFromFields(dsRequest);

        return dsRequest.execute();
    }

    private static void removeIncludeFromFields(DSRequest dsRequest) {
        dsRequest.getValues().remove("category_name");
        dsRequest.getValues().remove("account_name");
    }

    public static DSResponse validate(DSRequest dsRequest, RPCManager rpcManager) throws Exception {

        removeIncludeFromFields(dsRequest);

        return dsRequest.execute();
    }

}

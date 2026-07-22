package com.oneautomation.framework.base;

import com.oneautomation.framework.context.CacheMechanism;

public class CommonAutomation {

    protected CacheMechanism cache = CacheMechanism.getInstance();

    public void clearProps() {
        cache.clear();
    }
}

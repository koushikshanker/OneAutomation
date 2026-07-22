package com.oneautomation.framework.context;

import java.util.concurrent.ConcurrentHashMap;

public class CacheMechanism {

    private static final ThreadLocal<CacheMechanism> instance = ThreadLocal.withInitial(CacheMechanism::new);
    private final ConcurrentHashMap<String, String> cacheStore = new ConcurrentHashMap<>();

    private CacheMechanism() {
    }

    public static CacheMechanism getInstance() {
        return instance.get();
    }

    public void addResponse(String key, String value) {
        cacheStore.put(key, value);
    }

    public String get(String key) {
        return cacheStore.get(key);
    }

    public boolean exists(String key) {
        return cacheStore.containsKey(key);
    }

    public void remove(String key) {
        cacheStore.remove(key);
    }

    public void clear() {
        cacheStore.clear();
    }
}

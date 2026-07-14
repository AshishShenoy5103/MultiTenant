package com.example.payment.context;

public class TenantContext {
    public static final ThreadLocal<String> CURRENT_THREAD = new ThreadLocal<>();

    public static void setTennatId(String tennatId) {
        CURRENT_THREAD.set(tennatId);
    }

    public static String getTennatId() {
        return CURRENT_THREAD.get();
    }

    public static void clear() {
        CURRENT_THREAD.remove();
    }
}

package com.fc.jisx.jlog;

/**
 * Created by jisx on 2017/6/21.
 */

public enum JLogLevel {
     ALL("ALL"),VERBOSE("FINE"), DEBUG("CONFIG"), INFO("INFO"), WARN("WARNING"), ERROR("SEVERE");

    private String mName;

    JLogLevel(String name) {
        this.mName = name;
    }

    @Override
    public String toString() {
        return mName;
    }
}

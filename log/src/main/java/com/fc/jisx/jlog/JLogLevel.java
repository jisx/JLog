package com.fc.jisx.jlog;

/**
 * Created by jisx on 2017/6/21.
 */

public enum JLogLevel {
    VERBOSE("FINE", 2), DEBUG("CONFIG", 3), INFO("INFO", 4), WARN("WARNING", 5), ERROR("SEVERE", 6);

    private String mName;
    private int mLevel;

    JLogLevel(String name, int level) {
        this.mName = name;
        this.mLevel = level;
    }

    @Override
    public String toString() {
        return mName;
    }

    public int getLevel() {
        return mLevel;
    }

}

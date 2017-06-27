package com.fc.jisx.jlog;

/**
 * Created by jisx on 2017/6/21.
 */

public enum JLogLevel {
     ALL("ALL",0),VERBOSE("FINE",1), DEBUG("CONFIG",2), INFO("INFO",3), WARN("WARNING",4), ERROR("SEVERE",5);

    private String mName;
    private int mLevel;

    JLogLevel(String name,int level) {
        this.mName = name;
        this.mLevel = level;
    }

    @Override
    public String toString() {
        return mName;
    }

    public int getLevel(){
        return mLevel;
    }
}

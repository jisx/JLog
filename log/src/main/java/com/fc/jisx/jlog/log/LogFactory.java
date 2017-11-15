package com.fc.jisx.jlog.log;


import com.fc.jisx.jlog.JLogType;

import java.util.HashMap;

/**
 * Created by jisx on 2017/6/21.
 */

public enum LogFactory {

    INSTANCE {
        @Override
        public BaseLog getLog(JLogType type) {
            return log.get(type);
        }
    };

    public abstract BaseLog getLog(JLogType type);

    private static HashMap<JLogType, BaseLog> log = new HashMap<>();

    static {
        log.put(JLogType.JSON, new JsonLog());
        log.put(JLogType.TEXT, new TextLog());
        log.put(JLogType.XML, new XmlLog());
    }

}

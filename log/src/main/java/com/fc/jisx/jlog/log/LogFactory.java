package com.fc.jisx.jlog.log;


import com.fc.jisx.jlog.JBuilder;
import com.fc.jisx.jlog.JLog;
import com.fc.jisx.jlog.JLogType;

import java.util.HashMap;

/**
 * Created by jisx on 2017/6/21.
 */

public enum LogFactory {

    ;

    private static HashMap<JLogType, BaseLog> log;

    static BaseLog baseLog;

    static {
        log = new HashMap<>();
        log.put(JLogType.JSON, new JsonLog(new JBuilder()));
        log.put(JLogType.TEXT, new TextLog(new JBuilder()));
        log.put(JLogType.XML, new XmlLog(new JBuilder()));
    }

    public static BaseLog create(Object object, JBuilder builder) {
        baseLog = log.get(JLogType.JSON);

        if (log.get(JLogType.JSON).isSelfType(object)) {
            baseLog.setBuilder(builder);
            return baseLog;
        }

        baseLog = log.get(JLogType.XML);

        if (log.get(JLogType.XML).isSelfType(object)) {
            baseLog.setBuilder(builder);
            return baseLog;
        }

        baseLog = log.get(JLogType.TEXT);

        baseLog.setBuilder(builder);
        return baseLog;
    }
}

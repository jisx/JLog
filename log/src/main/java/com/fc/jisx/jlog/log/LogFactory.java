package com.fc.jisx.jlog.log;


import com.fc.jisx.jlog.JLogType;

import java.util.HashMap;

/**
 * Created by jisx on 2017/6/21.
 */

public class LogFactory {

    private static HashMap<JLogType, BaseLog> log;

    static {
        log = new HashMap<>();
        log.put(JLogType.FILE, new FileLog());
        log.put(JLogType.JSON, new JsonLog());
        log.put(JLogType.XML, new XmlLog());
        log.put(JLogType.TEXT, new TextLog());
    }

    public static BaseLog create(JLogType type) {
        return log.get(type);
    }
}

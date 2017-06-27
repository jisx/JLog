package com.fc.jisx.jlog.log;


import com.fc.jisx.jlog.JBuilder;
import com.fc.jisx.jlog.JLog;

import java.util.HashMap;

/**
 * Created by jisx on 2017/6/21.
 */

public class LogFactory {

    private static HashMap<JLog, BaseLog> log;

    public static BaseLog create(JLog type, JBuilder builder) {
        if (log == null)
            log = new HashMap<>();

        if (log.get(type) == null) {
            switch (type) {
                case JSON:
                    log.put(JLog.JSON, new JsonLog(builder));
                    break;

                case TEXT:
                    log.put(JLog.TEXT, new TextLog(builder));
                    break;

                case XML:
                    log.put(JLog.XML, new XmlLog(builder));
                    break;
            }

        }

        log.get(type).setBuilder(builder);

        return log.get(type);
    }
}

package com.fc.jisx.jlog.log;


import com.fc.jisx.jlog.JBuilder;
import com.fc.jisx.jlog.JLog;
import com.fc.jisx.jlog.JLogLevel;
import com.fc.jisx.jlog.JLogUtil;
import com.fc.jisx.jlog.RuntimeType;

/**
 * Created by jisx on 2017/6/21.
 */

public class TextLog extends BaseLog {

    public TextLog(JBuilder builder) {
        super(builder);
    }

    @Override
    public String parseToString(Object object) {
        return JLogUtil.parseObjectToString(object);
    }

    @Override
    public boolean isSelfType(Object value) {
        return true;
    }
}

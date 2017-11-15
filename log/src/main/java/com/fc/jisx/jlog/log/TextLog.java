package com.fc.jisx.jlog.log;


import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.util.ArrayList;

/**
 * Created by jisx on 2017/6/21.
 */

public class TextLog extends BaseLog {

    @Override
    public String parseToString(Object object) {
        return parseObjectToString(object);
    }


    public String parseThrowableToString(Throwable t) {
        Writer result = new StringWriter();
        PrintWriter printWriter = new PrintWriter(result);
        t.printStackTrace(printWriter);
        return result.toString();
    }

    public String parseObjectToString(Object object) {
        if (object instanceof Throwable) {
            return parseThrowableToString((Throwable) object);
        } else {
            return object.toString();
        }
    }

}

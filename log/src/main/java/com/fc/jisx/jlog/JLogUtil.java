package com.fc.jisx.jlog;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;

/**
 * Created by zhaokaiqiang on 15/12/11.
 */
public class JLogUtil {

    public static String parseThrowableToString(Throwable t) {
        Writer result = new StringWriter();
        PrintWriter printWriter = new PrintWriter(result);
        t.printStackTrace(printWriter);
        return result.toString();
    }

    public static String parseObjectToString(Object object) {
        if (object instanceof Throwable) {
            return parseThrowableToString((Throwable) object);
        } else {
            return object.toString();
        }
    }

}

package com.fc.jisx.jlog.formatter;

import com.fc.jisx.jlog.FormatterType;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;


/**
 * 格式化异常对象
 *
 * @author jisx
 * @date Created in 15:50 2018/1/5
 * @modify By:
 */
public class ThrowFormatter extends Formatter<Throwable> {

    @Override
    public String format(Throwable t, FormatterType formatterType) {
        return parseThrowableToString(t);
    }

    public String parseThrowableToString(Throwable t) {
        Writer result = new StringWriter();
        PrintWriter printWriter = new PrintWriter(result);
        t.printStackTrace(printWriter);
        return result.toString();
    }

}

package com.fc.jisx.jlog.formatter;

import com.fc.jisx.jlog.FormatterType;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;


/**
 * 格式化异常，把异常信息转换成字符串
 *
 * @author jisx
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

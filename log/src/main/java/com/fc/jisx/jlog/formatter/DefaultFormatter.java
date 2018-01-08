package com.fc.jisx.jlog.formatter;

import com.fc.jisx.jlog.FormatterType;

/*
 * 
 *
 * @author jisx
 * @date Created in 10:42 2018/1/5
 * @modify By:
 */
public class DefaultFormatter extends Formatter<Object> {

    @Override
    public String format(Object object, FormatterType formatterType) {
        return object.toString();
    }
}

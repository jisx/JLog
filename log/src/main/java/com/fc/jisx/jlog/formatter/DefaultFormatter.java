package com.fc.jisx.jlog.formatter;

import com.fc.jisx.jlog.FormatterType;

/**
 * 如果是未知的类型，则使用{@link Object#toString()} 方法进行转换
 *
 * @author jisx
 */
public class DefaultFormatter extends Formatter<Object> {

    @Override
    public String format(Object object, FormatterType formatterType) {
        return object.toString();
    }
}

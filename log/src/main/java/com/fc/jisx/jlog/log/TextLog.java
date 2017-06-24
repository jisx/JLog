package com.fc.jisx.jlog.log;


import com.fc.jisx.jlog.JLogLevel;
import com.fc.jisx.jlog.JLogUtil;
import com.fc.jisx.jlog.RuntimeType;

/**
 * Created by jisx on 2017/6/21.
 */

public class TextLog extends BaseLog{

    private final int MAX_LENGTH = 4000;

    @Override
    public void print(RuntimeType runtimeType, JLogLevel logLevel, String tag, Object obj) {

        int index = 0;
        String msg = obj.toString();
        int length = msg.length();
        int countOfSub = length / MAX_LENGTH;

        String[] strings = wrapperContent(getStackTraceIndex(tag));
        if (countOfSub > 0) {
            String sub;
            for (int i = 0; i < countOfSub; i++) {
                sub = msg.substring(index, index + MAX_LENGTH);
                if (i == 0) {
                    print(runtimeType, logLevel, tag == null ? strings[0] : tag, strings[1] + "\n" + sub);
                } else {
                    print(runtimeType, logLevel, tag == null ? strings[0] : tag, sub);
                }
                index += MAX_LENGTH;
            }
            print(runtimeType, logLevel, tag == null ? strings[0] : tag, msg.substring(index, length));
        } else {
            print(runtimeType, logLevel, tag == null ? strings[0] : tag, strings[1] + "\n" + msg);
        }
    }


    @Override
    public String parseToString(Object object) {
        return JLogUtil.parseObjectToString(object);
    }

}

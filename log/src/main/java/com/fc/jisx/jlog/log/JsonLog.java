package com.fc.jisx.jlog.log;

import com.fc.jisx.jlog.JBuilder;

/**
 * Created by zhaokaiqiang on 15/11/18.
 */
public class JsonLog extends BaseLog {

    public JsonLog(JBuilder builder) {
        super(builder);
    }

    @Override
    public String parseToString(Object object) {
        return "\n" + formatJson(object.toString());
    }

    /**
     * 得到格式化json数据  退格用\t 换行用\r
     *
     * @param jsonStr json字符串
     * @return 返回格式化的字符串
     */
    public static String formatJson(String jsonStr) {
        int level = 0;
        StringBuffer jsonForMatStr = new StringBuffer();
        for (int i = 0; i < jsonStr.length(); i++) {
            char c = jsonStr.charAt(i);
            if (level > 0 && '\n' == jsonForMatStr.charAt(jsonForMatStr.length() - 1)) {
                jsonForMatStr.append(getLevelStr(level));
            }
            switch (c) {
                case '{':
                case '[':
                    jsonForMatStr.append(c + "\n");
                    level++;
                    break;
                case ',':
                    jsonForMatStr.append(c + "\n");
                    break;
                case '}':
                case ']':
                    jsonForMatStr.append("\n");
                    level--;
                    jsonForMatStr.append(getLevelStr(level));
                    jsonForMatStr.append(c);
                    break;
                default:
                    jsonForMatStr.append(c);
                    break;
            }
        }

        return jsonForMatStr.toString();

    }

    private static String getLevelStr(int level) {
        StringBuffer levelStr = new StringBuffer();
        for (int levelI = 0; levelI < level; levelI++) {
            levelStr.append("\t");
        }
        return levelStr.toString();
    }

}

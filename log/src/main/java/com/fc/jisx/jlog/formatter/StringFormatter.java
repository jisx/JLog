package com.fc.jisx.jlog.formatter;

import android.annotation.SuppressLint;

import com.fc.jisx.jlog.FormatterType;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.StringReader;
import java.io.StringWriter;

import javax.xml.transform.OutputKeys;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

/**
 * File description.
 *
 * @author jisx
 */
public class StringFormatter extends Formatter<String> {

    private static final int INDENT = 4;

    @Override
    public String format(String message, FormatterType formatterType) {

        formatterType = formatterType == null ? FormatterType.DEFAULT : formatterType;

        switch (formatterType) {
            case XML:
                return formatXml(message);
            case JSON:
                return formatJson(message);
            default:
                return message;

        }
    }

    @SuppressLint("NewApi")
    public String formatXml(String xml) {
        String formattedString;
        if (xml == null || xml.trim().length() == 0) {
            throw new FormatException("XML empty.");
        }

        try {
            Source xmlInput = new StreamSource(new StringReader(xml));
            StreamResult xmlOutput = new StreamResult(new StringWriter());
            Transformer transformer = TransformerFactory.newInstance().newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount",
                    String.valueOf(INDENT));
            transformer.transform(xmlInput, xmlOutput);
            formattedString = xmlOutput.getWriter().toString().replaceFirst(">", ">\n");
        } catch (Exception e) {
            throw new FormatException("Parse XML error. XML string:" + xml, e);
        }
        return formattedString;
    }

    private String formatJson(String json) {
        String formattedString = null;
        if (json == null || json.trim().length() == 0) {
            throw new FormatException("JSON empty.");
        }

        try {
            if (json.startsWith("{")) {
                JSONObject jsonObject = new JSONObject(json);
                formattedString = jsonObject.toString(INDENT);
            } else if (json.startsWith("[")) {
                JSONArray jsonArray = new JSONArray(json);
                formattedString = jsonArray.toString(INDENT);
            } else {
                throw new FormatException("JSON should start with { or [, but found " + json);
            }
        } catch (Exception e) {
            throw new FormatException("Parse JSON error. JSON string:" + json, e);
        }
        return formattedString;
    }
}

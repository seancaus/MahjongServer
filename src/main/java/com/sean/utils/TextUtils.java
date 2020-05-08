package com.sean.utils;

import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TextUtils {

    public static boolean isEmpty(String str){
        return null == str || "".equals(str.trim());
    }

    public static String format(String text, Map<String,String>map){
        if(isEmpty(text))return "";

        Pattern p = Pattern.compile("\\{(?<key>\\w+)\\}");
        Matcher m = p.matcher(text);
        String ret = text;
        while (m.find()) {
            String key = m.group("key");
            ret = ret.replaceAll("\\{" + key + "\\}", map.get(key));
        }
        return ret;
    }
}

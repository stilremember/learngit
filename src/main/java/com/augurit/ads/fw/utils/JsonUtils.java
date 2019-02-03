//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.augurit.ads.fw.utils;

import org.codehaus.jackson.type.JavaType;
import org.springside.modules.mapper.JsonMapper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JsonUtils {
    private static JsonMapper mapper = JsonMapper.buildNonNullMapper();

    public JsonUtils() {
    }

    public static String toJson(Object data) {
        return mapper.toJson(data);
    }

    public static String toJsonP(String functionName, Object data) {
        return mapper.toJsonP(functionName, data);
    }

    public static <T> T fromJson(String jsonString, Class<T> beanClass) {
        return mapper.fromJson(jsonString, beanClass);
    }

    public static List<String> listFromJson(String jsonString) {
        return (List)mapper.fromJson(jsonString, List.class);
    }

    public static <T> List<T> listFromJson(String jsonString, Class<T> beanClass) {
        JavaType beanListType = mapper.constructParametricType(List.class, new Class[]{beanClass});
        return (List)mapper.fromJson(jsonString, beanListType);
    }

    public static Map<String, Object> mapFromJson(String jsonString) {
        return (Map)mapper.fromJson(jsonString, HashMap.class);
    }

    public static boolean isArrayJson(String jsonString) {
        boolean result = false;
        if (jsonString != null && jsonString.trim().length() > 0) {
            int length = jsonString.length();
            String firstChar = jsonString.substring(0, 1);
            String lastChar = jsonString.substring(length - 1, length);
            if (firstChar.equals("[") && lastChar.equals("]")) {
                result = true;
            }
        }

        return result;
    }

    public static int existBeansFromJson(String jsonString) {
        if (isArrayJson(jsonString)) {
            return 1;
        } else {
            return isObjectJson(jsonString) ? 0 : -1;
        }
    }

    public static boolean isObjectJson(String jsonString) {
        boolean result = false;
        if (jsonString != null && jsonString.trim().length() > 0) {
            int length = jsonString.length();
            String firstChar = jsonString.substring(0, 1);
            String lastChar = jsonString.substring(length - 1, length);
            if (firstChar.equals("{") && lastChar.equals("}")) {
                result = true;
            }
        }

        return result;
    }
}

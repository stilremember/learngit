//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package org.springside.modules.mapper;

import org.apache.commons.lang.StringUtils;
import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.map.DeserializationConfig.Feature;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.annotate.JsonSerialize.Inclusion;
import org.codehaus.jackson.map.util.JSONPObject;
import org.codehaus.jackson.type.JavaType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

public class JsonMapper {
    private static Logger logger = LoggerFactory.getLogger(JsonMapper.class);
    private ObjectMapper mapper = new ObjectMapper();

    public JsonMapper(Inclusion inclusion) {
        this.mapper.getSerializationConfig().setSerializationInclusion(inclusion);
        this.mapper.configure(Feature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        this.mapper.configure(Feature.FAIL_ON_NUMBERS_FOR_ENUMS, true);
        this.mapper.configure(org.codehaus.jackson.map.SerializationConfig.Feature.WRITE_DATES_AS_TIMESTAMPS, false);
    }

    public static JsonMapper buildNormalMapper() {
        return new JsonMapper(Inclusion.ALWAYS);
    }

    public static JsonMapper buildNonNullMapper() {
        return new JsonMapper(Inclusion.NON_NULL);
    }

    public static JsonMapper buildNonDefaultMapper() {
        return new JsonMapper(Inclusion.NON_DEFAULT);
    }

    public <T> T fromJson(String jsonString, Class<T> clazz) {
        if (StringUtils.isEmpty(jsonString)) {
            return null;
        } else {
            try {
                return this.mapper.readValue(jsonString, clazz);
            } catch (IOException var4) {
                logger.warn("parse json string error:" + jsonString, var4);
                return null;
            }
        }
    }

    public <T> T fromJson(String jsonString, JavaType javaType) {
        if (StringUtils.isEmpty(jsonString)) {
            return null;
        } else {
            try {
                return this.mapper.readValue(jsonString, javaType);
            } catch (IOException var4) {
                logger.warn("parse json string error:" + jsonString, var4);
                return null;
            }
        }
    }

    public JavaType constructParametricType(Class<?> parametrized, Class... parameterClasses) {
        return this.mapper.getTypeFactory().constructParametricType(parametrized, parameterClasses);
    }

    public String toJson(Object object) {
        try {
            return this.mapper.writeValueAsString(object);
        } catch (IOException var3) {
            logger.warn("write to json string error:" + object, var3);
            return null;
        }
    }

    public <T> T update(T object, String jsonString) {
        try {
            return this.mapper.updatingReader(object).readValue(jsonString);
        } catch (JsonProcessingException var4) {
            logger.warn("parse json string" + jsonString + " to object:" + object + " error.", var4);
        } catch (IOException var5) {
            logger.warn("parse json string" + jsonString + " to object:" + object + " error.", var5);
        }

        return null;
    }

    public String toJsonP(String functionName, Object object) {
        return this.toJson(new JSONPObject(functionName, object));
    }

    public void setEnumUseToString(boolean value) {
        this.mapper.getSerializationConfig().set(org.codehaus.jackson.map.SerializationConfig.Feature.WRITE_ENUMS_USING_TO_STRING, value);
        this.mapper.getDeserializationConfig().set(Feature.READ_ENUMS_USING_TO_STRING, value);
    }

    public ObjectMapper getMapper() {
        return this.mapper;
    }
}

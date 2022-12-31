/*
 * Copyright 1999-2022 Alibaba Group Holding Ltd.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.alibaba.nacos.naming.utils;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * json与类互相转换工具
 *
 * @author zhu.lei
 * @version 1.0
 * @date 2020-07-07 15:53
 */
public class JsonUtil {
    /**
     * 默认的mapper，只配置了如果json中的属性在类中不存在则忽略
     */
    public static final ObjectMapper DEFAULT_MAPPER = new ObjectMapper();
    public static final ObjectMapper SNAKE_CASE_MAPPER = new ObjectMapper();

    public static final ObjectMapper SNAKE_CASE_NO_NULL_MAPPER = new ObjectMapper();
    private static final Logger logger = LoggerFactory.getLogger(JsonUtil.class);

    static {
        DEFAULT_MAPPER.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    }

    static {
        SNAKE_CASE_MAPPER.setPropertyNamingStrategy(PropertyNamingStrategies.SNAKE_CASE);
        SNAKE_CASE_MAPPER.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        SNAKE_CASE_NO_NULL_MAPPER.setPropertyNamingStrategy(PropertyNamingStrategies.SNAKE_CASE);
        SNAKE_CASE_NO_NULL_MAPPER.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        SNAKE_CASE_NO_NULL_MAPPER.setSerializationInclusion(JsonInclude.Include.NON_NULL);
    }

    /**
     * Object转json string
     */
    public static String objectToJson(Object object, ObjectMapper mapper) {
        Objects.requireNonNull(object);
        Objects.requireNonNull(mapper);
        try {
            return mapper.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            logger.error("dump object [{}] to json failed, {}", object, e.getMessage());
        }
        return null;
    }

    /**
     * 将json String转换为Map
     */
    public static Map jsonToMap(String json) {
        return jsonToObject(json, Map.class, DEFAULT_MAPPER);
    }

    public static <T> T jsonToObject(String json, Class<T> valueType, ObjectMapper mapper) {
        Objects.requireNonNull(json);
        Objects.requireNonNull(valueType);
        Objects.requireNonNull(mapper);
        try {
            return mapper.readValue(json, valueType);
        } catch (JsonProcessingException e) {
            logger.error("parse json {} to object [{}] failed, {}", json, valueType.getName(), e.getMessage());
        }
        return null;

    }

    public static JsonNode jsonToNode(String json, ObjectMapper mapper) {
        Objects.requireNonNull(json);
        Objects.requireNonNull(mapper);
        try {
            return mapper.readTree(json);
        } catch (JsonProcessingException e) {
            logger.error("parse json {} to json node failed, {}", json, e.getMessage());
        }
        return null;
    }

    public static List jsonToList(String json) {
        return jsonToObject(json, List.class, DEFAULT_MAPPER);
    }
}

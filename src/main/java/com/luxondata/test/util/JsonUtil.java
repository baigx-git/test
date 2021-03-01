package com.luxondata.test.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import lombok.extern.slf4j.Slf4j;

/**
 * TODO
 *
 * @author bgx
 * @date 2021/3/1 16:40
 */
@Slf4j
public class JsonUtil {

    public static String objectToJson(Object obj){
        String json =null;
        ObjectMapper mapper = new ObjectMapper();
        try {
            PropertyNamingStrategy.PascalCaseStrategy pp = new PropertyNamingStrategy.PascalCaseStrategy();
            mapper.setPropertyNamingStrategy(pp);
            json = mapper.writeValueAsString(obj);
        }catch (JsonProcessingException e){
            log.error("json对象转换失败",e);
            e.printStackTrace();
        }
        return json;
    }

    public static<T> T JSONToObj(String jsonStr,Class<T> obj) {
        T t = null;
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            t = objectMapper.readValue(jsonStr,
                    obj);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return t;
    }
}

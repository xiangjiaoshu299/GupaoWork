package com.example.gatewaypersistence;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.route.RouteDefinition;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.StringWriter;
import java.util.*;

@Service
public class RouteDefinitionService {

    /**
     * 用一个内存的map模拟存储。因为懒得建表
     * Map的V用String类型，因为初始化时，用java代码构建一个RouteDefinition对象，貌似还挺复杂的。干脆像老师一样，json反序列化创建
     */
    Map<String, String> routeDefs = new HashMap<>();


    private ObjectMapper objectMapper = new ObjectMapper();

    public RouteDefinitionService(){

        routeDefs.put("first_route", "{\n" +
                "  \"id\": \"first_route\",\n" +
                "  \"predicates\": [{\n" +
                "    \"name\": \"Path\",\n" +
                "    \"args\": {\"_genkey_0\":\"/first\"}\n" +
                "  }],\n" +
                "  \"filters\": [],\n" +
                "  \"uri\": \"https://www.uri-destination.org\",\n" +
                "  \"order\": 0\n" +
                "}]");
    }

    public void delete(String routeDefKey) {
        routeDefs.remove(routeDefKey);
    }

    public void add(RouteDefinition def) {
        StringWriter writer = new StringWriter();
        try {
            objectMapper.writeValue(writer, def);
        } catch (IOException e) {
            e.printStackTrace();
        }

        routeDefs.put(def.getId(), writer.toString());
    }

    public List<RouteDefinition> getList() {
        List<RouteDefinition> list = new ArrayList<>();
        routeDefs.forEach((key, value) ->{
            RouteDefinition def = null;
            try {
                def = objectMapper.readValue(value, RouteDefinition.class);
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }

            list.add(def);
        });

        return list;
    }
}

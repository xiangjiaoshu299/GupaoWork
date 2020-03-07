package model;

import lombok.Data;
import utils.JacksonUtil;

@Data
public class WorkOrder {

    private String id;
    private String addUser;

    private Station station;

    public WorkOrder deepClone(){
        String str = JacksonUtil.BeanToJson(this);
        WorkOrder copy = JacksonUtil.JsonToBean(str, WorkOrder.class);
        return copy;
    }
}

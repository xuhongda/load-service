package com.xu.loadservicewebsocket.component;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;

/**
 * @author xuhongda on 2019/4/2
 * com.xu.loadservicewebsocket.component
 * load-service
 */
@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class People implements Serializable {

    private String name;

    private Integer age;

    private String stationId;
}

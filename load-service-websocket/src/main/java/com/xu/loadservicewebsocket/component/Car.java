package com.xu.loadservicewebsocket.component;

import lombok.Data;
import lombok.ToString;

/**
 * @author xuhongda on 2019/4/2
 * com.xu.loadservicewebsocket.component
 * load-service
 */
@Data
@ToString
public class Car {

    private Integer stationId;

    private String stationName;

    private String deviceId;

    private String vehicleId;

    private String rfId;

    private String plateNo;

    private Integer fuelType;

    private Double price;

    private Double fuelQuantity;

    private Double totalFee;

}
